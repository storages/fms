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
</style>

<div class="page-header position-relative" style="margin: 0px; height:10px;line-height: 25px;">
	<span style="font-size: 14px; font-weight: bold;margin-left:5px; padding:3px 3px 0px 3px; border:solid 1px gray; border-bottom: 0px;">请购单</span>
</div>
<div class="modal-footer" style="text-align: left;padding:5px;height: 25px;">
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
			<table id="sample-table-1" class="table table-striped table-bordered table-hover"  style=" font-size: 12px;margin-bottom: 0px;">
				<thead>
					<tr align="center">
						<th class="center" style="width:30px;">选择</th>
						<th class="center" style="width:40px;">流水号</th>
						<th class="center" style="width:71px;">申请单状态</th>
						<th class="center">申请单号码</th>
						<th class="center" style="width:91px;">项数</th>
						<th class="center">总数量</th>
						<th class="center">总金额</th>
						<th class="center" style="width:70px;">申请日期</th>
						<th class="center" style="width:60px;">已审批数</th>
						<th class="center" style="width:60px;">未审批数</th>
						<th class="center" style="width:200px;">备注</th>
						<th class="center" style="width:70px;">操作</th>
					</tr>
				</thead>
			</table>
			<div class="row-fluid" style="height: 20%; overflow: auto;">
				<div class="span12"  style="height: 430px;">
					<table id="sample-table-1" class="table table-striped table-bordered table-hover"  style=" font-size: 12px;">
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
											<a href="javascript:void(0);" onclick="edit(this,'10,11,12')">修改</a>｜
											<a href="javascript:void(0);" onclick="delData('${quotation.id}','Quotation')">删除</a>
										</td>
								</tr>
							</c:forEach>
					</table>
				</div>
			</div>
			<div class="modal-footer" style="padding:0px;">
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" id="add" style="margin-left: 10px;">新增</button>
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" id="save" onclick="saveData()">保存</button>
				<button class="btn btn-small btn-danger pull-left" data-dismiss="modal"  onclick="delData('','Quotation')">
					批量删除
				</button>
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" onclick="javascript:toMain('${pageContext.request.contextPath}/quotation_toImportPage.action')">Excel导入</button>
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
		</div>
	</div>
			<hr style="border: 3px solid; margin: 5px 0px 5px 0px;"/>
			
			<!-- 表体数据 -->
			
			
			<div class="modal-footer" style="text-align: left;padding:5px;height: 25px;">
				<span class="">供应商名称</span><input type="text" id="scmCocName" value="${scmCocName}" style="height:25px;width:100px;" class="" /> 
				<span class="">物料编码</span><input type="text" id="hsCode" value="${hsCode}" style="height:25px;width:100px;" class="" /> 
				<span class="">生效日期</span><input type="text" id="begineffectDate" value="${effectDate}" style="height:25px;width:100px;" class="" /><span>至</span>
				<input type="text" id="endeffectDate" value="${effectDate}" style="height:25px;width:100px;" class="" /> 
				<input class="btn btn-small btn-danger" data-toggle="button" type="button" value="查询" onclick="gotoPage(1,1)"
					style="height:25px; border: 2px; width:45px; margin-top:-10px;" />
			</div>
			<table id="sample-table-1" class="table table-striped table-bordered table-hover"  style=" font-size: 12px;margin-bottom: 0px;">
				<thead>
					<tr align="center">
						<th class="center" style="width:30px;">选择</th>
						<th class="center" style="width:40px;">流水号</th>
						<th class="center" style="width:71px;">申请单状态</th>
						<th class="center">申请单号码</th>
						<th class="center" style="width:91px;">项数</th>
						<th class="center">总数量</th>
						<th class="center">总金额</th>
						<th class="center" style="width:70px;">申请日期</th>
						<th class="center" style="width:60px;">已审批数</th>
						<th class="center" style="width:60px;">未审批数</th>
						<th class="center" style="width:200px;">备注</th>
						<th class="center" style="width:70px;">操作</th>
					</tr>
				</thead>
			</table>
	<div class="row-fluid" style="height:35%; overflow: auto;" >
		<div class="span12">
			<!--PAGE CONTENT BEGINS-->
			<div class="row-fluid">
				<div class="span12"  style="height: 430px;">
					<table id="sample-table-1" class="table table-striped table-bordered table-hover"  style=" font-size: 12px;margin-bottom: 0px;">
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
	</div>
	 <div class="modal-footer" style="padding:0px;">
		<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" id="add">新增</button>
		<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" id="save" onclick="saveData()">保存</button>
		<button class="btn btn-small btn-danger pull-left" data-dismiss="modal"  onclick="delData('','Quotation')">
			批量删除
		</button>
		<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" onclick="javascript:toMain('${pageContext.request.contextPath}/quotation_toImportPage.action')">Excel导入</button>
		<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" id="btn_update" title="根据当前所选的报价单信息，找到相对应的申请单、采购单，并把选中的生效日期以后的单价全部更新">更新单价</button>
	</div>
