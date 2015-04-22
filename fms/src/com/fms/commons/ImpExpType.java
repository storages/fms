package com.fms.commons;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 进出库类型
 * 
 * @author Administrator
 * 
 */
public class ImpExpType {
	/**
	 * 原料采购入库
	 */
	public static int PRUCHSE_IN = 1;

	/**
	 * 原料退换入库
	 */
	public static int IMG_BACK_IN = 2;

	/**
	 * 成品退换入库
	 */
	public static int EXG_BACK_IN = 3;
	/**
	 * 原料其它入库
	 */
	public static int IMG_OTHER_IN = 4;
	/**
	 * 成品其它入库
	 */
	public static int EXG_OTHER_IN = 5;
	/**
	 * 原料退换出库
	 */
	public static int IMG_BACK_OUT = 6;
	/**
	 * 成品退换出库
	 */
	public static int EXG_BACK_OUT = 7;
	/**
	 * 原料退货出库
	 */
	public static int IMG_RETURN_OUT = 8;
	/**
	 * 原料其它出库
	 */
	public static int IMG_OTHER_OUT = 9;
	/**
	 * 成品其它出库
	 */
	public static int EXG_OTHER_OUT = 10;
	/**
	 * 成品入库
	 */
	public static int EXG_IN = 11;
	/**
	 * 原料生产领用
	 */
	public static int MADE_GET = 12;
	/**
	 * 成品发货出库
	 */
	public static int DELIVERY_OUT = 13;

	/**
	 * 把值转换成中文类型
	 * 
	 * @param value
	 * @return
	 */
	public static String getImpExpType(int value) {
		String valType = null;
		switch (value) {
		case 1:
			valType = "原料采购入库";
			break;
		case 2:
			valType = "原料退换入库";
			break;
		case 3:
			valType = "成品退换入库";
			break;
		case 4:
			valType = "原料其它入库";
			break;
		case 5:
			valType = "成品其它入库";
			break;
		case 6:
			valType = " 原料退换出库";
			break;
		case 7:
			valType = "成品退换出库";
			break;
		case 8:
			valType = "原料退货出库";
			break;
		case 9:
			valType = "原料其它出库";
			break;
		case 10:
			valType = "成品其它出库";
			break;
		case 11:
			valType = "成品入库";
			break;
		case 12:
			valType = "原料生产领用";
			break;
		case 13:
			valType = "成品发货出库";
			break;
		}
		return valType;
	}

	/**
	 * 把中文字转换为数字
	 * 
	 * @param value
	 * @return
	 */
	public static Integer parseVal(String value) {
		Integer val = null;
		if (StringUtils.isNotBlank(value)) {
			if (ImpExpType.getImpExpType(1).equals(value)) {
				val = 1;
			} else if (ImpExpType.getImpExpType(2).equals(value)) {
				val = 2;
			} else if (ImpExpType.getImpExpType(3).equals(value)) {
				val = 3;
			} else if (ImpExpType.getImpExpType(4).equals(value)) {
				val = 4;
			} else if (ImpExpType.getImpExpType(5).equals(value)) {
				val = 5;
			} else if (ImpExpType.getImpExpType(6).equals(value)) {
				val = 6;
			} else if (ImpExpType.getImpExpType(7).equals(value)) {
				val = 7;
			} else if (ImpExpType.getImpExpType(8).equals(value)) {
				val = 8;
			} else if (ImpExpType.getImpExpType(9).equals(value)) {
				val = 9;
			} else if (ImpExpType.getImpExpType(10).equals(value)) {
				val = 10;
			} else if (ImpExpType.getImpExpType(11).equals(value)) {
				val = 11;
			} else if (ImpExpType.getImpExpType(12).equals(value)) {
				val = 12;
			} else if (ImpExpType.getImpExpType(13).equals(value)) {
				val = 13;
			}
		}
		return val;
	}

	/**
	 * 根据物料标志返回对应的单据类型
	 * 
	 * @param materialType
	 * @return
	 */
	public static List<Object[]> getImgTypeByMaterialType(String materialType) {
		List<Object[]> typeList = new ArrayList<Object[]>();
		if (StringUtils.isNotBlank(materialType)) {
			if (ImgExgFlag.EXG.equals(materialType)) {
				typeList.add(new Object[] { parseVal(getImpExpType(3)), getImpExpType(3) });
				typeList.add(new Object[] { parseVal(getImpExpType(5)), getImpExpType(5) });
				typeList.add(new Object[] { parseVal(getImpExpType(7)), getImpExpType(7) });
				typeList.add(new Object[] { parseVal(getImpExpType(10)), getImpExpType(10) });
				typeList.add(new Object[] { parseVal(getImpExpType(11)), getImpExpType(11) });
				typeList.add(new Object[] { parseVal(getImpExpType(13)), getImpExpType(13) });
			} else if (ImgExgFlag.IMG.equals(materialType)) {
				typeList.add(new Object[] { parseVal(getImpExpType(1)), getImpExpType(1) });
				typeList.add(new Object[] { parseVal(getImpExpType(2)), getImpExpType(2) });
				typeList.add(new Object[] { parseVal(getImpExpType(4)), getImpExpType(4) });
				typeList.add(new Object[] { parseVal(getImpExpType(6)), getImpExpType(6) });
				typeList.add(new Object[] { parseVal(getImpExpType(8)), getImpExpType(8) });
				typeList.add(new Object[] { parseVal(getImpExpType(9)), getImpExpType(9) });
				typeList.add(new Object[] { parseVal(getImpExpType(12)), getImpExpType(12) });
			}
		}

		return typeList;
	}
}
