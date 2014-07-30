<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP  'test.jsp' help  starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
  </head>
  
  <body>
    <a href="${pageContext.request.contextPath}/users_findUsers.action">测试Action跳转</a>
    <hr/>
    <br/>
    <h4>测试注册功能</h4>
    <form action="${pageContext.request.contextPath}/users_saveUser.action" method="post">
		登&nbsp;录&nbsp;名: <input type="text" name="user.loginName"/> <br/>
		用&nbsp;户&nbsp;名: <input type="text" name="user.userName"/>  <br/>
		密　　码: <input type="password" name="user.password"/>  <br/>
		重复密码: <input type="password" name="repass"/><br/>
		是否是管理员　<input type="radio" name="user.userFlag" value="S"/>是　　<input type="radio" name="user.userFlag" value="O"/>否
		<br/><input type="submit" value="注册"/>
    </form>
    
    <hr/>
    <br/>
    <h4>测试登录功能</h4>
    	用&nbsp;户&nbsp;名: <input type="text" name="user.userName" id="username"/><label  style="color:red; font-size:12px;" id="usernamemess"></label>  <br/>
		密　　码: <input type="password" name="user.password" id="password"/><label  style="color:red; font-size:12px;" id="passwordmess"></label>  <br/>
		<span style="color:red; font-size:12px;" id="mess"></span><br/>
		<input type="button" value="登录" onclick="findUserName()"/>
  </body>
  <script type="text/javascript">
  	function findUserName(){
	  	var username = $("#username").val();
	  	var password = $("#password").val();
	  	if(username==""){
	  		$("#usernamemess").html("用户名不能为空");
	  		$("#passwordmess").html("");
	  		return;
	  	}
	  	if(password==""){
	  		$("#usernamemess").html("");
	  		$("#passwordmess").html("密码不能为空");
	  		return;
	  	}
	  	$("#usernamemess").html("");
	  	$("#passwordmess").html("");
  		var url = "${pageContext.request.contextPath}/users_loginUser.action?loginName="+username+"&password="+password;
  		$.ajax({
	     type: "POST",
	     url:url,
	     async: false,
	     cache: false,
	     success:function(data){
	     	if("false"==data){
	     		$("#mess").html("用户名或密码不正确！");
	     	}else{
	           window.location.href="${pageContext.request.contextPath}/index.jsp";
	           $("#mess").html("");
	     	};
	     },error:function(){
	        	$("#mess").html("登录异常，请重新启动程序！");
	      }
	  	});
  	}
  </script>
</html>
