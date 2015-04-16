package com.fms.logic;

import java.util.Date;
import java.util.List;

import com.fms.core.entity.AclUser;

/**
 * 进出库Logic
 * 
 * @author Administrator
 * 
 */
public interface StorageLogic {
	/**
	 * 分页查找进出库数据
	 * 
	 * @param user
	 * @param entityName
	 * @param startDate
	 * @param endDate
	 * @param scmcocName
	 * @param hsCode
	 * @param index
	 * @param length
	 * @return
	 */
	List findStorage(AclUser user, String entityName, Date startDate, Date endDate, String scmcocName, String hsName, String flag, int index, int length);

	/**
	 * 获取进出库数据的记录数
	 * 
	 * @param entityName
	 * @param startDate
	 * @param endDate
	 * @param scmcocName
	 * @param hsName
	 * @return
	 */
	Integer findDataCount(String entityName, Date startDate, Date endDate, String scmcocName, String hsName, String flag);
}
