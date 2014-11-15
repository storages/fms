package com.fms.logic;

import java.util.Date;
import java.util.List;

import com.fms.core.entity.Quotation;
import com.fms.core.entity.Scmcoc;

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
	List<Quotation> findQuotations(String scmCocName,String hsCode,Date begineffectDate,Date endeffectDate,int index,int length);
	
	/**
	 * 获取数据的总条数
	 * @param scmCocName
	 * @param hsCode
	 * @param effectDate
	 * @return
	 */
	Integer findDataCount(String scmCocName,String hsCode,Date begineffectDate,Date endeffectDate);
	
	/**
	 * 保存报价单信息
	 * @param list
	 * @return
	 */
	List<Quotation> saveQuotations(List<Quotation> list);
	
	public List<Scmcoc> findAll();
	public Scmcoc findById(String id);
}
