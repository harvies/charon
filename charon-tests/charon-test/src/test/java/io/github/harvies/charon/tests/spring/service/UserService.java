package io.github.harvies.charon.tests.spring.service;

import io.github.harvies.charon.tests.spring.model.User;

/**
 * @author harvies
 */
public interface UserService {
    User getById(Long id);

}
