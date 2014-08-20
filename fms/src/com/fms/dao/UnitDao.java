package com.fms.dao;

import java.util.List;

import com.fms.core.entity.Unit;


public interface UnitDao {
	/**
	 * 查询所有的计量单位信息[分页]
	 * @param likeStr
	 * @param index
	 * @param length
	 * @return
	 */
	public List<Unit> findAllUnit(String likeStr,Integer index,Integer length);
	
	/**
	 * 查询数据的条数
	 * @param className
	 * @param name
	 * @return
	 */
	public Integer findDataCount(String className,String name);
	
	/**
	 * 根据id查询计量单位
	 * @param id
	 * @return
	 */
	public Unit findUnitById(String id);
	
	/**
	 * 保存单个实体
	 * @param Unit
	 */
	public void saveUnit(Unit unit);
	
	/**
	 * 查找计量单位编码是否重复
	 * @param code
	 * @return
	 */
	public String findUnitByCode(String code);
	
	/**
	 * 根据Id来删除计量单位信息
	 */
	public void delUnitById(String [] ids);

}
