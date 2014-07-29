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
    
    <title>My JSP 'scmcoc.jsp' starting page</title>
    
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
    <div class="page-header position-relative" style="margin-bottom: 0px;">
		<h5>基础资料＞＞供应商管理</h5>
	</div>
	<div class="row-fluid" >
		<div class="span12">
			<!--PAGE CONTENT BEGINS-->

			<div class="row-fluid">
				<div class="span12">
					<table id="sample-table-1" class="table table-striped table-bordered table-hover"  style=" font-size: 12px;">
						<thead>
							<tr align="center">
								<th class="center" style="width:30px;">选择</th>
								<th class="center">编号</th>
								<th class="center">供应商名称</th>
								<th class="hidden-480 center">供应商联系电话</th>
								<th class="hidden-phone center">供应商网络联系方式</th>
								<th class="hidden-480 center">供应商联系人</th>
								<th class="hidden-480 center">供应商地址</th>
								<th class="hidden-480 center">约定结算日期</th>
								<th class="hidden-480 center">备注</th>
								<th class="center">操作</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach var="scmcoc" items="${scmcocs}" varStatus="index" step="1">
								<tr>
									<td class="center" style="width:30px;" ><!-- .checkbox input[type="checkbox"] -->
										<input type="checkbox" value="${scmcoc.id}" name="sid" style="width:30px;"/>
									</td>

									<td class="center"><a href="#">${index.index+1}</a>
									</td>
									<td class="center">${scmcoc.code}</td>
									<td class="center">${scmcoc.name}</td>
									<td class="hidden-480 center">${scmcoc.linkPhone}</td>
									<td class="hidden-480 center">${scmcoc.networkLink}</td>
									<td class="hidden-480 center">${scmcoc.linkMan}</td>
									<td class="hidden-480 center">${scmcoc.address}</td>
									<td class="hidden-480 center">${scmcoc.endDate}</td>
									<td class="hidden-480 center">${scmcoc.note}</td>
									<td class="center">
										<a href="javascript:void(0);" onclick="toMain('${pageContext.request.contextPath}/pages/')">修改</a>｜
										<a href="javascript:void(0);" onclick="delSingleObject('${user.id}')">删除</a>
									</td>
								</tr>
							</c:forEach>
					</table>
				</div>
			</div>

			<div class="modal-footer">
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" onclick="toMain('${pageContext.request.contextPath}/pages/scmcoc/addscmcoc.jsp');">新增</button>
				<button class="btn btn-small btn-danger pull-left" data-dismiss="modal" onclick="delList()">
					批量删除
				</button>
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button">Excel导入</button>
				<!-- 分页 -->
				<!-- <div class="pagination pull-right no-margin">
					<ul>
						<li class="prev disabled"><a href="#"> <i
								class="icon-double-angle-left"></i> </a>
						</li>

						<li class="active"><a href="#">1</a>
						</li>

						<li><a href="#">2</a>
						</li>

						<li><a href="#">3</a>
						</li>

						<li class="next"><a href="#"> <i
							class="icon-double-angle-right"></i> </a>
						</li>
					</ul>
				</div> -->
			</div>
		</div>
		<!--PAGE CONTENT ENDS-->
	</div>
  </body>
</html>
