package com.fms.action;

import java.util.List;

import com.fms.base.action.BaseAction;
import com.fms.core.entity.BomExg;
import com.fms.logic.BomLogic;
import com.fms.logic.MaterialLogic;

/**
 * Bom表
 * 
 * @author Administrator
 * 
 */
public class BomAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected BomLogic bomLogic;
	protected MaterialLogic materLogic;

	/**
	 * 查找bom成品表
	 * 
	 * @return
	 */
	public String findBomExg() {
		List<BomExg> bomExgs = bomLogic.findBomExg();
		this.request.put("bomExgs", bomExgs);
		return this.SUCCESS;
	}

	public BomLogic getBomLogic() {
		return bomLogic;
	}

	public void setBomLogic(BomLogic bomLogic) {
		this.bomLogic = bomLogic;
	}

	public MaterialLogic getMaterLogic() {
		return materLogic;
	}

	public void setMaterLogic(MaterialLogic materLogic) {
		this.materLogic = materLogic;
	}

}
