package com.fms.base.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.fms.utils.AjaxResult;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {
	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;

	/**
	 * 父类获取上下文
	 */
	protected ActionContext context = ActionContext.getContext();
	
	/**
	 * 父类获取Session
	 */
	protected Map session = context.getSession();
	
	/**
	 * 父类获取Request
	 */
	protected Map request = (Map) this.context.get("request");
	
	/**
	 * 父类获取HttpServletResponse
	 */
	protected HttpServletResponse response = ServletActionContext.getResponse();
	
	/**
	 * 处理ajax
	 */
	protected PrintWriter out = null;
	protected AjaxResult result = new AjaxResult();
	/**
	 * 翻译字符编码
	 * 
	 * @param value
	 * @return
	 */
	protected String parseValue(String value) {
		if(null!=value && !"".equals(value)){
			try {
				return URLDecoder.decode(value, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
protected List<List<String>> parseJsonArr(String str) {
	JSONArray jsonArr = JSONArray.fromObject(str);
	String [] arr = jsonArr.toString().split("],");
	List<List<String>> list = new ArrayList<List<String>>();
	for(int i=0;i<arr.length;i++){
		String newstr = arr[i].replace('[', ' ').replace(']', ' ').replace('"', ' ').replace('　', ' ').trim();
		List<String> values = new ArrayList<String>();
		String [] arrs = newstr.split(",");
		for(int j=0;j<arrs.length;j++){
			try {
				values.add(URLDecoder.decode(arrs[j].toString().trim(),"utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		list.add(values);
	}
	return list;
}	

/**
 * 判断是否是数字
 * @param str
 * @return
 */
public static boolean isNumeric(String str){ 
	String reg = "\\d+(\\.\\d+)?"; 
    return str.matches(reg);  
 } 

	
	/**
	 * 前台传来的json格式字符串
	 */
	protected String jsonstr;

	public String getJsonstr() {
		return jsonstr;
	}

	public void setJsonstr(String jsonstr) {
		this.jsonstr = jsonstr;
	}
	
	
}
