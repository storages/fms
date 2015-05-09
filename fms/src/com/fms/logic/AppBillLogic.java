package com.fms.logic;

import java.util.Date;
import java.util.List;

import com.fms.core.entity.AclUser;
import com.fms.core.entity.AppBillHead;
import com.fms.core.entity.AppBillItem;
import com.fms.core.entity.Material;
import com.fms.core.entity.Quotation;

public interface AppBillLogic {
	List<AppBillItem> findAppBillItem(AclUser loginUser, Quotation q);

	/**
	 * 保存单条申请单表头
	 */
	AppBillHead saveAppBillHead(AclUser loginUser, AppBillHead head);

	/**
	 * 保存单条申请单表体
	 */
	AppBillItem saveAppBillItem(AclUser loginUser, AppBillItem item);

	/**
	 * 批量保存申请单表头
	 */
	List<AppBillHead> betchSaveAppBillHead(AclUser loginUser, List<AppBillHead> datas);

	/**
	 * 批量保存申请单表体
	 */
	List<AppBillItem> betchSaveAppBillItem(AclUser loginUser, List<AppBillItem> datas);

	/**
	 * 符合条件的记录数
	 * 
	 * @param appNo
	 * @param beginappDate
	 * @param endappDate
	 * @return
	 */
	Integer findDataCount(AclUser loginUser, String appNo, Date beginappDate, Date endappDate, String appStatus);

	/**
	 * 分页查询申请单列表数据
	 * 
	 * @param appNo
	 * @param beginappDate
	 * @param endappDate
	 * @param index
	 * @param length
	 * @return
	 */
	public List<AppBillHead> findAppBillHeads(AclUser loginUser, String appNo, Date beginappDate, Date endappDate, String appStatus, int index, int length);

	/**
	 * 根据表头id查询所有的表体
	 * 
	 * @param hid
	 * @return
	 */
	public List<AppBillItem> findItemByHid(AclUser loginUser, String hid, Date beginappDate, Date endappDate, String appStatus, AclUser user);

	/**
	 * 根据表头id查询表头对象
	 * 
	 * @param hid
	 * @return
	 */
	AppBillHead findHeadById(AclUser loginUser, String hid);

	/**
	 * 根据表体id查询表体对象
	 * 
	 * @param hid
	 * @return
	 */
	AppBillItem findItemById(AclUser loginUser, String id);

	/**
	 * 根据表体id数组批量查询表体对象
	 * 
	 * @param ids
	 * @return
	 */
	List<AppBillItem> findItemByIds(AclUser loginUser, String[] ids);

	/**
	 * 根据表头id数组批量查询表体对象
	 * 
	 * @param ids
	 * @return
	 */
	public List<AppBillItem> findItemByHeadIds(AclUser loginUser, String[] ids);

	/**
	 * 删除申请单表体
	 * 
	 * @param ids
	 */
	public void delAppBillItem(AclUser loginUser, String[] ids);

	/**
	 * 删除申请单表头
	 * 
	 * @param ids
	 */
	public void delAppBillHead(AclUser loginUser, String[] ids);

	/**
	 * 提交申请
	 * 
	 * @param ids
	 */
	public void submitApp(AclUser loginUser, String[] ids);

	/**
	 * 根据表体id来查询表头
	 * 
	 * @param itemId
	 * @return
	 */
	public AppBillHead findHeadByItemId(AclUser loginUser, String itemId);

	/**
	 * 批量审批申请单
	 * 
	 * @param data
	 */
	List<AppBillItem> verifyItem(AclUser loginUser, String[] itemIds, String verifyFlag, AclUser user, String mess);

	/**
	 * 根据表头id查询所有表头信息
	 * 
	 * @param headIds
	 * @return
	 */
	List<AppBillHead> findHeadsByHeadIds(AclUser loginUser, String[] headIds);

	/**
	 * 撤销审批
	 * 
	 * @param biilIds
	 */
	void cancelAppBill(AclUser loginUser, String[] appBillItemIds);

	/**
	 * 查找物料
	 * 
	 * @param hsCode
	 * @param hsName
	 * @param imgExgFlag
	 * @param isFromBom
	 * @param orderNo
	 * @return
	 */
	List<Material> findAllMaterialByCondent(String hsCode, String hsName, String imgExgFlag, String isFromBom, String orderNo);
}
