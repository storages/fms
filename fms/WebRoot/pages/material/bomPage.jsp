<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/public/public.js"></script>
<script type="text/javascript">
	//列表全选功能
	$("#checkAll").click(function(){
		if($("#checkAll").attr("checked")){
			$("input[name='sid']").attr("checked",'true');//全选 
		}else{
			$("input[name='sid']").removeAttr("checked");//取消全选
		}
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
		var hsCode = $("#hsCode").val(); 
		var hsName = $("#hsName").val(); 
		var hsModel = $("#hsModel").val(); 
		// 拼接URL
		var url = "${pageContext.request.contextPath}/bom_findBomExg.action?currIndex=" + pageNum + "&maxIndex="+ pageSize + "&hsCode="+parse(hsCode)+"&hsName="+parse(hsName)+"&hsModel="+parse(hsModel);
		// 在本窗口中显示指定URL的页面
		toMain(url);
	}
	
	function parse(str){
		return encodeURI(encodeURI(str));  
	}
	
	//新增BOM成品
	$('#add').click(function(){
		var url="${pageContext.request.contextPath}/bom_addExgData.action";
		toMain(url);
	});
	
	//查询按钮
	$("#btnQuery").click(function(){
		var hsCode = $("#hsCode").val(); 
		var hsName = $("#hsName").val(); 
		var hsModel = $("#hsModel").val();
		var url = "${pageContext.request.contextPath}/bom_findBomExg.action?&hsCode="+parse(hsCode)+"&hsName="+parse(hsName)+"&hsModel="+parse(hsModel);
		toMain(url);
	});
</script>


<div class="page-header position-relative" style="margin-bottom: 0px; height:10px;margin-top:0px;line-height: 25px;">
	<span style="font-size: 14px; font-weight: bold;margin-left:5px; padding:3px 3px 0px 3px; border:solid 1px gray; border-bottom: 0px;">BOM表管理</span>
</div>
<div class="modal-footer" style="text-align: left;padding:2px; height:29px;" >
	<span class="">成品编号</span><input type="text" id="hsCode" value="${hsCode}" style="height:25px;width:100px;" class="" /> 
	<span class="">成品名称</span><input type="text" id="hsName" value="${hsName}" style="height:25px;width:100px;" class="" /> 
	<span class="">成品规格</span><input type="text" id="hsModel" value="${hsModel}" style="height:25px;width:100px;" class="" /> 
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
								<th class="center">成品料号</th>
								<th class="center">成品名称</th>
								<th class="center">成品规格</th>
								<th class="hidden-480 center">计量单位</th>
								<th class="hidden-480 center" style="width:250px;">操作</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach var="bom" items="${bomExgs}" varStatus="index" step="1">
								<tr>
									<td class="center" style="width:30px;" ><!-- .checkbox input[type="checkbox"] -->
										<input type="checkbox" value="${bom.id}" name="sid" style="width:30px;"/>
									</td>

									<td class="center"><a href="#">${index.index+1}</a>
									</td>
									<td class="center">${bom.material.hsCode}</td>
									<td class="center">${bom.material.hsName}</td>
									<td class="center">${bom.material.model}</td>
									<td class="hidden-480 center">${bom.material.unit.name}　</td>
									<td class="center">
										<a href="javascript:void(0);" onclick="toedit('${bom.id}')">详细</a>｜
										<a href="javascript:void(0);" onclick="delData('${bom.id}','BomExg')">删除</a>
									</td>
								</tr>
							</c:forEach>
					</table>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button"id="add">新增</button>
				<button class="btn btn-small btn-danger pull-left" data-dismiss="modal" onclick="delData('','BomExg')">
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
	