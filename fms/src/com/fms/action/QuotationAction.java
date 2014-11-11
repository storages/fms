package com.fms.action;

import com.fms.base.action.BaseAction;
import com.fms.logic.QuotationLogic;
/**
 * 报价单Action
 * @author Administrator
 *
 */
public class QuotationAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected QuotationLogic quotationLogic;
	
	/**
	 * 查找符合条件的报价单信息
	 * @return
	 */
	public String findQuotations(){
		try{
		this.quotationLogic.findQuotations(null, null, null, -1, -1);
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
		
	}

	public QuotationLogic getQuotationLogic() {
		return quotationLogic;
	}

	public void setQuotationLogic(QuotationLogic quotationLogic) {
		this.quotationLogic = quotationLogic;
	}

	
	
}
