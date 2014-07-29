<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addscmcoc.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		.addcss{
			width:200px;
		}
		.captioncss{
			width:80px;
		}
		.tablecss{
			width:800px;
			margin-left:5px;
		}
	</style>
  </head>
  
  <body>
    <div class="page-header position-relative" style="margin-bottom: 0px;">
		<h5>基础资料＞＞供应商管理＞＞新增</h5>
	</div>
	<div class="row-fluid">
				<div class="span12">
					<table id="sample-table-1" class="table table-striped table-bordered table-hover tablecss"  style=" font-size: 12px;">
						<tr>
							<td class="captioncss">编码</td>
							<td class="hidden-480 addcss"><input type="text" value="${scmcoc.code}"/></td>
							<td class="captioncss">名称</td>
							<td class="hidden-480 addcss"><input type="text" value="${scmcoc.name}"/></td>
						</tr>
						<tr>
							<td class="captioncss">联系电话</td>
							<td class="hidden-480 addcss"><input type="text" value="${scmcoc.linkPhone}"/>
							<td class="captioncss">网络联系方式</td>
							<td class="hidden-480 addcss"><input type="text" value="${scmcoc.networkLink}"/></td>
						</tr>
						<tr>
							<td class="captioncss">联系人</td>
							<td class="hidden-480 addcss"><input type="text" value="${scmcoc.linkMan}"/></td>
							<td class="captioncss">地址</td>
							<td class="hidden-480 addcss"><input type="text" value="${scmcoc.address}"/></td>
						</tr>
						<tr>
							<td class="captioncss">约定结算日期</td>
							<td class="hidden-480 addcss"><input type="text" value="${scmcoc.endDate}"/></td>
							<td class="captioncss">备注</td>
							<td class="hidden-480 addcss"><input type="text" value="${scmcoc.note}"/></td>
						</tr>
					</table>
				</div>
			</div>
  </body>
</html>
