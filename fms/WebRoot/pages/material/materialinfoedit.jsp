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
    
    <title>My JSP 'materialedit.jsp' starting page</title>
    
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
    <input type="hidden" id="flag" value="${materinfo.id}"/><!-- 为了判断是新增还是修改 -->
  <div class="page-header position-relative" style="margin-bottom: 0px;">
    	<c:if test="${materinfo.id==null}">
			<h5>物料＞＞物料信息＞＞新增</h5>
		</c:if>
    	<c:if test="${materinfo.id!=null}">
			<h5>物料＞＞物料信息＞＞修改</h5>
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
					<td class="captioncss" style="text-align: right; ">物料编码</td>
					<td class="hidden-480 addcss" style=""><input type="text" value="${materinfo.hsCode}" name="material.hsCode" style="height:25px;" onblur="check()" id="codes"/><span style="color:red;">*</span></td>
					<td class="captioncss" style="text-align: right; ">物料名称</td>
					<td class="hidden-480 addcss" style=""><input type="text" value="${materinfo.hsName}" name="material.hsName" style="height:25px;" onblur="check()"/><span style="color:red;">*</span></td>
				</tr>
				<tr>
					<td class="captioncss" style="text-align: right; ">颜色</td>
					<td class="hidden-480 addcss" style=""><input type="text" value="${materinfo.color}" name="material.color" style="height:25px;"/><span style="color:red;">*</span></td>
					<td class="captioncss" style="text-align: right; ">物料类别</td>
					<td class="hidden-480 addcss" style="">
					<select id="form-field-select-1">
						<option value="I">原料</option>
						<option value="E">成品</option>
					</select>
						<span style="color:red;">*</span>
					</td>
				</tr>
				<tr>
					<td class="captioncss" style="text-align: right; ">计量单位</td>
					<td class="hidden-480 addcss" style="">
					<select name="material.unit" id="form-field-select-1">
						<c:forEach var="unit" items="${units}">
							<option value="${unit.name}">${unit.name}</option>
						</c:forEach>
					</select>
					<%-- <input type="text" value="${materinfo.unit.name}" name="material.unit" style="height:25px;"/> --%>
					
					<span style="color:red;">*</span></td>
					<td class="captioncss" style="text-align: right; " onblur="check()">规格</td>
					<td class="hidden-480 addcss" style=""><input type="text" value="${materinfo.model}" name="material.model" style="height:25px;"/><span style="color:red;">*</span></td>
				</tr>
				<tr>
					<td class="captioncss" style="text-align: right; ">数量</td>
					<td class="hidden-480 addcss" style=""><input type="text" value="${materinfo.qty}" name="material.qty" style="height:25px;"/><span style="color:red;">*</span></td>
					<td class="captioncss" style="text-align: right; " onblur="check()">批次号</td>
					<td class="hidden-480 addcss" style=""><input type="text" value="${materinfo.batchNO}" name="material.batchNO" style="height:25px;"/><span style="color:red;">*</span></td>
				</tr>
				<tr>
					<td class="captioncss" style="text-align: right; ">最低库存</td>
					<td class="hidden-480 addcss" style=""><input type="text" value="${materinfo.lowerQty}" name="material.lowerQty" style="height:25px;"/><span style="color:red;">*</span></td>
					<td class="captioncss" style="text-align: right; ">备注</td>
					<td class="hidden-480 addcss" style=""><textarea cols="120" rows="3" name="material.note" id="note">${materinfo.note}</textarea></td>
				</tr>
			</table>
		</div>
	</div>
  </body>
  <script type="text/javascript">
  var isPass = true;
			//名称
		function save(){
			var id= $('#matrId').val();
			var code= $(":input[name='material.hsCode']").val(); 
			var name= $(":input[name='material.hsName']").val(); 
			var color= $(":input[name='material.color']").val(); 
			var imgExgFlag= $("#form-field-select-1").val() ;
			var model= $(":input[name='material.model']").val(); 
			var unit= $(":input[name='material.unit']").val(); 
			var qty= $(":input[name='material.qty']").val(); 
			var batchNO= $(":input[name='material.batchNO']").val(); 
			var lowerQty= $(":input[name='material.lowerQty']").val(); 
			//备注
			var note= $("#note").val(); 
			var isEdit = $('#flag').val();
			if(code=="" || code==null){
				alert("编码不能为空！");
				isPass = false;
				return;
			}else if(name=="" || name==null){
				alert("名称不能为空！");
				isPass = false;
				return;
			}else if(color=="" || color==null){
				alert("颜色不能为空！");
				isPass = false;
				return;
			}else if(unit=="" || unit==null){
				alert("计量单位不能为空！");
				isPass = false;
				return;
			}else if(model=="" || model==null){
				alert("规格不能为空！");
				isPass = false;
				return;
			}else if(qty=="" || qty==null){
				alert("数量不能为空！");
				isPass = false;
				return;
			}else if(batchNO=="" || batchNO==null){
				alert("批次号不能为空！");
				isPass = false;
				return;
			}else if(lowerQty=="" || lowerQty==null){
				alert("最低库存不能为空！");
				isPass = false;
				return;
			}
			//新增
			if(isEdit==""){
				var url = "${pageContext.request.contextPath}/materInfo_checkMaterial.action?hsCode="+ code +"&hsName="+name+"&batchNO="+batchNO+"&model="+model;
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
				var param = "hsCode="+code+"&hsName="+parse(name)+"&color="+parse(color)+"&imgExgFlag="+parse(imgExgFlag)+"&model="+parse(model)+"&batchNO="+parse(batchNO)+"&unit="+parse(unit)+"&qty="+qty+"&lowerQty="+lowerQty+"&note="+parse(note)+"&ids="+isEdit
				var submitUrl = "${pageContext.request.contextPath}/materInfo_save.action?"+param;
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
		
		function check(){
			var code = $("#codes").val();
				var url = "${pageContext.request.contextPath}/materInfo_findHsCode.action?hsCode="+code;
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
				     	return;
				     }
			     },error:function(){
			        alert("程序异常，请重新启动程序！");
			      }
			  	});
		}
	</script>
</html>
