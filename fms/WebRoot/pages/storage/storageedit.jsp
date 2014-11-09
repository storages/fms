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
	<link rel="stylesheet" type="text/css" href="styles.css"> style="border: solid 1px red;"
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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/dialog/dialogCss.css" type="text/css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dialog/dialog.js"></script>
	<style type="text/css">
		.required{color:red;}
		p{margin-bottom: 2px;}
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
	<div class="row-fluid" id="mybox" style="margin-left: 5px;" >
		<div class="span12">
			<p>
				采购单号<span class="required">*</span><select style="height: 25px;width: 160px;">
					<option value="">---请选择采购单号---</option>
					<c:forEach var="b" items="${a}">
						<option value=""></option>
					</c:forEach>
				</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				订单号<span class="required">*</span><select style="height: 25px;width: 160px;">
					<option value="">---请选择订单号---</option>
					<c:forEach var="b" items="${a}">
						<option value=""></option>
					</c:forEach>
				</select>
			</p>
			</div>
				<p style="padding-left: 0px;">
					入库类型<span class="required">*</span><select style="height: 25px;width: 160px;" name="inStorage.impFlag">
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
					</select>&nbsp;&nbsp;&nbsp;&nbsp;
					物料类型<span class="required">*</span><select style="height: 25px;width: 160px;" name="inStorage.materialType.typeName">
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
				<p style="padding-left: 0px;">
					货物标志<span class="required">*</span><select style="height: 25px;width: 160px;" name="" id="imgexgflag">
						<option value="">---请选择---</option>
						<option value="">原料</option>
						<option value="">成品</option>
					</select>&nbsp;&nbsp;&nbsp;&nbsp;
					<span id="hcode">物料编码<span class="required">*</span><input style="height: 25px;width: 160px;" type="text" name="" readonly="readonly"><img alt="" style="cursor: pointer; margin-top: -5px;" id="showDialogForm" src="${pageContext.request.contextPath}/images/search.gif"></span>
					<span id="pcode" style="display: none;">商品编码<span class="required">*</span><input style="height: 25px;width: 160px;" type="text" name="" readonly="readonly"><img alt="" style="cursor: pointer; margin-top: -5px;" id="showDialogForm1" src="${pageContext.request.contextPath}/images/search.gif"></span>
				</p>
				<div class="dialog" id="dialog" title="货物选择">
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				<p style="border:solid 1px red;">${u.userName}</p>
				</div>
				<p style="padding-left: 0px;">
					物料名称<span class="required">*</span><input type="text" readonly="readonly" style="height: 25px;width: 160px;" name="inStorage.material.hsName" id="materialName" value="${inStorage.material.hsName}"/>&nbsp;&nbsp;&nbsp;&nbsp;
					规格型号<span class="required">*</span><input type="text" readonly="readonly" style="height: 25px;width: 160px;" name="inStorage.material.hsName" id="materialName" value=""/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</p>
				<p style="padding-left: 30px;">
					颜色<input type="text" style="height: 25px;width: 160px;" readonly="readonly" readonly="readonly" name="inStorage.material.color"/>&nbsp;&nbsp;&nbsp;&nbsp;
					入库数量<span class="required">*</span><input type="text" style="height: 25px;width: 160px;" id="inqty" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" name="inStorage.inQty"/>
					<!-- <input type="text" style="height: 25px;"/>&nbsp;&nbsp;&nbsp;&nbsp; -->
				</p>
				<p style="padding-left: 0px;">
					计量单位<span class="required">*</span><input type="text" readonly="readonly" style="height: 25px;width: 160px;" name="inStorage.material.unit.name"/>
					每件包装数量<span class="required">*</span><input type="text" style="height: 25px;width: 160px;" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" name="inStorage.specQty"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</p>
				<p style="padding-left: 30px;">
					件数<input type="text" readonly="readonly" style="height: 25px;width: 160px;" readonly="readonly" name="inStorage.pkgs"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					批次号<span class="required">*</span><input type="text" style="height: 25px;width: 160px;" name="inStorage.material.batchNO"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</p>
				<p style="padding-left: 7px;">
					入库日期<input type="text" readonly="readonly" id="inDate" style="height: 25px;width: 160px;" name="inStorage.impDate"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					备注<input type="text" style="height: 25px;width: 160px;" name="inStorage.note"/>
				</p>
				<p style="padding-left: 210px;">
					<input class="btn btn-small btn-danger pull-left" data-toggle="button" type="submit" value="保存"/>&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;<input class="btn btn-small btn-danger pull-left" data-toggle="button" type="reset" value="取消" style="margin-left: 10px;"/>
				</p>
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
  		$('#imgexgflag').change(function(){
  			var flag = $(this).find("option:selected").text(); 
  			if('原料'==flag){
  				$('#hcode').show();
  				$('#pcode').hide();
  			}else if('成品'==flag){
  				$('#hcode').hide();
  				$('#pcode').show();
  			}
  		});
  	});
  	
  </script>
</html>
