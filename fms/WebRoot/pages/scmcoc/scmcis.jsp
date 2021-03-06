<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page-header position-relative"
	style="margin-bottom: 0px; padding-bottom: 2px;">
	<h5 style="margin-top: 0px; margin-bottom: 0px;">基础资料＞＞客户管理</h5>
</div>
<div class="modal-footer" style="text-align: left;">
	<span class="">客户名称</span><input type="text" id="search"
		value="${searchStr}" style="height:25px;" class="" /> <input
		class="btn btn-small btn-danger" data-toggle="button" type="button"
		value="查询" onclick="gotoPage(1,1)"
		style="height:25px; border: 2px; width:45px; margin-top:-10px;" />
</div>
<div class="row-fluid" >
		<div class="span12">
			<!--PAGE CONTENT BEGINS-->
			<div class="row-fluid">
				<div class="span12">
					<table id="sample-table-1" class="table table-striped table-bordered table-hover"  style=" font-size: 12px;">
						<thead>
							<tr align="center">
								<th class="center" style="width:30px;">选择</th>
								<th class="center">序号</th>
								<th class="center">编号</th>
								<th class="center">客户名称</th>
								<th class="hidden-480 center">客户联系电话</th>
								<th class="hidden-phone center">客户网络联系方式</th>
								<th class="hidden-480 center">客户联系人</th>
								<th class="hidden-480 center">客户地址</th>
								<th class="hidden-480 center">结算方式</th>
								<th class="hidden-480 center">约定结算日期</th>
								<th class="hidden-480 center">备注</th>
								<th class="center">操作</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach var="scmcoc" items="${scmcocs}" varStatus="index" step="1">
								<tr>
									<td class="center" style="width:30px;" ><!-- .checkbox input[type="checkbox"] -->
										<input type="checkbox" value="${scmcoc.id}" name="sid" style="width:30px;"/>
									</td>

									<td class="center"><a href="#">${index.index+1}</a>
									</td>
									<td class="center">${scmcoc.code}</td>
									<td class="center">${scmcoc.name}</td>
									<td class="hidden-480 center">${scmcoc.linkPhone}　</td>
									<td class="hidden-480 center">${scmcoc.networkLink}　</td>
									<td class="hidden-480 center">${scmcoc.linkMan}　</td>
									<td class="hidden-480 center">${scmcoc.address}　</td>
									<td class="hidden-480 center">${scmcoc.settlement.name}　</td>
									<td class="hidden-480 center">每月${scmcoc.endDate}日</td>
									<td class="hidden-480 center">${scmcoc.note}　</td>
									<td class="center">
										<a href="javascript:void(0);" onclick="toedit('${scmcoc.id}')">修改</a>｜
										<a href="javascript:void(0);" onclick="delSingleScmcoc('${scmcoc.id}','true')">删除</a>
									</td>
								</tr>
							</c:forEach>
					</table>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" onclick="toedit('');">新增</button>
				<button class="btn btn-small btn-danger pull-left" data-dismiss="modal" onclick="delMoreScmcoc('true')">
					批量删除
				</button>
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" id="inputExcelsBut">Excel导入</button>
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
	<script type="text/javascript">
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
		var likeStr = $("#search").val(); 
		// 拼接URL
		var url = "${pageContext.request.contextPath}/scmcoc_findAllScmcoc.action?currIndex=" + pageNum + "&maxIndex="+ pageSize + "&searchStr="+parse(likeStr)+"&isCustom=true";
		// 在本窗口中显示指定URL的页面
		toMain(url);
	}
	
	function parse(str){
		return encodeURI(encodeURI(str));  
	}
	
	function toedit(id){
	if(id==''){
		var url = "${pageContext.request.contextPath}/scmcoc_findScmcocById.action?isCustom=true";
	}else{
		var url = "${pageContext.request.contextPath}/scmcoc_findScmcocById.action?ids="+id+"&isCustom=true";
	}
		toMain(url);
	}
	$("#inputExcelsBut").click(function(){
		var url = Global+"/scmcoc_toExcels.action?isScmcoc=false";
	  	toMain(url);
	});
	
	
	
	//function showImport(){
	  
 // 	}
</script>