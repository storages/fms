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
	List<BomExg> findBomExg();
}
