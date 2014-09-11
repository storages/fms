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
    
    <title>My JSP 'addstock.jsp' starting page</title>
    
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
  <input type="hidden" id="flag" value="${mater.id}"/><!-- 为了判断是新增还是修改 -->
  <div class="page-header position-relative" style="margin-bottom: 0px;">
    	<c:if test="${mater.id==null}">
			<h5>物料＞＞物料类别＞＞新增</h5>
		</c:if>
    	<c:if test="${mater.id!=null}">
			<h5>物料＞＞物料类别＞＞修改</h5>
		</c:if>
	</div>
	<div class="modal-footer">
		<input class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" value="保存" onclick="save();"/>
		<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" onclick="cancel()" title="恢复初始状态">取消</button>
	</div>
	<div class="row-fluid" id="mybox">
		<div class="span12">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover tablecss"  style=" font-size: 12px;">
				<tr>
					<td class="captioncss" style="text-align: right; ">名称</td>
					<td class="hidden-480 addcss" style=""><input type="text" value="${mater.typeName}" name="materialType.typeName" style="height:25px;"/><span style="color:red;">*</span></td>
				</tr>
				<tr>
					<td class="captioncss" style="text-align: right; ">备注</td>
					<td class="hidden-480 addcss" style=""><textarea cols="40" rows="3" name="materialType.note" id="note">${mater.note}</textarea></td>
				</tr>
			</table>
		</div>
	</div>
  </body>
  <script type="text/javascript">
		function save(){
			//名称
			var name= $(":input[name='materialType.typeName']").val(); 
			//备注
			var note= $("#note").val(); 
			var isEdit = $('#flag').val();
			var isPass = true;
			if(name==""){
				alert("名称不能为空！");
				isPass = false;
				return;
			}
			//新增
			if(isEdit==""){
				var url = "${pageContext.request.contextPath}/mater_findTypeByName.action?name="+name;
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
				var param = "name="+parse(name)+"&note="+parse(note)+"&ids="+isEdit;
				var submitUrl = "${pageContext.request.contextPath}/mater_save.action?"+param;
				toMain(submitUrl);
			}
		}
		
		//取消按钮
		function cancel(){
			var id = $('#flag').val();
			if(id==""){
				$(":input[name='materialType.name']").val("");
				$("#note").val("");
			}else{
				var url = "${pageContext.request.contextPath}/mater_findTypeById.action?ids="+id;
				toMain(url);
			}
		}
	</script>
</html>
