package com.fms.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JsonConfig;

import com.fms.base.action.BaseAction;
import com.fms.core.entity.Settlement;
import com.fms.core.entity.Stock;
import com.fms.logic.SettlementLogic;
import com.fms.temp.TempSettlement;
import com.fms.temp.TempStock;
import com.fms.utils.AjaxResult;
import com.fms.utils.ExcelUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.url.ajax.json.JSONException;
import com.url.ajax.json.JSONObject;

public class SettlementAction extends BaseAction {

	public SettlementAction(){
		System.out.println("---");
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SettlementLogic settlementLogic;
	/*********结算方式实体类*************/
	private Settlement settlement;
	private String ids;
	private String code;
	private String name;
	private String note;
	private String searhStr;
	
	/********* 获取前台选择的文件 ***********/
	 private File     uploadFile;         //上传的文件    名称是Form 对应的name 
	 private String   uploadFileContentType;   //文件的类型
	 private String   uploadFileFileName;    //文件的名称
	 //
	 private String sendStr; 

	private TempStock temp;
	
	
	public String findAllSett(){
		List<Settlement> settlements = this.settlementLogic.findAllSettlement(this.parseValue(searhStr));
		this.request.put("settlements", settlements);
		return this.SUCCESS;
	}
	
	public String findSettlById(){
		if (null != ids && !"".equals(ids)) {
			String[] arrIds = ids.split(",");
			if (null != arrIds && arrIds.length > 0) {
				String id = arrIds[0];
				Settlement settl = this.settlementLogic.findSettById(id);
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
			String findCode = this.settlementLogic.findSettByCode(code);
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
		this.settlementLogic.saveSettlement(setProperty(new Settlement()));
		return "save";
	}
	
	/**
	 * 删除结算方式
	 * @return
	 */
	public void delSettlById(){
		if (null != ids && !"".equals(ids)) {
			String [] idArr = ids.split(",");
			if(idArr!=null && idArr.length>0){
				PrintWriter out = null;
				AjaxResult  result=new AjaxResult();
				try {
					out = response.getWriter();
					response.setContentType("application/text");
					response.setCharacterEncoding("UTF-8");
					this.settlementLogic.delSettltById(idArr);
					result.setSuccess(true);
					result.setMsg("删除成功！");
					JSONObject json=new JSONObject(result);
					out.println(json.toString());
					out.flush();
					out.close();
				} catch (Exception e) {
					result.setSuccess(false);
					result.setMsg("数据被其它地方引用，不能删除！");
					JSONObject json=new JSONObject(result);
					out.println(json.toString());
					out.flush();
					out.close();
				}
			}
		}
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
		settl.setCode(parseValue(code));
		settl.setName(parseValue(name));
		settl.setNote(parseValue(note));
		return settl;
	}
	
	
	/**
	 * 解析excel数据，并验证数据有效性
	 * @return
	 */
	public void importData() {
		AjaxResult result=new AjaxResult();
		result.setSuccess(false);
		try {
			//就这句，如何获取jsp页面传过来的文件
			String[][] content = ExcelUtil.readExcel(uploadFile, 0);
			
			String [] title = new String[3];
			title[0] = content[0][0];
			title[1] = content[0][1];
			title[2] = content[0][2];
			if(!"编码".equals(title[0]) || !"结算方式名称".equals(title[1]) || !"备注".equals(title[2])){
				result.setSuccess(false);
				result.setMsg("导入的excel文件内容不正确!");
			}else{
				List<Settlement> settl = new ArrayList<Settlement>();
				for (int i = 1; i < content.length; i++) {
					Settlement s = new Settlement();
					s.setCode(content[i][0]);
					s.setName(content[i][1]);
					s.setNote(content[i][2]);
					settl.add(s);
				}
				List tlist = settlementLogic.doValidata(settl);
				result.setSuccess(true);
				result.setObj(tlist);
			}
		} catch (FileNotFoundException e) {
			result.setSuccess(false);
			result.setMsg("操作错误"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Gson gson=new Gson();
		String str= gson.toJson(result);
	    try {
			Writer writer= response.getWriter();
			writer.write(str);
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 清除错误的数据
	 * @return
	 */
	public void clearErrorData(){
		List errorList = new ArrayList();
		AjaxResult result=new AjaxResult();
		result.setSuccess(false);
		net.sf.json.JSONArray jsonArray= net.sf.json.JSONArray.fromObject(sendStr);
		List list= net.sf.json.JSONArray.toList(jsonArray, new TempSettlement(), new JsonConfig());
		if(null!=list && list.size()>0){
			for(int i = 0;i<list.size();i++){
				TempSettlement ts = (TempSettlement)list.get(i);
				if(null!=ts.getErrorInfo() && !"".equals(ts.getErrorInfo().trim())){
					errorList.add(ts);
				}
			}
			list.removeAll(errorList);
		}
		Gson gson=new Gson();
		result.setObj(list);
		result.setSuccess(true);
		String str= gson.toJson(result);
		Writer writer;
		try {
			writer = response.getWriter();
			writer.write(str);
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 保存正确的excel数据
	 * @throws JSONException 
	 */
	public String saveExcelData() {
		PrintWriter out = null;
		AjaxResult result = new AjaxResult();
		try {
			net.sf.json.JSONArray jsonArray= net.sf.json.JSONArray.fromObject(sendStr);
			List list= net.sf.json.JSONArray.toList(jsonArray, new TempSettlement(), new JsonConfig());
			if(null==list || list.size()<=0){
				out = response.getWriter();
				response.setContentType("application/text");
				response.setCharacterEncoding("UTF-8");
				result.setSuccess(false);
				result.setMsg("没有数据可保存!");
				JSONObject json = new JSONObject(result);
				out.println(json.toString());
				out.flush();
				out.close();
			}
			if (!this.settlementLogic.doSaveExcelData(list)) {
				out = response.getWriter();
				response.setContentType("application/text");
				response.setCharacterEncoding("UTF-8");
				result.setSuccess(false);
				result.setMsg("保存的数据中有错误，请点击【删除错误】按钮后再保存!");
				JSONObject json = new JSONObject(result);
				out.println(json.toString());
				out.flush();
				out.close();
			} else {
				out = response.getWriter();
				response.setContentType("application/text");
				response.setCharacterEncoding("UTF-8");
				result.setSuccess(true);
				result.setMsg("成功保存"+list.size()+"条数据！");
				session.put("tlist", null);
				JSONObject json = new JSONObject(result);
				out.println(json.toString());
				out.flush();
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}



	public SettlementLogic getSettlementLogic() {
		return settlementLogic;
	}

	public void setSettlementLogic(SettlementLogic settlementLogic) {
		this.settlementLogic = settlementLogic;
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

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploadFileContentType() {
		return uploadFileContentType;
	}

	public void setUploadFileContentType(String uploadFileContentType) {
		this.uploadFileContentType = uploadFileContentType;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public String getSendStr() {
		return sendStr;
	}

	public void setSendStr(String sendStr) {
		this.sendStr = sendStr;
	}

	public TempStock getTemp() {
		return temp;
	}

	public void setTemp(TempStock temp) {
		this.temp = temp;
	}
	

}
