package com.fms.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fms.base.action.BaseAction;
import com.fms.commons.ImgExgFlag;
import com.fms.commons.ImpExpType;
import com.fms.core.entity.InStorage;
import com.fms.core.entity.OutStorage;
import com.fms.core.entity.Stock;
import com.fms.core.vo.entity.TempEntity;
import com.fms.logic.MaterialTypeLogic;
import com.fms.logic.ScmcocLogic;
import com.fms.logic.StockLogic;
import com.fms.logic.StorageLogic;
import com.fms.logic.UnitLogic;
import com.fms.utils.AjaxResult;
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
	protected UnitLogic unitLogic;// 计量单位逻辑
	protected ScmcocLogic scmcocLogic;// 客户供应商逻辑
	protected StockLogic stockLogic;// 仓库逻辑

	/********* 分页用的属性 ***********/
	private Integer dataTotal;// 总记录数
	private String currIndex;// 当前页码
	private String maxIndex;// 每页显示最多条数
	private Integer pageNums;// 共有多少页
	private String searchStr;// 搜索条件
	private static final Integer DEFAULT_PAGESIZE = 10;

	/********* 获取前台选择的文件 ***********/
	private File uploadFile; // 上传的文件 名称是Form 对应的name
	private String uploadFileContentType; // 文件的类型
	private String uploadFileFileName; // 文件的名称
	private String sendStr;

	protected InStorage inStorage;// 入库
	protected OutStorage outStorage;// 出库
	protected String entityName;
	protected String impExpFlag;// 进出标志
	protected Date startDate;
	protected Date endDate;
	protected String scmcocName;
	protected String hsName;

	/**
	 * 获取入库列表数据
	 * 
	 * @return
	 */
	public String findAllInStorage() {
		try {
			Integer curr = (null == currIndex || "".equals(currIndex)) ? 1 : Integer.parseInt(currIndex);// 当前第几页
			Integer max = (null == maxIndex || "".equals(maxIndex)) ? 1 : Integer.parseInt(currIndex);// 每页最多显示条数
			dataTotal = this.storageLogic.findDataCount("InStorage", startDate, endDate, scmcocName, hsName, impExpFlag);
			List storageList = this.storageLogic.findStorage(getLoginUser(), "InStorage", startDate, endDate, impExpFlag, parseValue(scmcocName), parseValue(hsName), (curr - 1) * DEFAULT_PAGESIZE,
					DEFAULT_PAGESIZE);
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

	/**
	 * 编辑
	 * 
	 * @return
	 */
	public String editStorage() {
		if (StringUtils.isNotBlank(impExpFlag)) {
			Integer maxSerialNo = this.storageLogic.findMaxSerialNo(getLoginUser(), "InStorage", impExpFlag);
			if (null == maxSerialNo || maxSerialNo == 0) {
				Integer serialNo = 1;
			}
			String imgExgFlag = "0".equals(impExpFlag) ? ImgExgFlag.EXG : ImgExgFlag.IMG;
			List<TempEntity> typeList = ImpExpType.getImgTypeByMaterialType(imgExgFlag);
			List<Stock> stockList = stockLogic.findAllStock(getLoginUser(), null, -1, -1);
			this.request.put("imgExgFlag", imgExgFlag);
			this.request.put("impexptypes", typeList);
			this.request.put("stockList", stockList);
		}
		return "edit";
	}

	public void loadImpExpType() {
		PrintWriter out = null;
		AjaxResult result = new AjaxResult();
		try {
			List<TempEntity> typeList = ImpExpType.getImgTypeByMaterialType(impExpFlag);
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

	public String save() {
		if (inStorage != null) {

		}
		return "save";
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public StockLogic getStockLogic() {
		return stockLogic;
	}

	public void setStockLogic(StockLogic stockLogic) {
		this.stockLogic = stockLogic;
	}

}
