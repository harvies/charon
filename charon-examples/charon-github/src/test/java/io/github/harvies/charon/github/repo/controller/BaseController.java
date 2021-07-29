package io.github.harvies.charon.github.repo.controller;

import io.github.harvies.charon.github.repo.config.Config;
import org.kohsuke.github.GHMyself;
import org.kohsuke.github.GitHub;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * @author harvies
 */
public class BaseController {
    @Autowired
    private Config config;

    protected GitHub getGitHub() throws IOException {
        return GitHub.connectUsingOAuth(config.getOauthAccessToken());
    }

    protected GHMyself getMyself() throws IOException {
        return getGitHub().getMyself();
    }
}
