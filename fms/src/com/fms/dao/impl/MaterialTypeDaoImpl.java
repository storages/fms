package com.fms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.fms.base.dao.BaseDaoImpl;
import com.fms.core.entity.MaterialType;
import com.fms.dao.MaterialTypeDao;

public class MaterialTypeDaoImpl extends BaseDaoImpl implements MaterialTypeDao{

	public List<MaterialType> findAllType(String likeStr) {
		String hql = "FROM MaterialType a ";
		if(null!=likeStr && !"".equals(likeStr)){
			hql+=" where a.typeName like '%"+likeStr+"%' ";
		}
		hql +=" order by a.typeName";
		return this.find(hql);
	}

	public MaterialType findTypeByName(String name) {
		String hql = "FROM MaterialType a where a.typeName = ? ";
		List list = new ArrayList();
		list.add(name);
		return (MaterialType) this.findUniqueResult(hql, list.toArray());
	}

	public MaterialType findTypeById(String id) {
		String hql = "FROM MaterialType a where a.id = ? ";
		List list = new ArrayList();
		list.add(id);
		return (MaterialType) this.findUniqueResult(hql, list.toArray());
	}

	public void delMaterialTypeById(String[] ids) {
		String hql = "DELETE FROM MaterialType a where a.id = ? ";
		List param = new ArrayList();
		param.add(ids[0]);
		for(int i = 1 ; i < ids.length ; i++){
			hql+=" or a.id = ? ";
			param.add(ids[i]);
		}
		this.batchUpdateOrDelete(hql, param.toArray());
	}

}
