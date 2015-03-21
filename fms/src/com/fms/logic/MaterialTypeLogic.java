package com.fms.logic;

import java.util.List;

import com.fms.core.entity.AclUser;
import com.fms.core.entity.MaterialType;

public interface MaterialTypeLogic {
	/**
	 * 查找所有的物料类别
	 * @return
	 */
	List<MaterialType> findAllType(AclUser loginUser,String likeStr);
	
	/**
	 * 保存物料类别
	 * @param typeList
	 * @return
	 */
	List<MaterialType> saveMaterialType(AclUser loginUser,List<MaterialType> typeList);
	
	/**
	 * 根据名称查找物料类型
	 * @param name
	 * @return
	 */
	String findTypeByName(AclUser loginUser,String name);
	
	/**
	 * 根据Id查找物料类型
	 * @param id
	 * @return
	 */
	MaterialType findTypeById(AclUser loginUser,String id);
	
	/**
	 * 根据Id来删除物料类型
	 */
	void delMaterialTypeById(AclUser loginUser,String[] ids);
}
