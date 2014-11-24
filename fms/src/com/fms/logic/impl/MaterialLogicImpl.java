package com.fms.logic.impl;

import java.util.List;

import com.fms.core.entity.Material;
import com.fms.core.entity.Unit;
import com.fms.dao.MaterialDao;
import com.fms.logic.MaterialLogic;

public class MaterialLogicImpl implements MaterialLogic{
private MaterialDao materialDao;
	
	public List<Material> findAllMaterialInfo(String likeStr,String imgExgFlag, Integer index, Integer length) {
		return materialDao.findAllMaterialInfo(likeStr, imgExgFlag, index, length);
	}

	public MaterialDao getMaterialDao() {
		return materialDao;
	}

	public void setMaterialDao(MaterialDao materialDao) {
		this.materialDao = materialDao;
	}

	public Integer findDataCount(String className, String name) {
		return materialDao.findDataCount(className, name);
	}

	public List<Unit> findAllUnit() {
		return materialDao.findAllUnit();
	}

	public Material findMaterialById(String id) {
		return materialDao.findMaterialById(id);
	}

	public Material checkMaterial(String hsName, String model, String batchNO) {
		return materialDao.checkMaterial(hsName, model, batchNO);
	}

	public void saveOrUpdate(Material material) {
		materialDao.saveOrUpdate(material);
	}

	public void deleteMaterial(String[] ids) {
		materialDao.deleteMaterial(ids);
		
	}

	public List<Material> findMaterialById(String[] ids) {
		return materialDao.findMaterialById(ids);
	}
	public String findHsCode(String hsCode){
		return materialDao.findHsCode(hsCode);
	}

	public Material findMaterialByHsCode(String hsCode) {
		return this.materialDao.findMaterialByHsCode(hsCode);
	}

}
