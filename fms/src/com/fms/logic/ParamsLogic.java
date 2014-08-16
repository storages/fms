package com.fms.logic;

import com.fms.core.entity.ParameterSet;

public interface ParamsLogic {

	/**
	 * 获取参数设置的值
	 * @return
	 */
	public ParameterSet getParameterValue();
	
	/**
	 * 保存参数设置
	 * @param param
	 */
	public void saveParameterValue(ParameterSet param);
}
