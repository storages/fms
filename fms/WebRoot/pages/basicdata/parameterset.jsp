<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'stock.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/public/public.js"></script>
</head>

<body>
	<input type="hidden" id="flag" value="${params.id}" />
	<!-- 为了判断是新增还是修改 -->
	<div class="page-header position-relative" style="margin-bottom: 0px;">
		<h5>用户管理＞＞系统参数设置</h5>
	</div>
	<div class="modal-footer">
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
							name="params.weightBits" id="names" style="height:25px;" /></td>
					</c:if>
					<c:if test="${params.weightBits!=null}">
						<td class="hidden-480 addcss"><input type="text" id="weight"
							value="${params.weightBits}" name="params.weightBits" id="names"
							style="height:25px;" /></td>
					</c:if>
				</tr>
				<tr>
					<td class="captioncss" style="text-align: right; width:100px;">单价保留小数位数</td>
					<c:if test="${params.priceBits==null}">
						<td class="hidden-480 addcss"><input type="text" value="3" id="price"
							name="params.priceBits" id="names" style="height:25px;" /></td>
					</c:if>
					<c:if test="${params.priceBits!=null}">
						<td class="hidden-480 addcss"><input type="text" id="price"
							value="${params.priceBits}" name="params.priceBits" id="names"
							style="height:25px;" /></td>
					</c:if>
				</tr>
				<tr>
					<td class="captioncss" style="text-align: right; width:100px;">金额保留小数位数</td>
					<c:if test="${params.amountBits==null}">
						<td class="hidden-480 addcss"><input type="text" value="3" id="mount"
							name="params.amountBits" id="names" style="height:25px;" /></td>
					</c:if>
					<c:if test="${params.amountBits!=null}">
						<td class="hidden-480 addcss"><input type="text" id="mount"
							value="${params.amountBits}" name="params.amountBits" id="names"
							style="height:25px;" /></td>
					</c:if>
				</tr>
			</table>
		</div>
	</div>
	<script type="text/javascript">
		function save() {
			var id = $('#flag').val();
			var qtyBits = $(":input[name='params.qtyBits']").val();
			var weightBits = $(":input[name='params.weightBits']").val();
			var priceBits = $(":input[name='params.priceBits']").val();
			var amountBits = $(":input[name='params.amountBits']").val();
			checkRate();
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
			//保存
			var str = "qtyBits=" + qtyBits + "&weightBits=" + weightBits
					+ "&priceBits=" + priceBits + "&amountBits=" + amountBits
					+ "&id=" + id;
			var subUrl = "${pageContext.request.contextPath}/params_saveParamsValue.action?"
					+ str;
			toMain(subUrl);
			alert("保存成功");
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
		}

		function checkRate() {
			var qtyBits = $(":input[name='params.qtyBits']").val();
			var weightBits = $(":input[name='params.weightBits']").val();
			var priceBits = $(":input[name='params.priceBits']").val();
			var amountBits = $(":input[name='params.amountBits']").val();
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
			return true;
		}
	</script>
</body>
</html>
