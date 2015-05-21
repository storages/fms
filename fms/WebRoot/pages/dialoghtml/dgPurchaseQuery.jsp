<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
						<th class="center" style="width:91px;">采购单号</th>
						<th class="center" style="width:91px;">申请单号</th>
						<th class="center" style="width:70px;">供应商编码</th>
						<th class="center" style="width:150px;">供应商名称</th>
						<th class="center" style="width:90px;">采购时间</th>
						<th class="center" style="width:160px;">特别说明</th>
					</tr>
				</thead>
						<tbody id="headmodel">
							<c:forEach var="head" items="${headList}" varStatus="index" step="1">
								<tr>
									<td class="center" style="width:20px;" >
										<input type="radio" value="${head.id}" name="sid" style="width:20px;"/>
									</td>
										<td class="center" style="width:31px;">${head.serialNo}&nbsp;</td>
										<td class="center" style="width:91px;">${head.purchaseNo}&nbsp;</td>
										<td class="center" style="width:91px;">${head.appBillNo}&nbsp;</td>
										<td class="center" style="width:65px;">${head.scmcoc.code}&nbsp;</td>
										<td class="center" style="width:65px;">${head.scmcoc.name}&nbsp;</td>
										<td class="center" style="width:92px;"><fmt:formatDate value="${head.purchDate}" pattern="yyyy-MM-dd"/>&nbsp;</td>
										<td class="center" style="width:65px;">${head.specialNote}&nbsp;</td>
								</tr>
							</c:forEach>
						</tbody>
							
					</table>
				</div>
			</div>
			</div>
			</div>
