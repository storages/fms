package com.fms.dao;

import java.util.Date;
import java.util.List;

import com.fms.base.dao.BaseDao;
import com.fms.core.entity.AppBillHead;
import com.fms.core.entity.AppBillItem;
import com.fms.core.entity.Quotation;
/**
 * 申请单Dao类
 * @author Administrator
 *
 */
public interface AppBillDao extends BaseDao{
	/**
	 * 根据报价单查找申请单
	 * @param q
	 * @return
	 */
	List<AppBillItem> findAppBillItem(Quotation q);
	
	/**
	 * 根据表头查询表体
	 * @param head
	 * @return
	 */
	List<AppBillItem> findAppBillItemByHead(AppBillHead head);
	
	/**
	 * 批量保存申请单表头
	 */
	List<AppBillHead> betchSaveAppBillHead(List<AppBillHead> datas);
	
	/**
	 * 批量保存申请单表头
	 */
	List<AppBillItem> betchSaveAppBillItem(List<AppBillItem> datas);
	
	/**
	 * 保存单条申请单表头
	 */
	AppBillHead saveAppBillHead(AppBillHead head);
	
	/**
	 * 保存单条申请单表体
	 */
	AppBillItem saveAppBillItem(AppBillItem item);
	
	/**
	 * 符合条件的记录数
	 * @param appNo
	 * @param beginappDate
	 * @param endappDate
	 * @return
	 */
	Integer findDataCount(String appNo, Date beginappDate,Date endappDate,String appStatus);
	
	/**
	 * 查找申请单列表数据（分页）
	 * @param appNo
	 * @param beginappDate
	 * @param endappDate
	 * @return
	 */
	List<AppBillHead> findAppBillHeads(String appNo, Date beginappDate,Date endappDate,String appStatus, int index,int length);
	
	
}
