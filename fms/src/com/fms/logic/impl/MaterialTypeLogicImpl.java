package com.fms.logic.impl;

import java.util.List;

import com.fms.core.entity.MaterialType;
import com.fms.dao.MaterialTypeDao;
import com.fms.logic.MaterialTypeLogic;

public class MaterialTypeLogicImpl implements MaterialTypeLogic {

	private MaterialTypeDao dao;
	
	public List<MaterialType> findAllType(String likeStr) {
		return dao.findAllType(likeStr);
	}
	
	public List<MaterialType> saveMaterialType(List<MaterialType> typeList) {
		return this.dao.batchSaveOrUpdate(typeList);
	}
	
	
	
	
	public MaterialTypeDao getDao() {
		return dao;
	}
	public void setDao(MaterialTypeDao dao) {
		this.dao = dao;
	}

	public String findTypeByName(String name) {
		MaterialType matType = dao.findTypeByName(name);
		return matType==null?null:matType.getTypeName();
	}

	public MaterialType findTypeById(String id) {
		MaterialType matType = dao.findTypeById(id);
		return matType;
	}

	public void delMaterialTypeById(String[] ids) {
		this.dao.delMaterialTypeById(ids);
	}






	
}
