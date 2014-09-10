package com.fms.dao.impl;

import java.util.List;

import com.fms.base.dao.BaseDaoImpl;
import com.fms.core.entity.MaterialType;
import com.fms.dao.MaterialTypeDao;

public class MaterialTypeDaoImpl extends BaseDaoImpl implements MaterialTypeDao{

	public List<MaterialType> findAllType() {
		String hql = "FROM MaterialType a order by a.typeName";
		return this.find(hql);
	}

}
