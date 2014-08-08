package com.fms.logic.impl;

import java.util.List;

import com.fms.core.entity.Currencies;
import com.fms.core.entity.Scmcoc;
import com.fms.dao.CurrenciesDao;
import com.fms.dao.ScmcocDao;
import com.fms.logic.CurrenciesLogic;


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
		return currenciesDao.findAllCurrencies(likeStr, index, length);
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

	public void deleteCurrenciesById(String id) {
		currenciesDao.deleteCurrenciesById(id);
	}

	public Currencies findCurrenciesByCode(String code) {
		return currenciesDao.findCurrenciesByCode(code);
	}

	public Integer findDataCount(String className, String name) {
		return this.currenciesDao.findDataCount(className,name);
	}

	public void delete(List<String> ids) {
		this.currenciesDao.delete(ids);
	}
	
}