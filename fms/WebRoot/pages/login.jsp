<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>">





<title>系统登录</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css"><img src="${pageContext.request.contextPath}/imges/user.png"
	-->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/login/login.js"></script>
<head>
<meta charset="utf-8" />
<title>用户登录</title>

<meta name="description" content="User login page" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link
	href="${pageContext.request.contextPath}/css/public/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/css/public/bootstrap-responsive.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/css/public/font-awesome.min.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/public/public_1.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/public/ace.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/css/public/ace-responsive.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/css/public/ace-skins.min.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/login/login.css"
	rel="stylesheet" />
</head>


</head>




<% 
String name=null;
String passward = null;
try{ 
	Cookie[] cookies=request.getCookies(); 
	if(cookies!=null){ 
		for(int i=0;i<cookies.length;i++){ 
			if(cookies[i].getName().equals("user")){  
				name=cookies[i].getValue().split("/")[0];
				passward=cookies[i].getValue().split("/")[1]; 
				request.setAttribute("name",name); 
				request.setAttribute("pass",passward); 
				System.out.print("-----Cookie------"+name+"--------");
				System.out.print("-----Cookie------"+passward+"--------");
			} 
		} 
	} 
}catch(Exception e){ 
	e.printStackTrace(); 
} 

%> 



<body>
	<div class="logincss">
		<div class="row-fluid">
			<div class="position-relative">
				<div id="login-box" class="login-box visible widget-box no-border">
					<div class="widget-body">
						<div class="widget-main">
							<h4 class="header blue lighter bigger">
								<i class="green"><img src="${pageContext.request.contextPath}/imges/tishi_1.png"></img></i> 请输入你的信息
							</h4>

							<div class="space-6"></div>

							<form>
								<fieldset>
									<label> <span class="block input-icon input-icon-right">
											<input type="text" class="" placeholder="登录名" name="user.username" id="username" value="${name}" style="width:310px;"/>
											<i class="icon-user"><img src="${pageContext.request.contextPath}/imges/user.png"  style="margin-left: -13px; vertical-align: middle; height:23px;"/></i> </span> </label> <label><span
										class="block input-icon input-icon-right"> <input
											type="password" class="" placeholder="密码" name="user.password" id="password" value="${passward}" style="width:310px;"/> <i
											class="icon-lock"><img src="${pageContext.request.contextPath}/imges/lock.png"  style="margin-left: -13px;"/></i> </span> </label>
									<label  style="color:red; font-size:12px;" id="mess"></label>
									<div class="space"></div>
									<div class="clearfix">
										<label class="inline"> <input type="checkbox" name="forget" id="remenber"/> <span
											class="lbl"> 记住密码</span> </label>
										
										<input type="button" onclick="findUserName()"
											class="width-35 pull-right btn btn-small btn-primary" value="登录" style="margin-right: 109px;">
									</div>

									<div class="space-4"></div>
								</fieldset>
							</form>

							<!-- <div class="social-or-login center">
								<span class="bigger-110">登录使用</span>
							</div>

							<div class="social-login center">
								<a class="btn btn-primary"> <i class="icon-facebook"></i> </a> <a
									class="btn btn-info"> <i class="icon-twitter"></i> </a> <a
									class="btn btn-danger"> <i class="icon-google-plus"></i> </a>
							</div> -->
						</div>
						<!--/widget-main-->

						<div class="toolbar clearfix">
							<div>
								<a href="#" onclick="show_box('forgot-box'); return false;"
									class="forgot-password-link"> <i class="icon-arrow-left"></i>
									我忘记密码了 </a>
							</div>
<%-- 
							<div>
								<a href="${pageContext.request.contextPath}/pages/register.jsp"
									class="user-signup-link"> 我要注册 <i class="icon-arrow-right"></i>
								</a> --%>
							</div>
						</div>
					</div>
					<!--/widget-body-->
				</div>
				<!--/login-box-->
			</div>
		</div>
	</div>
</body>
</html>
