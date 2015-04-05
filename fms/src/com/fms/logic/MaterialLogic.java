package com.fms.logic;

import java.util.List;

import com.fms.core.entity.AclUser;
import com.fms.core.entity.Material;
import com.fms.core.entity.Unit;

public interface MaterialLogic {
	public List<Material> findAllMaterialInfo(AclUser loginUser, String likeStr, String imgExgFlag, Integer index, Integer length);

	public List<Material> findAllMaterialInfoByHsCode(String likeStr, String imgExgFlag, Integer index, Integer length);

	public Integer findDataCount(String className, String name);

	public Integer findDataCount(AclUser loginUser, String className, String name);

	public List<Unit> findAllUnit(AclUser loginUser);

	public Material findMaterialById(AclUser loginUser, String id);

	public List<Material> findMaterialById(AclUser loginUser, String[] ids);

	public Material checkMaterial(AclUser loginUser, String hsName, String model, String batchNO);

	public void saveOrUpdate(AclUser loginUser, Material material);

	void deleteMaterial(AclUser loginUser, String[] ids);

	public String findHsCode(AclUser loginUser, String hsCode);

	public Material findMaterialByHsCode(AclUser loginUser, String hsCode);

	List<Material> findMaterialExgs(AclUser loginUser, String hsCode, String hsName, String hsModel, String imgExgFlag);
}
