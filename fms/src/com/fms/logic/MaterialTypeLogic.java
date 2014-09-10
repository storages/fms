package com.fms.logic;

import java.util.List;

import com.fms.core.entity.MaterialType;

public interface MaterialTypeLogic {
	/**
	 * 查找所有的物料类别
	 * @return
	 */
	List<MaterialType> findAllType();
}
