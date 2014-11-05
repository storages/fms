package com.fms.logic.impl;

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
	
}
