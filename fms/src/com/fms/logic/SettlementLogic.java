package com.fms.logic;

import java.util.List;

import com.fms.core.entity.Settlement;

public interface SettlementLogic {
	/**
	 * 查询所有的结算方式
	 * 
	 * @return
	 */
	public List<Settlement> findAllSettlement(String searhStr);

	/**
	 * 保存结算方式
	 * 
	 * @return
	 */
	public void saveSettlement(Settlement settlement);
	
	/**
	 * 根据id来查询结算方式
	 * @param id
	 * @return
	 */
	public Settlement findSettById(String id);
	
	/**
	 * 根据code来查找结算方式
	 * @param code
	 * @return
	 */
	public String findSettByCode(String code);
	
	/**
	 * 根据Id来删除结算方式信息
	 */
	public void delSettltById(String [] ids);
	
	/**
	 * 根据名称来查找结算方式信息
	 * @param name
	 * @return
	 */
	public Settlement findAllSettlementByName(String name);
	
	/**
	 * 验证导入Excel数据
	 */
	public List<?> doValidata(List<?> dataList);
	
	/**
	 * 保存Excel数据到数据库
	 */
	public Boolean doSaveExcelData(List<?> dataList);
}
