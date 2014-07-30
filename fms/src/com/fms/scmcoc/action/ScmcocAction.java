package com.fms.scmcoc.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fms.base.action.BaseAction;
import com.fms.core.entity.Scmcoc;
import com.fms.scmcoc.logic.ScmcocLogic;

public class ScmcocAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ScmcocLogic scmcocLogic;
	private Scmcoc scmcoc;
	private String code;
	private String name;
	private String linkPhone;
	private String networkLink;
	private String address;
	private String linkMan;
	private String endDate;
	private String isCustom;
	private String note;

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

	/**
	 * 查询所有供应商或客户
	 * 
	 * @return
	 */
	public String findAllScmcoc() {
		// 是客户
		List<Scmcoc> scmcocs = this.scmcocLogic.findAllScmcoc(Boolean.parseBoolean(isCustom));
		this.request.put("scmcocs", scmcocs);
		return this.SUCCESS;
	}

	/**
	 * 保存供应商或客户
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveScmcoc() throws Exception {
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
		scmcoc.setCode(parse(code));
		scmcoc.setName(parse(name));
		scmcoc.setLinkPhone(parse(linkPhone));
		scmcoc.setLinkMan(parse(linkMan));
		scmcoc.setNetworkLink(parse(networkLink));
		scmcoc.setAddress(parse(address));
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
		try {
			Date date = sdf.parse(endDate);
			scmcoc.setEndDate(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
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
		try {
			return URLDecoder.decode(value, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
