package io.github.harvies.charon.spring.boot.web.interceptor;

import com.google.common.base.Stopwatch;
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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Stopwatch started = Stopwatch.createStarted();
        stopWatchThreadLocal.set(started);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        try {
            long millis = stopWatchThreadLocal.get().elapsed().toMillis();
            log.info("web request description:[{}] ,cast [{}] millis", getDescription(request), millis);
        } finally {
            stopWatchThreadLocal.remove();
        }
    }

    public String getDescription(HttpServletRequest httpServletRequest) {
        StringBuilder sb = new StringBuilder();
        sb.append("traceId=").append(TraceContext.traceId());
        sb.append("uri=").append(httpServletRequest.getRequestURI());
        sb.append(";method=").append(httpServletRequest.getMethod());
        sb.append(";params=").append(JsonUtils.toJSONString(httpServletRequest.getParameterMap()));
        sb.append(";client=").append(httpServletRequest.getRemoteAddr() + ":" + httpServletRequest.getRemotePort());
        sb.append(";session=").append(httpServletRequest.getRequestedSessionId());
        sb.append(";user=").append(httpServletRequest.getRemoteUser());
        return sb.toString();
    }
}
