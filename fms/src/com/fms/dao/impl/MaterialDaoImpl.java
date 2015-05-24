package com.fms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fms.base.dao.BaseDaoImpl;
import com.fms.core.entity.AclUser;
import com.fms.core.entity.Material;
import com.fms.core.entity.Unit;
import com.fms.dao.MaterialDao;

public class MaterialDaoImpl extends BaseDaoImpl implements MaterialDao {

	/**
	 * 查询所有物料信息【分页】
	 */
	public List<Material> findAllMaterialInfo(String likeStr, String imgExgFlag, Integer index, Integer length) {
		try {
			String hql = "select a from Material a left join fetch a.unit left join fetch a.materialType where 1=1 ";
			List param = new ArrayList();
			if (null != imgExgFlag && !"".equals(imgExgFlag)) {
				hql += " and a.imgExgFlag = ? ";
				param.add(imgExgFlag);
			}
			if (null != likeStr && !"".equals(likeStr)) {
				hql += " and a.hsName like '%" + likeStr + "%'";
			}
			return this.findPageList(hql, param.toArray(), index, length);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 按物料编码查找
	 * 
	 * @param likeStr
	 * @param imgExgFlag
	 * @param index
	 * @param length
	 * @return
	 */
	public List<Material> findAllMaterialInfoByHsCode(String likeStr, String imgExgFlag, Integer index, Integer length) {
		try {
			String hql = "select a from Material a left join fetch a.unit left join fetch a.materialType where 1=1 ";
			List param = new ArrayList();
			if (null != imgExgFlag && !"".equals(imgExgFlag)) {
				hql += " and a.imgExgFlag = ? ";
				param.add(imgExgFlag);
			}
			if (null != likeStr && !"".equals(likeStr)) {
				hql += " and a.hsCode like '%" + likeStr + "%'";
			}
			return this.findPageList(hql, param.toArray(), index, length);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Integer findDataCount(String className, String name, String imgExgFlag) {
		Integer num = 0;
		try {
			String hql = "select count(id) from " + className.trim() + " a where a.imgExgFlag =? ";
			if (null != name && !"".equals(name)) {
				hql += " and a.hsName like '%" + name + "%'";
			}
			num = this.count(hql, new Object[] { imgExgFlag });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}

	public Integer findDataCountExgs(AclUser loginUser, String className, String hsName, String hsCode, String hsModel, String imgExgFlag) {
		Integer num = 0;
		try {
			String hql = "select count(a.id) from " + className.trim() + " a where a.imgExgFlag =? ";
			if (null != hsName && !"".equals(hsName)) {
				hql += " and a.hsName like '%" + hsName + "%'";
			}
			if (null != hsCode && !"".equals(hsCode)) {
				hql += " and a.hsCode like '%" + hsCode + "%'";
			}
			if (null != hsModel && !"".equals(hsModel)) {
				hql += " and a.model like '%" + hsModel + "%'";
			}
			num = this.count(hql, new Object[] { imgExgFlag });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}

	public List<Unit> findAllUnit() {
		return this.find("select a from Unit a");
	}

	public Material findMaterialById(String id) {
		return (Material) this.findUniqueResult("select a from Material a left join fetch a.unit left join fetch a.materialType where a.id = ? ", new Object[] { id });
	}

	public Material checkMaterial(String hsName, String model, String hsCode) {
		String hql = "select a from Material a where a.hsName = ? and a.model =? and a.hsCode =? ";
		return (Material) this.uniqueResult(hql, new Object[] { hsName, model, hsCode });
	}

	public void deleteMaterial(String[] ids) {
		List<Material> mats = this.findMaterialById(ids);
		/*
		 * String hql = "DELETE FROM Material a where a.id = ? "; List param =
		 * new ArrayList(); param.add(ids[0]); for (int i = 1; i < ids.length;
		 * i++) { hql += " or a.id = ? "; param.add(ids[i]); }
		 */
		this.deleteAll(mats);
	}

	public List<Material> findMaterialById(String[] ids) {
		if (ids != null && !"".equals(ids)) {
			List param = new ArrayList();
			String hql = "from Material a where a.id = ? ";
			param.add(ids[0]);
			for (int i = 1; i < ids.length; i++) {
				hql += " or a.id = ? ";
				param.add(ids[i]);
			}
			return this.find(hql, param.toArray());
		}
		return null;
	}

	/**
	 * 验证物料编码是否重复
	 */
	public String findHsCode(String hsCode) {
		try {
			String hql = "select a.hsCode from Material a where a.hsCode =? ";
			return (String) this.uniqueResult(hql, new Object[] { hsCode });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Material findMaterialByHsCode(String hsCode) {
		return (Material) this.uniqueResult("select a from Material a where a.hsCode = ? ", new Object[] { hsCode });
	}

	/**
	 * 查找物料清单中的成品
	 * 
	 * @param hsCode
	 * @param hsName
	 * @param hsModel
	 * @param imgExgFlag
	 * @return
	 */
	public List<Material> findAllMaterialExgs(String hsCode, String hsName, String hsModel, String imgExgFlag, Integer index, Integer length) {
		try {
			String hql = "select a from Material a left join fetch a.unit left join fetch a.materialType where 1=1 ";
			List param = new ArrayList();
			if (null != imgExgFlag && !"".equals(imgExgFlag)) {
				hql += " and a.imgExgFlag = ? ";
				param.add(imgExgFlag);
			}
			if (null != hsCode && !"".equals(hsCode)) {
				hql += " and a.hsCode like '%" + hsCode + "%'";
			}
			if (null != hsName && !"".equals(hsName)) {
				hql += " and a.hsName like '%" + hsName + "%'";
			}
			if (null != hsModel && !"".equals(hsModel)) {
				hql += " and a.model like '%" + hsModel + "%'";
			}
			return this.findPageList(hql, param.toArray(), index, length);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Material> finsMaterialByHsCode(Object[] hsCodes) {
		List param = new ArrayList();
		String hql = "select mat from Material mat left join fetch mat.materialType mt left join fetch mat.unit u where mat.hsCode =? ";
		param.add(hsCodes[0]);
		if (hsCodes.length > 1) {
			for (int i = 1; i < hsCodes.length; i++) {
				hql += " or mat.hsCode =? ";
				param.add(hsCodes[i]);
			}
		}
		return this.find(hql, param.toArray());
	}

	public List<Material> finsMaterialByHsCode(String hsCode, String hsName, String imgExgFlag) {
		try {
			List param = new ArrayList();
			String hql = "select mat from Material mat left join fetch mat.materialType mt left join fetch mat.unit u where 1=1";
			if (StringUtils.isNotBlank(hsCode)) {
				hql += " and mat.hsCode like ? ";
				param.add("%" + hsCode + "%");
			}
			if (StringUtils.isNotBlank(hsName)) {
				hql += " and mat.hsName like ? ";
				param.add("%" + hsName + "%");
			}
			hql += " and mat.imgExgFlag =? ";
			param.add(imgExgFlag);
			return this.find(hql, param.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}