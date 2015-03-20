package com.fms.dao;

import java.util.List;

import com.fms.base.dao.BaseDao;
import com.fms.core.entity.BomExg;

/**
 * Bom表
 * 
 * @author Administrator
 * 
 */
public interface BomDao extends BaseDao {

	/**
	 * 查找BOM成品
	 * 
	 * @return
	 */
	List<BomExg> findBomExg(String hsName, String hsCode, String hsModel, Integer index, Integer length);

	/**
	 * 按条件查找记录数
	 * 
	 * @param className
	 * @param searchStr
	 * @return
	 */
	Integer findDataCount(String className, String hsName, String hsCode, String hsModel);

	/**
	 * 删除BOM成品
	 * 
	 * @param idArr
	 */
	void delBomExgByIds(String[] idArr);
}
