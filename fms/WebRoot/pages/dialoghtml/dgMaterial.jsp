<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">
$("#btnDgQuery").click(function(){
	var code = $("#tfCode").val();
	var name = $("#tfName").val()==""?"":encodeURI(encodeURI($("#tfName").val()));
	var flag = $("#imgExgFlag").val();
	var url = "${pageContext.request.contextPath}/storage_findMaterialList.action";
	var param = "?hsCode="+code+"&hsName="+name+"&imgExgFlag="+flag;
	$("#dg_content").load(url+param);
});
</script>

<div class="page-header position-relative" style="margin: 0px;margin-top: 5px; padding:0px; text-align: left;">
	　物料编码:<input type="text" id="tfCode" value="${hsCode}" style="width: 120px;"/>　　
	物料名称:<input type="text" id="tfName" value="${hsName}" style="width: 120px;"/>
	<input type="hidden" id="hideflag" value="material" style="width: 120px;"/>
	<button class="btn btn-small btn-danger" data-dismiss="modal" style="margin-top: -10px;" id="btnDgQuery">查询</button>
</div>
<input type="hidden" value="${imgExgFlag}" id="imgExgFlag"/>
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
									<td><input type="checkbox" value="${mater.id}" name="materid" /></td>
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
					
					
					