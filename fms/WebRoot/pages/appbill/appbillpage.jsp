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
	<span style="font-size: 14px; font-weight: bold;margin-left:5px; padding:3px 3px 0px 3px; border:solid 1px gray; border-bottom: 0px;">申请单</span>
</div>
<input type="hidden" id="d1" value="${beginappDate}"/><input type="hidden" id="d2" value="${endappDate}"/>
<div class="modal-footer" style="text-align: left;padding:5px;height: 25px;">
	<span class="">申请单号码</span><input type="text" id="happNo" value="${appNo}" style="height:25px;width:100px;" class="" /> 
	<span class="">申请日期</span><input type="text" id="hbeginappDate" value="${appDate}" style="height:25px;width:100px;" class="datebox" /><span>至</span>
	<input type="text" id="hendappDate" class="datebox" value="${appDate}" style="height:25px;width:100px;"/>
	申请单状态<select name="appHeadStatus" class="select_css" id="appHeadStatus">
				<option value="-1" <c:if test="${appStatus=='-1'}">selected="selected"</c:if> >全部</option>
				<option value="0" <c:if test="${appStatus=='0'}">selected="selected"</c:if> >未申请</option>
				<option value="1" <c:if test="${appStatus=='1'}">selected="selected"</c:if> >待审批</option>
				<option value="2" <c:if test="${appStatus=='2'}">selected="selected"</c:if> >审批通过</option>
				<option value="3" <c:if test="${appStatus=='3'}">selected="selected"</c:if> >审批不通过</option>
			</select>
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
						<th class="center" style="width:71px;">申请单状态</th>
						<th class="center" style="width:91px;">申请单号码</th>
						<th class="center" style="width:91px;">项数</th>
						<th class="center" style="width:71px;">总数量</th>
						<th class="center" style="width:71px;">总金额</th>
						<th class="center" style="width:90px;">申请日期</th>
						<th class="center" style="width:90px;">申请人</th>
						<th class="center" style="width:60px;">已审批数</th>
						<th class="center" style="width:60px;">未审批数</th>
						<th class="center"  style="width: 164px;">操作</th>
					</tr>
				</thead>
						<tbody id="headmodel">
							<c:forEach var="head" items="${heads}" varStatus="index" step="1">
								<tr">
									<td class="center" style="width:20px;" >
										<input type="checkbox" value="${head.id}" name="sid" style="width:20px;"/>
									</td>
										<td class="center" style="width:31px;">${head.serialNo}&nbsp;</td>
										<c:if test="${head.appStatus==0}">
											<td class="center" style="width:70px;">未申请&nbsp;</td>
										</c:if>
										<c:if test="${head.appStatus==1}">
											<td class="center" style="width:70px;">待审批&nbsp;</td>
										</c:if>
										<c:if test="${head.appStatus==2}">
											<td class="center" style="width:70px;">审批通过&nbsp;</td>
										</c:if>
										<c:if test="${head.appStatus==3}">
											<td class="center" style="width:70px;">审批不通过&nbsp;</td>
										</c:if>
										<c:if test="${head.appStatus==null}">
											<td class="center" style="width:70px;">&nbsp;</td>
										</c:if>
										<td class="center" style="width:91px;">${head.appNo}&nbsp;</td>
										<td class="center" style="width:91px;">${head.itemQty}&nbsp;</td>
										<td class="center" style="width:71px;">${head.totalQty}&nbsp;</td>
										<td class="center" style="width:65px;">${head.totalAmount}&nbsp;</td>
										<td class="center" style="width:92px;"><fmt:formatDate value="${head.appDate}" pattern="yyyy-MM-dd"/>&nbsp;</td>
										<td class="center" style="width:53px;">${head.submitUser.loginName}&nbsp;</td>
										<td class="center" style="width:53px;">${head.approvaledQty}&nbsp;</td>
										<td class="center" style="width:57px;">${head.unApprovalQty}&nbsp;</td>
										<td class="center" style="width:164px;">
											<a href="javascript:void(0);" onclick="adddetail('${head.id}');">详细</a>｜
											<a href="javascript:void(0);" onclick="delData('${head.id}','AppBillHead')"><span style="color: red;">删除</span></a>
										</td>
								</tr>
							</c:forEach>
					</table>
				</div>
			</div>
			</div>
			</div>
			<div class="modal-footer" style="padding:0px;">
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" id="add" style="margin-left: 10px;" onclick="javascript:toMain('${pageContext.request.contextPath}/appbill_addAppBillHead.action')">新增</button>
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" id="save" onclick="saveData()">保存</button>
				<button class="btn btn-small btn-danger pull-left" data-dismiss="modal"  onclick="delData('','AppBillHead')">批量删除</button>
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" onclick="javascript:toMain('${pageContext.request.contextPath}/quotation_toImportPage.action')">Excel导入</button>
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" onclick="submitBill()">提交申请</button>
				<c:if test="${u.userFlag=='S'||u.userFlag=='L'}">
					<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" onclick="">审批</button>
					<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" onclick="">撤销审批</button>
				</c:if>
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
//dialog窗口对象
var win = true;
	$(function(){
		$("#hbeginappDate").val($("#d1").val());
		$("#hendappDate").val($("#d2").val());
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
		var happNo = $("#happNo").val(); 
		var hbeginappDate = $("#hbeginappDate").val();
		var hendappDate = $("#hendappDate").val();
		var happstatus = $("#appHeadStatus").val();
		var likeStr = "appNo="+happNo+"&beginappDate="+hbeginappDate+"&endappDate="+hendappDate+"&appStatus="+ happstatus +"&currIndex=" + pageNum + "&maxIndex="+ pageSize;
		// 拼接URL
		var url = "${pageContext.request.contextPath}/appbill_findAppBillHeads.action?"+likeStr;
		// 在本窗口中显示指定URL的页面
		toMain(url);
	}
	
	function adddetail(id){
			var do_height = getTotalHeight();
			var do_width = getTotalWidth();
			var url = "${pageContext.request.contextPath}/appbill_findItemByHid.action?ids="+id;
			if(win!=false && win!=undefined){
				if(getBrowserType()=="chrome"){
					win = window.open ( url , "_blank" ,"dialogHeight="+ do_height-200 +"px;dialogWidth="+ do_width-100 +"px;scrollbars=no;location=no;resizable=no;channelmode=1;top="+ (do_height-(do_height-200))/2 +";left="+(do_width-(do_width-100))/2 ) ;
				}else{
					win = window.showModalDialog ( url , "_blank" ,"dialogHeight=600;dialogWidth=1300;scrollbars=no;location=no;resizable=no;channelmode=1;top="+ (do_height-(do_height-200))/2 +";left="+(do_width-(do_width-100))/2 );
				}
				win = false;
			}else{
				var url = "${pageContext.request.contextPath}/appbill_findAppBillHeads.action";
				toMain(url);
			}
		
		
	}
	
	//提交申请
	function submitBill(){
		var ids = "";
		$('input[name="sid"]:checked').each(function(){//遍历每一个名字为sid的复选框      
    		ids+=$(this).val()+",";//将选中的值组装成一个以','分割的字符串
    	});
		if(ids==""){
			alert("请勾选要申请的内容");
			return;
		}
		var url = "${pageContext.request.contextPath}/appbill_submitApp.action?ids="+ids;
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
			     		url = "${pageContext.request.contextPath}/appbill_findAppBillHeads.action";
			     		toMain(url);
			     	}
			     },error:function(){
			        	alert("程序异常，请重新启动程序！");
			      }
		});
	}
	
	//审批
	function verifyBill(){
		var ids = "";
		$('input[name="sid"]:checked').each(function(){//遍历每一个名字为sid的复选框      
    		ids+=$(this).val()+",";//将选中的值组装成一个以','分割的字符串
    	});
		if(ids==""){
			alert("请勾选要审批的内容");
			return;
		}
		var url = "${pageContext.request.contextPath}/appbill_*.action?ids="+ids;
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
			     		url = "${pageContext.request.contextPath}/appbill_findAppBillHeads.action";
			     		toMain(url);
			     	}
			     },error:function(){
			        	alert("程序异常，请重新启动程序！");
			      }
		});
	}
</script>
