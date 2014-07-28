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

	public ScmcocLogic getScmcocLogic() {
		return scmcocLogic;
	}

	public void setScmcocLogic(ScmcocLogic scmcocLogic) {
		this.scmcocLogic = scmcocLogic;
	}
	
	public String findAllScmcoc() throws Exception{
		List<Scmcoc> scmcocs = this.scmcocLogic.findAllScmcoc(Boolean.FALSE);
		this.request.put("scmcocs", scmcocs);
		return this.SUCCESS;
	}

}
