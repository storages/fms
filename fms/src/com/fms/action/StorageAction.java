package com.fms.action;

import com.fms.base.action.BaseAction;
import com.fms.logic.StorageLogic;
/**
 * 进出库
 * @author Administrator
 *
 */
public class StorageAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected StorageLogic storageLogic;

	/**
	 * 获取入库列表数据
	 * @return
	 */
	public String findAllInStorage(){
		return this.SUCCESS;
	}
	
	/**
	 * 编辑
	 * @return
	 */
	public String editStorage(){
		return "edit";
	}
	
	
	
	
	
	
	
	
	
	public StorageLogic getStorageLogic() {
		return storageLogic;
	}

	public void setStorageLogic(StorageLogic storageLogic) {
		this.storageLogic = storageLogic;
	}
	

}
