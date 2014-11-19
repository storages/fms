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
    
    <title>My JSP  'test.jsp'  starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
  
  <link rel="stylesheet" href="css/dialog/dialogCss.css" type="text/css"></link>
  <script type="text/javascript" src="js/dialog/dialog.js"></script>
  <script type="text/javascript" src="js/controlUI/editTable.js"></script>
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
		
		<hr/>
    	<br/>
    	
    	<a href="${pageContext.request.contextPath}/pages/loginUser.jsp">New LoginPage</a>
    	
    	<hr/>
    	<br/>
    	
    	<h4>测试下载模板</h4>
    	<a href="${pageContext.request.contextPath}/fileDownload.action?fileFlag=stockTemp">下载模板</a>
    	
    	<hr/>
    	<br/>
    	
    	<h4>测试dialog对话框</h4>
    	<button id="showDialogForm">弹出dialog</button>
    	<div class="dialog" id="dialog" title="测试dialog对话框"><input /></div>
    	
    	
    	<hr/>
    	<br/>
    	
    	<h4>测试Table编辑</h4>
    	<p><button onclick="closeAllEdit('mytable','1,2,3')">关闭所有编辑框</button></p>
    	<table border="1px solid black" id="mytable">
    		<thead>
    			<tr>
    				<td>　</td>
    				<td>语文</td>
    				<td>数学</td>
    				<td>评语</td>
    				<td>操作</td>
    			</tr>
    		</thead>
    		<tbody>
    			<tr>
    				<td>张三</td>
    				<td>98</td>
    				<td>80</td>
    				<td>继续努力</td>
    				<td><a href="javascript:void(0)" onclick="showTableEdit(this,'1,2,3')">修改</a>
    				<a href="javascript:void(0)" onclick="closeTableEdit(this,'1,2,3')">保存</a>
    				</td>
    			</tr>
    			<tr>
    				<td>李四</td>
    				<td>78</td>
    				<td>89</td>
    				<td>加油</td>
    				<td><a href="javascript:void(0)" onclick="showTableEdit(this,'1,2,3')">修改</a></td>
    			</tr>
    			<tr>
    				<td>王五</td>
    				<td>86</td>
    				<td>91</td>
    				<td>再接再励</td>
    				<td><a href="javascript:void(0)" onclick="showTableEdit(this,'1,2,3')">修改</a></td>
    			</tr>
    		</tbody>
    	</table>
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
