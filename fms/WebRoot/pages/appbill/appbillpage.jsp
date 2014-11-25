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
			<table id="sample-table-1" class="table table-striped table-bordered table-hover"  style=" font-size: 12px;margin-bottom: 0px;">
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
						<th class="center" style="width:60px;">已审批数</th>
						<th class="center" style="width:60px;">未审批数</th>
						<th class="center"  style="width: 164px;">操作</th>
					</tr>
				</thead>
			</table>
			<div class="row-fluid" style="height: auto; overflow: auto;">
				<div class="span12"  style="height: 20%;">
					<table id="sample-table-1" class="table table-striped table-bordered table-hover"  style=" font-size: 12px;">
						<tbody>
							<c:forEach var="head" items="${heads}" varStatus="index" step="1">
								<tr onclick="showItem($(this))">
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
										<td class="center" style="width:53px;">${head.approvaledQty}&nbsp;</td>
										<td class="center" style="width:57px;">${head.unApprovalQty}&nbsp;</td>
										<td class="center" style="width:164px;">
											<a href="javascript:void(0);" onclick="edit(this,'10,11,12')">撤销申请</a>｜
											<a href="javascript:void(0);" onclick="delData('${head.id}','AppBillHead')">删除</a>
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
				<button class="btn btn-small btn-danger pull-left" data-dismiss="modal"  onclick="delData('','Quotation')">批量删除</button>
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" onclick="javascript:toMain('${pageContext.request.contextPath}/quotation_toImportPage.action')">Excel导入</button>
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" onclick="">提交申请</button>
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" onclick="">审批</button>
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" onclick="">撤销审批</button>
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
			<hr style="border: 3px solid; margin: 5px 0px 5px 0px;"/>
			
			<!-- 表体数据 -->
			
			
			<div class="modal-footer" style="text-align: left;padding:5px;height: 25px;">
				<span class="">申请单号码</span><input type="text" id="scmCocName" value="${scmCocName}" style="height:25px;width:100px;" class="" /> 
	<span class="">申请日期</span><input type="text" id="begineffectDate" value="${effectDate}" style="height:25px;width:100px;" class="datebox" /><span>至</span>
	<input type="text" id="endeffectDate" class="datebox" value="${effectDate}" style="height:25px;width:100px;"/>
				<input class="btn btn-small btn-danger" data-toggle="button" type="button" value="查询" onclick="gotoPage(1,1)"
					style="height:25px; border: 2px; width:45px; margin-top:-10px;" />
			</div>
			<table id="sample-table-1" class="table table-striped table-bordered table-hover"  style=" font-size: 12px;margin-bottom: 0px;">
				<thead>
					<tr align="center">
						<th class="center" style="width:30px;">选择</th>
						<th class="center" style="width:40px;">流水号</th>
						<th class="center" style="width:71px;">申请单状态</th>
						<th class="center" style="width:65px;">供应商编码</th>
						<th class="center" style="width:115px;">供应商名称</th>
						<th class="center" style="width:55px;">物料编码</th>
						<th class="center" style="width:115px;">物料名称</th>
						<th class="center" style="width:95px;">物料规格型号</th>
						<th class="center" style="width:40px;">单价</th>
						<th class="center" style="width:55px;">申请数量</th>
						<th class="center" style="width:40px;">金额</th>
						<th class="center" style="width:55px;">申请日期</th>
						<th class="center">备注</th>
						<th class="center" style="width:70px;">操作</th>
					</tr>
				</thead>
			</table>
	<div class="row-fluid" style="height:auto; overflow: auto;" >
		<div class="span12">
			<!--PAGE CONTENT BEGINS-->
			<div class="row-fluid" id="itemdiv">
				<div class="span12" style="height: 40%;">
					<table id="sample-table-1" class="table table-striped table-bordered table-hover"  style=" font-size: 12px;margin-bottom: 0px;">
						<tbody>
							<c:forEach var="item" items="${items}" varStatus="index" step="1">
								<tr>
									<td class="center" style="width:30px;" >
										<input type="checkbox" value="${item.id}" name="sid" style="width:30px;"/>
									</td>
										<td class="center">${item.serialNo}</td>
										<td class="center">
											<c:if test="${item.appStatus==0}">未申请</c:if>
											<c:if test="${item.appStatus==1}">待审批</c:if>
											<c:if test="${item.appStatus==2}">审批通过</c:if>
											<c:if test="${item.appStatus==3}">审批不通过</c:if>
										</td>
										<td class="center">${item.scmcoc.code}</td>
										<td class="center">${item.scmcoc.name}&nbsp;</td>
										<td class="center">${item.material.hsCode}&nbsp;</td>
										<td class="center">${item.material.hsName}&nbsp;</td>
										<td class="center">${item.material.model}&nbsp;</td>
										<td class="center">${item.price}&nbsp;</td>
										<td class="hidden-480 center">${item.totalQty}&nbsp;</td>
										<td class="hidden-480 center">${item.amount}&nbsp;</td>
										<td class="center"><fmt:formatDate value="${item.appDate}" pattern="yyyy-MM-dd"/>&nbsp;</td>
										<td class="center">
											<a href="javascript:void(0);" onclick="edit(this,'10,11,12')">修改</a>｜
											<a href="javascript:void(0);" onclick="delData('${quotation.id}','Quotation')">删除</a>
										</td>
								</tr>
							</c:forEach>
					</table>
				</div>
			</div>
		</div>
		<!--PAGE CONTENT ENDS-->
		<div class="dialog" id="dialog" title="选择物料" style="display: none;">
		<%-- <input style="height: 25px;width: 160px;" type="text" name="${hsCode}" id="hsCode"/><input class="btn btn-small btn-danger" style="height: 25px; margin-top: -10px;border-top-width: 1px;" data-toggle="button" type="submit" value="查询"/> --%>
			<p style="height: 28px; margin-bottom: 0px;"><span>供应商</span>
				<select id="scmcoc">
						<option value="chooice">---请选择供应商---</option>
					<c:forEach var="scm" items="${scmcocs}">
						<option value="${scm.id}">${scm.name}</option>
					</c:forEach>
				</select>
			</p>
					<table id="sample-table-2" class="table table-striped table-bordered table-hover"  style=" font-size: 12px;margin-top:2px;">
						<thead>
							<tr align="center">
								<th class="center" style="width:30px;">选择</th>
								<th class="center" style="width:30px;">序号</th>
								<th class="center" style="width:60px;">物料编码</th>
								<th class="center">物料名称</th>
								<th class="center" style="width:60px;">计量单位</th>
								<th class="center" style="width:60px;">规格</th>
								<th class="center" style="width:60px;">物料标记</th>
								<th class="center">备注</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="mater" items="${mlist}" varStatus="index" step="1">
								<tr class="center">
									<td><input type="checkbox" value="${mater.id}" name="materid"/></td>
									<td>${index.index+1}&nbsp;</td>
									<td>${mater.hsCode}&nbsp;</td>
									<td>${mater.hsName}&nbsp;</td>
									<td>${mater.unit.name}&nbsp;</td>
									<td>${mater.model}&nbsp;</td>
									<c:if test="${mater.imgExgFlag=='I'}">
										<td>原料</td>
									</c:if>
									<c:if test="${mater.imgExgFlag=='E'}">
										<td>成品</td>
									</c:if>
									<td>${mater.note}&nbsp;</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
		</div>
	</div>
	 <div class="modal-footer" style="padding:0px;">
		<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" id="add" onclick="showAddBox()">新增</button>
		<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" id="save" onclick="saveData()">保存</button>
		<button class="btn btn-small btn-danger pull-left" data-dismiss="modal"  onclick="delData('','Quotation')">
			批量删除
		</button>
	</div>
	
	
	
	
<script type="text/javascript">
	$(function(){
		var parentHeight = $("#tomain").outerHeight(true);
		var itemdiv = $("#itemdiv").outerHeight(true);
		var thead = $("#sample-table-1").outerHeight(true);
		var headT = $(".row-fluid").outerHeight(true);
		$("#itemdiv").height(parentHeight-thead-headT-140);
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
	
	
	//当表头被选中一行时，自动加载相对应的表体
	function showItem(obj){
		var ids = obj.find('td').find(':checkbox').val(); //获取选中行的checkbox中的value值,也就是获取表头的id
		var url="${pageContext.request.contextPath}/appbill_findItemByHidToJson.action?ids="+ids;
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
		     		
		     	}
		     },error:function(){
		        	alert("程序异常，请重新启动程序！");
		      }
		  	});
	}
	
	//添加申请单表体，弹出对话框
	function showAddBox(){
  			showDialog();
  			var hsCode = $("#hsCode").val();
  			$("#okbutton").click(function(){
            	var scmid = $("#scmcoc").val();
            	if(scmid=="" || scmid=="chooice"){
            		alert("请选择供应商");
            		return;
            	}
				//jquery获取复选框值      
            	var paramstr = "";      
            	$('input[name="materid"]:checked').each(function(){//遍历每一个名字为materid的复选框，其中选中的执行函数      
            		paramstr+=$(this).val()+"/";//将选中的值组装成一个以'/'分割的字符串
            	}); 
            	if(paramstr==""){
            		alert("请选择物料");
            		return;
            	}
            	var url = "${pageContext.request.contextPath}/appbill_findMaterialByIds.action?ids="+paramstr+"&scmid="+scmid;
                toMain(url); 
				closeListenler();
			});
	}

</script>
