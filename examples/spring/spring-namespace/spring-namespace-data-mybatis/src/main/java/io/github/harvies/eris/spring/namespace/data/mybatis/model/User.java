package io.github.harvies.eris.spring.namespace.data.mybatis.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author harvies
 */
@Data
public class User implements Serializable {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
