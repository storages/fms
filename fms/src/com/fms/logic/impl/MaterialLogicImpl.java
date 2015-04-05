package com.fms.logic.impl;

import java.util.List;

import com.fms.core.entity.AclUser;
import com.fms.core.entity.Material;
import com.fms.core.entity.Unit;
import com.fms.dao.MaterialDao;
import com.fms.logic.MaterialLogic;

public class MaterialLogicImpl implements MaterialLogic {
	private MaterialDao materialDao;

	public List<Material> findAllMaterialInfo(AclUser loginUser, String likeStr, String imgExgFlag, Integer index, Integer length) {
		return materialDao.findAllMaterialInfo(likeStr, imgExgFlag, index, length);
	}

	public List<Material> findAllMaterialInfoByHsCode(String likeStr, String imgExgFlag, Integer index, Integer length) {
		return materialDao.findAllMaterialInfoByHsCode(likeStr, imgExgFlag, index, length);
	}

	public MaterialDao getMaterialDao() {
		return materialDao;
	}

	public void setMaterialDao(MaterialDao materialDao) {
		this.materialDao = materialDao;
	}

	public Integer findDataCount(AclUser loginUser, String className, String name) {
		return materialDao.findDataCount(className, name);
	}

	public List<Unit> findAllUnit(AclUser loginUser) {
		return materialDao.findAllUnit();
	}

	public Material findMaterialById(AclUser loginUser, String id) {
		return materialDao.findMaterialById(id);
	}

	public Material checkMaterial(AclUser loginUser, String hsName, String model, String batchNO) {
		return materialDao.checkMaterial(hsName, model, batchNO);
	}

	public void saveOrUpdate(AclUser loginUser, Material material) {
		materialDao.saveOrUpdate(material);
	}

	public void deleteMaterial(AclUser loginUser, String[] ids) {
		materialDao.deleteMaterial(ids);

	}

	public List<Material> findMaterialById(AclUser loginUser, String[] ids) {
		return materialDao.findMaterialById(ids);
	}

	public String findHsCode(AclUser loginUser, String hsCode) {
		return materialDao.findHsCode(hsCode);
	}

	public Material findMaterialByHsCode(AclUser loginUser, String hsCode) {
		return this.materialDao.findMaterialByHsCode(hsCode);
	}

	public List<Material> findMaterialExgs(AclUser loginUser, String hsCode, String hsName, String hsModel, String imgExgFlag) {
		return this.materialDao.findAllMaterialExgs(hsCode, hsName, hsModel, imgExgFlag);
	}

	public Integer findDataCount(String className, String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
