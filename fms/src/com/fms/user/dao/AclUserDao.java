package com.fms.user.dao;

import java.util.List;

import com.fms.core.entity.AclUser;

public interface AclUserDao {

	/**
	 * �û���¼���û�û�б����õĲſ��Ե�¼��
	 * 
	 * @param loginName
	 * @param password
	 * @return
	 */
	public abstract AclUser loginAclUser(String userName, String password);

	/**
	 * �����û�
	 * @param aclUser
	 */
	
	public abstract void saveAclUser(AclUser aclUser);

	/**
	 * ��ȡ�����û�
	 * @return
	 */
	public List<AclUser> findAllUser(String userflag);
	
	/**
	 * ɾ���û�
	 * @param ids
	 */
	public void deleteAclUser(String [] ids);
	
	/**
	 * �����û�id����ѯ�û�
	 * @param id
	 * @return
	 */
	public AclUser findUserById(String id);
	
}