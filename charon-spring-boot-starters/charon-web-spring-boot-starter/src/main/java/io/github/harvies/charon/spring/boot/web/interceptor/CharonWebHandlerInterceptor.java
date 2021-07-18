package io.github.harvies.charon.spring.boot.web.interceptor;

import com.google.common.base.Stopwatch;
import io.github.harvies.charon.util.RequestTag;
import io.github.harvies.charon.spring.boot.web.utils.IpUtils;
import io.github.harvies.charon.util.JsonUtils;
import io.github.harvies.charon.util.TraceUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CharonWebHandlerInterceptor implements HandlerInterceptor {

    /**
     * 请求计时器
     */
    private static final ThreadLocal<Stopwatch> STOP_WATCH_THREAD_LOCAL = new ThreadLocal<>();


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //请求计时
        STOP_WATCH_THREAD_LOCAL.set(Stopwatch.createStarted());
        //生成traceId
        TraceUtils.getTraceId();
        //记录请求标识
        RequestTag.set(request.getHeader(RequestTag.getTagName()));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            long millis = STOP_WATCH_THREAD_LOCAL.get().elapsed().toMillis();
            log.info("请求参数:[{}] ,耗时 [{}] millis", getDescription(request), millis);
        } finally {
            //请求计时移除
            STOP_WATCH_THREAD_LOCAL.remove();
            //traceId移除
            TraceUtils.removeTraceId();
            //请求标识移除
            RequestTag.remove();
        }
    }

    public String getDescription(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("traceId", TraceUtils.getTraceId());
        map.put("uri", request.getRequestURI());
        map.put("method", request.getMethod());
        map.put("params", request.getParameterMap());
        map.put("client", IpUtils.getIpAddr(request) + ":" + request.getRemotePort());
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, String> headerMap = new HashMap<>();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headerMap.put(headerName, request.getHeader(headerName));
        }
        map.put("header", headerMap);
        return JsonUtils.toJSONString(map);
    }
}
