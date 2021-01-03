package io.github.harvies.charon.config.annotation;

import io.github.harvies.charon.config.BeanRefreshScope;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Scope(value = BeanRefreshScope.SCOPE_REFRESH, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Documented
public @interface RefreshScope {
}
