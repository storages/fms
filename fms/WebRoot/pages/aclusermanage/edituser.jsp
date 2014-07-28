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
<base href="<%=basePath%>">

<title>My JSP 'edituser.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	.buttLeft{
		float:left;
	}
</style>
</head>

<body>
	<div class="widget-box">
		<div class="widget-header">
			<h5>用户修改</h5>

		</div>
	</div>
	<div style="width:99%; height:auto;margin-left:5px;">
		<div class="widget-box">
			<div class="widget-header">
			<!-- 按钮区 -->
			<p style="width:100%; height: auto;">
				<button class="btn btn-small btn-danger buttLeft" data-toggle="button" type="button">保存</button>
				<button class="btn btn-small btn-danger buttLeft" data-toggle="button" type="button">修改</button>
				<button class="btn btn-small btn-danger buttLeft" data-toggle="button" type="button">取消</button>
			</p>
			</div>

			<div class="widget-body">
				<div class="widget-main no-padding">
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th style="width:60px;">123</th>

								<th><input type="text"/></th>
								<th class="hidden-480">Status</th>
							</tr>
						</thead>

						<tbody>
							<tr>
								<td style="width:60px;">Alex</td>

								<td><a href="#">alex@email.com</a></td>

								<td class="hidden-480"><span class="label label-warning">Pending</span>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		</div>
	<!--/span-->
</body>
</html>
