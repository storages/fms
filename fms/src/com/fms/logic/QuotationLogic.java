package com.fms.logic;

import java.util.Date;
import java.util.List;

import com.fms.core.entity.Quotation;

/**
 * 报价单逻辑类
 * @author Administrator
 *
 */
public interface QuotationLogic {

	/**
	 * 根据条件获取报价单信息(分页)
	 * @return
	 */
	List<Quotation> findQuotations(String scmCocName,String hsCode,Date effectDate,int index,int length);
}
