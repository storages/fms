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
	List<BomExg> findBomExg();
}
