package com.fms.excel;

import java.util.List;

public abstract class importDataFromExcel {

	/**
	 * 保存excel数据到数据库
	 */
	public abstract void doSave();

	/**
	 * 验证excel数据是否有效
	 */
	public abstract List doValidata();
	

}
