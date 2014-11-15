	<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
	</style>
	<div class="navbar"  style="position: relative;">
		<div class="navbar-inner" id="setcss">
			<div class="container-fluid">
				<img alt="公司标志" src="${pageContext.request.contextPath}/imges/ls_logo.png" style="width: 60px;">
				<!--/.brand-->
				
				<ul style="float: right;height:auto; margin: 0px; padding:0px;">
					<li style="list-style: none; line-height:60px; color: white; font-size: 12px; float: left;">欢迎您：${user.loginName}</li>
					<c:if test="${user.userFlag=='S'}">
						<li style="list-style: none; line-height:60px; color: white; font-size: 12px;float: left;">　　您是【管理员】　<a href="" style="color:white;">注销</a></li>
					</c:if>
					<c:if test="${user.userFlag!='S'}">
						<li style="list-style: none; line-height:60px; color: white; font-size: 12px;float: left;">　　您是【普通用户】　<a href="" style="color:white;">注销</a></li>
					</c:if>
				</ul>
				
				<!--/.ace-nav-->
			</div>
			<!--/.container-fluid-->
		</div>
		<!--/.navbar-inner-->
	</div>
<script type="text/javascript">

//判断浏览器
/* $(function(){
	if ((navigator.userAgent.indexOf('MSIE') >= 0) && (navigator.userAgent.indexOf('Opera') < 0)){
		$("#setcss").attr("class", "iecss");//IE
	}else if (navigator.userAgent.indexOf('Firefox') >= 0){
		$("#setcss").attr("class", "firefoxcss");//Firefox
	}else
    if (navigator.userAgent.indexOf('Opera') >= 0){
    	$("#setcss").attr("class", "iecss");//Opera
    }else{
    	$("#setcss").attr("class", "iecss");//Other
    }
}); */ 
</script>