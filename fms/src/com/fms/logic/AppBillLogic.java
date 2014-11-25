package com.fms.logic;

import java.util.Date;
import java.util.List;

import com.fms.core.entity.AppBillHead;
import com.fms.core.entity.AppBillItem;
import com.fms.core.entity.Quotation;

public interface AppBillLogic {
	List<AppBillItem> findAppBillItem(Quotation q);
	/**
	 * 保存单条申请单表头
	 */
	AppBillHead saveAppBillHead(AppBillHead head);
	
	/**
	 * 保存单条申请单表体
	 */
	AppBillItem saveAppBillItem(AppBillItem item);
	
	/**
	 * 批量保存申请单表头
	 */
	List<AppBillHead> betchSaveAppBillHead(List<AppBillHead> datas);
	
	/**
	 * 批量保存申请单表体
	 */
	List<AppBillItem> betchSaveAppBillItem(List<AppBillItem> datas);
	
	/**
	 * 符合条件的记录数
	 * @param appNo
	 * @param beginappDate
	 * @param endappDate
	 * @return
	 */
	Integer findDataCount(String appNo, Date beginappDate,Date endappDate,String appStatus);
	
	/**
	 * 分页查询申请单列表数据
	 * @param appNo
	 * @param beginappDate
	 * @param endappDate
	 * @param index
	 * @param length
	 * @return
	 */
	public List<AppBillHead> findAppBillHeads(String appNo, Date beginappDate,Date endappDate,String appStatus, int index, int length);
	
	/**
	 * 根据表头id查询所有的表体
	 * @param hid
	 * @return
	 */
	public List<AppBillItem> findItemByHid(String hid);
	
	/**
	 * 根据表头id查询表头对象
	 * @param hid
	 * @return
	 */
	AppBillHead findHeadById(String hid);
	
	/**
	 * 根据表体id查询表体对象
	 * @param hid
	 * @return
	 */
	AppBillItem findItemById(String id);
}
