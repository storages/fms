package com.fms.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fms.base.action.BaseAction;
import com.fms.commons.AppBillStatus;
import com.fms.commons.CommonConstant;
import com.fms.core.entity.AclUser;
import com.fms.core.entity.AppBillHead;
import com.fms.core.entity.AppBillItem;
import com.fms.core.entity.Material;
import com.fms.core.entity.Quotation;
import com.fms.core.entity.Scmcoc;
import com.fms.logic.AppBillLogic;
import com.fms.logic.MaterialLogic;
import com.fms.logic.QuotationLogic;
import com.fms.logic.ScmcocLogic;
import com.fms.utils.AjaxResult;
import com.google.gson.Gson;
import com.url.ajax.json.JSONObject;

public class AppBillAction extends BaseAction {

	/**
	 * 申请单表头表体Action
	 */
	private static final long serialVersionUID = 1L;

	protected AppBillLogic appBillLogic;
	protected MaterialLogic materLogic;
	protected ScmcocLogic scmLogic;
	protected QuotationLogic quotationLogic;
	/********* 搜索条件 ***********/
	protected String appNo;// 申请单号码
	protected String beginappDate;// 申请日期（开始）
	protected String endappDate;// 申请日期（结束）
	protected String appStatus;// 申请状态

	/********* 分页用的属性 ***********/
	private Integer dataTotal;// 总记录数
	private String currIndex;// 当前页码
	private String maxIndex;// 每页显示最多条数
	private Integer pageNums;// 共有多少页
	private String className = "AppBillItem";// 表名称
	private static final Integer DEFAULT_PAGESIZE = 12;

	/********* 其它属性 ***********/
	protected String ids;// 物料id数组
	protected String scmid;// 供应商id
	protected String hid;// 申请单表头id
	protected String verify;// 审批是否通过标记：2表示：通过    3表示不通过

	public String getVerify() {
		return verify;
	}

	public void setVerify(String verify) {
		this.verify = verify;
	}

	/**
	 * 前台传来的json格式字符串
	 */
	protected String jsonstr;

	public String getJsonstr() {
		return jsonstr;
	}

	public void setJsonstr(String jsonstr) {
		this.jsonstr = jsonstr;
	}

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

	public QuotationLogic getQuotationLogic() {
		return quotationLogic;
	}

	public void setQuotationLogic(QuotationLogic quotationLogic) {
		this.quotationLogic = quotationLogic;
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
	 * 
	 * @return
	 */
	public String toAppBillPage() {
		return this.SUCCESS;
	}

	/**
	 * 查找符合条件的申请单信息
	 * 
	 * @return
	 */
	public String findAppBillHeads() {
		try {
			beginappDate = "".equals(beginappDate) ? null : beginappDate;
			endappDate = "".equals(endappDate) ? null : endappDate;
			Date date = null;
			if (null != beginappDate) {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(beginappDate);
			}
			Date date2 = null;
			if (null != endappDate) {
				date2 = new SimpleDateFormat("yyyy-MM-dd").parse(endappDate);
			}
			Integer curr = (null == currIndex || "".equals(currIndex)) ? 1
					: Integer.parseInt(currIndex);// 当前第几页
			Integer max = (null == maxIndex || "".equals(maxIndex)) ? 1
					: Integer.parseInt(currIndex);// 每页最多显示条数
			dataTotal = this.appBillLogic.findDataCount(appNo,
					(beginappDate == null || "".equals(beginappDate)) ? null
							: date, (endappDate == null || ""
							.equals(endappDate)) ? null : date2, appStatus);
			AclUser user = (AclUser) this.session.get(CommonConstant.LOGINUSER);
			if(user.getUserFlag().equals("P")){
				if(null==appStatus){
					appStatus = "-1";
				}
			}else{
				if(null==appStatus){
					appStatus = AppBillStatus.APPROVALING;
				}
			}
			List<AppBillHead> heads = this.appBillLogic.findAppBillHeads(appNo,
					(beginappDate == null || "".equals(beginappDate)) ? null
							: date, (endappDate == null || ""
							.equals(endappDate)) ? null : date2, appStatus,
					(curr - 1) * DEFAULT_PAGESIZE, DEFAULT_PAGESIZE);
			List<Material> mlist = materLogic.findAllMaterialInfo(null, null,-1, -1);
			List<Scmcoc> scmcocs = scmLogic.findAllScmcoc(false, null, -1, -1);
			this.request.put("u", user);
			this.request.put("scmcocs", scmcocs);
			this.request.put("heads", heads);
			this.request.put("mlist", mlist);
			this.request.put("currIndex", curr);
			this.request.put("maxIndex", max);
			this.request.put("pageNums", pageCount(max, dataTotal));
			this.request.put("appNo", appNo);
			this.request.put("beginappDate", beginappDate == null ? null
					: beginappDate);
			this.request.put("endappDate", endappDate == null ? null
					: endappDate);
			// findMaterial();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;

	}

	private Integer pageCount(Integer maxIndex, Integer dataTotal) {
		pageNums = (dataTotal / DEFAULT_PAGESIZE)
				+ (dataTotal % DEFAULT_PAGESIZE > 0 ? 1 : 0); // 总页数
		if (pageNums == 0) {
			pageNums += 1;
		}
		return pageNums;
	}

	/**
	 * 新增申请单表头
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String addAppBillHead() {
		try {
			AppBillHead head = new AppBillHead();
			AclUser user = (AclUser) this.session.get("u");
			head.setSubmitUser(user);
			List<AppBillHead> list = new ArrayList<AppBillHead>();
			list.add(this.appBillLogic.saveAppBillHead(head));
			this.request.put("heads", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.SUCCESS;
	}

	public String findItemByHid() {
		if (ids != null && !"".equals(ids)) {

			try {
				beginappDate = "".equals(beginappDate) ? null : beginappDate;
				endappDate = "".equals(endappDate) ? null : endappDate;
				Date date = null;
				if (null != beginappDate) {
					date = new SimpleDateFormat("yyyy-MM-dd")
							.parse(beginappDate);
				}
				Date date2 = null;
				if (null != endappDate) {
					date2 = new SimpleDateFormat("yyyy-MM-dd").parse(endappDate);
				}

				List<AppBillItem> items = this.appBillLogic.findItemByHid(ids,(beginappDate == null || "".equals(beginappDate)) ? null : date,
								(endappDate == null || "".equals(endappDate)) ? null: date2, appStatus);
				List<Material> mlist = materLogic.findAllMaterialInfo(null,null, -1, -1);
				List<Scmcoc> scmcocs = scmLogic.findAllScmcoc(false, null, -1,-1);
				this.request.put("scmcocs", scmcocs);
				this.request.put("items", items);
				this.request.put("mlist", mlist);
				this.request.put("his", ids);
				this.request.put("beginappDate", beginappDate == null ? null
						: beginappDate);
				this.request.put("endappDate", endappDate == null ? null
						: endappDate);
				this.request.put("appStatus", appStatus);

			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return "item";
	}

	public String findMaterialByIds() {
		if (ids != null && !"".equals(ids)) {
			String[] matrIds = ids.split("/");
			if (null != matrIds && matrIds.length > 0) {
				List<AppBillItem> itemList = new ArrayList<AppBillItem>();
				List<Material> list = this.materLogic.findMaterialById(matrIds);
				Scmcoc scm = this.scmLogic.findScmcocById(scmid);
				AppBillHead head = this.appBillLogic.findHeadById(hid);
				for (Material m : list) {
					try {
						Quotation q = this.quotationLogic
								.findQuotationByCondention(m, scm);
						AppBillItem item = new AppBillItem();
						item.setAppDate(new Date());
						item.setHead(head);
						item.setMaterial(m);
						item.setScmcoc(scm);
						item.setPrice(q == null ? 0d : q.getPrice());
						itemList.add(item);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				itemList = this.appBillLogic.betchSaveAppBillItem(itemList);
				List<AppBillItem> items = this.appBillLogic.findItemByHid(hid,
						null, null, null);
				List<Material> mlist = materLogic.findAllMaterialInfo(null,
						null, -1, -1);
				List<Scmcoc> scmcocs = scmLogic.findAllScmcoc(false, null, -1,
						-1);
				this.request.put("scmcocs", scmcocs);
				this.request.put("items", items);
				this.request.put("mlist", mlist);
				this.request.put("his", ids);
			}
		}
		return "item";
	}

	/**
	 * 修改申请单
	 */
	public void editAppBillItem() {
		PrintWriter out = null;
		AjaxResult result = new AjaxResult();
		List<List<String>> list = this.parseJsonArr(jsonstr);
		List<AppBillItem> editData = new ArrayList<AppBillItem>();
		if (null != list && list.size() > 0) {
			for (List<String> ldata : list) {
				List<String> contents = ldata;
				if (null != contents && contents.size() > 0) {
					try {
						String msg = "";
						AppBillItem item = this.appBillLogic
								.findItemById(contents.get(0));
						if (contents.get(1) == null
								|| "".equals(contents.get(1))
								|| "".equals(contents.get(1).trim())
								|| "null".equals(contents.get(1).trim())) {
							msg = "申请数量不能为空";
						} else if (!isNumeric(contents.get(1).trim())) {
							msg = "申请数量必须是数字";
						}
						if (contents.size() < 4) {
							contents.add(null);
						}
						out = response.getWriter();
						response.setContentType("application/text");
						response.setCharacterEncoding("UTF-8");
						if ("".equals(msg)) {
							item.setTotalQty(contents.get(1) == null
									|| "".equals(contents.get(1))
									|| "".equals(contents.get(1).trim())
									|| "null".equals(contents.get(1).trim()) ? null
									: Double.parseDouble(contents.get(1).trim()));// 申请数量
							item.setNote((contents.get(2) == null
									|| "".equals(contents.get(2))
									|| "".equals(contents.get(2).trim())
									|| "null".equals(contents.get(2).trim()) ? null
									: parseValue(contents.get(2).trim())));// 备注
							item.setAmount(item.getPrice()
									* (item.getTotalQty() == null ? 0d : item
											.getTotalQty()));
							editData.add(item);
							result.setSuccess(true);
							this.appBillLogic.betchSaveAppBillItem(editData);
						} else {
							result.setSuccess(false);
							result.setMsg(msg);
						}
					} catch (Exception e) {
						e.printStackTrace();
						/*
						 * result.setMsg(e.getMessage().toString()); JSONObject
						 * json = new JSONObject(result);
						 * out.println(json.toString()); out.flush();
						 */
					}
				}
			}
			JSONObject json = new JSONObject(result);
			out.println(json.toString());
			out.flush();
		}
	}

	/**
	 * 删除申请单表体
	 * 
	 * @return
	 */
	public void deleteAppBillItem() {
		if (null != ids && !"".equals(ids)) {
			String[] idArr = ids.split(",");
			if (idArr != null && idArr.length > 0) {
				PrintWriter out = null;
				AjaxResult result = new AjaxResult();
				try {
					out = response.getWriter();
					response.setContentType("application/text");
					response.setCharacterEncoding("UTF-8");
					this.appBillLogic.delAppBillItem(idArr);
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

	/**
	 * 删除申请单表头
	 * 
	 * @return
	 */
	public void deleteAppBillHead() {
		if (null != ids && !"".equals(ids)) {
			String[] idArr = ids.split(",");
			if (idArr != null && idArr.length > 0) {
				PrintWriter out = null;
				AjaxResult result = new AjaxResult();
				try {
					out = response.getWriter();
					response.setContentType("application/text");
					response.setCharacterEncoding("UTF-8");
					this.appBillLogic.delAppBillHead(idArr);
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

	/**
	 * 提交申请
	 */
	public void submitApp() {
		boolean flag = true;
		String msg = "错误信息：\n";// 错误信息
		if (null != ids && !"".equals(ids)) {
			String[] idArr = ids.split(",");
			if (idArr != null && idArr.length > 0) {
				PrintWriter out = null;
				AjaxResult result = new AjaxResult();
				try {
					out = response.getWriter();
					response.setContentType("application/text");
					response.setCharacterEncoding("UTF-8");
					for (int i = 0; i < idArr.length; i++) {
						List<AppBillItem> list = this.appBillLogic
								.findItemByHid(idArr[i], null, null, null);
						if (null == list || list.size() <= 0) {
							AppBillHead head = this.appBillLogic
									.findHeadById(idArr[i]);
							msg += "    申请单【" + head.getAppNo()
									+ "】不能包含空的申请明细\n";
							result.setMsg(msg);
							flag = false;
							result.setSuccess(flag);
						} else {
							String err = checkData(list);
							if ("".equals(err)) {
								flag = true;
							} else {
								msg += err;
								result.setMsg(msg);
								flag = false;
								result.setSuccess(flag);
							}
						}

					}
					if (flag) {
						this.appBillLogic.submitApp(idArr);
						result.setSuccess(true);
					}
					JSONObject json = new JSONObject(result);
					out.println(json.toString());
					out.flush();
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
					result.setSuccess(false);
					result.setMsg("数据提交失败！");
					JSONObject json = new JSONObject(result);
					out.println(json.toString());
					out.flush();
					out.close();
				}
			}
		}
	}

	private String checkData(List<AppBillItem> list) {
		String msg = "";
		if (null != list && list.size() > 0) {
			AppBillHead head = this.appBillLogic.findHeadByItemId(list.get(0)
					.getId());
			if (!AppBillStatus.UNAPPLY.equals(head.getAppStatus())) {
				msg += "    申请单【" + head.getAppNo() + "】已申请过了;\n";

			}
			if (list.get(0).getTotalQty() == null
					|| list.get(0).getTotalQty() == 0) {
				msg += "    申请单【" + head.getAppNo() + "】明细中数量不能为空;\n";
			}
		}
		return msg;
	}

	/**
	 * 审批
	 */
	public void verifyBill() {
		if (ids != null && !"".equals(ids)) {
			String[] idArr = ids.split(",");
			if (idArr != null && idArr.length > 0) {
				PrintWriter out = null;
				AjaxResult result = new AjaxResult();
				try {
					out = response.getWriter();
					response.setContentType("application/text");
					response.setCharacterEncoding("UTF-8");
					List<AppBillItem> list = this.appBillLogic
							.findItemByIds(idArr);
					if (null != list && list.size() > 0) {
						// 批量审批
					}
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					
				}
			}

		}
	}

	/**
	 * 显示所有选中要审批的表体数据
	 * @return
	 */
	public String verifyList() {
		if (ids != null && !"".equals(ids)) {
			String[] idArr = ids.split(",");
			if (idArr != null && idArr.length > 0) {
				List<AppBillItem> list = this.appBillLogic.findItemByIds(idArr);
				AclUser user = (AclUser) this.session.get(CommonConstant.LOGINUSER);
				this.request.put("u", user);
				this.request.put("items", list);
			}
		}
		return "item";
	}
	
	/**
	 * 开始审批数据
	 */
	public void verifyInfo(){
		if (ids != null && !"".equals(ids)) {
			String[] idArr = ids.split(",");
			if (idArr != null && idArr.length > 0) {
				AjaxResult result = new AjaxResult();
				try {
					AclUser user = (AclUser) this.session.get(CommonConstant.LOGINUSER);
					List<AppBillItem> data = this.appBillLogic.verifyItem(idArr,verify,user);
					PrintWriter out = null;
					out = response.getWriter();
					response.setContentType("application/text");
					response.setCharacterEncoding("UTF-8");
					if(null!=data && data.size()>0){
						result.setMsg("审批完成");
						result.setSuccess(true);
						JSONObject json = new JSONObject(result);
						out.println(json.toString());
						out.flush();
						out.close();
					}
					
				} catch (IOException e) {
					e.printStackTrace();
					result.setMsg("审批失败"+e.getMessage());
					result.setSuccess(false);
					JSONObject json = new JSONObject(result);
					out.println(json.toString());
					out.flush();
					out.close();
				}
			}
		}
	}
}
