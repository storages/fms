<!-- 引用dialog页面 -->
<jsp:include page="/pages/templet/dialog.jsp"></jsp:include>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/controlUI/editTable.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/public/public.js"></script>
<style type="text/css">
	.edit{
		padding:0px;
		line-height: 36px;
	}
</style>
<script type="text/javascript">
	var verNo;
	var changeLitener;
	//导入Bom原料按钮
	$('#btnImport').click(function(){
		var verNo = $("#form-field-select-1").find("option:selected").text();
		if("BOM版本号"==verNo){
			alert("请选择版本号!");
		}else{
			//var url = "${pageContext.request.contextPath}/bom_importData.action?hid="+$("#hid").val()+"&verNo="+verNo;
			toMain("${pageContext.request.contextPath}/bom_showImport.action?hid="+$("#hid").val()+"&verNo="+verNo);
		} 
	});
	//新增BOM版本
	$("#btnAddBomVersion").click(function(){
		var url = "${pageContext.request.contextPath}/bom_addVersion.action?ids="+$("#hid").val();
		toMain(url);
	}); 
	
	//删除BOM版本
	$("#btnDelBomVersion").click(function(){
		var verNo = $("#form-field-select-1").find("option:selected").text();
		if("BOM版本号"!=verNo){
			if(confirm("删除该版本号，会将该版本号下所对应的物料信息全部删除，是否继续？")){
				var url = "${pageContext.request.contextPath}/bom_delVersion.action?ids="+$("#hid").val()+"&verNo="+verNo;
				toMain(url);
			}
		}else{
			alert("请选择版本号!");
		}
	});
	
	//新增BOM物料
	$("#btnAddImg").click(function(){
		var verNo = $("#form-field-select-1").find("option:selected").text();
		if("BOM版本号"!=verNo){
			if(confirm("当前选择版本号为"+ verNo +"，是否继续？")){
				showDialog('选择BOM物料','dgAddBomImg.jsp');
				$("#dg_content").load("${pageContext.request.contextPath}/bom_findMaterial.action");
			}
		}else{
			alert("请选择版本号!");
		}
	});
	
	//对话框确定按钮
	$("#btnSure").click(function(){
		var paramstr = "";      
		 $('input[name="mid"]:checked').each(function(){//遍历每一个名字为materid的复选框，其中选中的执行函数      
		 	paramstr+=$(this).val()+"/";//将选中的值组装成一个以'/'分割的字符串
		 }); 
		 if(paramstr==""){
		 	alert("请选择物料");
		 	return;
		 }
		 var verNo = $("#form-field-select-1").find("option:selected").text();
		 toMain("${pageContext.request.contextPath}/bom_findMaterialByIds.action?ids="+paramstr+"&hid="+$("#hid").val()+"&verNo="+verNo);
		});
	
	//bom版本号下拉框选择后
	 $("#form-field-select-1").change(function(){
		var verNo = $("#form-field-select-1").find("option:selected").text();
		var url = "${pageContext.request.contextPath}/bom_detailList.action?ids="+$("#hid").val()+"&verNo="+verNo;
		toMain(url);
	});
	
	//修改
	function edit(obj,arr){
		showTableEdit(obj,arr);
	}
	
	//保存
	$("#btnSave").click(function(){
		//closeAllEdit("sample-table-1","7,8,9,10");
		var jsonstr = getModifyData();
		if(jsonstr==""){
			alert("没有数据保存!");
			return;
		}
		var dataArr = JSON.parse(jsonstr).toString().split(',');
		//验证单耗是否为数字
		if(isNaN(dataArr[1])){
   			alert("单耗只能是数字");
		}
		//验证损耗是否为数字
		if(isNaN(dataArr[2])){
			alert("损耗只能是数字");
		}
		var url = "${pageContext.request.contextPath}/bom_saveBomImg.action?jsonstr="+jsonstr;
		$.ajax({
			type: "POST",
		     url:url,
		     async: false,
		     cache: false,
		     success:function(data){
			     var result=jQuery.parseJSON(data);
			     if(result.success){
			     	var verNo = $("#form-field-select-1").find("option:selected").text();
					url = "${pageContext.request.contextPath}/bom_detailList.action?ids="+$("#hid").val()+"&verNo="+verNo;
			     	toMain(url);
			     }else{
			     	alert(result.msg);
			     }
		 	},error:function(){
		        alert("程序异常，请重新启动程序！");
		    }
		});
	});
	
</script>


<!-- 隐藏域     记录表头id，也就是成品的id-->
<input type="hidden" value="${hid}" id="hid"/>
<!-- 页面布局BEGIN -->
<div class="page-header position-relative" style="margin-bottom: 0px; height:10px;margin-top:0px;line-height: 25px;">
	<span style="font-size: 14px; font-weight: bold;margin-left:5px; padding:3px 3px 0px 3px; border:solid 1px gray; border-bottom: 0px;">成品对应的料件BOM表</span>
</div>

<div class="modal-footer" style="text-align: left;padding:2px; height:29px;" >
	<strong style="font-size: 14px;">BOM版本</strong>
	<select id="form-field-select-1" style="width:120px;">
		<option value="-1" >BOM版本号</option>
		<c:forEach var="no" items="${verList}">
			<c:if test="${no.versionNo==verNo}">
				<option value="${no.versionNo}" selected="selected">${no.versionNo}</option>
			</c:if>
			<c:if test="${no.versionNo!=verNo}">
				<option value="${no.versionNo}">${no.versionNo}</option>
			</c:if>
		</c:forEach>
    </select>
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
								<th class="center">原料料号</th>
								<th class="center">原料名称</th>
								<th class="center">原料规格</th>
								<th class="center">批次号</th>
								<th class="center">单耗</th>
								<th class="center">损耗</th>
								<th class="center">单项用量</th>
								<th class="center">备注</th>
								<th class="hidden-480 center" style="width:250px;">操作</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach var="bom" items="${imgList}" varStatus="index" step="1">
								<tr>
									<td class="center" style="width:30px;" ><!-- .checkbox input[type="checkbox"] -->
										<input type="checkbox" value="${bom.id}" name="sid" style="width:30px;"/>
									</td>

									<td class="center"><a href="#">${index.index+1}</a>
									</td>
									<td class="center">${bom.material.hsCode}</td>
									<td class="center">${bom.material.hsName}</td>
									<td class="center">${bom.material.model}</td>
									<td class="center">${bom.material.batchNO}</td>
									<td class="hidden-480 center" style="padding:0px; line-height: 36px;">${bom.unitConsume}　</td>
									<td class="hidden-480 center" style="padding:0px; line-height: 36px;">${bom.wastRate}　</td>
									<td class="hidden-480 center " style="padding:0px; line-height: 36px;">${bom.unitDosage}　</td>
									<td class="hidden-480 center ">${bom.note}　</td>
									<td class="center">
										<a href="javascript:void(0);" onclick="edit(this,'7,8,10')">修改</a>｜
										<a href="javascript:void(0);" onclick="delData('${bom.id}','BomImg')">删除</a>
									</td>
								</tr>
							</c:forEach>
					</table>
				</div>
			</div>
		</div>
		<!--PAGE CONTENT ENDS-->
	</div>
	<div class="modal-footer" style="text-align: left; padding: 0px 2px 2px; height: 34px;">
	    <div class="modal-footer" style="padding-top: 0px; padding-bottom: 0px;">
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" id="btnAddBomVersion">新增版本</button>
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" id="btnDelBomVersion">删除版本</button>
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" id="btnAddImg">新增原料</button>
				<button class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" id="btnSave">保存原料</button>
				<button class="btn btn-small btn-danger pull-left" data-dismiss="modal" onclick="delData('','BomImg')">批量删除原料</button>
				<button class="btn btn-small btn-danger pull-left" data-dismiss="modal" id="btnImport">导入BOM原料</button>
				<!-- <button class="btn btn-small btn-danger pull-left" data-dismiss="modal" id="btnOn">启用原料</button> -->
	    </div>
   </div>
<!-- 页面布局END -->
