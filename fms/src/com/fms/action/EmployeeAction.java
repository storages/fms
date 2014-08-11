package com.fms.action;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

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
	/**
	 * 判断是可以登录
	 */
	private  boolean isloginUser;
	
	private AclUser user;
	
	

	private static final long serialVersionUID = 1L;
    private	DeptLogic   deptLogic;
    private	EmployeeLogic  emplLogic;
	
	public String employees(){
		emplLogic.loadEmployee();
		
		return "manager";
	}
	
	/**
	 * 到增加员工页面
	 * @return
	 */
    public String addEmployee(){
    	 List<Department>  depts=deptLogic.findDept();
    	 request.put("depts", depts);
    	return "addemp";
    }	
    
    public void  saveEmpl() throws IOException{
    	Writer  writer=	null;
         AjaxResult  result=new AjaxResult();
         try{
        	 writer= response.getWriter();
        	 emplLogic.saveEmplAndUser(empl, isloginUser, user);
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

	public boolean isIsloginUser() {
		return isloginUser;
	}

	public void setIsloginUser(boolean isloginUser) {
		this.isloginUser = isloginUser;
	}

	public AclUser getUser() {
		return user;
	}

	public void setUser(AclUser user) {
		this.user = user;
	}




}
