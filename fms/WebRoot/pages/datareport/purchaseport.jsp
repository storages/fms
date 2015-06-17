<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/css/datepicker/jquery-ui-1.8.16.custom.css" type="text/css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/utils/chinese-of-spell.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datepicker/jquery.ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datepicker/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datepicker/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/public/public.js"></script>
<script type="text/javascript">
	$(function(){
		$("#beginDate").datepicker({
  			changeYear: true,
			changeMonth: true,
			yearRange: '1900:', 
			dateFormat: 'yy-mm-dd'
  		});
  		$("#endDate").datepicker({
  			changeYear: true,
			changeMonth: true,
			yearRange: '1900:', 
			dateFormat: 'yy-mm-dd'
  		});
  		
  		//查询按钮
  		$("#btnQuery").bind("click",function(){
  			var begindate = $("#beginDate").val();
  			var endDate = $("#endDate").val();
  			var scmcocName = $("#scmcocName").val();
  			var purchaseNo = $("#purchaseNo").val();
  			var url = "${pageContext.request.contextPath}/report_getPurchaseReport.action?begindate="+begindate+"&endDate="+endDate+"&scmcocName="+parse(scmcocName)+"&purchaseNo="+purchaseNo;
  			toMain(url);
  		});
	});
	
	function gototag(pageSize){
		var n = $("#gonum option:selected").val();
		gotoPage(n, pageSize);
	}
	
	/**
 * 转到指定页码
 * @param {Object} pageNum 要转到第几页        currIndex
 * @param {Object} pageSize 每页显示多少条数据    maxIndex 
 */
function gotoPage(pageNum, pageSize) {
	 var begindate = $("#beginDate").val();
		var endDate = $("#endDate").val();
		var scmcocName = $("#scmcocName").val();
		var purchaseNo = $("#purchaseNo").val();
		var url = "${pageContext.request.contextPath}/report_getPurchaseReport.action?begindate="+begindate+"&endDate="+endDate+"&scmcocName="+parse(scmcocName)+"&purchaseNo="+purchaseNo+"&currIndex=" + pageNum + "&maxIndex="+ pageSize ;
	// 在本窗口中显示指定URL的页面
	toMain(url);
}
</script>



  <body>
    <div class="page-header position-relative" style="margin-bottom: 0px; height:10px;margin-top:0px;line-height: 25px;">
		<h5>统计报表>>采购单统计</h5>
	</div>
	<div class="modal-footer" style="text-align: left;padding:2px; height:29px;" >
		<!--这里是查询条件   -->
		<span class="">采购日期</span><input type="text" id="beginDate" value="${startDate}" style="height:25px; width:100px;"/><span class="">至</span><input type="text" id="endDate" value="${endDate}" value="" style="height:25px; width:100px;" name="it" class="it date-pick"/>
		<span class="">供应商名称</span><input type="text" id="scmcocName" value="${scmcocName}"  style="height:25px; width:100px;" class=""/>
		<span class="">采购单号</span><input type="text" id="purchaseNo"  value="${purchaseNo}" style="height:25px; width:100px;" class=""/>
		<input class="btn btn-small btn-danger" data-toggle="button" type="button" value="查询" id="btnQuery" style="height:25px; border: 2px; width:45px; margin-top:-10px;"/>
	</div>
	
	<div >
		<div class="span12" style=" margin-left: 2px; padding-right:10px;" id="parant_div" >
			<!--PAGE CONTENT BEGINS-->

			<div class="row-fluid" >
				<div style="overflow-x: auto;">
					<table id="sample-table-1" class="table table-striped table-bordered table-hover"  style=" font-size: 12px;overflow: scroll;">
						<thead>
							<tr>
								<th class="center" style="width:30px;"><input id="checkAll" type="checkbox"/></th>
								<th class="center" width="50px;">流水号</th>
								<th class="center" width="140px;" >供应商名称</th>
								<th class="center" width="60px;">采购单号</th>
								<th class="center" width="80px;">总金额</th>
								<th class="center" width="80px;">采购日期</th>
								<th class="center" width="80px;">是否完结</th>
								<th class="center" width="100px;">备注</th>
							</tr>
						</thead>
						<tbody id="dataBody">
						<c:forEach var="storage" varStatus="index" step="1" items="${storageList}">
						</c:forEach>
						</tbody>
					</table>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<!-- 分页 -->
				<div class="pagination pull-right no-margin" style="width: 500px;">
					<ul>
						<li class="next"><a>当前页【${currIndex}/${pageNums}】</a>
						</li>
						<c:if test="${currIndex!=1}">
						<li class="next"><a href="javascript: gotoPage( 1, ${pageNums} )">首页</a>
						</li>
							<li class="next"><a href="javascript: gotoPage( ${currIndex - 1}, ${pageNums} )">上一页</a>
							</li>
						</c:if>
						<c:if test="${currIndex!=pageNums}">
							<li class="next"><a href="javascript: gotoPage( ${currIndex + 1}, ${pageNums} )">下一页 </a>
							</li>
						<li class="next"><a href="javascript: gotoPage( ${pageNums}, ${pageNums}) ">尾页 </a><label  class="pull-right no-margin" style="line-height: 30px;">直接到第</label>
						</li>
						</c:if>
						
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
	
	
  </body>
</html>
