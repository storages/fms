package com.fms.scmcoc.logic;

import java.util.List;

import com.fms.core.entity.Scmcoc;
import com.fms.scmcoc.dao.ScmcocDao;

public class ScmcocLogicImpl implements ScmcocLogic {

	private ScmcocDao scmcocDao;
	 
	public List<Scmcoc> findAllScmcoc(Boolean isCustom,String likeStr,Integer index,Integer length) {
		return scmcocDao.findAllScmcoc(isCustom,likeStr,index,length);
	}

	public Scmcoc findScmcocById(String id) {
		return scmcocDao.findScmcocById(id);
	}

	public void saveScmcoc(Scmcoc scmcoc) {
		scmcocDao.saveScmcoc(scmcoc);
	}

	public void betchSaveScmcoc(List<Scmcoc> data) {
		scmcocDao.betchSaveScmcoc(data);
	}

	public void deleteScmcocById(String id) {
		scmcocDao.deleteScmcocById(id);
	}

	public ScmcocDao getScmcocDao() {
		return scmcocDao;
	}

	public void setScmcocDao(ScmcocDao scmcocDao) {
		this.scmcocDao = scmcocDao;
	}

	public Scmcoc findScmcocByCode(String code) {
		return this.scmcocDao.findScmcocByCode(code);
	}

	public void delete(List<String> ids) {
		this.scmcocDao.delete(ids);
	}

	public Integer findDataCount(String className,Boolean isCustom,String name){
		return this.scmcocDao.findDataCount(className,isCustom,name);
	}
	
}
