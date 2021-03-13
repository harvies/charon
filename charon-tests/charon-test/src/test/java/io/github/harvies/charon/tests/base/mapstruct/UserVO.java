package io.github.harvies.charon.tests.base.mapstruct;

import lombok.Data;

import java.util.List;

/**
 * @author harvies
 * @Data注解会生成 各属性GetSet方法, ToString,equals,hashCode,canEqual
 * @Accessors chain = true set方法会返回当前对象
 */
@Data
public class UserVO {
    private String id;
    private String username;
    private String passWord;
    private List<String> statusList;
}
