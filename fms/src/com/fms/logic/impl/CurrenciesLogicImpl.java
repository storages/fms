package com.fms.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fms.core.entity.Currencies;
import com.fms.core.entity.Settlement;
import com.fms.dao.CurrenciesDao;
import com.fms.logic.CurrenciesLogic;
import com.fms.temp.TempCurrencies;
import com.fms.temp.TempSettlement;


public class CurrenciesLogicImpl implements CurrenciesLogic {

	private CurrenciesDao currenciesDao;
	
	 

	public CurrenciesDao getCurrenciesDao() {
		return currenciesDao;
	}

	public void setCurrenciesDao(CurrenciesDao currenciesDao) {
		this.currenciesDao = currenciesDao;
	}

	public List<Currencies> findAllCurrencies(String likeStr, Integer index,Integer length) {
		List<Currencies> list = currenciesDao.findAllCurrencies(likeStr, index, length);
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

	public void deleteCurrenciesById(String [] ids) {
		currenciesDao.deleteCurrenciesById(ids);
	}

	public String findCurrenciesByCode(String code) {
		return currenciesDao.findCurrenciesByCode(code);
	}

	public Integer findDataCount(String className, String name) {
		return this.currenciesDao.findDataCount(className,name);
	}

	public void delete(List<String> ids) {
		this.currenciesDao.delete(ids);
	}

	public List<TempCurrencies> doValidata(List<Currencies> dataList) {
		List<TempCurrencies> tempList = new ArrayList<TempCurrencies>();
		List<Currencies> settls = currenciesDao.findAllCurrencies();
		Map<String,Currencies> mapSelf = new HashMap<String,Currencies>();
		Map<String,Currencies> settCache = new HashMap<String,Currencies>();
		Map<String,Currencies> settcode = new HashMap<String,Currencies>();
		//定义关键key
		for(Currencies settl:settls){
			String key = settl.getCode()+"/"+settl.getName();
			settCache.put(key, settl);
			settcode.put(settl.getCode(),settl);
		}
		//验证数据
		for(Object obj:dataList){
			Currencies impSett = (Currencies) obj;
			TempCurrencies temp = new TempCurrencies();
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
				String mess = "单位名不能为空; ";
				temp.setErrorInfo(temp.getErrorInfo()==null?""+mess:temp.getErrorInfo()+mess);
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
	private TempCurrencies setProperties(Currencies src,TempCurrencies tag){
		if(null!=src && null!=tag){
			tag.setCode(src.getCode());
			tag.setName(src.getName());
			tag.setNote(src.getNote());
			return tag;
		}
		return null;
	}
}