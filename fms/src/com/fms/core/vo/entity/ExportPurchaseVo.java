package com.fms.core.vo.entity;

import com.fms.core.entity.Material;
import com.fms.core.entity.Scmcoc;

/**
 * 导出采购单临时类
 * 
 * @author Administrator
 * 
 */
public class ExportPurchaseVo {
	private int serialNo;
	private Scmcoc scmcoc;
	private Material material;
	private String purchaseNo;
	private Double price;
	private String note;
}
