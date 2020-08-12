package io.github.harvies.eris.spring.namespace.demo.service;

import io.github.harvies.eris.spring.namespace.demo.model.User;

/**
 * @author harvies
 */
public interface UserService {
    User getById(Long id);

}
