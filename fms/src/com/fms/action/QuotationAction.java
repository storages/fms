package com.fms.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import com.fms.base.action.BaseAction;
import com.fms.core.entity.Currencies;
import com.fms.core.entity.Material;
import com.fms.core.entity.Quotation;
import com.fms.core.entity.Scmcoc;
import com.fms.logic.MaterialLogic;
import com.fms.logic.QuotationLogic;
import com.fms.logic.ScmcocLogic;
import com.fms.temp.TempCurr;
import com.fms.temp.TempQuotation;
import com.fms.utils.AjaxResult;
import com.fms.utils.ExcelUtil;
import com.google.gson.Gson;
import com.url.ajax.json.JSONException;
import com.url.ajax.json.JSONObject;




/**
 * 报价单Action
 * 
 * @author Administrator
 * 
 */
public class QuotationAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected QuotationLogic quotationLogic;
	protected MaterialLogic materLogic;
	protected ScmcocLogic scmcocLogic;

	/********* 搜索条件 ***********/
	protected String scmCocName;//供应商名称
	protected String hsCode;//物料编码
	protected String begineffectDate;//生效日期（开始）
	protected String endeffectDate;//生效日期（结束）
	/********* 分页用的属性 ***********/
	private Integer dataTotal;// 总记录数
	private String currIndex;// 当前页码
	private String maxIndex;// 每页显示最多条数
	private Integer pageNums;// 共有多少页
	private String className = "Quotation";// 表名称
	private static final Integer DEFAULT_PAGESIZE = 10;
	/********* 其它参数 ***********/
	protected String ids;
	protected String scmid;
	
	/********* 获取前台选择的文件 ***********/
	 private File     uploadFile;         //上传的文件    名称是Form 对应的name 
	 private String   uploadFileContentType;   //文件的类型
	 private String   uploadFileFileName;    //文件的名称
	 //
	 private String sendStr; 
	

	/**
	 * 查找符合条件的报价单信息
	 * 
	 * @return
	 */
	public String findQuotations() {
		try {
			begineffectDate = "".equals(begineffectDate)?null:begineffectDate;
			endeffectDate = "".equals(endeffectDate)?null:endeffectDate;
			Date date = null;
	        if(null!=begineffectDate){
	        	date = new SimpleDateFormat("yyyy-MM-dd").parse(begineffectDate); 
	        }
	        Date date2 = null;
	        if(null!=endeffectDate){
	        	date2 = new SimpleDateFormat("yyyy-MM-dd").parse(endeffectDate); 
	        }
			Integer curr = (null==currIndex || "".equals(currIndex))?1:Integer.parseInt(currIndex);//当前第几页
			Integer max = (null==maxIndex || "".equals(maxIndex))?1:Integer.parseInt(currIndex);//每页最多显示条数
			dataTotal = this.quotationLogic.findDataCount(getLoginUser(),parseValue(scmCocName),parseValue(hsCode),(begineffectDate==null||"".equals(begineffectDate))?null:date,(endeffectDate==null||"".equals(endeffectDate))?null:date2);
			List<Quotation> quotations = this.quotationLogic.findQuotations(getLoginUser(),parseValue(scmCocName),parseValue(hsCode),begineffectDate==null?null:date,endeffectDate==null?null:date2, (curr-1)*DEFAULT_PAGESIZE,DEFAULT_PAGESIZE);
			List<Material> mlist = materLogic.findAllMaterialInfo(getLoginUser(),hsCode, null, -1, -1);
			List<Scmcoc> scmcocs = quotationLogic.findAll(getLoginUser());
			this.request.put("scmcocs", scmcocs);
			this.request.put("quotations", quotations);
			this.request.put("mlist", mlist);
			this.request.put("currIndex", curr);
			this.request.put("maxIndex", max);
			this.request.put("pageNums", pageCount(max, dataTotal));
			this.request.put("scmCocName", parseValue(scmCocName));
			this.request.put("hsCode", parseValue(hsCode));
			this.request.put("begineffectDate", begineffectDate==null?null:begineffectDate);
			this.request.put("endeffectDate", endeffectDate==null?null:endeffectDate);
			//findMaterial();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;

	}
	
	/**
	 * 根据前台页面传来的id,查找物料信息(
	 * @return
	 */
	public String findMaterialByIds(){
		if(ids!=null && !"".equals(ids)){
			String [] idarr = ids.split("/");
			List<Material> materData = this.materLogic.findMaterialById(getLoginUser(),idarr);
			List<Quotation> list = new ArrayList<Quotation>();
			try{
			Scmcoc coc = quotationLogic.findById(getLoginUser(),scmid);
			for(Material m:materData){
				Quotation q = new Quotation();
				q.setMaterial(m);
				q.setScmcoc(coc);
				list.add(q);
			}
			}catch(Exception e){e.printStackTrace();}
			this.saveQuotation(list);
			findQuotations();
		}
		return this.SUCCESS;
	} 
	
	/**
	 * 保存报价单
	 * @param list
	 */
	private void saveQuotation(List<Quotation> list){
		if(null!=list && list.size()>0){
			this.quotationLogic.saveQuotations(getLoginUser(),list);
		}
	}
	
	/**
	 * 修改报价单
	 */
	public void editQuotation(){
		PrintWriter out = null;
		AjaxResult result = new AjaxResult();
		List<List<String>> list = this.parseJsonArr(jsonstr);
		List<Quotation> editData = new ArrayList<Quotation>();
		if(null!=list && list.size()>0){
			for(List<String> ldata:list){
				List<String> contents = ldata;
				if(null!=contents && contents.size()>0){
					try {
						String msg = "";
						Quotation quotation = this.quotationLogic.findQuotationById(getLoginUser(),className, contents.get(0));
						if(contents.get(1)==null||"".equals(contents.get(1))||"".equals(contents.get(1).trim())||"null".equals(contents.get(1).trim())){
							msg= "单价不能为空";
						}else if(!isNumeric(contents.get(1).trim())){
							msg= "单价必须是数字";
						}
						if(contents.get(2)==null||"".equals(contents.get(2))||"".equals(contents.get(2).trim())||"null".equals(contents.get(2).trim())){
							msg= "生效日期不能为空";
						}else{
							try{
								new SimpleDateFormat("yyyy-MM-dd").parse(contents.get(2).trim());
							}catch(ParseException e){
								msg= "非法的生效日期";
							}
						}
						if(contents.size()<4){
							contents.add(null);
						}
						out = response.getWriter();
						response.setContentType("application/text");
						response.setCharacterEncoding("UTF-8");
						if("".equals(msg)){
							quotation.setPrice(contents.get(1)==null||"".equals(contents.get(1))||"".equals(contents.get(1).trim())||"null".equals(contents.get(1).trim())?null:Double.parseDouble(contents.get(1).trim()));//单价
							quotation.setEffectDate(contents.get(2)==null||"".equals(contents.get(2))||"".equals(contents.get(2).trim())||"null".equals(contents.get(2).trim())?null:new SimpleDateFormat("yyyy-MM-dd").parse(contents.get(2).trim()));//生效日期
							quotation.setNote((contents.get(3)==null||"".equals(contents.get(3))||"".equals(contents.get(3).trim())||"null".equals(contents.get(3).trim())?null:parseValue(contents.get(3).trim())));//备注
							editData.add(quotation);
							this.saveQuotation(editData);
							//this.findQuotations();
							result.setSuccess(true);
						}else{
							result.setSuccess(false);
							result.setMsg(msg);
						}
						JSONObject json = new JSONObject(result);
						out.println(json.toString());
						out.flush();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		//return this.SUCCESS;
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
			List list= net.sf.json.JSONArray.toList(jsonArray, new TempQuotation(), new JsonConfig());
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
			if (!this.quotationLogic.doSaveExcelData(getLoginUser(),list)) {
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
				result.setMsg("成功保存 "+list.size()+" 条数据！");
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
	
	/**
	 * 更新报价单
	 */
	public void updatePrice(){
		try {
			out = response.getWriter();
			response.setContentType("application/text");
			response.setCharacterEncoding("UTF-8");
			if(!"".equals(ids)&&null!=ids){
				String[] idarr = ids.split(",");
				int count = this.quotationLogic.updatePrice(getLoginUser(),idarr);
				if(count==-1){
					this.result.setMsg("报价单中的单价、生效日期不能为空，请检查!");
					this.result.setSuccess(false);
				}else if(count==0){
					this.result.setMsg("目前没有可更新的内容!");
					this.result.setSuccess(true);
				}else if(count>0){
					this.result.setMsg("成功更新"+count+"条数据!");
					this.result.setSuccess(true);
				}
				JSONObject json = new JSONObject(result);
				out.println(json.toString());
				out.flush();
			}
		} catch (IOException e) {
			this.result.setMsg("更新失败!");
			this.result.setSuccess(false);
			JSONObject json = new JSONObject(result);
			out.println(json.toString());
			out.flush();
			e.printStackTrace();
		}
	}
	
	/**
	 * 调用excel导入页面
	 * @return
	 */
	public String toImportPage(){
		return "importexcel";
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
			
			String [] title = new String[5];
			title[0] = content[0][0];
			title[1] = content[0][1];
			title[2] = content[0][2];
			title[3] = content[0][3];
			title[4] = content[0][4];
			if(!"供应商编码".equals(title[0]) || !"供应商名称".equals(title[1]) || !"物料编码".equals(title[2])|| !"物料名称".equals(title[3])|| !"单价".equals(title[4])){
				result.setSuccess(false);
				result.setMsg("导入的excel文件内容不正确!");
			}else{
				List<TempQuotation> tempList = new ArrayList<TempQuotation>();
				for (int i = 1; i < content.length; i++) {
					TempQuotation temp = new TempQuotation();
					temp.setScmcocCode(content[i][0]);
					temp.setScmcocName(content[i][1]);
					temp.setHsCode(content[i][2]);
					temp.setHsName(content[i][3]);
					temp.setPrice(content[i][4]);
					tempList.add(temp);
				}
				List tlist = this.quotationLogic.doValidata(getLoginUser(),tempList);
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
			e.printStackTrace();
		}
	}
	
	/**
	 * 清除excel导入验证错误的信息
	 */
	public void clearErrorData(){
		List errorList = new ArrayList();
		AjaxResult result=new AjaxResult();
		result.setSuccess(false);
		net.sf.json.JSONArray jsonArray= net.sf.json.JSONArray.fromObject(sendStr);
		List list= net.sf.json.JSONArray.toList(jsonArray, new TempQuotation(), new JsonConfig());
		if(null!=list && list.size()>0){
			for(int i = 0;i<list.size();i++){
				TempQuotation tq = (TempQuotation)list.get(i);
				if(null!=tq.getErrorInfo() && !"".equals(tq.getErrorInfo().trim())){
					errorList.add(tq);
				}
			}
			list.removeAll(errorList);
		}
		Gson gson=new Gson();
		result.setObj(list);
		result.setMsg("解析成功");
		result.setSuccess(true);
		String str= gson.toJson(result);
		Writer writer;
		try {
			writer = response.getWriter();
			writer.write(str.toString());
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	private Integer pageCount(Integer maxIndex,Integer dataTotal){
		pageNums = (dataTotal / DEFAULT_PAGESIZE) + (dataTotal % DEFAULT_PAGESIZE > 0 ? 1 : 0); // 总页数
		if(pageNums==0){
			pageNums+=1;
		}
		return pageNums;
	}
	
	/**
	 * 删除报价单信息
	 * 
	 * @return
	 */
	public void deleteQuotation() {

		if (null != ids && !"".equals(ids)) {
			String[] idArr = ids.split(",");
			if (idArr != null && idArr.length > 0) {
				PrintWriter out = null;
				AjaxResult result = new AjaxResult();
				try {
					out = response.getWriter();
					response.setContentType("application/text");
					response.setCharacterEncoding("UTF-8");
					this.quotationLogic.delQuotationById(getLoginUser(),idArr);
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
	
	public QuotationLogic getQuotationLogic() {
		return quotationLogic;
	}

	public void setQuotationLogic(QuotationLogic quotationLogic) {
		this.quotationLogic = quotationLogic;
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


	public String getScmCocName() {
		return scmCocName;
	}


	public void setScmCocName(String scmCocName) {
		this.scmCocName = scmCocName;
	}


	public String getHsCode() {
		return hsCode;
	}


	public void setHsCode(String hsCode) {
		this.hsCode = hsCode;
	}


	public String getBegineffectDate() {
		return begineffectDate;
	}


	public void setBegineffectDate(String begineffectDate) {
		this.begineffectDate = begineffectDate;
	}


	public String getEndeffectDate() {
		return endeffectDate;
	}


	public void setEndeffectDate(String endeffectDate) {
		this.endeffectDate = endeffectDate;
	}

	public MaterialLogic getMaterLogic() {
		return materLogic;
	}

	public void setMaterLogic(MaterialLogic materLogic) {
		this.materLogic = materLogic;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getScmid() {
		return scmid;
	}

	public void setScmid(String scmid) {
		this.scmid = scmid;
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

	public ScmcocLogic getScmcocLogic() {
		return scmcocLogic;
	}

	public void setScmcocLogic(ScmcocLogic scmcocLogic) {
		this.scmcocLogic = scmcocLogic;
	}




	

}
