package com.fms.logic.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fms.core.entity.Stock;
import com.fms.dao.StockDao;
import com.fms.excel.importDataFromExcel;
import com.fms.logic.StockLogic;
import com.fms.temp.TempStock;
import com.fms.utils.ExcelUtil;
import com.sun.org.apache.commons.beanutils.BeanUtils;

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

	public void importData(final List<?> dataList) {
		new importDataFromExcel(){
			List<TempStock> tempList = new ArrayList<TempStock>();
			List<Stock> stocks = stockDao.findAllStock(null, -1, -1);
			Map<String,Stock> map = new HashMap<String,Stock>();
			
			Map<String,Stock> stockCache = new HashMap<String,Stock>();
			
			
			@Override
			public void doSave() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public List<?> doValidata() {
				//定义关键key
				for(Stock stock:stocks){
					String key = stock.getCode()+"/"+stock.getName();
					stockCache.put(key, stock);
				}
				//验证数据
				for(Object obj:dataList){
					Stock impStock = (Stock) obj;
					TempStock temp = new TempStock();
					if(null==impStock.getCode() || "".equals(impStock.getCode().trim())){
						String mess = "编码不能为空; ";
						temp.setErrorInfo(temp.getErrorInfo()+mess);
					}
					if(null==impStock.getName() || "".equals(impStock.getName().trim())){
						String mess = "仓库名称不能为空; ";
						temp.setErrorInfo(temp.getErrorInfo()+mess);
					}
					String key2 = impStock.getCode()+"/"+impStock.getName();
					//验证导入数据在系统中是否重复
					if(stockCache.get(key2)!=null && null!=impStock.getCode() && !"".equals(impStock.getCode().trim()) && null!=impStock.getName() && !"".equals(impStock.getName().trim())){
						setProperties(impStock, temp);
						String mess = "对应编码【"+impStock.getCode()+"】、名称【 "+impStock.getName()+"】在系统中已存在; ";
						temp.setErrorInfo(temp.getErrorInfo()+mess);
					}
					//验证导入数据自身是否有重复
					Map<String,Stock> map = new HashMap<String,Stock>();
					if(map.get(key2)!=null && null!=impStock.getCode() && !"".equals(impStock.getCode().trim()) && null!=impStock.getName() && !"".equals(impStock.getName().trim())){
						String mess = "对应编码【"+impStock.getCode()+"】、名称【 "+impStock.getName()+"】在导入文件中重复; ";
						temp.setErrorInfo(temp.getErrorInfo()+mess);
					}
					tempList.add(setProperties(impStock, temp));
				}
				// TODO Auto-generated method stub
				return tempList;
			}
			
		};
		
	}


	/**
	 * 对象属性的拷贝【这里属性少，就不要用BeanUtils.copyProperties(arg0, arg1)方法了，效率低】
	 * @param src
	 * @param tag
	 * @return
	 */
	private TempStock setProperties(Stock src,TempStock tag){
		if(null!=src && null!=tag){
			tag.setCode(src.getCode());
			tag.setName(src.getName());
			tag.setNote(src.getNote());
			return tag;
		}
		return null;
	}
	
	private void getData(File file){
		try {
			 String[][] datas = ExcelUtil.readExcel(file, 0);
			 
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
