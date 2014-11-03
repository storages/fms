package com.fms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.fms.base.dao.BaseDaoImpl;
import com.fms.core.entity.Material;
import com.fms.core.entity.Unit;
import com.fms.dao.MaterialDao;

public class MaterialDaoImpl extends BaseDaoImpl implements MaterialDao {

	/**
	 * 查询所有物料信息【分页】
	 */
	public List<Material> findAllMaterialInfo(String likeStr,
			String imgExgFlag, Integer index, Integer length) {
		try {
			String hql = "select a from Material a left join fetch a.unit where a.imgExgFlag = ? ";
			List param = new ArrayList();
			if (null != likeStr && !"".equals(likeStr)) {
				hql += " and a.name like ? ";
				param.add("%" + likeStr + "%");
			}
			param.add(imgExgFlag);
			return this.findPageList(hql, param.toArray(), index, length);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Integer findDataCount(String className, String name) {
		String hql = "select count(id) from " + className.trim()
				+ " a where 1=1 ";
		if (null != name && !"".equals(name)) {
			hql += " and a.name like '%" + name + "%'";
		}
		return this.count(hql, new Object[] {});
	}

	public List<Unit> findAllUnit() {
		return this.find("select a from Unit a");
	}

	public Material findMaterialById(String id) {
		return (Material) this
				.findUniqueResult("select a from Material a where a.id = ? ",
						new Object[] { id });
	}

	public Material checkMaterial(String hsName, String model, String batchNO) {
		String hql = "select a from Material a where a.hsName = ? and a.model =? and a.batchNO =? ";
		return (Material) this.uniqueResult(hql, new Object[]{hsName,model,batchNO});
	}
}