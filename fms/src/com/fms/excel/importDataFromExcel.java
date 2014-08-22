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
	 * List装的类型是自己创建的一个临时类，当你验证哪个类的数据时，就继承哪 个类，再加一个属性errorInfo来暂存错误信息
	 */
	public abstract List<?> doValidata();
	

}
