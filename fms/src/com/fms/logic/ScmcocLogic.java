package com.fms.logic;

import java.util.List;

import com.fms.core.entity.Scmcoc;
import com.fms.temp.TempScmcoc;

/**
 * 供应商或客户逻辑中心接口
 * @author Administrator
 *
 */
public interface ScmcocLogic {

	/**
	 * 获取所有的供应商或客户[分页]
	 * @return
	 */
	public List<Scmcoc> findAllScmcoc(Boolean isCustom,String likeStr,Integer index,Integer length);
	/**
	 * 根据id查询供应商或客户
	 * @param id
	 * @return
	 */
	public Scmcoc findScmcocById(String id);
	/**
	 * 保存单个供应商或客户
	 * @param scmcoc
	 */
	public void saveScmcoc(Scmcoc scmcoc);
	
	/**
	 * 批量保存供应商或客户
	 * @param data
	 */
	public void betchSaveScmcoc(List<Scmcoc> data);
	/**
	 * 根据id删除供应商或客户
	 * @param id
	 */
	public void deleteScmcocById(String id);
	
	/**
	 * 根据编码查询供应商或客户
	 * @param code
	 * @return
	 */
	public Scmcoc findScmcocByCode(String code);
	
	/**
	 * 批量删除供应商或客户
	 * @param data
	 */
	public void delete(List<String> ids);
	
	/**
	 * 查找数据总条数
	 * @param className
	 * @param isCustom
	 * @param name
	 * @return
	 */
	public Integer findDataCount(String className,Boolean isCustom,String name);
	
	/**
	 * 验证数据
	 * @param data
	 * @return
	 */
	public List<TempScmcoc> doValidata(List<Scmcoc> data);
}
