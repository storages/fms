package com.fms.commons;

/**
 * 申请单状态(appStatus)   0、未申请  1、待审批  2、审批通过   3、审批不通过
 * @author Administrator
 * 
 */
public class AppBillStatus {
	/**
	 * 未申请
	 */
	public static final String UNAPPLY = "0";
	/**
	 * 待审批
	 */
	public static final String APPROVALING = "1";
	/**
	 * 审批通过
	 */
	public static final String APPROVED = "2";
	/**
	 * 审批不通过
	 */
	public static final String APPROVEDNOT = "3";
	
	/**
	 * 把值翻译成中文
	 * @param value
	 * @return
	 */
	public static String descValue(String value){
		if(null!=value && !"".equals(value.trim())){
			if(value.trim().equals(AppBillStatus.UNAPPLY)){
				return "未申请";
			}else if(value.trim().equals(AppBillStatus.APPROVALING)){
				return "待审批";
			}else if(value.trim().equals(AppBillStatus.APPROVED)){
				return "审批通过";
			}else if(value.trim().equals(AppBillStatus.APPROVEDNOT)){
				return "审批不通过";
			}
		}
		return null;
	}
	
	/**
	 * 把中文解析成值
	 * @param value
	 * @return
	 */
	public static String parseValue(String value){
		if(null!=value && !"".equals(value.trim())){
			if(value.trim().equals("未申请")){
				return AppBillStatus.UNAPPLY;
			}else if(value.trim().equals("待审批")){
				return AppBillStatus.APPROVALING;
			}else if(value.trim().equals("审批通过")){
				return AppBillStatus.APPROVED;
			}else if(value.trim().equals("审批不通过")){
				return AppBillStatus.APPROVEDNOT;
			}
		}
		return null;
	}
	
}
