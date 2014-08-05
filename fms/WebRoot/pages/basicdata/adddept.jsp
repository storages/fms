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

  </head>
  
  <body>
  	<input type="hidden" id="flag" value="${dept.id}"/><!-- 为了判断是新增还是修改 -->
    <input type="hidden" value="${dept.id}" id="flag"/>
    <div class="page-header position-relative" style="margin-bottom: 0px;">
    	<c:if test="${dept.id==null}">
			<h5>基础资料＞＞部门管理＞＞新增</h5>
		</c:if>
    	<c:if test="${dept.id!=null}">
			<h5>基础资料＞＞部门管理＞＞修改</h5>
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
					<c:if test="${dept.id==null}">
						<td class="hidden-480 addcss"><input type="text" value="${dept.code}" name="dept.code" style="height:25px;"/><span style="color:red;">*</span></td>
					</c:if>
					<c:if test="${dept.id!=null}">
						<td class="hidden-480 addcss"><input type="text" value="${dept.code}" name="dept.code" style="height:25px;" disabled="disabled"/><span style="color:red;">*</span></td>
					</c:if>
				</tr>
				<tr>
					<td class="captioncss" style="text-align: right; width:100px;">名称</td>
					<td class="hidden-480 addcss"><input type="text" value="${dept.name}" name="dept.name" id="names" style="height:25px;"/><span style="color:red;">*</span></td>
				</tr>
				<tr>
					<td class="captioncss" style="text-align: right; width:100px;">备注</td>
					<td class="hidden-480 addcss"><textarea cols="40" rows="3" name="dept.note"></textarea></td>
				</tr>
			</table>
		</div>
	</div> 
	
<script type="text/javascript">
	function save(){
		//编码
		var code = $(":input[name='dept.code']").val();  
		//名称
		var name= $(":input[name='dept.name']").val(); 
		//备注
		var note= $(":input[name='dept.note']").val(); 
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
			reutrn;
		}
		//新增
		if(isEdit==""){
			var url = "${pageContext.request.contextPath}/dept_findDeptByCode.action?code="+code;
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
			var param = "code="+code+"&name="+name+"&note="+note;
			var submitUrl = "${pageContext.request.contextPath}/dept_saveDept.action?"+param;
			alert(submitUrl);
			toMain(submitUrl);
		}
	}
</script>
  </body>
</html>
