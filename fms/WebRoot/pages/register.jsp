<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'register.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
<style type="text/css">
.span12 {
	width: 360px;
}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.min.js"></script></head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/register/register.js"></script>

<body>
	<div style="height:8%"></div>
	<div class="logincss">
		<div id="signup-box" class="signup-box widget-box no-border">
			<div class="widget-body">
				<div class="widget-main">
					<h4 class="header green lighter bigger">
						<i class="icon-group blue"></i> 注册新用户
					</h4>

					<div class="space-6"></div>
					<p>输入您的信息</p>

					<form action="${pageContext.request.contextPath}/users_saveUser.action"
						method="post" onsubmit="return checkInfo()">
						登&nbsp;录&nbsp;名:<input type="text" style="height:30px;"
							placeholder="登录用" name="user.loginName" id="loginName"/><span
							id="loginNamemess" style="color:red;">*</span><br />
						用&nbsp;户&nbsp;名:<input type="text" style="height:30px;"
							placeholder="登录后显示" name="user.userName" id="userName"/><span id="userNamemess"
							style="color:red;">*</span><br /> 
							密 　&nbsp;码:<input type="password" style="height:30px;" placeholder="密码"
							name="user.password" id="password"/><span id="passwordmess"
							style="color:red;">*</span><br /> 重复密码:<input type="password"
							style="height:30px;" placeholder="重复密码" name="password2" id="repassword"/><span
							id="password2mess" style="color:red;">*</span><br /><input type="radio" name="user.userFlag" value="S"/> <span
							class="lbl">管理员</span>　　<input type="radio"  name="user.userFlag" value="P" checked="checked"/> <span
							class="lbl">普通用户</span>
						<p style=" font-weight: bold; margin-left:26%;">
							<span style="color:red; font-size: 12px;margin-right: 40%;"
								id="regisMess"></span>注：带<span style="color:red;">*</span>必填
						</p>
						<input type="reset" value="重置"
							class="width-30 pull-left btn btn-small" /> <input type="submit"
							value="注册" class="width-65 pull-right btn btn-small btn-success" />
					</form>
				</div>

				<div class="clearfix">
					<!-- <button type="reset" class="width-30 pull-left btn btn-small">
						<i class="icon-refresh"></i> 重置
					</button> -->

					<!-- <button onclick="return false;"
						class="width-65 pull-right btn btn-small btn-success">
						注册 <i class="icon-arrow-right icon-on-right"></i>
					</button> -->
				</div>
				<p>
				<div class="toolbar center">
					<a href="${pageContext.request.contextPath}/pages/login.jsp"　class="back-to-login-link">去登录 </a>
				</div>
			</div>
			<!--/widget-body-->
		</div>
		<!--/signup-box-->
		<!--/position-relative-->
	</div>
</body>
</html>
