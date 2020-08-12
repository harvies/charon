package io.github.harvies.eris.spring.boot.cache.service;


import io.github.harvies.eris.spring.boot.cache.model.User;

import java.util.List;

/**
 * @author harvies
 */
public interface UserService {
    User getById(Long id);

    List<User> getByIdList(List<Long> idList);

}
