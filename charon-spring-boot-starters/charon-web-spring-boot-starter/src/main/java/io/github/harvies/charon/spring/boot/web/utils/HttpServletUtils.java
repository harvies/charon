package io.github.harvies.charon.spring.boot.web.utils;

import javax.servlet.http.HttpServletRequest;

public class HttpServletUtils {

    public static String getIp(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        /**
         * X-Forwarded-For:简称XFF头，它代表客户端，也就是HTTP的请求端真实的IP
         * 只有在通过了HTTP 代理或者负载均衡服务器时才会添加该项
         * 标准格式如下：X-Forwarded-For: client_ip, proxy1_ip, proxy2_ip
         * 此头是可构造的，因此某些应用中应该对获取到的ip进行验证
         */
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }
        /**
         *在多级代理网络中，直接用getHeader("x-forwarded-for")可能获取到的是unknown信息
         *此时需要获取代理代理服务器重新包装的HTTP头信息，
         */
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            //Apache+WebLogic搭配下出现的头
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip)) {
            //Apache+WebLogic搭配下出现的头
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            //可通过http头伪造，是由代理服务器发送的请求头
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            //可通过http头伪造，和X-Forwarded-For格式类似，以“,"分隔
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        return ip;
    }
}
