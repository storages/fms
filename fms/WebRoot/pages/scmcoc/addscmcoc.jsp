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
	
	<style type="text/css">
		.addcss{
			width:220px;
		}
		.captioncss{
			width:80px;
		}
		.tablecss{
			width:800px;
			margin-left:5px;
		}
	</style>
  
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/datepicker/jquery-ui-1.8.16.custom.css" type="text/css"></link>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/datepicker/jquery.ui.core.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/datepicker/jquery.ui.datepicker.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/datepicker/jquery.ui.datepicker-zh-CN.js"></script>
  
  </head>
  <body>
    <div class="page-header position-relative" style="margin-bottom: 0px;">
		<h5>基础资料＞＞供应商管理＞＞新增</h5>
	</div>
	<form id="scm" name="scm" method="post" action="${pageContext.request.contextPath}/scmcoc_saveScmcoc.action">
	<div class="modal-footer">
		<input type="submit" class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" value="保存"/>
		<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" onclick="">取消</button>
	</div> 
	<div class="row-fluid">
		<div class="span12">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover tablecss"  style=" font-size: 12px;">
				<tr>
					<td class="captioncss" style="text-align: right;">编码</td>
					<td class="hidden-480 addcss"><input type="text" value="${scmcoc.code}" name="scmcoc.code" style="height:25px;"/><span style="color:red";>*</span></td>
					<td class="captioncss" style="text-align: right;">名称</td>
					<td class="hidden-480 addcss"><input type="text" value="${scmcoc.name}" name="scmcoc.name" style="height:25px;"/><span style="color:red";>*</span></td>
				</tr>
				<tr>
					<td class="captioncss" style="text-align: right;">联系电话</td>
					<td class="hidden-480 addcss"><input type="text" value="${scmcoc.linkPhone}" name="scmcoc.linkPhone" style="height:25px;"/>
					<td class="captioncss" style="text-align: right;">网络联系方式</td>
					<td class="hidden-480 addcss"><input type="text" value="${scmcoc.networkLink}" name="scmcoc.networkLink" style="height:25px;"/></td>
				</tr>
				<tr>
					<td class="captioncss" style="text-align: right;">联系人</td>
					<td class="hidden-480 addcss"><input type="text" value="${scmcoc.linkMan}" name="scmcoc.linkMan" style="height:25px;"/></td>
					<td class="captioncss" style="text-align: right;">地址</td>
					<td class="hidden-480 addcss"><input type="text" value="${scmcoc.address}" name="scmcoc.address" style="height:25px;"/></td>
				</tr>
				<tr>
					<td class="captioncss" style="text-align: right;">约定结算日期</td>
					<td class="hidden-480 addcss"><input type="text" value="${scmcoc.endDate}" name="scmcoc.endDate" style="height:25px;" id="datepicker" readonly="readonly" /><span>m-d</span></td>
					<td class="captioncss" style="text-align: right;">备注</td>
					<td class="hidden-480 addcss"><input type="text" value="${scmcoc.note}" name="scmcoc.note" style="height:25px;"/></td>
				</tr>
			</table>
		</div>
	</div>
	</form>
  </body>
  
  <script language="javascript">
		$(document).ready(function(){
			$("#datepicker").datepicker({
				changeYear: false,
				changeMonth: true,
				yearRange: '1900:', 
				dateFormat: 'm-d'
			});
		});
	</script>
</html>
