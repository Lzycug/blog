package com.lzycug.aop.config;

import com.lzycug.aop.pojo.SysLog;
import com.lzycug.aop.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Objects;

/**
 * description：切面类，用于记录操作
 * author：lzyCug
 * date: 2020/4/5 22:34
 */
@Component
@Aspect
public class LogAspect {
    @Autowired
    LogService logService;

    @Autowired
    HttpServletRequest httpServletRequest;

    private Date visitTime; // 访问时间

    private Class aClass; // 访问的类

    private Method method; // 访问的方法

    // 方式二：使用切入点表达式注解的方式，在需要记录操作的的地方使用方法名pt1()
    @Pointcut("execution(* com.lzycug.aop.controller.*.*(..))")
    public void pt1() {
    }

    // 方式三：切入点表达式注解方式，在使用自定义注解的地方执行使用了方法pt2()的具体通知
    @Pointcut("@annotation(com.lzycug.aop.config.LogAop)")
    public void pt2() {
    }

    // 方式一：直接在通知上使用切入点表达式，指定需要记录操作的类或者方法
    @Before("execution(* com.lzycug.aop.controller.*.*(..))")
    public void doBefore(JoinPoint joinPoint) throws NoSuchMethodException {
        // 访问时间
        visitTime = new Date();

        // 获取访问的类
        aClass = joinPoint.getTarget().getClass();

        // 获取访问的方法名称
        String methodName = joinPoint.getSignature().getName();

        // 获取访问方法的参数数组
        Object[] args = joinPoint.getArgs();
        if (ObjectUtils.isEmpty(args)) {
            // 获取无参方法
            method = aClass.getMethod(methodName);
        } else {
            // 获取有参数的方法
            Class[] classes = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classes[i] = args[i].getClass();
            }
            method = aClass.getMethod(methodName, classes);
        }
    }

    @After("execution(* com.lzycug.aop.controller.*.*(..))")
    public void doAfter(JoinPoint joinPoint) {
        // 获取访问的时长
        long time = new Date().getTime() - visitTime.getTime();

        // 获取访问的IP
        String ip = httpServletRequest.getRemoteAddr();

        // 获取访问的url
        if (ObjectUtils.isEmpty(aClass) || ObjectUtils.isEmpty(method) || Objects.equals(aClass, LogAspect.class)) {
            return;
        }
        // 获取类上的映射路径
        Annotation annotation = aClass.getAnnotation(RequestMapping.class);
        RequestMapping requestMapping = null;
        if (annotation instanceof RequestMapping) {
            requestMapping = (RequestMapping) annotation;
        }
        if (ObjectUtils.isEmpty(requestMapping)) {
            return;
        }
        String[] classUrl = requestMapping.value();
        RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
        if (ObjectUtils.isEmpty(methodAnnotation)) {
            return;
        }
        String[] methodUrl = methodAnnotation.value();
        String url = classUrl[0] + "/" + methodUrl[0];

        // 封装日志记录对象
        SysLog sysLog = new SysLog();
        sysLog.setVisitTime(visitTime);
        sysLog.setIp(ip);
        sysLog.setUrl(url);
        sysLog.setExecutionTime(time);
        sysLog.setMethod("[类名] " + aClass.getName() + "[方法名] " + method.getName());

        logService.insert(sysLog);
    }
}
