package com.fms.dao;

import java.util.List;

import com.fms.base.dao.BaseDao;
import com.fms.core.entity.Currencies;
import com.fms.core.entity.Settlement;

public interface CurrenciesDao extends BaseDao{
	/**
	 * 获取所有的交易货币[分页]
	 * @return
	 */
	public List<Currencies> findAllCurrencies(String likeStr,Integer index,Integer length);
	/**
	 * 根据id查询交易货币
	 * @param id
	 * @return
	 */
	
	
	public List<Currencies> findAllCurrencies();
	
	public Currencies findCurrenciesById(String id);
	/**
	 * 保存单个交易货币
	 * @param scmcoc
	 */
	public void saveCurrencies(Currencies currencies);
	
	/**
	 * 批量保存交易货币
	 * @param data
	 */
	public void betchSaveCurrencies(List<Currencies> data);
	/**
	 * 根据id删除交易货币
	 * @param id
	 */
	public void deleteCurrenciesById(String [] ids);
	
	/**
	 * 根据编码查询交易货币
	 * @param code
	 * @return
	 */
	public String findCurrenciesByCode(String code);
	
	/**
	 * 批量删除交易货币
	 * @param data
	 */
	public void delete(List<String> ids);
	
	/**
	 * 查找数据总条数
	 * @param className
	 * @param isCustom
	 * @param name
	 * @return
	 */
	public Integer findDataCount(String className,String name);
}

