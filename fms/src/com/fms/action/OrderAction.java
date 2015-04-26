package com.fms.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;

import com.fms.base.action.BaseAction;
import com.fms.core.entity.OrderHead;
import com.fms.core.entity.OrderItem;
import com.fms.core.entity.Scmcoc;
import com.fms.core.vo.entity.TempEntity;
import com.fms.logic.OrderLogic;
import com.fms.logic.ScmcocLogic;
import com.fms.utils.AjaxResult;
import com.url.ajax.json.JSONObject;

/**
 * 订单Action
 * 
 * @author Administrator
 * 
 */
public class OrderAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected OrderLogic orderLogic;
	protected ScmcocLogic scmcocLogic;

	/********* 搜索条件 ***********/
	protected String orderNo;// 订单号码
	protected String beginPlaceOrderDate;// 下单日期（开始）
	protected String endPlaceOrderDate;// 下单日期（结束）
	protected String beginDeliveryDate;// 交货日期（开始）
	protected String endDeliveryDate;// 交货日期（结束）
	protected String scmCocName;// 客户名称
	protected String salesman;// 业务员

	protected String hsCode;// 商品编码
	protected String hsName;// 商品名称

	/********* 分页用的属性 ***********/
	private Integer dataTotal;// 总记录数
	private String currIndex;// 当前页码
	private String maxIndex;// 每页显示最多条数
	private Integer pageNums;// 共有多少页
	private String className = "OrderHead";// 表名称
	private static final Integer DEFAULT_PAGESIZE = 10;

	/********* 其它属性 ***********/
	private String hid;
	private String ids;
	private String editData;// 修改的数据

	/**
	 * 查找列表
	 * 
	 * @return
	 */
	public String findOrderPageList() {
		try {
			beginPlaceOrderDate = "".equals(beginPlaceOrderDate) ? null : beginPlaceOrderDate;
			endPlaceOrderDate = "".equals(endPlaceOrderDate) ? null : endPlaceOrderDate;
			beginDeliveryDate = "".equals(beginDeliveryDate) ? null : beginDeliveryDate;
			endDeliveryDate = "".equals(endDeliveryDate) ? null : endDeliveryDate;
			Date date = null;
			if (null != beginPlaceOrderDate) {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(beginPlaceOrderDate);
			}
			Date date2 = null;
			if (null != endPlaceOrderDate) {
				date2 = new SimpleDateFormat("yyyy-MM-dd").parse(endPlaceOrderDate);
			}
			Date date3 = null;
			if (null != beginDeliveryDate) {
				date3 = new SimpleDateFormat("yyyy-MM-dd").parse(beginDeliveryDate);
			}
			Date date4 = null;
			if (null != endDeliveryDate) {
				date4 = new SimpleDateFormat("yyyy-MM-dd").parse(endDeliveryDate);
			}
			Integer curr = (null == currIndex || "".equals(currIndex)) ? 1 : Integer.parseInt(currIndex);// 当前第几页
			Integer max = (null == maxIndex || "".equals(maxIndex)) ? 1 : Integer.parseInt(currIndex);// 每页最多显示条数

			dataTotal = this.orderLogic.findCount(orderNo, parseValue(scmCocName), salesman, date, date2, date3, date4);

			List<OrderHead> orderList = this.orderLogic.findOrderPageList(getLoginUser(), orderNo, parseValue(scmCocName), salesman, date, date2, date3, date4, (curr - 1) * DEFAULT_PAGESIZE,
					DEFAULT_PAGESIZE);
			List<Scmcoc> scmList = this.scmcocLogic.findAllScmcoc(getLoginUser(), true, null, -1, -1);
			List<TempEntity> salesmans = new ArrayList<TempEntity>();
			for (int i = 0; i < orderList.size(); i++) {
				salesmans.add(new TempEntity(i, orderList.get(i).getSalesman()));
			}
			this.request.put("orderList", orderList);
			this.request.put("scmList", scmList);
			this.request.put("hBeginPlaceOrderDate", beginPlaceOrderDate);
			this.request.put("hEndPlaceOrderDate", endPlaceOrderDate);
			this.request.put("hBeginDeliveryDate", beginDeliveryDate);
			this.request.put("hEndDeliveryDate", endDeliveryDate);
			this.request.put("scmCocName", parseValue(scmCocName));
			this.request.put("salesman", salesman);
			this.request.put("salesmans", salesmans);
			this.request.put("orderNo", orderNo);
			this.request.put("currIndex", curr);
			this.request.put("maxIndex", max);
			this.request.put("pageNums", pageCount(max, dataTotal));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.SUCCESS;
	}

	/**
	 * 新增订单表头
	 * 
	 * @return
	 */
	public String addOrder() {
		try {
			OrderHead ohead = this.orderLogic.addOrder(getLoginUser());
			this.findOrderPageList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.SUCCESS;
	}

	@SuppressWarnings("unused")
	public void saveOrder() {
		try {
			this.out = response.getWriter();
			AjaxResult result = new AjaxResult();
			response.setContentType("application/text");
			response.setCharacterEncoding("UTF-8");
			List<OrderHead> orderList = new ArrayList<OrderHead>();
			JSONArray jsonStr = JSONArray.fromObject(editData);
			Object[] arrData = jsonStr.toArray();
			OrderHead head = null;
			for (int i = 0; i < arrData.length; i = i + 6) {
				head = this.orderLogic.load(OrderHead.class, arrData[i].toString());
				Scmcoc scmcoc = this.scmcocLogic.findScmcocById(getLoginUser(), arrData[i + 1].toString());
				head.setScmcoc(scmcoc);
				if (null != arrData[i + 2].toString() && !"".equals(arrData[i + 2].toString())) {
					head.setPlaceOrderDate(new SimpleDateFormat("yyyy-MM-dd").parse(arrData[i + 2].toString()));
				}
				if (null != arrData[i + 3].toString() && !"".equals(arrData[i + 3].toString())) {
					head.setDeliveryDate(new SimpleDateFormat("yyyy-MM-dd").parse(arrData[i + 3].toString()));
				}
				if (StringUtils.isNotBlank(arrData[i + 4].toString())) {
					head.setSalesman(arrData[i + 4].toString());
				}
				orderList.add(head);
			}
			this.orderLogic.beatchSaveOrUpData(getLoginUser(), orderList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.setSuccess(true);
		JSONObject json = new JSONObject(result);
		out.println(json.toString());
		out.flush();
	}

	/**
	 * 删除
	 */
	public void delOrderHead() {
		try {
			this.out = response.getWriter();
			AjaxResult result = new AjaxResult();
			response.setContentType("application/text");
			response.setCharacterEncoding("UTF-8");
			if (ids != null && !"".equals(ids)) {
				String[] idArr = ids.split(",");
				this.orderLogic.delOrderHead(getLoginUser(), idArr);
				result.setSuccess(true);
				result.setMsg("删除成功!");
			}
			JSONObject json = new JSONObject(result);
			out.println(json.toString());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 显示订单详细清单(分页)
	 * 
	 * @return
	 */
	public String findOrderItems() {
		try {
			if (StringUtils.isNotBlank(hid)) {
				String[] hidArr = hid.split(",");
				Integer curr = (null == currIndex || "".equals(currIndex)) ? 1 : Integer.parseInt(currIndex);// 当前第几页
				Integer max = (null == maxIndex || "".equals(maxIndex)) ? 1 : Integer.parseInt(currIndex);// 每页最多显示条数
				dataTotal = this.orderLogic.countOrderItemsByHeadId(hidArr, hsCode, hsName);
				List<OrderItem> itemList = this.orderLogic.findOrderItemsByHeadId(hidArr, hsCode, parseValue(hsName), (curr - 1) * DEFAULT_PAGESIZE, DEFAULT_PAGESIZE);
				this.request.put("itemList", itemList);
				this.request.put("hid", hid);
				this.request.put("currIndex", curr);
				this.request.put("maxIndex", max);
				this.request.put("pageNums", pageCount(max, dataTotal));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "items";
	}

	private Integer pageCount(Integer maxIndex, Integer dataTotal) {
		pageNums = (dataTotal / DEFAULT_PAGESIZE) + (dataTotal % DEFAULT_PAGESIZE > 0 ? 1 : 0); // 总页数
		if (pageNums == 0) {
			pageNums += 1;
		}
		return pageNums;
	}

	public OrderLogic getOrderLogic() {
		return orderLogic;
	}

	public void setOrderLogic(OrderLogic orderLogic) {
		this.orderLogic = orderLogic;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getScmCocName() {
		return scmCocName;
	}

	public void setScmCocName(String scmCocName) {
		this.scmCocName = scmCocName;
	}

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
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

	public String getBeginPlaceOrderDate() {
		return beginPlaceOrderDate;
	}

	public void setBeginPlaceOrderDate(String beginPlaceOrderDate) {
		this.beginPlaceOrderDate = beginPlaceOrderDate;
	}

	public String getEndPlaceOrderDate() {
		return endPlaceOrderDate;
	}

	public void setEndPlaceOrderDate(String endPlaceOrderDate) {
		this.endPlaceOrderDate = endPlaceOrderDate;
	}

	public String getBeginDeliveryDate() {
		return beginDeliveryDate;
	}

	public void setBeginDeliveryDate(String beginDeliveryDate) {
		this.beginDeliveryDate = beginDeliveryDate;
	}

	public String getEndDeliveryDate() {
		return endDeliveryDate;
	}

	public void setEndDeliveryDate(String endDeliveryDate) {
		this.endDeliveryDate = endDeliveryDate;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public ScmcocLogic getScmcocLogic() {
		return scmcocLogic;
	}

	public void setScmcocLogic(ScmcocLogic scmcocLogic) {
		this.scmcocLogic = scmcocLogic;
	}

	public String getEditData() {
		return editData;
	}

	public void setEditData(String editData) {
		this.editData = editData;
	}

	public String getHsName() {
		return hsName;
	}

	public void setHsName(String hsName) {
		this.hsName = hsName;
	}

}
