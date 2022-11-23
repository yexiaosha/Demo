package com.yhdemo.demo.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * ip组件
 * @author wyh
 * @date 2022/11/23 14:12
 */
public class IpUtils {

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-real-ip");
        String state = "unknown";

        if (ip == null || ip.length() == 0 || state.equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
            if (ip != null) {
                ip = ip.split(",")[0].trim();
            }
        }

        if (ip == null || ip.length() == 0 || state.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || state.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || state.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }
}
