package com.fms.core.entity;

import com.fms.base.entity.BaseEntity;

/**
 * 工厂BOM管理【成品资料】
 * 
 * @author Administrator
 * 
 */

public class BomExg extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 物料清单对象
	 */
	private Material material;

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

}
