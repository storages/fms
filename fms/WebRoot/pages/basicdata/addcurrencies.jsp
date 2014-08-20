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
    
    <title>My JSP 'adddept.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  <script type="text/javascript" src="${pageContext.request.contextPath}/js/public/public.js"></script>
  </head>
  
  <body>
  	<input type="hidden" id="flag" value="${curr.id}"/><!-- 为了判断是新增还是修改 -->
    <div class="page-header position-relative" style="margin-bottom: 0px;">
    	<c:if test="${curr.id==null}">
			<h5>基础资料＞＞交易货币管理＞＞新增</h5>
		</c:if>
    	<c:if test="${curr.id!=null}">
			<h5>基础资料＞＞交易货币管理＞＞修改</h5>
		</c:if>
	</div>
	<div class="modal-footer">
		<input class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" value="保存" onclick="save()"/>
		<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" onclick="cancel()" title="恢复初始状态">取消</button>
	</div>
	<div class="row-fluid" id="mybox">
		<div class="span12">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover tablecss"  style=" font-size: 12px;">
				<tr>
					<td class="captioncss" style="text-align: right; width:100px;">编码</td>
					<c:if test="${curr.id==null}">
						<td class="hidden-480 addcss"><input type="text" value="${curr.code}" name="curr.code" style="height:25px;"/><span style="color:red;">*</span></td>
					</c:if>
					<c:if test="${curr.id!=null}">
						<td class="hidden-480 addcss"><input type="text" value="${curr.code}" name="curr.code" style="height:25px;" disabled="disabled"/><span style="color:red;">*</span></td>
					</c:if>
				</tr>
				<tr>
					<td class="captioncss" style="text-align: right; width:100px;">名称</td>
					<td class="hidden-480 addcss"><input type="text" value="${curr.name}" name="curr.name" id="names" style="height:25px;"/><span style="color:red;">*</span></td>
				</tr>
				<tr>
					<td class="captioncss" style="text-align: right; width:100px;">备注</td>
					<td class="hidden-480 addcss"><textarea cols="40" rows="3" name="curr.note" id="note">${curr.note}</textarea></td>
				</tr>
			</table>
		</div>
	</div> 
	
<script type="text/javascript">
	function save(){
		//编码
		var code = $(":input[name='curr.code']").val();  
		//名称
		var name= $(":input[name='curr.name']").val(); 
		//备注
		var note= $("#note").val(); 
		var isEdit = $('#flag').val();
		var isPass = true;
		if(code==""){
			alert("编码不能为空！");
			isPass = false;
			return;
		}
		if(name==""){
			alert("名称不能为空！");
			isPass = false;
			return;
		}
		//新增
		if(isEdit==""){
			var url = "${pageContext.request.contextPath}/currencies_findCurrenciesByCode.action?code="+code;
			$.ajax({
		     type: "POST",
		     url:url,
		     async: false,
		     cache: false,
		     success:function(data){
		     var result=jQuery.parseJSON(data);
		     if(!result.success){
		     		alert(result.msg);
		     		isPass = false;
		     	}
		     },error:function(){
		        	alert("程序异常，请重新启动程序！");
		      }
		  	});
		}
		if(isPass){
			var param = "code="+code+"&name="+parse(name)+"&note="+parse(note)+"&ids="+isEdit;
			var submitUrl = "${pageContext.request.contextPath}/currencies_saveCurrencies.action?"+param;
			toMain(submitUrl);
		}
	}
	
	//取消按钮
	function cancel(){
		var id = $('#flag').val();
		if(id==""){
			$(":input[name='dept.code']").val("");
			$(":input[name='dept.name']").val("");
			$("#note").val("");
		}else{
			var url = "${pageContext.request.contextPath}/currencies_findCurrenciesById.action?ids="+id;
			toMain(url);
		}
	}
</script>
  </body>
</html>
