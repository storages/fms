package com.fms.user.action;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.fms.base.action.BaseAction;
import com.fms.core.entity.AclUser;
import com.fms.user.logic.AclUserLogic;
import com.fms.utils.AjaxResult;
import com.opensymphony.xwork2.ActionContext;
import com.url.ajax.json.JSONObject;

public class AclUserAction extends BaseAction {

	private AclUser user;
	private AclUserLogic userLogic;
	private String userName;
	private String password;
	protected String ids;
	protected String forget;//��ס����
	/**
	 *�Ƿ���û������û��˻�����"false"��ʾ����;"true"��ʾ���á� 
	 */
	private String userForbid;//�Ƿ���û������û��˻�����"false"��ʾ����;"true"��ʾ���á�

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ����Action
	 * 
	 * @return
	 */
	public String gologin() {
		return "login";
	}

	/**
	 * �û���¼��ͨ��ajax��
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void loginUser() throws Exception {
		
		PrintWriter out = null;
		AjaxResult  result=new AjaxResult();
		
		try {
			out = response.getWriter();
			response.setContentType("application/text");
			response.setCharacterEncoding("UTF-8");
			AclUser aclUser = userLogic.loginAclUser(userName, password);
			if(aclUser==null){
				result.setSuccess(false);
				result.setMsg("�û��������벻��ȷ");
			}else if(aclUser.getIsForbid()){
				result.setSuccess(false);
				result.setMsg("�Բ��𣬸��˻��ѱ����ã�����ϵ����Ա��");
			}else{
				result.setSuccess(true);
				session.put("user", aclUser);
			}
		} catch (Exception e) {
			result.setMsg("�Բ�������ˣ�"+e.getMessage());
		}
		JSONObject json=new JSONObject(result);
		 out.println(json.toString());
		 out.flush();
		 out.close();
		
	}

	/**
	 * �����û���ע�ᡢ�޸ġ�
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveUser() throws Exception {
		userLogic.saveAclUser(user);
		return "edit";
	}

	/**
	 * ��ȡ���е��û�
	 * 
	 * @return
	 */
	public String findAllUser() {
		String userflag =((AclUser)ServletActionContext.getRequest().getSession().getAttribute("user")).getUserFlag();
		List<AclUser> users = this.userLogic.findAllUser(userflag);
		request.put("users", users);
		return "authority";
	}

	/**
	 * ɾ���û�
	 */
	public String deleteUser() throws Exception {
		if (null != ids && !"".equals(ids)) {
			String[] arr = ids.split(",");
			this.userLogic.deleteAclUser(arr);
		}
		return "del";
	}

	/**
	 * ���û������û��˺�
	 * @return
	 * @throws Exception
	 */
	public String stopOrOpenUser() throws Exception{
		if (null != ids && !"".equals(ids)) {
			String[] arr = ids.split(",");
			AclUser editUser = this.userLogic.findUserById(arr[0]);
			if(null!=editUser){
				if("false".equals(userForbid)){
					editUser.setIsForbid(Boolean.FALSE);
				}else if("true".equals(userForbid)){
					editUser.setIsForbid(Boolean.TRUE);
				}
				this.userLogic.saveAclUser(editUser);
			}
		}
		return "del";
	}
	
	public AclUser getUser() {
		return user;
	}

	public void setUser(AclUser user) {
		this.user = user;
	}

	public AclUserLogic getUserLogic() {
		return userLogic;
	}

	public void setUserLogic(AclUserLogic userLogic) {
		this.userLogic = userLogic;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getUserForbid() {
		return userForbid;
	}

	public void setUserForbid(String userForbid) {
		this.userForbid = userForbid;
	}

	public String getForget() {
		return forget;
	}

	public void setForget(String forget) {
		this.forget = forget;
	}

}
