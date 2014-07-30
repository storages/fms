package com.fms.scmcoc.logic;

import java.util.List;

import com.fms.core.entity.Scmcoc;

/**
 * ��Ӧ�̻�ͻ��߼����Ľӿ�
 * @author Administrator
 *
 */
public interface ScmcocLogic {

	/**
	 * ��ȡ���еĹ�Ӧ�̻�ͻ�
	 * @return
	 */
	public List<Scmcoc> findAllScmcoc(Boolean isCustom);
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
}