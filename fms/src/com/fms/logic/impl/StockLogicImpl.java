package com.fms.logic.impl;

import java.util.List;

import com.fms.core.entity.Stock;
import com.fms.dao.StockDao;
import com.fms.logic.StockLogic;

public class StockLogicImpl implements StockLogic {

	protected StockDao stockDao;
	
	
	public StockDao getStockDao() {
		return stockDao;
	}

	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}

	public List<Stock> findAllStock(String likeStr, Integer index,Integer length) {
		return this.stockDao.findAllStock(likeStr, index, length);
	}

	public Integer findDataCount(String className, String name) {
		return this.stockDao.findDataCount(className, name);
	}

	public Stock findStockById(String id) {
		return this.stockDao.findStockById(id);
	}

	public void saveStock(Stock stock) {
		this.stockDao.saveStock(stock);
	}

	public String findStockByCode(String code) {
		return this.stockDao.findStockByCode(code);
	}

	public void delStockById(String[] ids) {
		this.stockDao.delStockById(ids);
	}

}
