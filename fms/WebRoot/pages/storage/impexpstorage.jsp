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
  		$("#div_scorll").width($("#tomain").width()+1000);
  		$("#parant_div").width($("#tomain").width());
  		
  		//查询按钮
  		$("#btnQuery").bind("click",function(){
  			alert("查询");
  		});
  	});
  	
  	function editData(id){
  		toMain("${pageContext.request.contextPath}/storage_editStorage.action?id="+ id +"&inOrOutFlag=in");
  	}
  </script>

     <div class="page-header position-relative" style="margin-bottom: 0px; height:10px;margin-top:0px;line-height: 25px;">
		<h5>物料＞＞入库</h5>
	</div>
	<div class="modal-footer" style="text-align: left;padding:2px; height:29px;" >
		<span class="">入库日期</span><input type="text" id="beginDate" value="${startDate}" style="height:25px; width:100px;" readonly="readonly" /><span class="">至</span><input type="text" id="endDate" value="" style="height:25px; width:100px;" name="it" class="it date-pick"/>
		<span class="">供应商名称</span><input type="text" id="scmcocName" value="${endDate}"  style="height:25px; width:100px;" class=""/>
		<span class="">物料名称</span><input type="text" id="search" value="${hsName}" style="height:25px; width:100px;" class=""/>
		<input class="btn btn-small btn-danger" data-toggle="button" type="button" value="查询" id="btnQuery" style="height:25px; border: 2px; width:45px; margin-top:-10px;"/>
		<c:if test="${imgexgflag=='I'}">
		 	<input type="radio" name="materialType" value="I" style="margin-top: -5px;" onchange="changematerialtype('I')" value="I" checked="checked"/>&nbsp;原料　
		 	<input type="radio" name="materialType" value="E" style="margin-top: -5px;" onchange="changematerialtype('E')"  value="E"/>&nbsp;成品
		 </c:if>
		 <c:if test="${imgexgflag=='E'}">
		 	<input type="radio" name="materialType" style="margin-top: -5px;" onchange="changematerialtype('I')" value="I"/>&nbsp;原料　
		 	<input type="radio" name="materialType" style="margin-top: -5px;" onchange="changematerialtype('E')" value="E" checked="checked"/>&nbsp;成品
		 </c:if>
	</div>
	<div >
		<div class="span12" style=" margin-left: 2px; padding-right:10px;" id="parant_div" >
			<!--PAGE CONTENT BEGINS-->

			<div class="row-fluid" >
				<div style="overflow-x: auto;">
					 <div id="div_scorll">
					<table id="sample-table-1" class="table table-striped table-bordered table-hover"  style=" font-size: 12px;overflow: scroll;">
						<thead>
							<tr>
								<th class="center" style="width:30px;"><input type="checkbox" title="全选" id="checkAll"/></th>
								<th class="center" width="50px;">流水号</th>
								<th class="center" width="40px;" >状态</th>
								<th class="center" width="60px;">入库单号</th>
								<th class="center" width="80px;">订单号</th>
								<th class="center" width="80px;">采购单号</th>
								<th class="center" width="80px;">物料编码</th>
								<th class="center" width="150px;">物料名称</th>
								<th class="center" width="120px;">规格型号</th>
								<th class="center" width="60px;">颜色</th>
								<th class="center" width="90px;">数量</th>
								<th class="center" width="40px;">单位</th>
								<th class="center" width="80px;">批次号</th>
								<th class="center" width="60px;">数量/(包)</th>
								<th class="center" width="40px;">件数</th>
								<th class="center" width="90px;">供应商名称</th>
								<th class="center" width="60px;">仓库名称</th>
								<th class="center" width="60px;">物料标志</th>
								<th class="center" width="70px;">入库类型</th>
								<th class="center" width="60px;">物料类型</th>
								<th class="center" width="60px;">入库人</th>
								<th class="center" width="80px;">入库日期</th>
								<th class="center" width="80px;">备注</th>
								<th class="center" width="60px;">操作</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="storage" varStatus="index" step="1" items="${storageList}">
							<tr <c:if test="${ storage.useFlag==1}">style="color: red;" </c:if>>
									<td class="center" style="width:30px;" ><input type="checkbox" value="${bom.id}" name="sid" style="width:30px;"/></td>
									<td class="center"><a href="#">${index.index+1}</a></td>
									<c:if  test="${ storage.useFlag==0}">
										<td class="center">启用</td>
									</c:if>
									<c:if  test="${ storage.useFlag==1}">
										<td class="center">停用</td>
									</c:if>
									<td class="center">${storage.inStorageNo}&nbsp;</td>
									<td class="center">${storage.orderNo}&nbsp;</td>
									<td class="center">${storage.purchaseNo}&nbsp;</td>
									<td class="center">${storage.material.hsCode}&nbsp;</td>
									<td class="center">${storage.material.hsName}&nbsp;</td>
									<td class="center">${storage.material.model}&nbsp;</td>
									<td class="center">${storage.material.color}&nbsp;</td>
									<td class="center">${storage.inQty}&nbsp;</td>
									<td class="center">${storage.material.unit.name}&nbsp;</td>
									<td class="center">${storage.material.batchNO}&nbsp;</td>
									<td class="center">${storage.specQty}&nbsp;</td>
									<td class="center">${storage.pkgs}&nbsp;</td>
									<td class="center">${storage.scmcoc.name}&nbsp;</td>
									<td class="center">${storage.stock.name}&nbsp;</td>
									<c:if test="${storage.imgExgFlag=='I' }">
										<td class="hidden-480 center">原料&nbsp;</td>
									</c:if>
									<c:if test="${storage.imgExgFlag=='E' }">
										<td class="hidden-480 center">成品&nbsp;</td>
									</c:if>
										<td class="hidden-480 center">${storage.impFlag}&nbsp;</td>
										<td class="hidden-480 center">${storage.material.materialType.typeName}&nbsp;</td>
										<td class="hidden-480 center">${storage.handling}&nbsp;</td>
										<td class="hidden-480 center"><fmt:formatDate value="${storage.impDate}" pattern="yyyy-MM-dd"/>&nbsp;</td>
										<td class="hidden-480 center">${storage.note}&nbsp;</td>
									<td class="center">
										<a href="javascript:void(0);"  onclick="editData('${storage.id}')">修改</a>&nbsp;|
										<a href="javascript:void(0);" >删除</a>
									</td>
								</tr>
						</c:forEach>
						</tbody>
					</table>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" onclick="toMain('${pageContext.request.contextPath}/storage_editStorage.action?impExpFlag=I&inOrOutFlag=in');">新增</button>
				<button class="btn btn-small btn-danger pull-left" data-dismiss="modal" onclick="delMoreMater('I')">
					批量删除
				</button>
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" onclick="showImport()">Excel导入</button>
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
	</div>
  </body>
