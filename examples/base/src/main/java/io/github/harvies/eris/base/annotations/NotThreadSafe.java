package io.github.harvies.eris.base.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * 非线程安全的类标识
 */
@Target(ElementType.TYPE)
@Retention(SOURCE)
public @interface NotThreadSafe {
}
