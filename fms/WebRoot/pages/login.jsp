<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <base href="<%=basePath%>">
    
    <title>My JSP 'loginUser.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script src="${pageContext.request.contextPath}/js/jquery-1.8.2.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/utils/cookieUtils.js"></script>
	<script src="${pageContext.request.contextPath}/js/login/login.js"></script>
    <%-- <link href="<%=path%>/css/page/login.css" rel="stylesheet" type="text/css" /> --%>
	<style type="text/css">
		body,div,img,table,tr,td{margin:0 auto; padding:0px;}
		.besidebox{width:100%;height:100%;position: relative;}
		.mess{font-size: 12px; color:red;font-weight: bold; margin-left: 5px;}
		.info{position: absolute;}
	</style>
  </head>
  <body onload="shownotes()">
  	<div class="besidebox">
  		<img src="${pageContext.request.contextPath}/imges/login.png" id="loginbg" style="z-index: 0px;"/>
  		<div class="info" id="logininfo">
  		<form name="myform" action="${pageContext.request.contextPath}/loginAction_gologin.action" method="post">
  			<table>
  				<tr>
  					<td>用户名：</td>
  					<td style="text-align: left;"><input type="text" name="username" style="width: 155px;"/></td>
  				</tr>
  				<tr>
  					<td>密　码：</td>
  					<td style="text-align: left;"><input type="password" name="password" style="width: 155px;"/></td>
  				</tr>
  				<tr>
  					<td>验证码：</td>
  					<td><input type="text" style="width:70px; float:left;"/><a href="javascript:void(0);" onclick="changeImg()"><img src="" style="width:82px; height:25px;float:right; border: 0px;" title="看不清？点击换一个"/></a></td>
  				</tr>
  				<tr>
  					<td colspan="3"><input type="checkbox" id="remenber" style="font-size: 12px;"/>记住密码<span class="mess" id="error"></span></td>
  				</tr>
  				<tr>
  					<td colspan="3">
  						<input type="button"  id="login" value="登录"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  						<input type="reset" id="cs" value="重置"/>
  					</td>
  				</tr>
  			</table>
  			</form>
  		</div>
  	</div>
  </body>
  
  <script language="javascript">
  //动态设置登录页面的位置
	function shownotes()
	{
	 var w = 500;     //宽度
	 var h = 310;   //高度
	 var t = (screen.height-h)/2-80; //离顶部距离
	 var l = (screen.width-w)/2; //离左边距离
	 document.getElementById("loginbg").style.marginLeft = l+"px";
	 document.getElementById("loginbg").style.marginTop = t+"px";
	 document.getElementById("logininfo").style.left = l+190+"px";
	 document.getElementById("logininfo").style.top = t+130+"px";
	}

	//点击更换验证码
	function changeImg(){
		alert('换一张验证码!');
	}
</script>
</html>
