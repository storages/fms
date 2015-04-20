package com.fms.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
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

	public void saveEditLogs(AclUser logUser, Object newObj, String id) {

		Object obj = this.get(newObj.getClass(), id);

		OperateLogs logs = new OperateLogs();
		logs.setCreateDate(new Date());
		logs.setOrgUser(logUser);
		logs.setModifyDate(new Date());
		logs.setOrgType(OperateLogs.EDIT_OPERATE);
		StringBuffer strBuffer = new StringBuffer();
		StringBuffer ismustBuffer=new StringBuffer();
		StringBuffer subBuffer=new StringBuffer();
		Class clzz = obj.getClass();
		CnFileName clzzMeta= newObj.getClass().getAnnotation(CnFileName.class);
		strBuffer.append(clzzMeta.name()+":");
		Class newclazz = newObj.getClass();
		Field[] fields = clzz.getDeclaredFields();
		Field[] newFields = newclazz.getDeclaredFields();
		for (int x = 0; x < newFields.length; x++) {
			// 获取字段中包含fieldMeta的注解
			CnFileName meta = newFields[x].getAnnotation(CnFileName.class);
			// CnFileName newMeta = fields[x].getAnnotation(CnFileName.class);
			if (meta != null) {
				try {
					
					if(meta.isMustRecord().toString().equals("T"))
					{
						ismustBuffer.append(meta.name()+""+clzz
								.getMethod("get" + change(newFields[x].getName()),
										null).invoke(obj, null).toString());
					}
					//如果字段是一个子对象
					if(meta.isObjectModal().toString().equals("T")){
						Object subObj = clzz
								.getMethod("get" + change(fields[x].getName()),
										null).invoke(obj, null);
						Object subNewObj = newclazz
								.getMethod("get" + change(newFields[x].getName()),
										null).invoke(newObj, null);
						Class subclzz= subObj.getClass();
						subNewObj=this.get(subNewObj.getClass(),subclzz.getMethod("getId",null).invoke(subNewObj,null).toString());
						Field[] subNewFields=	subNewObj.getClass().getDeclaredFields();
						Field[] subFields=	subObj.getClass().getDeclaredFields();
						
						for(int z=0;z<subNewFields.length;z++){
							CnFileName submeta = subNewFields[z].getAnnotation(CnFileName.class);
						     if(null!=submeta){
						    	 if(meta.isMustRecord().toString().equals("T"))
									{
						    		   String strVal = subclzz
											.getMethod("get" + change(subFields[z].getName()),
													null).invoke(subObj, null).toString();
									   String newStrVal = subclzz
											.getMethod("get" + change(subNewFields[z].getName()),
													null).invoke(subNewObj, null).toString();
									   if(!strVal.equals(newStrVal)){
										    subBuffer.append(meta.name() + ":" + "{" + strVal + "}改成{"
												+ newStrVal + "}" + ",");
									     }
									}
						     }
						}
						if(subBuffer.toString().length()>0){
							strBuffer.append(meta.name()+"[");
							strBuffer.append(subBuffer.toString());
							strBuffer.append("]");
						}
						
					}else{//否则是个普通字段普通处理
						String strVal = clzz
								.getMethod("get" + change(fields[x].getName()),
										null).invoke(obj, null).toString();
						Object newValObj = newclazz
								.getMethod("get" + change(newFields[x].getName()),
										null).invoke(newObj, null);
						String newStrVal=newValObj.toString();
						
						if(newFields[x].getType().getSimpleName().equals("Date")){
							Date dataVal= (Date)newValObj;
		                   SimpleDateFormat simp=new SimpleDateFormat("yyyy-MM-dd");
							     newStrVal=simp.format(dataVal);
						}
					
						if(!strVal.equals(newStrVal)){
							strBuffer.append(meta.name() + ":" + "{" + strVal + "}改成{"
									+ newStrVal + "}" + ",");
						}
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

	
	public void saveDeleteLogs(AclUser logUser,String[] ids, Class clazz) {
		// TODO Auto-generated method stub
		OperateLogs logs = new OperateLogs();
		logs.setCreateDate(new Date());
		logs.setOrgUser(logUser);
		logs.setModifyDate(new Date());
		logs.setOrgType(OperateLogs.REMOVE_OPERATE);
		StringBuffer strBuffer = new StringBuffer();
		CnFileName clameta= (CnFileName) clazz.getAnnotation(CnFileName.class);
		strBuffer.append(clameta.name());
		for(String id:ids){
			strBuffer.append("[");
			Object obj = this.get(clazz, id);
			Field[] fields=	clazz.getDeclaredFields();
			StringBuffer	subBuffer  = new StringBuffer();
			for (int x = 0; x < fields.length; x++) {
				subBuffer.append("{");
				CnFileName meta = fields[x].getAnnotation(CnFileName.class);
				if (meta != null) {
					 if(meta.isMustRecord().toString().equals("T"))
						{
						   try {
							String strVal = clazz
										.getMethod("get" + change(fields[x].getName()),
												null).invoke(obj, null).toString();
							strBuffer.append(meta.name()+":"+strVal);	
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
						}
				}
				subBuffer.append("}");
			}
			strBuffer.append("]");
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
