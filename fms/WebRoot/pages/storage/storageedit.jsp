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
			<form action="${pageContext.request.contextPath}/storage_save.action" method="post">
				<p style="padding-left: 16px;">
					入库类型<span class="required">*</span>
					<select style="height: 25px;width:200px;" name="inStorage.impFlag">
						<option value="">---请选择入库类型---</option>
						<c:if test="${inStorage.impFlag=='0'}">
							<option value="0" selected="selected">采购入库</option>
						</c:if>
						<c:if test="${inStorage.impFlag!='0'}">
							<option value="0">采购入库</option>
						</c:if>
						<c:if test="${inStorage.impFlag=='1'}">
							<option value="1" selected="selected">退货入库</option>
						</c:if>
						<c:if test="${inStorage.impFlag!='1'}">
							<option value="1">退货入库</option>
						</c:if>
						<c:if test="${inStorage.impFlag=='2'}">
							<option value="2" selected="selected">其它入库</option>
						</c:if>
						<c:if  test="${inStorage.impFlag!='2'}">
							<option value="2">其它入库</option>
						</c:if>
					</select>&nbsp;&nbsp;&nbsp;
					物料类型:<span class="required">*</span>
					<select style="height: 25px;width:207px;" name="inStorage.materialType.typeName">
						<option value="">---请选择物料类型---</option>
						<c:forEach var="type" items="${types}">
							<c:if test="${inStorage.materialType.typeName==type.typeName}">
								<option value="${type.id}" selected="selected">${type.typeName}</option>
							</c:if>
							<c:if test="${inStorage.materialType.typeName!=type.typeName}">
								<option value="${type.id}">${type.typeName}</option>
							</c:if>
						</c:forEach>
					</select>
				</p>
				<p style="padding-left: 22px;">
					物料名称:<span class="required">*</span><input type="text" style="height: 25px;" name="inStorage.material.hsName" id="materialName" value="${inStorage.material.hsName}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					颜色:<input type="text" style="height: 25px;" readonly="readonly" name="inStorage.material.color"/>&nbsp;&nbsp;&nbsp;&nbsp;
				</p>
				<p style="padding-left: 20px;">
					计量单位:<span class="required">*</span><input type="text" style="height: 25px;" name="inStorage.material.unit.name"/>&nbsp;&nbsp;
					供应商名称:<span class="required">*</span>
					<select style="height: 25px;width:200px;" name="inStorage.scmcoc.name">
						<option value="">---请选择供应商---</option>
						<c:forEach var="scmCoc" items="${scmCocs}">
							<c:if test="${scmCoc.name==inStorage.scmcoc.name}">
								<option value="${scmCoc.id}" selected="selected">${scmCoc.name}</option>
							</c:if>
							<c:if test="${scmCoc.name!=inStorage.scmcoc.name}">
								<option value="${scmCoc.id}">${scmCoc.name}</option>
							</c:if>
						</c:forEach>
					</select>
					<!-- <input type="text" style="height: 25px;"/>&nbsp;&nbsp;&nbsp;&nbsp; -->
				</p>
				<p style="padding-left: 22px;">
					入库数量:<span class="required">*</span><input type="text" style="height: 25px;" id="inqty" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" name="inStorage.inQty"/>
					每件包装数量:<span class="required">*</span><input type="text" style="height: 25px;" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" name="inStorage.specQty"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</p>
				<p style="padding-left: 52px;">
					件数:<input type="text" style="height: 25px;" readonly="readonly" name="inStorage.pkgs"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					批次号:<span class="required">*</span><input type="text" style="height: 25px;" name="inStorage.material.batchNO"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</p>
				<p style="padding-left: 28px;">
					入库日期:<input type="text" readonly="readonly" id="inDate" style="height: 25px;" name="inStorage.impDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					备注:<input type="text" style="height: 25px;" name="inStorage.note"/>
				</p>
				<p style="padding-left: 210px;">
					<input class="btn btn-small btn-danger pull-left" data-toggle="button" type="submit" value="保存"/>&nbsp;&nbsp;
					&nbsp;&nbsp;<input class="btn btn-small btn-danger pull-left" data-toggle="button" type="reset" value="取消" style="margin-left: 10px;"/>
				</p>
			</form>
		</div>
	</div>
	<div id="covering" style="width:100%; height: 100%;"></div>
  </body>
  <script type="text/javascript">
  	$(function(){
  		$("#inDate").datepicker({
  			changeYear: true,
			changeMonth: true,
			yearRange: '1900:', 
			dateFormat: 'yy-mm-dd'
  		});
  		//调出物料
  		$("#materialName").click(function(){
  			var w = 500;     //宽度
	 		var h = 310;   //高度
  			var t = (screen.height-h)/2-80; //离顶部距离
	 		var l = (screen.width-w)/2; //离左边距离
	 		//$("#waitdiv").show();
  			var w = window.open('http://www.baidu.com','newwindow','height=300,width=600,top='+t+',left='+l+',toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no,alwaysRaised=yes,z-look=yes');
  			w.focus();
  		});
  	});
  	
  </script>
</html>
