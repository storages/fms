package com.fms.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JsonConfig;

import com.fms.base.action.BaseAction;
import com.fms.commons.ImgExgFlag;
import com.fms.core.entity.BomExg;
import com.fms.core.entity.BomImg;
import com.fms.core.entity.BomVersion;
import com.fms.core.entity.Material;
import com.fms.logic.BomLogic;
import com.fms.logic.MaterialLogic;
import com.fms.temp.TempBom;
import com.fms.utils.AjaxResult;
import com.fms.utils.ExcelUtil;
import com.fms.utils.MathUtils;
import com.google.gson.Gson;
import com.url.ajax.json.JSONException;
import com.url.ajax.json.JSONObject;

/**
 * Bom表
 * 
 * @author Administrator
 * 
 */
public class BomAction extends BaseAction {

	/********* 分页用的属性 ***********/
	private Integer dataTotal;// 总记录数
	private String currIndex;// 当前页码
	private String maxIndex;// 每页显示最多条数
	private Integer pageNums;// 共有多少页
	private String className = "BomExg";// 表名称
	private String searchStr;// 搜索条件
	private static final Integer DEFAULT_PAGESIZE = 10;

	/********* 其它属性 ***********/
	private String hsCode;
	private String hsModel;
	private String hsName;
	private String ids;// 物料的id,有可能是多个，用/分开
	private String verNo;// 版本号
	private String hid;// BomExg的id
	/**
	 * 前台传来的json格式字符串
	 */
	protected String jsonstr;

	/********* 获取前台选择的文件 ***********/
	private File uploadFile; // 上传的文件 名称是Form 对应的name
	private String uploadFileContentType; // 文件的类型
	private String uploadFileFileName; // 文件的名称
	private String sendStr;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected BomLogic bomLogic;
	protected MaterialLogic materLogic;

	/**
	 * 查找bom成品表
	 * 
	 * @return
	 */
	public String findBomExg() {
		try {
			Integer curr = (null == currIndex || "".equals(currIndex)) ? 1 : Integer.parseInt(currIndex);// 当前第几页
			Integer max = (null == maxIndex || "".equals(maxIndex)) ? 1 : Integer.parseInt(currIndex);// 每页最多显示条数
			dataTotal = this.bomLogic.findDataCount(getLoginUser(), className, parseValue(hsName), parseValue(hsCode), parseValue(hsModel));
			List<BomExg> bomExgs = bomLogic.findBomExg(getLoginUser(), parseValue(hsName), parseValue(hsCode), parseValue(hsModel), (curr - 1) * DEFAULT_PAGESIZE, DEFAULT_PAGESIZE);
			this.request.put("bomExgs", bomExgs);
			this.request.put("currIndex", curr);
			this.request.put("maxIndex", max);
			this.request.put("pageNums", pageCount(max, dataTotal));
			this.request.put("hsName", parseValue(hsName));
			this.request.put("hsCode", parseValue(hsCode));
			this.request.put("hsModel", parseValue(hsModel));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return this.SUCCESS;
	}

	/**
	 * 加载物料清单中的成品信息，并跳转到新增页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String addExgData() throws Exception {
		List<Material> exgList = materLogic.findMaterialExgs(getLoginUser(), hsCode, hsName, hsModel, ImgExgFlag.EXG);
		this.request.put("exgList", exgList);
		this.request.put("hsName", parseValue(hsName));
		this.request.put("hsCode", parseValue(hsCode));
		this.request.put("hsModel", parseValue(hsModel));
		return "add";
	}

	/**
	 * 保存bom成品表
	 */
	public void saveBomExg() {
		String[] idArr = null;
		PrintWriter out = null;
		String err = "";
		AjaxResult result = new AjaxResult();
		try {
			out = response.getWriter();
			response.setContentType("application/text");
			response.setCharacterEncoding("UTF-8");
			if (ids != null && !"".equals(ids)) {
				List<BomExg> list = new ArrayList<BomExg>();
				idArr = ids.split("/");
				List<Material> exgMaterials = this.materLogic.findMaterialById(getLoginUser(), idArr);
				for (Material mat : exgMaterials) {
					BomExg bomExg = new BomExg();
					bomExg.setMaterial(mat);
					list.add(bomExg);
				}
				list = this.bomLogic.saveBomExg(getLoginUser(), list);
				result.setSuccess(true);
				JSONObject json = new JSONObject(result);
				out.println(json.toString());
				out.flush();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			err = "程序异常";
			result.setMsg(err);
			result.setSuccess(false);
			JSONObject json = new JSONObject(result);
			out.println(json.toString());
			out.flush();
		}
	}

	/**
	 * 删除BOM成品表
	 */
	public void delBomExg() {
		if (null != ids && !"".equals(ids)) {
			String[] idArr = ids.split(",");
			if (idArr != null && idArr.length > 0) {
				PrintWriter out = null;
				AjaxResult result = new AjaxResult();
				try {
					out = response.getWriter();
					response.setContentType("application/text");
					response.setCharacterEncoding("UTF-8");
					this.bomLogic.delBomExgByIds(getLoginUser(), idArr);
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
	 * 显示bom清单列表 ids bomExg的id
	 * 
	 * @return
	 */
	public String detailList() {
		try {
			if (ids != null && !"".equals(ids)) {
				if (!isNumeric(verNo)) {
					verNo = "-1";
				}
				List<BomImg> imgList = this.bomLogic.findBomImg(hsName, hsCode, hsModel, ids, verNo == null ? 1 : Integer.parseInt(verNo), -1, -1);
				List<BomVersion> verList = this.bomLogic.findBomVersion(ids);
				this.request.put("verList", verList);
				this.request.put("imgList", imgList);
				this.request.put("hsName", parseValue(hsName));
				this.request.put("hsCode", parseValue(hsCode));
				this.request.put("hsModel", parseValue(hsModel));
				this.request.put("verNo", Integer.parseInt(verNo));
				this.request.put("hid", ids);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "imglist";
	}

	public String findMaterial() {
		List<Material> mlist = materLogic.findAllMaterialInfoByHsCode(hsCode, ImgExgFlag.IMG, -1, -1);
		this.request.put("mlist", mlist);
		this.request.put("hsCode", hsCode);
		return "dialog";
	}

	/**
	 * ids 选择的物料多个id hid bomExg的id
	 * 
	 * @return
	 */
	public String findMaterialByIds() {
		try {
			if (null != ids && !"".equals(ids)) {
				String[] idArr = ids.split("/");
				List<Material> mList = materLogic.findMaterialById(getLoginUser(), idArr);// 查物料
				BomExg bomExg = this.bomLogic.findBomExgById(hid);// 查询BomExg
				BomVersion bomVersion = this.bomLogic.findBomVersionByVerNo(Integer.parseInt(verNo));// 查询Bomversion
				bomVersion.setBomExg(bomExg);
				List<BomImg> imgList = new ArrayList<BomImg>();
				for (Material m : mList) {
					BomImg bomImg = new BomImg();
					bomImg.setBomVersion(bomVersion);
					bomImg.setMaterial(m);
					imgList.add(bomImg);
				}
				imgList = this.bomLogic.saveBomImgs(imgList);
				this.request.put("verList", this.bomLogic.findBomVersion(hid));
				this.request.put("verNo", bomVersion.getVersionNo());
				List<BomImg> bomImgList = this.bomLogic.findBomImg(hsName, hsCode, hsModel, hid, verNo == null ? 1 : Integer.parseInt(verNo), -1, -1);
				this.request.put("hid", hid);
				this.request.put("imgList", bomImgList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "imglist";
	}

	public String showImport() {
		this.request.put("hid", hid);
		this.request.put("verNo", verNo);
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
			// 就这句，如何获取jsp页面传过来的文件
			String[][] content = ExcelUtil.readExcel(uploadFile, 0);
			String[] title = new String[5];
			title[0] = content[0][0];
			title[1] = content[0][1];
			title[2] = content[0][2];
			title[3] = content[0][3];
			title[4] = content[0][4];

			if (!"原料编码".equals(title[0]) && !"原料批次号".equals(title[1]) || !"单耗".equals(title[2]) || !"损耗".equals(title[3]) || !"备注".equals(title[4])) {
				result.setSuccess(false);
				result.setMsg("导入的excel文件内容不正确!");
			} else {
				List<TempBom> tempBoms = new ArrayList<TempBom>();
				for (int i = 1; i < content.length; i++) {
					TempBom bom = new TempBom();
					bom.setImgCode(content[i][0]);
					bom.setImgBetchNo(content[i][1]);
					if (!isNumeric(content[i][2])) {
						bom.setConsumption(-1.0);
					} else if (null == content[i][2] || "".equals(content[i][2].trim())) {
						bom.setConsumption(null);
					} else {
						bom.setConsumption(Double.parseDouble(content[i][2].trim()));
					}
					if (!isNumeric(content[i][3])) {
						bom.setWastage(null);
					} else if (null == content[i][3] || "".equals(content[i][3].trim())) {
						bom.setWastage(null);
					} else {
						bom.setWastage(Double.parseDouble(content[i][3].trim()));
					}
					bom.setNote(content[i][4]);
					tempBoms.add(bom);
				}
				List tlist = this.bomLogic.doValidata(tempBoms, hid, Integer.parseInt(verNo));
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
	 * 清除错误的数据
	 * 
	 * @return
	 */
	public void clearErrorData() {
		List errorList = new ArrayList();
		AjaxResult result = new AjaxResult();
		result.setSuccess(false);
		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(sendStr);
		List list = net.sf.json.JSONArray.toList(jsonArray, new TempBom(), new JsonConfig());
		if (null != list && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				TempBom bom = (TempBom) list.get(i);
				if (null != bom.getErrorInfo() && !"".equals(bom.getErrorInfo().trim())) {
					errorList.add(bom);
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
			List<TempBom> list = net.sf.json.JSONArray.toList(jsonArray, new TempBom(), new JsonConfig());
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
			if (!this.bomLogic.doSaveExcelData(list, Integer.parseInt(verNo), hid)) {
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

	/**
	 * 添加BOM版本号
	 */
	public String addVersion() {
		try {
			if (ids != null && !"".equals(ids)) {
				Integer bomNo = this.bomLogic.findBomVersionNoByHead(ids);
				if (bomNo == null || bomNo < 0) {
					bomNo = 1;
				} else {
					bomNo += 1;
				}
				BomVersion bomVersion = new BomVersion();
				bomVersion.setVersionNo(bomNo);
				BomExg bomExg = this.bomLogic.findBomExgById(ids);
				bomVersion.setBomExg(bomExg);
				SimpleDateFormat df = new SimpleDateFormat("yyyy-dd-MM");
				bomVersion.setNote(df.format(new Date()));
				bomVersion = this.bomLogic.saveBomVersion(bomVersion);
				List<BomImg> imgList = this.bomLogic.findBomImg(hsName, hsCode, hsModel, ids, Integer.parseInt((verNo == null || "".equals(verNo)) ? bomVersion.getVersionNo().toString() : verNo), -1,
						-1);
				List<BomVersion> verList = this.bomLogic.findBomVersion(ids);
				this.request.put("verList", verList);
				this.request.put("imgList", imgList);
				this.request.put("hsName", parseValue(hsName));
				this.request.put("hsCode", parseValue(hsCode));
				this.request.put("hsModel", parseValue(hsModel));
				this.request.put("hid", ids);
				this.request.put("verNo", bomVersion.getVersionNo());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "imglist";
	}

	/**
	 * 删除BOM版本
	 * 
	 * @return
	 */
	public String delVersion() {
		try {
			if (ids != null && !"".equals(ids) && verNo != null && !"".equals(verNo)) {
				this.bomLogic.delVersion(ids, Integer.parseInt(verNo));
				List<BomImg> imgList = this.bomLogic.findBomImg(hsName, hsCode, hsModel, ids, Integer.parseInt(verNo), -1, -1);
				List<BomVersion> verList = this.bomLogic.findBomVersion(ids);
				this.request.put("verList", verList);
				this.request.put("imgList", imgList);
				this.request.put("hsName", parseValue(hsName));
				this.request.put("hsCode", parseValue(hsCode));
				this.request.put("hsModel", parseValue(hsModel));
				this.request.put("hid", ids);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "imglist";
	}

	private Integer pageCount(Integer maxIndex, Integer dataTotal) {
		pageNums = (dataTotal / DEFAULT_PAGESIZE) + (dataTotal % DEFAULT_PAGESIZE > 0 ? 1 : 0); // 总页数
		if (pageNums == 0) {
			pageNums += 1;
		}
		return pageNums;
	}

	/**
	 * 修改BomImg,并保存（带验证）
	 */
	public void saveBomImg() {
		try {
			PrintWriter out = response.getWriter();
			AjaxResult result = new AjaxResult();
			response.setContentType("application/text");
			response.setCharacterEncoding("UTF-8");
			Boolean flag = true;
			List<BomImg> bomImgList = new ArrayList<BomImg>();
			StringBuilder err = new StringBuilder();
			List<List<String>> list = this.parseJsonArr(jsonstr);
			if (null != list && list.size() > 0) {
				for (List<String> ldata : list) {
					List<String> contents = ldata;
					if (null != contents && contents.size() > 0) {
						BomImg bomImg = this.bomLogic.findBomImgById(contents.get(0).trim());
						err.append("原料料号为【" + bomImg.getMaterial().getHsCode() + "】的");
						if (!isNumeric(contents.get(1).trim())) {
							err.append("单耗不是数字</br>");
							flag = false;
						}
						// 验证损耗是否为数字
						if (!isNumeric(contents.get(2).trim())) {
							err.append("损耗不是数字</br>");
							flag = false;
						}
						// 验证通过后，自动计算单项用量(单项用量=单耗/(1-损耗))
						if (flag) {
							bomImg.setUnitConsume(Double.parseDouble(contents.get(1).trim()));
							bomImg.setWastRate(Double.parseDouble(contents.get(2).trim()));
							Double d = MathUtils.subtract(1d, bomImg.getWastRate());
							bomImg.setUnitDosage(MathUtils.dividend(bomImg.getUnitConsume(), d, 3));
							bomImg.setNote("empty".equals(contents.get(3)) ? null : contents.get(3).trim());
							bomImgList.add(bomImg);
							result.setSuccess(flag);
						} else {
							result.setMsg(err.toString());
							result.setSuccess(flag);
						}
					}
				}
				this.bomLogic.saveBomImgs(bomImgList);
				JSONObject json = new JSONObject(result);
				out.println(json.toString());
				out.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除BomImg
	 */
	public void delBomImg() {
		try {
			if (ids != null && !"".equals(ids) && verNo != null && !"".equals(verNo)) {
				String[] idArr = ids.split(",");
				if (idArr != null && idArr.length > 0) {
					PrintWriter out = null;
					AjaxResult result = new AjaxResult();
					try {
						out = response.getWriter();
						response.setContentType("application/text");
						response.setCharacterEncoding("UTF-8");
						this.bomLogic.delBomImgByIds(idArr);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public BomLogic getBomLogic() {
		return bomLogic;
	}

	public void setBomLogic(BomLogic bomLogic) {
		this.bomLogic = bomLogic;
	}

	public MaterialLogic getMaterLogic() {
		return materLogic;
	}

	public void setMaterLogic(MaterialLogic materLogic) {
		this.materLogic = materLogic;
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

	public String getSearchStr() {
		return searchStr;
	}

	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}

	public String getHsCode() {
		return hsCode;
	}

	public void setHsCode(String hsCode) {
		this.hsCode = hsCode;
	}

	public String getHsModel() {
		return hsModel;
	}

	public void setHsModel(String hsModel) {
		this.hsModel = hsModel;
	}

	public Integer getPageNums() {
		return pageNums;
	}

	public void setPageNums(Integer pageNums) {
		this.pageNums = pageNums;
	}

	public String getHsName() {
		return hsName;
	}

	public void setHsName(String hsName) {
		this.hsName = hsName;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
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

	public String getVerNo() {
		return verNo;
	}

	public void setVerNo(String verNo) {
		this.verNo = verNo;
	}

	public String getHid() {
		return hid;
	}

	public void setHid(String hid) {
		this.hid = hid;
	}

	public String getJsonstr() {
		return jsonstr;
	}

	public void setJsonstr(String jsonstr) {
		this.jsonstr = jsonstr;
	}
}
