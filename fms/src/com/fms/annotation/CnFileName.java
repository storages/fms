package com.fms.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.Documented;
@Documented
@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CnFileName {
	 public String name()default "";
	 public String isMustRecord() default"F";
	 public String isObjectModal() default"F";
}


/**
 *@CnFileName(name="ID")
	private int id;
 *
 */
