package com.fms.dao;

import java.util.List;

import com.fms.base.dao.BaseDao;
import com.fms.core.entity.Material;
import com.fms.core.entity.Unit;

public interface MaterialDao extends BaseDao{
	public List<Material> findAllMaterialInfo(String likeStr,String imgExgFlag, Integer index, Integer length);
	public Integer findDataCount(String className, String name);
	public List<Unit> findAllUnit();
	public Material findMaterialById(String id);
	public Material checkMaterial(String hsName,String model,String batchNO);
	void deleteMaterial(String [] ids);
}
