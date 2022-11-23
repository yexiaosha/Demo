package com.yhdemo.demo.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 用户登录拦截器
 * @author wyh
 * @date 2022/11/21 17:29
 */

@Component
public class UserLoginInterceptor implements HandlerInterceptor {

    /**
     * 登录拦截器
     * @param request
     * @param response
     * @param handler
     * @return 是否拦截
     *
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String uri = request.getRequestURI();

        if (request.getSession().getAttribute("username") != null) {
            return true;
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
        return false;
    }
}
