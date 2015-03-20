package com.fms.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.fms.base.action.BaseAction;
import com.fms.commons.ImgExgFlag;
import com.fms.core.entity.BomExg;
import com.fms.core.entity.Material;
import com.fms.logic.BomLogic;
import com.fms.logic.MaterialLogic;
import com.fms.utils.AjaxResult;
import com.url.ajax.json.JSONObject;

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
	private String ids;
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

	/**
	 * 保存bom成品表
	 */
	public void saveBomExg() {
		String[] idArr = null;
		PrintWriter out = null;
		String err = "";
		AjaxResult result = new AjaxResult();
		try {
			out = response.getWriter();
			response.setContentType("application/text");
			response.setCharacterEncoding("UTF-8");
			if (ids != null && !"".equals(ids)) {
				List<BomExg> list = new ArrayList<BomExg>();
				idArr = ids.split("/");
				List<Material> exgMaterials = this.materLogic.findMaterialById(idArr);
				for (Material mat : exgMaterials) {
					BomExg bomExg = new BomExg();
					bomExg.setMaterial(mat);
					list.add(bomExg);
				}
				list = this.bomLogic.saveBomExg(list);
				result.setSuccess(true);
				JSONObject json = new JSONObject(result);
				out.println(json.toString());
				out.flush();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			err = "程序异常";
			result.setMsg(err);
			result.setSuccess(false);
			JSONObject json = new JSONObject(result);
			out.println(json.toString());
			out.flush();
		}
	}

	/**
	 * 删除BOM成品表
	 */
	public void delBomExg() {
		if (null != ids && !"".equals(ids)) {
			String[] idArr = ids.split(",");
			if (idArr != null && idArr.length > 0) {
				PrintWriter out = null;
				AjaxResult result = new AjaxResult();
				try {
					out = response.getWriter();
					response.setContentType("application/text");
					response.setCharacterEncoding("UTF-8");
					this.bomLogic.delBomExgByIds(idArr);
					result.setSuccess(true);
					result.setMsg("删除成功！");
					JSONObject json = new JSONObject(result);
					out.println(json.toString());
					out.flush();
					out.close();
				} catch (Exception e) {
					result.setSuccess(false);
					result.setMsg("数据被其它地方引用，不能删除！");
					JSONObject json = new JSONObject(result);
					out.println(json.toString());
					out.flush();
					out.close();
				}
			}
		}
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

	public String getHsCode() {
		return hsCode;
	}

	public void setHsCode(String hsCode) {
		this.hsCode = hsCode;
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

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}
