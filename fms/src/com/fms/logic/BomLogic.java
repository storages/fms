package com.fms.logic;

import java.util.List;

import com.fms.core.entity.AclUser;
import com.fms.core.entity.BomExg;

/**
 * Bom表接口
 * 
 * @author Administrator
 * 
 */
public interface BomLogic {
	/**
	 * 查找BOM成品
	 * 
	 * @return
	 */
	List<BomExg> findBomExg(AclUser loginUser,String hsName, String hsCode, String hsModel, Integer index, Integer length);

	/**
	 * 按条件查找记录数
	 * 
	 * @param className
	 * @param searchStr
	 * @return
	 */
	Integer findDataCount(AclUser loginUser,String className, String hsName, String hsCode, String hsModel);

	/**
	 * 保存BOM成品表
	 * 
	 * @param data
	 * @return
	 */
	List<BomExg> saveBomExg(AclUser loginUser,List<BomExg> data);

	/**
	 * 删除BOM成品表
	 * 
	 * @param idArr
	 */
	void delBomExgByIds(AclUser loginUser,String[] idArr);
}
