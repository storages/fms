package com.fms.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

public class LogAspect {
	
	   public void logArgAndReturn(JoinPoint point, Object returnObj) {

	       //此方法返回的是一个数组，数组中包括request以及ActionCofig等类对象
	      Object[] args = point.getArgs();

       System.out.println("目标参数列表：");

	      if (args != null) {

	            for (Object obj : args) {

	                System.out.println(obj + ",");

	          }

	          System.out.println();

	            System.out.println("执行结果是：" + returnObj);

	       }

	   }

	   public void logArgAndReturn2(JoinPoint point) {

	       //此方法返回的是一个数组，数组中包括request以及ActionCofig等类对象
	      Object[] args = point.getArgs();

       System.out.println("目标参数列表：");

	      if (args != null) {

	            for (Object obj : args) {

	                System.out.println(obj + ",");

	          }

	          System.out.println();

	            System.out.println("开始：");

	       }

	   }

}
