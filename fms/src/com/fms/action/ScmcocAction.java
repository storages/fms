package com.fms.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fms.base.action.BaseAction;
import com.fms.core.entity.Scmcoc;
import com.fms.logic.ScmcocLogic;

public class ScmcocAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ScmcocLogic scmcocLogic;
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
	private String note;
	
	/*********分页用的属性***********/
	private Integer dataTotal;//总记录数
	private String currIndex;//当前页码
	private String maxIndex;//每页显示最多条数
	private Integer pageNums;//共有多少页
	private String className="Scmcoc";//表名称
	private String searchStr;//搜索条件
	private static final Integer DEFAULT_PAGESIZE = 11; 

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
		Scmcoc scmcoc = new Scmcoc();
		// 是客户
		if ("true".equals(isCustom)) {
			scmcoc.setIsCustom(Boolean.TRUE);
			// 是供应商
		} else if ("false".equals(isCustom)) {
			scmcoc.setIsCustom(Boolean.FALSE);
		}
		this.scmcocLogic.saveScmcoc(this.setProperty(scmcoc));
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
		return scmcoc;
	}

	/**
	 * 翻译字符编码
	 * 
	 * @param value
	 * @return
	 */
	private String parse(String value) {
		if(null!=value && !"".equals(value)){
			try {
				return URLDecoder.decode(value, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return null;
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
		return "save";
	}

	/**
	 * 根据id来查询供应商或客户
	 * @return
	 * @throws Exception
	 */
	public String findScmcocById() throws Exception {
		if (null != ids && !"".equals(ids)) {
			String[] arrIds = ids.split(",");
			if (null != arrIds && arrIds.length > 0) {
				String id = arrIds[0];
				Scmcoc scm = this.scmcocLogic.findScmcocById(id);
				if (null != scm) {
					this.request.put("scmcoc", scm);
				}
			}
		}
		return "find";
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
	
}
