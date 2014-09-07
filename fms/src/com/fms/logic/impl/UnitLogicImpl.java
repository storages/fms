package com.fms.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fms.core.entity.Stock;
import com.fms.core.entity.Unit;
import com.fms.dao.UnitDao;
import com.fms.logic.UnitLogic;
import com.fms.temp.TempStock;
import com.fms.temp.TempUnit;

public class UnitLogicImpl implements UnitLogic {
	
	private UnitDao unitDao ;

	public List<Unit> findAllUnit(String likeStr, Integer index, Integer length) {
		return this.unitDao.findAllUnit(likeStr, index, length);
	}

	public Integer findDataCount(String className, String name) {
		return this.unitDao.findDataCount(className, name);
	}

	public Unit findUnitById(String id) {
		return this.unitDao.findUnitById(id);
	}

	public void saveUnit(Unit unit) {
		this.unitDao.saveUnit(unit);
	}

	public String findUnitByCode(String code) {
		return this.unitDao.findUnitByCode(code);
	}

	public void delUnitById(String[] ids) {
		this.unitDao.delUnitById(ids);
	}

	public UnitDao getUnitDao() {
		return unitDao;
	}

	public void setUnitDao(UnitDao unitDao) {
		this.unitDao = unitDao;
	}
	public List<?> doValidata(final List<?> dataList) {
		List<TempUnit> tempList = new ArrayList<TempUnit>();
		List<Unit> units = unitDao.findAllUnit(null, -1, -1);
		Map<String,Unit> mapSelf = new HashMap<String,Unit>();
		Map<String,Unit> unitCache = new HashMap<String,Unit>();
		Map<String,Unit> unitcode = new HashMap<String,Unit>();
			//定义关键key
			for(Unit unit:units){
				String key = unit.getCode()+"/"+unit.getName();
				unitCache.put(key, unit);
				unitcode.put(unit.getCode(),unit);
			}
			//验证数据
			for(Object obj:dataList){
				Unit impUnit = (Unit) obj;
				TempUnit temp = new TempUnit();
				if(null==impUnit.getCode() || "".equals(impUnit.getCode().trim())){
					String mess = "编码不能为空; ";
					temp.setErrorInfo(temp.getErrorInfo()==null?""+mess:temp.getErrorInfo()+mess);
				}
				if(null!=impUnit.getCode() || !"".equals(impUnit.getCode().trim())){
					if(unitcode.get(impUnit.getCode())!=null){
						String mess = "编码【"+impUnit.getCode()+"】已用过; ";
						temp.setErrorInfo(temp.getErrorInfo()==null?""+mess:temp.getErrorInfo()+mess);
					}
				}
				if(null==impUnit.getName() || "".equals(impUnit.getName().trim())){
					String mess = "仓库名称不能为空; ";
					temp.setErrorInfo(temp.getErrorInfo()==null?""+mess:temp.getErrorInfo()+mess);
				}
				String key2 = impUnit.getCode()+"/"+impUnit.getName();
				//验证导入数据在系统中是否重复
				if(unitCache.get(key2)!=null && null!=impUnit.getCode() && !"".equals(impUnit.getCode().trim()) && null!=impUnit.getName() && !"".equals(impUnit.getName().trim())){
					setProperties(impUnit, temp);
					String mess = "对应编码【"+impUnit.getCode()+"】、名称【 "+impUnit.getName()+"】在系统中已存在; ";
					temp.setErrorInfo(temp.getErrorInfo()==null?""+mess:temp.getErrorInfo()+mess);
				}
				//验证导入数据自身是否有重复
				
				if(mapSelf.get(key2)!=null && null!=impUnit.getCode() && !"".equals(impUnit.getCode().trim()) && null!=impUnit.getName() && !"".equals(impUnit.getName().trim())){
					String mess = "对应编码【"+impUnit.getCode()+"】、名称【 "+impUnit.getName()+"】在导入文件中重复; ";
					temp.setErrorInfo(temp.getErrorInfo()==null?""+mess:temp.getErrorInfo()+mess);
				}
				mapSelf.put(key2, temp);
				tempList.add(setProperties(impUnit, temp));
			}
			return tempList;
		}

	/**
	 * 对象属性的拷贝【这里属性少，就不要用BeanUtils.copyProperties(arg0, arg1)方法了，效率低】
	 * @param src
	 * @param tag
	 * @return
	 */
	private TempUnit setProperties(Unit src,TempUnit tag){
		if(null!=src && null!=tag){
			tag.setCode(src.getCode());
			tag.setName(src.getName());
			tag.setNote(src.getNote());
			return tag;
		}
		return null;
	}
	
	private Unit decProperties(TempUnit src,Unit tag){
		if(null!=src && null!=tag){
			tag.setCode(src.getCode());
			tag.setName(src.getName());
			tag.setNote(src.getNote());
			return tag;
		}
		return null;
	}

	public Boolean doSaveExcelData(List<?> dataList) {
		List<Unit> unitL = new ArrayList<Unit>();
		//重新验证是否有错误的数据
		for(Object obj:dataList){
			TempUnit tu = (TempUnit)obj;
			if(null!=tu.getErrorInfo() && !"".equals(tu.getErrorInfo().trim())){
				return false;
			}else{
				Unit u = new Unit();
				unitL.add(decProperties(tu,u));
				continue;
			}
		}
		try{
			this.unitDao.batchSaveOrUpdate(unitL);
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}
}
