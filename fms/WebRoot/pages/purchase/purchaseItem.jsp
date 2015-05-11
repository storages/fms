<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/datepicker/jquery-ui-1.8.16.custom.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/dialog/dialogCss.css" type="text/css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/utils/chinese-of-spell.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datepicker/jquery.ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datepicker/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datepicker/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dialog/dialog.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/public/public.js"></script>

<script type="text/javascript">
var isEdit = "";
$(function(){
	$("#btnQuery").click(function(){
		var hid=$("#hid").val();
		var hsCode = $("#hsCode").val();
		alert(hid);
		if(hid!=null||hid!=""){
			var url = "${pageContext.request.contextPath}/purchase_detail.action?hid="+hid+"&hsCode="+hsCode;
			toMain(url);
		}
	});
	$(".datebox").datepicker({
		changeYear: true,
		changeMonth: true,
		yearRange: '1900:', 
		dateFormat: 'yy-mm-dd'
	});
	
	$(".btnEdit").bind("click",function(){
		var tr = $(this).parent().parent();
		tr.children('td').each(function(){
			var _input = $(this).children('input');
			if(_input.attr("type")!="checkbox" && _input.attr("type")!="undefind"){
			_input.attr("disabled",false);
			_input.css("border","solid 1px red");
			isEdit = "edit";
			}
		});
	});
	
	//修改按钮
	$("#btnEdit").bind("click",function(){
		$("#sample-table-1 :input[name='deliveryDate']").each(function(){
			$(this).attr("disabled",false);
			$(this).css("border","solid 1px red");
			isEdit = "edit";
		 });
	});
	
	//保存按钮
	$("#btnSave").click(function(){
		var hid=$("#hid").val();
		var modifyData =[];
		if(isEdit=="edit"){
			$("#sample-table-1 :input").each(function(){
				modifyData.push($(this).val());
			 });
			modifyData.join(",");
			var url = "${pageContext.request.contextPath}/purchase_savePurchaseItems.action?jsonstr="+modifyData+"&hid="+hid;
			toMain(url);
		}else{
			alert("没有数据保存!");
		}
	});
	
	
});
	
</script>

<div class="page-header position-relative" style="margin: 0px; height:10px;line-height: 25px;">
	<input type="hidden" value="${hid}"  id="hid"/>
	<span style="font-size: 14px; font-weight: bold;margin-left:5px; padding:3px 3px 0px 3px; border:solid 1px gray; border-bottom: 0px;">采购单详细列表</span>
</div>


<div class="modal-footer" style="text-align: left;padding:5px;height: 25px;">
	<span class="">物料编号</span><input type="text" id="hsCode" value="${hsCode}" style="height:25px;width:100px;" class="" /> 
	<input class="btn btn-small btn-danger" data-toggle="button" type="button"  id="btnQuery"  value="查询"  style="height:25px; border: 2px; width:45px; margin-top:-10px;" />
</div>

<div class="row-fluid" >
		<div class="span12">
			<!--PAGE CONTENT BEGINS-->
			<div class="row-fluid">
				<div class="span12" >
					<table id="sample-table-1" class="table table-striped table-bordered table-hover" style=" font-size: 12px;">
						<thead>
					<tr align="center">
						<th class="center" style="width:20px;"><input type="checkbox" title="全选" id="checkAll"/></th>
						<th class="center" style="width:25px;">项号</th>
						<th class="center" style="width:120px;">物料编号</th>
						<th class="center" style="width:120px;">物料名称</th>
						<th class="center" style="width:40px;">规格型号</th>
						<%--<th class="center" style="width:40px;">批次号</th>
						--%><th class="center" style="width:60px;">计量单位</th>
						<th class="center" style="width:40px;">采购数量</th>
						<th class="center" style="width:40px;">单价</th>
						<th class="center" style="width:40px;">金额</th>
						<th class="center" style="width:40px;">采购日期</th>
						<th class="center" style="width:60px;">交货日期</th>
						<th class="center" style="width:60px;">是否下单</th>
						<th class="center"  style="width: 90px;">操作</th>
					</tr>
				</thead>
						<tbody id="puritem">
							<c:forEach var="item" items="${itemList}" varStatus="index" step="1">
								<tr>
									<td class="center" style="width:20px;" ><input type="checkbox" value="${item.id}" name="sid" style="width:20px;"/></td>
									<td class="center" style="width:20px;" >${index.index+1}</td>
									<td class="center" style="width:20px;" >${item.material.hsCode}&nbsp;</td>
									<td class="center" style="width:20px;" >${item.material.hsName}&nbsp;</td>
									<td class="center" style="width:20px;" >${item.material.model}&nbsp;</td><%--
									<td class="center" style="width:20px;" >${item.material.batchNO}&nbsp;</td>
									--%><td class="center" style="width:60px;" >${item.material.unit.name}&nbsp;</td>
									<td class="center" style="width:20px;" >${item.qty}&nbsp;</td>
									<td class="center" style="width:20px;" >${item.price}&nbsp;</td>
									<td class="center" style="width:20px;" >${item.amount}&nbsp;</td>
									<td class="center" style="width:60px;" ><fmt:formatDate value="${item.purchaseDate}" type="date"/>&nbsp;</td>
									<td class="center" style="width:60px;" ><input type="text"  name="deliveryDate" class="datebox" value="<fmt:formatDate value="${item.deliveryDate}" type="date" pattern='yyyy-MM-dd'/>" style="width:73px; margin:0px; border:0px;" disabled="disabled"/></td>
									<td class="center" style="width:20px;" ><input type="checkbox" readonly="readonly" disabled="disabled" value="${item.isBuy }" <c:if test="${item.isBuy }">checked="checked"</c:if>/></td>
									<td class="center" style="width:20px;" >
											<c:if test="${!item.isBuy}"><a href="javascript:void(0);" class="btnEdit"><span style="color: green;">修改</span></a></c:if>
											<c:if test="${item.isBuy}"><span style="color:gray;" title="<c:if test='${item.isBuy}'>已下单，不能修改</c:if>">修改</span></c:if>
											<%--<c:if test="${!item.isBuy}"><a href="javascript:void(0);" onclick="delData('${item.id}','PurchaseItem');"><span style="color: red;">删除</span></a></c:if>
											<c:if test="${item.isBuy}"><span style="color:gray;" title="<c:if test='${item.isBuy}'>已下单，不能删除</c:if>">删除</span></c:if>
									--%></td>
								</tr>
							</c:forEach>
						</tbody>
							
					</table>
				</div>
			</div>
			</div>
			</div>
			<div class="modal-footer" style="padding:0px;">
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" id="btnEdit">修改</button>
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" id="btnSave">保存</button>
				<button class="btn btn-small btn-danger pull-left" data-dismiss="modal"  onclick="delData('','PurchaseItem')">批量删除</button>
				</div>