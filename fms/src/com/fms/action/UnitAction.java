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
import com.fms.core.entity.Stock;
import com.fms.core.entity.Unit;
import com.fms.logic.UnitLogic;
import com.fms.temp.TempStock;
import com.fms.temp.TempUnit;
import com.fms.utils.AjaxResult;
import com.fms.utils.ExcelUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.url.ajax.json.JSONException;
import com.url.ajax.json.JSONObject;

public class UnitAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected UnitLogic unitLogic;
	
	
private String ids;
	
	/*********计量单位实体类属性*************/
	private String code;
	private String name;
	private String note;
	
	
	/*********分页用的属性***********/
	private Integer dataTotal;//总记录数
	private String currIndex;//当前页码
	private String maxIndex;//每页显示最多条数
	private Integer pageNums;//共有多少页
	private String className="Unit";//表名称
	private String searchStr;//搜索条件
	private static final Integer DEFAULT_PAGESIZE = 10; 
	
	/********* 获取前台选择的文件 ***********/
	 private File     uploadFile;         //上传的文件    名称是Form 对应的name 
	 private String   uploadFileContentType;   //文件的类型
	 private String   uploadFileFileName;    //文件的名称
	 //
	 private String sendStr; 

	private TempStock temp;
	
	/**
	 * 查询所有计量单位列表【分页】
	 * @return
	 */
	public String findAllUnit(){
		Integer curr = (null==currIndex || "".equals(currIndex))?1:Integer.parseInt(currIndex);//当前第几页
		Integer max = (null==maxIndex || "".equals(maxIndex))?1:Integer.parseInt(currIndex);//每页最多显示条数
		dataTotal = this.unitLogic.findDataCount(className,parseValue(searchStr));
		List<Unit> units = this.unitLogic.findAllUnit(parseValue(searchStr),(curr-1)*DEFAULT_PAGESIZE,DEFAULT_PAGESIZE);
		request.put("units", units);
		this.request.put("currIndex", curr);
		this.request.put("maxIndex", max);
		this.request.put("pageNums", pageCount(max, dataTotal));
		this.request.put("searchStr", parseValue(searchStr));
		return this.SUCCESS;
	}
	
	
	private Integer pageCount(Integer maxIndex,Integer dataTotal){
		pageNums = (dataTotal / DEFAULT_PAGESIZE) + (dataTotal % DEFAULT_PAGESIZE > 0 ? 1 : 0); // 总页数
		if(pageNums==0){
			pageNums+=1;
		}
		return pageNums;
	}
	
	
	public String findUnitById(){
		if (null != ids && !"".equals(ids)) {
			String[] arrIds = ids.split(",");
			if (null != arrIds && arrIds.length > 0) {
				String id = arrIds[0];
				Unit unit = this.unitLogic.findUnitById(id);
				if (null != unit) {
					this.request.put("unit", unit);
				}
			}
		}
		return "find";
	}

	
	/**
	 * 保存计量单位信息
	 * @return
	 * @throws Exception
	 */
	public String saveUnit(){
		this.unitLogic.saveUnit(this.setProperty(new Unit()));
		return "save";
	}
	
	/**
	 * 填充对象
	 * 
	 * @param unit
	 * @return
	 */
	private Unit setProperty(Unit unit) {
		if(null!=ids && !"".equals(ids)){
			unit.setId(ids);
		}
		unit.setCode(parseValue(code));
		unit.setName(parseValue(name));
		unit.setNote(parseValue(note));
		return unit;
	}
	
	
	/**
	 * 验证计量单位编码是否重复
	 */
	public void findUnitByCode(){
		PrintWriter out = null;
		AjaxResult  result=new AjaxResult();
		try {
			out = response.getWriter();
			response.setContentType("application/text");
			response.setCharacterEncoding("UTF-8");
			String findCode = this.unitLogic.findUnitByCode(code);
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
	 * 删除计量单位信息
	 * @return
	 */
	public void deleteUnit(){
		
		if (null != ids && !"".equals(ids)) {
			String [] idArr = ids.split(",");
			if(idArr!=null && idArr.length>0){
				PrintWriter out = null;
				AjaxResult  result=new AjaxResult();
				try {
					out = response.getWriter();
					response.setContentType("application/text");
					response.setCharacterEncoding("UTF-8");
					this.unitLogic.delUnitById(idArr);
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
			if(!"编码".equals(title[0]) || !"计量单位名称".equals(title[1]) || !"备注".equals(title[2])){
				result.setSuccess(false);
				result.setMsg("导入的excel文件内容不正确!");
			}else{
			
				List<Unit> units = new ArrayList<Unit>();
				for (int i = 1; i < content.length; i++) {
					Unit s = new Unit();
					s.setCode(content[i][0]);
					s.setName(content[i][1]);
					s.setNote(content[i][2]);
					units.add(s);
				}
				List tlist = unitLogic.doValidata(units);
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
		List list= net.sf.json.JSONArray.toList(jsonArray, new TempUnit(), new JsonConfig());
		if(null!=list && list.size()>0){
			for(int i = 0;i<list.size();i++){
				TempUnit tu = (TempUnit)list.get(i);
				if(null!=tu.getErrorInfo() && !"".equals(tu.getErrorInfo().trim())){
					errorList.add(tu);
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
			List list= net.sf.json.JSONArray.toList(jsonArray, new TempUnit(), new JsonConfig());
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
			if (!this.unitLogic.doSaveExcelData(list)) {
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

	public List toListofClassName(JsonArray arr,Class t){
		List list=new ArrayList();
		Gson gson=new Gson();
		for(int x=0;x<arr.size();x++){
		list.add(gson.fromJson(arr.get(x), t.getClass()));
		}
		return list;
	}
	
	public UnitLogic getUnitLogic() {
		return unitLogic;
	}

	public void setUnitLogic(UnitLogic unitLogic) {
		this.unitLogic = unitLogic;
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


	public String getSearchStr() {
		return searchStr;
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


	public Integer getPageNums() {
		return pageNums;
	}


	public void setPageNums(Integer pageNums) {
		this.pageNums = pageNums;
	}


	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
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
