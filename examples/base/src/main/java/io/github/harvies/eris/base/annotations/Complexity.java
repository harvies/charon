package io.github.harvies.eris.base.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * 复杂度
 */
@Target(ElementType.TYPE)
@Retention(SOURCE)
public @interface Complexity {
    /**
     * 空间
     *
     * @return
     */
    String space();

    /**
     * 时间
     *
     * @return
     */
    String time();
}
