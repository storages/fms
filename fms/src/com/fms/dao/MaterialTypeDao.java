package com.fms.dao;

import java.util.List;

import com.fms.base.dao.BaseDao;
import com.fms.core.entity.MaterialType;

public interface MaterialTypeDao extends BaseDao{

	/**
	 * 查找所有的物料类别
	 * @return
	 */
	List<MaterialType> findAllType();
}
