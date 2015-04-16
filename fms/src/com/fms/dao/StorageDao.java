package com.fms.dao;

import java.util.Date;
import java.util.List;

import com.fms.base.dao.BaseDao;

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
}
