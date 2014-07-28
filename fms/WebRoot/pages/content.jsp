<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <jsp:include page="/pages/head/head.jsp"></jsp:include> --%>
<style type="text/css">
	#sidebar{
	}
</style>
<!-- 左边菜单树 -->
<div class="navbar">
	<div class="navbar-inner" id="setcss">
		<!-- <div class="container-fluid"></div> -->
			<img alt="公司标志" src="${pageContext.request.contextPath}/imges/ls_logo.png" style="width: 60px;float: left;">
				<!--/.brand-->
				
				<ul style="float: right;height:auto; margin: 0px 5px 0px 0px; padding:0px;">
					<li style="list-style: none; line-height:60px; color: white; font-size: 12px; float: left;">欢迎您：${user.loginName}</li>
					<c:if test="${user.userFlag=='S'}">
						<li style="list-style: none; line-height:60px; color: white; font-size: 12px;float: left;">　　您是【管理员】　<a href="" style="color:white;">注销</a></li>
					</c:if>
					<c:if test="${user.userFlag!='S'}">
						<li style="list-style: none; line-height:60px; color: white; font-size: 12px;float: left;">　　您是【普通用户】　<a href="" style="color:white;">注销</a></li>
					</c:if>
				</ul>
	</div>
</div>
<div class="sidebar" id="sidebar" style="left: 2px; margin-top: 60px;">
	<div id="main">
	
	<p style="font-size:15px; font-weight: bold; margin:5px 0px 0px 5px;">联塑塑胶制品公司</p>
	<ul id="browser" class="filetree">
		<li  class="closed"><span class="folder directer" >用户管理</span>
			<ul>
				<li><span class="file"><a href="javascript:void(0);" onclick="toMain('${pageContext.request.contextPath}/pages/register.jsp')">添加用户</a></span></li>
				<li><span class="file"><a href="javascript:void(0);" onclick="toMain('${pageContext.request.contextPath}/users_findAllUser.action')"">用户权限</a></span></li>
				<li><span class="file"><a href="javascript:void(0);" onclick="">用户分组</a></span></li>
				<li><span class="file"><a href="javascript:void(0);" onclick="">操作日志</a></span></li>
				<li><span class="file"><a href="javascript:void(0);" onclick="">系统参数设置</a></span></li>
			</ul>
		</li>
		<li  class="closed"><span class="folder directer">基础资料</span>
			<ul>
				<li><span class="file"><a href="">供应商管理</a></span></li>
				<li><span class="file"><a href="">客户管理</a></span></li>
				<li><span class="file"><a href="">交易货币管理</a></span></li>
				<li><span class="file"><a href="">计量单位管理</a></span></li>
				<li><span class="file"><a href="">仓库管理</a></span></li>
				<li><span class="file"><a href="">物料管理</a></span></li>
			</ul>
		</li>
		<li  class="closed"><span class="folder directer">职员管理</span>
			<ul>
				<li><span class="file"><a href="">职员信息</a></span></li>
			</ul>
		</li>
		<li  class="closed"><span class="folder directer">物料</span>
			<ul>
				<li><span class="file"><a href="">物料信息</a></span></li>
				<li><span class="file"><a href="">物料分类</a></span></li>
			</ul>
		</li>
		<li class="closed"><span class="folder directer">订单管理</span>
			<ul>
				<li><span class="file"><a href="">订单信息</a></span></li>
			</ul>
		</li>
		<li class="closed"><span class="folder directer">销售管理</span>
			<ul>
				<li><span class="file"><a href="">销售业绩</a></span></li>
			</ul>
		</li>
		<li class="closed"><span class="folder directer">报表查询</span>
			<ul>
				<li><span class="file"><a href="">原料耗用统计</a></span></li>
				<li><span class="file"><a href="">订单统计</a></span></li>
				<li><span class="file"><a href="">销售统计</a></span></li>
			</ul>
		</li>
		
		
	</ul>
	
	</div>
</div>



<!-- 右边主功能区 -->
<div class="page-content" style="margin-left: 192px; height:89%; padding:0px; font-size: 12px;" id="tomain">
	<jsp:include page="/pages/templet/templet.jsp"></jsp:include>
</div>
<!--/.page-content-->



