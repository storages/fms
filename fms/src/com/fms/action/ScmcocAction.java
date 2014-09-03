package com.fms.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fms.base.action.BaseAction;
import com.fms.core.entity.Scmcoc;
import com.fms.core.entity.Settlement;
import com.fms.logic.ScmcocLogic;
import com.fms.logic.SettlementLogic;
import com.fms.temp.TempStock;
import com.fms.utils.AjaxResult;
import com.fms.utils.ExcelUtil;
import com.google.gson.Gson;

public class ScmcocAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ScmcocLogic scmcocLogic;
	private SettlementLogic settlementLogic;
	private Scmcoc scmcoc;
	
	/****客户供应商属性*****/
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
	
	/*********分页用的属性***********/
	private Integer dataTotal;//总记录数
	private String currIndex;//当前页码
	private String maxIndex;//每页显示最多条数
	private Integer pageNums;//共有多少页
	private String className="Scmcoc";//表名称
	private String searchStr;//搜索条件
	private static final Integer DEFAULT_PAGESIZE = 11; 
	
	private File     uploadFile;         //上传的文件    名称是Form 对应的name 
	 private String   uploadFileContentType;   //文件的类型
	 private String   uploadFileFileName;    //文件的名称
	 //
	 private String sendStr; 

	private TempStock temp;

	/**
	 * 查询所有供应商或客户
	 * 
	 * @return
	 */
	public String findAllScmcoc() {
		// 是客户
		Integer curr = (null==currIndex || "".equals(currIndex))?1:Integer.parseInt(currIndex);//当前第几页
		Integer max = (null==maxIndex || "".equals(maxIndex))?1:Integer.parseInt(currIndex);//每页最多显示条数
		dataTotal = this.scmcocLogic.findDataCount(className,Boolean.parseBoolean(isCustom),parse(searchStr));
		List<Scmcoc> scmcocs = this.scmcocLogic.findAllScmcoc(Boolean.parseBoolean(isCustom),parse(searchStr),(curr-1)*DEFAULT_PAGESIZE,DEFAULT_PAGESIZE);
		this.request.put("scmcocs", scmcocs);
		this.request.put("currIndex", curr);
		this.request.put("maxIndex", max);
		this.request.put("pageNums", pageCount(max, dataTotal));
		this.request.put("searchStr", parse(searchStr));
		if("true".equals(isCustom)){
			return "cis";//是客户页面请求
		}
		return this.SUCCESS;//是供应商请求
	}

	/**
	 * 保存供应商或客户
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveScmcoc() {
		try{
		Scmcoc scmcoc = new Scmcoc();
		// 是客户
		if ("true".equals(isCustom)) {
			scmcoc.setIsCustom(Boolean.TRUE);
			// 是供应商
		} else if ("false".equals(isCustom)) {
			scmcoc.setIsCustom(Boolean.FALSE);
		}
		this.scmcocLogic.saveScmcoc(this.setProperty(scmcoc));
		System.out.println("保存成功");
		}catch(Exception e){
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
			Scmcoc sc = this.scmcocLogic.findScmcocByCode(code);
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
	 * 解析excel数据，并验证数据有效性
	 * @return
	 */
	public void importData() {
		AjaxResult result=new AjaxResult();
		result.setSuccess(false);
		try {
			//就这句，如何获取jsp页面传过来的文件
			String[][] content = ExcelUtil.readExcel(uploadFile, 1);
			List<Settlement> setList = this.settlementLogic.findAllSettlement(null);
			Map<String,Settlement> settCache = new HashMap<String,Settlement>();
			for(Settlement temp:setList){
				String key = temp.getName();
				settCache.put(key, temp);
			}
			List<Scmcoc> scmcocs = new ArrayList<Scmcoc>();
			for (int i = 0; i < content.length; i++) {
				Scmcoc s = new Scmcoc();
				s.setCode(content[i][0]);
				s.setName(content[i][1]);
				s.setLinkMan(content[i][2]);
				String name = (null==content[i][3] || "".equals(content[i][3].trim()))?"":content[i][3].trim();
				//Settlement sett = settlementLogic.findAllSettlementByName(name);
				s.setSettlement(settCache.get(name));
				s.setLinkPhone(content[i][4]);
				s.setNetworkLink(content[i][5]);
				s.setAddress(content[i][6]);
				s.setEndDate(content[i][7]);
				s.setIsCustom(false);
				s.setNote(content[i][8]);
				scmcocs.add(s);
			}
			List tlist = scmcocLogic.doValidata(scmcocs);
			result.setSuccess(true);
			result.setObj(tlist);
		} catch (FileNotFoundException e) {
			result.setSuccess(false);
			result.setMsg("操作错误"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Gson gson=new Gson();
		String str= gson.toJson(result);
	    try {
			Writer writer= response.getWriter();
			writer.write(str);
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
		if(null!=ids && !"".equals(ids)){
			scmcoc.setId(ids);
		}
		scmcoc.setCode(parse(code));
		scmcoc.setName(parse(name));
		scmcoc.setLinkPhone(parse(linkPhone));
		scmcoc.setLinkMan(parse(linkMan));
		scmcoc.setNetworkLink(parse(networkLink));
		scmcoc.setAddress(parse(address));
		scmcoc.setEndDate(endDate);
		scmcoc.setNote(parse(note));
		if(null!=settlementId && !"".equals(settlementId)){
			//查询结算方式
			Settlement stt = settlementLogic.findSettById(settlementId);
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
		if (null != ids && !"".equals(ids)) {
			String[] arrIds = ids.split(",");
			if(null!=arrIds && arrIds.length>0){
				List<String> list = new ArrayList<String>();
				for(String id:arrIds){
					list.add(id);
				}
				this.scmcocLogic.delete(list);
			}
		}
		if("true".equals(isCustom)){
			return "cis";//是客户页面请求
		}
		return "save";//是供应商请求
	}

	/**
	 * 根据id来查询供应商或客户
	 * @return
	 * @throws Exception
	 */
	public String findScmcocById() throws Exception {
		//查询结算方式
 		List<Settlement> settlements = this.settlementLogic.findAllSettlement("");
		this.request.put("settlements", settlements);
		if (null != ids && !"".equals(ids)) {
			String[] arrIds = ids.split(",");
			if (null != arrIds && arrIds.length > 0) {
				String id = arrIds[0];
				Scmcoc scm = this.scmcocLogic.findScmcocById(id);
				if (null != scm) {
					this.request.put("scmcoc", scm);
				}
			}
		}else{
			this.request.put("scmcoc", new Scmcoc());
		}
		if("true".equals(isCustom)){
			return "findcis";//是客户页面请求
		}
		return "findcoc";//是供应商请求
	}
	
	
	private Integer pageCount(Integer maxIndex,Integer dataTotal){
		pageNums = (dataTotal / DEFAULT_PAGESIZE) + (dataTotal % DEFAULT_PAGESIZE > 0 ? 1 : 0); // 总页数
		if(pageNums==0){
			pageNums+=1;
		}
		return pageNums;
	}
	
	/*********Getter and Setter method*********/
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
	
}
