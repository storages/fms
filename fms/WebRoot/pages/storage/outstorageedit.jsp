<%@page import="org.apache.commons.lang.StringUtils"%>
<jsp:include page="/pages/templet/dialog.jsp"></jsp:include>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<link rel="stylesheet" href="${pageContext.request.contextPath}/css/datepicker/jquery-ui-1.8.16.custom.css" type="text/css"></link>


<script type="text/javascript" src="${pageContext.request.contextPath}/js/utils/chinese-of-spell.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datepicker/jquery.ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datepicker/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datepicker/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/public/public.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/dialog/dialogCss.css" type="text/css"></link>

<style type="text/css">

</style>

<script type="text/javascript">

function parse(str){
	return encodeURI(encodeURI(str));  
}
	
	$(function(){
		$(".number").keyup(function(){    
            $(this).val($(this).val().replace(/[^0-9.]/g,''));    
        }).bind("paste",function(){  //CTR+V事件处理    
            $(this).val($(this).val().replace(/[^0-9.]/g,''));     
        }).css("ime-mode", "disabled"); //CSS设置输入法不可用  
		
		initUi();
		//物料标志改变事件
		$("#imgexgflag").change(function(){
			$("#isClear").val("");
			initUi();
		});
		//出库类型改变事件
		$("#impExpType").change(function(){
			var imgExg = $("#imgexgflag").val();
			var value = $(this).find("option:selected").text();
			$("#isClear").val("");
			initUi();
			if("原料其它出库"==value && "I"==imgExg){
				$("#purchNo").html("");
			}else if("原料其它出库"!=value && "I"==imgExg){
				$("#purchNo").html("*");
			}
			if("成品其它出库"==value && "E"==imgExg){
				$("#orderNo").html("");
			}else if("成品其它出库"!=value && "E"==imgExg){
				$("#orderNo").html("*");
			}else if("原料生产领用"==value){
				$("#purchNo").html("");
			}
		});
		
		$("#imgexgflag").change(function(){
			$("#isClear").val("");
			var val = $("#imgexgflag").val();
			var inOrOutFlag = $("#inOrOutFlag").val();
			var url ="${pageContext.request.contextPath}/outstorage_loadImpExpType.action";
			$.ajax({
				type: "POST",
				url:url,
				data:{impExpFlag:val,inOrOutFlag:inOrOutFlag},
				async: false,
				cache: false,
				success:function(args){
					var result=jQuery.parseJSON(args);
					if(result.success){
						$("#impExpType").empty(); 
							var mylist = result.obj;
							var options = "";
							var str = "";
							 for(var i=0; i<mylist.length; i++){
								 if(i==1){
									 str = "selected='selected'";
									 options += "<option value='"+mylist[i].code+"' "+str+">"+ mylist[i].name +"</option>";
								 }else{
									options += "<option value='"+mylist[i].code+"'>"+ mylist[i].name +"</option>";
								 }
							}
							 $("#impExpType").html(options); 
					}
				}
			});
		});
		
		//上一页面
		$("#addgoback").click(function(){
			toMain("${pageContext.request.contextPath}/storage_findAllInStorage.action");
		});
		$("#editgoback").click(function(){
			toMain("${pageContext.request.contextPath}/storage_findAllInStorage.action");
		});
		
		//保存
		$("#btnAdd").bind("click",function(){
			var serialNo = $("#serialNo").val();
			var impExpType = $("#impExpType").val();
			var imgexgflag = $("#imgexgflag").val();
			var outStorageNo = $("#outStorageNo").val();
			var hsCode = $("#hsCode").val();
			var expQty = $("#expQty").val();
			var packQty = $("#packQty").val();
			var id = $("#flag").val();
			var imgExg = $("#imgexgflag").val();
			var value = $("#impExpType").find("option:selected").text();
			var purchNo=  ($("#tfPurchaseNo").val()==undefined||$("#tfPurchaseNo").val()=="")?"":$("#tfPurchaseNo").val();
			if("原料其它出库"!=value && "I"==imgExg){
				if(purchNo==""){
					alert("采购单号不能为空!");
					return;
				}
			}
			var orderNo;
			if("成品其它出库"!=value && "E"==imgExg){
				orderNo = $("#tfOrderNo").val();
				if(orderNo==""){
					alert("订单号不能为空!");
					return;
				}
			}
			if(serialNo==""){
				alert("流水号不能为空!");
				return;
			}
			if(impExpType==""){
				alert("出库类型不能为空!");
				return;
			}
			if(imgexgflag==""){
				alert("物料标志不能为空!");
				return;
			}
			if(outStorageNo==""){
				alert("出库单号不能为空!");
				return;
			}
			if(hsCode==""){
				alert("物料编码不能为空!");
				return;
			}
			
			if(expQty==""){
				alert("出库数量不能为空!");
				return;
			}
			if(checkNumber(expQty)){
				alert("出库数量只能填数字!");
				return;
			}
			/*if(packQty==""){
				alert("每件包装数量不能为空!");
				return;
			}*/
			if(checkNumber(packQty)){
				alert("每件包装数量只能填数字!");
				return;
			}
			
			//提交保存数据
			var scmcocName = $("#tfScmcoc").val();
			var hsName = $("#hsName").val();
			var hsModel = $("#hsModel").val();
			var unitName = $("#unitName").val();
			var stockId = $("#stockName").val();
			var pkgs = $("#pkgs").val();
			var note = $("#note").val();
			var orderNo = ($("#tfOrderNo").val()==undefined||$("#tfOrderNo").val()=="")?"":$("#tfOrderNo").val();
			var url = "${pageContext.request.contextPath}/storage_saveInStorage.action";
			var data = "serialNo="+serialNo+"&impFlag="+impExpType+"&imgExgFlag="+imgexgflag;
				   data+="&purchaseNo="+purchNo+"&outStorageNo="+outStorageNo+"&orderNo="+orderNo;
				   data+="&scmcocName="+parse(scmcocName)+"&hsCode="+hsCode+"&hsName="+parse(hsName);
				   data+="&model="+hsModel+"&unitName="+parse(unitName)+"&expQty="+expQty+"&packQty="+packQty;
				   data+="&pkgs="+pkgs+"&stockId="+stockId+"&note="+parse(note)+"&id="+id;
			$.ajax({
				url:url,
				type:"POST",
				data:data,
				async: false,
				cache: false,
				success:function(args){
					var result=jQuery.parseJSON(args);
					if(result.success){
						toMain("${pageContext.request.contextPath}/storage_findAllInStorage.action");
					}else{
						alert(result.msg);
					}
				}
			});
		});
		
		
		
		
		//采购单查询
		$("#purchQuery").bind("click",function(){
			showDialog('选择采购单','dgPurchaseQuery.jsp');
			$("#dg_content").load("${pageContext.request.contextPath}/storage_findPurchaseHead.action");
		});
		//订单查询
		$("#orderQuery").bind("click",function(){
			showDialog('选择订单','dgOrderQuery.jsp');
			$("#dg_content").load("${pageContext.request.contextPath}/storage_findOrderHeadList.action");
		});
		
		//物料查询
		$("#hsCodeQuery").bind("click",function(){
			var purchNo = ($("#tfPurchaseNo").val()==null||$("#tfPurchaseNo").val()=="")?"":$("#tfPurchaseNo").val();
			var orderNo = ($("#tfOrderNo").val()==null||$("#tfOrderNo").val()=="")?"":$("#tfOrderNo").val();
			/*if(purchNo==null && orderNo==null){
				alert("至少要填一个采购单号或订单号!");
			}else{*/
				showDialog('选择物料','dgMaterial.jsp');
				var imgexgflag = $("#imgexgflag").val();
				$("#dg_content").load("${pageContext.request.contextPath}/storage_findMaterial.action?imgExgFlag="+imgexgflag+"&purchaseNo="+purchNo+"&orderNo="+orderNo);
			//}
		});
		
		//自动计算件数(出库数量)触发
		$("#expQty").bind("blur",function(){
			autoCountPaks();
		});
		//自动计算件数(每件包装数量)触发
		$("#packQty").bind("blur",function(){
			autoCountPaks();
		});
		
	});
	
		 $("#btnSure").click(function(){
			 var id = "";    
			 var hideflag = $("#hideflag").val();
			 $('input[name="materid"]:checked').each(function(){//遍历每一个名字为materid的复选框，其中选中的执行函数      
			 	id+=$(this).val()+"/";//将选中的值组装成一个以'/'分割的字符串
			 }); 
			 if(id==""){
				 if(hideflag=="material"){
					 alert("请选择一条物料信息");
				 }else if(hideflag=="purchase"){
			 		alert("请选择一条采购单信息");
				 }else if(hideflag=="order"){
					 alert("请选择一条订单信息");
				 }
			 	return;
			 }
			 
			 if(hideflag=="material"){
				 $.ajax({
					 url:'${pageContext.request.contextPath}/storage_getHsCodeByMaterialId.action',
					 data:{id:id},
					 async: false,
					 cache: false,
					 success:function(args){
						var result=jQuery.parseJSON(args);
						if(result.success){
							$("#hsCode").val(result.obj.hsCode);
							$("#hsName").val(result.obj.hsName);
							$("#hsModel").val(result.obj.model);
							$("#unitName").val(result.obj.unit.name);
							closeDialog();
						}
					 }
				 });
			 }else if(hideflag=="purchase"){
				 $.ajax({
					 url:'${pageContext.request.contextPath}/storage_getPurchaseNoById.action',
					 data:{id:id},
					 async: false,
					 cache: false,
					 success:function(args){
						var result=jQuery.parseJSON(args);
						if(result.success){
							$("#hsCode").val("");
							$("#hsName").val("");
							$("#hsModel").val("");
							$("#unitName").val("");
							$("#tfPurchaseNo").val(result.obj.purchaseNo);
							$("#tfScmcoc").val(result.obj.scmcoc.name);
							closeDialog();
						}
					 }
				 });
			 } else if(hideflag=="order"){
				 $.ajax({
					 url:'${pageContext.request.contextPath}/storage_getOrderNoById.action',
					 data:{id:id},
					 async: false,
					 cache: false,
					 success:function(args){
						var result=jQuery.parseJSON(args);
						if(result.success){
							$("#tfOrderNo").val(result.obj.orderNo);
							$("#tfScmcoc").val(result.obj.scmcoc.name);
							closeDialog();
						}
					 }
				 });
			 }
			 
		 });
	
	function initUi(){
		var val = $("#imgexgflag").val();
		var isClear = $("#isClear").val();
		var value = $(this).find("option:selected").text();
		if("I"==val){
			$("#purchNo").html("*");
			$("#purchQuery").show();
			$("#captionScm").html("供应商名称");
			$("#orderQuery").hide();
			$("#orderNo").html("");
		}else if("E"==val){
			$("#orderNo").html("*");
			$("#orderQuery").show();
			$("#captionScm").html("客户名称");
			$("#purchNo").html("");
			$("#purchQuery").hide();
		}
		
		if("false"!=isClear){
			$("#hsCode").val("");
			$("#hsName").val("");
			$("#hsModel").val("");
			$("#unitName").val("");
			$("#tfScmcoc").val("");
			$("#tfPurchaseNo").val("");
			$("#tfOrderNo").val("");
		}
		
	}
	
	
	function checkNumber(val){
		return isNaN(val);
	}
	
	//自动计算件数
	function autoCountPaks(){
			var expQty = $("#expQty").val();
			var packQty = $("#packQty").val();
			if(expQty!="" && packQty!="" && !checkNumber(expQty) &&!checkNumber(packQty)){
				var url = '${pageContext.request.contextPath}/storage_conutPaks.action';
				$.ajax({
					url:url,
					data:{expQty:expQty,packQty:packQty},
					success:function(args){
						var result=jQuery.parseJSON(args);
						if(result.success){
							$("#pkgs").val(result.obj);
						}
					}
				});
			}	
	}
	
</script>
    <input type="hidden" id="flag" value="${outStorage.id}"/><!-- 为了判断是新增还是修改 -->
    <input type="hidden" id="inOrOutFlag" value="${inOrOutFlag}"/>
    <input type="hidden" id="isClear" value="${isClear}"/>
   <div class="page-header position-relative" style="margin-bottom: 0px; height:10px;margin-top:0px;line-height: 25px;">
    	<c:if test="${storage.id==null}">
			<h5>物料＞＞<a href="javascript:void(0);" id="addgoback">出库</a>＞＞新增</h5>
		</c:if>
    	<c:if test="${storage.id!=null}">
			<h5>物料＞＞<a href="javascript:void(0);" id="editgoback">出库</a>＞＞修改</h5>
		</c:if>
	</div>
	<div>
		<table id="sample-table-1" class="table table-striped table-bordered "  style=" font-size: 12px; width:600px; border: 0px;">
			<tr style="border:0px;">
				<td style="width: 60px;border:0px;text-align: right;padding:0px; ">流水号</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;">*</td>
				<td style="width: 150px;border:0px;padding:0px;"><input id="serialNo" type="text" style="height:25px;  width:100%;" readonly="readonly"  value="${outStorage.serialNo}"/></td>
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">出库类型</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;">*</td>
				<td style="width: 150px;border:0px;padding:0px;">
					<select style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;border-color:red;" id="impExpType" >
						<c:forEach var="mattype" items="${impexptypes}">
							<option value="${mattype.code}" <c:if test="${outStorage.expFlag==mattype.code}">selected="selected"</c:if>>${mattype.name}</option>
						</c:forEach>
					</select>
				</td>
				<td style="width: 10px;border:0px;padding:0px;"></td>
			</tr>
			<tr style="border:0px;">
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">物料标志</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;">*</td>
				<td style="width: 150px;border:0px;padding:0px;">
					<select id="imgexgflag" style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;border-color:red;" >
						<c:if test="${imgExgFlag=='I'}">
							<option value="I" selected="selected">原料</option>
							<option value="E">成品</option>
						</c:if>
						<c:if test="${imgExgFlag=='E'}">
							<option value="I" >原料</option>
							<option value="E" selected="selected">成品</option>
						</c:if>
					</select>
				</td>
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">采购单号</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;" id="purchNo"></td>
				<td style="width: 150px;border:0px;padding:0px;"><input id="tfPurchaseNo" type="text"  value="${outStorage.purchaseNo}" style="height:25px; width:100%;  padding-top:0px;padding-bottom: 0px;" readonly="readonly" /></td>
				<td style="width: 10px;border:0px;padding:0px;"><img src="${pageContext.request.contextPath}/images/search.gif" style="margin-top: 6px; cursor: pointer;" id="purchQuery"/></td>
			</tr>
			<tr style="border:0px;">
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">出库单号</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;">*</td>
				<td style="width: 150px;border:0px;padding:0px;"><input type="text" value="${outStorage.outStorageNo}"  id="outStorageNo" style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;border-color:red;"/></td>
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">订单号</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;"  id="orderNo">*</td>
				<td style="width: 150px;border:0px;padding:0px;"><input type="text" id="tfOrderNo" value="${outStorage.orderNo}"  style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;" readonly="readonly"/></td>
				<td style="width: 10px;border:0px;padding:0px;"><img  src="${pageContext.request.contextPath}/images/search.gif" style="margin-top: 6px; cursor: pointer;" id="orderQuery"/></td>
			</tr>
			<tr style="border:0px;">
				<td style="width: 60px;border:0px;text-align: right;padding:0px;" id="captionScm">供应商名称</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;"></td>
				<td style="width: 150px;border:0px;padding:0px;"><input type="text" id="tfScmcoc" value="${outStorage.scmcoc.name}"  style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;" readonly="readonly"/></td>
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">物料编码</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;">*</td>
				<td style="width: 150px;border:0px;padding:0px;"><input type="text"  id="hsCode" value="${outStorage.material.hsCode}"  style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;" readonly="readonly" /></td>
				<td style="width: 10px;border:0px;padding:0px;"><img id="hsCodeQuery" src="${pageContext.request.contextPath}/images/search.gif" style="margin-top: 6px; cursor: pointer;"/></td>
			</tr>
			<tr style="border:0px;">
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">物料名称</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;"></td>
				<td style="width: 150px;border:0px;padding:0px;"><input readonly="readonly"   value="${outStorage.material.hsName}" type="text" style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;" id="hsName"/></td>
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">规格型号</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;"></td>
				<td style="width: 150px;border:0px;padding:0px;"><input readonly="readonly"  id="hsModel" value="${outStorage.material.model}" type="text" style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;"/></td>
			</tr>
			<tr style="border:0px;">
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">计量单位</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;"></td>
				<td style="width: 150px;border:0px;padding:0px;"><input readonly="readonly"  id="unitName" value="${outStorage.material.unit.name}" type="text" style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;"/></td>
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">出库数量</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;">*</td>
				<td style="width: 150px;border:0px;padding:0px;"><input type="text"  class="number"  id="expQty" value="${outStorage.expQty}" style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;border-color:red;"/></td>
			</tr>
			<tr style="border:0px;">
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">每件包装数</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;"></td>
				<td style="width: 150px;border:0px;padding:0px;"><input type="text"  class="number"    id="packQty" value="${outStorage.specQty}" style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;border-color:red;"/></td>
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">件数</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;"></td>
				<td style="width: 150px;border:0px;padding:0px;"><input type="text"  id="pkgs" value="${outStorage.pkgs}"  style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;"  readonly="readonly"/></td>
			</tr>
			<tr style="border:0px;">
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">仓库名称</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;"></td>
				<td style="width: 150px;border:0px;padding:0px;">
					<select id="stockName"  style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;border-color:red;" >
						<c:forEach var="stock" items="${stockList}">
							<option value="${stock.id}" <c:if test="${outStorage.stock.name==stock.name}">selected="selected"</c:if>>${stock.name}</option>
						</c:forEach>
					</select>
				</td>
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">启用状态</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;"></td>
				<td style="width: 150px;border:0px;padding:0px;">
					<c:if test="${outStorage.useFlag=='1'}">
						<input name="useFlag"  type="radio" value="${outStorage.useFlag}" checked="checked" />停用　　
					</c:if>
					<c:if test="${outStorage.useFlag!='1'}">
						<input name="useFlag"  type="radio" value="${outStorage.useFlag}"  />停用　　
					</c:if>
					<c:if test="${outStorage.useFlag=='0'}">
						<input name="useFlag"  type="radio" value="${outStorage.useFlag}" checked="checked" />启用　　
					</c:if>
					<c:if test="${outStorage.useFlag!='0'}">
						<input name="useFlag"  type="radio" value="${outStorage.useFlag}"  />启用　　
					</c:if>
				</td>
			</tr>
			<tr>
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">备注</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;"></td>
				<td colspan="4"  style="width: 150px;border:0px;padding:0px;"><textarea id="note"   style="resize: none; height:25px; width:100%;padding-top:0px;padding-bottom: 0px;border-color:red;">${outStorage.note}</textarea></td>
			</tr>
			<tr style="border:0px;text-align:center; ">
				<td colspan="6" style="border:0px;text-align:center;">
					<button class="btn btn-small btn-danger " data-toggle="button" type="button"  id="btnAdd">保存</button>
					<button class="btn btn-small btn-danger " data-toggle="button" type="button" onclick="">取消</button>
				</td>
			</tr>
		</table>
	</div>
