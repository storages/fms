
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
	var domloadPath ='${loadPath}';
	var selectAge='${empl.age}';
	$("#age").val(selectAge);
	var selectgender='${empl.gender}';
	$("#gender").val(selectgender);
	var selectnation='${empl.nation}';
	$("#nation").val(selectnation);
	var selectDiploma='${empl.diploma}';
	$("#diploma").val(selectDiploma);
	var selectDepartment='${empl.department.id}';
	$("#department").val(selectDepartment);
</script>
<link rel="stylesheet" href="<%=path%>/css/page/canvas.css"  type="text/css" /></link>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/datepicker/jquery-ui-1.8.16.custom.css"
	type="text/css"></link>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/datepicker/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/datepicker/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/datepicker/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/public/public.js"></script>

</head>
<script>
	
</script>
<body style="position: relative;">
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
		<input class="btn btn-small btn-danger pull-left" data-toggle="button"
			type="button" value="保存" id="saveEmpl" />
		<button class="btn btn-small btn-danger pull-left"
			data-toggle="button" type="button" id="empreset" title="恢复初始状态">取消</button>
	</div>
	<div class="row-fluid" id="mybox">
		<div class="span12">
			<form action="<%=path%>/empl_saveEmpl.action" target="empframe"
				method="post">
				<input type="hidden" name="id" value="${empl.id}" />
				<!-- 为了判断是新增还是修改 -->
				<table id="sample-table-1"
					class="table table-striped table-bordered table-hover tablecss"
					style=" font-size: 12px;">
					<tr>
						<td class="captioncss" style="text-align: right; width:100px;">员工号</td>
						<td class="hidden-480 addcss">
								<input type="text" id="code" value="${empl.code}" name="empl.code"
									style="height:25px;"  /><!-- disabled="disabled" -->
								<span style="color:red;">*</span>
						</td>
						<td class="captioncss" style="text-align:  right; width:100px;">姓名</td>
						<td class="hidden-480 addcss">
						<input type="text"
							name="empl.name" id="name" style="height:25px;" value="${empl.name}" /><span
							style="color:red;">*</span>
						</td>
						<td class="hidden-480 addcss" rowspan="2"
							style="vertical-align:middle;">
							<input name="wfloginUser" value="true"<c:if test="${empl.wfloginUser}"> checked="checked" </c:if> id="isloginUser" type="checkbox" />登录用户</td>
						<td class="hidden-480 addcss" colspan="1" rowspan="2">
							<center>
							<c:if test="${empl.photo!=null&&empl.photo!=''}">
								<img width="100px" height="150px" id="photoImg" alt=""
									src="${loadPath}${empl.photo}" /><br />
							</c:if>
							<c:if test="${empl.photo==null||empl.photo==''}">
							<img width="100px" height="150px" id="photoImg" alt=""
									src="<%=path%>/images/defaulttopimg.jpg" /><br />
							</c:if>
								<button class="btn btn-small btn-danger"
									style="margin-top: 5px;" id="uploadButton" data-toggle="button" type="button"
									title="上传头像">上传</button>
								<input type="hidden" id="emplphoto"  value="${empl.photo}" name="empl.photo" />
							</center></td>
					</tr>
					<tr>
						<td class="captioncss" style="text-align: right; width:100px;">别名</td>
						<td class="hidden-480 addcss"><input type="text"
							name="empl.nickName" id="nickName" value="${empl.nickName}" style="height:25px;" /><span
							style="color:red;">*</span>
						</td>
						<td class="captioncss" style="text-align: right; width:100px;">年龄</td>
						<td class="hidden-480 addcss"><select id="age" name="empl.age"
							id="age"><option value="-1">请选择</option></select><span style="color:red;">*</span>
						</td>
					</tr>
					<c:if test="${empl.id==null||empl.id!=''}">
					<tr id="loginuserTR" style="display: none;">
						<td class="captioncss" style="text-align: right; width:100px;">登录名<br>
						</td>
						<td class="hidden-480 addcss"><input type="text" 
							name="user.loginName" id="loginName" style="height:25px;" /><span
							style="color:red;">*</span>
						</td>
						<td class="captioncss" style="text-align: right; width:100px;">登录密码</td>
						<td class="hidden-480 addcss"><input type="password" 
							name="user.password" id="password" style="height:25px;" /><span
							style="color:red;">*</span>
						</td>
						<td class="captioncss" style="text-align: right; width:100px;">确认密码</td>
						<td class="hidden-480 addcss"><input type="password"
							name="passwordto" id="passwordto" style="height:25px;" /><span
							style="color:red;">*</span>
						</td>
					</tr>
					</c:if>
					<tr>

						<td class="captioncss" style="text-align: right; width:100px;">性别</td>
						<td class="hidden-480 addcss"><select id="gender" name="empl.gender">
						<option value="-1">请选择</option>
						<option value="男">男</option>
						<option value="女">女</option>
						</select><span style="color:red;">*</span>
						</td>
						<td class="captioncss" style="text-align: right; width:100px;">出身日期</td>
						<td class="hidden-480 addcss"><input type="text"
							name="empl.birthday" value="${empl.birthday }" style="height:25px;" id="birthday"
							readonly="readonly" /> <span style="color:red;">*</span>
						</td>
						<td class="captioncss" style="text-align: right; width:100px;">身份证</td>
						<td class="hidden-480 addcss"><input type="text" value="${empl.identityCard }"
							name="empl.identityCard" id="identityCard" style="height:25px;" /><span
							style="color:red;">*</span>
						</td>
					</tr>
					<tr>
						<td class="captioncss" style="text-align: right; width:100px;">名族</td>
						<td class="hidden-480 addcss"><select id="nation" name="empl.nation">
						<option value="-1">请选择</option>
								<option value="汉族">汉族</option>
								<option value="蒙古族">蒙古族</option>
								<option value="回族">回族</option>
								<option value="藏族">藏族</option>
								<option value="维吾尔族">维吾尔族</option>
								<option value="苗族">苗族</option>
								<option value="彝族">彝族</option>
								<option value="壮族">壮族</option>
								<option value="布依族">布依族</option>
								<option value="朝鲜族">朝鲜族</option>
								<option value="满族">满族</option>
								<option value="侗族">侗族</option>
								<option value="瑶族">瑶族</option>
								<option value="白族">白族</option>
								<option value="土家族">土家族</option>
								<option value="哈尼族">哈尼族</option>
								<option value="哈萨克族">哈萨克族</option>
								<option value="傣族">傣族</option>
								<option value="黎族">黎族</option>
								<option value="傈僳族">傈僳族</option>
								<option value="佤族">佤族</option>
								<option value="畲族">畲族</option>
								<option value="高山族">高山族</option>
								<option value="拉祜族">拉祜族</option>
								<option value="水族">水族</option>
								<option value="东乡族">东乡族</option>
								<option value="纳西族">纳西族</option>
								<option value="景颇族">景颇族</option>
								<option value="柯尔克孜族">柯尔克孜族</option>
								<option value="土族">土族</option>
								<option value="达斡尔族">达斡尔族</option>
								<option value="仫佬族">仫佬族</option>
								<option value="羌族">羌族</option>
								<option value="布朗族">布朗族</option>
								<option value="撒拉族">撒拉族</option>
								<option value="毛南族">毛南族</option>
								<option value="仡佬族">仡佬族</option>
								<option value="锡伯族">锡伯族</option>
								<option value="阿昌族">阿昌族</option>
								<option value="普米族">普米族</option>
								<option value="塔吉克族">塔吉克族</option>
								<option value="怒族">怒族</option>
								<option value="乌孜别克族">乌孜别克族</option>
								<option value="俄罗斯族">俄罗斯族</option>
								<option value="鄂温克族">鄂温克族</option>
								<option value="德昂族">德昂族</option>
								<option value="保安族">保安族</option>
								<option value="裕固族">裕固族</option>
								<option value="京族">京族</option>
								<option value="塔塔尔族">塔塔尔族</option>
								<option value="独龙族">独龙族</option>
								<option value="鄂伦春族">鄂伦春族</option>
								<option value="赫哲族">赫哲族</option>
								<option value="门巴族">门巴族</option>
								<option value="珞巴族">珞巴族</option>
								<option value="基诺族">基诺族</option>
						</select>
						</td>
						<td class="captioncss" style="text-align: right; width:100px;">籍贯</td>
						<td class="hidden-480 addcss"><input type="text" id="origo"
							name="empl.origo" style="height:25px;" value="${empl.origo}" /><span style="color:red;">*</span>
						</td>
						<td class="captioncss" style="text-align: right; width:100px;">学历</td>
						<td class="hidden-480 addcss"><select id="diploma" name="empl.diploma">
						       <option value="-1">请选择</option>
								<option value="xx">小学</option>
								<option value="cg">初中</option>
								<option value="gz">中专/高中</option>
								<option value="dz">大专</option>
								<option value="bk">本科</option>
								<option value="sh">硕士</option>
								<option value="bs">博士</option>
								<option value="bsh">博士后</option>
						</select></td>
					</tr>
					<tr>
						<td class="captioncss" style="text-align: right; width:100px;">家庭住址</td>
						<td class="hidden-480 addcss"><input type="text"
							name="empl.address" value="${empl.address}" id="address" style="height:25px;" /><span
							style="color:red;">*</span>
						</td>
						<td class="captioncss" style="text-align: right; width:100px;">现住址</td>
						<td class="hidden-480 addcss"><input type="text"
							name="empl.currentResidence" value="${empl.currentResidence}" id="currentResidence" style="height:25px;" /><span
							style="color:red;">*</span>
						</td>
						<td class="captioncss" style="text-align: right; width:100px;">联系电话</td>
						<td class="hidden-480 addcss"><input type="text" value="${empl.linkPhone}"
							name="empl.linkPhone" id="linkPhone" style="height:25px;" /><span
							style="color:red;">*</span>
						</td>
					</tr>
					<tr>
						<td class="captioncss" style="text-align: right; width:100px;">网络联系方式</td>
						<td class="hidden-480 addcss"><input type="text"
							name="empl.networkLink" id="networkLink" value="${empl.networkLink}" style="height:25px;" /><span
							style="color:red;">*</span>
						</td>
						<td class="captioncss" style="text-align: right; width:100px;">
							入职日期</td>
						<td class="hidden-480 addcss"><input type="text"
							name="empl.entryDate" value="${empl.entryDate}" style="height:25px;" id="entryDate"
							readonly="readonly" /><span style="color:red;">*</span>
						</td>
						<td class="captioncss" style="text-align: right; width:100px;">所属部门</td>
						<td class="hidden-480 addcss"><select id="department"
							name="empl.department.id">
							<option value="-1">请选择</option>
							<c:forEach items="${depts}"
									var="item">
									<option value="${item.id}">${item.name}</option>
								</c:forEach>
						</select><span style="color:red;">*</span>
						</td>
					</tr>
					<tr>
						<td class="captioncss" style="text-align: right; width:100px;">
							职位名称</td>
						<td class="hidden-480 addcss"><input type="text"
							name="empl.positionName" value="${empl.positionName}" id="positionName" style="height:25px;" /><span
							style="color:red;">*</span>
						</td>
						<td class="captioncss" style="text-align: right; width:100px;">备注</td>
						<td class="hidden-480 addcss" colspan="3"><textarea
								style="width: 450px;" rows="4" name="empl.note" id="note">${empl.note}</textarea>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div id="canvas" class="canvasdiv"></div>
	<div id="ulploddiv" class="ulploddiv">
		<div class="upload_vc" style="">
			<form name="uploadImg" action="<%=path%>/myupload_uploadAjax.action"
				method="post" target="imgframe" enctype="multipart/form-data">
				<p>请选择上传照片</p>
				<ul>
					<li><span>选择照片 :</span> <input style="height: 30px; width: 150px;" name="uploadFile" type="file" /></li>
					<li>
					        <button class="btn btn-small btn-danger pull-left buts1" id="fileUpload" data-toggle="button" type="button">确定</button>
							<button class="btn btn-small btn-danger pull-right buts2"  id="closecanvas" data-toggle="button" type="button">取消</button>
					</li>
				</ul>
			</form>
		</div>
	</div>
	<script type="text/javascript" src="<%=path%>/js/page/employee-page.js"></script>
</body>
</html>
