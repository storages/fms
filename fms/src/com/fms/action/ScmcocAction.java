﻿package com.fms.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JsonConfig;

import com.fms.base.action.BaseAction;
import com.fms.core.entity.Scmcoc;
import com.fms.core.entity.Settlement;
import com.fms.logic.ScmcocLogic;
import com.fms.logic.SettlementLogic;
import com.fms.temp.TempScmcoc;
import com.fms.temp.TempStock;
import com.fms.utils.AjaxResult;
import com.fms.utils.ExcelUtil;
import com.google.gson.Gson;
import com.url.ajax.json.JSONObject;

public class ScmcocAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ScmcocLogic scmcocLogic;
	private SettlementLogic settlementLogic;
	private Scmcoc scmcoc;

	/**** 客户供应商属性 *****/
	private String ids;
	private String code;
	private String name;
	private String linkPhone;
	private String networkLink;
	private String address;
	private String linkMan;
	private String endDate;
	private String isCustom;
	private String settlementId;
	private String note;

	/********* 分页用的属性 ***********/
	private Integer dataTotal;// 总记录数
	private String currIndex;// 当前页码
	private String maxIndex;// 每页显示最多条数
	private Integer pageNums;// 共有多少页
	private String className = "Scmcoc";// 表名称
	private String searchStr;// 搜索条件
	private static final Integer DEFAULT_PAGESIZE = 15;

	private File uploadFile; // 上传的文件 名称是Form 对应的name
	private String uploadFileContentType; // 文件的类型
	private String uploadFileFileName; // 文件的名称
	//
	private String sendStr;
	private String isScmcoc;

	private TempStock temp;

	/**
	 * 查询所有供应商或客户
	 * 
	 * @return
	 */
	public String findAllScmcoc() {
		// 是客户
		Integer curr = (null == currIndex || "".equals(currIndex)) ? 1 : Integer.parseInt(currIndex);// 当前第几页
		Integer max = (null == maxIndex || "".equals(maxIndex)) ? 1 : Integer.parseInt(currIndex);// 每页最多显示条数
		dataTotal = this.scmcocLogic.findDataCount(getLoginUser(), className, Boolean.parseBoolean(isCustom), parseValue(searchStr));
		List<Scmcoc> scmcocs = this.scmcocLogic.findAllScmcoc(getLoginUser(), Boolean.parseBoolean(isCustom), parseValue(searchStr), (curr - 1) * DEFAULT_PAGESIZE, DEFAULT_PAGESIZE);
		this.request.put("scmcocs", scmcocs);
		this.request.put("currIndex", curr);
		this.request.put("maxIndex", max);
		this.request.put("pageNums", pageCount(max, dataTotal));
		this.request.put("searchStr", parseValue(searchStr));
		if ("true".equals(isCustom)) {
			return "cis";// 是客户页面请求
		}
		return this.SUCCESS;// 是供应商请求
	}

	/**
	 * 保存供应商或客户
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveScmcoc() {
		try {
			Scmcoc scmcoc = new Scmcoc();
			// 是客户
			if ("true".equals(isCustom)) {
				scmcoc.setIsCustom(Boolean.TRUE);
				// 是供应商
			} else if ("false".equals(isCustom)) {
				scmcoc.setIsCustom(Boolean.FALSE);
			}
			this.scmcocLogic.saveScmcoc(getLoginUser(), this.setProperty(scmcoc));
			System.out.println("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "save";
	}

	/**
	 * 根据编码来查询供应商或客户
	 * 
	 * @return
	 */
	public void findScmcocByCode() {
		PrintWriter out = null;
		response.setContentType("application/text");
		response.setCharacterEncoding("UTF-8");
		try {
			Scmcoc sc = this.scmcocLogic.findScmcocByCode(getLoginUser(), code);
			out = response.getWriter();
			if (null != sc) {
				out.write("false");
			} else {
				out.write("true");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	/**
	 * 到导入Excles 的页面
	 * 
	 * @return
	 */
	public String toExcels() {
		this.request.put("isScmcoc", isScmcoc);
		return "inputExcels";
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
			// 就这句，如何获取jsp页面传过来的文件
			String[][] content = ExcelUtil.readExcel(uploadFile, 0);
			if (null != content && null != content[0] && content[0].length != 10) {
				result.setSuccess(false);
				result.setMsg("导入的excel文件内容不正确!");
			} else {
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
				if (!validataFile(title)) {
					result.setSuccess(false);
					result.setMsg("导入的excel文件内容不正确!");
				}
				List<Settlement> setList = this.settlementLogic.findAllSettlement(getLoginUser(), null);
				Map<String, Settlement> settCache = new HashMap<String, Settlement>();
				for (Settlement temp : setList) {
					String key = temp.getName();
					settCache.put(key, temp);
				}
				List<Scmcoc> scmcocs = new ArrayList<Scmcoc>();
				for (int i = 1; i < content.length; i++) {
					Scmcoc s = new Scmcoc();
					s.setCode(content[i][0]);
					s.setName(content[i][1]);
					s.setLinkMan(content[i][2]);
					Settlement scmodal = new Settlement();
					String name = (null == content[i][3] || "".equals(content[i][3].trim())) ? "" : content[i][3].trim();
					scmodal.setName(name);
					// Settlement sett =
					// settlementLogic.findAllSettlementByName(name);
					s.setSettlement(scmodal);
					s.setLinkPhone(content[i][4]);
					s.setNetworkLink(content[i][5]);
					s.setAddress(content[i][6]);
					s.setEndDate(content[i][7]);
					s.setIsCustom(false);
					s.setNote(content[i][8]);
					scmcocs.add(s);
				}
				List tlist = scmcocLogic.doValidata(getLoginUser(), scmcocs, settCache);
				result.setSuccess(true);
				result.setObj(tlist);
			}
		} catch (FileNotFoundException e) {
			result.setSuccess(false);
			result.setMsg("操作错误" + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Gson gson = new Gson();
		String str = gson.toJson(result);
		try {
			Writer writer = response.getWriter();
			writer.write(str);
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 删除错误信息
	 */
	public void clearErrorData() {
		try {
			List errorList = new ArrayList();
			AjaxResult result = new AjaxResult();
			result.setSuccess(false);
			net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(sendStr);
			List list = net.sf.json.JSONArray.toList(jsonArray, new TempScmcoc(), new JsonConfig());
			if (null != list && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					TempScmcoc ts = (TempScmcoc) list.get(i);
					if (null != ts.getErrorInfo() && !"".equals(ts.getErrorInfo().trim())) {
						errorList.add(ts);
					}
				}
				list.removeAll(errorList);
			}
			Gson gson = new Gson();
			result.setObj(list);
			result.setSuccess(true);
			String str = gson.toJson(result);
			PrintWriter out = null;
			response.setContentType("application/text");
			out = response.getWriter();
			out.write(str);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void saveExcelData() {
		PrintWriter out = null;
		AjaxResult result = new AjaxResult();
		try {
			net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(sendStr);
			List list = net.sf.json.JSONArray.toList(jsonArray, new TempScmcoc(), new JsonConfig());
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
			if (!this.scmcocLogic.doSaveExcelData(getLoginUser(), list, isScmcoc)) {
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

	}

	/**
	 * 填充对象
	 * 
	 * @param scmcoc
	 * @return
	 */
	private Scmcoc setProperty(Scmcoc scmcoc) {
		if (null != ids && !"".equals(ids)) {
			scmcoc.setId(ids);
		}
		scmcoc.setCode(parseValue(code));
		scmcoc.setName(parseValue(name));
		scmcoc.setLinkPhone(parseValue(linkPhone));
		scmcoc.setLinkMan(parseValue(linkMan));
		scmcoc.setNetworkLink(parseValue(networkLink));
		scmcoc.setAddress(parseValue(address));
		scmcoc.setEndDate(endDate);
		scmcoc.setNote(parseValue(note));
		if (null != settlementId && !"".equals(settlementId)) {
			// 查询结算方式
			Settlement stt = settlementLogic.findSettById(getLoginUser(), settlementId);
			scmcoc.setSettlement(stt);
		}
		return scmcoc;
	}

	/**
	 * 删除供应商或客户
	 * 
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		try {
			if (null != ids && !"".equals(ids)) {
				String[] arrIds = ids.split(",");
				if (null != arrIds && arrIds.length > 0) {
					List<String> list = new ArrayList<String>();
					for (String id : arrIds) {
						list.add(id);
					}
					this.scmcocLogic.delete(getLoginUser(), list);
				}
			}
			if ("true".equals(isCustom)) {
				return "save";// 是客户页面请求
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "save";// 是供应商请求
	}

	/**
	 * 根据id来查询供应商或客户
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findScmcocById() throws Exception {
		// 查询结算方式
		List<Settlement> settlements = this.settlementLogic.findAllSettlement(getLoginUser(), "");
		this.request.put("settlements", settlements);
		if (null != ids && !"".equals(ids)) {
			String[] arrIds = ids.split(",");
			if (null != arrIds && arrIds.length > 0) {
				String id = arrIds[0];
				Scmcoc scm = this.scmcocLogic.findScmcocById(getLoginUser(), id);
				if (null != scm) {
					this.request.put("scmcoc", scm);
				}
			}
		} else {
			this.request.put("scmcoc", new Scmcoc());
		}
		if ("true".equals(isCustom)) {
			return "findcis";// 是客户页面请求
		}
		return "findcoc";// 是供应商请求
	}

	private Integer pageCount(Integer maxIndex, Integer dataTotal) {
		pageNums = (dataTotal / DEFAULT_PAGESIZE) + (dataTotal % DEFAULT_PAGESIZE > 0 ? 1 : 0); // 总页数
		if (pageNums == 0) {
			pageNums += 1;
		}
		return pageNums;
	}

	private Boolean validataFile(Object[] obj) {
		Boolean flag = false;
		if (null != obj && obj.length > 0) {
			if ("编码".equals(obj[0]) && "客户/供应商名称".equals(obj[1]) && "联系人".equals(obj[2]) && "结算方式".equals(obj[3]) && "联系电话".equals(obj[4]) && "网络联系方式".equals(obj[5]) && "客户/供应商地址".equals(obj[6])
					&& "约定结算日期".equals(obj[7]) && "备注".equals(obj[8])) {
				flag = true;
			}
		}
		return flag;
	}

	/********* Getter and Setter method *********/
	public ScmcocLogic getScmcocLogic() {
		return scmcocLogic;
	}

	public void setScmcocLogic(ScmcocLogic scmcocLogic) {
		this.scmcocLogic = scmcocLogic;
	}

	public SettlementLogic getSettlementLogic() {
		return settlementLogic;
	}

	public void setSettlementLogic(SettlementLogic settlementLogic) {
		this.settlementLogic = settlementLogic;
	}

	public Scmcoc getScmcoc() {
		return scmcoc;
	}

	public void setScmcoc(Scmcoc scmcoc) {
		this.scmcoc = scmcoc;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

	public String getNetworkLink() {
		return networkLink;
	}

	public void setNetworkLink(String networkLink) {
		this.networkLink = networkLink;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getIsCustom() {
		return isCustom;
	}

	public void setIsCustom(String isCustom) {
		this.isCustom = isCustom;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	public String getSearchStr() {
		return searchStr;
	}

	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}

	public String getSettlementId() {
		return settlementId;
	}

	public void setSettlementId(String settlementId) {
		this.settlementId = settlementId;
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

	public TempStock getTemp() {
		return temp;
	}

	public void setTemp(TempStock temp) {
		this.temp = temp;
	}

	public String getIsScmcoc() {
		return isScmcoc;
	}

	public void setIsScmcoc(String isScmcoc) {
		this.isScmcoc = isScmcoc;
	}

}
