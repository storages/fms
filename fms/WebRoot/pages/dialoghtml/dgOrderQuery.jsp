<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<input type="hidden" id="hideflag" value="order" style="width: 120px;"/>
<div class="row-fluid" >
		<div class="span12">
			<!--PAGE CONTENT BEGINS-->
			<div class="row-fluid">
				<div class="span12" >
					<table id="sample-table-1" class="table table-striped table-bordered table-hover" style=" font-size: 12px;">
						<thead>
					<tr align="center">
						<th class="center" style="width:30px;">选择</th>
						<th class="center" style="width:40px;padding:0px;margin:0px;">流水号</th>
						<th class="center" style="width:91px;">订单号</th>
						<th class="center" style="width:130px;">客户名称</th>
						<th class="center" style="width:90px;">下单日期</th>
						<th class="center" style="width:90px;">交货日期</th>
						<th class="center" style="width:90px;">业务员</th>
					</tr>
				</thead>
						<tbody id="headmodel">
							<c:forEach var="head" items="${headList}" varStatus="index" step="1">
								<tr>
									<td class="center" style="width:20px;" >
										<input type="radio" value="${head.id}" name="materid" style="width:20px;"/>
									</td>
										<td class="center" style="width:31px;">${head.serialNo}&nbsp;</td>
										<td class="center" style="width:91px;">${head.orderNo}&nbsp;</td>
										<td class="center" style="width:65px;">${head.scmcoc.name}&nbsp;</td>
										<td class="center" style="width:92px;"><fmt:formatDate value="${head.placeOrderDate}" pattern="yyyy-MM-dd"/>&nbsp;</td>
										<td class="center" style="width:92px;"><fmt:formatDate value="${head.deliveryDate}" pattern="yyyy-MM-dd"/>&nbsp;</td>
										<td class="center" style="width:65px;">${head.salesman}&nbsp;</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			</div>
			</div>
