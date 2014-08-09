package com.fms.action;

import java.util.List;

import com.fms.base.action.BaseAction;
import com.fms.core.entity.Department;
import com.fms.core.entity.Employee;
import com.fms.logic.DeptLogic;
import com.fms.logic.EmployeeLogic;
import com.fms.utils.AjaxResult;

public class EmployeeAction extends BaseAction {
	/**
	 * 
	 */
	Employee   empl;
	

	private static final long serialVersionUID = 1L;
	DeptLogic   deptLogic;
	EmployeeLogic  emplLogic;
	
	public String employees(){
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
    
    public AjaxResult   saveEmpl(){
         AjaxResult  result=new AjaxResult();
         try{
        	 emplLogic.saveEmpl(empl);
        	 result.setSuccess(false);
         }catch(Exception e){
        	 result.setSuccess(false);
        	 e.printStackTrace();
         }
    	return result;
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




}
