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
    
    <title>My JSP 'storageedit.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/datepicker/jquery-ui-1.8.16.custom.css"
	type="text/css"></link>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/utils/chinese-of-spell.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/datepicker/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/datepicker/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datepicker/jquery.ui.datepicker-zh-CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/public/public.js"></script>
  </head>
  
  <body>
    <input type="hidden" id="flag" value="${materinfo.id}"/><!-- 为了判断是新增还是修改 -->
    <div class="page-header position-relative" style="margin-bottom: 0px;">
    	<c:if test="${materinfo.id==null}">
			<h5>物料＞＞<a href="">入库</a>＞＞新增</h5>
		</c:if>
    	<c:if test="${materinfo.id!=null}">
			<h5>物料＞＞<a href="">出库</a>＞＞修改</h5>
		</c:if>
	</div>
	<div class="row-fluid" id="mybox">
		<div class="span12">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover tablecss"  style=" font-size: 12px;">
				<tr>
					<td class="captioncss" style="text-align: right; ">物料名称</td>
					<td class="hidden-480 addcss" style=""><input type="text" value="${materinfo.hsName}" name="material.hsName" style="height:25px;" onblur="check()"/><span style="color:red;">*</span></td>
					<td class="captioncss" style="text-align: right; ">颜色</td>
					<td class="hidden-480 addcss" style=""><input type="text" value="${materinfo.color}" name="material.color" style="height:25px;"/><span style="color:red;">*</span></td>
				</tr>
				<tr>
					<td class="captioncss" style="text-align: right; ">计量单位</td>
					<td class="hidden-480 addcss" style="">
					<select name="material.unit" id="form-field-select-1" style="height: 25px; ">
						<c:forEach var="unit" items="${units}">
							<option value="${unit.name}">${unit.name}</option>
						</c:forEach>
					</select>
					<%-- <input type="text" value="${materinfo.unit.name}" name="material.unit" style="height:25px;"/> --%>
					
					<span style="color:red;">*</span></td>
					<td class="captioncss" style="text-align: right; " onblur="check()">供应商名称</td>
					<td class="hidden-480 addcss" style=""><input type="text" value="${materinfo.model}" name="material.model" style="height:25px;"/><span style="color:red;">*</span></td>
				</tr>
				<tr>
					<td class="captioncss" style="text-align: right; ">入库数量</td>
					<td class="hidden-480 addcss" style=""><input type="text" value="${materinfo.qty}" name="material.qty" style="height:25px;"/><span style="color:red;">*</span></td>
					<td class="captioncss" style="text-align: right; " onblur="check()">每件包装数量</td>
					<td class="hidden-480 addcss" style=""><input type="text" value="${materinfo.batchNO}" name="material.batchNO" style="height:25px;"/><span style="color:red;">*</span></td>
				</tr>
				<tr>
					<td class="captioncss" style="text-align: right; ">件数</td>
					<td class="hidden-480 addcss" style=""><input type="text" value="${materinfo.lowerQty}" name="material.lowerQty" style="height:25px;"/><span style="color:red;">*</span></td>
					<td class="captioncss" style="text-align: right; " onblur="check()">批次号</td>
					<td class="hidden-480 addcss" style=""><input type="text" value="${materinfo.batchNO}" name="material.batchNO" style="height:25px;"/><span style="color:red;">*</span></td>
				</tr>
				<tr>
					<td class="captioncss" style="text-align: right; ">入库日期</td>
					<td class="hidden-480 addcss" style=""><input type="text" value="${materinfo.lowerQty}" id="indate" name="material.lowerQty" style="height:25px;"/><span style="color:red;">*</span></td>
					<td class="captioncss" style="text-align: right; ">备注</td>
					<td class="hidden-480 addcss" style=""><input type="text" name="note" style="height:25px;"/></td>
				</tr>
				<tr>
				</tr>
			</table>
		</div>
	</div>
  </body>
  <script type="text/javascript">
  	$(function(){
  		$("#indate").datepicker({
  			changeYear: true,
			changeMonth: true,
			yearRange: '1900:', 
			dateFormat: 'yy-mm-dd'
  		});
  	});
  </script>
</html>
