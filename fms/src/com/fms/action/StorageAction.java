package com.fms.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;

import com.fms.base.action.BaseAction;
import com.fms.commons.ImgExgFlag;
import com.fms.commons.ImpExpType;
import com.fms.commons.PurchaseBillStatus;
import com.fms.core.entity.InStorage;
import com.fms.core.entity.Material;
import com.fms.core.entity.OrderHead;
import com.fms.core.entity.OutStorage;
import com.fms.core.entity.PurchaseBill;
import com.fms.core.entity.Scmcoc;
import com.fms.core.entity.Stock;
import com.fms.core.vo.entity.TempEntity;
import com.fms.logic.MaterialLogic;
import com.fms.logic.MaterialTypeLogic;
import com.fms.logic.OrderLogic;
import com.fms.logic.PurchaseBillLogic;
import com.fms.logic.ScmcocLogic;
import com.fms.logic.StockLogic;
import com.fms.logic.StorageLogic;
import com.fms.logic.UnitLogic;
import com.fms.temp.TempInStorage;
import com.fms.utils.AjaxResult;
import com.fms.utils.ExcelUtil;
import com.fms.utils.MathUtils;
import com.google.gson.Gson;
import com.url.ajax.json.JSONObject;

/**
 * 进出库
 * 
 * @author Administrator
 * 
 */
public class StorageAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected StorageLogic storageLogic;// 进出库逻辑
	protected MaterialTypeLogic logic;// 物料类型逻辑
	protected MaterialLogic materLogic;// 物料逻辑
	protected UnitLogic unitLogic;// 计量单位逻辑
	protected ScmcocLogic scmcocLogic;// 客户供应商逻辑
	protected StockLogic stockLogic;// 仓库逻辑
	protected PurchaseBillLogic purchaseBillLogic;// 采购单逻辑
	protected OrderLogic orderLogic;// 订单逻辑

	/********* 分页用的属性 ***********/
	private Integer dataTotal;// 总记录数
	private String currIndex;// 当前页码
	private String maxIndex;// 每页显示最多条数
	private Integer pageNums;// 共有多少页
	private String searchStr;// 搜索条件
	private static final Integer DEFAULT_PAGESIZE = 15;

	/********* 获取前台选择的文件 ***********/
	private File uploadFile; // 上传的文件 名称是Form 对应的name
	private String uploadFileContentType; // 文件的类型
	private String uploadFileFileName; // 文件的名称
	private String sendStr;

	protected InStorage inStorage;// 入库
	protected OutStorage outStorage;// 出库
	protected String entityName;
	protected String impExpFlag;// 进出标志
	protected String startDate;
	protected String endDate;
	protected String scmcocName;
	protected String serialNo;
	protected String hsName;
	protected String hsCode;
	protected String inOrOutFlag;// 进出库标志
	protected String id;// 进出库id
	protected String purchaseNo;// 采购单号
	protected String orderNo;// 订单号
	protected String imgExgFlag;// 物料标记
	protected String inQty;// 入库数量
	protected String packQty;// 每件包装数
	protected String model;// 规格
	protected String unitName;// 计量单位
	protected String pkgs;// 件数
	protected String stockName;// 仓库名称
	protected String impFlag;// 入库类型
	protected String inStorageNo;// 入库单号
	protected String stockId;// 仓库id
	protected String note;// 备注
	protected String ids;// 批量删除时，public.js传过来的多个id以，分割的字符串

	/**
	 * 获取入库列表数据
	 * 
	 * @return
	 */
	public String findAllInStorage() {
		try {
			startDate = "".equals(startDate) ? null : startDate;
			endDate = "".equals(endDate) ? null : endDate;
			Date date = null;
			if (null != startDate) {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
			}
			Date date2 = null;
			if (null != endDate) {
				date2 = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
			}

			Integer curr = (null == currIndex || "".equals(currIndex)) ? 1 : Integer.parseInt(currIndex);// 当前第几页
			Integer max = (null == maxIndex || "".equals(maxIndex)) ? 1 : Integer.parseInt(currIndex);// 每页最多显示条数
			System.out.println(parseValue(scmcocName));
			dataTotal = this.storageLogic.findDataCount("InStorage", date, date2, parseValue(scmcocName), parseValue(hsName), impExpFlag);
			List storageList = this.storageLogic.findStorage(getLoginUser(), "InStorage", date, date2, parseValue(scmcocName), parseValue(hsName), impExpFlag, (curr - 1) * DEFAULT_PAGESIZE,
					DEFAULT_PAGESIZE);

			for (Object obj : storageList) {
				InStorage is = (InStorage) obj;
				is.setImpFlag(ImpExpType.getImpExpType(Integer.parseInt(is.getImpFlag())));
			}

			this.request.put("storageList", storageList);
			this.request.put("currIndex", curr);
			this.request.put("maxIndex", max);
			this.request.put("pageNums", pageCount(max, dataTotal));
			this.request.put("startDate", startDate);
			this.request.put("endDate", endDate);
			this.request.put("imgexgflag", impExpFlag);
			this.request.put("scmcocName", parseValue(scmcocName));
			this.request.put("hsName", parseValue(hsName));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "imp";
	}

	private Integer pageCount(Integer maxIndex, Integer dataTotal) {
		pageNums = (dataTotal / DEFAULT_PAGESIZE) + (dataTotal % DEFAULT_PAGESIZE > 0 ? 1 : 0); // 总页数
		if (pageNums == 0) {
			pageNums += 1;
		}
		return pageNums;
	}

	/**
	 * 编辑
	 * 
	 * @return
	 */
	public String editStorage() {
		try {
			// if (StringUtils.isNotBlank(impExpFlag)) {
			Integer maxSerialNo = this.storageLogic.findMaxSerialNo(getLoginUser(), "InStorage", impExpFlag);
			Integer serialNo = 0;
			if (null == maxSerialNo || maxSerialNo == 0) {
				serialNo = 1;
			} else {
				serialNo = ++maxSerialNo;
			}
			InStorage storage = null;
			if (StringUtils.isBlank(id)) {
				storage = new InStorage();
				storage.setSerialNo(serialNo);
			} else {
				storage = (InStorage) this.storageLogic.findStorageById(InStorage.class, id);
			}
			// String imgExgFlag = "0".equals(impExpFlag) ? ImgExgFlag.EXG :
			// ImgExgFlag.IMG;
			this.impExpFlag = this.impExpFlag == null ? storage.getImgExgFlag() : this.impExpFlag;
			List<TempEntity> typeList = ImpExpType.getImgTypeByMaterialType(impExpFlag);
			getTypeList(typeList);
			List<Stock> stockList = stockLogic.findAllStock(getLoginUser(), null, -1, -1);
			this.request.put("imgExgFlag", impExpFlag);
			this.request.put("impexptypes", typeList);
			this.request.put("stockList", stockList);
			this.request.put("inOrOutFlag", inOrOutFlag);
			this.request.put("inStorage", storage);
			this.request.put("isClear", "false");
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "edit";
	}

	public void loadImpExpType() {
		PrintWriter out = null;
		AjaxResult result = new AjaxResult();
		try {
			List<TempEntity> typeList = ImpExpType.getImgTypeByMaterialType(impExpFlag);// "I"/"E"
			getTypeList(typeList);
			out = response.getWriter();
			response.setContentType("application/text");
			response.setCharacterEncoding("UTF-8");
			result.setObj(typeList);
			result.setSuccess(true);
			JSONObject json = new JSONObject(result);
			out.println(json.toString());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getTypeList(List<TempEntity> typeList) {
		// String imgExgFlag = "0".equals(impExpFlag) ? ImgExgFlag.EXG :
		// ImgExgFlag.IMG;
		List<TempEntity> tempList = new ArrayList<TempEntity>();
		for (TempEntity en : typeList) {
			// 成品入库
			if ("in".equals(inOrOutFlag) && ImgExgFlag.EXG.equals(impExpFlag)) {
				if (en.getCode() == 7 || en.getCode() == 10 || en.getCode() == 13) {
					tempList.add(en);
				}
				// 成品出库
			} else if ("out".equals(inOrOutFlag) && ImgExgFlag.EXG.equals(impExpFlag)) {
				if (en.getCode() != 7 || en.getCode() != 10 || en.getCode() != 13) {
					tempList.add(en);
				}
				// 原料入库
			} else if ("in".equals(inOrOutFlag) && ImgExgFlag.IMG.equals(impExpFlag)) {
				if (en.getCode() == 6 || en.getCode() == 8 || en.getCode() == 9 || en.getCode() == 12) {
					tempList.add(en);
				}
			} else if ("out".equals(inOrOutFlag) && ImgExgFlag.IMG.equals(impExpFlag)) {
				if (en.getCode() != 6 || en.getCode() != 8 || en.getCode() != 9 || en.getCode() != 12) {
					tempList.add(en);
				}
			}
		}
		typeList.removeAll(tempList);
	}

	/**
	 * 查询采购单表头
	 * 
	 * @return
	 */
	public String findPurchaseHead() {
		try {
			List<PurchaseBill> heads = this.purchaseBillLogic.findPurchaseBill(Boolean.FALSE, PurchaseBillStatus.EFFECTED);
			this.request.put("headList", heads);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "dialog";
	}

	/**
	 * 根据id获取采购单号
	 */
	public void getPurchaseNoById() {
		try {
			if (StringUtils.isNotBlank(id)) {
				id = id.split("/")[0];
			}
			PurchaseBill bill = this.purchaseBillLogic.findPurchaseById(getLoginUser(), id);
			PrintWriter out = null;
			AjaxResult result = new AjaxResult();
			out = response.getWriter();
			response.setContentType("application/text");
			response.setCharacterEncoding("UTF-8");
			result.setObj(bill);
			result.setSuccess(true);
			JSONObject json = new JSONObject(result);
			out.println(json.toString());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取物料信息（如果有采购单就取采购单表体的物料，如果有订单，就取订单表体的物料，如果都没有，则取物料清单相应类型的所有数据）
	 * 
	 * @return
	 */
	public String findMaterial() {
		try {
			List<Material> mlist = new ArrayList<Material>();
			// 如果有采购单就取采购单表体的物料
			if (StringUtils.isNotBlank(purchaseNo)) {
				mlist = this.purchaseBillLogic.findPurchaseItemMaterialByNo(purchaseNo, hsCode, this.parseValue(hsName));
			} else if (StringUtils.isNotBlank(orderNo)) {
				// 如果有订单，就取订单表体的物料
				mlist = this.orderLogic.findOrderItemMaterialByNo(orderNo, hsCode, this.parseValue(hsName));
			} else {
				// 如果都没有，则取物料清单相应类型的所有数据
				mlist = this.materLogic.finsMaterialByHsCode(hsCode, this.parseValue(hsName), imgExgFlag);
			}
			request.put("mlist", mlist);
			request.put("imgExgFlag", imgExgFlag);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "materdialg";
	}

	/**
	 * 选择物料信息
	 * 
	 * @return
	 */
	public String findMaterialList() {
		List<Material> mlist = new ArrayList<Material>();
		mlist = this.materLogic.finsMaterialByHsCode(hsCode, this.parseValue(hsName), imgExgFlag);
		request.put("mlist", mlist);
		request.put("hsCode", hsCode);
		request.put("hsName", this.parseValue(hsName));
		request.put("imgExgFlag", imgExgFlag);
		return "materdialg";
	}

	/**
	 * 获取订单信息
	 */
	public String findOrderHeadList() {
		List<OrderHead> olist = new ArrayList<OrderHead>();
		olist = this.orderLogic.findOrderHeadByStauts(false);
		request.put("headList", olist);
		return "dgorder";
	}

	/**
	 * 根据id获取物料信息
	 */
	public void getHsCodeByMaterialId() {
		try {
			if (StringUtils.isNotBlank(id)) {
				id = id.split("/")[0];
			}
			Material material = this.materLogic.findMaterialById(getLoginUser(), id);
			PrintWriter out = null;
			AjaxResult result = new AjaxResult();
			out = response.getWriter();
			response.setContentType("application/text");
			response.setCharacterEncoding("UTF-8");
			result.setObj(material);
			result.setSuccess(true);
			JSONObject json = new JSONObject(result);
			out.println(json.toString());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据id获取订单号
	 */
	public void getOrderNoById() {
		try {
			if (StringUtils.isNotBlank(id)) {
				id = id.split("/")[0];
			}
			OrderHead head = this.orderLogic.findOrderHeadById(id);
			PrintWriter out = null;
			AjaxResult result = new AjaxResult();
			out = response.getWriter();
			response.setContentType("application/text");
			response.setCharacterEncoding("UTF-8");
			result.setObj(head);
			result.setSuccess(true);
			JSONObject json = new JSONObject(result);
			out.println(json.toString());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 自动计算件数
	 */
	public void conutPaks() {
		try {
			PrintWriter out = null;
			AjaxResult result = new AjaxResult();
			out = response.getWriter();
			response.setContentType("application/text");
			response.setCharacterEncoding("UTF-8");
			Double qty = 0d;
			Double packQtys = 0d;
			if (StringUtils.isNotBlank(inQty) && isNumeric(inQty)) {
				qty = Double.parseDouble(inQty);
			}
			if (StringUtils.isNotBlank(packQty) && isNumeric(packQty)) {
				packQtys = Double.parseDouble(packQty);
			}
			int pcs = MathUtils.round(MathUtils.dividend(qty, packQtys), "up");
			result.setObj(pcs);
			result.setSuccess(true);
			JSONObject json = new JSONObject(result);
			out.println(json.toString());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveInStorage() {
		try {
			PrintWriter out = null;
			AjaxResult result = new AjaxResult();
			out = response.getWriter();
			response.setContentType("application/text");
			response.setCharacterEncoding("UTF-8");

			InStorage inStorage = null;
			if (StringUtils.isNotBlank(id)) {
				inStorage = (InStorage) this.storageLogic.findStorageById(InStorage.class, id);
			} else {
				inStorage = new InStorage();
			}
			int serNo = 0;
			if (StringUtils.isNotBlank(serialNo) && isNumeric(serialNo)) {
				serNo = Integer.parseInt(serialNo);
			} else {
				serNo++;
			}
			inStorage.setSerialNo(serNo);// 流水号
			inStorage.setImpFlag(impFlag);// 入库类型
			inStorage.setImgExgFlag(imgExgFlag);// 物料标志
			inStorage.setPurchaseNo(purchaseNo);// 采购单号
			inStorage.setInStorageNo(inStorageNo);// 入库单号
			inStorage.setOrderNo(orderNo);// 订单号
			Scmcoc scmcoc = this.scmcocLogic.findScmcocByName(this.parseValue(scmcocName));
			inStorage.setScmcoc(scmcoc);// 供应商
			Material material = this.materLogic.findMaterialByHsCode(getLoginUser(), hsCode);
			inStorage.setMaterial(material);// 物料清单

			Double qty = 0d;
			Double specQty = 0d;
			Double pcs = 0d;
			if (StringUtils.isNotBlank(inQty) && isNumeric(inQty)) {
				qty = Double.parseDouble(inQty);
			}
			if (StringUtils.isNotBlank(packQty) && isNumeric(packQty)) {
				specQty = Double.parseDouble(packQty);
			}
			if (StringUtils.isNotBlank(pkgs) && isNumeric(pkgs)) {
				pcs = Double.parseDouble(pkgs);
			}
			inStorage.setInQty(qty);// 入库数量
			inStorage.setSpecQty(specQty);// 每件包装数量
			inStorage.setPkgs(pcs);// 件数
			Stock stock = null;
			if (StringUtils.isNotBlank(stockId)) {
				stock = this.stockLogic.findStockById(getLoginUser(), stockId);
			}
			inStorage.setStock(stock);// 仓库
			inStorage.setNote(this.parseValue(note));// 备注
			inStorage.setUseFlag("0");// 默认启用
			inStorage.setImpDate(new Date());// 入库日期
			inStorage.setHandling(this.getLoginUser().getLoginName());// 入库人
			String mess = this.storageLogic.saveStorage(this.getLoginUser(), inStorage);
			if (StringUtils.isNotBlank(mess)) {
				result.setSuccess(false);
				result.setMsg(mess);
			} else {
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
	 * 删除出入库信息
	 */
	public void delStorages() {
		try {
			if (StringUtils.isNotBlank(ids)) {
				String[] idsArr = ids.split(",");
				PrintWriter out = null;
				AjaxResult result = new AjaxResult();
				if (idsArr != null && idsArr.length > 0) {
					out = response.getWriter();
					response.setContentType("application/text");
					response.setCharacterEncoding("UTF-8");
					this.storageLogic.deleteStoragesByIds(getLoginUser(), "InStorage", idsArr);
					result.setSuccess(true);
					result.setMsg("删除成功！");
					JSONObject json = new JSONObject(result);
					out.println(json.toString());
					out.flush();
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("数据被其它地方引用，不能删除！");
			JSONObject json = new JSONObject(result);
			out.println(json.toString());
			out.flush();
			out.close();
		}
	}

	/**
	 * 调用excel导入页面
	 * 
	 * @return
	 */
	public String toImportPage() {
		return "importexcel";
	}

	public void importData() {
		AjaxResult result = new AjaxResult();
		result.setSuccess(false);
		try {
			// 就这句，如何获取jsp页面传过来的文件
			String[][] content = ExcelUtil.readExcel(uploadFile, 0);
			String[] title = new String[10];
			title[0] = content[0][0];
			title[1] = content[0][1];
			title[2] = content[0][2];
			title[3] = content[0][3];
			title[4] = content[0][4];
			title[5] = content[0][5];
			title[6] = content[0][6];
			title[7] = content[0][7];
			title[8] = content[0][8];
			title[9] = content[0][9];
			if (!"物料标志".equals(title[0]) || !"入库类型".equals(title[1]) || !"入库单号".equals(title[2]) //
					|| !"订单号".equals(title[3]) || !"采购单号".equals(title[4]) || !"入库数量".equals(title[5])//
					|| !"物料编码".equals(title[6]) || !"仓库名称".equals(title[7]) || !"数量/(包装)".equals(title[8]) || !"备注".equals(title[9])) {
				result.setSuccess(false);
				result.setMsg("导入的excel文件内容不正确!");
			} else {
				List<TempInStorage> tempInStor = new ArrayList<TempInStorage>();
				for (int i = 1; i < content.length; i++) {
					TempInStorage inStorage = new TempInStorage();
					inStorage.setImgExgFlag(content[i][0]);
					inStorage.setImpFlag(content[i][1]);
					inStorage.setInStorageNo(content[i][2]);
					inStorage.setOrderNo(content[i][3]);
					inStorage.setPurchaseNo(content[i][4]);
					inStorage.setInQty(content[i][5]);
					inStorage.setHsCode(content[i][6]);
					inStorage.setStockName(content[i][7]);
					inStorage.setSpecQty(content[i][8]);
					inStorage.setNote(content[i][9]);
					tempInStor.add(inStorage);
				}
				// 开始验证
				List tlist = this.storageLogic.doValidata(tempInStor);
				result.setSuccess(true);
				result.setObj(tlist);
			}

		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("程序错误" + e.getMessage());
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
	 * 清除错误的数据
	 */
	public void clearErrorData() {
		List errorList = new ArrayList();
		AjaxResult result = new AjaxResult();
		result.setSuccess(false);
		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(sendStr);
		List list = net.sf.json.JSONArray.toList(jsonArray, new TempInStorage(), new JsonConfig());
		if (null != list && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				TempInStorage tis = (TempInStorage) list.get(i);
				if (null != tis.getErrorInfo() && !"".equals(tis.getErrorInfo().trim())) {
					errorList.add(tis);
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
	 * 保存正确的数据
	 */
	public String saveData() {
		PrintWriter out = null;
		AjaxResult result = new AjaxResult();
		try {
			net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(sendStr);
			List<TempInStorage> list = net.sf.json.JSONArray.toList(jsonArray, new TempInStorage(), new JsonConfig());
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
			if (!this.storageLogic.doSaveExcelData(this.getLoginUser(), list)) {
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

	public StorageLogic getStorageLogic() {
		return storageLogic;
	}

	public void setStorageLogic(StorageLogic storageLogic) {
		this.storageLogic = storageLogic;
	}

	public MaterialTypeLogic getLogic() {
		return logic;
	}

	public void setLogic(MaterialTypeLogic logic) {
		this.logic = logic;
	}

	public UnitLogic getUnitLogic() {
		return unitLogic;
	}

	public void setUnitLogic(UnitLogic unitLogic) {
		this.unitLogic = unitLogic;
	}

	public InStorage getInStorage() {
		return inStorage;
	}

	public void setInStorage(InStorage inStorage) {
		this.inStorage = inStorage;
	}

	public OutStorage getOutStorage() {
		return outStorage;
	}

	public void setOutStorage(OutStorage outStorage) {
		this.outStorage = outStorage;
	}

	public String getImpExpFlag() {
		return impExpFlag;
	}

	public void setImpExpFlag(String impExpFlag) {
		this.impExpFlag = impExpFlag;
	}

	public ScmcocLogic getScmcocLogic() {
		return scmcocLogic;
	}

	public void setScmcocLogic(ScmcocLogic scmcocLogic) {
		this.scmcocLogic = scmcocLogic;
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

	public String getSearchStr() {
		return searchStr;
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

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public StockLogic getStockLogic() {
		return stockLogic;
	}

	public void setStockLogic(StockLogic stockLogic) {
		this.stockLogic = stockLogic;
	}

	public String getInOrOutFlag() {
		return inOrOutFlag;
	}

	public void setInOrOutFlag(String inOrOutFlag) {
		this.inOrOutFlag = inOrOutFlag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PurchaseBillLogic getPurchaseBillLogic() {
		return purchaseBillLogic;
	}

	public void setPurchaseBillLogic(PurchaseBillLogic purchaseBillLogic) {
		this.purchaseBillLogic = purchaseBillLogic;
	}

	public MaterialLogic getMaterLogic() {
		return materLogic;
	}

	public void setMaterLogic(MaterialLogic materLogic) {
		this.materLogic = materLogic;
	}

	public String getPurchaseNo() {
		return purchaseNo;
	}

	public void setPurchaseNo(String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getHsName() {
		return hsName;
	}

	public void setHsName(String hsName) {
		this.hsName = hsName;
	}

	public String getScmcocName() {
		return scmcocName;
	}

	public void setScmcocName(String scmcocName) {
		this.scmcocName = scmcocName;
	}

	public String getHsCode() {
		return hsCode;
	}

	public void setHsCode(String hsCode) {
		this.hsCode = hsCode;
	}

	public OrderLogic getOrderLogic() {
		return orderLogic;
	}

	public void setOrderLogic(OrderLogic orderLogic) {
		this.orderLogic = orderLogic;
	}

	public String getImgExgFlag() {
		return imgExgFlag;
	}

	public void setImgExgFlag(String imgExgFlag) {
		this.imgExgFlag = imgExgFlag;
	}

	public String getInQty() {
		return inQty;
	}

	public void setInQty(String inQty) {
		this.inQty = inQty;
	}

	public String getPackQty() {
		return packQty;
	}

	public void setPackQty(String packQty) {
		this.packQty = packQty;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getPkgs() {
		return pkgs;
	}

	public void setPkgs(String pkgs) {
		this.pkgs = pkgs;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getImpFlag() {
		return impFlag;
	}

	public void setImpFlag(String impFlag) {
		this.impFlag = impFlag;
	}

	public String getInStorageNo() {
		return inStorageNo;
	}

	public void setInStorageNo(String inStorageNo) {
		this.inStorageNo = inStorageNo;
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}
