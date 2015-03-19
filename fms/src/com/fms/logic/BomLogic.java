package com.fms.logic;

import java.util.List;

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
	List<BomExg> findBomExg(String hsName, String hsCode, String hsModel, Integer index, Integer length);

	/**
	 * 按条件查找记录数
	 * 
	 * @param className
	 * @param searchStr
	 * @return
	 */
	Integer findDataCount(String className, String hsName, String hsCode, String hsModel);
}
