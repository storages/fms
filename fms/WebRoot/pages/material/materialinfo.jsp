<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/public/public.js"></script>
     <div class="page-header position-relative" style="margin-bottom: 0px;">
		<h5>物料＞＞物料统计</h5>
	</div>
	<div class="modal-footer" style="text-align: left;">
		<span class="">物料名称</span><input type="text" id="search" value="${searchStr}" style="height:25px;" class=""/>
		<input class="btn btn-small btn-danger" data-toggle="button" type="button" value="查询" onclick="search()" style="height:25px; border: 2px; width:45px; margin-top:-10px;"/>
		 <c:if test="${imgexgflag=='I'}">
		 	<input type="radio" name="materialType" value="I" style="margin-top: -5px;" onchange="changematerialtype('I')" checked="checked"/>&nbsp;原料　
		 	<input type="radio" name="materialType" value="E" style="margin-top: -5px;" onchange="changematerialtype('E')"/>&nbsp;成品
		 </c:if>
		 <c:if test="${imgexgflag=='E'}">
		 	<input type="radio" name="materialType" style="margin-top: -5px;" onchange="changematerialtype('I')" />&nbsp;原料　
		 	<input type="radio" name="materialType" style="margin-top: -5px;" onchange="changematerialtype('E')" checked="checked"/>&nbsp;成品
		 </c:if>
	</div>
	<div class="row-fluid" >
		<div class="span12">
			<!--PAGE CONTENT BEGINS-->

			<div class="row-fluid">
				<div class="span12">
					<table id="sample-table-1" class="table table-striped table-bordered table-hover"  style=" font-size: 12px;">
						<thead>
							<tr>
								<th class="center" style="width:30px;">选择</th>
								<th class="center">行号</th>
								<th class="center">物料编码</th>
								<th class="center">物料名称</th>
								<th class="center">颜色</th>
								<th class="center">物料标记</th>
								<th class="center">物料类别</th>
								<th class="center">计量单位</th>
								<th class="center">数量</th>
								<th class="center">批次号</th>
								<th class="center">最低库存</th>
								<th class="center">备注</th>
								<th class="center">操作</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="info" varStatus="index" step="1" items="${materials}">
							<tr>
								<td class="center" style="width:30px;" ><!-- .checkbox input[type="checkbox"] -->
									<input type="checkbox" value="${info.id}" name="sid" style="width:30px;"/>
								</td>
									<td class="center">${index.index+1}</td>
									<td class="center">${info.hsCode}　</td>
									<td class="center">${info.hsName}　</td>
									<td class="center">${info.color}　</td>
									
									<c:if test="${info.imgExgFlag=='I'}">
										<td class="center">原料</td>
									</c:if>
									<c:if test="${info.imgExgFlag=='E'}">
										<td class="center">成品</td>
									</c:if>
									<td class="center">${info.materialType.typeName}　</td>
									<td class="center">${info.unit.name}　</td>
									<td class="center">${info.qty}　</td>
									<td class="center">${info.batchNO}　</td>
									<td class="center">${info.lowerQty}　</td>
									<td class="center">${info.note}　</td>
									<td class="center">
										<a href="javascript:toMain('${pageContext.request.contextPath}/materInfo_findMaterialById.action?ids=${info.id}')">修改</a>｜
										<a href="javascript:void(0);" onclick="delSingleMaterial('${info.id}','${info.imgExgFlag}')">删除</a>
									</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" onclick="toMain('${pageContext.request.contextPath}/materInfo_add.action');">新增</button>
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
		var url = "${pageContext.request.contextPath}/materInfo_findAllMaterial.action?currIndex=" + pageNum + "&maxIndex="+ pageSize + "&searchStr="+parse(likeStr);
		// 在本窗口中显示指定URL的页面
		toMain(url);
	}
	
	function parse(str){
		return encodeURI(encodeURI(str));  
	}
	
	function toedit(id){
	alert(id);
		var url = "${pageContext.request.contextPath}/materInfo_findMaterialById.action?ids="+id;
		toMain("${pageContext.request.contextPath}/materInfo_findMaterialById.action?ids=");
	}
	function showImport(){
	  	var url = "${pageContext.request.contextPath}/pages/import/unitimport.jsp";
	  	toMain(url);
  	}
  	function changematerialtype(flag){
  	var str = $('#search').val();
  		var url = "${pageContext.request.contextPath}/materInfo_findAllMaterial.action?imgExgFlag="+flag+"&searchStr="+parse(str);
  		toMain(url);
  	}
  	function search(){
  		var str = $('#search').val();
  		var flag = $('input:radio:checked').val();
  		var url = "${pageContext.request.contextPath}/materInfo_findAllMaterial.action?imgExgFlag="+flag+"&searchStr="+parse(str);
  		toMain(url);
  	}
  	
</script>
