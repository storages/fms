package com.fms.logic.impl;

import java.util.Date;
import java.util.List;

import com.fms.core.entity.AclUser;
import com.fms.dao.StorageDao;
import com.fms.logic.StorageLogic;

public class StorageLogicImpl implements StorageLogic {
	protected StorageDao storageDao;

	public StorageDao getStorageDao() {
		return storageDao;
	}

	public void setStorageDao(StorageDao storageDao) {
		this.storageDao = storageDao;
	}

	public List findStorage(AclUser user, String entityName, Date startDate, Date endDate, String scmcocName, String hsName, String flag, int index, int length) {
		return this.storageDao.findStorage(entityName, startDate, endDate, scmcocName, hsName, flag, index, length);
	}

	public Integer findDataCount(String entityName, Date startDate, Date endDate, String scmcocName, String hsName, String flag) {
		try {
			return this.storageDao.findDataCount(entityName, startDate, endDate, scmcocName, hsName, flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Integer findMaxSerialNo(AclUser user, String entityName, String imgExgFlag) {
		return this.storageDao.findMaxSerialNo(entityName, imgExgFlag);
	}
}
