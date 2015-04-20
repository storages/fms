package com.fms.logic;

import java.util.List;

import com.fms.core.entity.AclUser;
import com.fms.core.entity.OperateLogs;

public interface OperateLogsLogic {
	
	public List<OperateLogs> findAllEmpl(AclUser loginUser,String likeStr,Integer index,Integer length);

	public OperateLogs getEmplById(AclUser loginUser,String id);
	
	public int countListEmpl(AclUser loginUser,String str);
}
