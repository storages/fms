package com.fms.action;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.fms.base.action.BaseAction;
import com.fms.core.entity.AclUser;
import com.fms.core.entity.Department;
import com.fms.core.entity.Employee;
import com.fms.logic.DeptLogic;
import com.fms.logic.EmployeeLogic;
import com.fms.utils.AjaxResult;
import com.url.ajax.json.JSONObject;

public class EmployeeAction extends BaseAction {
	/**
	 * 
	 */
	private  Employee   empl;
	
	private AclUser user;
	
	private int pageindex;
	
	private int pageReows=2;
	
	

	private static final long serialVersionUID = 1L;
    private	DeptLogic   deptLogic;
    private	EmployeeLogic  emplLogic;
    
    private String emid;
	
	public String employees(){
		 List list =emplLogic.findAllEmpl(null, 1, pageReows);
		 int count= emplLogic.countListEmpl(null);
		 request.put("pagecount",count);
		 request.put("empls", list);
		return "manager";
	}
	
	
	public void employeesAjax(){
		AjaxResult  result=new AjaxResult();
		Writer writer=null;
		try{
			writer=response.getWriter();
		result.setSuccess(false);
		 List list =emplLogic.findAllEmpl(null, pageindex,pageReows);
		 result.setSuccess(true);
		 result.setObj(list);

		}catch(Exception e){
		 result.setMsg(e.getMessage());
		}
		 JSONObject json=new JSONObject(result);
		 try {
			writer.write(json.toString());
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 到增加员工页面
	 * @return
	 */
    public String addEmployee(){
    	 List<Department>  depts=deptLogic.findDept();
    	 request.put("depts", depts);
    	 HttpServletRequest req = ServletActionContext.getRequest();
    	 String scheme = req.getScheme();
    	 String path = scheme+"://"+req.getServerName()+":"+req.getServerPort()+"/photo/";
    	 req.setAttribute("loadPath",path);
    	return "addemp";
    }	
	/**
	 * 修改员工
	 * @return
	 */
    public String editEmployee(){
    	 List<Department>  depts=deptLogic.findDept();
    	 request.put("depts", depts);
    	 HttpServletRequest req = ServletActionContext.getRequest();
    	 if(emid!=null){
    		Employee empl= emplLogic.getEmplById(emid);
    		 request.put("empl", empl);
    	 }
    	 String scheme = req.getScheme();
    	 String path = scheme+"://"+req.getServerName()+":"+req.getServerPort()+"/photo/";
    	 req.setAttribute("loadPath",path);
    	return "addemp";
    }	
    
    public void  saveEmpl() throws IOException{
    	Writer  writer=	null;
         AjaxResult  result=new AjaxResult();
         try{
        	 writer= response.getWriter();
        	 if(null!=empl.getId()&&!"".equals(empl.getId())){
        		 //修改
        		 result.setMsg("update");
        	 }else{
        		 //新增
        		 result.setMsg("add");
        	 }
        	 emplLogic.saveEmplAndUser(empl, empl.isWfloginUser(), user);
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	
	/**
	 * service  get set方法
	 * @return
	 */
	public Employee getEmpl() {
		return empl;
	}

	public void setEmpl(Employee empl) {
		this.empl = empl;
	}

	

	public EmployeeLogic getEmplLogic() {
		return emplLogic;
	}

	public void setEmplLogic(EmployeeLogic emplLogic) {
		this.emplLogic = emplLogic;
	}

	public DeptLogic getDeptLogic() {
		return deptLogic;
	}

	public void setDeptLogic(DeptLogic deptLogic) {
		this.deptLogic = deptLogic;
	}

	public AclUser getUser() {
		return user;
	}

	public void setUser(AclUser user) {
		this.user = user;
	}

	public String getEmid() {
		return emid;
	}

	public void setEmid(String emid) {
		this.emid = emid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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




}
