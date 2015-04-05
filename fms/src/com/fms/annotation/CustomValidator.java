/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fms.annotation;

/**
 * 自定义验证
 * 
 * @author gdc
 */
public @interface CustomValidator {
	/**
	 * 验证ID
	 * 
	 * @return
	 */
	public String validatorId();

}
