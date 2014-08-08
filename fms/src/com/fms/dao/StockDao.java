package com.fms.dao;

import java.util.List;

import com.fms.base.dao.BaseDao;
import com.fms.core.entity.Stock;

public interface StockDao  extends BaseDao{

	/**
	 * 查询所有的仓库信息[分页]
	 * @param likeStr
	 * @param index
	 * @param length
	 * @return
	 */
	public List<Stock> findAllStock(String likeStr,Integer index,Integer length);
	
	/**
	 * 查询数据的条数
	 * @param className
	 * @param name
	 * @return
	 */
	public Integer findDataCount(String className,String name);
	
	/**
	 * 根据id查询仓库
	 * @param id
	 * @return
	 */
	public Stock findStockById(String id);
	
	/**
	 * 保存单个实体
	 * @param stock
	 */
	public void saveStock(Stock stock);
	
	/**
	 * 查找仓库编码是否重复
	 * @param code
	 * @return
	 */
	public String findStockByCode(String code);
	
	/**
	 * 根据Id来删除仓库信息
	 */
	public void delStockById(String [] ids);
}
