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
			String hql = "select a from Material a left join fetch a.unit where 1=1 ";
			List param = new ArrayList();
			if(null != imgExgFlag && !"".equals(imgExgFlag)){
				hql+=" and a.imgExgFlag = ? ";
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

	public Integer findDataCount(String className, String name) {
		Integer num = 0;
		try{
		String hql = "select count(id) from " + className.trim()
				+ " a where 1=1 ";
		if (null != name && !"".equals(name)) {
			hql += " and a.hsName like '%" + name + "%'";
		}
		num = this.count(hql, new Object[] {});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
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
	
	public void deleteMaterial(String [] ids) {
		String hql = "DELETE FROM Material a where a.id = ? ";
		List param = new ArrayList();
		param.add(ids[0]);
		for(int i = 1; i<ids.length ; i++){
			hql+=" or a.id = ? ";
			param.add(ids[i]);
		}
		this.batchUpdateOrDelete(hql, param.toArray());
	}

	public List<Material> findMaterialById(String[] ids) {
		if(ids!=null && !"".equals(ids)){
			List param = new ArrayList();
			String hql = "from Material a where a.id = ? ";
			param.add(ids[0]);
			for(int i = 1 ;i<ids.length;i++){
				hql+=" or a.id = ? ";
				param.add(ids[i]);
			}
			return this.find(hql, param.toArray());
		}
		return null;
	}
	
	public String findHsCode(String hsCode){
		try{
			String hql = "select a from Material a where a.hsCode = "+hsCode;
			return (String) this.uniqueResult(hql,new Object[]{});
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public Material findMaterialByHsCode(String hsCode){
		return (Material) this.uniqueResult("select a from Material a where a.hsCode = ? ", new Object[]{hsCode});
	}
}