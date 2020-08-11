package io.github.harvies.charon.spring.boot.web.interceptor;

import com.google.common.base.Stopwatch;
import io.github.harvies.charon.spring.boot.web.utils.HttpServletUtils;
import io.github.harvies.charon.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class CharonWebHandlerInterceptor implements HandlerInterceptor {

    private ThreadLocal<Stopwatch> stopWatchThreadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Stopwatch started = Stopwatch.createStarted();
        stopWatchThreadLocal.set(started);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            long millis = stopWatchThreadLocal.get().elapsed().toMillis();
            log.info("请求参数:[{}] ,耗时 [{}] millis", getDescription(request), millis);
        } finally {
            stopWatchThreadLocal.remove();
        }
    }

    public String getDescription(HttpServletRequest request) {
        return "traceId=" + TraceContext.traceId() +
                ";uri=" + request.getRequestURI() +
                ";method=" + request.getMethod() +
                ";params=" + JsonUtils.toJSONString(request.getParameterMap()) +
                ";client=" + HttpServletUtils.getIp(request) + ":" + request.getRemotePort() +
                ";session=" + request.getRequestedSessionId() +
                ";user=" + request.getRemoteUser();
    }
}
