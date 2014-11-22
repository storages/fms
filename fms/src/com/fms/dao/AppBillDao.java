package com.fms.dao;

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
	 * 保存申请单表体
	 */
	List<AppBillItem> saveAppBillItem(List<AppBillItem> datas);
}
