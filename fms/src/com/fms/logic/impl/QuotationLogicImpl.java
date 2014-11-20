package com.fms.logic.impl;

import java.util.Date;
import java.util.List;

import com.fms.core.entity.Quotation;
import com.fms.core.entity.Scmcoc;
import com.fms.dao.QuotationDao;
import com.fms.logic.QuotationLogic;

public class QuotationLogicImpl implements QuotationLogic{

	protected QuotationDao quotationDao;
	
	
	public List<Quotation> findQuotations(String scmCocName, String hsCode,Date begineffectDate,Date endeffectDate, int index, int length) {
		return quotationDao.findQuotations(scmCocName, hsCode, begineffectDate,endeffectDate, index, length);
	}
	
	public Integer findDataCount(String scmCocName, String hsCode,Date begineffectDate,Date endeffectDate) {
		return quotationDao.findDataCount(scmCocName, hsCode, begineffectDate,endeffectDate);
	}
	
	public QuotationDao getQuotationDao() {
		return quotationDao;
	}
	public void setQuotationDao(QuotationDao quotationDao) {
		this.quotationDao = quotationDao;
	}
	public List<Quotation> saveQuotations(List<Quotation> list){
		Integer serialNo = quotationDao.getSerialNo("Quotation");
		for(Quotation q:list){
			if(null!=serialNo && serialNo<0){
				throw new RuntimeException("获取流水号时，可能给定的类名不正确!");
			}else if(serialNo==null){
				serialNo = 1;
			}else{
				serialNo+=1;
			}
			q.setSerialNo(serialNo);
		}
		return this.quotationDao.batchSaveOrUpdate(list);
	}
	public List<Scmcoc> findAll(){
		return quotationDao.findAll();
	}
	public Scmcoc findById(String id){
		return quotationDao.findById(id);
	}
	
	public List<Quotation> findQuotationByIds(String [] ids){
		return this.quotationDao.findQuotationByIds(ids);
	}
	
}
