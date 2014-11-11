package com.fms.dao;

import java.util.Date;
import java.util.List;

import com.fms.base.dao.BaseDao;
import com.fms.core.entity.Quotation;
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
	List<Quotation> findQuotations(String scmCocName,String hsCode,Date effectDate,int index,int length);

}
