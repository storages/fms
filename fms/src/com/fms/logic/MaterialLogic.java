package com.fms.logic;

import java.util.List;

import com.fms.core.entity.Material;
import com.fms.core.entity.Unit;

public interface MaterialLogic {
	public List<Material> findAllMaterialInfo(String likeStr,String imgExgFlag, Integer index, Integer length);

	public Integer findDataCount(String className, String name);
	public List<Unit> findAllUnit();
	public Material findMaterialById(String id);
	public Material checkMaterial(String hsName,String model,String batchNO);
	public void saveOrUpdate(Material material);
}
