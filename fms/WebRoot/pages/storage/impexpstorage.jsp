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
    
    <title>My JSP 'impexpstorage.jsp' starting page</title>
    
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
		<h5>物料＞＞入库</h5>
	</div>
	<div class="modal-footer" style="text-align: left;">
		<span class="">入库日期</span><input type="text" id="beginDate" value="" style="height:25px; width:100px;" class=""/><span class="">至</span><input type="text" id="endDate" value="" style="height:25px; width:100px;" class=""/>
		<span class="">供应商名称</span><input type="text" id="scmcocName" value="" style="height:25px; width:100px;" class=""/>
		<span class="">物料名称</span><input type="text" id="search" value="${searchStr}" style="height:25px; width:100px;" class=""/>
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
								<th class="center">进出库类型</th>
								<th class="center">物料名称</th>
								<th class="center">颜色</th>
								<th class="center">计量单位</th>
								<th class="center">供应商名称</th>
								<th class="center">入库数量</th>
								<th class="center">每件包装数量</th>
								<th class="center">件数</th>
								<th class="center">批次号</th>
								<th class="center">入库日期</th>
								<th class="center">备注</th>
								<th class="center">操作</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="info" varStatus="index" step="1" items="${materials}">
							<tr>
								<td class="center" style="width:30px;" ><!-- .checkbox input[type="checkbox"] -->
									<input type="checkbox" value="${info.id}" name="sid" style="width:30px;"/>
								</td>
									<td class="center">${index.index+1}</td>
									<td class="center">${info.hsName}　</td>
									<td class="center">${info.color}　</td>
									<td class="center">${info.unit.name}　</td>
									<td class="center">${info.qty}　</td>
									<td class="center">${info.batchNO}　</td>
									<td class="center">${info.lowerQty}　</td>
									<td class="center">${info.lowerQty}　</td>
									<td class="center">${info.lowerQty}　</td>
									<td class="center">${info.lowerQty}　</td>
									<td class="center">${info.lowerQty}　</td>
									<td class="center">${info.lowerQty}　</td>
									<td class="center">${info.note}　</td>
									<td class="center">
										<a href="javascript:void(0);" onclick="toedit('${info.id}')">修改</a>｜
										<a href="javascript:void(0);" onclick="delSingleMaterial('${info.id}','${info.imgExgFlag}')">删除</a>
									</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" onclick="toMain('${pageContext.request.contextPath}/materInfo_add.action');">新增</button>
				<button class="btn btn-small btn-danger pull-left" data-dismiss="modal" onclick="delMoreMater('I')">
					批量删除
				</button>
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" onclick="showImport()">Excel导入</button>
				<!-- 分页 -->
				<div class="pagination pull-right no-margin" style="width: 500px;">
					<ul>
						<li class="next"><a>当前页【${currIndex}/${pageNums}】</a>
						</li>
						<c:if test="${currIndex!=1}">
						<li class="next"><a href="javascript: gotoPage( 1, ${pageNums} )">首页</a>
						</li>
							<li class="next"><a href="javascript: gotoPage( ${currIndex - 1}, ${pageNums} )">上一页</a>
							</li>
						</c:if>
						<c:if test="${currIndex!=pageNums}">
							<li class="next"><a href="javascript: gotoPage( ${currIndex + 1}, ${pageNums} )">下一页 </a>
							</li>
						<li class="next"><a href="javascript: gotoPage( ${pageNums}, ${pageNums}) ">尾页 </a><label  class="pull-right no-margin" style="line-height: 30px;">直接到第</label>
						</li>
						</c:if>
						
					</ul>
					<select class="pagination pull-right no-margin" style="width:60px;" id="gonum" onchange="gototag('${pageNums}')">
						<c:forEach begin="1" end="${pageNums}" var="pnum">
							<c:if test="${pnum==currIndex}">
								<option selected="selected" value="${pnum}">${pnum}页</option>
							</c:if>
							<c:if test="${pnum!=currIndex}">
								<option  value="${pnum}">${pnum}页</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
	</div>
  </body>
</html>
