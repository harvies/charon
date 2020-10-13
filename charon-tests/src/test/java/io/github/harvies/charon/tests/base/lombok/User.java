package io.github.harvies.charon.tests.base.lombok;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

/**
 * @author harvies
 * @Data注解会生成 各属性GetSet方法, ToString,equals,hashCode,canEqual
 * @Accessors chain = true set方法会返回当前对象
 */
@Data
@Accessors(chain = true)
public class User {
    private Long id;
    /**
     * NonNull 无参构造会变成所有不为空的字段构造函数
     */
    @NonNull
    private String username;
    private String password;
}
