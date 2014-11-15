package com.fms.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fms.base.action.BaseAction;
import com.fms.core.entity.Material;
import com.fms.core.entity.Quotation;
import com.fms.core.entity.Scmcoc;
import com.fms.logic.MaterialLogic;
import com.fms.logic.QuotationLogic;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;


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
			/*SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
	        String str = formatDate.format(begineffectDate);
	        String str2 = formatDate.format(endeffectDate);*/
			Date date = null;
	        if(null!=begineffectDate){
	        	date = new SimpleDateFormat("yyyy-MM-dd").parse(begineffectDate); 
	        }
	        Date date2 = null;
	        if(null!=endeffectDate){
	        	date2 = new SimpleDateFormat("yyyy-MM-dd").parse(endeffectDate); 
	        }
	        /*Date time1 = null;
	        Date time2 = null;
	        try {
	            time1 = formatDate.parse(str);
	            time2 = formatDate.parse(str2);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }*/
			Integer curr = (null==currIndex || "".equals(currIndex))?1:Integer.parseInt(currIndex);//当前第几页
			Integer max = (null==maxIndex || "".equals(maxIndex))?1:Integer.parseInt(currIndex);//每页最多显示条数
			dataTotal = this.quotationLogic.findDataCount(parse(scmCocName),parse(hsCode),(begineffectDate==null||"".equals(begineffectDate))?null:date,(endeffectDate==null||"".equals(endeffectDate))?null:date2);
			List<Quotation> quotations = this.quotationLogic.findQuotations(parse(scmCocName),parse(hsCode),begineffectDate==null?null:date,endeffectDate==null?null:date2, -1, -1);
			List<Material> mlist = materLogic.findAllMaterialInfo(hsCode, null, -1, -1);
			List<Scmcoc> scmcocs = quotationLogic.findAll();
			this.request.put("scmcocs", scmcocs);
			this.request.put("quotations", quotations);
			this.request.put("mlist", mlist);
			this.request.put("currIndex", curr);
			this.request.put("maxIndex", max);
			this.request.put("pageNums", pageCount(max, dataTotal));
			this.request.put("scmCocName", parse(scmCocName));
			this.request.put("hsCode", parse(hsCode));
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
