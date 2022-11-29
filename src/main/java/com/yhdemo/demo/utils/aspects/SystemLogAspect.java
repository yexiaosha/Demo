package com.yhdemo.demo.utils.aspects;

import com.alibaba.fastjson.JSON;
import com.yhdemo.demo.pojo.SysLog;
import com.yhdemo.demo.pojo.param.LoginParam;
import com.yhdemo.demo.service.LogService;
import com.yhdemo.demo.utils.DateUtils;
import com.yhdemo.demo.utils.HttpContextUtils;
import com.yhdemo.demo.utils.IpUtils;
import com.yhdemo.demo.utils.JwtUtils;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 日志切点类
 * @author wyh
 * @date 2022/11/23 10:17
 */
@Aspect
@Component
@Order(0)
@Slf4j
public class SystemLogAspect {

    @Resource
    private LogService logService;

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

    @Pointcut("@annotation(com.yhdemo.demo.utils.aspects.SystemServiceLog)")
    public void serviceAspect() {
    }

    @Pointcut("@annotation(SystemControllerLog)")
    public void controllerAspect() {
    }

    /**
     * 前置通知，拦截controller层记录用户的操作
     * @param joinPoint 切入点
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        String token = request.getHeader("Authorization");
        String name = getUsername(token);
        SystemControllerLog systemControllerLog = ((MethodSignature) (joinPoint.getSignature())).getMethod()
                .getAnnotation(SystemControllerLog.class);
        SysLog sysLog = new SysLog();
        if (systemControllerLog != null) {
            String description = systemControllerLog.value();
            sysLog.setDescription(description);
        }
        try {
            logger.info("===============前置通知开始===============");
            logger.info("请求类型：Controller接口" + request.getMethod());
            logger.info(
                    "请求方法：" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName()));
            logger.info("方法描述：" + sysLog.getDescription());
            if (name == null) {
                name = "游客未登录";
            }
            logger.info("请求用户：" + name);
            logger.info("请求ip：" + IpUtils.getIpAddress(request));

            //存入数据库
            sysLog.setUsername(name);
            sysLog.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName()));
            sysLog.setCreateDate(DateUtils.getPresentTime());
            sysLog.setIp(IpUtils.getIpAddress(request));
            logger.info("请求时间：" + sysLog.getCreateDate());
            logService.addLog(sysLog);
        } catch (Exception e) {
            logger.error("========前置通知异常========");
            logger.error("异常信息：{}", e.getMessage());
        }
    }

    @AfterReturning("controllerAspect()")
    public void doAfter() {
        logger.info("=====请求完成=====");
    }

    /**
     * Service异常处理通知
     * @param joinPoint 切入点
     * @param e         所导致的异常类
     */
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
    public void daAfterThrowing(JoinPoint joinPoint, Throwable e) {
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        SystemServiceLog systemServiceLog = ((MethodSignature) (joinPoint.getSignature())).getMethod()
                .getAnnotation(SystemServiceLog.class);
        SysLog sysLog = new SysLog();
        sysLog.setDescription(systemServiceLog.value());
        StringBuilder params = new StringBuilder();
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                params.append(JSON.toJSONString(joinPoint.getArgs()[i])).append(";");
            }
        }

        try {
            logger.info("========异常日志========");
            logger.info("执行代码：" + e.getClass().getName());
            logger.info("执行信息：" + e.getMessage());
            logger.info("执行方法：" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName()
                    + "()"));
            logger.info("方法描述：" + sysLog.getDescription());
            logger.info("请求人：" + name);
            logger.info("请求ip：" + IpUtils.getIpAddress(request));
            logger.info("请求参数：" + params);
            sysLog.setUsername(name);
            sysLog.setMethod(
                    (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            sysLog.setParams(params.toString());
            sysLog.setCreateDate(DateUtils.getPresentTime());
            sysLog.setIp(IpUtils.getIpAddress(request));
            logService.addLog(sysLog);
        } catch (Exception ex) {
            logger.error("=====异常通知异常=====");
            logger.error("异常信息:{}", ex.getMessage());
        }
    }

    public String getUsername(String token){
        Map<String, Object> map = JwtUtils.verifyToken(token);
        if (map == null){
            return null;
        }

        String userJson = (String) redisTemplate.opsForValue().get("TOKEN_" + token);
        log.info(userJson);
        if (!(StringUtils.hasText(userJson))){
            return null;
        }
        LoginParam loginParam = JSON.parseObject(userJson, LoginParam.class);
        log.info(String.valueOf(loginParam));
        return loginParam.getUsername();
    }
}