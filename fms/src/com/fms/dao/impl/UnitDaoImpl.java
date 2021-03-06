package com.fms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.fms.base.dao.BaseDaoImpl;
import com.fms.core.entity.Stock;
import com.fms.core.entity.Unit;
import com.fms.dao.UnitDao;

public class UnitDaoImpl extends BaseDaoImpl implements UnitDao {

	public List<Unit> findAllUnit(String likeStr, Integer index, Integer length) {
		String hql = "select a from Unit a where 1=1 ";
		List param = new ArrayList();
		if(null!=likeStr && !"".equals(likeStr)){
			hql+=" and a.name like '%"+likeStr+"%'";
		}
		if(index==null || length==null){
			return this.find(hql, new Object[]{});
		}
		return this.findPageList(hql, new Object[]{},index,length);
	}

	public Integer findDataCount(String className, String name) {
		String hql = "select count(id) from "+className.trim() +" a where 1=1 ";
		List param = new ArrayList();
		if(null!=name && !"".equals(name)){
			hql+=" and a.name like '%"+name+"%'";
		}
		return this.count(hql, new Object[]{});
	}

	public Unit findUnitById(String id) {
		String hql = "select a from Unit a where a.id = ? ";
		List param = new ArrayList();
		param.add(id);
		return (Unit) this.findUniqueResult(hql, param.toArray());
	}

	public void saveUnit(Unit unit) {
		this.saveOrUpdate(unit);
	}

	public String findUnitByCode(String code) {
		String hql = "select a.code from Unit a where a.code = ? ";
		List param = new ArrayList();
		param.add(code);
		return (String) this.findUniqueResult(hql, param.toArray());
	}

	public void delUnitById(String[] ids) {
		String hql = "DELETE FROM Unit a WHERE a.id = ? ";
		List param = new ArrayList();
		param.add(ids[0]);
		for(int i = 1 ; i < ids.length ; i++){
			hql+=" or a.id = ? ";
			param.add(ids[i]);
		}
		this.batchUpdateOrDelete(hql, param.toArray());
	}

}
