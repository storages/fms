package com.fms.logic;

import java.util.Date;
import java.util.List;

import com.fms.core.entity.AclUser;
import com.fms.core.entity.OutStorage;
import com.fms.temp.TempOutStorage;

/**
 * 出库Logic
 * 
 * @author Administrator
 * 
 */
public interface OutStorageLogic {
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

	/**
	 * (根据物料标志【原料】或【成品】) 获取最大流水号
	 * 
	 * @param user
	 * @param impExpFlag
	 * @return
	 */
	Integer findMaxSerialNo(AclUser user, String entityName, String imgExgFlag);

	/**
	 * 根据id查询出出库信息
	 * 
	 * @param id
	 * @return
	 */
	Object findStorageById(Class clazz, String id);

	/**
	 * 保存出出库
	 * 
	 * @param storage
	 */
	String saveStorage(AclUser user, OutStorage storage);

	/**
	 * 根据id删除多条出库
	 * 
	 * @param ids
	 */
	void deleteStoragesByIds(AclUser user, String entityName, String[] ids);

	/**
	 * 验证导入数据
	 * 
	 * @param tempOutStorages
	 * @return
	 */
	List doValidata(List<TempOutStorage> tempOutStorages);

	/**
	 * 保存Excel导入数据
	 * 
	 * @param list
	 */
	Boolean doSaveExcelData(AclUser aclUser, List<TempOutStorage> list);
}
