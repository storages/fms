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
	
	<style type="text/css">
		.required{color:red;}
	</style>
  </head>
  <body>
    <input type="hidden" id="flag" value="${materinfo.id}"/><!-- 为了判断是新增还是修改 -->
    <div class="page-header position-relative" style="margin-bottom: 0px;">
    	<c:if test="${storage.id==null}">
			<h5>物料＞＞<a href="">入库</a>＞＞新增</h5>
		</c:if>
    	<c:if test="${storage.id!=null}">
			<h5>物料＞＞<a href="">出库</a>＞＞修改</h5>
		</c:if>
	</div>
	<div class="row-fluid" id="mybox">
		<div class="span12">
			<form action="" method="post">
				<p style="padding-left: 16px;">
					<c:if test="${inStorage.impExpFalg=='0'}">
						进出库类型:<input type="text" style="height: 25px;" readonly="readonly" value="入库"/>&nbsp;&nbsp;&nbsp;
					</c:if>
					<c:if test="${inStorage.impExpFalg=='1'}">
						进出库类型:<input type="text" style="height: 25px;" readonly="readonly" value="出库"/>&nbsp;&nbsp;&nbsp;
					</c:if>
					物料类型:<span class="required">*</span>
					<select style="height: 25px;width:207px;">
						<option value="">---请选择物料类型---</option>
						<c:forEach var="type" items="${types}">
							<option value="${type.id}">${type.typeName}</option>
						</c:forEach>
					</select>
				</p>
				<p style="padding-left: 22px;">
					物料名称:<span class="required">*</span><input type="text" style="height: 25px;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					颜色:<input type="text" style="height: 25px;" readonly="readonly"/>&nbsp;&nbsp;&nbsp;&nbsp;
				</p>
				<p style="padding-left: 20px;">
					计量单位:<span class="required">*</span><select style="height: 25px;width:207px;">
						<option value="">---请选择计量单位---</option>
						<c:forEach var="unit" items="${units}">
							<option value="${unit.id}">${unit.name}</option>
						</c:forEach>
					</select>&nbsp;&nbsp;
					供应商名称:<span class="required">*</span>
					<select style="height: 25px;width:200px;">
						<option value="">---请选择供应商---</option>
						<c:forEach var="scmCoc" items="${scmCocs}">
							<option value="${scmCoc.id}">${scmCoc.name}</option>
						</c:forEach>
					</select>
					<!-- <input type="text" style="height: 25px;"/>&nbsp;&nbsp;&nbsp;&nbsp; -->
				</p>
				<p style="padding-left: 22px;">
					入库数量:<span class="required">*</span><input type="text" style="height: 25px;" id="inqty" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
					每件包装数量:<span class="required">*</span><input type="text" style="height: 25px;" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</p>
				<p style="padding-left: 52px;">
					件数:<input type="text" style="height: 25px;" readonly="readonly"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					批次号:<span class="required">*</span><input type="text" style="height: 25px;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</p>
				<p style="padding-left: 28px;">
					入库日期:<input type="text" readonly="readonly" id="inDate" style="height: 25px;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					备注:<input type="text" style="height: 25px;"/>
				</p>
				<p style="padding-left: 210px;">
					<input class="btn btn-small btn-danger pull-left" data-toggle="button" type="submit" value="保存"/>&nbsp;&nbsp;
					&nbsp;&nbsp;<input class="btn btn-small btn-danger pull-left" data-toggle="button" type="reset" value="取消" style="margin-left: 10px;"/>
				</p>
			</form>
		</div>
	</div>
  </body>
  <script type="text/javascript">
  	$(function(){
  		$("#inDate").datepicker({
  			changeYear: true,
			changeMonth: true,
			yearRange: '1900:', 
			dateFormat: 'yy-mm-dd'
  		});
  		
  	});
  	
  </script>
</html>
