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
	
	/****�ͻ���Ӧ������*****/
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
	
	/*********��ҳ�õ�����***********/
	private Integer dataTotal;//�ܼ�¼��
	private String currIndex;//��ǰҳ��
	private String maxIndex;//ÿҳ��ʾ�������
	private Integer pageNums;//���ж���ҳ
	private String className="Scmcoc";//������
	private String searchStr;//��������
	private static final Integer DEFAULT_PAGESIZE = 11; 

	/**
	 * ��ѯ���й�Ӧ�̻�ͻ�
	 * 
	 * @return
	 */
	public String findAllScmcoc() {
		// �ǿͻ�
		Integer curr = (null==currIndex || "".equals(currIndex))?1:Integer.parseInt(currIndex);//��ǰ�ڼ�ҳ
		Integer max = (null==maxIndex || "".equals(maxIndex))?1:Integer.parseInt(currIndex);//ÿҳ�����ʾ����
		dataTotal = this.scmcocLogic.findDataCount(className,Boolean.parseBoolean(isCustom),parse(searchStr));
		List<Scmcoc> scmcocs = this.scmcocLogic.findAllScmcoc(Boolean.parseBoolean(isCustom),parse(searchStr),(curr-1)*DEFAULT_PAGESIZE,DEFAULT_PAGESIZE);
		this.request.put("scmcocs", scmcocs);
		this.request.put("currIndex", curr);
		this.request.put("maxIndex", max);
		this.request.put("pageNums", pageCount(max, dataTotal));
		this.request.put("searchStr", parse(searchStr));
		if("true".equals(isCustom)){
			return "cis";//�ǿͻ�ҳ������
		}
		return this.SUCCESS;//�ǹ�Ӧ������
	}

	/**
	 * ���湩Ӧ�̻�ͻ�
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveScmcoc() {
		Scmcoc scmcoc = new Scmcoc();
		// �ǿͻ�
		if ("true".equals(isCustom)) {
			scmcoc.setIsCustom(Boolean.TRUE);
			// �ǹ�Ӧ��
		} else if ("false".equals(isCustom)) {
			scmcoc.setIsCustom(Boolean.FALSE);
		}
		this.scmcocLogic.saveScmcoc(this.setProperty(scmcoc));
		return "save";
	}

	/**
	 * ���ݱ�������ѯ��Ӧ�̻�ͻ�
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
	 * ������
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
	 * �����ַ�����
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
	 * ɾ����Ӧ�̻�ͻ�
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
	 * ����id����ѯ��Ӧ�̻�ͻ�
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
		pageNums = (dataTotal / DEFAULT_PAGESIZE) + (dataTotal % DEFAULT_PAGESIZE > 0 ? 1 : 0); // ��ҳ��
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
