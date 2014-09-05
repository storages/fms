package com.fms.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fms.core.entity.Currencies;
import com.fms.core.entity.Settlement;
import com.fms.dao.CurrenciesDao;
import com.fms.logic.CurrenciesLogic;
import com.fms.temp.TempCurr;


public class CurrenciesLogicImpl implements CurrenciesLogic {

	private CurrenciesDao currenciesDao;

	public CurrenciesDao getCurrenciesDao() {
		return currenciesDao;
	}

	public void setCurrenciesDao(CurrenciesDao currenciesDao) {
		this.currenciesDao = currenciesDao;
	}

	public List<Currencies> findAllCurrencies(String likeStr, Integer index,
			Integer length) {
		List<Currencies> list = currenciesDao.findAllCurrencies(likeStr, index,
				length);
		return list;
	}

	public Currencies findCurrenciesById(String id) {
		return currenciesDao.findCurrenciesById(id);
	}

	public void saveCurrencies(Currencies currencies) {
		currenciesDao.saveCurrencies(currencies);
	}

	public void betchSaveCurrencies(List<Currencies> data) {
		currenciesDao.betchSaveCurrencies(data);
	}

	public void deleteCurrenciesById(String[] ids) {
		currenciesDao.deleteCurrenciesById(ids);
	}

	public String findCurrenciesByCode(String code) {
		return currenciesDao.findCurrenciesByCode(code);
	}

	public Integer findDataCount(String className, String name) {
		return this.currenciesDao.findDataCount(className, name);
	}

	public void delete(List<String> ids) {
		this.currenciesDao.delete(ids);
	}

	public List<?> doValidata(List<?> dataList) {
		List<TempCurr> tempList = new ArrayList<TempCurr>();
		List<Currencies> currList = currenciesDao.findAllCurrencies(null, -1,-1);
		Map<String, Currencies> mapSelf = new HashMap<String, Currencies>();
		Map<String, Currencies> settCache = new HashMap<String, Currencies>();
		Map<String, Currencies> settcode = new HashMap<String, Currencies>();
		// 定义关键key
		for (Currencies cu : currList) {
			String key = cu.getCode() + "/" + cu.getName();
			settCache.put(key, cu);
			settcode.put(cu.getCode(), cu);
		}
		//验证数据
		for(Object obj:dataList){
			Currencies impCurr = (Currencies) obj;
			TempCurr temp = new TempCurr();
			if(null==impCurr.getCode() || "".equals(impCurr.getCode().trim())){
				String mess = "编码不能为空; ";
				temp.setErrorInfo(temp.getErrorInfo()==null?""+mess:temp.getErrorInfo()+mess);
			}
			if(null!=impCurr.getCode() || !"".equals(impCurr.getCode().trim())){
				if(settcode.get(impCurr.getCode())!=null){
					String mess = "编码【"+impCurr.getCode()+"】已用过; ";
					temp.setErrorInfo(temp.getErrorInfo()==null?""+mess:temp.getErrorInfo()+mess);
				}
			}
			if(null==impCurr.getName() || "".equals(impCurr.getName().trim())){
				String mess = "货币名称不能为空; ";
				temp.setErrorInfo(temp.getErrorInfo()==null?""+mess:temp.getErrorInfo()+mess);
			}
			String key2 = impCurr.getCode()+"/"+impCurr.getName();
			//验证导入数据在系统中是否重复
			if(settCache.get(key2)!=null && null!=impCurr.getCode() && !"".equals(impCurr.getCode().trim()) && null!=impCurr.getName() && !"".equals(impCurr.getName().trim())){
				setProperties(impCurr, temp);
				String mess = "对应编码【"+impCurr.getCode()+"】、名称【 "+impCurr.getName()+"】在系统中已存在; ";
				temp.setErrorInfo(temp.getErrorInfo()==null?""+mess:temp.getErrorInfo()+mess);
			}
			//验证导入数据自身是否有重复
			
			if(mapSelf.get(key2)!=null && null!=impCurr.getCode() && !"".equals(impCurr.getCode().trim()) && null!=impCurr.getName() && !"".equals(impCurr.getName().trim())){
				String mess = "对应编码【"+impCurr.getCode()+"】、名称【 "+impCurr.getName()+"】在导入文件中重复; ";
				temp.setErrorInfo(temp.getErrorInfo()==null?""+mess:temp.getErrorInfo()+mess);
			}
			mapSelf.put(key2, temp);
			tempList.add(setProperties(impCurr, temp));
		}
		return tempList;
	}

	/**
	 * 对象属性的拷贝【这里属性少，就不要用BeanUtils.copyProperties(arg0, arg1)方法了，效率低】
	 * @param src
	 * @param tag
	 * @return
	 */
	private TempCurr setProperties(Currencies src,TempCurr tag){
		if(null!=src && null!=tag){
			tag.setCode(src.getCode());
			tag.setName(src.getName());
			tag.setNote(src.getNote());
			return tag;
		}
		return null;
	}
	
	public Boolean doSaveExcelData(List<?> dataList) {
		List<Currencies> currL = new ArrayList<Currencies>();
		//重新验证是否有错误的数据
		for(Object obj:dataList){
			TempCurr tc = (TempCurr)obj;
			if(null!=tc.getErrorInfo() && !"".equals(tc.getErrorInfo().trim())){
				return false;
			}else{
				Currencies c = new Currencies();
				currL.add(decProperties(tc,c));
				continue;
			}
		}
		try{
			this.currenciesDao.batchSaveOrUpdate(currL);
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}

	private Currencies decProperties(TempCurr src,Currencies tag){
		if(null!=src && null!=tag){
			tag.setCode(src.getCode());
			tag.setName(src.getName());
			tag.setNote(src.getNote());
			return tag;
		}
		return null;
	}
	
}