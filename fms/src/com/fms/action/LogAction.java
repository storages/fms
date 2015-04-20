package com.fms.action;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import com.fms.base.action.BaseAction;
import com.fms.core.entity.Employee;
import com.fms.core.entity.OperateLogs;
import com.fms.logic.EmployeeLogic;
import com.fms.logic.OperateLogsLogic;
import com.fms.utils.AjaxResult;
import com.google.gson.Gson;

public class LogAction  extends BaseAction{

	
    private	OperateLogsLogic operLogic;
    
    private int pageindex;
    
	private int pageReows=10;
    
    private String names;
    
    
	public String logs(){
		 List list =operLogic.findAllEmpl(getLoginUser(),names, 1, pageReows);
		 int count= operLogic.countListEmpl(getLoginUser(),names);
		 request.put("pagecount",count);
		 request.put("logs", list);
		 request.put("names", names);
		return "manager";
	}
	
	
	public void logsAjax(){
		AjaxResult<List<OperateLogs>>  result=new AjaxResult();
		Writer writer=null;
		try{
			writer=response.getWriter();
		result.setSuccess(false);
		 List list =operLogic.findAllEmpl(getLoginUser(),names, pageindex,pageReows);
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


	public OperateLogsLogic getOperLogic() {
		return operLogic;
	}


	public void setOperLogic(OperateLogsLogic operLogic) {
		this.operLogic = operLogic;
	}


	public int getPageindex() {
		return pageindex;
	}


	public void setPageindex(int pageindex) {
		this.pageindex = pageindex;
	}


	public int getPageReows() {
		return pageReows;
	}


	public void setPageReows(int pageReows) {
		this.pageReows = pageReows;
	}


	public String getNames() {
		return names;
	}


	public void setNames(String names) {
		this.names = names;
	}
	
	
	
	
}
