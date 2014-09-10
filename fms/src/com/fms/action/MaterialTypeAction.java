package com.fms.action;

import java.util.List;

import com.fms.base.action.BaseAction;
import com.fms.core.entity.MaterialType;
import com.fms.logic.MaterialTypeLogic;

/**
 * 物料类型
 * @author Administrator
 *
 */
public class MaterialTypeAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MaterialTypeLogic logic;
	
	public String findAllMaterialType(){
		try{
		List<MaterialType> typeList = this.logic.findAllType();
			this.request.put("typeList", typeList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return this.SUCCESS;
	}
	
	public MaterialTypeLogic getLogic() {
		return logic;
	}
	public void setLogic(MaterialTypeLogic logic) {
		this.logic = logic;
	}

	
}
