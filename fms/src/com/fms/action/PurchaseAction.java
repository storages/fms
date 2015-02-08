package com.fms.action;

import com.fms.base.action.BaseAction;
import com.fms.logic.PurchaseBillLogic;

/**
 * 采购单Action
 * @author Administrator
 *
 */
public class PurchaseAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected PurchaseBillLogic purchaseBillLogic;
	public PurchaseBillLogic getPurchaseBillLogic() {
		return purchaseBillLogic;
	}
	public void setPurchaseBillLogic(PurchaseBillLogic purchaseBillLogic) {
		this.purchaseBillLogic = purchaseBillLogic;
	}
	
	/**
	 * 获取采购单表头
	 * @return
	 */
	public String findPurchaseHeads(){
		System.out.println("---------------");
		return this.SUCCESS;
	}

}
