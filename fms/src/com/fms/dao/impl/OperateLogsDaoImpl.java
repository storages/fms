package com.fms.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.fms.annotation.CnFileName;
import com.fms.base.dao.BaseDaoImpl;
import com.fms.core.entity.AclUser;
import com.fms.core.entity.OperateLogs;
import com.fms.dao.OperateLogsDao;

public class OperateLogsDaoImpl extends BaseDaoImpl implements OperateLogsDao {

	public void saveNewLogs(AclUser logUser, Object obj) {
		// TODO Auto-generated method stub
		OperateLogs logs = new OperateLogs();
		logs.setCreateDate(new Date());
		logs.setOrgUser(logUser);
		logs.setModifyDate(new Date());
		logs.setOrgType(OperateLogs.CREATE_OPERATE);
		StringBuffer strBuffer = new StringBuffer();
		Class clzz = obj.getClass();
		Field[] fields = clzz.getDeclaredFields();
		for (Field f : fields) {
			// 获取字段中包含fieldMeta的注解
			CnFileName meta = f.getAnnotation(CnFileName.class);
			if (meta != null) {
				try {
					strBuffer.append(meta.name()
							+ ":"
							+ clzz.getMethod("get" + change(f.getName()))
									.invoke(obj, null) + ",");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		logs.setMsg(strBuffer.toString());
		this.saveOrUpdate(logs);
	}

	public void saveEditLogs(AclUser logUser, Object newObj, int id) {

		Object obj = this.load(newObj.getClass(), id);

		OperateLogs logs = new OperateLogs();
		logs.setCreateDate(new Date());
		logs.setOrgUser(logUser);
		logs.setModifyDate(new Date());
		logs.setOrgType(OperateLogs.EDIT_OPERATE);
		StringBuffer strBuffer = new StringBuffer();
		Class clzz = obj.getClass();
		Class newclazz = newObj.getClass();
		Field[] fields = clzz.getDeclaredFields();
		Field[] newFields = newclazz.getDeclaredFields();
		for (int x = 0; x < fields.length; x++) {
			// 获取字段中包含fieldMeta的注解
			CnFileName meta = fields[x].getAnnotation(CnFileName.class);
			// CnFileName newMeta = fields[x].getAnnotation(CnFileName.class);
			if (meta != null) {
				try {
					String strVal = clzz
							.getMethod("get" + change(fields[x].getName()),
									null).invoke(obj, null).toString();
					String newStrVal = newclazz
							.getMethod("get" + change(fields[x].getName()),
									null).invoke(obj, null).toString();
					if(!strVal.equals(newStrVal)){
						strBuffer.append(meta.name() + ":" + "{" + strVal + "}改成{"
								+ newStrVal + "}" + ",");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		logs.setMsg(strBuffer.toString());
		this.saveOrUpdate(logs);

	}

	public static String change(String src) {
		if (src != null) {
			StringBuffer sb = new StringBuffer(src);
			sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
			return sb.toString();
		} else {
			return null;
		}
	}

}
