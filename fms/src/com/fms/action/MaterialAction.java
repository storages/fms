package com.fms.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.fms.base.action.BaseAction;
import com.fms.core.entity.Material;
import com.fms.core.entity.MaterialType;
import com.fms.core.entity.Unit;
import com.fms.logic.MaterialLogic;
import com.fms.logic.MaterialTypeLogic;
import com.fms.utils.AjaxResult;
import com.url.ajax.json.JSONObject;

public class MaterialAction extends BaseAction {

	protected MaterialLogic materLogic;
	protected MaterialTypeLogic logic;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ids;
	/********* 物料实体类属性 *************/
	// 物料名称
	private String hsName;
	// 颜色
	private String color;
	// 物料类别
	private MaterialType materialType;
	// 计量单位
	private String unit;
	// 数量
	private String qty;
	// 规格
	private String model;
	// 成品或原料标记("I"原料，"E"成品)
	private String imgExgFlag;
	// 批次号
	private String batchNO;
	// 最低库存
	private String lowerQty;
	// 备注
	private String note;
	// 物料编码
	private String hsCode;
	// 物料类型编码id
	private String typeId;
	/********* 分页用的属性 ***********/
	private Integer dataTotal;// 总记录数
	private String currIndex;// 当前页码
	private String maxIndex;// 每页显示最多条数
	private Integer pageNums;// 共有多少页
	private String className = "Material";// 表名称
	private String searchStr;// 搜索条件
	private static final Integer DEFAULT_PAGESIZE = 10;

	/**
	 * 查询所有的物料信息【分页】
	 * 
	 * @return
	 */
	public String findAllMaterial() {

		try {
			Integer curr = (null == currIndex || "".equals(currIndex)) ? 1 : Integer.parseInt(currIndex);// 当前第几页
			Integer max = (null == maxIndex || "".equals(maxIndex)) ? 1 : Integer.parseInt(currIndex);// 每页最多显示条数
			dataTotal = this.materLogic.findDataCount(getLoginUser(),className, parseValue(searchStr));
			// imgExgFlag =
			// (this.context.getSession().get("imgExgFlag")!=null)?this.context.getSession().get("imgExgFlag").toString():imgExgFlag;
			List<Material> material = this.materLogic.findAllMaterialInfo(getLoginUser(),parseValue(searchStr), imgExgFlag, (curr - 1) * DEFAULT_PAGESIZE, DEFAULT_PAGESIZE);
			this.request.put("materials", material);
			this.request.put("currIndex", curr);
			this.request.put("maxIndex", max);
			this.request.put("pageNums", pageCount(max, dataTotal));
			this.request.put("searchStr", parseValue(searchStr));
			this.request.put("imgexgflag", imgExgFlag);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.SUCCESS;

	}

	private Integer pageCount(Integer maxIndex, Integer dataTotal) {
		pageNums = (dataTotal / DEFAULT_PAGESIZE) + (dataTotal % DEFAULT_PAGESIZE > 0 ? 1 : 0); // 总页数
		if (pageNums == 0) {
			pageNums += 1;
		}
		return pageNums;
	}

	public void findAllUnit() {
		List<Unit> units = this.materLogic.findAllUnit(getLoginUser());
		this.request.put("units", units);
	}

	public String findMaterialById() {
		if (null != ids && !"".equals(ids)) {
			String[] arrIds = ids.split(",");
			if (null != arrIds && arrIds.length > 0) {
				String id = arrIds[0];
				Material materinfo = this.materLogic.findMaterialById(getLoginUser(),id);
				if (null != materinfo) {
					findAllUnit();
					List<MaterialType> types = this.logic.findAllType(getLoginUser(),null);
					this.request.put("materialTypes", types);
					this.request.put("materinfo", materinfo);
					this.request.put("imgExgFlag", imgExgFlag);
				}
			}
		}
		return "editinfo";
	}

	public String save() {
		Material m = new Material();
		if (null != ids && !"".equals(ids)) {
			m.setId(ids);
		}
		MaterialType types = this.logic.findTypeById(getLoginUser(),typeId);
		m.setMaterialType(types);
		m.setHsCode(this.parseValue(hsCode));
		m.setHsName(this.parseValue(hsName));
		m.setColor(this.parseValue(color));
		m.setImgExgFlag(imgExgFlag);
		m.setQty((qty == null || "".equals(qty)) ? 0.0 : Double.parseDouble(qty));
		m.setModel(this.parseValue(model));
		m.setBatchNO(batchNO);
		m.setLowerQty((lowerQty == null || "".equals(lowerQty)) ? 0.0 : Double.parseDouble(lowerQty));
		m.setNote(this.parseValue(note));
		List<Unit> list = this.materLogic.findAllUnit(getLoginUser());
		for (Unit u : list) {
			if (u.getName().equals(this.parseValue(unit))) {
				m.setUnit(u);
				break;
			}
		}
		this.materLogic.saveOrUpdate(getLoginUser(),m);
		this.session.put("imgExgFlag", m.getImgExgFlag());
		return "save";
	}

	public String add() {
		findAllUnit();
		List<MaterialType> types = this.logic.findAllType(getLoginUser(),null);
		this.request.put("materialTypes", types);
		this.request.put("imgExgFlag", imgExgFlag);
		return "add";
	}

	public String deleteMaterial() throws Exception {
		if (null != ids && !"".equals(ids)) {
			String[] arrIds = ids.split(",");
			if (null != arrIds && arrIds.length > 0) {
				this.materLogic.deleteMaterial(getLoginUser(),arrIds);
			}
		}
		return "save";
	}

	/**
	 * 验证物料信息是否重复【名称+规格+批次号】
	 */
	public void checkMaterial() {
		PrintWriter out = null;
		AjaxResult result = new AjaxResult();
		try {
			out = response.getWriter();
			response.setContentType("application/text");
			response.setCharacterEncoding("UTF-8");
			Material material = this.materLogic.checkMaterial(getLoginUser(),hsName, model, batchNO);
			if (null != material) {
				result.setSuccess(false);
				result.setMsg("该物料已存在！【名称+规格+批次号】都相同");
			} else {
				result.setSuccess(true);
			}
			JSONObject json = new JSONObject(result);
			out.println(json.toString());
			out.flush();
		} catch (IOException e) {
			result.setMsg("对不起出错了：/n" + e.getMessage());
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * 验证物料编码是否重复
	 */
	public void findHsCode() {
		PrintWriter out = null;
		AjaxResult result = new AjaxResult();
		try {
			out = response.getWriter();
			response.setContentType("application/text");
			response.setCharacterEncoding("UTF-8");
			String findCode = this.materLogic.findHsCode(getLoginUser(),hsCode);
			if (null != findCode) {
				result.setSuccess(false);
				result.setMsg("编码已使用过了！");
			} else {
				result.setSuccess(true);
			}
			JSONObject json = new JSONObject(result);
			out.println(json.toString());
			out.flush();
		} catch (IOException e) {
			result.setMsg("对不起出错了：/n" + e.getMessage());
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	public String getHsName() {
		return hsName;
	}

	public void setHsName(String hsName) {
		this.hsName = hsName;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public MaterialType getMaterialType() {
		return materialType;
	}

	public void setMaterialType(MaterialType materialType) {
		this.materialType = materialType;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getImgExgFlag() {
		return imgExgFlag;
	}

	public void setImgExgFlag(String imgExgFlag) {
		this.imgExgFlag = imgExgFlag;
	}

	public String getBatchNO() {
		return batchNO;
	}

	public void setBatchNO(String batchNO) {
		this.batchNO = batchNO;
	}

	public String getLowerQty() {
		return lowerQty;
	}

	public void setLowerQty(String lowerQty) {
		this.lowerQty = lowerQty;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public MaterialLogic getMaterLogic() {
		return materLogic;
	}

	public void setMaterLogic(MaterialLogic materLogic) {
		this.materLogic = materLogic;
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

	public Integer getPageNums() {
		return pageNums;
	}

	public void setPageNums(Integer pageNums) {
		this.pageNums = pageNums;
	}

	public String getSearchStr() {
		return searchStr;
	}

	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getHsCode() {
		return hsCode;
	}

	public void setHsCode(String hsCode) {
		this.hsCode = hsCode;
	}

	public MaterialTypeLogic getLogic() {
		return logic;
	}

	public void setLogic(MaterialTypeLogic logic) {
		this.logic = logic;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

}
