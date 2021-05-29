package io.github.harvies.charon.util.lombok;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author harvies
 */
@Slf4j
public class LombokTest {
    @Test
    public void testNonNull() {
        User user = new User("aaa");
        try {
            user.setUsername(null);
        } catch (Exception e) {
            log.warn("e", e);
        }
    }

    /**
     * @author harvies
     * @ Data注解会生成 各属性GetSet方法, ToString,equals,hashCode,canEqual
     * @ Accessors chain = true set方法会返回当前对象
     */
    @Data
    @Accessors(chain = true)
    private static class User {
        private Long id;
        /**
         * NonNull 无参构造会变成所有不为空的字段构造函数
         */
        @NonNull
        private String username;
        private String password;
    }

}


