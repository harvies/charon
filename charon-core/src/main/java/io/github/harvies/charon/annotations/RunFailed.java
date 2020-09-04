package io.github.harvies.charon.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * 运行失败
 * @author harvies
 */
@Target(ElementType.TYPE)
@Retention(SOURCE)
public @interface RunFailed {
}
