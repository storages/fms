<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/datepicker/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datepicker/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dialog/dialog.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/public/public.js"></script>

<div class="page-header position-relative" style="margin: 0px; height:10px;line-height: 25px;">
	<span style="font-size: 14px; font-weight: bold;margin-left:5px; padding:3px 3px 0px 3px; border:solid 1px gray; border-bottom: 0px;">采购单详细列表</span>
</div>


<div class="modal-footer" style="text-align: left;padding:5px;height: 25px;">
	<span class="">物料编码</span><input type="text" id="hsCode" value="${hsCode}" style="height:25px;width:100px;" class="" /> 
	<input class="btn btn-small btn-danger" data-toggle="button" type="button" value="查询" onclick="gotoPage(1,1)"
		style="height:25px; border: 2px; width:45px; margin-top:-10px;" />
</div>

<div class="row-fluid" >
		<div class="span12">
			<!--PAGE CONTENT BEGINS-->
			<div class="row-fluid">
				<div class="span12" >
					<table id="sample-table-1" class="table table-striped table-bordered table-hover" style=" font-size: 12px;">
						<thead>
					<tr align="center">
						<th class="center" style="width:30px;"><input type="checkbox" title="全选" id="checkAll"/></th>
						<th class="center" style="width:40px;">项号</th>
						<th class="center" style="width:40px;">物料编号</th>
						<th class="center" style="width:40px;">物料名称</th>
						<th class="center" style="width:40px;">规格型号</th>
						<th class="center" style="width:40px;">批次号</th>
						<th class="center" style="width:40px;">计量单位</th>
						<th class="center" style="width:40px;">采购数量</th>
						<th class="center" style="width:40px;">单价</th>
						<th class="center" style="width:40px;">金额</th>
						<th class="center" style="width:40px;">采购日期</th>
						<th class="center" style="width:40px;">交货日期</th>
						<th class="center" style="width:40px;">是否下单</th>
						<th class="center"  style="width: 90px;">操作</th>
					</tr>
				</thead>
						<tbody id="puritem">
							<c:forEach var="head" items="${itemList}" varStatus="index" step="1">
								<tr>
									<td class="center" style="width:20px;" ><input type="checkbox" value="${item.id}" name="sid" style="width:20px;"/></td>
									<td class="center" style="width:20px;" >${index.index}</td>
									<td class="center" style="width:20px;" >${item.material.hsCode}&nbsp;</td>
									<td class="center" style="width:20px;" >${item.material.hsName}&nbsp;</td>
									<td class="center" style="width:20px;" >${item.material.model}&nbsp;</td>
									<td class="center" style="width:20px;" >${item.material.batchNO}&nbsp;</td>
									<td class="center" style="width:20px;" >${tem.material.unit.name}&nbsp;</td>
									<td class="center" style="width:20px;" >${item.qty}&nbsp;</td>
									<td class="center" style="width:20px;" >${item.price}&nbsp;</td>
									<td class="center" style="width:20px;" >${item.amount}&nbsp;</td>
									<td class="center" style="width:20px;" >${item.purchaseDate}&nbsp;</td>
									<td class="center" style="width:20px;" ><input type="checkbox"   value="${item.isBuy }" <c:if test="${item.isBuy }">checked="checked"</c:if>/></td>
									<td class="center" style="width:20px;" >
											<c:if test="${!item.isBuy}"><a href="javascript:void(0);" onclick="adddetail('${item.id}');"><span style="color: red;">删除</span></a>｜</c:if>
											<c:if test="${item.isBuy}"><span style="color:gray;" title="<c:if test='${tem.isBuy}'>已下单，不能删除</c:if>">删除</span>｜</c:if>
											<c:if test="${!item.isBuy}"><a href="javascript:void(0);"  onclick="edit(this,'13')"><span style="color: green;">修改</span></a></c:if>
											<c:if test="${item.isBuy}"><span style="color:gray;" title="<c:if test='${head.appStatus==1}'>已下单，不能修改</c:if>">修改</span></c:if>
									</td>
								</tr>
							</c:forEach>
						</tbody>
							
					</table>
				</div>
			</div>
			</div>
			</div>