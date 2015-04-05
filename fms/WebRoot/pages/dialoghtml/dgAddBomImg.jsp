<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
$("#checkAlls").click(function(){
	if($("#checkAlls").attr("checked")){
		$("input[name='mid']").attr("checked",'true');//全选 
	}else{
		$("input[name='mid']").removeAttr("checked");//取消全选
	}
});

$("#btnDgQuery").click(function(){
	$("#dg_content").load("${pageContext.request.contextPath}/bom_findMaterial.action?hsCode="+$("#tfHsCode").val());
});
</script>
<div class="modal-footer" style="text-align: left;padding:0px; height:29px; position: static;" >
	物料编码<input type="text" value="${hsCode}" style="height:25px;" id="tfHsCode"/>
	<button class="btn btn-small btn-danger" data-dismiss="modal" style="margin-top: -10px;" id="btnDgQuery">查询</button>
</div>
<table id="sample-table-2" class="table table-striped table-bordered table-hover"  style=" font-size: 12px;margin-top:2px;">
						<thead>
							<tr align="center">
								<th class="center" style="width:30px;"><input type="checkbox" title="全选" id="checkAlls"/></th>
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
									<td><input type="checkbox" value="${mater.id}" name="mid"/></td>
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