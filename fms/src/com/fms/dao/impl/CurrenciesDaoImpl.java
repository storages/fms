package com.fms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.fms.base.dao.BaseDaoImpl;
import com.fms.core.entity.Currencies;
import com.fms.core.entity.Scmcoc;
import com.fms.dao.CurrenciesDao;

public class CurrenciesDaoImpl extends BaseDaoImpl implements CurrenciesDao {

	public List<Currencies> findAllCurrencies(String likeStr, Integer index,
			Integer length) {
		String hql = "select * from Currencies a  ";
		List param = new ArrayList();
		if(null!=likeStr && !"".equals(likeStr)){
			hql+=" and a.name like '%"+likeStr+"%'";
			//param.add("'%"+ likeStr +"%'");
		}
		return this.findPageList(hql, param.toArray(),index,length);
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

	public void deleteCurrenciesById(String id) {
		String hql = "delete from Currencies a where a.id = ? ";
		List param = new ArrayList();
		param.add(id);
		this.batchUpdateOrDelete(hql,param.toArray());
		
	}

	public Currencies findCurrenciesByCode(String code) {
		String hql = "select a from Currencies a where a.code = ? ";
		List param = new ArrayList();
		param.add(code);
		return (Currencies) this.findUniqueResult(hql, param.toArray());
	}

	public Integer findDataCount(String className, String name) {
		String hql = "select count(id) from "+className.trim() +" a  ";
		List param = new ArrayList();
		if(null!=name && !"".equals(name)){
			hql+=" and a.name like '%"+name+"%'";
			//param.add("'%"+name+"%'");
		}
		return this.count(hql, param.toArray());
	}

	public void delete(List<String> ids) {
		String hql = "delete Currencies a where 1=1 and ";
		for(int i=0 ; i<ids.size(); i++){
			hql+=" a.id = ? or ";
		}
		hql = hql.substring(0,hql.trim().length()-2);
		Object [] objs = ids.toArray();
		this.batchUpdateOrDelete(hql, objs);
	}

}
