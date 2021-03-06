﻿package com.fms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.fms.base.dao.BaseDaoImpl;
import com.fms.core.entity.Scmcoc;
import com.fms.dao.ScmcocDao;

public class ScmcocDaoImpl extends BaseDaoImpl implements ScmcocDao {

	public List<Scmcoc> findAllScmcoc(Boolean isCustom, String likeStr, Integer index, Integer length) {
		String hql = "select a from Scmcoc a left join fetch a.settlement where a.isCustom = ? ";
		List param = new ArrayList();
		param.add(isCustom);
		if (null != likeStr && !"".equals(likeStr)) {
			hql += " and a.name like '%" + likeStr + "%'";
			// param.add("'%"+ likeStr +"%'");
		}
		return this.findPageList(hql, param.toArray(), index, length);
	}

	public Scmcoc findScmcocById(String id) {
		String hql = "select a from Scmcoc a left join fetch a.settlement where a.id = ? ";
		List param = new ArrayList();
		param.add(id);
		return (Scmcoc) this.findUniqueResult(hql, param.toArray());
	}

	public void saveScmcoc(Scmcoc scmcoc) {
		this.saveOrUpdate(scmcoc);
	}

	public void betchSaveScmcoc(List<Scmcoc> data) {
		this.batchSaveOrUpdate(data);
	}

	public void deleteScmcocById(String id) {
		String hql = "delete from Scmcoc a where a.id = ? ";
		List param = new ArrayList();
		param.add(id);
		this.batchUpdateOrDelete(hql, param.toArray());
	}

	public Scmcoc findScmcocByCode(String code) {
		String hql = "select a from Scmcoc a left join fetch a.settlement where a.code = ? ";
		List param = new ArrayList();
		param.add(code);
		return (Scmcoc) this.findUniqueResult(hql, param.toArray());
	}

	public void delete(List<String> ids) {
		String hql = "delete Scmcoc a where 1=1 and ";
		for (int i = 0; i < ids.size(); i++) {
			hql += " a.id = ? or ";
		}
		hql = hql.substring(0, hql.trim().length() - 2);
		Object[] objs = ids.toArray();
		this.batchUpdateOrDelete(hql, objs);
	}

	public Integer findDataCount(String className, Boolean isCustom, String name) {
		try {
			String hql = "select count(a.id) from " + className.trim() + " a where a.isCustom = ? ";
			List param = new ArrayList();
			param.add(isCustom);
			if (null != name && !"".equals(name)) {
				hql += " and a.name like '%" + name + "%'";
				// param.add("'%"+name+"%'");
			}
			return this.count(hql, param.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Scmcoc findScmcocByName(String name) {
		String hql = "select a from Scmcoc a left join fetch a.settlement where a.name = ? ";
		List param = new ArrayList();
		param.add(name);
		return (Scmcoc) this.findUniqueResult(hql, param.toArray());
	}

}
