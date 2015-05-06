<jsp:include page="/pages/templet/dialog.jsp"></jsp:include>
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
	var dataStatus="";
	$(function(){
		
		$(".numberText").keyup(function () {
        //如果输入非数字，则替换为''，如果输入数字，则在每4位之后添加一个空格分隔
        this.value = this. value.replace(/[^\d.]/g,'');
    	});
		
		//上一页面
		$("#goback").click(function(){
			toMain("${pageContext.request.contextPath}/order_findOrderPageList.action");
		});
	
		//新增按钮
		$("#add").click(function(){
			showDialog('选择商品','dgAddOrderItem.jsp');
			$("#dg_content").load("${pageContext.request.contextPath}/order_findMaterial.action");
		});
		
		//修改按钮
	$("#btnEdit ").click(function(){
		$("#sample-table-1 :input[name='qty']").each(function(){
			$(this).attr("disabled",false);
			$(this).css("border","solid 1px red");
		 });
		$("#sample-table-1 :input[name='price']").each(function(){
			$(this).attr("disabled",false);
			$(this).css("border","solid 1px red");
		 });
		$("#sample-table-1 :input[name='amount']").each(function(){
			$(this).attr("disabled",false);
			$(this).css("border","solid 1px red");
		 });
		$("#sample-table-1 :input[name='note']").each(function(){
			$(this).attr("disabled",false);
			$(this).css("border","solid 1px red");
		 });
		 dataStatus = "edit";
	});
		
		//行后面的修改
		$(".rowEdit").bind("click",function(){
			var tr = $(this).parent().parent();
			tr.children('td').each(function(){
				var _input = $(this).children('input');
				_input.attr("disabled",false);
				_input.css("border","solid 1px red");
			});
			 dataStatus = "edit";
		});
		
		//对话框确定按钮
	$("#btnSure").click(function(){
		var paramstr = "";      
		 $('input[name="mid"]:checked').each(function(){//遍历每一个名字为materid的复选框，其中选中的执行函数      
		 	paramstr+=$(this).val()+"/";//将选中的值组装成一个以'/'分割的字符串
		 }); 
		 if(paramstr==""){
		 	alert("请选择物料");
		 	return;
		 }
		 toMain("${pageContext.request.contextPath}/order_findMaterialByIds.action?ids="+paramstr+"&hid="+$("#hid").val());
		});
		
		//保存按钮
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
		var url = "${pageContext.request.contextPath}/order_saveOrderItem.action";
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
					toMain("${pageContext.request.contextPath}/order_findOrderItems.action?hid="+$("#hid").val());
				}else{
					alert("保存失败!原因："+result.msg);
				}
			}
		});
	});
		
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
		var hid = $("#hid").val();
		var hsCode = $("#hsCode").val();
		var hsName = $("#hsName").val();
		var queryStr = "&hid="+hid+"&hsCode="+hsCode+"&hsName="+parse(hsName);
		// 拼接URL
		var url = "${pageContext.request.contextPath}/order_findOrderItems.action?currIndex=" + pageNum + "&maxIndex="+ pageSize + queryStr;
		// 在本窗口中显示指定URL的页面
		toMain(url);
	}
	
	function parse(str){
		return encodeURI(encodeURI(str));  
	}
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
						<th class="center" style="width:91px;">规格型号</th>
						<th class="center" style="width:71px;">订单数量</th>
						<th class="center" style="width:71px;">计量单位</th>
						<th class="center" style="width:90px;">单价/元</th>
						<th class="center" style="width:90px;">总价/元</th>
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
									<td class="center" style="width:20px;padding:0px;" ><input class="numberText" name="qty" type="text" value="${item.qty}" style="width:90px;margin: 2px 0px;height: 30px;border: 0px;"  disabled="disabled"/></td>
									<td class="center" style="width:20px;" >${item.unit.name}</td>
									<td class="center" style="width:20px;padding:0px;" ><input type="text"  class="numberText" name="price" value="${item.price}"  style="width:100px;margin: 2px 0px;height: 30px;border: 0px;" disabled="disabled"/></td>
									<td class="center" style="width:20px;padding:0px;" ><input type="text"  class="numberText" name="amount" value="${item.amount}" style="width:100px;margin: 2px 0px;height: 30px;border: 0px;"  disabled="disabled"/></td>
									<td class="center" style="width:20px;padding:0px;" ><input type="text" name="note" value="${item.note}" style="width:150px;margin: 2px 0px;height: 30px;border: 0px;" disabled="disabled" /></td>
									<td class="center" style="width:20px;" >
										<a href="javascript:void(0);"  class="rowEdit">修改</a>|
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
				<button class="btn btn-small btn-danger pull-left" data-dismiss="modal"  onclick="delData('','OrderItem')">批量删除</button>
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