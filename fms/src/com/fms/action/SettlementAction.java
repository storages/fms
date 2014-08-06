package com.fms.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.fms.base.action.BaseAction;
import com.fms.core.entity.Settlement;
import com.fms.core.entity.Stock;
import com.fms.logic.SettlementLogic;
import com.fms.utils.AjaxResult;
import com.url.ajax.json.JSONObject;

public class SettlementAction extends BaseAction {

	public SettlementAction(){
		System.out.println("---");
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SettlementLogic settLogic;
	/*********结算方式实体类*************/
	private Settlement settlement;
	private String ids;
	private String code;
	private String name;
	private String note;
	private String searhStr;
	
	
	public String findAllSett(){
		List<Settlement> settlements = this.settLogic.findAllSettlement(this.parse(searhStr));
		this.request.put("settlements", settlements);
		return this.SUCCESS;
	}
	
	public String findSettlById(){
		if (null != ids && !"".equals(ids)) {
			String[] arrIds = ids.split(",");
			if (null != arrIds && arrIds.length > 0) {
				String id = arrIds[0];
				Settlement settl = this.settLogic.findSettById(id);
				if (null != settl) {
					this.request.put("settl", settl);
				}
			}
		}
		return "find";
	}
	
	/**
	 * 验证结算方式编码是否重复
	 */
	public void findSettByCode(){
		PrintWriter out = null;
		AjaxResult  result=new AjaxResult();
		try {
			out = response.getWriter();
			response.setContentType("application/text");
			response.setCharacterEncoding("UTF-8");
			String findCode = this.settLogic.findSettByCode(code);
			if(null!=findCode){
				result.setSuccess(false);
				result.setMsg("编码已使用过了！");
			}else{
				result.setSuccess(true);
			}			
			JSONObject json=new JSONObject(result);
			out.println(json.toString());
			out.flush();
		} catch (IOException e) {
			result.setMsg("对不起出错了：/n"+e.getMessage());
		}finally{
			if(out!=null){
				out.close();
			}
		}
	}
	
	/**
	 * 保存结算方式
	 * @return
	 */
	public String saveSettl(){
		this.settLogic.saveSettlement(setProperty(new Settlement()));
		return "save";
	}
	
	/**
	 * 删除结算方式
	 * @return
	 */
	public String delSettlById(){
		if (null != ids && !"".equals(ids)) {
			String [] idArr = ids.split(",");
			if(idArr!=null && idArr.length>0){
				this.settLogic.delSettltById(idArr);
			}
		}
		return "save";
	}
	
	/**
	 * 填充对象
	 * 
	 * @param scmcoc
	 * @return
	 */
	private Settlement setProperty(Settlement settl) {
		if(null!=ids && !"".equals(ids)){
			settl.setId(ids);
		}
		settl.setCode(parse(code));
		settl.setName(parse(name));
		settl.setNote(parse(note));
		return settl;
	}
	
	
	public SettlementLogic getSettLogic() {
		return settLogic;
	}


	public void setSettLogic(SettlementLogic settLogic) {
		this.settLogic = settLogic;
	}


	public Settlement getSettlement() {
		return settlement;
	}

	public void setSettlement(Settlement settlement) {
		this.settlement = settlement;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
