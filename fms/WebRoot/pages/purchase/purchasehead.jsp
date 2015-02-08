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
	<span style="font-size: 14px; font-weight: bold;margin-left:5px; padding:3px 3px 0px 3px; border:solid 1px gray; border-bottom: 0px;">采购单</span>
</div>

<div class="modal-footer" style="text-align: left;padding:5px;height: 25px;">
	<span class="">采购单号码</span><input type="text" id="happNo" value="${appNo}" style="height:25px;width:100px;" class="" /> 
	<span class="">采购日期</span><input type="text" id="hbeginappDate" value="${appDate}" style="height:25px;width:100px;" class="datebox" /><span>至</span>
	<input type="text" id="hendappDate" class="datebox" value="${appDate}" style="height:25px;width:100px;"/>
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
						<tbody id="headmodel"></tbody>
							
					</table>
				</div>
			</div>
			</div>
			</div>



<script type="text/javascript">
	$(function(){
		$("#hbeginappDate").val($("#d1").val());
		$("#hendappDate").val($("#d2").val());
	});
</script>