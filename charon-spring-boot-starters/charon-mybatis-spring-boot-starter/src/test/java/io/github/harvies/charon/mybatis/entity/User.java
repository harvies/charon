package io.github.harvies.charon.mybatis.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author harvies
 */
@Data
//链式调用
@Accessors(chain = true)
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
