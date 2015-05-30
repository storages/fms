package com.fms.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fms.commons.ImgExgFlag;
import com.fms.core.entity.AclUser;
import com.fms.core.entity.BomExg;
import com.fms.core.entity.BomImg;
import com.fms.core.entity.BomVersion;
import com.fms.core.entity.Material;
import com.fms.dao.BomDao;
import com.fms.dao.MaterialDao;
import com.fms.logic.BomLogic;
import com.fms.temp.TempBom;
import com.fms.utils.MathUtils;

public class BomLogicImpl implements BomLogic {

	protected BomDao bomDao;
	protected MaterialDao materialDao;
	protected List<BomImg> bomImgs = new ArrayList<BomImg>();

	public List<BomExg> findBomExg(AclUser loginUser, String hsName, String hsCode, String hsModel, Integer index, Integer length) {
		return this.bomDao.findBomExg(hsName, hsCode, hsModel, index, length);
	}

	/**
	 * 按条件查找记录数
	 * 
	 * @param className
	 * @param searchStr
	 * @return
	 */
	public Integer findDataCount(AclUser loginUser, String className, String hsName, String hsCode, String hsModel) {
		return bomDao.findDataCount(className, hsName, hsCode, hsModel);
	}

	public List<BomExg> saveBomExg(AclUser loginUser, List<BomExg> data) {
		return this.bomDao.batchSaveOrUpdate(data);
	}

	public List<TempBom> doValidata(List<TempBom> temps, String exgId, Integer verNo) {
		Map<String, Material> matMap = new HashMap<String, Material>();
		Map<String, BomImg> imgBomMap = new HashMap<String, BomImg>();
		Map<String, TempBom> selfBomMap = new HashMap<String, TempBom>();
		// 准备数据
		List<Material> matList = this.materialDao.findAllMaterialInfo(null, null, ImgExgFlag.IMG, -1, -1);
		for (Material m : matList) {
			String key = m.getHsCode() + "~@~" + m.getBatchNO();
			matMap.put(key, m);
		}
		List<BomImg> imgList = this.findBomImg(null, null, null, exgId, verNo, -1, -1);
		for (BomImg i : imgList) {
			String key = i.getMaterial().getHsCode() + "~@~" + i.getMaterial().getBatchNO();
			imgBomMap.put(key, i);
		}
		// 开始校验
		Map<String, TempBom> selfMap = new HashMap<String, TempBom>();
		for (TempBom tmp : temps) {
			StringBuilder builder = new StringBuilder();
			// 检查必填项是否为空
			if (tmp.getImgCode() == null || "".equals(tmp.getImgCode())) {
				builder.append("BOM原料编码不能为空! ");
			}
			if (tmp.getImgBetchNo() == null || "".equals(tmp.getImgBetchNo())) {
				builder.append("BOM原料批次号不能为空! ");
			}
			if (tmp.getConsumption() == null || "".equals(tmp.getConsumption())) {
				builder.append("BOM原料单耗不能为空! ");
			}
			if (tmp.getWastage() == null || "".equals(tmp.getWastage())) {
				builder.append("BOM原料损耗不能为空! ");
			}
			if (tmp.getConsumption() != null && !"".equals(tmp.getConsumption()) && tmp.getConsumption() == null) {
				builder.append("BOM原料单耗只能是数字! ");
			}
			if (tmp.getWastage() != null && !"".equals(tmp.getWastage()) && tmp.getWastage() == null) {
				builder.append("BOM原料损耗只能是数字! ");
			}

			// 验证导入的Excel文件中是否存在重复数据

			String selfKey = tmp.getImgCode() + "~@~" + tmp.getImgBetchNo();
			if (selfBomMap.get(selfKey) != null) {
				builder.append("导入的Excel文件中数据重复! ");
				continue;
			} else {
				selfBomMap.put(selfKey, tmp);
			}
			// 验证原料在物料清单中是否存在
			String imgKey = tmp.getImgCode() + "~@~" + tmp.getImgBetchNo();
			if (null != imgKey && matMap.get(imgKey) == null) {
				builder.append("原料编码【" + tmp.getImgCode());
				builder.append("】、批次号【" + tmp.getImgBetchNo());
				builder.append("】对应的物料在系统中不存在!");
			}
			// 验证系统中是否有重复
			String key = tmp.getImgCode() + "~@~" + tmp.getImgBetchNo();
			if (imgBomMap.get(key) != null) {
				builder.append("此条信息在系统中已经存在!");
			}
			tmp.setErrorInfo(builder.toString());
		}
		return temps;
	}

	public BomDao getBomDao() {
		return bomDao;
	}

	public void setBomDao(BomDao bomDao) {
		this.bomDao = bomDao;
	}

	public void delBomExgByIds(AclUser loginUser, String[] idArr) {
		try {
			// 首先要删除成品对应的物料Bom
			List<BomImg> imgs = this.bomDao.findBomImgByExgIds(idArr);
			this.bomDao.deleteAll(imgs);
			// 删除版本号
			this.bomDao.deleteAll(this.bomDao.findBomVerSionByExgBomIds(idArr));
			// 再删除成品Bom
			this.bomDao.deleteAll(this.bomDao.findBomExgByIds(idArr));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MaterialDao getMaterialDao() {
		return materialDao;
	}

	public void setMaterialDao(MaterialDao materialDao) {
		this.materialDao = materialDao;
	}

	public List<BomImg> findBomImg(String hsName, String hsCode, String hsModel, String exgId, Integer verNo, Integer index, Integer length) {
		return this.bomDao.findBomImg(hsName, hsCode, hsModel, exgId, verNo, index, length);
	}

	public List<BomVersion> findBomVersion(String exgBomId) {
		return this.bomDao.findBomVersion(exgBomId);
	}

	public Boolean doSaveExcelData(List<TempBom> data, Integer verNo, String bomExgId) {
		for (TempBom tb : data) {
			if (tb.getErrorInfo() != null && !"".equals(tb.getErrorInfo())) {
				return false;
			}
		}
		List<BomImg> resultL = null;
		if (null != data && data.size() > 0) {
			resultL = convertBomImg(data, verNo, bomExgId);
			this.bomDao.batchSaveOrUpdate(resultL);
		}
		return true;
	}

	/**
	 * 转换成BomImg,并保存
	 * 
	 * @return
	 */
	private List<BomImg> convertBomImg(List<TempBom> data, Integer verNo, String bomExgId) {
		List<BomImg> bomImgList = new ArrayList<BomImg>();
		BomVersion bomVersion = this.findBomVersionByVerNo(verNo);
		List<Material> matL = this.materialDao.findAllMaterialInfo(null, null, ImgExgFlag.IMG, -1, -1);
		Map<String, Material> matMap = new HashMap<String, Material>();
		for (Material m : matL) {
			String key = m.getHsCode() + "~@~" + m.getBatchNO();
			matMap.put(key, m);
		}
		for (TempBom tmp : data) {
			BomImg bomImg = new BomImg();
			bomImg.setBomVersion(bomVersion);
			String key2 = tmp.getImgCode() + "~@~" + tmp.getImgBetchNo();
			bomImg.setMaterial(matMap.get(key2));
			bomImg.setUnitConsume(tmp.getConsumption());
			bomImg.setWastRate(tmp.getWastage());
			Double d = MathUtils.subtract(1d, bomImg.getWastRate());
			bomImg.setUnitDosage(MathUtils.dividend(bomImg.getUnitConsume(), d, 3));
			bomImg.setNote(tmp.getNote());
			bomImgList.add(bomImg);
		}
		return bomImgList;
	}

	public Integer findBomVersionNoByHead(String exgId) {
		return this.bomDao.findBomVersionNoByHead(exgId);
	}

	public BomVersion saveBomVersion(BomVersion bomVersion) {
		return (BomVersion) this.bomDao.saveOrUpdateNoCache(bomVersion);
	}

	public BomExg findBomExgById(String id) {
		return this.bomDao.findBomExgById(id);
	}

	public BomVersion findBomVersionById(String verId) {
		return (BomVersion) this.bomDao.load(BomVersion.class, verId);
	}

	/**
	 * 删除BOM版本
	 * 
	 * @param id
	 */
	public void delVersion(String id, Integer verNo) {
		// 先删除该BOM版本下的所有物料
		List<BomImg> imgList = this.bomDao.findBomImg(null, null, null, id, verNo, -1, -1);
		this.bomDao.deleteAll(imgList);
		this.bomDao.delVersion(id, verNo);
	}

	public BomVersion findBomVersionByVerNo(Integer verNo) {
		return this.bomDao.findBomVersionByVerNo(verNo);
	}

	public List<BomImg> saveBomImgs(List<BomImg> bomImgs) {
		return this.bomDao.batchSaveOrUpdate(bomImgs);
	}

	public BomImg findBomImgById(String id) {
		return this.bomDao.findBomImgById(id);
	}

	public void delBomImgByIds(String[] imgIds) {
		try {
			this.bomDao.delBomImgByIds(imgIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Integer findImgCount(AclUser loginUser, String entityName, Integer verNo, String bomExgId) {
		return this.bomDao.findImgCount(entityName, verNo, bomExgId);
	}
}
