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
 * 字段
 * 
 * @author gdc
 */
@Target(ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ConvertFields {

	public ConvertField[] value();

}
