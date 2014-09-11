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
    
    <title>My JSP 'materialtype.jsp' starting page</title>
    
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
     <div class="page-header position-relative" style="margin-bottom: 0px;">
		<h5>物料＞＞物料类别</h5>
	</div>
	<div class="modal-footer" style="text-align: left;">
		<span class="">类别名称</span><input type="text" id="search" value="${searchStr}" style="height:25px;" class=""/>
		<input class="btn btn-small btn-danger" data-toggle="button" type="button" value="查询" onclick="search()" style="height:25px; border: 2px; width:45px; margin-top:-10px;"/>
	</div>
	<div class="row-fluid" >
		<div class="span12">
			<!--PAGE CONTENT BEGINS-->

			<div class="row-fluid">
				<div class="span12">
					<table id="sample-table-1" class="table table-striped table-bordered table-hover"  style=" font-size: 12px;">
						<thead>
							<tr>
								<th class="center" style="width:30px;">选择</th>
								<th class="center">编号</th>
								<th class="center">类别名称</th>
								<th class="center">备注</th>
								<th class="center">操作</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="materialType" varStatus="index" step="1" items="${typeList}">
							<tr>
								<td class="center" style="width:30px;" ><!-- .checkbox input[type="checkbox"] -->
									<input type="checkbox" value="${materialType.id}" name="sid" style="width:30px;"/>
								</td>
									<td class="center">${index.index+1}</td>
									<td class="center">${materialType.typeName}　</td>
									<td class="center">${materialType.note}　</td>
									<td class="center">
										<a href="javascript:void(0);" onclick="toedit('${materialType.id}')">修改</a>｜
										<a href="javascript:void(0);" onclick="delData('${materialType.id}','MaterialType')">删除</a>
									</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" onclick="toMain('${pageContext.request.contextPath}/pages/material/addmaterialtype.jsp');">新增</button>
				<button class="btn btn-small btn-danger pull-left" data-dismiss="modal" onclick="delData('','MaterialType')">
					批量删除
				</button>
			</div>
		</div>
	</div>
	<!--PAGE CONTENT ENDS-->
  </body>
  <script type="text/javascript">
  	//修改
  	function toedit(id){
		var url = "${pageContext.request.contextPath}/mater_findTypeById.action?ids="+id;
		toMain(url);
	}
	function search(){
		var searhStr = $('#search').val();
		var url = "${pageContext.request.contextPath}/mater_findAllMaterialType.action?searhStr="+parse(searhStr);
		toMain(url);
	}
  </script>
</html>
