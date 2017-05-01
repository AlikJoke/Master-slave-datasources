package ru.project.master.slave.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.project.master.slave.annotations.ReadOnly;
import ru.project.master.slave.datasources.DataSourceType;
import ru.project.master.slave.routing.DbHolder;

@Aspect
@Component
public class RouteQueryAdvisor {

	@Autowired
	private DbHolder dbHolder;

	@Pointcut(value = "execution(* ru.project.master.slave.dao.impl.*.*(..))")
	public void aroundQueryPointCut() {

	}

	@Around("aroundQueryPointCut()")
	public Object aroundQueryAdvice(ProceedingJoinPoint pjp) throws Throwable {
		if (pjp.getTarget().getClass().getAnnotation(ReadOnly.class) == null)
			return pjp.proceed();
		dbHolder.setDbType(DataSourceType.SLAVE);
		Object res = pjp.proceed();
		dbHolder.clearDbType();
		return res;
	}
}
