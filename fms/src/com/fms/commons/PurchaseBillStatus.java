package com.fms.commons;
/**
 * 采购单状态
 * @author Administrator
 *
 */
public class PurchaseBillStatus {
	/**
	 * 未生效
	 */
	public static final String UNEFFECT = "0";
	/**
	 * 已生效
	 */
	public static final String EFFECTED = "1";
	
	/**
	 * 把值翻译成中文
	 * @param value
	 * @return
	 */
	public static String descValue(String value){
		if(null!=value && !"".equals(value.trim())){
			if(value.trim().equals(PurchaseBillStatus.UNEFFECT)){
				return "未生效";
			}else if(value.trim().equals(PurchaseBillStatus.EFFECTED)){
				return "已生效";
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
			if(value.trim().equals("未生效")){
				return PurchaseBillStatus.UNEFFECT;
			}else if(value.trim().equals("已生效")){
				return PurchaseBillStatus.EFFECTED;
			}
		}
		return null;
	}

}

