package com.fms.excel;

import java.util.List;

/**
 * 从Excel中导入数据到系统
 * @author guodacai 2014-8-22 下午3:32:36
 *
 */
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
