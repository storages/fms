<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico">
<link rel="Bookmark" href="${pageContext.request.contextPath}/images/favicon.ico">
<base href="<%=basePath%>">

<title>联硕塑料制品公司-系统管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<link
	href="${pageContext.request.contextPath}/css/public/bootstrap.min.css"
	rel="stylesheet"/>
<link
	href="${pageContext.request.contextPath}/css/public/bootstrap-responsive.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/css/public/font-awesome.min.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/public/public_1.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/public/ace.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/css/public/ace-responsive.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/css/public/ace-skins.min.css"
	rel="stylesheet" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/jquerytree/jquery.treeview.css"
	type="text/css"></link>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/jquerytree/tree.css"
	type="text/css"></link>
 
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.8.2.min.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquerytree/jquery.cookie.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquerytree/jquery.treeview.js"></script>
	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/utils/jquery.pager.js"></script>
<script type="text/javascript">
    var   Global='${pageContext.request.contextPath}';
	$(function() {
		$("#browser").treeview();
		$("#browser").bind(
				"contextmenu",
				function(event) {
					if ($(event.target).is("li")
							|| $(event.target).parents("li").length) {
						$("#browser").treeview(
								{
									remove : $(event.target).parents("li")
											.filter(":first")
								});
						return false;
					}
				});
	});
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/menujump/menuBindUrl.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/controlUI/editTable.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/public/json2.js"></script>
</head>

<body oncontextmenu="return false">
	<jsp:include page="/pages/content.jsp"></jsp:include>
	<!-- WAIT FLASH ACTIVE -->
		<div id="waitdiv" class="waitcss" style="display: none;">
			<img src="${pageContext.request.contextPath}/imges/loading.gif" class="waitgif" id="waitgif"/>
		</div>
</body>
</html>