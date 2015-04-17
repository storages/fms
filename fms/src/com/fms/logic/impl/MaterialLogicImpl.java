package com.fms.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fms.commons.ImgExgFlag;
import com.fms.core.entity.AclUser;
import com.fms.core.entity.Material;
import com.fms.core.entity.MaterialType;
import com.fms.core.entity.Unit;
import com.fms.dao.MaterialDao;
import com.fms.dao.MaterialTypeDao;
import com.fms.logic.MaterialLogic;
import com.fms.temp.TempMater;

public class MaterialLogicImpl implements MaterialLogic {
	private MaterialDao materialDao;
	private MaterialTypeDao dao;

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

	public MaterialTypeDao getDao() {
		return dao;
	}

	public void setDao(MaterialTypeDao dao) {
		this.dao = dao;
	}

	public Integer findDataCount(AclUser loginUser, String className, String name, String imgExgFlag) {
		return materialDao.findDataCount(className, name, imgExgFlag);
	}

	public Integer findDataCountExgs(AclUser loginUser, String className, String hsName, String hsCode, String hsModel, String imgExgFlag) {
		return materialDao.findDataCountExgs(loginUser, className, hsName, hsCode, hsModel, imgExgFlag);
	}

	public List<Unit> findAllUnit(AclUser loginUser) {
		return materialDao.findAllUnit();
	}

	public Material findMaterialById(AclUser loginUser, String id) {
		return materialDao.findMaterialById(id);
	}

	public Material checkMaterial(AclUser loginUser, String hsName, String model, String hsCode) {
		return materialDao.checkMaterial(hsName, model, hsCode);
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

	public List<Material> findMaterialExgs(AclUser loginUser, String hsCode, String hsName, String hsModel, String imgExgFlag, Integer index, Integer length) {
		return this.materialDao.findAllMaterialExgs(hsCode, hsName, hsModel, imgExgFlag, index, length);
	}

	public Integer findDataCount(String className, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TempMater> doValidata(List<TempMater> tdata, AclUser loginUser) {
		// 准备数据
		List<Material> matList = this.materialDao.findAllMaterialInfo(null, null, -1, -1);
		Map<String, Material> materCodeMap = new HashMap<String, Material>();
		Map<String, Material> materNameMap = new HashMap<String, Material>();
		for (Material m : matList) {
			materCodeMap.put(m.getHsCode(), m);
			materNameMap.put(m.getHsName(), m);
		}
		List<Unit> ulist = this.findAllUnit(loginUser);
		Map<String, Unit> uMap = new HashMap<String, Unit>();
		for (Unit u : ulist) {
			uMap.put(u.getName(), u);
		}
		List<MaterialType> typeList = this.dao.findAllType(null);
		Map<String, MaterialType> typeMap = new HashMap<String, MaterialType>();
		for (MaterialType mt : typeList) {
			typeMap.put(mt.getTypeName(), mt);
		}
		// 开始校验
		Map<String, TempMater> selfCodeMap = new HashMap<String, TempMater>();
		Map<String, TempMater> selfNameMap = new HashMap<String, TempMater>();
		if (tdata != null && tdata.size() > 0) {
			for (TempMater temp : tdata) {
				StringBuilder builder = new StringBuilder();
				if (selfCodeMap.get(temp.getHsCode()) != null) {
					builder.append("物料编码【" + temp.getHsCode() + "】在导入的Excel中重复! ");
					temp.setErrorInfo(builder.toString());
					continue;
				} else {
					selfCodeMap.put(temp.getHsCode(), temp);
				}
				if (selfNameMap.get(temp.getHsName()) != null) {
					builder.append("物料名称【" + temp.getHsName() + "】在导入的Excel中重复! ");
					temp.setErrorInfo(builder.toString());
					continue;
				} else {
					selfNameMap.put(temp.getHsName(), temp);
				}
				// 检查必填项是否为空
				System.out.println(ImgExgFlag.descValue(temp.getImgExgFlag()));
				if (null == temp.getImgExgFlag() || "".equals(temp.getImgExgFlag())) {
					builder.append("物料标记不能为空! ");
				} else if (!"原料".equals(temp.getImgExgFlag()) && !"成品".equals(temp.getImgExgFlag())) {
					builder.append("物料标记只能是【原料】或是【成品】! ");
				}
				if (null == temp.getHsCode() || "".equals(temp.getHsCode())) {
					builder.append("物料编码不能为空! ");
				} else if (materCodeMap.get(temp.getHsCode()) != null) {
					builder.append("物料编码在系统中重复! ");
				}
				if (null == temp.getHsName() || "".equals(temp.getHsName())) {
					builder.append("物料名称不能为空! ");
				} else if (materNameMap.get(temp.getHsName()) != null) {
					builder.append("物料名称在系统中重复! ");
				}
				if (null == temp.getMaterialType() || "".equals(temp.getMaterialType())) {
					builder.append("物料类别不能为空! ");
				} else if (typeMap.get(temp.getMaterialType()) == null) {
					builder.append("物料类别在系统中不存在! ");
				}
				if (null == temp.getUnit() || "".equals(temp.getUnit())) {
					builder.append("计量单位不能为空! ");
				} else if (uMap.get(temp.getUnit()) == null) {
					builder.append("计量单位在系统中不存在! ");
				}
				if (null == temp.getLowerQty() || "".equals(temp.getLowerQty())) {
					builder.append("最低库存不能为空! ");
				} else if (temp.getLowerQty() - (-1.0) == 0.0) {
					builder.append("最低库存只能填数字或大于零的数字!");
				}
				temp.setErrorInfo(builder.toString());
			}
		}
		return tdata;
	}

	/**
	 * 保存excel导入的数据
	 */
	public Boolean doSaveExcelData(List<TempMater> data, AclUser loginUser) {
		if (data != null && data.size() > 0) {
			for (TempMater tm : data) {
				if (tm.getErrorInfo() != null && !"".equals(tm.getErrorInfo())) {
					return false;
				}
			}
		}
		try {
			List<Material> resultL = null;
			if (null != data && data.size() > 0) {
				resultL = convertMaterial(data, loginUser);
				this.materialDao.batchSaveOrUpdate(resultL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	private List<Material> convertMaterial(List<TempMater> data, AclUser loginUser) {
		List<Material> mlist = new ArrayList<Material>();
		if (null != data && data.size() > 0) {
			List<Unit> ulist = this.findAllUnit(loginUser);
			Map<String, Unit> uMap = new HashMap<String, Unit>();
			for (Unit u : ulist) {
				uMap.put(u.getName(), u);
			}
			List<MaterialType> typeList = this.dao.findAllType(null);
			Map<String, MaterialType> typeMap = new HashMap<String, MaterialType>();
			for (MaterialType mt : typeList) {
				typeMap.put(mt.getTypeName(), mt);
			}
			for (TempMater tmp : data) {
				Material material = new Material();
				material.setHsCode(tmp.getHsCode());
				material.setHsName(tmp.getHsName());
				material.setModel(tmp.getModel());
				material.setImgExgFlag(ImgExgFlag.parseValue(tmp.getImgExgFlag()));
				material.setColor(tmp.getColor());
				material.setUnit(uMap.get(tmp.getUnit()));
				material.setLowerQty(tmp.getLowerQty());
				material.setMaterialType(typeMap.get(tmp.getMaterialType()));
				mlist.add(material);
			}
		}
		return mlist;
	}
}
