package com.fms.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;

import com.fms.base.action.BaseAction;
import com.fms.commons.ImgExgFlag;
import com.fms.core.entity.Employee;
import com.fms.core.entity.Material;
import com.fms.core.entity.OrderHead;
import com.fms.core.entity.OrderItem;
import com.fms.core.entity.Scmcoc;
import com.fms.core.vo.entity.TempEntity;
import com.fms.logic.EmployeeLogic;
import com.fms.logic.MaterialLogic;
import com.fms.logic.OrderLogic;
import com.fms.logic.ScmcocLogic;
import com.fms.temp.TempOrder;
import com.fms.utils.AjaxResult;
import com.fms.utils.ExcelUtil;
import com.fms.utils.MathUtils;
import com.google.gson.Gson;
import com.url.ajax.json.JSONException;
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
	protected MaterialLogic materialLogic;
	protected EmployeeLogic emplLogic;

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
	private static final Integer DEFAULT_PAGESIZE = 15;

	/********* 获取前台选择的文件 ***********/
	private File uploadFile; // 上传的文件 名称是Form 对应的name
	private String uploadFileContentType; // 文件的类型
	private String uploadFileFileName; // 文件的名称
	private String sendStr;

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

			dataTotal = this.orderLogic.findCount(orderNo, parseValue(scmCocName), parseValue(salesman), date, date2, date3, date4);

			List<OrderHead> orderList = this.orderLogic.findOrderPageList(getLoginUser(), orderNo, parseValue(scmCocName), parseValue(salesman), date, date2, date3, date4, (curr - 1)
					* DEFAULT_PAGESIZE, DEFAULT_PAGESIZE);
			List<Scmcoc> scmList = this.scmcocLogic.findAllScmcoc(getLoginUser(), true, null, -1, -1);
			Map<String, TempEntity> salesMap = new HashMap<String, TempEntity>();
			for (int i = 0; i < orderList.size(); i++) {
				if (StringUtils.isNotBlank(orderList.get(i).getSalesman())) {
					TempEntity te = new TempEntity();
					te.setCode(i + 1);
					te.setName(orderList.get(i).getSalesman());
					salesMap.put(te.getName(), te);
				}
			}
			List<TempEntity> salesmans = new ArrayList<TempEntity>();
			for (Entry<String, TempEntity> entry : salesMap.entrySet()) {
				salesmans.add(entry.getValue());
			}
			this.request.put("orderList", orderList);
			this.request.put("scmList", scmList);
			this.request.put("hBeginPlaceOrderDate", beginPlaceOrderDate);
			this.request.put("hEndPlaceOrderDate", endPlaceOrderDate);
			this.request.put("hBeginDeliveryDate", beginDeliveryDate);
			this.request.put("hEndDeliveryDate", endDeliveryDate);
			this.request.put("scmCocName", parseValue(scmCocName));
			System.out.println(scmCocName);
			this.request.put("salesman", parseValue(salesman));
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

	/**
	 * 保存订单表头
	 */
	@SuppressWarnings("unused")
	public void saveOrder() {
		AjaxResult result = new AjaxResult();
		Map<String, Employee> emplMap = new HashMap<String, Employee>();
		String err = "";
		try {
			List<Employee> empls = this.emplLogic.findAllEmpl();
			for (Employee em : empls) {
				emplMap.put(em.getName(), em);
			}
			this.out = response.getWriter();
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
				if (StringUtils.isNotBlank(head.getSalesman()) && emplMap.get(head.getSalesman()) == null) {
					err += "业务员" + head.getSalesman() + ",\n";
				}
				orderList.add(head);
			}
			if (StringUtils.isNotBlank(err)) {
				result.setSuccess(false);
				result.setMsg(err.substring(0, err.length() - 2) + "\n在系统中不存在!");
			} else {
				this.orderLogic.beatchSaveOrUpData(getLoginUser(), orderList);
				result.setSuccess(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		} finally {
			JSONObject json = new JSONObject(result);
			out.println(json.toString());
			out.flush();
		}
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
				this.request.put("hsCode", hsCode);
				this.request.put("hsName", hsName);
				this.request.put("pageNums", pageCount(max, dataTotal));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "items";
	}

	public String findMaterial() {
		List<Material> mlist = materialLogic.findAllMaterialInfoByHsCode(hsCode, ImgExgFlag.EXG, -1, -1);
		this.request.put("mlist", mlist);
		this.request.put("hsCode", hsCode);
		return "dialog";
	}

	/**
	 * ids 选择的物料多个id
	 * 
	 * @return
	 */
	public String findMaterialByIds() {
		try {
			if (null != ids && !"".equals(ids)) {
				String[] idArr = ids.split("/");
				List<Material> mList = materialLogic.findMaterialById(getLoginUser(), idArr);// 查物料
				OrderHead head = this.orderLogic.load(OrderHead.class, hid);// 查询OrderHead
				List<OrderItem> items = new ArrayList<OrderItem>();
				for (Material m : mList) {
					OrderItem item = new OrderItem();
					item.setOrderHead(head);
					item.setHsCode(m.getHsCode());
					item.setHsModel(m.getModel());
					item.setHsName(m.getHsName());
					item.setUnit(m.getUnit());
					item.setSerialNo(orderLogic.getOrderItemSerialNo());
					if (item.getPrice() != null && item.getQty() != null) {
						item.setAmount(item.getPrice() * item.getQty());
					}
					items.add(item);
				}
				items = this.orderLogic.beatchSaveOrUpDataItems(getLoginUser(), items);
				findOrderItems();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "items";
	}

	/**
	 * 保存订单表体
	 */
	public void saveOrderItem() {
		AjaxResult result = new AjaxResult();
		String errValue = "";
		try {
			this.out = response.getWriter();
			response.setContentType("application/text");
			response.setCharacterEncoding("UTF-8");
			List<OrderItem> orderItemList = new ArrayList<OrderItem>();
			JSONArray jsonStr = JSONArray.fromObject(editData);
			Object[] arrData = jsonStr.toArray();
			OrderItem item = null;
			for (int i = 0; i < arrData.length; i = i + 5) {
				item = this.orderLogic.findOrderItemById(arrData[i].toString());
				if (null != arrData[i + 1] && !"".equals(arrData[i + 1])) {
					errValue = arrData[i + 1].toString();
					item.setQty(Double.parseDouble(arrData[i + 1].toString()));
				}
				if (null != arrData[i + 2] && !"".equals(arrData[i + 2])) {
					errValue = arrData[i + 2].toString();
					item.setPrice(Double.parseDouble(arrData[i + 2].toString()));
				}
				if (item.getQty() != null && item.getPrice() != null) {
					item.setAmount(MathUtils.multiply(item.getQty(), item.getPrice()));
				}
				if (null != arrData[i + 4] && !"".equals(arrData[i + 4])) {
					item.setNote(arrData[i + 4].toString());
				}
				orderItemList.add(item);
			}
			orderItemList = this.orderLogic.beatchSaveOrUpDataItems(getLoginUser(), orderItemList);
			result.setSuccess(true);
		} catch (NumberFormatException ex) {
			result.setSuccess(false);
			result.setMsg("输入数字" + errValue + "非法!");
		} catch (IOException e) {
			result.setMsg("数据处理异常!");
			result.setSuccess(false);
		} finally {
			JSONObject json = new JSONObject(result);
			this.out.println(json.toString());
			this.out.flush();
		}
	}

	/**
	 * 删除订单表体
	 */
	public void delOrderItems() {
		AjaxResult result = new AjaxResult();
		try {
			this.out = response.getWriter();
			response.setContentType("application/text");
			response.setCharacterEncoding("UTF-8");
			if (StringUtils.isNotBlank(ids)) {
				String[] idArr = ids.split(",");
				this.orderLogic.delOrderItemByIds(getLoginUser(), idArr);
				result.setSuccess(true);
			}
		} catch (Exception e) {
			result.setSuccess(false);
		} finally {
			JSONObject json = new JSONObject(result);
			this.out.println(json.toString());
			this.out.flush();
		}
	}

	/**
	 * 显示导入Excel界面
	 * 
	 * @return
	 */
	public String showImport() {
		return "import";
	}

	/**
	 * 解析excel数据，并验证数据有效性
	 * 
	 * @return
	 */
	public void importData() {
		AjaxResult result = new AjaxResult();
		result.setSuccess(false);
		try {
			String[][] content = ExcelUtil.readExcel(uploadFile, 0);
			String[] title = new String[9];
			title[0] = content[0][0];
			title[1] = content[0][1];
			title[2] = content[0][2];
			title[3] = content[0][3];
			title[4] = content[0][4];
			title[5] = content[0][5];
			title[6] = content[0][6];
			title[7] = content[0][7];
			title[8] = content[0][8];
			if (!"订单号码".equals(title[0]) || !"客户全称".equals(title[1]) || !"下单日期".equals(title[2]) || !"交货日期".equals(title[3]) || !"业务员".equals(title[4]) || !"物料编码".equals(title[5])
					|| !"订单数量".equals(title[6]) || !"单价".equals(title[7]) || !"备注".equals(title[8])) {
				result.setSuccess(false);
				result.setMsg("导入的excel文件内容不正确!");
			} else {
				List<TempOrder> tempOrders = new ArrayList<TempOrder>();
				for (int i = 1; i < content.length; i++) {
					tempOrders.add(convertData(content[i]));
				}
				List tlist = this.orderLogic.doValidata(tempOrders);
				result.setSuccess(true);
				result.setObj(tlist);
			}
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("操作错误" + e.getMessage());
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String str = gson.toJson(result);
		try {
			Writer writer = response.getWriter();
			writer.write(str);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 把导入的内容转换成临时类
	 * 
	 * @param arr
	 * @return
	 */
	private TempOrder convertData(Object[] arr) {
		TempOrder tmp = new TempOrder();
		try {
			if (null != arr[0] && !"".equals(arr[0])) {
				tmp.setOrderNo(arr[0].toString());
			}
			if (null != arr[1] && !"".equals(arr[1])) {
				tmp.setScmCocName(arr[1].toString());
			}
			if (null != arr[2] && !"".equals(arr[2])) {
				tmp.setPlaceOrderDate(new SimpleDateFormat("yyyy-MM-dd").parse(arr[2].toString()));
			}
			if (null != arr[3] && !"".equals(arr[3])) {
				tmp.setDeliveryDate(new SimpleDateFormat("yyyy-MM-dd").parse(arr[3].toString()));
			}
			if (null != arr[4] && !"".equals(arr[4])) {
				tmp.setSalesman(arr[4].toString());
			}
			if (null != arr[5] && !"".equals(arr[5])) {
				tmp.setHsCode(arr[5].toString());
			}
			if (null != arr[6] && !"".equals(arr[6])) {
				tmp.setQty(Double.valueOf(arr[6].toString()));
			}
			if (null != arr[7] && !"".equals(arr[7])) {
				tmp.setPrice(Double.valueOf(arr[7].toString()));
			}
			if (null != arr[8] && !"".equals(arr[8])) {
				tmp.setNote(arr[8].toString());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return tmp;
	}

	/**
	 * 清除错误的数据
	 * 
	 * @return
	 */
	public void clearErrorData() {
		List errorList = new ArrayList();
		AjaxResult result = new AjaxResult();
		result.setSuccess(false);
		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(sendStr);
		List list = net.sf.json.JSONArray.toList(jsonArray, new TempOrder(), new JsonConfig());
		if (null != list && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				TempOrder order = (TempOrder) list.get(i);
				if (null != order.getErrorInfo() && !"".equals(order.getErrorInfo().trim())) {
					errorList.add(order);
				}
			}
			list.removeAll(errorList);
		}
		Gson gson = new Gson();
		result.setObj(list);
		result.setSuccess(true);
		String str = gson.toJson(result);
		Writer writer;
		try {
			response.setContentType("application/text");
			response.setCharacterEncoding("UTF-8");
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
	 * 
	 * @throws JSONException
	 */
	@SuppressWarnings("unchecked")
	public String saveExcelData() {
		PrintWriter out = null;
		AjaxResult result = new AjaxResult();
		try {
			net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(sendStr);
			List<TempOrder> list = net.sf.json.JSONArray.toList(jsonArray, new TempOrder(), new JsonConfig());
			if (null == list || list.size() <= 0) {
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
			if (!this.orderLogic.doSaveExcelData(getLoginUser(), list)) {
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
				result.setMsg("成功保存" + list.size() + "条数据！");
				session.put("tlist", null);
				JSONObject json = new JSONObject(result);
				out.println(json.toString());
				out.flush();
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.SUCCESS;
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

	public MaterialLogic getMaterialLogic() {
		return materialLogic;
	}

	public void setMaterialLogic(MaterialLogic materialLogic) {
		this.materialLogic = materialLogic;
	}

	public String getHsCode() {
		return hsCode;
	}

	public void setHsCode(String hsCode) {
		this.hsCode = hsCode;
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

	public EmployeeLogic getEmplLogic() {
		return emplLogic;
	}

	public void setEmplLogic(EmployeeLogic emplLogic) {
		this.emplLogic = emplLogic;
	}

}
