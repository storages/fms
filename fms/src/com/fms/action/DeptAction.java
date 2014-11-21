package com.fms.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import com.fms.base.action.BaseAction;
import com.fms.core.entity.Department;
import com.fms.logic.DeptLogic;
import com.fms.utils.AjaxResult;
import com.url.ajax.json.JSONObject;

public class DeptAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DeptLogic deptLogic;
	
	private String ids;
	
	/*********部门实体类属性*************/
	private String code;
	private String name;
	private String note;
	
	
	/*********分页用的属性***********/
	private Integer dataTotal;//总记录数
	private String currIndex;//当前页码
	private String maxIndex;//每页显示最多条数
	private Integer pageNums;//共有多少页
	private String className="Department";//表名称
	private String searchStr;//搜索条件
	private static final Integer DEFAULT_PAGESIZE = 10; 
	
	
	
	public String findAllDept() throws Exception{
		Integer curr = (null==currIndex || "".equals(currIndex))?1:Integer.parseInt(currIndex);//当前第几页
		Integer max = (null==maxIndex || "".equals(maxIndex))?1:Integer.parseInt(currIndex);//每页最多显示条数
		dataTotal = this.deptLogic.findDataCount(className,parseValue(searchStr));
		List<Department> depts = this.deptLogic.findAllDept(parseValue(searchStr),(curr-1)*DEFAULT_PAGESIZE,DEFAULT_PAGESIZE);
		request.put("depts", depts);
		this.request.put("currIndex", curr);
		this.request.put("maxIndex", max);
		this.request.put("pageNums", pageCount(max, dataTotal));
		this.request.put("searchStr", parseValue(searchStr));
		return this.SUCCESS;
	}

	
	private Integer pageCount(Integer maxIndex,Integer dataTotal){
		pageNums = (dataTotal / DEFAULT_PAGESIZE) + (dataTotal % DEFAULT_PAGESIZE > 0 ? 1 : 0); // 总页数
		if(pageNums==0){
			pageNums+=1;
		}
		return pageNums;
	}
	
	
	public String findDeptByid() throws Exception{
		if (null != ids && !"".equals(ids)) {
			String[] arrIds = ids.split(",");
			if (null != arrIds && arrIds.length > 0) {
				String id = arrIds[0];
				Department dep = this.deptLogic.findDeptById(id);
				if (null != dep) {
					this.request.put("dept", dep);
				}
			}
		}
		return "find";
	}
	
	/**
	 * 保存部门信息
	 * @return
	 * @throws Exception
	 */
	public String saveDept() throws Exception{
		this.deptLogic.saveDept(this.setProperty(new Department()));
		return "save";
	}
	
	
	
	
	/**
	 * 填充对象
	 * 
	 * @param scmcoc
	 * @return
	 */
	private Department setProperty(Department dept) {
		if(null!=ids && !"".equals(ids)){
			dept.setId(ids);
		}
		dept.setCode(parseValue(code));
		dept.setName(parseValue(name));
		dept.setNote(parseValue(note));
		return dept;
	}

	/**
	 * 验证部门编码是否重复
	 */
	public void findDeptByCode(){
		PrintWriter out = null;
		AjaxResult  result=new AjaxResult();
		try {
			out = response.getWriter();
			response.setContentType("application/text");
			response.setCharacterEncoding("UTF-8");
			String findCode = this.deptLogic.findDeptByCode(code);
			if(null!=findCode){
				result.setSuccess(false);
				result.setMsg("编码已使用过了！");
			}else{
				result.setSuccess(true);
			}			
			JSONObject json=new JSONObject(result);
			out.println(json.toString());
			out.flush();
		} catch (IOException e) {
			result.setMsg("对不起出错了：/n"+e.getMessage());
		}finally{
			if(out!=null){
				out.close();
			}
		}
	}
	
	/**
	 * 删除部门信息
	 * @return
	 */
	public void deleteDept(){
		
		if (null != ids && !"".equals(ids)) {
			String [] idArr = ids.split(",");
			if(idArr!=null && idArr.length>0){
				PrintWriter out = null;
				AjaxResult  result=new AjaxResult();
				try {
					out = response.getWriter();
					response.setContentType("application/text");
					response.setCharacterEncoding("UTF-8");
					this.deptLogic.delDeptById(idArr);
					result.setSuccess(true);
					result.setMsg("删除成功！");
					JSONObject json=new JSONObject(result);
					out.println(json.toString());
					out.flush();
					out.close();
				} catch (Exception e) {
					result.setSuccess(false);
					result.setMsg("数据被其它地方引用，不能删除！");
					JSONObject json=new JSONObject(result);
					out.println(json.toString());
					out.flush();
					out.close();
				}
			}
		}
	}
	
	public DeptLogic getDeptLogic() {
		return deptLogic;
	}

	public void setDeptLogic(DeptLogic deptLogic) {
		this.deptLogic = deptLogic;
	}


	public String getIds() {
		return ids;
	}


	public void setIds(String ids) {
		this.ids = ids;
	}


	public Integer getDataTotal() {
		return dataTotal;
	}


	public void setDataTotal(Integer dataTotal) {
		this.dataTotal = dataTotal;
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


	public Integer getPageNums() {
		return pageNums;
	}


	public void setPageNums(Integer pageNums) {
		this.pageNums = pageNums;
	}


	public String getSearchStr() {
		return searchStr;
	}


	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
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


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}
	
}
