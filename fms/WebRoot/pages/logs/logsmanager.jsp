

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>

	<title>日志管理</title>

	<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
			<meta http-equiv="expires" content="0"/>
				<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
					<meta http-equiv="description" content="This is my page"/>


						<style type="text/css">
body {
	margin: 0;
	background: url(${pageContext.request.contextPath}/images/01.jpg) repeat
		center;
	font-size: 12px;
}

h1,p {
	margin: 0;
}

ul {
	padding: 0;
	margin: 0;
	list-style: none;
}

/* demo */
.demo {
	width: 800px;
	margin: 30px;
}

#result {
	font-size: 24px;
	font-family: '微软雅黑', '宋体';
	color: #333;
	margin: 0;
	font-weight: normal;
}

#pager ul.pages {
	padding-top: 20px;
	height: 40px;
}

#pager ul.pages li {
	float: left;
	border: 1px solid #ddd;
	background: #fff;
	margin: 0 5px 0 0;
	padding: 5px 8px;
}

#pager ul.pages li:hover {
	border: 1px solid #ec9db1;
	background: #fff2f5;
}

#pager ul.pages li.pgEmpty {
	border: 1px solid #eee;
	color: #999;
}

#pager ul.pages li.pgCurrent {
	border: 1px solid #ec97ab;
	color: #555;
	font-weight: 700;
	background-color: #f8e8ec;
}

.txt {
	padding-top: 5px;
	font-size: 14px;
	font-family: Arial, Times New Roman;
}

.txt b {
	margin-left: 5px;
	color: #f60;
}
</style>
						<script type="text/javascript"
							src="${pageContext.request.contextPath}/js/public/public.js"></script>
						<script type="text/javascript"
							src="<%=path%>/js/utils/jquery.tmpl.min.js">
							
						</script>
						<script type="text/javascript">
							var maxPageCount = ${pagecount};
							var DATA_ROWS = 2;
							var names = '${names}';
						</script>
						<link rel="stylesheet" href="<%=path%>/css/page/canvas.css"
							type="text/css" />
</head>

<body>
	<div class="page-header position-relative" style="margin-bottom: 0px;">
		<h5>日志管理＞＞日志</h5>
	</div>
	<div class="modal-footer" style="text-align: left; display: none;">
		<span class="">员工名称</span><input type="text" id="search"
			value="${names}" style="height:25px;" class="" /> <input
			class="btn btn-small btn-danger" data-toggle="button" type="submit"
			value="查询" id="searchbutton"
			style="height:25px; border: 2px; width:45px; margin-top:-10px;" />
	</div>
	<div class="row-fluid">
		<div class="span12">
			<!--PAGE CONTENT BEGINS-->

			<div class="row-fluid">
				<div class="span12">
					<table id="sample-table-1"
						class="table table-striped table-bordered table-hover"
						style=" font-size: 12px;">
						<thead>
							<tr align="center">
								<th class="center" style="width:30px;">选择</th>
								<th class="center">日志类型</th>
								<th class="hidden-480 center">操作人</th>
								<th class="hidden-phone center">操作内容</th>
								<th class="center">操作</th>
							</tr>
						</thead>

						<tbody id="tbody">
							<c:forEach var="item" items="${logs}" varStatus="index" step="1">
								<tr>
									<td class="center" style="width:30px;">
										<!-- .checkbox input[type="checkbox"] --> <input
										type="checkbox" value="${item.id}" name="sid"
										style="width:30px;" /></td>
									<td class="center">${item.orgType}</td>
									<td class="center">${item.orgUser.userName}</td>
									<c:set var="msg" value="${item.msg}" />
									<c:choose>
										<c:when test="${fn:length(msg) >30}">
												<td class="hidden-480 center"><c:out value="${fn:substring(msg, 0, 30)}......" /></td>
										</c:when>
										<c:otherwise>
											<td class="hidden-480 center"><c:out value="${msg}" /></td>
										</c:otherwise>
									</c:choose>
								
									<td class="center"><a href="javascript:void(0);"
										delete-emp="${item.id}">查看</a></td>
								</tr>
							</c:forEach>
					</table>
				</div>
			</div>

			<%--<div class="modal-footer">
				<a href="javaScript:void;"></a>
				<button id="addEmpl" class="btn btn-small btn-danger pull-left"
					data-toggle="button" type="button">增加员工</button>
				<button class="btn btn-small btn-danger pull-left"
					data-dismiss="modal" id="deleteAll">批量删除</button>
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button">Excel导入</button>
				<!-- 分页 -->
				<div class="pagination pull-right no-margin" style="width: 500px;">

				</div>
				<div class="demo">
					<div id="pager"></div>
				</div>
			</div>

		--%></div>
		<!--PAGE CONTENT ENDS-->
	</div>
	<script type="text/javascript"
		src="<%=path%>/js/page/logsmanager-page.js">
		
	</script>
</body>

<script id="SXrow" type="text/x-jquery-tmpl">
<tr>
<td class="center" style="width:30px;" ><!-- .checkbox input[type="checkbox"] -->
	<input type="checkbox" value="{{= id}}" name="sid" style="width:30px;"/>
</td>
<td class="center">{{= orgType}}</td>
<td class="center">{{= orgUser.userName}}</td>
<td class="hidden-480 center">{{= msg}}　</td>
<td class="center">
	<a href="javascript:void(0);"  delete-emp="{{= id}}">查看</a>
</td>
</tr>
</script>
</html>