package com.fms.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.fms.base.action.BaseAction;
import com.fms.core.entity.MaterialType;
import com.fms.core.entity.Stock;
import com.fms.logic.MaterialTypeLogic;
import com.fms.utils.AjaxResult;
import com.url.ajax.json.JSONObject;

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
	protected MaterialTypeLogic logic;
	protected MaterialType materialType;
	protected String name;
	protected String ids;
	protected String note;
	protected String searhStr;
	
	public String findAllMaterialType(){
		try{
			List<MaterialType> typeList = this.logic.findAllType(searhStr);
			this.request.put("typeList", typeList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return this.SUCCESS;
	}
	
	/**
	 * 保存物料类别
	 * @return
	 */
	public String save(){
		try{
			List<MaterialType> list = new ArrayList<MaterialType>();
			list.add(setProperty(new MaterialType()));
		this.logic.saveMaterialType(list);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "save";
	}
	
	
	/**
	 * 填充对象
	 * 
	 * @param scmcoc
	 * @return
	 */
	private MaterialType setProperty(MaterialType mater) {
		if (null != ids && !"".equals(ids)) {
			mater.setId(ids);
		}
		mater.setTypeName(parseValue(name));
		mater.setNote(parseValue(note));
		return mater;
	}
	
	
	/**
	 * 验证名称是否重复
	 */
	public void findTypeByName(){
		PrintWriter out = null;
		AjaxResult result = new AjaxResult();
		try {
			out = response.getWriter();
			response.setContentType("application/text");
			response.setCharacterEncoding("UTF-8");
			String findName = this.logic.findTypeByName(name);
			if (null != findName) {
				result.setSuccess(false);
				result.setMsg("名称已使用过了！");
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
	
	public String findTypeById(){
		if (null != ids && !"".equals(ids)) {
			String[] arrIds = ids.split(",");
			if (null != arrIds && arrIds.length > 0) {
				String id = arrIds[0];
				MaterialType mater = this.logic.findTypeById(id);
				if (null != mater) {
					this.request.put("mater", mater);
				}
			}
		}
		return "find";
	}
	
	/**
	 * 删除物料类型
	 */
	public void deleteMaterialType(){
		if (null != ids && !"".equals(ids)) {
			String[] idArr = ids.split(",");
			if (idArr != null && idArr.length > 0) {
				PrintWriter out = null;
				AjaxResult result = new AjaxResult();
				try {
					out = response.getWriter();
					response.setContentType("application/text");
					response.setCharacterEncoding("UTF-8");
					this.logic.delMaterialTypeById(idArr);
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
	
	public MaterialTypeLogic getLogic() {
		return logic;
	}
	public void setLogic(MaterialTypeLogic logic) {
		this.logic = logic;
	}

	public MaterialType getMaterialType() {
		return materialType;
	}

	public void setMaterialType(MaterialType materialType) {
		this.materialType = materialType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getSearhStr() {
		return searhStr;
	}

	public void setSearhStr(String searhStr) {
		this.searhStr = searhStr;
	}

	

	
}
