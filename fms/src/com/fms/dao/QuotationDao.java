package com.fms.dao;

import java.util.Date;
import java.util.List;

import com.fms.base.dao.BaseDao;
import com.fms.core.entity.Quotation;
import com.fms.core.entity.Scmcoc;
/**
 * 报价单Dao类
 * @author Administrator
 *
 */
public interface QuotationDao extends BaseDao{
	/**
	 * 根据条件获取报价单信息(分页)
	 * @return
	 */
	List<Quotation> findQuotations(String scmCocName,String hsCode,Date begineffectDate,Date endeffectDate,int index,int length);
	
	/**
	 * 获取数据的总条数
	 * @param scmCocName
	 * @param hsCode
	 * @param effectDate
	 * @return
	 */
	Integer findDataCount(String scmCocName,String hsCode,Date begineffectDate,Date endeffectDate);
	
	public List<Scmcoc> findAll();
	public Scmcoc findById(String id);
	
	public List<Quotation> findQuotationByIds(String [] ids);
	
	public void delQuotationById(String[] ids);
}
