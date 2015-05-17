<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/public/public.js"></script>
<style type="text/css">
	
</style>

<script type="text/javascript">
	function gototag(pageSize){
		var n = $("#gonum option:selected").val();
		gotoPage(n, pageSize);
	}
	//上一页面
	$("#goback").click(function(){
		toMain("${pageContext.request.contextPath}/bom_findBomExg.action");
	});
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
		var url = "${pageContext.request.contextPath}/bom_addExgData.action?currIndex=" + pageNum + "&maxIndex="+ pageSize + "&hsCode="+parse(hsCode)+"&hsName="+parse(hsName)+"&hsModel="+parse(hsModel);
		// 在本窗口中显示指定URL的页面
		toMain(url);
	}
	
	function parse(str){
		return encodeURI(encodeURI(str));  
	}
	//确定按钮
	$("#btnSure").click(function(){
		var str = "";
		$('input[name="sid"]:checked').each(function(){ 
    		str+=$(this).val()+"/";
  		});
  		if(str==""){
  			alert('请勾选成品信息');
  		}else{
  			var url = "${pageContext.request.contextPath}/bom_saveBomExg.action?ids="+str;
		     $.ajax({
				     type: "POST",
				     url:url,
				     async: false,
				     cache: false,
				     success:function(data){
						 var result=jQuery.parseJSON(data);
						 if(result.success){
						 	var url = "${pageContext.request.contextPath}/bom_findBomExg.action";
							toMain(url);
						 }else{
						 	alert(result.msg);
						 }
					 },error:function(){
					    alert("程序异常，请重新启动程序！");
					 }
			});
  		}
	});
	$("#btnClose").click(function(){
		var url = "${pageContext.request.contextPath}/bom_findBomExg.action";
		toMain(url);
	});
</script>
<div class="page-header position-relative" style="margin: 0px; height:10px;line-height: 25px;">
	<h5 style="margin: 0px;">物料管理>><a href="javascript:void(0);"  id="goback">物料BOM表管理</a>>>(新增)物料清单-成品</h5>
</div>
<div class="modal-footer" style="text-align: left;padding:2px; height:29px;" >
	<span class="">成品编码</span><input type="text" id="hsCode" value="${hsCode}" style="height:25px;width:100px;" class="" /> 
	<span class="">成品名称</span><input type="text" id="hsName" value="${hsName}" style="height:25px;width:100px;" class="" /> 
	<span class="">成品规格</span><input type="text" id="hsModel" value="${hsModel}" style="height:25px;width:100px;" class="" /> 
	<input class="btn btn-small btn-danger" data-toggle="button" type="button" value="查询" onclick="gotoPage(1,1)"
		style="height:25px; border: 2px; width:45px; margin-top:-10px;" />
</div>

<div class="row-fluid">
				<div class="span12">
					<table id="sample-table-1" class="table table-striped table-bordered table-hover"  style=" font-size: 12px;">
						<thead>
							<tr>
								<th class="center" style="width:10px;"><input type="checkbox" title="全选" id="checkAll"/></th>
								<th class="center">行号</th>
								<th class="center">物料标记</th>
								<th class="center">成品编码</th>
								<th class="center">成品名称</th>
								<th class="center">成品规格</th>
								<th class="center">批次号</th>
								<th class="center">颜色</th>
								<th class="center">物料类别</th>
								<th class="center">计量单位</th>
								<th class="center">备注</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="exg" varStatus="index" step="1" items="${exgList}">
							<tr>
								<td class="center" style="width:30px;" ><!-- .checkbox input[type="checkbox"] -->
									<input type="checkbox" value="${exg.id}" name="sid" style="width:30px;"/>
								</td>
									<td class="center">${index.index+1}</td>
									<c:if test="${exg.imgExgFlag=='I'}">
										<td class="center">原料</td>
									</c:if>
									<c:if test="${exg.imgExgFlag=='E'}">
										<td class="center">成品</td>
									</c:if>
									<td class="center">${exg.hsCode}　</td>
									<td class="center">${exg.hsName}　</td>
									<td class="center">${exg.model}　</td>
									<td class="center">${exg.batchNO}　</td>
									<td class="center">${exg.color}　</td>
									<td class="center">${exg.materialType.typeName}　</td>
									<td class="center">${exg.unit.name}　</td>
									<td class="center">${exg.note}　</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			
	<!-- <div class="modal-footer" style="text-align: left;padding:2px; height:29px;" > -->
	<div class="modal-footer">
		<input class="btn btn-small btn-danger" data-toggle="button" type="button" value="确定" id="btnSure" style="height:25px; border: 2px; width:45px;" />
		<input class="btn btn-small btn-danger" data-toggle="button" type="button" value="关闭" id="btnClose" style="height:25px; border: 2px; width:45px;" />
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