package com.fms.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.fms.base.action.BaseAction;
import com.fms.commons.CommonConstant;
import com.fms.core.entity.AclUser;
import com.fms.logic.AclUserLogic;
import com.fms.utils.AjaxResult;
import com.fms.utils.MD5Util;
import com.url.ajax.json.JSONObject;

public class AclUserAction extends BaseAction {

	private AclUser user;
	private AclUserLogic userLogic;
	private String userName;
	private String password;
	private String loginName;
	private String userFlag;
	protected String ids;
	protected String forget;// 记住密码
	/**
	 * 是否禁用或启用用户账户：【"false"表示启用;"true"表示禁用】
	 */
	private String userForbid;// 是否禁用或启用用户账户：【"false"表示启用;"true"表示禁用】

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 测试Action
	 * 
	 * @return
	 */
	public void test() {
		PrintWriter out = null;
		AjaxResult result = new AjaxResult();
		Boolean isExits = null;
		try {
			out = response.getWriter();
			response.setContentType("application/text");
			response.setCharacterEncoding("UTF-8");
			isExits = this.userLogic.findUserByName(getLoginUser(), "admin");
			if (isExits != null) {
				result.setMsg("Action跳转成功!");
				result.setSuccess(true);
				JSONObject json = new JSONObject(result);
				out.println(json.toString());
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			result.setMsg("Action跳转异常!");
			result.setSuccess(false);
			JSONObject json = new JSONObject(result);
			out.println(json.toString());
			out.flush();
			out.close();
		}
	}

	/**
	 * 启动跳转
	 * 
	 * @return
	 */
	public String gologin() {
		return "login";
	}

	/**
	 * 用户登录【通过ajax】
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void loginUser() throws Exception {

		PrintWriter out = null;
		AjaxResult result = new AjaxResult();

		try {
			out = response.getWriter();
			response.setContentType("application/text");
			response.setCharacterEncoding("UTF-8");
			AclUser aclUser = userLogic.loginAclUser(userName, password);
			if (aclUser == null) {
				result.setSuccess(false);
				result.setMsg("用户名或密码不正确");
			} else if (aclUser.getIsForbid()) {
				result.setSuccess(false);
				result.setMsg("该账户已被禁用");
			} else {
				result.setSuccess(true);
				session.put(CommonConstant.LOGINUSER, aclUser);
				// 登录成功后，要记录最后的登录时间
				if (null != aclUser) {
					aclUser.setLastlogin(new Date());
					aclUser.setPassword(MD5Util.encryptData(password));
					this.userLogic.saveAclUser(aclUser, aclUser);
				}
			}
		} catch (Exception e) {
			result.setMsg("对不起出错了：" + e.getMessage());
		}
		JSONObject json = new JSONObject(result);
		out.println(json.toString());
		out.flush();
		out.close();

	}

	/**
	 * 保存用户【注册、修改】
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveUser() throws Exception {
		user.setPassword(MD5Util.encryptData(user.getPassword().trim()));
		userLogic.saveAclUser(getLoginUser(), user);
		return "edit";
	}

	/**
	 * 获取所有的用户
	 * 
	 * @return
	 */
	public String findAllUser() {
		String userflag = ((AclUser) ServletActionContext.getRequest().getSession().getAttribute("u")).getUserFlag();
		List<AclUser> users = this.userLogic.findAllUser(getLoginUser(), userflag);
		request.put("users", users);
		return "authority";
	}

	/**
	 * 删除用户
	 */
	public void deleteUser() {

		if (null != ids && !"".equals(ids)) {
			String[] idArr = ids.split(",");
			if (idArr != null && idArr.length > 0) {
				PrintWriter out = null;
				AjaxResult result = new AjaxResult();
				try {
					out = response.getWriter();
					response.setContentType("application/text");
					response.setCharacterEncoding("UTF-8");
					this.userLogic.deleteAclUser(getLoginUser(), idArr);
					result.setSuccess(true);
					result.setMsg("删除成功！");
					JSONObject json = new JSONObject(result);
					out.println(json.toString());
					out.flush();
					out.close();
				} catch (Exception e) {
					result.setSuccess(false);
					result.setMsg("数据被其它地方引用，不能删除！");
					JSONObject json = new JSONObject(result);
					out.println(json.toString());
					out.flush();
					out.close();
				}
			}
		}
	}

	public void registerUser() {
		System.out.println("注册");
		PrintWriter out = null;
		AjaxResult result = new AjaxResult();
		try {
			out = response.getWriter();
			response.setContentType("application/text");
			response.setCharacterEncoding("UTF-8");
			AclUser user = new AclUser();
			user.setLoginName(loginName);
			user.setUserName(userName);
			user.setUserFlag(userFlag);
			user.setPassword(MD5Util.encryptData(password.trim()));
			this.userLogic.saveAclUser(getLoginUser(), user);
			result.setSuccess(true);
			result.setMsg("注册成功");
			JSONObject json = new JSONObject(result);
			out.println(json.toString());
			out.flush();
		} catch (IOException e) {
			result.setMsg("注册失败");
			result.setSuccess(false);
			e.printStackTrace();
		}
	}

	/**
	 * 禁用或启用用户账号
	 * 
	 * @return
	 * @throws Exception
	 */
	public String stopOrOpenUser() throws Exception {
		if (null != ids && !"".equals(ids)) {
			String[] arr = ids.split(",");
			AclUser editUser = this.userLogic.findUserById(getLoginUser(), arr[0]);
			if (null != editUser) {
				if ("false".equals(userForbid)) {
					editUser.setIsForbid(Boolean.FALSE);
				} else if ("true".equals(userForbid)) {
					editUser.setIsForbid(Boolean.TRUE);
				}
				this.userLogic.saveAclUser(getLoginUser(), editUser);
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

	public static void main(String[] args) {
		System.out.println(MD5Util.encryptData("admin"));
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

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserFlag() {
		return userFlag;
	}

	public void setUserFlag(String userFlag) {
		this.userFlag = userFlag;
	}

}
