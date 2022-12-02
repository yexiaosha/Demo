package com.yhdemo.demo.handler;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 登录的过滤器实现
 * @author wyh
 * @date 2022/11/21 18:31
 */

@Component
@Slf4j
public class LogCostFilter implements Filter {

    public static long time;

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        long start = System.currentTimeMillis();
        chain.doFilter(request, response);
        time = System.currentTimeMillis() - start;
        log.info("该请求响应时间为= " + time + "毫秒");

    }

}
