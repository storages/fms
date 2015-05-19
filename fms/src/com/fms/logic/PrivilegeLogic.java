package com.fms.logic;

import java.util.List;

import com.fms.core.entity.AclUser;
import com.fms.core.entity.Privilege;

public interface PrivilegeLogic {
	public void savePrivilege(AclUser loginUser,Privilege modal);
	public void updatePrivilege(AclUser loginUser, Privilege modal);
	public int countListPrivilege(AclUser loginUser, String str);
	public void deletePrivilege(AclUser loginUser, String id);
	public Privilege getPrivilegeById(AclUser loginUser, String id);
	public void deletePrivilege(AclUser loginUser, String[] ids);
	public List<Privilege> findAllPrivilege(AclUser loginUser, String likeStr, Integer index, Integer length);

}
