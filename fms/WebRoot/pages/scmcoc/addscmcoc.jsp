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
	<div class="modal-footer">
		<input class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" value="保存" onclick="save()"/>
		<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" onclick="">取消</button>
	</div> 
	<div class="row-fluid" id="mybox">
		<div class="span12">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover tablecss"  style=" font-size: 12px;">
				<tr>
					<td class="captioncss" style="text-align: right;">编码</td>
					<td class="hidden-480 addcss"><input type="text" value="${scmcoc.code}" name="scmcoc.code" style="height:25px;"/><span style="color:red;">*</span></td>
					<td class="captioncss" style="text-align: right;">名称</td>
					<td class="hidden-480 addcss"><input type="text" value="${scmcoc.name}" name="scmcoc.name" id="names" style="height:25px;"/><span style="color:red;">*</span></td>
				</tr>
				<tr>
					<td class="captioncss" style="text-align: right;">联系电话</td>
					<td class="hidden-480 addcss"><input type="text" value="${scmcoc.linkPhone}" name="scmcoc.linkPhone" style="height:25px;"/>
					<td class="captioncss" style="text-align: right;">网络联系方式</td>
					<td class="hidden-480 addcss"><input type="text" value="${scmcoc.networkLink}" name="scmcoc.networkLink" style="height:25px;"/></td>
				</tr>
				<tr>
					<td class="captioncss" style="text-align: right;">联系人</td>
					<td class="hidden-480 addcss"><input type="text" value="${scmcoc.linkMan}" name="scmcoc.linkMan" id="link" style="height:25px;"/></td>
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
		var code = $(":input[name='scmcoc.code']").val();  
		//名称
		var name= $(":input[name='scmcoc.name']").val(); 
		//联系电话
		var linkPhone =  $(":input[name='scmcoc.linkPhone']").val(); 
		//网络联系方式
		var networkLink =  $(":input[name='scmcoc.networkLink']").val(); 
		//联系人
		var linkMan = $(":input[name='scmcoc.linkMan']").val(); 
		alert(linkMan);
		//地址
		var address = $(":input[name='scmcoc.address']").val(); 
		//约定结算时间
		var endDate = $(":input[name='scmcoc.endDate']").val(); 
		//备注
		var note = $(":input[name='scmcoc.note']").val(); 
		if(code==""){
			alert("编码不能为空！");
			return false;
		}
		if(name==""){
			alert("供应商名称不能为空！");
			return false;
		}
		//编码是否可用
		var url = "${pageContext.request.contextPath}/scmcoc_findScmcocByCode.action?code="+code;
		$.ajax({
	     type: "POST",
	     url:url,
	     async: false,
	     cache: false,
	     success:function(data){
	     	if("false"==data){
	     		alert("编码已使用过！");
	     		return false;
	     	}
	     },error:function(){
	        	$("#mess").html("程序异常，请重新启动程序！");
	        	return false;
	      }
	  	});
	  	var str = "code="+parse(code)+
	  			  "&name="+parse(name)+
	  			  "&linkPhone="+parse(linkPhone)+
	  			  "&networkLink="+parse(networkLink)+
	  			  "&linkMan="+parse(linkMan)+
	  			  "&address="+parse(address)+
	  			  "&endDate="+parse(endDate)+
	  			  "&note="+parse(note)+
	  			  "&isCustom=false";
	  			  alert(parse(linkMan));
	  	var submitUrl = "${pageContext.request.contextPath}/scmcoc_saveScmcoc.action?"+str;
	  	toMain(submitUrl);
	}
	
	function parse(str){
		return encodeURI(encodeURI(str));  
	}
	</script>
</html>
