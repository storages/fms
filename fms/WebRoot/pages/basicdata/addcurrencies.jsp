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
    
    <title>My JSP 'addcurrencies.jsp' starting page</title>
    
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
  	<input type="hidden" value="${currencies.id}" id="flag"/>
    <div class="page-header position-relative" style="margin-bottom: 0px;">
    	<c:if test="${currencies.id==null}">
			<h5>基础资料＞＞交易货币管理＞＞新增</h5>
		</c:if>
    	<c:if test="${currencies.id!=null}">
			<h5>基础资料＞＞交易货币管理＞＞修改</h5>
		</c:if>
	</div>
	<div class="modal-footer">
		<input class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" value="保存" onclick="save()"/>
		<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" onclick="">取消</button>
	</div> 
	<div class="row-fluid" id="mybox">
		<div class="span12">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover tablecss"  style=" font-size: 12px;">
				<tr>
					<td class="captioncss" style="text-align: right; width:100px;">编码</td>
					<c:if test="${currencies.id==null}">
						<td class="hidden-480 addcss"><input type="text" value="${currencies.code}" name="scmcoc.code" style="height:25px;"/><span style="color:red;">*</span></td>
					</c:if>
					<c:if test="${currencies.id!=null}">
						<td class="hidden-480 addcss"><input type="text" value="${currencies.code}" name="scmcoc.code" style="height:25px;" disabled="disabled"/><span style="color:red;">*</span></td>
					</c:if>
					<td class="captioncss" style="text-align: right; width:100px;">名称</td>
					<td class="hidden-480 addcss"><input type="text" value="${currencies.name}" name="scmcoc.name" id="names" style="height:25px;"/><span style="color:red;">*</span></td>
				</tr>
				<tr>
					<td class="captioncss" style="text-align: right;">备注</td>
					<td class="hidden-480 addcss"><input type="text" value="${currencies.note}" name="scmcoc.note" style="height:25px;"/></td>
				</tr>
			</table>
		</div>
	</div>
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
	
	//保存
	function save(){
		//编码
		var code = $(":input[name='currencies.code']").val();  
		//名称
		var name= $(":input[name='currencies.name']").val(); 
		 
		//备注
		var note = $(":input[name='currencies.note']").val(); 
		var isPass = true;
		if(code==""){
			alert("编码不能为空！");
			return false;
		}
		if(name==""){
			alert("供应商名称不能为空！");
			return false;
		}
		
		//编码是否可用
		var isEdit = $("#flag").val();
		if(isEdit==""){
			var url = "${pageContext.request.contextPath}/currencies_findCurrenciesByCode.action?code="+code;
			$.ajax({
		     type: "POST",
		     url:url,
		     async: false,
		     cache: false,
		     success:function(data){
		     	if("false"==data){
		     		alert("编码已使用过！");
		     		isPass = false;
		     	}
		     },error:function(){
		        	$("#mess").html("程序异常，请重新启动程序！");
		        	return false;
		      }
		  	});
	  	}
	  	if(isPass){
		  	var str = "code="+parse(code)+
		  			  "&name="+parse(name)+
		  			  "&note="+parse(note);
		  	var submitUrl = "${pageContext.request.contextPath}/currencies_saveCurrencies.action?"+str;
		  	if(isEdit!=""){
		  		submitUrl = submitUrl +"&ids="+isEdit;
		  	}
	  	toMain(submitUrl);
	  	}
	}
	
	function parse(str){
		return encodeURI(encodeURI(str));  
	}
	</script>
</html>
