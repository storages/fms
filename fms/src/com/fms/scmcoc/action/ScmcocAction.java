package com.fms.scmcoc.action;

import java.util.List;

import com.fms.base.action.BaseAction;
import com.fms.core.entity.Scmcoc;
import com.fms.scmcoc.logic.ScmcocLogic;

public class ScmcocAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ScmcocLogic scmcocLogic;
	private Scmcoc scmcoc;

	public ScmcocLogic getScmcocLogic() {
		return scmcocLogic;
	}

	public void setScmcocLogic(ScmcocLogic scmcocLogic) {
		this.scmcocLogic = scmcocLogic;
	}
	
	public Scmcoc getScmcoc() {
		return scmcoc;
	}

	public void setScmcoc(Scmcoc scmcoc) {
		this.scmcoc = scmcoc;
	}

	public String findAllScmcoc()  {
		List<Scmcoc> scmcocs = this.scmcocLogic.findAllScmcoc(Boolean.FALSE);
		this.request.put("scmcocs", scmcocs);
		return this.SUCCESS;
	}

	public String saveScmcoc() throws Exception{
		this.scmcocLogic.saveScmcoc(scmcoc);
		return "save";
	}
}
