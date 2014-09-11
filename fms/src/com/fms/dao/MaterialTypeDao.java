package com.fms.dao;

import java.util.List;

import com.fms.base.dao.BaseDao;
import com.fms.core.entity.MaterialType;

public interface MaterialTypeDao extends BaseDao{

	/**
	 * 查找所有的物料类别
	 * @return
	 */
	List<MaterialType> findAllType(String likeStr);
	
	/**
	 * 根据名称查找物料类型
	 * @param name
	 * @return
	 */
	MaterialType findTypeByName(String name);
	
	/**
	 * 根据Id查找物料类型
	 * @param id
	 * @return
	 */
	MaterialType findTypeById(String id);
	
	/**
	 * 根据Id来删除物料类型
	 */
	void delMaterialTypeById (String [] ids);
	
}
