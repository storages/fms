package com.fms.action;

import java.util.List;

import com.fms.base.action.BaseAction;
import com.fms.commons.ImgExgFlag;
import com.fms.core.entity.BomExg;
import com.fms.core.entity.Material;
import com.fms.logic.BomLogic;
import com.fms.logic.MaterialLogic;

/**
 * Bom表
 * 
 * @author Administrator
 * 
 */
public class BomAction extends BaseAction {

	/********* 分页用的属性 ***********/
	private Integer dataTotal;// 总记录数
	private String currIndex;// 当前页码
	private String maxIndex;// 每页显示最多条数
	private Integer pageNums;// 共有多少页
	private String className = "BomExg";// 表名称
	private String searchStr;// 搜索条件
	private static final Integer DEFAULT_PAGESIZE = 10;

	/********* 其它属性 ***********/
	private String hsCode;
	private String hsModel;
	private String hsName;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected BomLogic bomLogic;
	protected MaterialLogic materLogic;

	/**
	 * 查找bom成品表
	 * 
	 * @return
	 */
	public String findBomExg() {
		try {
			Integer curr = (null == currIndex || "".equals(currIndex)) ? 1 : Integer.parseInt(currIndex);// 当前第几页
			Integer max = (null == maxIndex || "".equals(maxIndex)) ? 1 : Integer.parseInt(currIndex);// 每页最多显示条数
			dataTotal = this.bomLogic.findDataCount(className, parseValue(hsName), parseValue(hsCode), parseValue(hsModel));
			List<BomExg> bomExgs = bomLogic.findBomExg(parseValue(hsName), parseValue(hsCode), parseValue(hsModel), (curr - 1) * DEFAULT_PAGESIZE, DEFAULT_PAGESIZE);
			this.request.put("bomExgs", bomExgs);
			this.request.put("currIndex", curr);
			this.request.put("maxIndex", max);
			this.request.put("pageNums", pageCount(max, dataTotal));
			this.request.put("hsName", parseValue(hsName));
			this.request.put("hsCode", parseValue(hsCode));
			this.request.put("hsModel", parseValue(hsModel));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return this.SUCCESS;
	}

	/**
	 * 加载物料清单中的成品信息，并跳转到新增页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String addExgData() throws Exception {
		List<Material> exgList = materLogic.findMaterialExgs(hsCode, hsName, hsModel, ImgExgFlag.EXG);
		this.request.put("exgList", exgList);
		this.request.put("hsName", parseValue(hsName));
		this.request.put("hsCode", parseValue(hsCode));
		this.request.put("hsModel", parseValue(hsModel));
		return "add";
	}

	private Integer pageCount(Integer maxIndex, Integer dataTotal) {
		pageNums = (dataTotal / DEFAULT_PAGESIZE) + (dataTotal % DEFAULT_PAGESIZE > 0 ? 1 : 0); // 总页数
		if (pageNums == 0) {
			pageNums += 1;
		}
		return pageNums;
	}

	public BomLogic getBomLogic() {
		return bomLogic;
	}

	public void setBomLogic(BomLogic bomLogic) {
		this.bomLogic = bomLogic;
	}

	public MaterialLogic getMaterLogic() {
		return materLogic;
	}

	public void setMaterLogic(MaterialLogic materLogic) {
		this.materLogic = materLogic;
	}

	public Integer getDataTotal() {
		return dataTotal;
	}

	public void setDataTotal(Integer dataTotal) {
		this.dataTotal = dataTotal;
	}

	public String getCurrIndex() {
		return currIndex;
	}

	public void setCurrIndex(String currIndex) {
		this.currIndex = currIndex;
	}

	public String getMaxIndex() {
		return maxIndex;
	}

	public void setMaxIndex(String maxIndex) {
		this.maxIndex = maxIndex;
	}

	public String getSearchStr() {
		return searchStr;
	}

	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}

	public String getHaCode() {
		return hsCode;
	}

	public void setHaCode(String haCode) {
		this.hsCode = haCode;
	}

	public String getHsModel() {
		return hsModel;
	}

	public void setHsModel(String hsModel) {
		this.hsModel = hsModel;
	}

	public Integer getPageNums() {
		return pageNums;
	}

	public void setPageNums(Integer pageNums) {
		this.pageNums = pageNums;
	}

	public String getHsName() {
		return hsName;
	}

	public void setHsName(String hsName) {
		this.hsName = hsName;
	}

}
