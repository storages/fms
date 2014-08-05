package com.fms.dao;

import java.util.ArrayList;
import java.util.List;

import com.fms.base.dao.BaseDao;
import com.fms.core.entity.Department;
import com.fms.core.entity.Stock;

public class StockDaoImpl extends BaseDao implements StockDao {

	public List<Stock> findAllStock(String likeStr, Integer index,Integer length) {
		String hql = "select a from Stock a where 1=1 ";
		List param = new ArrayList();
		if(null!=likeStr && !"".equals(likeStr)){
			hql+=" and a.name like '%"+likeStr+"%'";
			//param.add("'%"+ likeStr +"%'");
		}
		return this.findPageList(hql, new Object[]{},index,length);
	}

	public Integer findDataCount(String className, String name) {
		String hql = "select count(id) from "+className.trim() +" a where 1=1 ";
		List param = new ArrayList();
		if(null!=name && !"".equals(name)){
			hql+=" and a.name like '%"+name+"%'";
		}
		return this.count(hql, new Object[]{});
	}

	public Stock findStockById(String id) {
		String hql = "select a from Stock a where a.id = ? ";
		List param = new ArrayList();
		param.add(id);
		return (Stock) this.findUniqueResult(hql, param.toArray());
	}

	public void saveStock(Stock stock) {
		this.saveOrUpdate(stock);
	}

	public String findStockByCode(String code) {
		String hql = "select a.code from Stock a where a.code = ? ";
		List param = new ArrayList();
		param.add(code);
		return (String) this.findUniqueResult(hql, param.toArray());
	}

	public void delStockById(String[] ids) {
		String hql = "DELETE FROM Stock a WHERE a.id = ? ";
		List param = new ArrayList();
		param.add(ids[0]);
		for(int i = 1 ; i < ids.length ; i++){
			hql+=" or a.id = ? ";
			param.add(ids[i]);
		}
		this.batchUpdateOrDelete(hql, param.toArray());
	}

}
