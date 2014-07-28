package com.fms.scmcoc.dao;

import java.util.ArrayList;
import java.util.List;

import com.fms.base.dao.BaseDao;
import com.fms.core.entity.Scmcoc;

public class ScmcocDaoImpl extends BaseDao implements ScmcocDao {

	public List<Scmcoc> findAllScmcoc(Boolean isCustom) {
		String hql = "select a from Scmcoc a where a.isCustom = ? ";
		List param = new ArrayList();
		param.add(isCustom);
		return this.find(hql, param.toArray());
	}

	public Scmcoc findScmcocById(String id) {
		String hql = "select a from Scmcoc a where a.id = ? ";
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
		this.batchUpdateOrDelete(hql,param.toArray());
	}

}
