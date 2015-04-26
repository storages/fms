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

<script type="text/javascript">
	$(function(){
		//上一页面
		$("#goback").click(function(){
			toMain("${pageContext.request.contextPath}/order_findOrderPageList.action");
		});
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
	alert(1);
		var hid = $("#hid").val();
		var hsCode = $("#hsCode").val();
		var hsName = $("#hsCode").val();
		var queryStr = "&hid="+hid+"&hsCode="+hsCode+"&hsName="+parse(hsName);
		// 拼接URL
		var url = "${pageContext.request.contextPath}/order_findOrderItems.action?currIndex=" + pageNum + "&maxIndex="+ pageSize + queryStr;
		// 在本窗口中显示指定URL的页面
		toMain(url);
	}
	
	function parse(str){
		return encodeURI(encodeURI(str));  
	}
		
	});
</script>

<input type="hidden" value="${hid}" id="hid"/>
<div class="page-header position-relative" style="margin: 0px; padding:0px;">
	<h5 style="margin: 0px;">订单>><a href="javascript:void(0);" id="goback">订单列表</a>>>订单详细清单</h5>
</div>
<div class="modal-footer"  style="text-align: left; padding-top: 5px; padding-bottom: 0px;">
		<span class="">商品编码</span><input type="text" id="hsCode" value="${hsCode}" style="height:25px; width:100px;"  class=""/>
		<span class="">商品名称</span><input type="text" id="hsName" value="${hsName}" style="height:25px; width:100px;"  class=""/>
		<input class="btn btn-small btn-danger" data-toggle="button" type="button" value="查询" onclick="gotoPage(1,1);" style="height:25px; border: 2px; width:45px; margin-top:-10px;" />
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
						<th class="center" style="width:91px;">商品编码</th>
						<th class="center" style="width:130px;">商品名称</th>
						<th class="center" style="width:51px;">规格型号</th>
						<th class="center" style="width:71px;">订单数量</th>
						<th class="center" style="width:71px;">计量单位</th>
						<th class="center" style="width:90px;">单价</th>
						<th class="center" style="width:90px;">总价</th>
						<th class="center" style="width:90px;">备注</th>
						<th class="center"  style="width: 164px;">操作</th>
					</tr>
				</thead>
						<tbody id="headmodel">
							<c:forEach var="item" items="${itemList}" varStatus="index" step="1">
								<tr>
									<td class="center" style="width:20px;" ><input type="checkbox" value="${item.id}" name="sid" style="width:20px;"/></td>
									<td class="center" style="width:20px;" >${index.index+1}</td>
									<td class="center" style="width:20px;" >${item.hsCode}</td>
									<td class="center" style="width:20px;" >${item.hsName}</td>
									<td class="center" style="width:20px;" >${item.hsModel}</td>
									<td class="center" style="width:20px;" >${item.qty}</td>
									<td class="center" style="width:20px;" >${item.unit.name}</td>
									<td class="center" style="width:20px;" >${item.price}</td>
									<td class="center" style="width:20px;" >${item.amount}</td>
									<td class="center" style="width:20px;" >${item.note}</td>
									<td class="center" style="width:20px;" >
										<a href="javascript:void(0);" onclick="">修改</a>
										<a href="javascript:void(0);" onclick="delData('${item.id}','OrderItem');">删除</a>
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
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" >Excel导入</button>
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