package com.fms.action;

import com.fms.base.action.BaseAction;
import com.fms.logic.ReportLogic;

/**
 * 所有报表ACtion
 * 
 * @author Administrator
 * 
 */
public class RePortDataAction extends BaseAction {
	protected ReportLogic reportLogic;
	/********* 分页用的属性 ***********/
	private Integer dataTotal;// 总记录数
	private String currIndex;// 当前页码
	private String maxIndex;// 每页显示最多条数
	private Integer pageNums;// 共有多少页
	private String searchStr;// 搜索条件
	private static final Integer DEFAULT_PAGESIZE = 15;

	protected String startDate;
	protected String endDate;
	protected String scmcocName;
	protected String hsName;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	public String getScmcocName() {
		return scmcocName;
	}

	public void setScmcocName(String scmcocName) {
		this.scmcocName = scmcocName;
	}

	public String getHsName() {
		return hsName;
	}

	public void setHsName(String hsName) {
		this.hsName = hsName;
	}

	public String getPurchaseReport() {
		/*
		 * startDate = "".equals(startDate) ? null : startDate; endDate =
		 * "".equals(endDate) ? null : endDate; Date date = null; if (null !=
		 * startDate) { date = new
		 * SimpleDateFormat("yyyy-MM-dd").parse(startDate); } Date date2 = null;
		 * if (null != endDate) { date2 = new
		 * SimpleDateFormat("yyyy-MM-dd").parse(endDate); }
		 * 
		 * Integer curr = (null == currIndex || "".equals(currIndex)) ? 1 :
		 * Integer.parseInt(currIndex);// 当前第几页 Integer max = (null == maxIndex
		 * || "".equals(maxIndex)) ? 1 : Integer.parseInt(currIndex);// 每页最多显示条数
		 * System.out.println(parseValue(scmcocName));
		 * 
		 * dataTotal = this.storageLogic.findDataCount("InStorage", date, date2,
		 * parseValue(scmcocName), parseValue(hsName), impExpFlag); List
		 * storageList = this.storageLogic.findStorage(getLoginUser(),
		 * "InStorage", date, date2, parseValue(scmcocName), parseValue(hsName),
		 * impExpFlag, (curr - 1) * DEFAULT_PAGESIZE, DEFAULT_PAGESIZE);
		 * 
		 * for (Object obj : storageList) { InStorage is = (InStorage) obj;
		 * is.setImpFlag
		 * (ImpExpType.getImpExpType(Integer.parseInt(is.getImpFlag()))); }
		 * 
		 * this.request.put("storageList", storageList);
		 * this.request.put("currIndex", curr); this.request.put("maxIndex",
		 * max); this.request.put("pageNums", pageCount(max, dataTotal));
		 * this.request.put("startDate", startDate); this.request.put("endDate",
		 * endDate); this.request.put("imgexgflag", impExpFlag);
		 * this.request.put("scmcocName", parseValue(scmcocName));
		 * this.request.put("hsName", parseValue(hsName)); } catch
		 * (NumberFormatException e) { e.printStackTrace(); } catch
		 * (ParseException e) { e.printStackTrace(); }
		 */
		return "purchaseReport";
	}

}
