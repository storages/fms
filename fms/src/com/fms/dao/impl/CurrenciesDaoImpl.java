package com.fms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.fms.base.dao.BaseDaoImpl;
import com.fms.core.entity.Currencies;
import com.fms.dao.CurrenciesDao;

public class CurrenciesDaoImpl extends BaseDaoImpl implements CurrenciesDao {

	public List<Currencies> findAllCurrencies(String likeStr, Integer index, Integer length) {
		String hql = "from Currencies a where 1=1 ";
		List param = new ArrayList();
		if (null != likeStr && !"".equals(likeStr)) {
			hql += " and a.name like '%" + likeStr + "%'";
		}
		List<Currencies> list = this.findPageList(hql, param.toArray(), index, length);
		return list;
	}

	public Currencies findCurrenciesById(String id) {
		String hql = "select a from Currencies a where a.id = ? ";
		List param = new ArrayList();
		param.add(id);
		return (Currencies) this.findUniqueResult(hql, param.toArray());
	}

	public void saveCurrencies(Currencies currencies) {
		this.saveOrUpdate(currencies);

	}

	public void betchSaveCurrencies(List<Currencies> data) {
		this.batchSaveOrUpdate(data);

	}

	public void deleteCurrenciesById(String[] ids) {

		String hql = "delete from Currencies a where a.id = ? ";
		List param = new ArrayList();
		param.add(ids[0]);
		for (int i = 1; i < ids.length; i++) {
			hql += " or a.id = ? ";
			param.add(ids[i]);
		}
		this.batchUpdateOrDelete(hql, param.toArray());

	}

	public String findCurrenciesByCode(String code) {
		String hql = "select a from Currencies a where a.code = ? ";
		List param = new ArrayList();
		param.add(code);
		return (String) this.findUniqueResult(hql, param.toArray());
	}

	public Integer findDataCount(String className, String name) {
		String hql = "select count(id) from " + className.trim() + " a where 1=1 ";
		List param = new ArrayList();
		if (null != name && !"".equals(name)) {
			hql += " and a.name like '%" + name + "%'";
			// param.add("'%"+name+"%'");
		}
		return this.count(hql, param.toArray());
	}

	public void delete(List<String> ids) {
		String hql = "delete Currencies a where 1=1 and ";
		for (int i = 0; i < ids.size(); i++) {
			hql += " a.id = ? or ";
		}
		hql = hql.substring(0, hql.trim().length() - 2);
		Object[] objs = ids.toArray();
		this.batchUpdateOrDelete(hql, objs);
	}

	public List<Currencies> findAllCurrencies() {
		// TODO Auto-generated method stub
		String hql = "SELECT a FROM Currencies a ";
		return this.find(hql, null);
	}

}
