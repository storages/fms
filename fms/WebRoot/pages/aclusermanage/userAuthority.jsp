<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/public/public.js"></script></head>

	<div class="page-header position-relative" style="margin-bottom: 0px;">
		<h5>用户管理＞＞用户权限</h5>
	</div>
	<!--/.page-header-->

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
								<th class="center">登录名</th>
								<th class="hidden-480 center">用户名</th>
								<th class="hidden-phone center">是否管理员</th>
								<th class="hidden-480 center">账户状态</th>
								<th class="hidden-480 center">最后登录时间</th>
								<th class="center">操作</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach var="user" items="${users}" varStatus="index" step="1">
								<c:if test="${u.userFlag!=user.userFlag}">
									<tr>
										<td class="center" style="width:30px;" ><!-- .checkbox input[type="checkbox"] -->
											<input type="checkbox" value="${user.id}" name="uid" style="width:30px;"/>
										</td>
	
										<td class="center"><a href="#">${index.index+1}</a>
										</td>
										<td class="center">${user.loginName}</td>
										<td class="hidden-480 center">${user.userName}</td>
										<c:if test="${'L'==user.userFlag}">
											<td class="center">超级用户</td>
										</c:if>
										<c:if test="${'S'==user.userFlag}">
											<td class="center">管理员</td>
										</c:if>
										<c:if test="${'P'==user.userFlag}">
											<td class="center">普通用户</td>
										</c:if>
										<c:if test="${user.isForbid}">
											<td class="hidden-480 center">
												已禁用
											</td>
										</c:if>
										<c:if test="${!user.isForbid}">
											<td class="hidden-480 center">
												正常
											</td>
										</c:if>
										<c:if test="${user.lastlogin==null}">
											<td class="hidden-480 center">从未登录</td>
										</c:if>
										<c:if test="${user.lastlogin!=null}">
											<td class="hidden-480 center">${user.lastlogin}</td>
										</c:if>
										<td class="center">
											<a href="javascript:void(0);" onclick="">修改权限</a>｜<a href="javascript:void(0);" onclick="toMain('${pageContext.request.contextPath}/pages/aclusermanage/edituser.jsp')">修改信息</a>｜
											<a href="javascript:void(0);" onclick="delData('${user.id}','AclUser')">删除</a>|
											<c:if test="${!user.isForbid}">
												<a href="javascript:void(0);" onclick="toMain('${pageContext.request.contextPath}/users_stopOrOpenUser.action?userForbid=true&ids=${user.id}')">禁用</a>
											</c:if>
											<c:if test="${user.isForbid}">
												<a href="javascript:void(0);" onclick="toMain('${pageContext.request.contextPath}/users_stopOrOpenUser.action?userForbid=false&ids=${user.id}')">启用</a>
											</c:if>
										</td>
									</tr>
								</c:if>
							</c:forEach>
					</table>
				</div>
			</div>

			<div class="modal-footer">
				<button class="btn btn-small btn-danger pull-left" data-dismiss="modal" onclick="delData('','AclUser')">
					批量删除
				</button>

			</div>
		</div>
		<!--PAGE CONTENT ENDS-->
	</div>
