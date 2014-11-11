<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/datepicker/jquery-ui-1.8.16.custom.css"
	type="text/css"></link>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/utils/chinese-of-spell.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/datepicker/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/datepicker/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datepicker/jquery.ui.datepicker-zh-CN.js"></script>
<div class="page-header position-relative" style="margin-bottom: 0px; height:10px;">
	<span style="font-size: 14px; font-weight: bold;">报价单</span>
</div>
<div class="modal-footer" style="text-align: left;">
	<span class="">供应商名称</span><input type="text" id="scmCocName" value="${scmCocName}" style="height:25px;width:100px;" class="" /> 
	<span class="">物料编码</span><input type="text" id="hsCode" value="${hsCode}" style="height:25px;width:100px;" class="" /> 
	<span class="">生效日期以后</span><input type="text" id="effectDate" value="${effectDate}" style="height:25px;width:100px;" class="" /> 
	<input class="btn btn-small btn-danger" data-toggle="button" type="button" value="查询" onclick="gotoPage(1,1)"
		style="height:25px; border: 2px; width:45px; margin-top:-10px;" />
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
								<th class="center">流水号</th>
								<th class="center">供应商编码</th>
								<th class="center">供应商名称</th>
								<th class="center">供应商联系电话</th>
								<th class="center">物料编码</th>
								<th class="center">物料名称</th>
								<th class="center">规格型号</th>
								<th class="center">单价</th>
								<th class="center">生效日期</th>
								<th class="center">备注</th>
								<th class="center">操作</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach var="quotation" items="${quotations}" varStatus="index" step="1">
								<tr>
									<td class="center" style="width:30px;" ><!-- .checkbox input[type="checkbox"] -->
										<input type="checkbox" value="${quotation.id}" name="sid" style="width:30px;"/>
									</td>

									<td class="center"><a href="#">${quotation.serialNo}</a>
									</td>
									<td class="center">${quotation.scmCoc.code}</td>
									<td class="center">${quotation.scmCoc.name}</td>
									<td class="hidden-480 center">${quotation.scmCoc.linkPhone}　</td>
									<td class="hidden-480 center">${quotation.material.hsCode}　</td>
									<td class="hidden-480 center">${quotation.material.hsName}　</td>
									<td class="hidden-480 center">${quotation.material.model}　</td>
									<td class="hidden-480 center">${quotation.price}　</td>
									<td class="hidden-480 center">${quotation.price}　</td>
									<td class="hidden-480 center">${scmcoc.note}　</td>
									<td class="center">
										<a href="javascript:void(0);" onclick="toedit('${scmcoc.id}')">修改</a>｜
										<a href="javascript:void(0);" onclick="delSingleScmcoc('${scmcoc.id}','true')">删除</a>
									</td>
								</tr>
							</c:forEach>
					</table>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" onclick="toedit('');">新增</button>
				<button class="btn btn-small btn-danger pull-left" data-dismiss="modal" onclick="delMoreScmcoc()">
					批量删除
				</button>
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button">Excel导入</button>
				<!-- 分页 -->
				<div class="pagination pull-right no-margin" style="width: 700px;">
					<ul>
						<li class="next"><a>当前页【${currIndex}/${pageNums}】</a>
						</li>
						<li class="next"><a href="javascript: gotoPage( 1, ${pageNums} )">首页</a>
						</li>
						<c:if test="${currIndex!=1}">
							<li class="next"><a href="javascript: gotoPage( ${currIndex - 1}, ${pageNums} )">上一页</a>
							</li>
						</c:if>
						<c:if test="${currIndex!=pageNums}">
							<li class="next"><a href="javascript: gotoPage( ${currIndex + 1}, ${pageNums} )">下一页 </a>
							</li>
						</c:if>
						
						<li class="next"><a href="javascript: gotoPage( ${pageNums}, ${pageNums}) ">尾页 </a><label  class="pull-right no-margin" style="line-height: 30px;">直接到第</label>
						</li>
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
		<!--PAGE CONTENT ENDS-->
	</div>
<script type="text/javascript">
	$(function(){
		$("#effectDate").datepicker({
  			changeYear: true,
			changeMonth: true,
			yearRange: '1900:', 
			dateFormat: 'yy-mm-dd'
  		});
	});
</script>