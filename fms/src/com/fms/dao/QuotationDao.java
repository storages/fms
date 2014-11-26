package com.fms.dao;

import java.util.Date;
import java.util.List;

import com.fms.base.dao.BaseDao;
import com.fms.core.entity.AppBillItem;
import com.fms.core.entity.Material;
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
	/**
	 * 查询所有的供应商或客户
	 * @return
	 */
	public List<Scmcoc> findAll();
	/**
	 * 根据id查找单条报价单
	 * @param id
	 * @return
	 */
	public Scmcoc findById(String id);
	/**
	 * 根据id批量查找报价单
	 * @param ids
	 * @return
	 */
	public List<Quotation> findQuotationByIds(String [] ids);
	/**
	 * 根据id删除报价单
	 * @param ids
	 */
	public void delQuotationById(String[] ids);
	
	/**
	 * 根据物料、供应商、和日期来查询最新报价单
	 * @param m
	 * @param date
	 * @return
	 */
	public Quotation findQuotationByCondention(Material m,Scmcoc scm);
	
}
