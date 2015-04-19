package com.fms.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fms.base.action.BaseAction;
import com.fms.commons.AppBillStatus;
import com.fms.commons.CommonConstant;
import com.fms.core.entity.AclUser;
import com.fms.core.entity.AppBillHead;
import com.fms.core.entity.AppBillItem;
import com.fms.core.entity.Material;
import com.fms.core.entity.PurchaseItem;
import com.fms.core.entity.Quotation;
import com.fms.core.entity.Scmcoc;
import com.fms.logic.AppBillLogic;
import com.fms.logic.MaterialLogic;
import com.fms.logic.PurchaseBillLogic;
import com.fms.logic.QuotationLogic;
import com.fms.logic.ScmcocLogic;
import com.fms.utils.AjaxResult;
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
	protected PurchaseBillLogic purchaseBillLogic;
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
	protected String verify;// 审批是否通过标记：2表示：通过 3表示不通过
	protected String noPassReason;// 审批不通过原因
	protected String orderNo;// 订单号

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

	public String getNoPassReason() {
		return noPassReason;
	}

	public void setNoPassReason(String noPassReason) {
		this.noPassReason = noPassReason;
	}

	public PurchaseBillLogic getPurchaseBillLogic() {
		return purchaseBillLogic;
	}

	public void setPurchaseBillLogic(PurchaseBillLogic purchaseBillLogic) {
		this.purchaseBillLogic = purchaseBillLogic;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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
			AclUser user = (AclUser) this.session.get(CommonConstant.LOGINUSER);
			if (user.getUserFlag().equals("P")) {
				if (null == appStatus) {
					appStatus = "-1";
				}
			} else {
				if (null == appStatus) {
					appStatus = AppBillStatus.APPROVALING;
				}
			}
			Integer curr = (null == currIndex || "".equals(currIndex)) ? 1 : Integer.parseInt(currIndex);// 当前第几页
			Integer max = (null == maxIndex || "".equals(maxIndex)) ? 1 : Integer.parseInt(currIndex);// 每页最多显示条数
			List<AppBillHead> heads = findApplyBillHeads(curr);
			List<Material> mlist = materLogic.findAllMaterialInfo(getLoginUser(), null, null, -1, -1);
			List<Scmcoc> scmcocs = scmLogic.findAllScmcoc(getLoginUser(), false, null, -1, -1);
			this.request.put("u", user);
			this.request.put("scmcocs", scmcocs);
			this.request.put("heads", heads);
			this.request.put("mlist", mlist);
			this.request.put("currIndex", curr);
			this.request.put("maxIndex", max);
			this.request.put("pageNums", pageCount(max, dataTotal));
			this.request.put("appNo", appNo);
			this.request.put("beginappDate", beginappDate == null ? null : beginappDate);
			this.request.put("endappDate", endappDate == null ? null : endappDate);
			// findMaterial();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;

	}

	private Integer pageCount(Integer maxIndex, Integer dataTotal) {
		pageNums = (dataTotal / DEFAULT_PAGESIZE) + (dataTotal % DEFAULT_PAGESIZE > 0 ? 1 : 0); // 总页数
		if (pageNums == 0) {
			pageNums += 1;
		}
		return pageNums;
	}

	private List<AppBillHead> findApplyBillHeads(Integer curr) {
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

			dataTotal = this.appBillLogic.findDataCount(getLoginUser(), appNo, (beginappDate == null || "".equals(beginappDate)) ? null : date, (endappDate == null || "".equals(endappDate)) ? null
					: date2, appStatus);

			List<AppBillHead> heads = this.appBillLogic.findAppBillHeads(getLoginUser(), appNo, (date == null || "".equals(date)) ? null : date, (date2 == null || "".equals(date2)) ? null : date2,
					appStatus, (curr - 1) * DEFAULT_PAGESIZE, DEFAULT_PAGESIZE);
			return heads;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
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
			Integer curr = (null == currIndex || "".equals(currIndex)) ? 1 : Integer.parseInt(currIndex);// 当前第几页
			List<AppBillHead> list = new ArrayList<AppBillHead>();
			this.appBillLogic.saveAppBillHead(getLoginUser(), head);
			list = findApplyBillHeads(curr);
			this.request.put("heads", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.SUCCESS;
	}

	/**
	 * 保存申请单表头
	 */
	public void saveApplyBillHead() {
		AjaxResult result = null;
		PrintWriter out = null;
		List<AppBillHead> heads = new ArrayList<AppBillHead>();
		try {
			response.setContentType("application/text");
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			result = new AjaxResult();
			if (null == jsonstr || StringUtils.isBlank(jsonstr)) {
				result.setMsg("订单号不能为空!");
				result.setSuccess(false);
			} else {
				List<List<String>> list = this.parseJsonArr(jsonstr);
				for (List<String> ldata : list) {
					List contents = ldata;
					AppBillHead head = this.appBillLogic.findHeadById(this.getLoginUser(), contents.get(0).toString());
					orderNo = (StringUtils.isBlank(ldata.get(1)) || "empty".equals(ldata.get(1))) ? null : ldata.get(1).toString();
					head.setOrderNo(orderNo);
					heads.add(head);
				}
				this.appBillLogic.betchSaveAppBillHead(getLoginUser(), heads);
				result.setSuccess(true);
			}
			JSONObject json = new JSONObject(result);
			out.println(json.toString());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据申请单表头id查找表体
	 * 
	 * @return
	 */
	public String findItemByHid() {
		if (ids != null && !"".equals(ids)) {

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
				AclUser user = (AclUser) this.session.get(CommonConstant.LOGINUSER);
				List<AppBillItem> items = this.appBillLogic.findItemByHid(getLoginUser(), ids, (beginappDate == null || "".equals(beginappDate)) ? null : date,
						(endappDate == null || "".equals(endappDate)) ? null : date2, appStatus, user);
				List<Material> mlist = materLogic.findAllMaterialInfo(getLoginUser(), null, null, -1, -1);
				List<Scmcoc> scmcocs = scmLogic.findAllScmcoc(getLoginUser(), false, null, -1, -1);
				this.request.put("scmcocs", scmcocs);
				this.request.put("items", items);
				this.request.put("mlist", mlist);
				this.request.put("his", ids);
				this.request.put("beginappDate", beginappDate == null ? null : beginappDate);
				this.request.put("endappDate", endappDate == null ? null : endappDate);
				this.request.put("appStatus", appStatus);

			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return "item";
	}

	/**
	 * 检查是否可以新增
	 */
	public void checkStatus() {
		if (ids != null && !"".equals(ids)) {
			PrintWriter out = null;
			String err = "";
			AjaxResult result = new AjaxResult();
			try {
				out = response.getWriter();
				response.setContentType("application/text");
				response.setCharacterEncoding("UTF-8");

				AppBillHead head = this.appBillLogic.findHeadById(getLoginUser(), ids);
				String idsArr[] = ids.split("/");
				List<AppBillItem> items = this.appBillLogic.findItemByIds(getLoginUser(), idsArr);
				for (AppBillItem item : items) {
					if (item.getAppStatus().equals(AppBillStatus.APPROVED)) {
						err += "选择的申请单中包含已经审批通过，不能再次审批!";
					}
				}
				if (!err.trim().equals("")) {
					result.setMsg(err);
					result.setSuccess(false);
					JSONObject json = new JSONObject(result);
					out.println(json.toString());
					out.flush();
					out.close();
				} else if (head != null && (head.getAppStatus().equals(AppBillStatus.APPROVALING) || head.getAppStatus().equals(AppBillStatus.APPROVED))) {
					result.setSuccess(false);
					result.setMsg(head.getAppStatus().equals(AppBillStatus.APPROVALING) ? "选择的申请单在等待审批，不能再次操作!" : "选择的申请单已经审批通过，不能再次操作!");
					JSONObject json = new JSONObject(result);
					out.println(json.toString());
					out.flush();
					out.close();
				} else {
					result.setSuccess(true);
					JSONObject json = new JSONObject(result);
					out.println(json.toString());
					out.flush();
					out.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String findMaterialByIds() {
		if (ids != null && !"".equals(ids)) {
			hid = hid.split("/")[0];
			String[] matrIds = ids.split("/");
			if (null != matrIds && matrIds.length > 0) {
				AclUser user = (AclUser) this.session.get(CommonConstant.LOGINUSER);
				List<AppBillItem> itemList = new ArrayList<AppBillItem>();
				List<Material> list = this.materLogic.findMaterialById(getLoginUser(), matrIds);
				Scmcoc scm = this.scmLogic.findScmcocById(getLoginUser(), scmid);
				AppBillHead head = this.appBillLogic.findHeadById(getLoginUser(), hid);
				for (Material m : list) {
					try {
						Quotation q = this.quotationLogic.findQuotationByCondention(getLoginUser(), m, scm);
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
				itemList = this.appBillLogic.betchSaveAppBillItem(getLoginUser(), itemList);
				List<AppBillItem> items = this.appBillLogic.findItemByHid(getLoginUser(), hid, null, null, null, user);
				List<Material> mlist = materLogic.findAllMaterialInfo(getLoginUser(), null, null, -1, -1);
				List<Scmcoc> scmcocs = scmLogic.findAllScmcoc(getLoginUser(), false, null, -1, -1);
				this.request.put("scmcocs", scmcocs);
				this.request.put("items", items);
				this.request.put("mlist", mlist);
				if (null != itemList && itemList.size() > 0) {
					this.request.put("his", itemList.get(0).getHead().getId());
				}
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
						AppBillItem item = this.appBillLogic.findItemById(getLoginUser(), contents.get(0));
						if (contents.get(1) == null || "".equals(contents.get(1)) || "".equals(contents.get(1).trim()) || "null".equals(contents.get(1).trim())) {
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
							item.setTotalQty(contents.get(1) == null || "".equals(contents.get(1)) || "".equals(contents.get(1).trim()) || "null".equals(contents.get(1).trim()) ? null : Double
									.parseDouble(contents.get(1).trim()));// 申请数量
							item.setNote((contents.get(2) == null || "".equals(contents.get(2)) || "".equals(contents.get(2).trim()) || "null".equals(contents.get(2).trim()) ? null
									: parseValue(contents.get(2).trim())));// 备注
							item.setAmount(item.getPrice() * (item.getTotalQty() == null ? 0d : item.getTotalQty()));
							editData.add(item);
							result.setSuccess(true);
							this.appBillLogic.betchSaveAppBillItem(getLoginUser(), editData);
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
					this.appBillLogic.delAppBillItem(getLoginUser(), idArr);
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
					this.appBillLogic.delAppBillHead(getLoginUser(), idArr);
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
						List<AppBillItem> list = this.appBillLogic.findItemByHid(getLoginUser(), idArr[i], null, null, null, null);
						if (null == list || list.size() <= 0) {
							AppBillHead head = this.appBillLogic.findHeadById(getLoginUser(), idArr[i]);
							msg += "    申请单【" + head.getAppNo() + "】不能包含空的申请明细\n";
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
						this.appBillLogic.submitApp(getLoginUser(), idArr);
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
			AppBillHead head = this.appBillLogic.findHeadByItemId(getLoginUser(), list.get(0).getId());
			if (!AppBillStatus.UNAPPLY.equals(head.getAppStatus())) {
				msg += "    申请单【" + head.getAppNo() + "】已申请过了;\n";

			}
			if (list.get(0).getTotalQty() == null || list.get(0).getTotalQty() == 0) {
				msg += "    申请单【" + head.getAppNo() + "】明细中数量不能为空;\n";
			}
			if (StringUtils.isBlank(head.getOrderNo())) {
				msg += "    申请单【" + head.getAppNo() + "】订单号不能为空;\n";
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
					List<AppBillItem> list = this.appBillLogic.findItemByHeadIds(getLoginUser(), idArr);
					if (null != list && list.size() > 0) {
						// 批量审批
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {

				}
			}

		}
	}

	/**
	 * 显示所有选中要审批的表体数据
	 * 
	 * @return
	 */
	public String verifyList() {
		if (ids != null && !"".equals(ids)) {
			String[] idArr = ids.split(",");
			if (idArr != null && idArr.length > 0) {
				List<AppBillItem> list = this.appBillLogic.findItemByHeadIds(getLoginUser(), idArr);
				AclUser user = (AclUser) this.session.get(CommonConstant.LOGINUSER);
				this.request.put("u", user);
				this.request.put("items", list);
				this.request.put("his", ids);
			}
		}
		return "item";
	}

	/**
	 * 开始审批数据
	 */
	public void verifyInfo() {
		if (ids != null && !"".equals(ids)) {
			String[] idArr = ids.split(",");
			if (idArr != null && idArr.length > 0) {
				AjaxResult result = new AjaxResult();
				try {
					AclUser user = (AclUser) this.session.get(CommonConstant.LOGINUSER);
					List<AppBillItem> data = this.appBillLogic.verifyItem(getLoginUser(), idArr, verify, user,
							(noPassReason == null || "".equals(noPassReason.trim()) ? "" : this.parseValue(noPassReason)));
					PrintWriter out = null;
					out = response.getWriter();
					response.setContentType("application/text");
					response.setCharacterEncoding("UTF-8");
					if (null != data && data.size() > 0) {
						result.setMsg("审批完成");
						result.setSuccess(true);
						JSONObject json = new JSONObject(result);
						out.println(json.toString());
						out.flush();
						out.close();
					}

				} catch (IOException e) {
					e.printStackTrace();
					result.setMsg("审批失败" + e.getMessage());
					result.setSuccess(false);
					JSONObject json = new JSONObject(result);
					out.println(json.toString());
					out.flush();
					out.close();
				}
			}
		}
	}

	/**
	 * 撤销审批
	 */
	public void cancelVerify() {
		if (ids != null && !"".equals(ids)) {
			String[] idArr = ids.split(",");
			if (idArr != null && idArr.length > 0) {
				AjaxResult result = new AjaxResult();
				String err = "";
				try {
					AclUser user = (AclUser) this.session.get(CommonConstant.LOGINUSER);
					List<AppBillHead> data = this.appBillLogic.findHeadsByHeadIds(getLoginUser(), idArr);
					PrintWriter out = null;
					out = response.getWriter();
					response.setContentType("application/text");
					response.setCharacterEncoding("UTF-8");
					String[] appBillNos = new String[data.size()];
					for (int i = 0; i < data.size(); i++) {
						appBillNos[i] = data.get(i).getAppNo();
					}
					List<PurchaseItem> purchaseItems = this.purchaseBillLogic.findBillItemByAppNo(getLoginUser(), appBillNos);
					for (PurchaseItem item : purchaseItems) {
						if (item.getIsBuy()) {
							err += "选择的撤销申请单中包含已经向供应商下单购买，不能撤销!";
						}
					}
					if (!"".equals(err)) {
						result.setMsg(err);
						result.setSuccess(false);
						JSONObject json = new JSONObject(result);
						out.println(json.toString());
						out.flush();
						out.close();
					} else {
						// 撤销审批
						// 1、删除采购单数据
						this.purchaseBillLogic.betchDelPurchase(getLoginUser(), purchaseItems);
						// 2、把申请单的状态修改成未审批状态
						boolean isBack = rollBackAppBillStateToInit(idArr);

						if (isBack) {
							err = "撤销成功!";
						} else {
							err = "撤销失败!";
						}
						result.setMsg(err);
						result.setSuccess(true);
						JSONObject json = new JSONObject(result);
						out.println(json.toString());
						out.flush();
						out.close();
					}
				} catch (Exception ex) {
					err = "撤销失败!";
					result.setMsg(err);
					result.setSuccess(false);
					JSONObject json = new JSONObject(result);
					out.println(json.toString());
					out.flush();
					out.close();
				}
			}
		}
	}

	/**
	 * 撤销审批
	 * 
	 * @param idArr
	 */
	private boolean rollBackAppBillStateToInit(String[] idArr) {
		boolean isBack = true;
		List<AppBillHead> heads = new ArrayList<AppBillHead>();
		try {
			List<AppBillItem> appItems = this.appBillLogic.findItemByHeadIds(getLoginUser(), idArr);
			for (AppBillItem item : appItems) {
				item.setAppStatus(AppBillStatus.UNAPPLY);// 申请单表体申请状态还原成未申请状态
				item.getHead().setAppStatus(AppBillStatus.UNAPPLY);// 申请单表头申请状态还原成未申请状态
				heads.add(item.getHead());
			}
			this.appBillLogic.betchSaveAppBillHead(getLoginUser(), heads);
			this.appBillLogic.betchSaveAppBillItem(getLoginUser(), appItems);
		} catch (Exception e) {
			isBack = false;
		}
		return isBack;
	}
}
