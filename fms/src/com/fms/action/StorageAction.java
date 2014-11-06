package com.fms.action;

import java.util.List;

import com.fms.base.action.BaseAction;
import com.fms.commons.ImpExpFlag;
import com.fms.core.entity.InStorage;
import com.fms.core.entity.MaterialType;
import com.fms.core.entity.OutStorage;
import com.fms.core.entity.Scmcoc;
import com.fms.core.entity.Unit;
import com.fms.logic.MaterialTypeLogic;
import com.fms.logic.ScmcocLogic;
import com.fms.logic.StorageLogic;
import com.fms.logic.UnitLogic;
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
	
	protected StorageLogic storageLogic;//进出库逻辑
	protected MaterialTypeLogic logic;//物料类型逻辑
	protected UnitLogic unitLogic;//计量单位逻辑
	protected ScmcocLogic scmcocLogic;//客户供应商逻辑
	
	protected InStorage inStorage;//入库
	protected OutStorage outStorage;//出库
	
	protected String impExpFlag;//进出标志
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
		try{
		List<MaterialType> types = this.logic.findAllType(null);//加载物料类型下拉框
		List<Unit> units = this.unitLogic.findAllUnit(null, null, null);//加载计量单位下拉框
		List<Scmcoc> scmCocs = this.scmcocLogic.findAllScmcoc(impExpFlag.equals(ImpExpFlag.INSTORAGE)?false:true, null, -1, -1);
		this.request.put("types", types);
		this.request.put("units", units);
		this.request.put("scmCocs", scmCocs);
		if(inStorage==null){
			inStorage = new InStorage();
			inStorage.setImpExpFalg(impExpFlag);
		}
		this.request.put("inStorage", inStorage);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "edit";
	}
	
	
	
	
	
	
	
	
	
	public StorageLogic getStorageLogic() {
		return storageLogic;
	}

	public void setStorageLogic(StorageLogic storageLogic) {
		this.storageLogic = storageLogic;
	}

	public MaterialTypeLogic getLogic() {
		return logic;
	}

	public void setLogic(MaterialTypeLogic logic) {
		this.logic = logic;
	}

	public UnitLogic getUnitLogic() {
		return unitLogic;
	}

	public void setUnitLogic(UnitLogic unitLogic) {
		this.unitLogic = unitLogic;
	}

	public InStorage getInStorage() {
		return inStorage;
	}

	public void setInStorage(InStorage inStorage) {
		this.inStorage = inStorage;
	}

	public OutStorage getOutStorage() {
		return outStorage;
	}

	public void setOutStorage(OutStorage outStorage) {
		this.outStorage = outStorage;
	}

	public String getImpExpFlag() {
		return impExpFlag;
	}

	public void setImpExpFlag(String impExpFlag) {
		this.impExpFlag = impExpFlag;
	}

	public ScmcocLogic getScmcocLogic() {
		return scmcocLogic;
	}

	public void setScmcocLogic(ScmcocLogic scmcocLogic) {
		this.scmcocLogic = scmcocLogic;
	}
	

}
