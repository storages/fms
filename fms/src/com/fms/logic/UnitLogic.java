package com.fms.logic;

import java.util.List;

import com.fms.core.entity.AclUser;
import com.fms.core.entity.Unit;

public interface UnitLogic {
	/**
	 * 查询所有的计量单位信息[分页]
	 * 
	 * @param likeStr
	 * @param index
	 * @param length
	 * @return
	 */
	public List<Unit> findAllUnit(AclUser loginUser,String likeStr, Integer index, Integer length);

	/**
	 * 查询数据的条数
	 * 
	 * @param className
	 * @param name
	 * @return
	 */
	public Integer findDataCount(AclUser loginUser,String className, String name);

	/**
	 * 根据id查询计量单位
	 * 
	 * @param id
	 * @return
	 */
	public Unit findUnitById(AclUser loginUser,String id);

	/**
	 * 保存单个实体
	 * 
	 * @param Unit
	 */
	public void saveUnit(AclUser loginUser,Unit unit);

	/**
	 * 查找计量单位编码是否重复
	 * 
	 * @param code
	 * @return
	 */
	public String findUnitByCode(AclUser loginUser,String code);

	/**
	 * 根据Id来删除计量单位信息
	 */
	public void delUnitById(AclUser loginUser,String[] ids);
	
	/**
	 * 验证导入Excel数据
	 */
	public List<?> doValidata(AclUser loginUser,List<?> dataList);
	
	/**
	 * 保存Excel数据到数据库
	 */
	public Boolean doSaveExcelData(AclUser loginUser,List<?> dataList);
}
