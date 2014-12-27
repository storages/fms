package com.fms.logic;

import java.util.Date;
import java.util.List;

import com.fms.core.entity.AclUser;
import com.fms.core.entity.AppBillHead;
import com.fms.core.entity.AppBillItem;
import com.fms.core.entity.Material;
import com.fms.core.entity.Quotation;
import com.fms.core.entity.Scmcoc;

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
	public List<AppBillItem> findItemByHid(String hid,Date beginappDate,Date endappDate,String appStatus);
	
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
	
	/**
	 * 根据表体id数组批量查询表体对象
	 * @param ids
	 * @return
	 */
	public List<AppBillItem> findItemByIds(String[] ids);
	
	/**
	 * 删除申请单表体
	 * @param ids
	 */
	public void delAppBillItem(String[] ids);
	
	/**
	 * 删除申请单表头
	 * @param ids
	 */
	public void delAppBillHead(String[] ids);
	
	/**
	 * 提交申请
	 * @param ids
	 */
	public void submitApp(String [] ids);
	
	/**
	 * 根据表体id来查询表头
	 * @param itemId
	 * @return
	 */
	public AppBillHead findHeadByItemId(String itemId);
	
	/**
	 * 批量审批申请单
	 * @param data
	 */
	List<AppBillItem> verifyItem(String[] itemIds,String verifyFlag,AclUser user);
}
