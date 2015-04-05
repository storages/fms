/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fms.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 转换后对象缓存
 * 
 * @author gdc
 */
@Target(ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ConversionCache {
	/**
	 * 缓存主键
	 * 
	 * @return
	 */
	public String[] keyFiled();

	/**
	 * 多字段组合主键 连接字符
	 * 
	 * @return
	 */
	public String joinChar() default "/";

}
