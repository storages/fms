package com.fms.dao;

import java.util.Date;
import java.util.List;

import com.fms.base.dao.BaseDao;
import com.fms.core.entity.InStorage;
import com.fms.core.entity.OutStorage;

/**
 * 进出库Dao
 * 
 * @author Administrator
 * 
 */
public interface StorageDao extends BaseDao {
	/**
	 * 分页查找进入库数据
	 * 
	 * @param entityName
	 * @param startDate
	 * @param endDate
	 * @param scmcocName
	 * @param hsCode
	 * @param index
	 * @param length
	 * @return
	 */
	List findStorage(String entityName, Date startDate, Date endDate, String scmcocName, String hsName, String flag, int index, int length);

	/**
	 * 获取进出库数据的记录数
	 * 
	 * @param entityName
	 * @param startDate
	 * @param endDate
	 * @param scmcocName
	 * @param hsName
	 * @param flag
	 * @return
	 */
	Integer findDataCount(String entityName, Date startDate, Date endDate, String scmcocName, String hsName, String flag);

	/**
	 * (根据物料标志【原料】或【成品】) 获取最大流水号
	 * 
	 * @param impExpFlag
	 * @return
	 */
	Integer findMaxSerialNo(String entityName, String imgExgFlag);

	/**
	 * 根据id查询出入库信息
	 * 
	 * @param id
	 * @return
	 */
	Object findStorageById(Class clazz, String id);

	/**
	 * 根据id删除多条出入库
	 * 
	 * @param ids
	 */
	void deleteStoragesByIds(String entityName, String[] ids);

	/**
	 * 汇总入库数量
	 * 
	 * @param storage
	 * @return
	 */
	Object countQtyByPurchaseNo(InStorage storage);

	/**
	 * 汇总入库数量
	 * 
	 * @param storage
	 * @return
	 */
	Object countOutQtyByOrderNo(OutStorage storage);

	/**
	 * 根据入库信息查询采购单表体某项物料的总数
	 * 
	 * @param storage
	 * @return
	 */
	Object countPurchaseItemQty(InStorage storage);

	/**
	 * 根据入库信息查询订单表体某项物料的总数
	 * 
	 * @param storage
	 * @return
	 */
	Object countOrderItemQty(InStorage storage);

	Object getObjectById(String entityName, String id);

	/**
	 * 汇总原料出库的数量
	 * 
	 * @param purachseNo
	 * @param hsCode
	 * @return
	 */
	Object countImgExpStorageQty(String purachseNo, String hsCode);

	/**
	 * 汇总成品出库的数量
	 * 
	 * @param purachseNo
	 * @param hsCode
	 * @return
	 */
	public Object countExgExpStorageQty(String orderNo, String hsCode);

	/**
	 * 根据入库的物料查询采购单中采购数量【原料】
	 * 
	 * @param purachseNo
	 * @param hsCode
	 * @return
	 */
	Object findInStorageQty(String purachseNo, String hsCode);

	/**
	 * 根据入库的物料查询采购单中采购数量【成品】
	 * 
	 * @param purachseNo
	 * @param hsCode
	 * @return
	 */
	public Object findInStorageExgQty(String orderNo, String hsCode);

	InStorage findInStorageById(String id);
}
