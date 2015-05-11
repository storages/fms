package com.fms.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fms.base.action.BaseAction;
import com.fms.core.entity.PurchaseBill;
import com.fms.core.entity.PurchaseItem;
import com.fms.logic.PurchaseBillLogic;
import com.fms.utils.AjaxResult;
import com.url.ajax.json.JSONObject;

/**
 * 采购单Action
 * 
 * @author Administrator
 * 
 */
public class PurchaseAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected PurchaseBillLogic purchaseBillLogic;

	public PurchaseBillLogic getPurchaseBillLogic() {
		return purchaseBillLogic;
	}

	public void setPurchaseBillLogic(PurchaseBillLogic purchaseBillLogic) {
		this.purchaseBillLogic = purchaseBillLogic;
	}

	/********* 分页用的属性 ***********/
	private Integer dataTotal;// 总记录数
	private String currIndex;// 当前页码
	private String maxIndex;// 每页显示最多条数
	private Integer pageNums;// 共有多少页
	private String className = "PurchaseBill";// 表名称
	private static final Integer DEFAULT_PAGESIZE = 15;

	protected String appBillNo;// 采购 单号码
	protected String purBeginDate;// 采购单开始日期
	protected String purEndDate;// 采购单结束日期

	protected String hid;// 采购单表头id
	protected String jsonstr;// 修改数据的json字符串(数组：id,修改的值)
	protected String ids;// 审批时传递过来的多个id
	protected String flag;// 前台是生效(true)还是回卷(false)
	protected String hsCode;// 物料编号

	/**
	 * 获取采购单表头
	 * 
	 * @return
	 */
	public String findPurchaseHeads() {
		try {
			purBeginDate = "".equals(purBeginDate) ? null : purBeginDate;
			purEndDate = "".equals(purEndDate) ? null : purEndDate;
			Date date = null;
			if (null != purBeginDate) {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(purBeginDate);
			}
			Date date2 = null;
			if (null != purEndDate) {
				date2 = new SimpleDateFormat("yyyy-MM-dd").parse(purEndDate);
			}
			Integer curr = (null == currIndex || "".equals(currIndex)) ? 1 : Integer.parseInt(currIndex);// 当前第几页
			Integer max = (null == maxIndex || "".equals(maxIndex)) ? 1 : Integer.parseInt(currIndex);// 每页最多显示条数
			dataTotal = this.purchaseBillLogic.getDataCount(getLoginUser(), appBillNo, (purBeginDate == null || "".equals(purBeginDate)) ? null : date,
					(purEndDate == null || "".equals(purEndDate)) ? null : date2);

			List<PurchaseBill> headList = this.purchaseBillLogic.findPurchaseHeads(getLoginUser(), appBillNo, (purBeginDate == null || "".equals(purBeginDate)) ? null : date,
					(purEndDate == null || "".equals(purEndDate)) ? null : date2, (curr - 1) * DEFAULT_PAGESIZE, DEFAULT_PAGESIZE);

			this.request.put("headList", headList);
			this.request.put("currIndex", curr);
			this.request.put("maxIndex", max);
			this.request.put("pageNums", pageCount(max, dataTotal));
			this.request.put("appNo", appBillNo);
			this.request.put("purBeginDate", purBeginDate == null ? null : purBeginDate);
			this.request.put("purEndDate", purEndDate == null ? null : purEndDate);
			System.out.println("---------------");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return this.SUCCESS;
	}

	/**
	 * 修改数据
	 */
	public void editheadData() {
		PrintWriter out = null;
		AjaxResult result = new AjaxResult();
		List<List<String>> list = this.parseJsonArr(jsonstr);
		List<PurchaseBill> editData = new ArrayList<PurchaseBill>();
		if (null != list && list.size() > 0) {
			String msg = "";
			for (List<String> ldata : list) {
				List<String> contents = ldata;
				if (null != contents && contents.size() > 0) {
					try {
						PurchaseBill head = this.purchaseBillLogic.findPurchaseById(getLoginUser(), contents.get(0));
						if (contents.size() < 2) {
							contents.add(null);
						}
						head.setSpecialNote(contents.get(1));
						editData.add(head);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			try {
				out = response.getWriter();
				response.setContentType("application/text");
				response.setCharacterEncoding("UTF-8");
				if ("".equals(msg)) {
					result.setSuccess(true);
					this.purchaseBillLogic.saveOrUpdate(getLoginUser(), editData);
				} else {
					result.setSuccess(false);
					result.setMsg(msg);
				}
				JSONObject json = new JSONObject(result);
				out.println(json.toString());
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private Integer pageCount(Integer maxIndex, Integer dataTotal) {
		pageNums = (dataTotal / DEFAULT_PAGESIZE) + (dataTotal % DEFAULT_PAGESIZE > 0 ? 1 : 0); // 总页数
		if (pageNums == 0) {
			pageNums += 1;
		}
		return pageNums;
	}

	/**
	 * 跳转到详细清单
	 * 
	 * @return
	 */
	public String detail() {
		try {
			List<PurchaseItem> itemList = this.purchaseBillLogic.findItemByHid(getLoginUser(), hid, hsCode);
			this.request.put("itemList", itemList);
			this.request.put("hid", hid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "detail";
	}

	/**
	 * 生效/回卷
	 */
	public void purchEffect() {
		try {
			PrintWriter out = response.getWriter();
			response.setContentType("application/text");
			response.setCharacterEncoding("UTF-8");
			AjaxResult result = new AjaxResult();
			if (ids != null && !"".equals(ids)) {
				String[] idArr = ids.split(",");
				// 先验证该采购单是否有详细清单
				for (int i = 0; i < idArr.length; i++) {
					List<PurchaseItem> items = this.purchaseBillLogic.findItemByHid(getLoginUser(), idArr[i], null);
					if (null == items || items.size() == 0) {
						result.setMsg("你选择生效的采购单中没有详细清单!");
						result.setSuccess(false);
						JSONObject json = new JSONObject(result);
						out.println(json.toString());
						out.flush();
						return;
					}
				}
				Boolean isSuccess = this.purchaseBillLogic.purchEffect(getLoginUser(), idArr, Boolean.parseBoolean(flag));
				if (Boolean.TRUE.equals(isSuccess)) {
					result.setSuccess(true);
				} else {
					result.setSuccess(false);
				}
			}
			JSONObject json = new JSONObject(result);
			out.println(json.toString());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String savePurchaseItems() {
		try {
			if (StringUtils.isNotBlank(jsonstr)) {
				List<PurchaseItem> items = new ArrayList<PurchaseItem>();
				String[] dataArr = jsonstr.split(",");
				for (int i = 1; i < dataArr.length; i = i + 3) {
					PurchaseItem item = this.purchaseBillLogic.findPurchaseItemById(dataArr[i]);
					Date date = null;
					if (StringUtils.isNotBlank(dataArr[i + 1])) {
						date = SimpleDateFormat.getDateInstance().parse(dataArr[i + 1]);
						item.setDeliveryDate(date);
					}
					items.add(item);
				}
				items = this.purchaseBillLogic.betchSavePurchaseItems(getLoginUser(), items);
				List<PurchaseItem> itemList = this.purchaseBillLogic.findItemByHid(getLoginUser(), hid, null);
				this.request.put("itemList", itemList);
				this.request.put("hid", hid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "detail";
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

	public String getAppBillNo() {
		return appBillNo;
	}

	public void setAppBillNo(String appBillNo) {
		this.appBillNo = appBillNo;
	}

	public String getPurBeginDate() {
		return purBeginDate;
	}

	public void setPurBeginDate(String purBeginDate) {
		this.purBeginDate = purBeginDate;
	}

	public String getPurEndDate() {
		return purEndDate;
	}

	public void setPurEndDate(String purEndDate) {
		this.purEndDate = purEndDate;
	}

	public String getJsonstr() {
		return jsonstr;
	}

	public void setJsonstr(String jsonstr) {
		this.jsonstr = jsonstr;
	}

	public String getHid() {
		return hid;
	}

	public void setHid(String hid) {
		this.hid = hid;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getHsCode() {
		return hsCode;
	}

	public void setHsCode(String hsCode) {
		this.hsCode = hsCode;
	}

}
