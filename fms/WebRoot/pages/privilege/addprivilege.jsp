<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
   <script type="text/javascript" src="<%=path%>/js/jquery.form.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/public/public.js"></script>
  <script type="text/javascript">
     var piviId='${pivi.id}'?'${pivi.id}':0;
   </script>

  <body>
  	<!-- 为了判断是新增还是修改 -->
    <div class="page-header position-relative" style="margin-bottom: 0px;">
    	<c:if test="${pivi.id==null}">
			<h5>权限管理＞＞新增</h5>
		</c:if>
    	<c:if test="${pivi.id!=null}">
			<h5>权限管理＞＞修改</h5>
		</c:if>
	</div>
	<div class="modal-footer">
		<input class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" value="保存" onclick="save()"/>
		<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" onclick="cancel()" title="恢复初始状态">取消</button>
	</div>
	<form id="subform" action="${pageContext.request.contextPath}/pivi_save" method="post">
	<div class="row-fluid" id="mybox">
		<div class="span12">
		   <input type="hidden" id="flag" name="privilege.id" value="${pivi.id}"/>
			<table id="sample-table-1" class="table table-striped table-bordered table-hover tablecss"  style=" font-size: 12px;">
				<tr>
					<td class="captioncss" style="text-align: right; width:100px;">名称</td>
					<td class="hidden-480 addcss"><input type="text" value="${dept.name}" name="privilege.name" style="height:25px;"/><span style="color:red;">*</span></td>
				</tr>
				<tr>
					<td class="captioncss" style="text-align: right; width:100px;">编码</td>
					<td class="hidden-480 addcss"><input type="text" value="${pivi.code}" name="privilege.code" id="names" style="height:25px;"/><span style="color:red;">*</span></td>
				</tr>
				<tr>
					<td class="captioncss" style="text-align: right; width:100px;">权限地址</td>
					<td class="hidden-480 addcss"><textarea cols="70" rows="3" name="privilege.url" id="note">${pivi.url}</textarea><span style="color:red;">*</span></td>
				</tr>
				<tr>
					<td class="captioncss" style="text-align: right; width:100px;">权限说明</td>
					<td class="hidden-480 addcss"><textarea cols="70" rows="3" name="privilege.remakes" id="note">${pivi.remakes}</textarea></td>
				</tr>
				
			</table>
		</div>
	</div> 
	</form>
	
<script type="text/javascript">
	function save(){
		//编码
		var code = $("input[name='privilege.code']").val().trim();  
		//名称
		var name= $("input[name='privilege.name']").val().trim(); 
		//地址
		var url= $("textarea[name='privilege.url']").val().trim(); 
		//备注
		var remakes= $("textarea[name='privilege.remakes']").val().trim(); 

		if(code.length<=0){
			alert("编码不能为空！");
			return false;
		}
		if(name.length<=0){
			alert("名称不能为空！");
			return   false;
		}
		if(url.length<=0){
			alert("权限地址不能为空！");
			return   false;
		}
		  
		//新增
		if(piviId!=0){
			 $.post(Global+"/pivi_edit.action",{"privilege.code":code,"privilege.name":name,"privilege.url":url,"privilege.remakes":remakes,"privilege.id":piviId},function(result){
				 var results=jQuery.parseJSON(result);
				 if(results.success){
					 toMain(Global+"/pivi_privileges.action");
				 }else{
					 alert("保存失败:"+results.msg);
				 }
				  });
		}
		else{
			 $.post(Global+"/pivi_save.action",{"privilege.code":code,"privilege.name":name,"privilege.url":url,"privilege.remakes":remakes},function(result){
				 var results=jQuery.parseJSON(result);
				 if(results.success){
					 toMain(Global+"/pivi_privileges.action");
				 }else{
					 alert("保存失败:"+results.msg);
				 }
				  });
			/*
			privilege["privilege"]=privilege;
			var url = Global+"/pivi_save.action";
			$.ajax({
		     type: "POST",
		     url:url,
		     data:param,
		     dataType:"json",
		     async: false,
		     cache: false,
		     success:function(data){
		    	 alert(data);
		     var result=jQuery.parseJSON(data);
		     if(result.success){
		    	 toMain(Global+"/pivi_privileges.action");
		     	}else{
		     		alert("保存失败！稍后再试"+result.msg);
		     	}
		     },error:function(){
		        	alert("程序异常，请重新启动程序！");
		      }
		  	});
			*/
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
			var url = "${pageContext.request.contextPath}/dept_findDeptByid.action?ids="+id;
			toMain(url);
		}
	}
</script>
