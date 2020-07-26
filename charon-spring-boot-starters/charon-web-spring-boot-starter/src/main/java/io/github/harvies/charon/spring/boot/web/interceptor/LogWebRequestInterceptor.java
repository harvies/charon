package io.github.harvies.charon.spring.boot.web.interceptor;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class LogWebRequestInterceptor implements WebRequestInterceptor {

    private ThreadLocal<Stopwatch> stopWatchThreadLocal = new ThreadLocal<>();

    @Override
    public void preHandle(WebRequest request) throws Exception {
        Stopwatch started = Stopwatch.createStarted();
        stopWatchThreadLocal.set(started);
    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) throws Exception {

    }

    @Override
    public void afterCompletion(WebRequest request, Exception ex) throws Exception {
        try {
            long millis = stopWatchThreadLocal.get().elapsed().toMillis();
            log.info("web request description:[{}] cast [{}] millis", request.getDescription(true), millis);
        } finally {
            stopWatchThreadLocal.remove();
        }
    }
}
