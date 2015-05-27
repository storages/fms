package com.fms.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fms.base.dao.BaseDaoImpl;
import com.fms.dao.StorageDao;
import com.fms.utils.FmsDateUtils;

/**
 * 进出库Dao
 * 
 * @author Administrator
 * 
 */
public class StorageDaoImpl extends BaseDaoImpl implements StorageDao {

	public List findStorage(String entityName, Date startDate, Date endDate, String scmcocName, String hsName, String flag, int index, int length) {
		List params = new ArrayList();
		String inOutDate = "InStorage".equals(entityName) ? "and a.impDate " : "and a.expDate";
		StringBuilder sql = new StringBuilder();
		sql.append("select a from ");
		sql.append(entityName.trim());
		sql.append(" a left join fetch a.material mat ");
		sql.append(" left join fetch a.scmcoc scm ");
		sql.append(" left join fetch mat.unit u ");
		sql.append(" left join fetch mat.materialType t ");
		sql.append(" left join fetch a.stock stock where 1=1 ");
		if (StringUtils.isNotBlank(flag)) {
			sql.append(" a.imgExgFlag= ? ");
			params.add(flag);
		}
		if (startDate != null) {
			sql.append(inOutDate);
			sql.append(" >= ?");
			params.add(FmsDateUtils.getStartDate(startDate));
		}
		if (endDate != null) {
			sql.append(inOutDate);
			sql.append(" <= ?");
			params.add(FmsDateUtils.getEndDate(endDate));
		}
		if (StringUtils.isNotBlank(scmcocName)) {
			sql.append(" and scm.name like ? ");
			params.add("%" + scmcocName + "%");
		}
		if (StringUtils.isNotBlank(hsName)) {
			sql.append(" and mat.hsName like ? ");
			params.add("%" + hsName + "%");
		}
		return this.findPageList(sql.toString(), params.toArray(), index, length);
	}

	public Integer findDataCount(String entityName, Date startDate, Date endDate, String scmcocName, String hsName, String flag) {
		List params = new ArrayList();
		String inOutDate = "InStorage".equals(entityName) ? "and a.impDate " : "and a.expDate";
		StringBuilder sql = new StringBuilder();
		sql.append("select count(a.id) from ");
		sql.append(entityName.trim());
		sql.append(" a left join a.material mat ");
		sql.append(" left join mat.unit u ");
		sql.append(" left join a.scmcoc scm ");
		sql.append(" left join a.stock stock where 1=1 ");
		if (StringUtils.isNotBlank(flag)) {
			sql.append(" a.imgExgFlag= ? ");
			params.add(flag);
		}
		if (startDate != null) {
			sql.append(inOutDate);
			sql.append(" >= ?");
			params.add(FmsDateUtils.getStartDate(startDate));
		}
		if (endDate != null) {
			sql.append(inOutDate);
			sql.append(" <= ?");
			params.add(FmsDateUtils.getEndDate(endDate));
		}
		if (StringUtils.isNotBlank(scmcocName)) {
			sql.append(" and scm.name like ? ");
			params.add("%" + scmcocName + "%");
		}
		if (StringUtils.isNotBlank(hsName)) {
			sql.append(" and mat.hsName like ? ");
			params.add("%" + hsName + "%");
		}
		return this.count(sql.toString(), params.toArray());
	}

	public Integer findMaxSerialNo(String entityName, String imgExgFlag) {
		List params = new ArrayList();
		String hql = "select max(a.serialNo) from " + entityName.trim() + " a ";
		// if (StringUtils.isNotBlank(imgExgFlag)) {
		// hql += " where a.imgExgFlag =? ";
		// params.add(imgExgFlag);
		// }
		return (Integer) this.uniqueResult(hql, params.toArray());
	}

	public Object findStorageById(Class clazz, String id) {
		String hql = "select a from " + clazz.getSimpleName() + " a " //
				+ " left join fetch a.material b " //
				+ " left join fetch a.scmcoc c " //
				+ " left join fetch c.settlement d " //
				+ " left join fetch b.unit e "//
				+ " left join fetch b.materialType f  "//
				+ " left join fetch a.stock g  "//
				+ " where a.id =? ";
		return this.findUniqueResult(hql, new Object[] { id });
	}

	public void deleteStoragesByIds(String entityName, String[] ids) {
		List param = new ArrayList();
		if (null != ids && ids.length > 0) {
			String hql = "DELETE FROM " + entityName + " a WHERE a.id = ? ";
			param.add(ids[0]);
			for (int i = 1; i < ids.length; i++) {
				hql += " OR a.id =? ";
				param.add(ids[i]);
			}
			this.batchUpdateOrDelete(hql, param.toArray());
		}
	}

}
