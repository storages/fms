package com.fms.logic;

import java.util.List;

import com.fms.core.entity.AclUser;
import com.fms.core.entity.Currencies;
import com.fms.temp.TempCurrencies;

/**
 * 供应商或客户逻辑中心接口
 * @author Administrator
 *
 */
public interface CurrenciesLogic {

	/**
	 * 获取所有的交易货币[分页]
	 * @return
	 */
	public List<Currencies> findAllCurrencies(AclUser loginUser,String likeStr,Integer index,Integer length);
	/**
	 * 根据id查询交易货币
	 * @param id
	 * @return
	 */
	public Currencies findCurrenciesById(AclUser loginUser,String id);
	/**
	 * 保存单个交易货币
	 * @param scmcoc
	 */
	public void saveCurrencies(AclUser loginUser,Currencies currencies);
	
	/**
	 * 批量保存交易货币
	 * @param data
	 */
	public void betchSaveCurrencies(AclUser loginUser,List<Currencies> data);
	/**
	 * 根据id删除交易货币
	 * @param id
	 */
	public void deleteCurrenciesById(AclUser loginUser,String [] id);
	
	/**
	 * 根据编码查询交易货币
	 * @param code
	 * @return
	 */
	public String findCurrenciesByCode(AclUser loginUser,String code);
	
	/**
	 * 批量删除交易货币
	 * @param data
	 */
	public void delete(AclUser loginUser,List<String> ids);
	
	/**
	 * 查找数据总条数
	 * @param className
	 * @param isCustom
	 * @param name
	 * @return
	 */
	public Integer findDataCount(AclUser loginUser,String className,String name);

	
	/**
	 * 验证导入Excel数据
	 */
	public List<?> doValidata(AclUser loginUser,List<?> dataList);
	
	/**
	 * 保存Excel数据到数据库
	 */
	public Boolean doSaveExcelData(AclUser loginUser,List<?> dataList); 

}
