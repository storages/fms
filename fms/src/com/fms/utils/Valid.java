/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fms.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 验证
 * 
 * @author gdc
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Valid {
	/**
	 * 中文名
	 * 
	 * @return
	 */
	public String cnName() default "";

	/**
	 * 不允许为空
	 * 
	 * @return
	 */
	public boolean nullable() default true;

	/**
	 * 必须为len长度
	 * 
	 * @return
	 */
	public int len() default -1;

	/**
	 * 最大长度
	 * 
	 * @return
	 */
	public int maxLen() default -1;

	/**
	 * 最小长度
	 * 
	 * @return
	 */
	public int minLen() default -1;

	/**
	 * 日期
	 * 
	 * @return
	 */
	public boolean isDate() default false;

	/**
	 * 数字
	 * 
	 * @return
	 */
	public boolean isNumber() default false;

	/**
	 * 整数
	 * 
	 * @return
	 */
	public boolean isInteger() default false;

}
