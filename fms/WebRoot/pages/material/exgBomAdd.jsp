<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/public/public.js"></script>
<style type="text/css">
	
</style>

<script type="text/javascript">
	
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
	<span style="font-size: 14px; font-weight: bold;margin-left:5px; padding:3px 3px 0px 3px; border:solid 1px gray; border-bottom: 0px;">物料清单-成品</span>
</div>
<div class="modal-footer" style="text-align: left;padding:2px; height:29px;" >
	<span class="">成品编号</span><input type="text" id="hsCode" value="${hsCode}" style="height:25px;width:100px;" class="" /> 
	<span class="">成品名称</span><input type="text" id="hsName" value="${hsName}" style="height:25px;width:100px;" class="" /> 
	<span class="">成品规格</span><input type="text" id="hsCode" value="${hsModel}" style="height:25px;width:100px;" class="" /> 
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
								<th class="center">物料编码</th>
								<th class="center">物料名称</th>
								<th class="center">物料规格</th>
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
			
	<div class="modal-footer" style="text-align: left;padding:2px; height:29px;" >
		<input class="btn btn-small btn-danger" data-toggle="button" type="button" value="确定" id="btnSure" style="height:25px; border: 2px; width:45px;" />
		<input class="btn btn-small btn-danger" data-toggle="button" type="button" value="关闭" id="btnClose" style="height:25px; border: 2px; width:45px;" />
	</div>