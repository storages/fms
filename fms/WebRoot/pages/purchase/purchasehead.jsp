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
<style type="text/css">
  input{margin:0px;}
  #sample-table-1 th{padding: 0px;}
</style>

<div class="page-header position-relative" style="margin: 0px; height:10px;line-height: 25px;">
	<h5 style="margin: 0px;">采购单>>采购单列表</h5>
</div>

<div class="modal-footer" style="text-align: left;padding:5px;height: 25px;">
	<span class="">采购单号码</span><input type="text" id="happNo" value="${appNo}" style="height:25px;width:100px;" class="" /> 
	<span class="">采购日期</span><input type="text" id="hbeginDate" value="${purBeginDate}" style="height:25px;width:100px;" class="datebox" /><span>至</span>
	<input type="text" id="hendDate" class="datebox" value="${purEndDate}" style="height:25px;width:100px;"/>
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
						<th class="center" style="width:30px;">选择</th>
						<th class="center" style="width:40px;">流水号</th>
						<th class="center" style="width:91px;">采购单号</th>
						<th class="center" style="width:50px;">状态</th>
						<th class="center" style="width:91px;">申请单号</th>
						<th class="center" style="width:41px;">项数</th>
						<th class="center" style="width:70px;">供应商编码</th>
						<th class="center" style="width:150px;">供应商名称</th>
						<th class="center" style="width:70px;">总金额</th>
						<th class="center" style="width:50px;">打印次数</th>
						<th class="center" style="width:90px;">采购时间</th>
						<th class="center" style="width:50px;">完结否</th>
						<th class="center" style="width:160px;">特别说明</th>
						<th class="center"  style="width: 90px;">操作</th>
					</tr>
				</thead>
						<tbody id="headmodel">
							<c:forEach var="head" items="${headList}" varStatus="index" step="1">
								<tr>
									<td class="center" style="width:20px;" >
										<input type="checkbox" value="${head.id}" name="sid" style="width:20px;"/>
									</td>
										<td class="center" style="width:31px;">${head.serialNo}&nbsp;</td>
										<td class="center" style="width:91px;">${head.purchaseNo}&nbsp;</td>
										<c:if test="${head.purchStatus=='0'}">
											<td class="center" style="width:70px;">未生效&nbsp;</td>
										</c:if>
										<c:if test="${head.purchStatus=='1'}">
											<td class="center" style="width:70px;">生效&nbsp;</td>
										</c:if>
										<td class="center" style="width:91px;">${head.appBillNo}&nbsp;</td>
										<td class="center" style="width:41px;">${head.itemNo}&nbsp;</td>
										<td class="center" style="width:65px;">${head.scmcoc.code}&nbsp;</td>
										<td class="center" style="width:65px;">${head.scmcoc.name}&nbsp;</td>
										<td class="center" style="width:65px;">${head.totalAmount}&nbsp;</td>
										<td class="center" style="width:65px;">${head.printCount}&nbsp;</td>
										<td class="center" style="width:92px;"><fmt:formatDate value="${head.purchDate}" pattern="yyyy-MM-dd"/>&nbsp;</td>
										<c:if test="${head.isComplete}">
											<td class="center" style="width:53px;">已完结&nbsp;</td>
										</c:if>
										<c:if test="${!head.isComplete}">
											<td class="center" style="width:53px;">未完结&nbsp;</td>
										</c:if>
										<td class="center" style="width:65px;">${head.specialNote}&nbsp;</td>
										<td class="center" style="width:164px;">
											<a href="javascript:void(0);" onclick="adddetail('${head.id}');">详细</a>｜
											<c:if test="${head.purchStatus=='0'}"><a href="javascript:void(0);"  onclick="edit(this,'13')"><span style="color: green;">修改</span></a></c:if>
											<c:if test="${head.purchStatus=='1'}">
												<span style="color:gray;" title="<c:if test='${head.purchStatus==1}'>生效状态，不能修改</c:if>">修改</span>
											</c:if>
										</td>
								</tr>
							</c:forEach>
						</tbody>
							
					</table>
				</div>
			</div>
			</div>
			</div>
<div class="modal-footer" style="padding:0px;">
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" id="save" onclick="saveData()">保存</button>
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" id="effect" >生效</button>
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" id="back" >回卷</button>
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" id="print" onclick="printPus()">导出采购单</button>
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


<script type="text/javascript">
	/**
	 * 转到指定页码
	 * @param {Object} pageNum 要转到第几页        currIndex
	 * @param {Object} pageSize 每页显示多少条数据    maxIndex 
	 */
	function gotoPage(pageNum, pageSize) {
		var happNo = $("#happNo").val(); 
		var hbeginappDate = $("#hbeginDate").val();
		var hendappDate = $("#hendDate").val();
		var likeStr = "appBillNo="+happNo+"&purBeginDate="+hbeginappDate+"&purEndDate="+hendappDate+"&currIndex=" + pageNum + "&maxIndex="+ pageSize;
		// 拼接URL
		var url = "${pageContext.request.contextPath}/purchase_findPurchaseHeads.action?"+likeStr;
		// 在本窗口中显示指定URL的页面
		toMain(url);
	}

//去采购单表体
function adddetail(hid){
	var url = "${pageContext.request.contextPath}/purchase_detail.action?hid="+hid;
	toMain(url);
}

$("#hbeginDate").datepicker({
	changeYear: true,
	changeMonth: true,
	yearRange: '1900:', 
	dateFormat: 'yy-mm-dd'
});

$("#hendDate").datepicker({
	changeYear: true,
	changeMonth: true,
	yearRange: '1900:', 
	dateFormat: 'yy-mm-dd'
});
	$(function(){
		$("#hbeginappDate").val($("#d1").val());
		$("#hendappDate").val($("#d2").val());
		
		//保存（也只能做特别说明修改后的保存）
		$('#save').click(function(){
			var jsonstr = getModifyData();
			if(jsonstr==""){
				alert("没有数据保存!");
				return;
			}
			var url = "${pageContext.request.contextPath}/purchase_editheadData.action?jsonstr="+jsonstr;
			$.ajax({
				 type: "POST",
				 url:url,
				 async: false,
				 cache: false,
				 success:function(data){
				 var result=jQuery.parseJSON(data);
				 if(!result.success){
				 		alert(result.msg);
				 	}else{
				 		var id = $('#head').val();
				 		var url = "${pageContext.request.contextPath}/purchase_findPurchaseHeads.action";
				 		toMain(url);
				 	}
				 },error:function(){
				    	alert("程序异常，请重新启动程序！");
				  }
				});
		});
		
		//生效
		$('#effect').click(function(){
			var url = "${pageContext.request.contextPath}/purchase_purchEffect.action";
			var splitStr = "";
			$('input[name="sid"]:checked').each(function(){
				splitStr+=$(this).val()+',';
			  }); 
			if(splitStr==""){
				alert("请选择要生效的内容!");
				return;
			}
			splitStr = splitStr.substring(0, splitStr.length-1);
			$.ajax({
			 	type: "POST",
				url:url,
				data:{ids:splitStr,flag:"true"},
				async: false,
				cache: false,
				success:function(args){
					var result=jQuery.parseJSON(args);
					if(result.success){
						url = "${pageContext.request.contextPath}/purchase_findPurchaseHeads.action";
						toMain(url);
					}else{
						alert(result.msg);
					}
				},error:function(){
				    	alert("程序异常，请重新启动程序！");
				  }
			});
		});
		
		//回卷
		$('#back').click(function(){
			var url = "${pageContext.request.contextPath}/purchase_purchEffect.action";
			var splitStr = "";
			$('input[name="sid"]:checked').each(function(){
				splitStr+=$(this).val()+',';
			  }); 
			if(splitStr==""){
				alert("请选择要回卷的内容!");
				return;
			}
			splitStr = splitStr.substring(0, splitStr.length-1);
			$.ajax({
			 	type: "POST",
				url:url,
				data:{ids:splitStr,flag:"false"},
				async: false,
				cache: false,
				success:function(args){
					var result=jQuery.parseJSON(args);
					if(result.success){
						url = "${pageContext.request.contextPath}/purchase_findPurchaseHeads.action";
						toMain(url);
					}else{
						alert(result.msg);
					}
				},error:function(){
				    	alert("程序异常，请重新启动程序！");
				  }
			});
		});
		
		//导出采购单
		$('#print').click(function(){
			var n = 0;
			var url = "${pageContext.request.contextPath}/purchase_exportPurchase.action";
			var splitStr = "";
			$('input[name="sid"]:checked').each(function(){
				n++;
				splitStr+=$(this).val()+',';
			  }); 
			if(splitStr==""){
				alert("请选择要导出的采购单!");
				return;
			}else if(n>1){
				alert("请选择单笔数据导出采购单!");
				return;
			}
			if(!confirm("导出采购单，系统就认为您确认向供应商下单，这笔采购单信息将不能做任何修改，您确认吗?")){
				return;
			}
			splitStr = splitStr.substring(0, splitStr.length-1);
			$.ajax({
			 	type: "POST",
				url:url,
				data:{ids:splitStr},
				async: false,
				cache: false,
				success:function(args){
					var result=jQuery.parseJSON(args);
					if(result.success){
						alert(result.msg);
						url = "${pageContext.request.contextPath}/purchase_findPurchaseHeads.action";
						toMain(url);
					}else{
						alert(result.msg);
					}
				},error:function(){
				    	alert("程序异常，请重新启动程序！");
				  }
			});
		});
	});
	
	//修改
	function edit(obj,arr){
		showTableEdit(obj,arr);
	}
</script>