package io.github.harvies.charon.github.repo.controller;

import com.google.common.collect.Lists;
import io.github.harvies.charon.github.repo.vo.RepoVO;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.GHRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @author harvies
 */
@RestController
@RequestMapping("/repo")
@Slf4j
public class RepoController extends BaseController {

    @RequestMapping("/list")
    public List<RepoVO> list() throws IOException {
        List<GHRepository> ghRepositories = getMyself().listRepositories().asList();
        List<RepoVO> repoVOList = Lists.newArrayList();
        ghRepositories.forEach(ghRepository -> {
            try {
                RepoVO.RepoVOBuilder repoVOBuilder = RepoVO.builder()
                    .url(ghRepository.getUrl())
                    .description(ghRepository.getDescription())
                    .name(ghRepository.getName())
                    .fullName(ghRepository.getFullName())
                    .language(ghRepository.getLanguage())
                    .fork(ghRepository.isFork())
                    ._private(ghRepository.isPrivate())
                    .forkCount(ghRepository.getForks())
                    .stargazersCount(ghRepository.getStargazersCount())
                    .watcherCount(ghRepository.getWatchers())
                    .size(ghRepository.getSize())
                    .defaultBranch(ghRepository.getDefaultBranch())
                    .language(ghRepository.getLanguage())
                    .id(ghRepository.getId())
                    .createdAt(ghRepository.getCreatedAt())
                    .updateAt(ghRepository.getUpdatedAt());
                repoVOList.add(repoVOBuilder.build());
            } catch (Exception e) {
                log.info("e", e);
            }
        });
        return repoVOList;
    }

    @RequestMapping("/delete")
    public void delete(String name) throws IOException {
        getMyself().getRepository(name).delete();
    }

    @RequestMapping("/deleteAllFork")
    public void deleteAllFork() throws IOException {
        getMyself().getAllRepositories().values().forEach(ghRepository -> {
            if (ghRepository.isFork()){
                try {
                    ghRepository.delete();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
