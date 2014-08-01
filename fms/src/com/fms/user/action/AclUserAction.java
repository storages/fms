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
				if("true"==forget){
					Cookie user = new Cookie("user",aclUser.getLoginName()+"/"+aclUser.getPassword());
				}else{
					Cookie ucookie=new Cookie("username",userName);
					ucookie.setMaxAge(60*60*24*365);
					response.addCookie(ucookie);
					Cookie pcookie=new Cookie("password", password);
					pcookie.setMaxAge(60*60*24*365);
					response.addCookie(pcookie);
				}
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
		List<AclUser> users = this.userLogic.findAllUser();
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

}
