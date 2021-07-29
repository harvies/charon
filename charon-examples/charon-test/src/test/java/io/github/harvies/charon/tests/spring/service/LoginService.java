package io.github.harvies.charon.tests.spring.service;

/**
 * @author harvies
 */
public interface LoginService {
    /**
     * @param username
     * @param password
     * @return
     */
    boolean login(String username, String password);

    void setRunning(boolean b);
}
