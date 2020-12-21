package io.github.harvies.charon.spring.boot.web.aop;

import io.github.harvies.charon.result.BaseResult;
import io.github.harvies.charon.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 日志切面类
 */
@Aspect
@Component
@Slf4j
public class LogAspect {
    @PostConstruct
    public void init() {
        log.info("LogAspect init");
    }

    /**
     * ..表示包及子包 该方法代表controller层的所有方法
     */
    @Pointcut("execution(public * io.github.harvies..*.controller..*.*(..))")
    public void controllerMethod() {
    }


    /**
     * 方法执行前
     *
     * @param joinPoint
     * @throws Exception
     */
    @Before("controllerMethod()")
    public void LogRequestInfo(JoinPoint joinPoint) throws Exception {

//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//
//        StringBuilder requestLog = new StringBuilder();
//        Signature signature = joinPoint.getSignature();
//        requestLog.append(((MethodSignature) signature).getMethod().getAnnotation(ApiOperation.class).value()).append("\t")
//                .append("请求信息：").append("URL = {").append(request.getRequestURI()).append("},\t")
//                .append("请求方式 = {").append(request.getMethod()).append("},\t")
//                .append("请求IP = {").append(request.getRemoteAddr()).append("},\t")
//                .append("类方法 = {").append(signature.getDeclaringTypeName()).append(".")
//                .append(signature.getName()).append("},\t");
//
//        // 处理请求参数
//        String[] paramNames = ((MethodSignature) signature).getParameterNames();
//        Object[] paramValues = joinPoint.getArgs();
//        int paramLength = null == paramNames ? 0 : paramNames.length;
//        if (paramLength == 0) {
//            requestLog.append("请求参数 = {} ");
//        } else {
//            requestLog.append("请求参数 = [");
//            for (int i = 0; i < paramLength - 1; i++) {
//                requestLog.append(paramNames[i]).append("=").append(JSONObject.toJSONString(paramValues[i])).append(",");
//            }
//            requestLog.append(paramNames[paramLength - 1]).append("=").append(JSONObject.toJSONString(paramValues[paramLength - 1])).append("]");
//        }
//
//        log.info(requestLog.toString());
    }


    /**
     * 方法执行后
     *
     * @param resultVO
     */
    @AfterReturning(returning = "resultVO", pointcut = "controllerMethod()")
    public void logResultVOInfo(BaseResult resultVO) {
        log.info("请求结果：" + JsonUtils.toJSONString(resultVO));
    }


}
