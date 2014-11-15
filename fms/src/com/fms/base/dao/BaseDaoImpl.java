package com.fms.base.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.lang.reflect.Field;
import org.apache.commons.lang.StringUtils;
import org.hibernate.CacheMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import sun.security.jca.GetInstance.Instance;

import com.fms.base.entity.BaseEntity;
import com.sun.xml.internal.bind.v2.model.core.PropertyInfo;


/**
 * 数据访问基础类
 */
public class BaseDaoImpl extends HibernateDaoSupport implements BaseDao {

	protected void initDao() throws Exception {
		super.initDao();
		this.getHibernateTemplate().setCacheQueries(false);
	}

	/**
	 * 获得分页 List 来自带多个参数的 hsql 语句
	 */
	public List findPageList(String hsql, Object[] objParams, int index,
			int length) {
		return this.findList(hsql, objParams, index, length);
	}

	/**
	 * 获得分页 List 来自带多丄1�7个参数的 hsql 语句
	 */
	public List findPageList(String hsql, Object objParam, int index, int length) {
		Object[] objParams = { objParam };
		return this.findList(hsql, objParams, index, length);
	}

	/**
	 * 获得分页 List 来自带没有参数的 hsql 语句
	 */
	public List findPageList(String hsql, int index, int length) {
		return this.findList(hsql, null, index, length);
	}

	/**
	 * 获得分页 List 来自带多丄1�7来代替名字参数的 hsql 语句
	 */
	public List find(String hsql, Object[] objParams) {
		return this.findList(hsql, objParams, -1, -1);
	}

	/**
	 * 获得分页 List 来自带一丄1�7来代替名字参数的 hsql 语句
	 */
	public List find(String hsql, Object objParam) {
		Object[] objParams = { objParam };
		return this.findList(hsql, objParams, -1, -1);
	}

	/**
	 * 获得扄1�7有的数据来自无参数的 hsql
	 */
	public List find(String hsql) {
		return this.findList(hsql, null, -1, -1);
	}

	public List find(String tableName, String sFields, String sValue) {
		String hsql = null;
		List list = null;
		if (sFields == null || sFields.equals("")) {

			hsql = "select a from " + tableName + " a";
			list = this.getHibernateTemplate().find(hsql);
		} else {
			hsql = "select a from " + tableName + " a where a." + sFields
					+ " like ? ";
			list = this.getHibernateTemplate().find(hsql,
					new Object[] { "%" + sValue + "%" });
		}
		return list;
	}


	/**
	 * 获得 List 来自带多丄1�7来代替名字参数的 hsql 语句
	 */
	public List findBeginToEndList(String hsql, Object[] objParams) {
		return this.findList(hsql, objParams, -1, -1);
	}

	/**
	 * 获得 List 来自带一丄1�7来代替名字参数的 hsql 语句
	 */
	public List findBeginToEndList(String hsql, Object objParam) {
		Object[] objParams = { objParam };
		return this.findList(hsql, objParams, -1, -1);
	}

	
	/**
	 * 获得从某行开始到朄1�7后的数据来自无参数的 hsql
	 */
	public List findBeginToEndList(String hsql) {
		return this.findList(hsql, null, -1, -1);
	}

	/**
	 * 查询(没用缓存)
	 * 
	 * @param hsql
	 * @param objParams
	 * @return
	 */
	public List findListNoCache(final String hsql, final Object[] objParams) {
		return this.findListNoCache(hsql, objParams, -1, -1);
	}

	/**
	 * 查询(没用缓存)
	 * 
	 * @param hsql
	 * @return
	 */
	public List findListNoCache(final String hsql) {
		return this.findListNoCache(hsql, -1, -1);
	}

	/**
	 * 公共分页查询方法
	 */
	private List findList(final String hsql, final Object[] objParams,
			final int index, final int length) {
		if ((index == -1) & (length == -1)) {
			if (objParams == null) {
				return getHibernateTemplate().find(hsql);
			} else {
				return getHibernateTemplate().find(hsql, objParams);
			}
		}

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				// List list = new ArrayList();
//				session.setCacheMode(CacheMode.IGNORE);
				Query query = session.createQuery(hsql);
				if (objParams != null) {
					for (int i = 0; i < objParams.length; i++) {
						query.setParameter(i, objParams[i]);
					}
				}
				if (index != -1) {
					query.setFirstResult(index);
				}
				if (length != -1) {
					query.setMaxResults(length);
				}
				return query.list();
			}
		});
	}

	/**
	 * 公共分页查询方法
	 */
	public List findListNoCache(final String hsql, final Object[] objParams,
			final int index, final int length) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				// List list = new ArrayList();
				session.setCacheMode(CacheMode.IGNORE);
				Query query = session.createQuery(hsql).setCacheable(false);
				if (objParams != null) {
					for (int i = 0; i < objParams.length; i++) {
						query.setParameter(i, objParams[i]);
					}
				}
				if (index != -1) {
					query.setFirstResult(index);
				}
				if (length != -1) {
					query.setMaxResults(length);
				}
				return query.list();
			}
		});
	}

	/**
	 * 公共分页查询方法
	 */
	public List findListNoCache(final String hsql, final int index,
			final int length) {
		return findListNoCache(hsql, null, index, length);
	}

	/**
	 * 公共分页查询方法
	 */
	public List findNoCache(final String hsql, final Object[] objParams) {
		return findListNoCache(hsql, objParams, -1, -1);
	}
	
	/**
	 * 公共查询方法
	 * @param hsql
	 * @param objParams
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object uniqueResult(final String hsql , final Object[] objParams){
		return getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				// List list = new ArrayList();
				session.setCacheMode(CacheMode.IGNORE);
				Query query = session.createQuery(hsql).setCacheable(false);
				if (objParams != null) {
					for (int i = 0; i < objParams.length; i++) {
						query.setParameter(i, objParams[i]);
					}
				}				
				query.setFirstResult(0);								
				query.setMaxResults(1);
				return query.uniqueResult();
			}
		});		
	}
	
	/**
	 * 公共查询方法
	 * @param hsql
	 * @param objParams
	 * @return
	 */
	public int count(final String hsql , final Object[] objParams){
		@SuppressWarnings("unchecked")
		Number number = (Number)getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				// List list = new ArrayList();
				session.setCacheMode(CacheMode.IGNORE);
				Query query = session.createQuery(hsql).setCacheable(false);
				if (objParams != null) {
					for (int i = 0; i < objParams.length; i++) {
						query.setParameter(i, objParams[i]);
					}
				}				
				query.setFirstResult(0);								
				query.setMaxResults(1);
				return query.uniqueResult();
			}
		});		
		return number == null? 0 : number.intValue();
	}

	/**
	 * 批量修改不用cache
	 */
	@SuppressWarnings("unchecked")
	public List batchSaveOrUpdate(final List list) {
		return (List) getHibernateTemplate().execute(
				new HibernateCallback() {
					public List doInHibernate(Session session)
							throws HibernateException {
						
						List ls = new ArrayList();
						session.setCacheMode(CacheMode.IGNORE);
						for (int i = 0; i < list.size(); i++) {
							session.saveOrUpdate(list.get(i));
							ls.add(list.get(i));
						}
						session.flush();
						session.clear();
						return ls;
					}
				});
	}

	/**
	 * 修改不用cache
	 */
	public Object saveOrUpdateNoCache(final Object obj) {
		return getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				session.setCacheMode(CacheMode.IGNORE);
				session.saveOrUpdate(obj);
				session.flush();
				session.clear();
				return obj;
			}
		});
	}

	/**
	 * 批量修改或�1�7�批量删附1�7
	 */
	public int batchUpdateOrDelete(final String hsql, final Object[] objParams) {

		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(hsql);
						if (objParams != null) {
							for (int i = 0; i < objParams.length; i++) {
								query.setParameter(i, objParams[i]);
							}
						}
						return query.executeUpdate();
					}
				});
	}
	
	public Object findUniqueResult(final String hql , final Object[] objParams){
		return getHibernateTemplate().execute(new HibernateCallback<Object>(){
		//	@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query q = session.createQuery(hql);
				if (objParams != null) {
					for (int i = 0; i < objParams.length; i++) {
						q.setParameter(i, objParams[i]);
					}
				}
				List result = q.list();
				//q.setFirstResult(0);
				//q.setMaxResults(1);
				if(null!=result && result.size()>0){
					return result.get(0);
				}
				return null;
			}
		});
	} 

	/**
	 * 批量修改或�1�7�批量删附1�7
	 */
	@SuppressWarnings("unchecked")
	public int batchUpdateOrDeleteInSql(final String sql,
			final List<List<Object>> list, final int batchSize) {

		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Connection connection = null;
						try {							
							connection = session.connection();
							connection.setAutoCommit(true);
							PreparedStatement stmt = connection
									.prepareStatement(sql);
							int size = list.size();
							for (int i = 0; i < size; i++) {
								List objParams = list.get(i);
								for (int j = 0; j < objParams.size(); j++) {
									stmt.setObject(j + 1, objParams.get(j));
								}
								stmt.addBatch();
								if ((i % batchSize) == 0 || i == size - 1) {
									stmt.executeBatch();
									stmt.clearBatch();
								}
							}
							stmt.close();
							return size;
						} catch (Exception ex) {
							throw new RuntimeException(ex);
						} finally {
							try {
								if (connection != null
										&& !connection.isClosed()) {
									connection.close();
								}
							} catch (Exception ex) {
								throw new RuntimeException(ex);
							}
						}
					}
				});
	}

	@SuppressWarnings("unchecked")
	public int executeSql(final String sql,final Object[] params){
		
		return (Integer)getHibernateTemplate().execute(new HibernateCallback() {			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				SQLQuery q = session.createSQLQuery(sql);
				for(int i = 0 ;i < params.length ; i++){
					q.setParameter(i, params[i]);
				}
				return q.executeUpdate();
			}
		});
	}
	
	/**
	 * 批量修改或�1�7�批量删附1�7
	 */
	public int batchUpdateOrDelete(String hsql) {
		
		return batchUpdateOrDelete(hsql, null);
	}

	/**
	 * 批量修改或�1�7�批量删附1�7
	 */
	public int batchUpdateOrDelete(String hsql, Object objParam) {
		return batchUpdateOrDelete(hsql, new Object[] { objParam });
	}

	public Object load(final Class entityClass, final Serializable id) {
		try {
			return this.getHibernateTemplate().load(entityClass, id);
		} catch (Exception e) {
			return this.getHibernateTemplate().get(entityClass, id);
		}
	}

	public Object get(final Class entityClass, final Serializable id) {
		return this.getHibernateTemplate().get(entityClass, id);
	}

	/**
	 * 保存实体对象 防止传�1�7�过来的对象和缓存中的对象的版本不一臄1�7
	 * 
	 * @param obj
	 */
	public void saveOrUpdate(Object obj) {
		if (obj == null) {
			return;
		}
		if(obj instanceof BaseEntity && StringUtils.isNotBlank(((BaseEntity)obj).getId())){
			((BaseEntity)obj).setModifyDate(new Date());//设置修改时间
			this.getHibernateTemplate().merge(obj);				
		}else{
			((BaseEntity)obj).setCreateDate(new Date());//设置创建时间
			this.getHibernateTemplate().save(obj);
		}		

	}
	
	@SuppressWarnings("unchecked")
	public void merger(final Object obj){
		if(obj == null)
			return;
		getHibernateTemplate().execute(new HibernateCallback() {			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				return session.merge(obj);
			}
		});
	}

	/**
	 * 删除实体对象 防止传�1�7�过来的对象和缓存中的对象的版本不一臄1�7
	 * 
	 * @param obj
	 */
	public void delete(Object obj) {
		if (obj == null) {
			return;
		}
		if(obj instanceof BaseEntity){			
			this.getHibernateTemplate().delete(load(obj.getClass(),((BaseEntity)obj).getId()));			
		}else{
			this.getHibernateTemplate().delete(obj);
		}
	}

	/**
	 * 删除实体对象 防止传�1�7�过来的对象和缓存中的对象的版本不一臄1�7
	 * 
	 * @param entities
	 *            Collection类型
	 */
	public void deleteAll(Collection entities) {
		Iterator iterator = entities.iterator();
		while (iterator.hasNext()) {
			this.delete(iterator.next());
		}
	}
	
/*	*//**
	 * 
	 * @param pageIndex
	 * @param pageRows
	 * @param condition
	 * @return
	 *//*
	 public List<T> findAll(int pageIndex,int pageRows,String condition){
		 pageIndex=(pageIndex-1)*pageRows;
		 if(condition!=null){
			 return getMySession().createQuery("FROM "+clazz.getSimpleName()+" where "+condition ).setFirstResult(pageIndex).setMaxResults(pageRows).list();
		 }
	 return getMySession().createQuery("FROM "+clazz.getSimpleName()).setFirstResult(pageIndex).setMaxResults(pageRows).list();
   }
*/
	
	public Integer getSerialNo(String clazz){
		String filedName = "serialNo";
		Integer serialNo = null;
		Field[] fields;
		try {
			Class c = Class.forName("com.fms.core.entity."+clazz);
			fields = c.getDeclaredFields();
			for(Field filed:fields){
				System.out.println(filed.getName()+"\n");
				if(filedName.equals(filed.getName())){
					String hql = "select max(a.serialNo) from "+clazz+" a ";
					serialNo = (Integer) this.uniqueResult(hql, new Object[]{});
					return serialNo;
				}
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

}