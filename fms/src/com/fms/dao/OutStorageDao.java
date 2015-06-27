package com.fms.dao;

import java.util.Date;
import java.util.List;

import com.fms.base.dao.BaseDao;
import com.fms.core.entity.OutStorage;

/**
 * 出库Dao
 * 
 * @author Administrator
 * 
 */
public interface OutStorageDao extends BaseDao {
	/**
	 * 分页查找进出库数据
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
	 * 根据id查询出出库信息
	 * 
	 * @param id
	 * @return
	 */
	Object findStorageById(Class clazz, String id);

	/**
	 * 根据id删除多条出出库
	 * 
	 * @param ids
	 */
	void deleteStoragesByIds(String entityName, String[] ids);

	/**
	 * 汇总出库数量
	 * 
	 * @param storage
	 * @return
	 */
	Object countQtyByPurchaseNo(OutStorage storage);

	/**
	 * 汇总出库数量
	 * 
	 * @param storage
	 * @return
	 */
	Object countOutQtyByOrderNo(OutStorage storage);

	/**
	 * 根据出库信息查询采购单表体某项物料的总数
	 * 
	 * @param storage
	 * @return
	 */
	Object countPurchaseItemQty(OutStorage storage);

	/**
	 * 根据出库信息查询订单表体某项物料的总数
	 * 
	 * @param storage
	 * @return
	 */
	Object countOrderItemQty(OutStorage storage);

	Object getObjectById(String entityName, String id);

	/**
	 * 
	 * @param imgExgFlag
	 *            物料标志
	 * @param purachseNo
	 *            采购单号
	 * @param orderNo
	 *            订单号
	 * @param hsCode
	 *            物料编码
	 * @return
	 */
	Object countExpQty(String imgExgFlag, String purachseNo, String orderNo, String hsCode);
}
