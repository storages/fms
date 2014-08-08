package com.fms.dao;

import java.util.List;

import com.fms.base.dao.BaseDao;
import com.fms.core.entity.Currencies;

public interface CurrenciesDao extends BaseDao{
	/**
	 * 获取所有的供应商或客户[分页]
	 * @return
	 */
	public List<Currencies> findAllCurrencies(String likeStr,Integer index,Integer length);
	/**
	 * 根据id查询供应商或客户
	 * @param id
	 * @return
	 */
	public Currencies findCurrenciesById(String id);
	/**
	 * 保存单个供应商或客户
	 * @param scmcoc
	 */
	public void saveCurrencies(Currencies currencies);
	
	/**
	 * 批量保存供应商或客户
	 * @param data
	 */
	public void betchSaveCurrencies(List<Currencies> data);
	/**
	 * 根据id删除供应商或客户
	 * @param id
	 */
	public void deleteCurrenciesById(String id);
	
	/**
	 * 根据编码查询供应商或客户
	 * @param code
	 * @return
	 */
	public Currencies findCurrenciesByCode(String code);
	
	/**
	 * 批量删除供应商或客户
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

