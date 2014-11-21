package com.fms.action;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.fms.base.action.BaseAction;
import com.fms.core.entity.Material;
import com.fms.core.entity.Quotation;
import com.fms.core.entity.Scmcoc;
import com.fms.logic.MaterialLogic;
import com.fms.logic.QuotationLogic;
import com.fms.utils.AjaxResult;
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
			dataTotal = this.quotationLogic.findDataCount(parseValue(scmCocName),parseValue(hsCode),(begineffectDate==null||"".equals(begineffectDate))?null:date,(endeffectDate==null||"".equals(endeffectDate))?null:date2);
			List<Quotation> quotations = this.quotationLogic.findQuotations(parseValue(scmCocName),parseValue(hsCode),begineffectDate==null?null:date,endeffectDate==null?null:date2, -1, -1);
			List<Material> mlist = materLogic.findAllMaterialInfo(hsCode, null, -1, -1);
			List<Scmcoc> scmcocs = quotationLogic.findAll();
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
			List<Material> materData = this.materLogic.findMaterialById(idarr);
			List<Quotation> list = new ArrayList<Quotation>();
			try{
			Scmcoc coc = quotationLogic.findById(scmid);
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
	
	private void saveQuotation(List<Quotation> list){
		if(null!=list && list.size()>0){
			this.quotationLogic.saveQuotations(list);
		}
	}
	
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
						Quotation quotation = this.quotationLogic.findQuotationById(className, contents.get(0));
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
						out = response.getWriter();
						response.setContentType("application/text");
						response.setCharacterEncoding("UTF-8");
						if("".equals(msg)){
							quotation.setPrice(contents.get(1)==null||"".equals(contents.get(1))||"".equals(contents.get(1).trim())||"null".equals(contents.get(1).trim())?null:Double.parseDouble(contents.get(1).trim()));//单价
							System.out.println(contents.get(2).trim());
							quotation.setEffectDate(contents.get(2)==null||"".equals(contents.get(2))||"".equals(contents.get(2).trim())||"null".equals(contents.get(2).trim())?null:new SimpleDateFormat("yyyy-MM-dd").parse(contents.get(2).trim()));//生效日期
							quotation.setNote((contents.get(3)==null||"".equals(contents.get(3))||"".equals(contents.get(3).trim())||"null".equals(contents.get(3).trim())?null:parseValue(contents.get(3).trim())));//备注
							editData.add(quotation);
							this.saveQuotation(editData);
							this.findQuotations();
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
	
	private Integer pageCount(Integer maxIndex,Integer dataTotal){
		pageNums = (dataTotal / DEFAULT_PAGESIZE) + (dataTotal % DEFAULT_PAGESIZE > 0 ? 1 : 0); // 总页数
		if(pageNums==0){
			pageNums+=1;
		}
		return pageNums;
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




	

}
