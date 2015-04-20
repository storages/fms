<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<link rel="stylesheet" href="${pageContext.request.contextPath}/css/datepicker/jquery-ui-1.8.16.custom.css" type="text/css"></link>


<script type="text/javascript" src="${pageContext.request.contextPath}/js/utils/chinese-of-spell.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datepicker/jquery.ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datepicker/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datepicker/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/public/public.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/dialog/dialogCss.css" type="text/css"></link>

<style type="text/css">
</style>

<script type="text/javascript">
	$(function(){
		initUi();
		//货物标志改变事件
		$("#imgexgflag").change(function(){
			initUi();
		});
	});
	
	function initUi(){
		var val = $("#imgexgflag").val();
		if("I"==val){
			$("#purchNo").show();
			$("#orderNo").hide();
			$("#captionsc").html("供应商名称");
			$("#tishi").html("--请选择供应商名称--");
		}else if("E"==val){
			$("#purchNo").hide();
			$("#orderNo").show();
			$("#captionsc").html("&nbsp;客户名称&nbsp;");
			$("#tishi").html("--请选择客户名称--");
		}
	}
	
	
</script>
    <input type="hidden" id="flag" value="${storage.id}"/><!-- 为了判断是新增还是修改 -->
    <div class="page-header position-relative" style="margin-bottom: 0px;">
    	<c:if test="${storage.id==null}">
			<h5>物料＞＞<a href="">入库</a>＞＞新增</h5>
		</c:if>
    	<c:if test="${storage.id!=null}">
			<h5>物料＞＞<a href="">出库</a>＞＞修改</h5>
		</c:if>
	</div>
	<div>
		<table id="sample-table-1" class="table table-striped table-bordered "  style=" font-size: 12px; width:600px; border: 0px;">
			<tr style="border:0px;">
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">流水号</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;">*</td>
				<td style="width: 150px;border:0px;padding:0px;"><input type="text" style="height:25px; width:100%;" readonly="readonly"/></td>
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">入库类型</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;">*</td>
				<td style="width: 150px;border:0px;padding:0px;">
					<select style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;" >
						<option value="0">A</option>
						<option value="1">B</option>
					</select>
				</td>
				<td style="width: 10px;border:0px;padding:0px;"></td>
			</tr>
			<tr style="border:0px;">
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">物料标志</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;">*</td>
				<td style="width: 150px;border:0px;padding:0px;">
					<select style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;" >
						<option value="I">原料</option>
						<option value="E">成品</option>
					</select>
				</td>
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">采购单号</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;">*</td>
				<td style="width: 150px;border:0px;padding:0px;"><input type="text" style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;" readonly="readonly" /></td>
				<td style="width: 10px;border:0px;padding:0px;"><img src="${pageContext.request.contextPath}/images/search.gif" style="margin-top: 6px; cursor: pointer;"/></td>
			</tr>
			<tr style="border:0px;">
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">入库单号</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;">*</td>
				<td style="width: 150px;border:0px;padding:0px;"><input type="text" style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;"/></td>
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">订单号</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;">*</td>
				<td style="width: 150px;border:0px;padding:0px;"><input type="text" style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;"/></td>
				<td style="width: 10px;border:0px;padding:0px;"><img src="${pageContext.request.contextPath}/images/search.gif" style="margin-top: 6px; cursor: pointer;"/></td>
			</tr>
			<tr style="border:0px;">
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">供应商名称</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;"></td>
				<td style="width: 150px;border:0px;padding:0px;"><input type="text" style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;" readonly="readonly"/></td>
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">物料编码</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;">*</td>
				<td style="width: 150px;border:0px;padding:0px;"><input type="text" style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;" readonly="readonly" /></td>
				<td style="width: 10px;border:0px;padding:0px;"><img src="${pageContext.request.contextPath}/images/search.gif" style="margin-top: 6px; cursor: pointer;"/></td>
			</tr>
			<tr style="border:0px;">
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">物料名称</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;"></td>
				<td style="width: 150px;border:0px;padding:0px;"><input readonly="readonly" type="text" style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;"/></td>
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">规格型号</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;"></td>
				<td style="width: 150px;border:0px;padding:0px;"><input readonly="readonly" type="text" style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;"/></td>
			</tr>
			<tr style="border:0px;">
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">计量单位</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;"></td>
				<td style="width: 150px;border:0px;padding:0px;"><input readonly="readonly" type="text" style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;"/></td>
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">入库数量</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;"></td>
				<td style="width: 150px;border:0px;padding:0px;"><input type="text" style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;"/></td>
			</tr>
			<tr style="border:0px;">
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">每件包装数</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;"></td>
				<td style="width: 150px;border:0px;padding:0px;"><input type="text" style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;"/></td>
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">件数</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;"></td>
				<td style="width: 150px;border:0px;padding:0px;"><input type="text" style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;"  readonly="readonly"/></td>
			</tr>
			<tr style="border:0px;">
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">仓库名称</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;"></td>
				<td style="width: 150px;border:0px;padding:0px;"><input type="text" style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;"/></td>
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">备注</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;"></td>
				<td style="width: 150px;border:0px;padding:0px;"><input type="text" style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;"/></td>
			</tr>
			<tr style="border:0px;text-align:center; ">
				<td colspan="6" style="border:0px;text-align:center;">
					<button class="btn btn-small btn-danger " data-toggle="button" type="button" onclick="">保存</button>
					<button class="btn btn-small btn-danger " data-toggle="button" type="button" onclick="">取消</button>
				</td>
			</tr>
		</table>
	</div>
  
