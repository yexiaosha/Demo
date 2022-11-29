package com.yhdemo.demo.handler;

import com.alibaba.fastjson.JSON;
import com.yhdemo.demo.pojo.User;
import com.yhdemo.demo.pojo.param.LoginParam;
import com.yhdemo.demo.service.LoginService;
import com.yhdemo.demo.vo.ErrorCode;
import com.yhdemo.demo.vo.Result;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 用户登录拦截器
 * @author wyh
 * @date 2022/11/21 17:29
 */

@Component
public class UserLoginInterceptor implements HandlerInterceptor {

    @Resource
    private LoginService loginService;

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

       String token = request.getHeader("Authorization");
       if (token == null){
           Result result = Result.fail(ErrorCode.ACCOUNT_NOT_AUTHORIZATION.getCode(), ErrorCode.ACCOUNT_NOT_AUTHORIZATION.getMsg());
           response.setContentType("application/json;charset=utf-8");
           response.getWriter().print(JSON.toJSONString(result));
           return false;
       }
        LoginParam loginParam = loginService.checkToken(token);
        if (loginParam == null){
            Result result = Result.fail(ErrorCode.ACCOUNT_NOT_AUTHORIZATION.getCode(), ErrorCode.ACCOUNT_NOT_AUTHORIZATION.getMsg());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }

        return true;
    }
}
