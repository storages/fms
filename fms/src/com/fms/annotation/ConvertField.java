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

import com.fms.utils.Valid;

/**
 * 字段
 * 
 * @author gdc
 */
@Target(ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ConvertField {
	/**
	 * 来源字段 不存在时使用field作为来源字段
	 * 
	 * @return
	 */
	public String[] sourceField();

	/**
	 * 多个来源字段之间的连接字符
	 * 
	 * @return
	 */
	public String separatorChar() default "/";

	/**
	 * 字段名称
	 * 
	 * @return
	 */
	public String field();

	/**
	 * 引用使用@TContextCache的对象
	 * 
	 * @return
	 */
	public boolean refCache() default false;

	/**
	 * 应用转换上下文属性对象数据
	 * 
	 * @return
	 */
	public boolean refContextAttr() default false;

	/**
	 * 值映射表 例如："进口数据":"1","出口数据":"2"
	 * 
	 * @return
	 */
	public String valueMap() default ("");

	/**
	 * 验证器
	 * 
	 * @return
	 */
	public Valid[] valid() default {};

	/**
	 * 自定义验证
	 * 
	 * @return
	 */
	public CustomValidator[] customValid() default {};

}
