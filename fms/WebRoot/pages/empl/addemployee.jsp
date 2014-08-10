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
  <iframe name="send" style="display: none;"></iframe>
    <div class="page-header position-relative" style="margin-bottom: 0px;">
    	<c:if test="${dept.id==null}">
			<h5>基础资料＞＞员工＞＞新增</h5>
		</c:if>
    	<c:if test="${dept.id!=null}">
			<h5>基础资料＞＞员工＞＞修改</h5>
		</c:if>
	</div>
	<div class="modal-footer">
		<input class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" value="保存" onclick="save()"/>
		<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" onclick="cancel()" title="恢复初始状态">取消</button>
	</div>
	<div class="row-fluid" id="mybox">
		<div class="span12">
		 <form action="<%=path %>/empl_save" target="send">
  	<input type="hidden" id="flag" value="${dept.id}"/><!-- 为了判断是新增还是修改 -->
			<table id="sample-table-1" class="table table-striped table-bordered table-hover tablecss"  style=" font-size: 12px;">
				<tr>
					<td class="captioncss" style="text-align: right; width:100px;">编码</td>
					<td class="hidden-480 addcss"><c:if test="${dept.id==null}"><input type="text" value="${dept.code}" name="dept.code" style="height:25px;"/><span style="color:red;">*</span></c:if>	<c:if test="${dept.id!=null}"><input type="text" value="${dept.code}" name="dept.code" style="height:25px;" disabled="disabled"/><span style="color:red;">*</span></c:if></td>
				    <td class="captioncss" style="text-align: right; width:100px;">姓名</td>
					<td class="hidden-480 addcss"><input type="text"  name="dept.name" id="names" style="height:25px;"/><span style="color:red;">*</span></td>
					<td class="hidden-480 addcss"  colspan="2" rowspan="2">
					<center>
					<img width="100px" height="150px" alt="" src="<%=path%>/images/defaulttopimg.jpg"/><br/>
					<button class="btn btn-small btn-danger" style="margin-top: 5px;" data-toggle="button" type="button"  title="上传头像">上传</button>
					</center> 
					</td>
				</tr>
				<tr>
				
					<td class="captioncss" style="text-align: right; width:100px;">别名</td>
					<td class="hidden-480 addcss"><input type="text" name="dept.name" id="names" style="height:25px;"/><span style="color:red;">*</span></td>
				    <td class="captioncss" style="text-align: right; width:100px;">年龄</td>
					<td class="hidden-480 addcss"><input type="text"  name="dept.name" id="names" style="height:25px;"/><span style="color:red;">*</span></td>
				</tr>
				<tr>
					
					<td class="captioncss" style="text-align: right; width:100px;">性别</td>
					<td class="hidden-480 addcss"><select><option>男</option><option>女</option></select><span style="color:red;">*</span></td>
				    <td class="captioncss" style="text-align: right; width:100px;">出身日期</td>
					<td class="hidden-480 addcss"><input type="text"  name="dept.name" id="names" style="height:25px;"/><span style="color:red;">*</span></td>
		     	    <td class="captioncss" style="text-align: right; width:100px;">身份证</td>
					<td class="hidden-480 addcss"><input type="text" name="dept.name" id="names" style="height:25px;"/><span style="color:red;">*</span></td>
				</tr>
				<tr>
					<td class="captioncss" style="text-align: right; width:100px;">名族</td>
					<td class="hidden-480 addcss"><input type="text"  name="dept.name" id="names" style="height:25px;"/><span style="color:red;">*</span></td>
					<td class="captioncss" style="text-align: right; width:100px;">籍贯</td>
					<td class="hidden-480 addcss"><input type="text" name="dept.name" id="names" style="height:25px;"/><span style="color:red;">*</span></td>
				    <td class="captioncss" style="text-align: right; width:100px;">学历</td>
					<td class="hidden-480 addcss"><input type="text"  name="dept.name" id="names" style="height:25px;"/><span style="color:red;">*</span></td>
				</tr>
				<tr>
					<td class="captioncss" style="text-align: right; width:100px;">家庭住址</td>
					<td class="hidden-480 addcss"><input type="text" name="dept.name" id="names" style="height:25px;"/><span style="color:red;">*</span></td>
					<td class="captioncss" style="text-align: right; width:100px;">现住址</td>
					<td class="hidden-480 addcss"><input type="text"  name="dept.name" id="names" style="height:25px;"/><span style="color:red;">*</span></td>
					<td class="captioncss" style="text-align: right; width:100px;">联系电话</td>
					<td class="hidden-480 addcss"><input type="text" name="dept.name" id="names" style="height:25px;"/><span style="color:red;">*</span></td>
				</tr>
					<tr>
					<td class="captioncss" style="text-align: right; width:100px;">网络联系方式</td>
					<td class="hidden-480 addcss"><input type="text"  name="dept.name" id="names" style="height:25px;"/><span style="color:red;">*</span></td>
					<td class="captioncss" style="text-align: right; width:100px;"> 入职日期</td>
					<td class="hidden-480 addcss"><input type="text" name="dept.name" id="names" style="height:25px;"/><span style="color:red;">*</span></td>
				    <td class="captioncss" style="text-align: right; width:100px;">所属部门</td>
					<td class="hidden-480 addcss"><select><c:forEach items="${depts}" var="item"><option>${item.name}</option></c:forEach></select><span style="color:red;">*</span></td>
				</tr>
				<tr>
					<td class="captioncss" style="text-align: right; width:100px;">  职位名称</td>
					<td class="hidden-480 addcss"><input type="text" name="dept.name" id="names" style="height:25px;"/><span style="color:red;">*</span></td>
			    	<td class="captioncss" style="text-align: right; width:100px;">备注</td>
					<td class="hidden-480 addcss" colspan="3"><textarea style="width: 450px;" rows="4" name="dept.note" id="note">${dept.note}</textarea></td>
				</tr>
			</table>
			</form>
		</div>
	</div> 
<script type="text/javascript">
	function save(){
		//编码
		var code = $(":input[name='dept.code']").val();  
		//名称
		var name= $(":input[name='dept.name']").val(); 
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
			var param = "code="+code+"&name="+parse(name)+"&note="+parse(note)+"&ids="+isEdit;
			var submitUrl = "${pageContext.request.contextPath}/dept_saveDept.action?"+param;
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
			var url = "${pageContext.request.contextPath}/dept_findDeptByid.action?ids="+id;
			toMain(url);
		}
	}
</script>
<script type="text/javascript" src="<%=path%>/js/page/employee-page.js"></script>
  </body>
</html>
