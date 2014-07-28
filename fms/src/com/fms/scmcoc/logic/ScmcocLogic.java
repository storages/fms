package com.fms.scmcoc.logic;

import java.util.List;

import com.fms.core.entity.Scmcoc;

/**
 * 供应商或客户逻辑中心接口
 * @author Administrator
 *
 */
public interface ScmcocLogic {

	/**
	 * 获取所有的供应商或客户
	 * @return
	 */
	public List<Scmcoc> findAllScmcoc(Boolean isCustom);
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
}
