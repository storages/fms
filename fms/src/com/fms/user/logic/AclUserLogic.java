package com.fms.user.logic;

import java.util.List;

import com.fms.core.entity.AclUser;


/**
 * �û�   �߼�������
 * @author Administrator
 *
 */
public interface AclUserLogic {

	/**
	 * ��¼
	 * @param loginName
	 * @param password
	 * @return
	 */
	public AclUser loginAclUser(String userName, String password);
	
	/**
	 * �����û�
	 * @param aclUser
	 */
	public void saveAclUser(AclUser aclUser);
	
	/**
	 * ��ȡ�����û�
	 * @return
	 */
	public List<AclUser> findAllUser();
	
	/**
	 * ɾ���û�
	 * @param ids
	 */
	public void deleteAclUser(String [] ids);
}
