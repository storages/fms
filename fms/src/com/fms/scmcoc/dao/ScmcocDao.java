package com.fms.scmcoc.dao;

import java.util.List;

import com.fms.core.entity.Scmcoc;

public interface ScmcocDao {
	/**
	 * ��ȡ���еĹ�Ӧ�̻�ͻ�[��ҳ]
	 * @return
	 */
	public List<Scmcoc> findAllScmcoc(Boolean isCustom,Integer index,Integer length);
	/**
	 * ����id��ѯ��Ӧ�̻�ͻ�
	 * @param id
	 * @return
	 */
	public Scmcoc findScmcocById(String id);
	/**
	 * ���浥����Ӧ�̻�ͻ�
	 * @param scmcoc
	 */
	public void saveScmcoc(Scmcoc scmcoc);
	
	/**
	 * �������湩Ӧ�̻�ͻ�
	 * @param data
	 */
	public void betchSaveScmcoc(List<Scmcoc> data);
	/**
	 * ����idɾ����Ӧ�̻�ͻ�
	 * @param id
	 */
	public void deleteScmcocById(String id);
	
	/**
	 * ���ݱ����ѯ��Ӧ�̻�ͻ�
	 * @param code
	 * @return
	 */
	public Scmcoc findScmcocByCode(String code);
	
	/**
	 * ����ɾ����Ӧ�̻�ͻ�
	 * @param data
	 */
	public void delete(List<String> ids);
}

