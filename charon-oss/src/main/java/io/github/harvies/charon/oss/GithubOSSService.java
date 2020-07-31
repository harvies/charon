package io.github.harvies.charon.oss;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.kohsuke.github.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author harvies
 */
@Slf4j
public class GithubOSSService implements OSSService {
    /**
     * 配置
     */
    private GithubProperties githubProperties;

    public GithubOSSService(GithubProperties githubProperties) {
        this.githubProperties = githubProperties;
    }

    /**
     * 登录信息缓存
     */
    private volatile static Map<String, GHRepository> REPOSITORY_MAP = new HashMap<>();

    @SneakyThrows
    @Override
    public FileDTO upload(byte[] bytes, String fileName) {
        log.info("fileName:[{}]", fileName);
        String username = githubProperties.getUsername();
        GHRepository ghRepository = getGHRepository(username, githubProperties.getRepositoryName());
        log.info("ghRepository:[{}]", ghRepository);

        synchronized (ghRepository) {
            String path = doUpload(bytes, fileName, ghRepository);

            return new FileDTO("https://raw.githubusercontent.com/" + username + "/" + githubProperties.getRepositoryName() + "/" + githubProperties.getBranch() + "/" + path)
                    .setCustomDomainUrl(githubProperties.getCustomDomain() + "/" + path);
        }
    }

    private String doUpload(byte[] bytes, String fileName, GHRepository ghRepository) throws IOException {
        /**
         * 1. 获取 Ref
         */
        String ref = ghRepository.getRef("heads/" + githubProperties.getBranch()).getObject().getSha();
        log.info("ref:[{}]", ref);

        /**
         * 2.获取commit
         */
        GHCommit commit = ghRepository.getCommit(ref);
        log.info("commit:[{}]", commit);

        /**
         * 3. 生成 Blob
         */
        GHBlob ghBlob = ghRepository.createBlob().binaryContent(bytes).create();
        log.info("ghBlob:[{}]", ghBlob);

        /**
         * 4. 生成 tree
         */
        String dateStr = DateFormatUtils.format(System.currentTimeMillis(), "yyyy/MM/dd/yyyymmddHHmmsssss-");
        String path = dateStr + fileName;
        GHTree ghTree = ghRepository.createTree().shaEntry(path, ghBlob.getSha(), false)
                .baseTree(commit.getTree().getSha())
                .create();
        log.info("ghTree:[{}]", ghTree);

        /**
         * 5. 生成 Commit
         */
        GHCommit ghCommit = ghRepository.createCommit().committer(githubProperties.getCommitterName(), githubProperties.getCommitterEmail(), new Date()).message("commit")
                .tree(ghTree.getSha()).parent(ref).create();
        log.info("ghCommit:[{}]", ghCommit);

        /**
         * 6. 更新 Ref
         */
        ghRepository.getRef("heads/" + githubProperties.getBranch()).updateTo(ghCommit.getSHA1(), false);
        return path;
    }

    private GHRepository getGHRepository(String username, String repositoryName) throws IOException {
        String key = username + "|" + repositoryName;
        GHRepository ghRepository = REPOSITORY_MAP.get(key);
        if (ghRepository == null) {
            synchronized (REPOSITORY_MAP) {
                //双重检查锁
                ghRepository = REPOSITORY_MAP.get(key);
                if (ghRepository == null) {
                    //仅使用accessToken参数需要先获取用户信息
                    GitHubBuilder gitHubBuilder = new GitHubBuilder().withOAuthToken(githubProperties.getOauthAccessToken(), username);
                    if (githubProperties.getEnableProxy()) {
                        gitHubBuilder.withProxy(new Proxy(githubProperties.getProxyType(), new InetSocketAddress(githubProperties.getProxyHost(), githubProperties.getProxyPort())));
                    }
                    GitHub gitHub = gitHubBuilder.build();
                    ghRepository = gitHub.getUser(username).getRepository(repositoryName);
                    log.debug("put to map");
                    REPOSITORY_MAP.put(key, ghRepository);
                }
            }
        }
        return ghRepository;
    }
}
