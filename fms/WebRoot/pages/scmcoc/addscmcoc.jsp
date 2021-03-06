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
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/public/public.js"></script>
  </head>
  <body>
  	<input type="hidden" value="${scmcoc.id}" id="flag"/>
    <div class="page-header position-relative" style="margin-bottom: 0px;">
    	<c:if test="${scmcoc.id==null}">
			<h5>基础资料＞＞供应商管理＞＞新增</h5>
		</c:if>
    	<c:if test="${scmcoc.id!=null}">
			<h5>基础资料＞＞供应商管理＞＞修改</h5>
		</c:if>
	</div>
	<div class="modal-footer">
		<input class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" value="保存" onclick="save()"/>
		<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" onclick="cancel()" title="恢复初始状态">取消</button>
		<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" onclick="closefrom()" title="关闭此窗口">关闭</button>
	</div> 
	<div class="row-fluid" id="mybox">
		<div class="span12">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover tablecss"  style=" font-size: 12px;">
				<tr>
					<td class="captioncss" style="text-align: right; width:100px;">编码</td>
					<c:if test="${scmcoc.id==null}">
						<td class="hidden-480 addcss"><input type="text" value="${scmcoc.code}" name="scmcoc.code" style="height:25px;"/><span style="color:red;">*</span></td>
					</c:if>
					<c:if test="${scmcoc.id!=null}">
						<td class="hidden-480 addcss"><input type="text" value="${scmcoc.code}" name="scmcoc.code" style="height:25px;" disabled="disabled"/><span style="color:red;">*</span></td>
					</c:if>
					<td class="captioncss" style="text-align: right; width:100px;">名称</td>
					<td class="hidden-480 addcss"><input type="text" value="${scmcoc.name}" name="scmcoc.name" id="names" style="height:25px;"/><span style="color:red;">*</span></td>
				</tr>
				<tr>
					<td class="captioncss" style="text-align: right; width:100px;">联系电话</td>
					<td class="hidden-480 addcss"><input type="text" value="${scmcoc.linkPhone}" name="scmcoc.linkPhone" style="height:25px;"/>
					<td class="captioncss" style="text-align: right;">网络联系方式</td>
					<td class="hidden-480 addcss"><input type="text" value="${scmcoc.networkLink}" name="scmcoc.networkLink" style="height:25px;"/></td>
				</tr>
				<tr>
					<td class="captioncss" style="text-align: right; width:100px;">联系人</td>
					<td class="hidden-480 addcss"><input type="text" value="${scmcoc.linkMan}" name="scmcoc.linkMan" id="link" style="height:25px;"/></td>
					<td class="captioncss" style="text-align: right;">地址</td>
					<td class="hidden-480 addcss"><input type="text" value="${scmcoc.address}" name="scmcoc.address" style="height:25px;"/></td>
				</tr>
				<tr>
					<td class="captioncss" style="text-align: right; width:100px;">约定结算日期  每月</td>
					<td class="hidden-480 addcss">
						<%-- <input type="text" value="${scmcoc.endDate}" name="scmcoc.endDate" style="height:25px;" id="datepicker" readonly="readonly" />日<span>m-d</span> --%>
						<select id="endDate" style="width:200px;">
							<c:forEach var="day" begin="1" end="28" step="1">
								<c:if test="${scmcoc.endDate==day}">
									<option value="${day}" selected="selected">${day}</option>
								</c:if>
								<c:if test="${scmcoc.endDate!=day}">
									<option value="${day}">${day}</option>
								</c:if>
							</c:forEach>
						</select>日 <span style="color:red;">*</span>
					</td>
					<td class="captioncss" style="text-align: right;">结算方式</td>
					<td class="hidden-480 addcss">
						<select style="width:200px;" id="sett">
							<c:forEach var="settl" items="${settlements}">
								<c:if test="${scmcoc.settlement.name==settl.name}">
									<option value="${settl.id}" selected="selected">${settl.name}</option>
								</c:if>
								<c:if test="${scmcoc.settlement.name!=settl.name}">
									<option value="${settl.id}">${settl.name}</option>
								</c:if>
									<%-- <option value="${settl.id}">${settl.name}</option> --%>
							</c:forEach>
						</select> <span style="color:red;">*</span>
					</td>
				</tr>
				<tr>
					<td class="captioncss" style="text-align: right;">备注</td>
					<td colspan="3" class="hidden-480 addcss">
						<textarea rows="4" cols="10" name="scmcoc.note" id="note" style="width:600px;">${scmcoc.note}</textarea>
					</td>
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
		//地址
		var address = $(":input[name='scmcoc.address']").val(); 
		//结算方式【取id,到后台查】
		var settlementId = $("#sett option:selected").val(); 
		//约定结算时间
		//var endDate = $(":input[name='scmcoc.endDate']").val(); 
		var endDate = $("#endDate option:selected").val(); 
		//备注
		var note = $("#note").val(); 
		var isPass = true;
		if(code==""){
			alert("编码不能为空！");
			return false;
		}
		if(name==""){
			alert("供应商名称不能为空！");
			return false;
		}
		if(settlementId==""){
			alert("结算方式不能为空！");
			return false;
		}
		if(endDate==""){
			alert("约定结算日期不能为空！");
			return false;
		}
		//编码是否可用
		var isEdit = $("#flag").val();
		if(isEdit==""){
			var url = "${pageContext.request.contextPath}/scmcoc_findScmcocByCode.action?code="+code;
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
		  			  "&linkPhone="+parse(linkPhone)+
		  			  "&networkLink="+parse(networkLink)+
		  			  "&linkMan="+parse(linkMan)+
		  			  "&address="+parse(address)+
		  			  "&settlementId="+settlementId+
		  			  "&endDate="+parse(endDate)+
		  			  "&note="+parse(note)+
		  			  "&isCustom=false";
		  	var submitUrl = "${pageContext.request.contextPath}/scmcoc_saveScmcoc.action?"+str;
		  	if(isEdit!=""){
		  		submitUrl = submitUrl +"&ids="+isEdit;
		  	}
	  	toMain(submitUrl);
	  	}
	}
	
	function parse(str){
		return encodeURI(encodeURI(str));  
	}
	
	//取消按钮
	function cancel(){
		var id = $('#flag').val();
		if(id==""){
			//编码
			$(":input[name='scmcoc.code']").val("");  
			//名称
			$(":input[name='scmcoc.name']").val(""); 
			//联系电话
			 $(":input[name='scmcoc.linkPhone']").val(""); 
			//网络联系方式
			$(":input[name='scmcoc.networkLink']").val(""); 
			//联系人
			$(":input[name='scmcoc.linkMan']").val(""); 
			//地址
			$(":input[name='scmcoc.address']").val(""); 
			//约定结算时间
			//var endDate = $(":input[name='scmcoc.endDate']").val(); 
			$("#endDate option:selected").val(""); 
			//备注
			$(":input[name='scmcoc.note']").val(""); 
		}else{
			var url = "${pageContext.request.contextPath}/scmcoc_findScmcocById.action?ids="+id+"&isCustom=false";
			toMain(url);
		}
	}
	function closefrom(){
		var url = "${pageContext.request.contextPath}/scmcoc_findAllScmcoc.action?isCustom=false";
		toMain(url);
	}
	</script>
</html>
