package com.fms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.fms.base.dao.BaseDao;
import com.fms.core.entity.Settlement;
import com.fms.dao.SettlementDao;

public class SettlementDaoImpl extends BaseDao implements SettlementDao {

	public List<Settlement> findAllSettlement(String searhStr) {
		String hql = "SELECT a FROM Settlement a ";
		if(null!=searhStr&&!"".equals(searhStr)){
			hql+=" where a.name like '%"+searhStr+"%'";
		}
		return this.find(hql, null);
	}

	public void saveSettlement(Settlement settlement) {
		this.saveOrUpdate(settlement);
	}

	public Settlement findSettById(String id) {
		List list = new ArrayList();
		String hql = "SELECT a FROM Settlement a ";
		if(null!=id && !"".equals(id)){
			hql+=" where a.id = ? ";
			list.add(id);
		}
		return (Settlement) this.findUniqueResult(hql, list.toArray());
	}

	public String findSettByCode(String code) {
		String hql = "select a.code from Settlement a where a.code = ?";
		List list = new ArrayList();
		list.add(code);
		return (String) this.findUniqueResult(hql, list.toArray());
	}

	public void delSettltById(String [] ids) {
		String hql = "DELETE FROM Settlement a where a.id = ? ";
		List param = new ArrayList();
		param.add(ids[0]);
		for(int i = 1; i<ids.length ; i++){
			hql+=" or a.id = ? ";
			param.add(ids[i]);
		}
		this.batchUpdateOrDelete(hql, param.toArray());
	}

}
