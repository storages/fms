<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/public/public.js"></script>


<div class="page-header position-relative" style="margin-bottom: 0px; height:10px;margin-top:0px;line-height: 25px;">
	<span style="font-size: 14px; font-weight: bold;margin-left:5px; padding:3px 3px 0px 3px; border:solid 1px gray; border-bottom: 0px;">入库管理</span>
</div>
<div class="modal-footer" style="text-align: left;padding:2px; height:29px;" >
	<span class="">物料编号</span><input type="text" id="hsCode" value="${hsCode}" style="height:25px;width:100px;" class="" /> 
	<span class="">物料名称</span><input type="text" id="hsName" value="${hsName}" style="height:25px;width:100px;" class="" /> 
	<input class="btn btn-small btn-danger" data-toggle="button" type="button" value="查询" id="btnQuery" style="height:25px; border: 2px; width:45px; margin-top:-10px;" />
</div>

<div class="row-fluid" >
		<div class="span12">
			<!--PAGE CONTENT BEGINS-->
			<div class="row-fluid">
				<div class="span12">
					<table id="sample-table-1" class="table table-striped table-bordered table-hover"  style=" font-size: 12px;">
						<thead>
							<tr align="center">
								<th class="center" style="width:30px;"><input type="checkbox" title="全选" id="checkAll"/></th>
								<th class="center">序号</th>
								<th class="center">状态</th>
								<th class="center">入库单号</th>
								<th class="center">采购单号</th>
								<th class="center">订单号</th>
								<th class="center">物料名称</th>
								<th class="center">物料规格</th>
								<th class="center">批次号</th>
								<th class="center">数量</th>
								<th class="center">单位</th>
								<th class="center">仓库名称</th>
								<th class="center">入库人</th>
								<th class="center">入库日期</th>
								<th class="center" style="width:250px;">操作</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach var="instorage" items="${InStorages}" varStatus="index" step="1">
							</c:forEach>
					</table>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button"id="add">新增</button>
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button"id="edit">修改</button>
				<button class="btn btn-small btn-danger pull-left" data-dismiss="modal" onclick="delData('','InStorage')">批量删除</button>
				<button class="btn btn-small btn-danger pull-left" data-dismiss="modal" id="save">保存</button>
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" id="btnImport">Excel导入</button>
				<!-- 分页 -->
				<div class="pagination pull-right no-margin" style="width: 700px;">
					<ul>
						<li class="next"><a>当前页【${currIndex}/${pageNums}】</a>
						</li>
						<li class="next"><a href="javascript: gotoPage( 1, ${pageNums} )">首页</a>
						</li>
						<c:if test="${currIndex!=1}">
							<li class="next"><a href="javascript: gotoPage( ${currIndex - 1}, ${pageNums} )">上一页</a>
							</li>
						</c:if>
						<c:if test="${currIndex!=pageNums}">
							<li class="next"><a href="javascript: gotoPage( ${currIndex + 1}, ${pageNums} )">下一页 </a>
							</li>
						</c:if>
						
						<li class="next"><a href="javascript: gotoPage( ${pageNums}, ${pageNums}) ">尾页 </a><label  class="pull-right no-margin" style="line-height: 30px;">直接到第</label>
						</li>
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
		<!--PAGE CONTENT ENDS-->
	</div>