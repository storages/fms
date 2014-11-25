package com.fms.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fms.base.action.BaseAction;
import com.fms.core.entity.AppBillHead;
import com.fms.core.entity.AppBillItem;
import com.fms.core.entity.Material;
import com.fms.core.entity.Scmcoc;
import com.fms.logic.AppBillLogic;
import com.fms.logic.MaterialLogic;
import com.fms.logic.ScmcocLogic;
import com.fms.utils.AjaxResult;
import com.google.gson.Gson;

public class AppBillAction extends BaseAction {

	/**
	 * 申请单表头表体Action
	 */
	private static final long serialVersionUID = 1L;
	
	protected AppBillLogic appBillLogic;
	protected MaterialLogic materLogic;
	protected ScmcocLogic scmLogic;
	/********* 搜索条件 ***********/
	protected String appNo;//申请单号码
	protected String beginappDate;//申请日期（开始）
	protected String endappDate;//申请日期（结束）
	protected String appStatus;//申请状态

	/********* 分页用的属性 ***********/
	private Integer dataTotal;// 总记录数
	private String currIndex;// 当前页码
	private String maxIndex;// 每页显示最多条数
	private Integer pageNums;// 共有多少页
	private String className = "AppBillItem";// 表名称
	private static final Integer DEFAULT_PAGESIZE = 10;
	
	/********* 其它属性 ***********/
	protected String ids;//物料id数组
	protected String scmid;//供应商id
	protected String hid;//申请单表头id
	
	
	public AppBillLogic getAppBillLogic() {
		return appBillLogic;
	}

	public void setAppBillLogic(AppBillLogic appBillLogic) {
		this.appBillLogic = appBillLogic;
	}
	
	public MaterialLogic getMaterLogic() {
		return materLogic;
	}

	public void setMaterLogic(MaterialLogic materLogic) {
		this.materLogic = materLogic;
	}

	public ScmcocLogic getScmLogic() {
		return scmLogic;
	}

	public void setScmLogic(ScmcocLogic scmLogic) {
		this.scmLogic = scmLogic;
	}

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public String getBeginappDate() {
		return beginappDate;
	}

	public void setBeginappDate(String beginappDate) {
		this.beginappDate = beginappDate;
	}

	public String getEndappDate() {
		return endappDate;
	}

	public void setEndappDate(String endappDate) {
		this.endappDate = endappDate;
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

	public String getAppStatus() {
		return appStatus;
	}

	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
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

	public String getHid() {
		return hid;
	}

	public void setHid(String hid) {
		this.hid = hid;
	}

	/**
	 * 跳转到申请单页面
	 * @return
	 */
	public String toAppBillPage(){
		return this.SUCCESS;
	}

	/**
	 * 查找符合条件的申请单信息
	 * 
	 * @return
	 */
	public String findAppBillHeads() {
		try {
			beginappDate = "".equals(beginappDate)?null:beginappDate;
			endappDate = "".equals(endappDate)?null:endappDate;
			Date date = null;
	        if(null!=beginappDate){
	        	date = new SimpleDateFormat("yyyy-MM-dd").parse(beginappDate); 
	        }
	        Date date2 = null;
	        if(null!=endappDate){
	        	date2 = new SimpleDateFormat("yyyy-MM-dd").parse(endappDate); 
	        }
			Integer curr = (null==currIndex || "".equals(currIndex))?1:Integer.parseInt(currIndex);//当前第几页
			Integer max = (null==maxIndex || "".equals(maxIndex))?1:Integer.parseInt(currIndex);//每页最多显示条数
			dataTotal = this.appBillLogic.findDataCount(appNo,(beginappDate==null||"".equals(beginappDate))?null:date,(endappDate==null||"".equals(endappDate))?null:date2,appStatus);
			List<AppBillHead> heads = this.appBillLogic.findAppBillHeads(appNo, (beginappDate==null||"".equals(beginappDate))?null:date,(endappDate==null||"".equals(endappDate))?null:date2,appStatus,(curr-1)*DEFAULT_PAGESIZE,DEFAULT_PAGESIZE);
			findItemByHid();
			List<Material> mlist = materLogic.findAllMaterialInfo(null, null, -1, -1);
			List<Scmcoc> scmcocs = scmLogic.findAllScmcoc(false, null, -1, -1);
			this.request.put("scmcocs", scmcocs);
			this.request.put("heads", heads);
			this.request.put("mlist", mlist);
			this.request.put("currIndex", curr);
			this.request.put("maxIndex", max);
			this.request.put("pageNums", pageCount(max, dataTotal));
			this.request.put("appNo", appNo);
			this.request.put("beginappDate", beginappDate==null?null:beginappDate);
			this.request.put("endappDate", endappDate==null?null:endappDate);
			//findMaterial();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;

	}
	
	private Integer pageCount(Integer maxIndex,Integer dataTotal){
		pageNums = (dataTotal / DEFAULT_PAGESIZE) + (dataTotal % DEFAULT_PAGESIZE > 0 ? 1 : 0); // 总页数
		if(pageNums==0){
			pageNums+=1;
		}
		return pageNums;
	}
	
	/**
	 * 新增申请单表头
	 * @return
	 */
	public String addAppBillHead(){
		AppBillHead head = new AppBillHead();
		List<AppBillHead> list = new ArrayList<AppBillHead>();
		list.add(this.appBillLogic.saveAppBillHead(head));
		this.request.put("heads",list);
		return this.SUCCESS;
	}
	

	
	public void findItemByHid(){
		if(ids!=null && !"".equals(ids)){
			List<AppBillItem> items = this.appBillLogic.findItemByHid(ids);
			this.request.put("items", items);
		}
	}
	
	public void findItemByHidToJson(){
		if(ids!=null && !"".equals(ids)){
			PrintWriter out = null;
			AjaxResult result = new AjaxResult();
			Gson gson = new Gson();
			try {
				out = response.getWriter();
				response.setContentType("application/text");
				response.setCharacterEncoding("UTF-8");
				List<AppBillItem> items = this.appBillLogic.findItemByHid(ids);
				result.setSuccess(true);
				result.setObj(items);
				result.setMsg("获取成功");
				String str = gson.toJson(result);
				out.print(str);
				out.flush();
			} catch (IOException e) {
				result.setSuccess(false);
				result.setMsg("获取失败");
				String str = gson.toJson(result);
				out.print(str);
				out.flush();
				e.printStackTrace();
			}
		}
	}
	
	public void findMaterialByIds(){
		if(ids!=null && !"".equals(ids)){
			String [] matrIds = ids.split("/");
			if(null!=matrIds && matrIds.length>0){
				List<AppBillItem> itemList = new ArrayList<AppBillItem>();
				List<Material> list = this.materLogic.findMaterialById(matrIds);
				Scmcoc scm = this.scmLogic.findScmcocById(scmid);
				AppBillHead head = this.appBillLogic.findHeadById(hid);
				for(Material m:list){
					AppBillItem item = new AppBillItem();
					item.setAppDate(new Date());
					item.setHead(head);
					item.setMaterial(m);
					itemList.add(item);
				}
				itemList = this.appBillLogic.betchSaveAppBillItem(itemList);
			}
		}
	}
}
