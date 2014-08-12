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
	<script type="text/javascript">
	var domloadPath='${loadPath}';
	</script>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/css/datepicker/jquery-ui-1.8.16.custom.css" type="text/css"></link>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/datepicker/jquery.ui.core.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/datepicker/jquery.ui.datepicker.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/datepicker/jquery.ui.datepicker-zh-CN.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/public/public.js"></script>
  </head>
  <script>
  </script>
  <body>
  <iframe name="empframe" id="saveEmpframe" style="display: none;"></iframe>
    <iframe name="imgframe" id="uploadimgframe" style="display: none;"></iframe>
    <div class="page-header position-relative" style="margin-bottom: 0px;">
    	<c:if test="${dept.id==null}">
			<h5>基础资料＞＞员工＞＞新增</h5>
		</c:if>
    	<c:if test="${dept.id!=null}">
			<h5>基础资料＞＞员工＞＞修改</h5>
		</c:if>
	</div>
	<div class="modal-footer">
		<input class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" value="保存" id="saveEmpl"/>
		<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" onclick="cancel()" title="恢复初始状态">取消</button>
	</div>
	<div class="row-fluid" id="mybox">
		<div class="span12">
		 <form action="<%=path %>/empl_saveEmpl.action" target="empframe" method="post" >
  	            <input type="hidden" name="id"/><!-- 为了判断是新增还是修改 -->
			<table id="sample-table-1" class="table table-striped table-bordered table-hover tablecss"  style=" font-size: 12px;">
				<tr>
					<td class="captioncss" style="text-align: right; width:100px;">员工号</td>
					<td class="hidden-480 addcss"><c:if test="${dept.id==null}"><input type="text" value="${dept.code}" name="empl.code" style="height:25px;"/><span style="color:red;">*</span></c:if>	<c:if test="${dept.id!=null}"><input type="text" value="${dept.code}" name="dept.code" style="height:25px;" disabled="disabled"/><span style="color:red;">*</span></c:if></td>
				    <td class="captioncss" style="text-align:  right; width:100px;">姓名</td>
					<td class="hidden-480 addcss" ><input type="text"  name="empl.name" id="names" style="height:25px;"/><span style="color:red;">*</span></td>
					<td class="hidden-480 addcss" rowspan="2" style="vertical-align:middle;"><input name="isloginUser" value="true" id="isloginUser" type="checkbox" />登录用户</td>
					<td class="hidden-480 addcss"  colspan="1" rowspan="2">
					<center>
					<img width="100px" height="150px" id="photoImg" alt="" src="<%=path%>/images/defaulttopimg.jpg"/><br/>
					<button class="btn btn-small btn-danger" style="margin-top: 5px;" data-toggle="button" type="button"  title="上传头像">上传</button>
					<input type="hidden" id="emplphoto" name="empl.photo"/>
					</center> 
					</td>
				</tr>
				<tr>
					<td class="captioncss" style="text-align: right; width:100px;">别名</td>
					<td class="hidden-480 addcss"><input type="text" name="empl.nickName" id="names" style="height:25px;"/><span style="color:red;">*</span></td>
				    <td class="captioncss" style="text-align: right; width:100px;">年龄</td>
					<td class="hidden-480 addcss"><select name="empl.age" id="age"></select><span style="color:red;">*</span></td>
				</tr>
				<tr id="loginuserTR" style="display: none;">
					<td class="captioncss" style="text-align: right; width:100px;">登录名<br></td>
					<td class="hidden-480 addcss"><input type="text" name="user.loginName" id="names" style="height:25px;"/><span style="color:red;">*</span></td>
				    <td class="captioncss" style="text-align: right; width:100px;">登录密码</td>
					<td class="hidden-480 addcss"><input type="password"  name="user.password" id="names" style="height:25px;"/><span style="color:red;">*</span></td>
		     	    <td class="captioncss" style="text-align: right; width:100px;">确认密码</td>
					<td class="hidden-480 addcss"><input type="password" name="passwordto" id="names" style="height:25px;"/><span style="color:red;">*</span></td>
				</tr>
				<tr>
					
					<td class="captioncss" style="text-align: right; width:100px;">性别</td>
					<td class="hidden-480 addcss"><select name="empl.gender"><option>男</option><option>女</option></select><span style="color:red;">*</span></td>
				    <td class="captioncss" style="text-align: right; width:100px;">出身日期</td>
					<td class="hidden-480 addcss"><input type="text" name="empl.birthday" style="height:25px;" id="birthday" readonly="readonly" /> <span style="color:red;">*</span></td>
		     	    <td class="captioncss" style="text-align: right; width:100px;">身份证</td>
					<td class="hidden-480 addcss"><input type="text" name="empl.identityCard"  style="height:25px;"/><span style="color:red;">*</span></td>
				</tr>
				<tr>
					<td class="captioncss" style="text-align: right; width:100px;">名族</td>
					<td class="hidden-480 addcss"><input type="text"  name="empl.nation" i style="height:25px;"/><span style="color:red;">*</span></td>
					<td class="captioncss" style="text-align: right; width:100px;">籍贯</td>
					<td class="hidden-480 addcss"><input type="text" name="empl.origo"  style="height:25px;"/><span style="color:red;">*</span></td>
				    <td class="captioncss" style="text-align: right; width:100px;">学历</td>
					<td class="hidden-480 addcss">
					<select name="empl.diploma">
					<option  value="xx">小学</option>
					<option value="cg">初中</option>
					<option value="gz">中专/高中</option>
					<option value="dz">大专</option>
					<option value="bk">本科</option>
					<option value="sh">硕士</option>
					<option value="bs">博士</option>
					<option value="bsh">博士后</option>
					</select>
					</td>
				</tr>
				<tr>
					<td class="captioncss" style="text-align: right; width:100px;">家庭住址</td>
					<td class="hidden-480 addcss"><input type="text" name="empl.address" id="names" style="height:25px;"/><span style="color:red;">*</span></td>
					<td class="captioncss" style="text-align: right; width:100px;">现住址</td>
					<td class="hidden-480 addcss"><input type="text"  name="empl.currentResidence" id="names" style="height:25px;"/><span style="color:red;">*</span></td>
					<td class="captioncss" style="text-align: right; width:100px;">联系电话</td>
					<td class="hidden-480 addcss"><input type="text" name="empl.linkPhone" id="names" style="height:25px;"/><span style="color:red;">*</span></td>
				</tr>
					<tr>
					<td class="captioncss" style="text-align: right; width:100px;">网络联系方式</td>
					<td class="hidden-480 addcss"><input type="text"  name="empl.networkLink" id="names" style="height:25px;"/><span style="color:red;">*</span></td>
					<td class="captioncss" style="text-align: right; width:100px;"> 入职日期</td>
					<td class="hidden-480 addcss"><input type="text" name="empl.entryDate" style="height:25px;" id="entryDate" readonly="readonly" /><span style="color:red;">*</span></td>
				    <td class="captioncss" style="text-align: right; width:100px;">所属部门</td>
					<td class="hidden-480 addcss"><select name="empl.department.id"><c:forEach items="${depts}" var="item"><option value="${item.id}">${item.name}</option></c:forEach></select><span style="color:red;">*</span></td>
				</tr>
				<tr>
					<td class="captioncss" style="text-align: right; width:100px;">  职位名称</td>
					<td class="hidden-480 addcss"><input type="text" name="empl.positionName" id="names" style="height:25px;"/><span style="color:red;">*</span></td>
			    	<td class="captioncss" style="text-align: right; width:100px;">备注</td>
					<td class="hidden-480 addcss" colspan="3"><textarea style="width: 450px;" rows="4" name="empl.note" id="note">${dept.note}</textarea></td>
				</tr>
			</table>
			</form>
		</div>
	</div> 
	<div></div>
	<div id="ulploddiv">
	    <div> 
	    <form name="uploadImg" action="<%=path%>/myupload_uploadAjax.action" method="post" target="imgframe" enctype="multipart/form-data">
	    <ul>
	    <li>选择上传文件 : <input name="uploadFile" type="file"/></li>
	    <li><button class="btn btn-small btn-danger pull-left" id="fileUpload" data-toggle="button" type="button" >上传</button></li>
	    </ul>
	    </form>
	    </div>
	</div>
<script type="text/javascript" src="<%=path%>/js/page/employee-page.js"></script>
  </body>
</html>
