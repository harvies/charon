package io.github.harvies.charon.oss;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
public class GithubOSSProvider implements OSSProvider {
    /**
     * 配置
     */
    private GithubProperties properties;

    public GithubOSSProvider(GithubProperties properties) {
        this.properties = properties;
    }

    /**
     * 登录信息缓存 String key = username + "|" + repositoryName;
     */
    private volatile static Map<String, GHRepository> REPOSITORY_MAP = new HashMap<>();

    @SneakyThrows
    @Override
    public FileDTO upload(@NonNull byte[] bytes, @NonNull String fileName) {
        log.info("begin upload,fileName:[{}]", fileName);
        String username = properties.getUsername();
        GHRepository ghRepository = getGHRepository(username, properties.getRepositoryName());
        log.info("ghRepository:[{}]", ghRepository);

        synchronized (ghRepository) {
            String path = doUpload(bytes, fileName, ghRepository);
            String url = "https://raw.githubusercontent.com/" + username + "/" + properties.getRepositoryName() + "/" + properties.getBranch() + "/" + path;
            log.info("upload success, url:[{}]", url);
            String customDomain = properties.getCustomDomain();
            if (StringUtils.isBlank(customDomain)) {
                customDomain = "https://cdn.jsdelivr.net/gh/" + properties.getUsername() + "/" + properties.getRepositoryName() + "@" + properties.getBranch();
            }
            FileDTO fileDTO = new FileDTO(url);
            if (StringUtils.isNotBlank(customDomain)) {
                fileDTO.setCdnUrl(customDomain + "/" + path);
            }

            return fileDTO;
        }
    }

    private String doUpload(byte[] bytes, String fileName, GHRepository ghRepository) throws IOException {
        /**
         * 1. 获取 Ref
         */
        String ref = ghRepository.getRef("heads/" + properties.getBranch()).getObject().getSha();
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
        String path = Utils.getPath(fileName);
        GHTree ghTree = ghRepository.createTree().shaEntry(path, ghBlob.getSha(), false)
                .baseTree(commit.getTree().getSha())
                .create();
        log.info("ghTree:[{}]", ghTree);

        /**
         * 5. 生成 Commit
         */
        GHCommit ghCommit = ghRepository.createCommit().committer(properties.getCommitterName(), properties.getCommitterEmail(), new Date()).message("commit")
                .tree(ghTree.getSha()).parent(ref).create();
        log.info("ghCommit:[{}]", ghCommit);

        /**
         * 6. 更新 Ref
         */
        ghRepository.getRef("heads/" + properties.getBranch()).updateTo(ghCommit.getSHA1(), false);
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
                    GitHubBuilder gitHubBuilder = new GitHubBuilder().withOAuthToken(properties.getOauthAccessToken(), username);
                    if (properties.getEnableProxy()) {
                        gitHubBuilder.withProxy(new Proxy(properties.getProxyType(), new InetSocketAddress(properties.getProxyHost(), properties.getProxyPort())));
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
