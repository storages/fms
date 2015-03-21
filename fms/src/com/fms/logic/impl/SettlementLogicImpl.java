package com.fms.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fms.core.entity.AclUser;
import com.fms.core.entity.Settlement;
import com.fms.core.entity.Stock;
import com.fms.dao.SettlementDao;
import com.fms.logic.SettlementLogic;
import com.fms.temp.TempSettlement;
import com.fms.temp.TempStock;

public class SettlementLogicImpl implements SettlementLogic {

	protected SettlementDao settlementDao;
	
	public List<Settlement> findAllSettlement(AclUser loginUser,String searhStr) {
		return settlementDao.findAllSettlement(searhStr);
	}

	public void saveSettlement(AclUser loginUser,Settlement settlement) {
		this.settlementDao.saveSettlement(settlement);
	}

	public SettlementDao getSettlementDao() {
		return settlementDao;
	}

	public void setSettlementDao(SettlementDao settlementDao) {
		this.settlementDao = settlementDao;
	}

	public Settlement findSettById(AclUser loginUser,String id) {
		return this.settlementDao.findSettById(id);
	}

	public String findSettByCode(AclUser loginUser,String code) {
		return this.settlementDao.findSettByCode(code);
	}

	public void delSettltById(AclUser loginUser,String[] ids) {
		this.settlementDao.delSettltById(ids);
	}

	public Settlement findAllSettlementByName(AclUser loginUser,String name) {
		return this.settlementDao.findAllSettlementByName(name);
	}

	public List<?> doValidata(AclUser loginUser,List<?> dataList) {
		List<TempSettlement> tempList = new ArrayList<TempSettlement>();
		List<Settlement> settls = settlementDao.findAllSettlement(null);
		Map<String,Settlement> mapSelf = new HashMap<String,Settlement>();
		Map<String,Settlement> settCache = new HashMap<String,Settlement>();
		Map<String,Settlement> settcode = new HashMap<String,Settlement>();
		Map<String,Settlement> settName = new HashMap<String,Settlement>();
		//定义关键key
		for(Settlement settl:settls){
			String key = settl.getCode()+"/"+settl.getName();
			settCache.put(key, settl);
			settcode.put(settl.getCode(),settl);
			settName.put(settl.getName(),settl);
		}
		//验证数据
		for(Object obj:dataList){
			Settlement impSett = (Settlement) obj;
			TempSettlement temp = new TempSettlement();
			if(null==impSett.getCode() || "".equals(impSett.getCode().trim())){
				String mess = "编码不能为空; ";
				temp.setErrorInfo(temp.getErrorInfo()==null?""+mess:temp.getErrorInfo()+mess);
			}
			if(null!=impSett.getCode() || !"".equals(impSett.getCode().trim())){
				if(settcode.get(impSett.getCode())!=null){
					String mess = "编码【"+impSett.getCode()+"】已用过; ";
					temp.setErrorInfo(temp.getErrorInfo()==null?""+mess:temp.getErrorInfo()+mess);
				}
			}
			if(null==impSett.getName() || "".equals(impSett.getName().trim())){
				String mess = "结算方式名称不能为空; ";
				temp.setErrorInfo(temp.getErrorInfo()==null?""+mess:temp.getErrorInfo()+mess);
			}
			if(null==impSett.getName() || "".equals(impSett.getName().trim())){
				if(settName.get(impSett.getName())!=null){
					String mess = "结算方式名称【"+impSett.getName()+"】已存在; ";
					temp.setErrorInfo(temp.getErrorInfo()==null?""+mess:temp.getErrorInfo()+mess);
				}
			}
			String key2 = impSett.getCode()+"/"+impSett.getName();
			//验证导入数据在系统中是否重复
			if(settCache.get(key2)!=null && null!=impSett.getCode() && !"".equals(impSett.getCode().trim()) && null!=impSett.getName() && !"".equals(impSett.getName().trim())){
				setProperties(impSett, temp);
				String mess = "对应编码【"+impSett.getCode()+"】、名称【 "+impSett.getName()+"】在系统中已存在; ";
				temp.setErrorInfo(temp.getErrorInfo()==null?""+mess:temp.getErrorInfo()+mess);
			}
			//验证导入数据自身是否有重复
			
			if(mapSelf.get(key2)!=null && null!=impSett.getCode() && !"".equals(impSett.getCode().trim()) && null!=impSett.getName() && !"".equals(impSett.getName().trim())){
				String mess = "对应编码【"+impSett.getCode()+"】、名称【 "+impSett.getName()+"】在导入文件中重复; ";
				temp.setErrorInfo(temp.getErrorInfo()==null?""+mess:temp.getErrorInfo()+mess);
			}
			mapSelf.put(key2, temp);
			tempList.add(setProperties(impSett, temp));
		}
		return tempList;
	}

	/**
	 * 对象属性的拷贝【这里属性少，就不要用BeanUtils.copyProperties(arg0, arg1)方法了，效率低】
	 * @param src
	 * @param tag
	 * @return
	 */
	private TempSettlement setProperties(Settlement src,TempSettlement tag){
		if(null!=src && null!=tag){
			tag.setCode(src.getCode());
			tag.setName(src.getName());
			tag.setNote(src.getNote());
			return tag;
		}
		return null;
	}
	
	public Boolean doSaveExcelData(AclUser loginUser,List<?> dataList) {
		List<Settlement> settL = new ArrayList<Settlement>();
		//重新验证是否有错误的数据
		for(Object obj:dataList){
			TempSettlement ts = (TempSettlement)obj;
			if(null!=ts.getErrorInfo() && !"".equals(ts.getErrorInfo().trim())){
				return false;
			}else{
				Settlement s = new Settlement();
				settL.add(decProperties(ts,s));
				continue;
			}
		}
		try{
		this.settlementDao.batchSaveOrUpdate(settL);
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}

	private Settlement decProperties(TempSettlement src,Settlement tag){
		if(null!=src && null!=tag){
			tag.setCode(src.getCode());
			tag.setName(src.getName());
			tag.setNote(src.getNote());
			return tag;
		}
		return null;
	}

}
