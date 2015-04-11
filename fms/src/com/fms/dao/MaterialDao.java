package com.fms.dao;

import java.util.List;

import com.fms.base.dao.BaseDao;
import com.fms.core.entity.AclUser;
import com.fms.core.entity.Material;
import com.fms.core.entity.Unit;

public interface MaterialDao extends BaseDao {
	public List<Material> findAllMaterialInfo(String likeStr, String imgExgFlag, Integer index, Integer length);

	public List<Material> findAllMaterialInfoByHsCode(String likeStr, String imgExgFlag, Integer index, Integer length);

	public Integer findDataCount(String className, String name, String imgExgFlag);

	public Integer findDataCountExgs(AclUser loginUser, String className, String hsName, String hsCode, String hsModel, String imgExgFlag);

	public List<Unit> findAllUnit();

	public Material findMaterialById(String id);

	public List<Material> findMaterialById(String[] ids);

	public Material checkMaterial(String hsName, String model, String batchNO);

	void deleteMaterial(String[] ids);

	String findHsCode(String hsCode);

	public Material findMaterialByHsCode(String hsCode);

	public List<Material> findAllMaterialExgs(String hsCode, String hsName, String hsModel, String imgExgFlag, Integer index, Integer length);
}
