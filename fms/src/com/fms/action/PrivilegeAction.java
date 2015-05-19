package com.fms.action;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import com.fms.base.action.BaseAction;
import com.fms.core.entity.OperateLogs;
import com.fms.core.entity.Privilege;
import com.fms.dao.PrivilegeDao;
import com.fms.logic.PrivilegeLogic;
import com.fms.utils.AjaxResult;
import com.fms.utils.Contents;
import com.google.gson.Gson;
import com.url.ajax.json.JSONObject;

public class PrivilegeAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  PrivilegeLogic privilegeLogic;
	private String searchText;
	private int pageindex;
	private Privilege  privilege;
	
	
	public String privileges(){
		List list =privilegeLogic.findAllPrivilege(getLoginUser(), searchText, (Contents.pageIndex-1)*Contents.pageRows, Contents.pageRows);
		int count=privilegeLogic.countListPrivilege(getLoginUser(), searchText);
		request.put("pagecount", count);
		request.put("privileges", list);
		request.put("searchText", searchText);
		return "privileges";
	}
	
	public void listAjax(){
		AjaxResult<List<Privilege>>  result=new AjaxResult();
		Writer writer=null;
		try{
			writer=response.getWriter();
		result.setSuccess(false);
		 List list =privilegeLogic.findAllPrivilege(getLoginUser(),searchText,( pageindex-1)*Contents.pageRows,Contents.pageRows);
		 result.setSuccess(true);
		 result.setObj(list);

		}catch(Exception e){
		 result.setMsg(e.getMessage());
		}
		 Gson son=new Gson();
		 String str= son.toJson(result);
		 try {
			writer.write(str);
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String addPrivilege(){
		return "addPivi";
	}
	public void save() throws Exception{
		Writer  writer=	null;
        AjaxResult  result=new AjaxResult();
        try{
       	 writer= response.getWriter();
       	 privilegeLogic.savePrivilege(getLoginUser(),privilege);
       	 result.setSuccess(true);
        }catch(Exception e){
       	 result.setSuccess(false);
       	 result.setMsg(e.getMessage());
       	 e.printStackTrace();
        }
        JSONObject  json=new JSONObject(result);
        writer.write(json.toString());
        writer.flush();
        writer.close();
	}
    public void edit() throws Exception{
    	Writer  writer=	null;
        AjaxResult  result=new AjaxResult();
        try{
       	 writer= response.getWriter();
       	 privilegeLogic.savePrivilege(getLoginUser(),privilege);
       	 result.setSuccess(true);
        }catch(Exception e){
       	 result.setSuccess(false);
       	 result.setMsg(e.getMessage());
       	 e.printStackTrace();
        }
        JSONObject  json=new JSONObject(result);
        writer.write(json.toString());
        writer.flush();
        writer.close();
	}

	public PrivilegeLogic getPrivilegeLogic() {
		return privilegeLogic;
	}

	public void setPrivilegeLogic(PrivilegeLogic privilegeLogic) {
		this.privilegeLogic = privilegeLogic;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public int getPageindex() {
		return pageindex;
	}

	public void setPageindex(int pageindex) {
		this.pageindex = pageindex;
	}

	public Privilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}
