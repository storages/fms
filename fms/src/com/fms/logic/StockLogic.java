package com.fms.logic;

import java.util.List;

import com.fms.core.entity.Stock;

public interface StockLogic  {

	/**
	 * 查询所有的仓库信息[分页]
	 * 
	 * @param likeStr
	 * @param index
	 * @param length
	 * @return
	 */
	public List<Stock> findAllStock(String likeStr, Integer index,
			Integer length);

	/**
	 * 查询数据的条数
	 * 
	 * @param className
	 * @param name
	 * @return
	 */
	public Integer findDataCount(String className, String name);

	/**
	 * 根据id查询仓库
	 * 
	 * @param id
	 * @return
	 */
	public Stock findStockById(String id);

	/**
	 * 保存单个实体
	 * 
	 * @param stock
	 */
	public void saveStock(Stock stock);

	/**
	 * 查找仓库编码是否重复
	 * 
	 * @param code
	 * @return
	 */
	public String findStockByCode(String code);

	/**
	 * 根据Id来删除仓库信息
	 */
	public void delStockById(String[] ids);
	
	/**
	 * 验证导入Excel数据
	 */
	public List<?> doValidata(List<?> dataList);
	
	/**
	 * 保存Excel数据到数据库
	 */
	public Boolean doSaveExcelData(List<?> dataList);
	
}
