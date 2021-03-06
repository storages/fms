package com.fms.logic;

import java.util.Date;
import java.util.List;

import com.fms.core.entity.AclUser;
import com.fms.core.entity.Material;
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
	List<Quotation> findQuotations(AclUser loginUser,String scmCocName,String hsCode,Date begineffectDate,Date endeffectDate,int index,int length);
	
	/**
	 * 获取数据的总条数
	 * @param scmCocName
	 * @param hsCode
	 * @param effectDate
	 * @return
	 */
	Integer findDataCount(AclUser loginUser,String scmCocName,String hsCode,Date begineffectDate,Date endeffectDate);
	
	/**
	 * 保存报价单信息
	 * @param list
	 * @return
	 */
	List<Quotation> saveQuotations(AclUser loginUser,List<Quotation> list);
	/**
	 * 获取供应商
	 * @return
	 */
	public List<Scmcoc> findAll(AclUser loginUser);
	/**
	 * 根据id获取供应商
	 * @param id
	 * @return
	 */
	public Scmcoc findById(AclUser loginUser,String id);
	/**
	 * 根据id获取报价单信息
	 * @param ids
	 * @return
	 */
	public List<Quotation> findQuotationByIds(AclUser loginUser,String [] ids);
	/**
	 * 根据id和类名称获取报价单信息
	 * @param entityName
	 * @param id
	 * @return
	 */
	public Quotation findQuotationById(AclUser loginUser,String entityName,String id);
	/**
	 * 根据id删除报价单信息
	 * @param ids
	 */
	public void delQuotationById(AclUser loginUser,String[] ids);
	/**
	 * 根据报价单id找到报价单信息，再更新相关模块的单价
	 * 这里在更新申请单单价时，是否只能允许【未申请、申请不通过】的状态才能更新？现状是全部都更新
	 * @param ids
	 * @return
	 */
	public int updatePrice(AclUser loginUser,String[] ids);
	
	/**
	 * 验证导入Excel数据
	 */
	public List<?> doValidata(AclUser loginUser,List<?> dataList);
	
	/**
	 * 保存Excel数据到数据库
	 */
	public Boolean doSaveExcelData(AclUser loginUser,List<?> dataList); 
	
	/**
	 * 根据物料、供应商、和日期来查询报价单
	 * @param m
	 * @param date
	 * @return
	 */
	public Quotation findQuotationByCondention(AclUser loginUser,Material m,Scmcoc scm);
}
