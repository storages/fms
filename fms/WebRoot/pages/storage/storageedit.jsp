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
		.required{color:red;}
		p{margin-bottom: 2px;}
</style>
    <input type="hidden" id="flag" value="${materinfo.id}"/><!-- 为了判断是新增还是修改 -->
    <div class="page-header position-relative" style="margin-bottom: 0px;">
    	<c:if test="${storage.id==null}">
			<h5>物料＞＞<a href="">入库</a>＞＞新增</h5>
		</c:if>
    	<c:if test="${storage.id!=null}">
			<h5>物料＞＞<a href="">出库</a>＞＞修改</h5>
		</c:if>
	</div>
	<div class="row-fluid" id="mybox" style="margin-left: 5px;" >
		<div class="span12">
			<p>
				<span id="hcode">&nbsp;&nbsp;&nbsp;&nbsp;流水号</span><span class="required">*</span><input style="height: 25px;width: 160px;" type="text" name="inStorage.serialNo" readonly="readonly">&nbsp;&nbsp;&nbsp;&nbsp;
				<span id="useFlag">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态</span><input style="height: 25px;width: 160px;" type="text" name="inStorage.useFlag"  readonly="readonly">&nbsp;&nbsp;&nbsp;&nbsp;
			</p>
			<p>
				&nbsp;&nbsp;货物标志<span class="required">*</span><select style="height: 25px;width: 160px;font-size: 12px;" name="" id="imgexgflag">
						<option value="">---请选择---</option>
						<option value="I" <c:if test="${inStorage.imgExgFlag==imgExgFlag}">selected="selected"</c:if>>原料</option>
						<option value="E" <c:if test="${inStorage.imgExgFlag==imgExgFlag}">selected="selected"</c:if>>成品</option>
					</select>&nbsp;&nbsp;&nbsp;&nbsp;
				<span id="inStorageNo">入库单号</span><span class="required">*</span><input style="height: 25px;width: 160px;" type="text" name="inStorage.inStorageNo" >&nbsp;&nbsp;&nbsp;&nbsp;
			</p>
			<p>
				<span id="inStorageNo">&nbsp;&nbsp;采购单号</span><span class="required">*</span><input style="height: 25px;width: 160px;" type="text" name="inStorage.purchaseNo" readonly="readonly">&nbsp;&nbsp;&nbsp;&nbsp;
				<span id="orderNo">&nbsp;&nbsp;&nbsp;订单号</span><input style="height: 25px;width: 160px;" type="text" name="inStorage.orderNo" >&nbsp;&nbsp;&nbsp;&nbsp;
			</p>
			<p>
				<span id="inStorageNo">&nbsp;&nbsp;物料名称</span><span class="required">*</span><input style="height: 25px;width: 160px;" type="text" name="inStorage.HsName" readonly="readonly">&nbsp;&nbsp;&nbsp;&nbsp;
				<span id="orderNo">&nbsp;物料编码</span><input style="height: 25px;width: 160px;" type="text" name="inStorage.material.hsCode" ><img alt="选择物料" src="${pageContext.request.contextPath}/images/search.gif" id="dgMaterQuery" style="cursor: pointer;">&nbsp;&nbsp;&nbsp;&nbsp;
			</p>
			<p>
				<span id="inStorageNo">&nbsp;&nbsp;颜&nbsp;&nbsp;&nbsp;&nbsp;色</span><span class="required">*</span><input style="height: 25px;width: 160px;" type="text" name="inStorage.material.color" readonly="readonly">&nbsp;&nbsp;&nbsp;&nbsp;
				<span id="orderNo">&nbsp;规格型号</span><input style="height: 25px;width: 160px;" type="text" name="inStorage.material.model" readonly="readonly">&nbsp;&nbsp;&nbsp;&nbsp;
			</p>
			<p>
				&nbsp;&nbsp;单&nbsp;&nbsp;&nbsp;&nbsp;位<span class="required">*</span><select style="height: 25px;width: 160px;font-size: 12px;" name="" id="unit">
						<option value="">---请选择单位---</option>
						<c:forEach var="unit" items="${units}">
							<option value="${unit.id}" <c:if test="${unit.name==inStorage.material.unit.name}"> selected="selected"</c:if>>${unit.name}</option>
						</c:forEach>
					</select>
				<span id="orderNo">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数&nbsp;&nbsp;&nbsp;量</span><input style="height: 25px;width: 160px;" type="text" name="inStorage.inQty" >&nbsp;&nbsp;&nbsp;&nbsp;
			</p>
			<p>
				<span id="orderNo">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;批次号</span><input style="height: 25px;width: 160px;" type="text" name="inStorage.betchNo" >&nbsp;
				<span id="inStorageNo">&nbsp;&nbsp;数量/(包)</span><span class="required">*</span><input style="height: 25px;width: 160px;" type="text" name="inStorage.specQty">&nbsp;&nbsp;&nbsp;&nbsp;
			</p>
			<p>
				<span id="orderNo">&nbsp;&nbsp;&nbsp;&nbsp;件&nbsp;&nbsp;&nbsp;数</span><input style="height: 25px;width: 160px;" type="text" name="inStorage.pkgs" >&nbsp;&nbsp;
				供应商名称<span class="required">*</span><select style="height: 25px;width: 160px;font-size: 12px;" name="" id="scmcoc">
						<option value="">---请选择供应商名称---</option>
						<c:forEach var="scmc" items="${scmcocs}">
							<option value="${scmc.id}" <c:if test="${scmc.name==inStorage.scmcoc.name}"> selected="selected"</c:if>>${scmc.name}</option>
						</c:forEach>
					</select>&nbsp;&nbsp;&nbsp;&nbsp;
			</p>
			<p style="padding-left: 0px;">
					&nbsp;&nbsp;入库类型<span class="required">*</span><select style="height: 25px;width: 160px; font-size: 12px;" name="inStorage.impFlag">
						<option value="">---请选择入库类型---</option>
						<c:forEach var="impexptype" items="${impexptypes}">
							<option value="${impexptype.code}" <c:if test="${inStorage.impFlag==impexptype.code}"> selected="selected"</c:if>>${impexptype.name}</option>
						</c:forEach>
					</select>
					&nbsp;&nbsp;&nbsp;&nbsp;仓库名称<span class="required">*</span><select style="height: 25px;width: 160px;font-size: 12px;" name="" id="stock">
						<option value="">---请选择仓库名称---</option>
						<c:forEach var="stock" items="${stocks}">
							<option value="${stock.id}" <c:if test="${stock.name==inStorage.stock.name}"> selected="selected"</c:if>>${stock.name}</option>
						</c:forEach>
					</select>
				</p>
			<p style="padding-left: 0px;">
					&nbsp;&nbsp;物料类型<span class="required">*</span><select style="height: 25px;width: 160px;font-size: 12px;" name="inStorage.materialType.typeName">
						<option value="">---请选择物料类型---</option>
						<c:forEach var="mtype" items="${types}">
								<option value="${mtype.id}" <c:if test="${inStorage.materialType.typeName==mtype.typeName}">selected="selected"</c:if>>${mtype.typeName}</option>
						</c:forEach>
					</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<span id="note">备&nbsp;&nbsp;&nbsp;注</span><input style="height: 25px;width: 160px;" type="text" name="inStorage.note" >
				</p>
		</div>
	</div>
  
