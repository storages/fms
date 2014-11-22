package com.fms.action;

import com.fms.base.action.BaseAction;
import com.fms.logic.AppBillLogic;

public class AppBillAction extends BaseAction {

	/**
	 * 申请单表头表体Action
	 */
	private static final long serialVersionUID = 1L;
	
	protected AppBillLogic appBillLogic;

	public AppBillLogic getAppBillLogic() {
		return appBillLogic;
	}

	public void setAppBillLogic(AppBillLogic appBillLogic) {
		this.appBillLogic = appBillLogic;
	}
	
	

}
