<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/public/public.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/public/public.js"></script>

	<!-- 为了判断是新增还是修改 -->
	<input type="hidden" id="flag" value="${params.id}" />
	<div class="page-header position-relative" style="margin: 0px; height:10px;line-height: 25px;">
	<h5>基础资料＞＞系统参数设置</h5>
	</div>
	<div class="modal-footer">
		<button class="btn btn-small btn-danger pull-left"
			data-toggle="button" type="button" onclick="edit()">修改</button>
		<button class="btn btn-small btn-danger pull-left"
			data-toggle="button" type="button" onclick="save()">保存</button>
		<button class="btn btn-small btn-danger pull-left"
			data-toggle="button" type="button" onclick="cancel()" title="恢复初始状态">取消</button>
		<button class="btn btn-small btn-danger pull-left"
			data-toggle="button" type="button" onclick="defaultValue()"
			title="恢复默认值">恢复默认值</button>
	</div>
	<div class="row-fluid" id="mybox">
		<div class="span12">
			<table id="sample-table-1"
				class="table table-striped table-bordered table-hover tablecss"
				style=" font-size: 12px;">
				<tr>
					<td class="captioncss" style="text-align: right; width:100px;">数量保留小数位数</td>
					<c:if test="${params.qtyBits==null}">
						<td class="hidden-480 addcss"><input type="text" value="3" id="qty"
							name="params.qtyBits" style="height:25px;"/>
						</td>
					</c:if>
					<c:if test="${params.qtyBits!=null}">
						<td class="hidden-480 addcss"><input type="text" id="qty"
							value="${params.qtyBits}" name="params.qtyBits" 
							style="height:25px;" /></td>
					</c:if>
				</tr>
				<tr>
					<td class="captioncss" style="text-align: right; width:100px;">重量保留小数位数</td>
					<c:if test="${params.weightBits==null}">
						<td class="hidden-480 addcss"><input type="text" value="3" id="weight"
							name="params.weightBits"  style="height:25px;" /></td>
					</c:if>
					<c:if test="${params.weightBits!=null}">
						<td class="hidden-480 addcss"><input type="text" id="weight"
							value="${params.weightBits}" name="params.weightBits"
							style="height:25px;" /></td>
					</c:if>
				</tr>
				<tr>
					<td class="captioncss" style="text-align: right; width:100px;">单价保留小数位数</td>
					<c:if test="${params.priceBits==null}">
						<td class="hidden-480 addcss"><input type="text" value="3" id="price"
							name="params.priceBits" style="height:25px;" /></td>
					</c:if>
					<c:if test="${params.priceBits!=null}">
						<td class="hidden-480 addcss"><input type="text" id="price"
							value="${params.priceBits}" name="params.priceBits"
							style="height:25px;" /></td>
					</c:if>
				</tr>
				<tr>
					<td class="captioncss" style="text-align: right; width:100px;">金额保留小数位数</td>
					<c:if test="${params.amountBits==null}">
						<td class="hidden-480 addcss"><input type="text" value="3" id="mount"
							name="params.amountBits"  style="height:25px;" /></td>
					</c:if>
					<c:if test="${params.amountBits!=null}">
						<td class="hidden-480 addcss"><input type="text" id="mount"
							value="${params.amountBits}" name="params.amountBits"
							style="height:25px;" /></td>
					</c:if>
				</tr>
				<tr>
					<td class="captioncss" style="text-align: right; width:100px;">采购单打印次数</td>
					<c:if test="${params.printCount==null}">
						<td class="hidden-480 addcss"><input type="text" value="1" id="printCount"
							name="params.printCount"  style="height:25px;" /></td>
					</c:if>
					<c:if test="${params.printCount!=null}">
						<td class="hidden-480 addcss"><input type="text" id="printCount" 
							value="${params.printCount}" name="params.printCount"
							style="height:25px;" /></td>
					</c:if>
				</tr>
			</table>
		</div>
	</div>
	<script type="text/javascript">
	$(function(){
		//浏览状态
		$("#sample-table-1 input[type='text']").each(function(){
			$(this).attr("readonly","readonly");
		});
	});
	
	function edit(){
		//编辑状态
		$("#sample-table-1 input[type='text']").each(function(){
			$(this).removeAttr("readonly");
			$(this).css("border-color","red");
		});
	}
	
	//保存
		function save() {
			var id = $('#flag').val();
			var qtyBits = $(":input[name='params.qtyBits']").val();
			var weightBits = $(":input[name='params.weightBits']").val();
			var priceBits = $(":input[name='params.priceBits']").val();
			var amountBits = $(":input[name='params.amountBits']").val();
			var printCount = $(":input[name='params.printCount']").val();
			if(!checkRate()){
				return;
			}
			if (qtyBits == "") {
				$(":input[name='params.qtyBits']").val("3");
			}
			if (weightBits == "") {
				$(":input[name='params.weightBits']").val("3");
			}
			if (priceBits == "") {
				$(":input[name='params.priceBits']").val("3");
			}
			if (amountBits == "") {
				$(":input[name='params.amountBits']").val("3");
			}
			if (amountBits == "") {
				$(":input[name='params.amountBits']").val("1");
			}
			//保存
			var str = "qtyBits=" + qtyBits + "&weightBits=" + weightBits
					+ "&priceBits=" + priceBits + "&amountBits=" + amountBits
					+ "&id=" + id+"&printCount="+printCount;
			var subUrl = "${pageContext.request.contextPath}/params_saveParamsValue.action?"
					+ str;
			toMain(subUrl);
			//alert("保存成功");
		}

		//取消按钮
		function cancel() {
			var url = "${pageContext.request.contextPath}/params_getParameterValue.action";
			toMain(url);
		}

		//恢复默认值
		function defaultValue() {
			$(":input[name='params.weightBits']").val("3");
			$(":input[name='params.qtyBits']").val("3");
			$(":input[name='params.priceBits']").val("3");
			$(":input[name='params.amountBits']").val("3");
			$(":input[name='params.printCount']").val("1");
			save();
		}

		function checkRate() {
			var qtyBits = $(":input[name='params.qtyBits']").val();
			var weightBits = $(":input[name='params.weightBits']").val();
			var priceBits = $(":input[name='params.priceBits']").val();
			var amountBits = $(":input[name='params.amountBits']").val();
			var printCount = $(":input[name='params.printCount']").val();
			if(isNaN(qtyBits)){
				alert("数量保留小数位数只能是正整数");
				return false;
			}
			if(isNaN(weightBits)){
				alert("重量保留小数位数只能是正整数");
				return false;
			}
			if(isNaN(priceBits)){
				alert("单价保留小数位数只能是正整数");
				return false;
			}
			if(isNaN(amountBits)){
				alert("金额保留小数位数只能是正整数");
				return false;
			}
			if(isNaN(printCount)){
				alert("采购单打印次数只能是正整数");
				return false;
			}
			return true;
		}
	</script>
