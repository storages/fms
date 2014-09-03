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
    
    <title>My JSP 'settlement.jsp' starting page</title>
    
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
		<h5>基础资料＞＞结算方式</h5>
	</div>
	<div class="modal-footer" style="text-align: left;">
		<span class="">结算方式名称</span><input type="text" id="search" value="${searchStr}" style="height:25px;" class=""/>
		<input class="btn btn-small btn-danger" data-toggle="button" type="button" value="查询" onclick="search()" style="height:25px; border: 2px; width:45px; margin-top:-10px;"/>
	</div> 
	<div class="span12" style="margin-left: 0px;">
			<!--PAGE CONTENT BEGINS-->

			<div class="row-fluid">
				<div class="span12" style="margin-left: 0px;">
				<table id="sample-table-1" class="table table-striped table-bordered table-hover"  style=" font-size: 12px;">
					<thead>
							<tr align="center">
								<th class="center" style="width:30px;">选择</th>
								<th class="center">序号</th>
								<th class="center">编号</th>
								<th class="center">结算方式名称</th>
								<th class="center">备注</th>
								<th class="center">操作</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach var="settl" items="${settlements}" varStatus="index" step="1">
								<tr>
									<td class="center" style="width:30px;" ><!-- .checkbox input[type="checkbox"] -->
										<input type="checkbox" value="${settl.id}" name="sid" style="width:30px;"/>
									</td>

									<td class="center"><a href="#">${index.index+1}</a>
									</td>
									<td class="center">${settl.code}</td>
									<td class="center">${settl.name}</td>
									<td class="center">${settl.note}　</td>
									<td class="center">
										<a href="javascript:void(0);" onclick="toedit('${settl.id}')">修改</a>｜
										<a href="javascript:void(0);" onclick="delData('${settl.id}','Settlement')">删除</a>
									</td>
								</tr>
							</c:forEach>
					</table>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" onclick="toMain('${pageContext.request.contextPath}/pages/basicdata/addsettlement.jsp');">新增</button>
				<button class="btn btn-small btn-danger pull-left" data-dismiss="modal" onclick="delData('','Settlement')">
					批量删除
				</button>
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" onclick="showImport()">Excel导入</button>
			</div>
	</div>
  </body>
  <script type="text/javascript">
  	function toedit(id){
		var url = "${pageContext.request.contextPath}/settl_findSettlById.action?ids="+id;
		toMain(url);
	}
	function search(){
		var searhStr = $('#search').val();
		var url = "${pageContext.request.contextPath}/settl_findAllSett.action?searhStr="+parse(searhStr);
		toMain(url);
	}
	function showImport(){
	  	var url = "${pageContext.request.contextPath}/pages/import/settlementimport.jsp";
	  	toMain(url);
  	}
  </script>
</html>
