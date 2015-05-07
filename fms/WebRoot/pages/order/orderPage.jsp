<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/public/public.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/datepicker/jquery-ui-1.8.16.custom.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/dialog/dialogCss.css" type="text/css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/utils/chinese-of-spell.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datepicker/jquery.ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datepicker/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datepicker/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dialog/dialog.js"></script>


<script type="text/javascript">
var dataStatus="";
$(function(){
	$("input[name='qdate']").datepicker({
		changeYear: true,
		changeMonth: true,
		yearRange: '1900:', 
		dateFormat: 'yy-mm-dd'
	});
	
	
	//新增订单表头按钮
	$("#add").click(function(){
		var url = "${pageContext.request.contextPath}/order_addOrder.action";
		toMain(url);
	});
	
	//修改订单表头按钮
	$("#btnEdit").click(function(){
		$("#sample-table-1 :input[name='scmName']").each(function(){
			$(this).attr("disabled",false);
			$(this).css("border","solid 1px red");
		 });
		$("#sample-table-1 :input[name='qdate']").each(function(){
			$(this).attr("disabled",false);
			$(this).css("border","solid 1px red");
		 });
		$("#sample-table-1 :input[name='sale']").each(function(){
			$(this).attr("disabled",false);
			$(this).css("border","solid 1px red");
		 });
		 dataStatus = "edit";
	});
	
	//导入订单按钮
	$('#btnImport').click(function(){
			toMain("${pageContext.request.contextPath}/order_showImport.action");
	});
	
	//保存订单表头按钮
	$("#btnSave").click(function(){
	if(dataStatus!="edit"){
			alert("没有要保存的数据!");
			return;
		}
		var modifyData = [];
		$("#sample-table-1 tr td").children().each(function(){
			var obj = this.tagName;
			if(obj=="INPUT" || obj=="SELECT"){
				modifyData.push($(this).val());
			}
		});
		var url = "${pageContext.request.contextPath}/order_saveOrder.action";
		var jsonstr = JSON.stringify(modifyData);
		$.ajax({
			type: "POST",
			url:url,
			data:{editData:jsonstr},
			async: false,
			cache: false,
			success:function(args){
				var result=jQuery.parseJSON(args);
				if(result.success){
					toMain("${pageContext.request.contextPath}/order_findOrderPageList.action");
				}else{
					alert(result.msg);
				}
			}
		});
	});
	
});
	
	//跳转到详细清单页面
	function toDetail(hid){
		var url = "${pageContext.request.contextPath}/order_findOrderItems.action?hid="+hid;
		toMain(url);
	}


function gototag(pageSize){
	var n = $("#gonum option:selected").val();
	gotoPage(n, pageSize);
}
		
		/**
	 * 转到指定页码
	 * @param {Object} pageNum 要转到第几页        currIndex
	 * @param {Object} pageSize 每页显示多少条数据    maxIndex 
	 */
	function gotoPage(pageNum, pageSize) {
		var orderNo = $("#orderNo").val();
		var hBeginPlaceOrderDate = $("#hBeginPlaceOrderDate").val();
		var hEndPlaceOrderDate = $("#hEndPlaceOrderDate").val();
		var hBeginDeliveryDate = $("#hBeginDeliveryDate").val();
		var hEndDeliveryDate = $("#hEndDeliveryDate").val();
		var scmCocName = $("#scmCocName").val();
		var salesman = $("#salesman option:selected").text();
		var likeStr = "&orderNo="+orderNo+"&beginPlaceOrderDate="+hBeginPlaceOrderDate+"&endPlaceOrderDate="+hEndPlaceOrderDate+"&beginDeliveryDate="+hBeginDeliveryDate+"&endDeliveryDate="+hEndDeliveryDate+"&scmCocName="+parse(scmCocName)+"&salesman="+parse(salesman);
		// 拼接URL
		var url = "${pageContext.request.contextPath}/order_findOrderPageList.action?currIndex=" + pageNum + "&maxIndex="+ pageSize + parse(likeStr);
		// 在本窗口中显示指定URL的页面
		toMain(url);
	}
	
	function parse(str){
		return encodeURI(str);  
	}
</script>



<div class="page-header position-relative" style="margin: 0px;padding:0px;">
	<h5 style="margin: 0px;">订单＞＞订单列表</h5>
</div>
<div class="modal-footer"  style="text-align: left; padding-top: 5px; padding-bottom: 0px;">
		<span class="">订单号</span><input type="text" id="orderNo" value="${orderNo}" style="height:25px; width:100px;"  class=""/>
		<span class="">客户名称</span><input type="text" id="scmCocName" value="${scmCocName}" style="height:25px;width:100px;" class=""/>
		<span class="">下单日期</span><input type="text" id="hBeginPlaceOrderDate" value="${hBeginPlaceOrderDate}"  name="qdate"  style="height:25px;width:100px;" class="datebox" /><span>至</span><input type="text" id="hEndPlaceOrderDate" class="datebox"  name="qdate"  value="${hEndPlaceOrderDate}" style="height:25px;width:100px;"/>
		<span class="">交货日期</span><input type="text" id="hBeginDeliveryDate"  name="qdate"  value="${hBeginDeliveryDate}" style="height:25px;width:100px;" class="datebox" /><span>至</span><input type="text" id="hEndDeliveryDate" class="datebox"  name="qdate"  value="${hEndDeliveryDate}" style="height:25px;width:100px;"/>
		<span class="">业务员</span>
		<select id="salesman" style="width:100px;">
			<c:forEach var="sales" items="${salesmans}">
				<option value="${sales.name}"  <c:if test="${salesman== sales.name}"> selected="selected" </c:if>>${sales.name}</option>
			</c:forEach>
		</select>
		<input class="btn btn-small btn-danger" data-toggle="button" type="button" value="查询" onclick="gotoPage(1,1)" style="height:25px; border: 2px; width:45px; margin-top:-10px;" />
	</div>
	
	<div class="row-fluid" >
		<div class="span12">
			<!--PAGE CONTENT BEGINS-->
			<div class="row-fluid">
				<div class="span12" >
					<table id="sample-table-1" class="table table-striped table-bordered table-hover" style=" font-size: 12px;">
						<thead>
					<tr align="center">
						<th class="center" style="width:30px;"><input id="checkAll" type="checkbox"/></th>
						<th class="center" style="width:40px;padding:0px;margin:0px;">流水号</th>
						<th class="center" style="width:91px;">订单号</th>
						<th class="center" style="width:130px;">客户名称</th>
						<th class="center" style="width:51px;">项数</th>
						<th class="center" style="width:71px;">订单总数</th>
						<th class="center" style="width:71px;">总金额</th>
						<th class="center" style="width:90px;">下单日期</th>
						<th class="center" style="width:90px;">交货日期</th>
						<th class="center" style="width:90px;">业务员</th>
						<th class="center" style="width:60px;">是否结案</th>
						<th class="center"  style="width: 164px;">操作</th>
					</tr>
				</thead>
						<tbody id="headmodel">
							<c:forEach var="order" items="${orderList}" varStatus="index" step="1">
								<tr>
									<td class="center" style="width:20px;" ><input type="checkbox" value="${order.id}" name="sid" style="width:20px;"/></td>
									<td class="center" style="width:31px;">${order.serialNo}&nbsp;</td>
									<td class="center" style="width:31px;">${order.orderNo}&nbsp;</td>
									<td class="center" style="width:31px; padding:0px;">
										<select name="scmName"  disabled="disabled" style="width:130px;margin: 0px;border: 0px; padding:0px; font-size: 12px;">
											<c:forEach var="scm" items="${scmList}">
												<option  value="${scm.id}" <c:if test="${order.scmcoc.name==scm.name}">selected="selected"</c:if>>${scm.name}</option>
											</c:forEach>
										</select>
									</td>
									<td class="center" style="width:31px;">${order.itemQty}&nbsp;</td>
									<td class="center" style="width:31px;">${order.totalQty}&nbsp;</td>
									<td class="center" style="width:31px;">${order.totalAmount}&nbsp;</td>
									<td class="center" style="width:31px;padding:0px;"><input type="text" name="qdate" value="${order.placeOrderDate}" style="width:90px;margin: 0px;height: 36px;border: 0px;" disabled="disabled"/>&nbsp;</td>
									<td class="center" style="width:31px;padding:0px;"><input type="text"  name="qdate"  value="${order.deliveryDate}" style="width:90px;margin: 0px;height: 36px;border: 0px;" disabled="disabled"/>&nbsp;</td>
									<td class="center" style="width:31px;padding:0px;"><input type="text"  name="sale" value="${order.salesman}" style="width:90px;margin: 0px;height: 36px;border: 0px;" disabled="disabled"/>&nbsp;</td>
									<td class="center" style="width:31px;">
										<c:if test="${order.isFinish}">
											<input type="checkbox"  checked="checked"  disabled="disabled"/>
										</c:if>
										<c:if test="${!order.isFinish}">
											<input type="checkbox" readonly="readonly" disabled="disabled"/>
										</c:if>
									</td>
									<td  class="center" style="width:31px;">
										<a href="javascript:void(0);" onclick="toDetail('${order.id}')">详细</a>｜
										<a href="javascript:void(0);" onclick="delData('${order.id}','OrderHead');">删除</a>
									</td>
								</tr>
							</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal-footer" style="padding:0px;">
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" id="add" style="margin-left: 10px;">新增</button>
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" id="btnEdit">修改</button>
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" id="btnSave">保存</button>
				<button class="btn btn-small btn-danger pull-left" data-dismiss="modal"  onclick="delData('','OrderHead')">批量删除</button>
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button"   id="btnImport">Excel导入</button>
				<!-- 分页 -->
				<div class="pagination pull-right no-margin" style="width: 500px;">
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
	