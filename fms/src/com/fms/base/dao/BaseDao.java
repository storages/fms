package com.fms.base.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface BaseDao {
	/**
	 * 获得分页 List 来自带多个参数的 hsql 语句
	 */
	public List findPageList(String hsql, Object[] objParams, int index,
			int length);
	/**
	 * 获得分页 List 来自带多丄1�7个参数的 hsql 语句
	 */
	public List findPageList(String hsql, Object objParam, int index, int length);

	/**
	 * 获得分页 List 来自带没有参数的 hsql 语句
	 */
	public List findPageList(String hsql, int index, int length);
	
	/**
	 * 获得分页 List 来自带多丄1�7来代替名字参数的 hsql 语句
	 */
	public List find(String hsql, Object[] objParams);
	
	
	
	/**
	 * 获得分页 List 来自带一丄1�7来代替名字参数的 hsql 语句
	 */
	public List find(String hsql, Object objParam);
	
	
	/**
	 * 获得扄1�7有的数据来自无参数的 hsql
	 */
	public List find(String hsql);
	
	
	public List find(String tableName, String sFields, String sValue) ;
	
	

	/**
	 * 获得 List 来自带多丄1�7来代替名字参数的 hsql 语句
	 */
	public List findBeginToEndList(String hsql, Object[] objParams) ;
	
	/**
	 * 获得 List 来自带一丄1�7来代替名字参数的 hsql 语句
	 */
	public List findBeginToEndList(String hsql, Object objParam);
	
	
			/**
			 * 获得从某行开始到朄1�7后的数据来自无参数的 hsql
			 */
			public List findBeginToEndList(String hsql);
	
	
	
	/**
	 * 查询(没用缓存)
	 * 
	 * @param hsql
	 * @param objParams
	 * @return
	 */
	public List findListNoCache(final String hsql, final Object[] objParams);

	/**
	 * 查询(没用缓存)
	 * 
	 * @param hsql
	 * @return
	 */
	public List findListNoCache(final String hsql) ;
	

	
	public List findListNoCache(final String hsql, final Object[] objParams,
			final int index, final int length);
	
	
	/**
	 * 公共分页查询方法
	 */
	public List findListNoCache(final String hsql, final int index,
			final int length) ;
	
	
	/**
	 * 公共分页查询方法
	 */
	public List findNoCache(final String hsql, final Object[] objParams);
	
	
	
	
	/**
	 * 公共查询方法
	 * @param hsql
	 * @param objParams
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object uniqueResult(final String hsql , final Object[] objParams);
	
	
	
	/**
	 * 公共查询方法
	 * @param hsql
	 * @param objParams
	 * @return
	 */
	public int count(final String hsql , final Object[] objParams);
	
	
	/**
	 * 批量修改不用cache
	 */
	@SuppressWarnings("unchecked")
	public List batchSaveOrUpdate(final List list) ;
	
	
	/**
	 * 修改不用cache
	 */
	public Object saveOrUpdateNoCache(final Object obj) ;
	
	/**
	 * 批量修改或�1�7�批量删附1�7
	 */
	public int batchUpdateOrDelete(final String hsql, final Object[] objParams);
	
	
	public Object findUniqueResult(final String hql , final Object[] objParams);
	
	
	/**
	 * 批量修改或�1�7�批量删附1�7
	 */
	@SuppressWarnings("unchecked")
	public int batchUpdateOrDeleteInSql(final String sql,
			final List<List<Object>> list, final int batchSize) ;
	
	@SuppressWarnings("unchecked")
	public int executeSql(final String sql,final Object[] params);
	

	/**
	 * 批量修改或�1�7�批量删附1�7
	 */
	public int batchUpdateOrDelete(String hsql) ;
	
	/**
	 * 批量修改或�1�7�批量删附1�7
	 */
	public int batchUpdateOrDelete(String hsql, Object objParam) ;
	

	public Object load(final Class entityClass, final Serializable id) ;
	
	public Object get(final Class entityClass, final Serializable id);
	
	
	/**
	 * 保存实体对象 防止传�1�7�过来的对象和缓存中的对象的版本不一臄1�7
	 * 
	 * @param obj
	 */
	public void saveOrUpdate(Object obj) ;
	

	@SuppressWarnings("unchecked")
	public void merger(final Object obj);
	
	
	/**
	 * 删除实体对象 防止传�1�7�过来的对象和缓存中的对象的版本不一臄1�7
	 * 
	 * @param obj
	 */
	public void delete(Object obj) ;
	
	

	/**
	 * 删除实体对象 防止传�1�7�过来的对象和缓存中的对象的版本不一臄1�7
	 * 
	 * @param entities
	 *            Collection类型
	 */

	public void deleteAll(Collection entities);
	
	/**
	 * 获取流水号
	 * @param className
	 * @return
	 */
	public Integer getSerialNo(String clazz);
	
	/**
	 * 根据id查找实体
	 * @param id
	 * @return
	 */
	public Object findEntityById(String entityName,String id);
}
