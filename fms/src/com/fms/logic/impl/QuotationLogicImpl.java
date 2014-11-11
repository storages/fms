package com.fms.logic.impl;

import java.util.Date;
import java.util.List;

import com.fms.core.entity.Quotation;
import com.fms.dao.QuotationDao;
import com.fms.logic.QuotationLogic;

public class QuotationLogicImpl implements QuotationLogic{

	protected QuotationDao quotationDao;
	
	
	public List<Quotation> findQuotations(String scmCocName, String hsCode,Date effectDate, int index, int length) {
		return quotationDao.findQuotations(scmCocName, hsCode, effectDate, index, length);
	}
	
	
	public QuotationDao getQuotationDao() {
		return quotationDao;
	}
	public void setQuotationDao(QuotationDao quotationDao) {
		this.quotationDao = quotationDao;
	}

	
	
}
