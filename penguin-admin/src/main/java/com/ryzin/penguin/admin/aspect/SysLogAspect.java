package com.ryzin.penguin.admin.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.ryzin.penguin.admin.model.SysLog;
import com.ryzin.penguin.admin.service.SysLogService;
import com.ryzin.penguin.admin.util.HttpContextUtils;
import com.ryzin.penguin.admin.util.IPUtils;
import com.ryzin.penguin.admin.util.ShiroUtils;


/**
 * 系统日志，切面处理类
 */
@Aspect
@Component
public class SysLogAspect {
	
	@Autowired
	private SysLogService sysLogService;
	
	@Pointcut("execution(* com.louis.kitty.*.service.*.*(..))")
	public void logPointCut() { 
		
	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		// 执行方法
		Object result = point.proceed();
		// 执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;
		// 保存日志
		saveSysLog(point, time);
		return result;
	}

	private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		SysLog sysLog = new SysLog();
		
//		Method method = signature.getMethod();
//		com.louis.merak.admin.annotation.SysLog syslogAnno = method.getAnnotation(com.louis.merak.admin.annotation.SysLog.class);
//		if(syslogAnno != null){
//			//注解上的描述
//			sysLog.setOperation(syslogAnno.value());
//		}

		// 请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		sysLog.setMethod(className + "." + methodName + "()");

		// 请求的参数
		Object[] args = joinPoint.getArgs();
		try{
			String params = JSONObject.toJSONString(args[0]);
			sysLog.setParams(params);
		} catch (Exception e){
		}

		// 获取request
		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
		// 设置IP地址
		sysLog.setIp(IPUtils.getIpAddr(request));

		// 用户名
		String username = ShiroUtils.getUserName();
		sysLog.setUsername(username);
		
		// 执行时长(毫秒)
		sysLog.setTime(time);
		
		// 保存系统日志
//		sysLogService.save(sysLog);
	}

	
}