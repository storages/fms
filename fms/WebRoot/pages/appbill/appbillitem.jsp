<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link
	href="${pageContext.request.contextPath}/css/public/bootstrap.min.css"
	rel="stylesheet"/>
<link
	href="${pageContext.request.contextPath}/css/public/bootstrap-responsive.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/css/public/font-awesome.min.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/public/public_1.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/public/ace.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/css/public/ace-responsive.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/css/public/ace-skins.min.css"
	rel="stylesheet" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/jquerytree/jquery.treeview.css"
	type="text/css"></link>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/jquerytree/tree.css"
	type="text/css"></link>
 
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.8.2.min.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquerytree/jquery.cookie.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquerytree/jquery.treeview.js"></script>
	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/utils/jquery.pager.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/datepicker/jquery-ui-1.8.16.custom.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/dialog/dialogCss.css" type="text/css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/utils/chinese-of-spell.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datepicker/jquery.ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datepicker/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datepicker/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dialog/dialog.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/public/public.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/controlUI/editTable.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/public/json2.js"></script>
<style type="text/css">
  input{margin:0px;}
  #sample-table-1 th{padding: 0px;}
  #sample-table-2 td{padding:3px 0px;text-align: center;}
</style>
<body>
<input type="hidden" value="${his}" id="head"/>
<input type="hidden" value="${orderNo}" id="orderNo"/>
<input type="hidden" value="${isFromBom}" id="isFromBom"/>
<div class="page-header position-relative" style="margin: 0px; height:10px;line-height: 25px;">
	<%--<span style="font-size: 14px; font-weight: bold;margin-left:5px; padding:3px 3px 0px 3px; border:solid 1px gray; border-bottom: 0px;">申请单详细列表</span>--%>
	<h5 style="margin: 0px;">申请单>><a href="javascript:void(0);"  id="goback">申请单列表</a>>>申请单详细清单</h5>
</div>
<div class="modal-footer" style="padding:0px;">
				<c:if test="${u.userFlag=='P'}"><button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" id="add" style="margin-left: 10px;">新增</button></c:if>
				<c:if test="${u.userFlag=='P'}"><button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" id="save" onclick="saveData()">保存</button></c:if>
				<c:if test="${u.userFlag!='P'}"><button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" id="btn_verify" >审批</button></c:if>
				<c:if test="${u.userFlag=='P'}"><button class="btn btn-small btn-danger pull-left" data-dismiss="modal"  onclick="delData('','AppBillItem')">批量删除</button></c:if>
				<button class="btn btn-small btn-danger pull-left" data-dismiss="modal"  onclick="closeform();">关闭</button>
</div>
<div class="modal-footer" style="text-align: left;padding:5px;height: 25px;">
	<span class="">申请单状态</span>
	<select id="appStatus" style="width:100px;"> 
		<c:if test="${u.userFlag=='P'}"><option value="-1" <c:if test="${appStatus=='-1'}">selected="selected"</c:if> >全部</option></c:if>
		<c:if test="${u.userFlag=='P'}"><option value="0" <c:if test="${appStatus=='0'}">selected="selected"</c:if> >未申请</option></c:if>
		<option value=""  selected="selected">审批状态</option>
		<option value="1" <c:if test="${appStatus=='1'}">selected="selected"</c:if> >待审批</option>
		<option value="2" <c:if test="${appStatus=='2'}">selected="selected"</c:if> >审批通过</option>
		<c:if test="${u.userFlag=='P'}"><option value="3" <c:if test="${appStatus=='3'&& u.userFlag=='P'}">selected="selected"</c:if> >审批不通过</option></c:if>
	</select>
	<span class="">申请日期</span><input type="text" name="qdate" id="begineffectDate" value="${beginappDate}" style="height:25px;width:100px;" class="datebox" /><span>至</span>
	<input type="text" id="endeffectDate" class="datebox" name="qdate"  value="${endappDate}" style="height:25px;width:100px;"/>
				<input class="btn btn-small btn-danger" data-toggle="button" type="button" value="查询"
					style="height:25px; border: 2px; width:45px; margin-top:-10px;" id="btn_query"/>
			</div>
	<div class="row-fluid" style="height:100%;" >
		<div class="span12">
			<!--PAGE CONTENT BEGINS-->
			<div class="row-fluid" id="itemdiv">
				<div class="span12" style="height: 40%;">
					<table id="sample-table-1" class="table table-striped table-bordered table-hover"  style=" font-size: 12px;margin-bottom: 0px;">
						<thead>
					<tr align="center">
						<th class="center" style="width:15px;"><input id="checkAll" type="checkbox"/></th>
						<th class="center" style="width:40px;">行号</th>
						<th class="center" style="width:71px;">状态</th>
						<th class="center" style="width:65px;">供应商编码</th>
						<th class="center" style="width:95px;">供应商名称</th>
						<th class="center" style="width:75px;">物料编码</th>
						<th class="center" style="width:115px;">物料名称</th>
						<th class="center" style="width:95px;">物料规格型号</th>
						<th class="center" style="width:40px;">单价</th>
						<th class="center" style="width:55px;">申请数量</th>
						<th class="center" style="width:50px;">单位</th>
						<th class="center" style="width:40px;">金额</th>
						<th class="center" style="width:95px;">申请日期</th>
						<th class="center" style="width:75px;">不通过原因</th>
						<th class="center" style="width:75px;">备注</th>
						<c:if test="${u.userFlag=='P'}"><th class="center" style="width:60px;">操作</th></c:if>
					</tr>
				</thead>
						<tbody>
							<c:forEach var="item" items="${items}" varStatus="index" step="1">
								<tr>
									<td class="center" style="width:30px;" >
										<input type="checkbox" value="${item.id}" name="sid" style="width:30px;"/>
									</td>
										<td class="center">${index.index+1}</td>
										<td class="center">
											<c:if test="${item.appStatus=='0' }">未申请</c:if>
											<c:if test="${item.appStatus=='1'}">待审批</c:if>
											<c:if test="${item.appStatus=='2'}">审批通过</c:if>
											<c:if test="${item.appStatus=='3'}">审批不通过</c:if>
										</td>
										<td class="center">${item.scmcoc.code}</td>
										<td class="center">${item.scmcoc.name}&nbsp;</td>
										<td class="center">${item.material.hsCode}&nbsp;</td>
										<td class="center">${item.material.hsName}&nbsp;</td>
										<td class="center">${item.material.model}&nbsp;</td>
										<td class="center">${item.price}&nbsp;</td>
										<td class="hidden-480 center">${item.totalQty}&nbsp;</td>
										<td class="hidden-480 center">${item.material.unit.name}&nbsp;</td>
										<td class="hidden-480 center">${item.amount}&nbsp;</td>
										<td class="center"><fmt:formatDate value="${item.appDate}" pattern="yyyy-MM-dd"/>&nbsp;</td>
										<td class="center" width="80px;">${item.noPassReason}&nbsp;</td>
										<td class="center" width="80px;">${item.note}&nbsp;</td>
										<c:if test="${u.userFlag=='P'}">
										<c:if test="${item.appStatus==0 || item.appStatus==3}">
											<td class="center">
												<a href="javascript:void(0);" onclick="edit(this,'10,15')">修改</a>|
												<a href="javascript:void(0);" onclick="delData('${item.id}','AppBillItem')">删除</a>
											</td>
										</c:if>
										<c:if test="${item.appStatus==1 || item.appStatus==2}">
											<td class="center">
												<span style="color: gray;" 
													title="<c:if test='${item.appStatus==1}'>待审批状态，不能修改</c:if><c:if test='${item.appStatus==2}'>审批通过状态，不能修改</c:if>">修改</span>|
												<span style="color: gray;"
													title="<c:if test='${item.appStatus==1}'>待审批状态，不能删除</c:if><c:if test='${item.appStatus==2}'>审批通过状态，不能删除</c:if>">删除</span>
											</td>
										</c:if>
									</c:if>
								</tr>
							</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<div class="dialog" id="dialog" title="选择物料" style="display: none;width:70%;">
		<%-- <input style="height: 25px;width: 160px;" type="text" name="${hsCode}" id="hsCode"/><input class="btn btn-small btn-danger" style="height: 25px; margin-top: -10px;border-top-width: 1px;" data-toggle="button" type="submit" value="查询"/> --%>
			<p style="height: 28px; margin-bottom: 0px;"><span>供应商</span>
				<select id="scmcoc" style="height:28px; width:150px;">
						<option value="chooice">---请选择供应商---</option>
					<c:forEach var="scm" items="${scmcocs}">
						<option value="${scm.id}">${scm.name}</option>
					</c:forEach>
				</select>
				<span>物料编码</span><input type="text" value="${hsCode}" id="hsCode" style="height:28px; width:90px;"/>
				<span>物料名称</span><input type="text" value="${hsName}"  id="hsName" style="height:28px; width:90px;"/>
				<button class="btn btn-small btn-danger" data-toggle="button" type="button"  style="margin-left: 10px; height: 25px; border-top-width: 0px; margin-top: -8px; border-bottom-width: 2px; padding-bottom: 0px;" onclick="queryMater();">查询</button>
			</p>
			<div id="matContent">
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
		<!-- 审批时显示输入不通过的原因div层 -->
		<div id="verifyForm" style="width:400px;height:200px;border:solid 1px black; background-color:#F5FFE8; position: absolute; left:400; top:200; display: none; padding:5px;">
			<input type="radio" name="verify" value="2" checked="checked">审批通过　　<input type="radio" name="verify" value="3">审批不通过
			<br>不通过原因:<br><textarea style="height: 120px;width:380px;" id="reason"></textarea>
			<input class="btn btn-small btn-danger" data-toggle="button" type="button" value="确定"
					style="height:25px; border: 2px; width:45px; margin-top:-10px;" id="btn_ok"/>
			<input class="btn btn-small btn-danger" data-toggle="button" type="button" value="取消"
					style="height:25px; border: 2px; width:45px; margin-top:-10px;" id="btn_cancel"/>
		</div>
	</body>	
		<script type="text/javascript">
				//对话框查询按钮
			function queryMater(){
					var scmid = $("#scmcoc").val();
					var hid = $('#head').val();
					var hsCode = $("#hsCode").val();
					var hsName = $("#hsName").val();
					var isFromBom = $("#isFromBom").val();
					var url = "${pageContext.request.contextPath}/appbill_findMaterial.action?scmid="+scmid+"&hsCode="+hsCode+"&hsName"+parse(hsName)+"&hid="+hid+"&isFromBom="+isFromBom;
					$("#matContent").load(url);
			}
			
			$(function(){
				
				
				//上一页面
				$("#goback").click(function(){
					toMain("${pageContext.request.contextPath}/appbill_findAppBillHeads.action");
				});
				
			
			$("input[name='qdate']").datepicker({
				changeYear: true,
				changeMonth: true,
				yearRange: '1900:', 
				dateFormat: 'yy-mm-dd'
			});
			
				$("#add").click(function(){
					var hid = $('#head').val();
					var orderNo = $('#orderNo').val();
					var isFromBom="N";
					//检查是否可以新增(待审批和审批 通过状态不能新增)
					var checkUrl = "${pageContext.request.contextPath}/appbill_checkStatus.action?ids="+hid;
					$.ajax({
						     type: "POST",
						     url:checkUrl,
						     async: false,
						     cache: false,
						     success:function(data){
								 var result=jQuery.parseJSON(data);
								 if(!result.success){
								 	alert(result.msg);
								 	return;
								 }else{
									 if(orderNo!="undefined"&&orderNo!=""){
										 if(confirm('是否根据BOM表来添加信息?如果这样,首先保证BOM表中有对应信息!') ){
											 isFromBom = "Y";
										 }
											 $("#isFromBom").val(isFromBom);
											 //弹出物料对话框
					  							showDialog();
											 	var url = "${pageContext.request.contextPath}/appbill_findMaterial.action?isFromBom="+isFromBom+"&hid="+hid;
											 	$("#matContent").load(url);
									 }else{
											 $("#isFromBom").val(isFromBom);
										 if(confirm('该申请单没有订单号,将直接从物料清单中调取?') ){
											//弹出物料对话框
					  							showDialog();
											 	var url = "${pageContext.request.contextPath}/appbill_findMaterial.action?isFromBom="+isFromBom+"&hid="+hid;
											 	$("#matContent").load(url);
										 }
									 }
								 }
							 },error:function(){
							    alert("程序异常，请重新启动程序！");
							 }
					  	});
					
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
		            	var url = "${pageContext.request.contextPath}/appbill_findMaterialByIds.action?ids="+paramstr+"&scmid="+scmid+"&hid="+hid;
						closeListenler();
		               toMain(url);
					});
				});
			
				//查询
				$("#btn_query").click(function(){
					var hid = $("#head").val();
					var appStatus = $("#appStatus").val();
					var begineffectDate = $("#begineffectDate").val();
					var endeffectDate = $("#endeffectDate").val();
					var param = hid+"&beginappDate="+begineffectDate+"&endappDate="+endeffectDate+"&appStatus="+appStatus;
					var url = "${pageContext.request.contextPath}/appbill_findItemByHid.action?ids="+param;
					window.location.href=url;
				});
		
				//审批
				$("#btn_verify").click(function(){
				//jquery获取复选框值      
		            	var paramstr = "";      
		            	$('input[name="sid"]:checked').each(function(){//遍历每一个名字为sid的复选框，其中选中的执行函数      
		            		paramstr+=$(this).val()+"/";//将选中的值组装成一个以'/'分割的字符串
		            	});
		           if(paramstr==""){
		           	alert("请勾选要审批的内容");
		           	return;
		           }
		           //var hid = $("#head").val();
		           var url = "${pageContext.request.contextPath}/appbill_checkStatus.action?ids="+paramstr;
		           $.ajax({
		           		url:url,
		           		dateType:"json",
		           		async: false,
						cache: false,
						success:function(data){
							var result=jQuery.parseJSON(data);
							if(result.success){
								$("#verifyForm").show();
							}else{
								alert(result.msg);
							}
						}
		           });
		           //
				});
			});
			//审批时显示输入不通过的原因div层【取消按钮】
			$("#btn_cancel").click(function(){
				$("#verifyForm").hide();
			});
			
			//审批时显示输入不通过的原因div层【确认按钮】
			$("#btn_ok").click(function(){
				var verify = $('input[name="verify"]:checked').val();
				var reason = $("#reason").val().trim();
				if(verify==""){
					alert("请选择是否通过!");
					return;
				}else if(verify=="3" && reason==""){
					alert("请输入不通过的原因!");
					return;
				}else{
					$("#verifyForm").hide();
					
					//开始审批
					//jquery获取复选框值      
		            	var paramstr = "";  
		            	$('input[name="sid"]:checked').each(function(){//遍历每一个名字为sid的复选框，其中选中的执行函数      
		            		paramstr+=$(this).val()+",";//将选中的值组装成一个以'/'分割的字符串
		            	});
		            	var url = "${pageContext.request.contextPath}/appbill_verifyInfo.action";
		            	$.ajax({
						     type: "POST",
						     url:url,
						     data:{ids:paramstr,verify:verify,noPassReason:parse(reason)},
						     async: false,
						     cache: false,
						     success:function(args){
								 var result=jQuery.parseJSON(args);
								 if(result.success){
								 	alert(result.msg);
								 	var ids = $('#head').val();
								 	url = "${pageContext.request.contextPath}/appbill_verifyList.action?ids="+ids;
								 	toMain(url);
								 }else{
								 alert(result.msg);
								 }
							 },error:function(){
							    alert("程序异常，请重新启动程序！");
							 }
					  	});
				}
			});
			
			//修改
			function edit(obj,arr){
				showTableEdit(obj,arr);
			}
			
			//保存修改的数据
			function saveData(){
				var jsonstr = getModifyData();
				if(jsonstr==""){
					alert("没有数据保存!");
					return;
				}
				var url = "${pageContext.request.contextPath}/appbill_editAppBillItem.action?jsonstr="+jsonstr;
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
					     		var url = "${pageContext.request.contextPath}/appbill_findItemByHid.action?ids="+id;
					     		window.location.href=url;
					     		closeform();
					     	}
					     },error:function(){
					        	alert("程序异常，请重新启动程序！");
					      }
					  	});
			}	
		</script>
