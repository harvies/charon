package io.github.harvies.charon.mybatis.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author harvies
 */
@Data
//链式调用
@Accessors(chain = true)
public class User implements Serializable {
    private static final long serialVersionUID = -4721607536018568393L;
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
