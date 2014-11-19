<%@ page language="java" import="java.util.*,java.text.SimpleDateFormat" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/dialog/dialogCss.css" type="text/css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dialog/dialog.js"></script>
<div class="page-header position-relative" style="margin-bottom: 0px; height:10px;">
	<span style="font-size: 14px; font-weight: bold;">报价单</span>
</div>
<div class="modal-footer" style="text-align: left;">
	<span class="">供应商名称</span><input type="text" id="scmCocName" value="${scmCocName}" style="height:25px;width:100px;" class="" /> 
	<span class="">物料编码</span><input type="text" id="hsCode" value="${hsCode}" style="height:25px;width:100px;" class="" /> 
	<span class="">生效日期</span><input type="text" id="begineffectDate" value="${effectDate}" style="height:25px;width:100px;" class="" /><span>至</span>
	<input type="text" id="endeffectDate" value="${effectDate}" style="height:25px;width:100px;" class="" /> 
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
								<th class="center" style="width:30px;">流水号</th>
								<th class="center" style="width:71px;">供应商编码</th>
								<th class="center">供应商名称</th>
								<th class="center" style="width:91px;">供应商联系电话</th>
								<th class="center">物料编码</th>
								<th class="center">物料名称</th>
								<th class="center">规格型号</th>
								<th class="center" style="width:60px;">计量单位</th>
								<th class="center" style="width:50px;">单价</th>
								<th class="center" style="width:60px;">生效日期</th>
								<th class="center" style="width:100px;">备注</th>
								<th class="center" style="width:70px;">操作</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach var="quotation" items="${quotations}" varStatus="index" step="1">
								<tr>
									<td class="center" style="width:30px;" >
										<input type="checkbox" value="${quotation.id}" name="sid" style="width:30px;"/>
									</td>
										<td class="center">${quotation.serialNo}</td>
										<td class="center">${quotation.scmcoc.code}</td>
										<td class="center">${quotation.scmcoc.name}</td>
										<td class="hidden-480 center">${quotation.scmcoc.linkPhone}&nbsp;</td>
										<td class="hidden-480 center">${quotation.material.hsCode}&nbsp;</td>
										<td class="hidden-480 center">${quotation.material.hsName}&nbsp;</td>
										<td class="hidden-480 center">${quotation.material.model}&nbsp;</td>
										<td class="hidden-480 center">${quotation.material.unit.name}&nbsp;</td>
										<td class="hidden-480 center">${quotation.price}&nbsp;</td>
										<td class="center"><fmt:formatDate value="${quotation.effectDate}" pattern="yyyy-MM-dd"/>&nbsp;</td>
<%-- 										<td class="hidden-480 center">${quotation.effectDate}　</td> --%>
										<td class="hidden-480 center">${quotation.note}&nbsp;</td>
										<td class="center">
											<a href="javascript:void(0);" onclick="edit(this,'10,11,12','${quotation.id}')">修改</a>｜
											<a href="javascript:void(0);" onclick="">删除</a>
										</td>
								</tr>
							</c:forEach>
					</table>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" id="add">新增</button>
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" id="save" onclick="closeAllEdit('sample-table-1','10,11,12')">保存</button>
				<button class="btn btn-small btn-danger pull-left" data-dismiss="modal" onclick="delMoreScmcoc()">
					批量删除
				</button>
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button">Excel导入</button>
				<!-- 分页 -->
				<div class="pagination pull-right no-margin" style="width: 700px;">
					<ul>
						<li class="next"><a>当前页【${currIndex}/${pageNums}】</a>
						</li>
						<c:if test="${currIndex!=1 && currIndex!=null}">
						<li class="next"><a href="javascript: gotoPage( 1, ${pageNums} )">首页</a></li>
							<li class="next"><a href="javascript: gotoPage( ${currIndex - 1}, ${pageNums} )">上一页</a>
							</li>
						</c:if>
						<c:if test="${currIndex!=pageNums}">
							<li class="next"><a href="javascript: gotoPage( ${currIndex + 1}, ${pageNums} )">下一页 </a>
							</li>
						<li class="next"><a href="javascript: gotoPage( ${pageNums}, ${pageNums}) ">尾页 </a><label  class="pull-right no-margin" style="line-height: 30px;">直接到第</label>
						</li>
						</c:if>
						
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
		<input type="hidden" id="hid"/>
		<!-- dialog -->
		<div class="dialog" id="dialog" title="选择物料">
		<%-- <input style="height: 25px;width: 160px;" type="text" name="${hsCode}" id="hsCode"/><input class="btn btn-small btn-danger" style="height: 25px; margin-top: -10px;border-top-width: 1px;" data-toggle="button" type="submit" value="查询"/> --%>
			<p style="height: 28px; margin-bottom: 0px;"><span>供应商</span>
				<select id="scmcoc">
						<option value="chooice">---请选择供应商---</option>
					<c:forEach var="scm" items="${scmcocs}">
						<option value="${scm.id}">${scm.name}</option>
					</c:forEach>
				</select>
			</p>
					<table id="sample-table-1" class="table table-striped table-bordered table-hover"  style=" font-size: 12px;margin-top:2px;">
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
									<td>${index.index+1}</td>
									<td>${mater.hsCode}</td>
									<td>${mater.hsName}</td>
									<td>${mater.unit.name}</td>
									<td>${mater.model}</td>
									<td>${mater.imgExgFlag}</td>
									<td>${mater.note}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
		</div>
	</div>
<script type="text/javascript">
	$(function(){
		$("#begineffectDate").datepicker({
  			changeYear: true,
			changeMonth: true,
			yearRange: '1900:', 
			dateFormat: 'yy-mm-dd'
  		});
		$("#endeffectDate").datepicker({
  			changeYear: true,
			changeMonth: true,
			yearRange: '1900:', 
			dateFormat: 'yy-mm-dd'
  		});
  		$("#add").click(function(){
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
            	var url = "${pageContext.request.contextPath}/quotation_findMaterialByIds.action?ids="+paramstr+"&scmid="+scmid;
                toMain(url); 
				closeListenler();
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
		var scmCocName = $("#scmCocName").val(); 
		var hsCode = $("#hsCode").val(); 
		var begineffectDate = $("#begineffectDate").val();
		var endeffectDate = $("#endeffectDate").val();
		var likeStr = "scmCocName="+parse(scmCocName)+"&hsCode="+parse(hsCode)+"&begineffectDate="+begineffectDate+"&endeffectDate="+endeffectDate;
		// 拼接URL
		var url = "${pageContext.request.contextPath}/quotation_findQuotations.action?"+likeStr;
		// 在本窗口中显示指定URL的页面
		toMain(url);
	}
	
	function parse(str){
		return encodeURI(encodeURI(str));  
	}
function edit(obj,arr,ids){
	var va = $('#hid').val();
	$('#hid').val(va+","+ids);
	showTableEdit(obj,arr);
}
	
</script>