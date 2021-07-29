package io.github.harvies.charon.tests.base.hystrix.collapse.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * 合并
 * @author harvies
 */
@Target(ElementType.METHOD)
@Retention(SOURCE)
public @interface Merge {
}
