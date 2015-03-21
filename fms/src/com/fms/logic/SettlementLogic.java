package com.fms.logic;

import java.util.List;

import com.fms.core.entity.AclUser;
import com.fms.core.entity.Settlement;

public interface SettlementLogic {
	/**
	 * 查询所有的结算方式
	 * 
	 * @return
	 */
	public List<Settlement> findAllSettlement(AclUser loginUser,String searhStr);

	/**
	 * 保存结算方式
	 * 
	 * @return
	 */
	public void saveSettlement(AclUser loginUser,Settlement settlement);
	
	/**
	 * 根据id来查询结算方式
	 * @param id
	 * @return
	 */
	public Settlement findSettById(AclUser loginUser,String id);
	
	/**
	 * 根据code来查找结算方式
	 * @param code
	 * @return
	 */
	public String findSettByCode(AclUser loginUser,String code);
	
	/**
	 * 根据Id来删除结算方式信息
	 */
	public void delSettltById(AclUser loginUser,String [] ids);
	
	/**
	 * 根据名称来查找结算方式信息
	 * @param name
	 * @return
	 */
	public Settlement findAllSettlementByName(AclUser loginUser,String name);
	
	/**
	 * 验证导入Excel数据
	 */
	public List<?> doValidata(AclUser loginUser,List<?> dataList);
	
	/**
	 * 保存Excel数据到数据库
	 */
	public Boolean doSaveExcelData(AclUser loginUser,List<?> dataList);
}
