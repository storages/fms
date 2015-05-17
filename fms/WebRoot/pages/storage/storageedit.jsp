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
	$(function(){
		initUi();
		//货物标志改变事件
		$("#imgexgflag").change(function(){
			initUi();
		});
		
		$("#imgexgflag").change(function(){
			var val = $("#imgexgflag").val();
			var url ="${pageContext.request.contextPath}/storage_loadImpExpType.action";
			$.ajax({
				type: "POST",
				url:url,
				data:{impExpFlag:val},
				async: false,
				cache: false,
				success:function(args){
					var result=jQuery.parseJSON(args);
					if(result.success){
						$("#impExpType").empty(); 
							var mylist = result.obj;
							var options = "";
							 for(var i=0; i<mylist.length; i++){
								options += "<option value='"+mylist[i].code+"'>"+ mylist[i].name +"</option>";
							}
							 $("#impExpType").html(options); 
					}
				}
			});
		});
		
		//上一页面
		$("#goback").click(function(){
			toMain("${pageContext.request.contextPath}/storage_findAllInStorage.action");
		});
	});
			
	
	function initUi(){
		var val = $("#imgexgflag").val();
		if("I"==val){
			$("#purchNo").html("*");
			$("#purchQuery").show();
			$("#captionScm").html("供应商名称");
			$("#orderNo").html("");
			$("#orderQuery").hide();
		}else if("E"==val){
			$("#orderNo").html("*");
			$("#orderQuery").show();
			$("#captionScm").html("客户名称");
			$("#purchNo").html("");
			$("#purchQuery").hide();
		}
	}
	
	
</script>
    <input type="hidden" id="flag" value="${storage.id}"/><!-- 为了判断是新增还是修改 -->
    <div class="page-header position-relative" style="margin-bottom: 0px;">
    	<c:if test="${storage.id==null}">
			<h5>物料＞＞<a href="javascript:void(0);" id="goback">入库</a>＞＞新增</h5>
		</c:if>
    	<c:if test="${storage.id!=null}">
			<h5>物料＞＞<a href="">出库</a>＞＞修改</h5>
		</c:if>
	</div>
	<div>
		<table id="sample-table-1" class="table table-striped table-bordered "  style=" font-size: 12px; width:600px; border: 0px;">
			<tr style="border:0px;">
				<td style="width: 60px;border:0px;text-align: right;padding:0px; ">流水号</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;">*</td>
				<td style="width: 150px;border:0px;padding:0px;"><input type="text" style="height:25px;  width:100%;" readonly="readonly" /></td>
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">入库类型</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;">*</td>
				<td style="width: 150px;border:0px;padding:0px;">
					<select style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;border-color:red;" id="impExpType" >
						<c:forEach var="mattype" items="${impexptypes}">
							<option value="${mattype.code}">${mattype.name}</option>
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
				<td style="width: 3px;border:0px;color:red;padding:0px;" id="purchNo">*</td>
				<td style="width: 150px;border:0px;padding:0px;"><input type="text" style="height:25px; width:100%;  padding-top:0px;padding-bottom: 0px;" readonly="readonly" /></td>
				<td style="width: 10px;border:0px;padding:0px;"><img src="${pageContext.request.contextPath}/images/search.gif" style="margin-top: 6px; cursor: pointer;" id="purchQuery"/></td>
			</tr>
			<tr style="border:0px;">
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">入库单号</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;">*</td>
				<td style="width: 150px;border:0px;padding:0px;"><input type="text" style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;border-color:red;"/></td>
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">订单号</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;"  id="orderNo">*</td>
				<td style="width: 150px;border:0px;padding:0px;"><input type="text" style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;" readonly="readonly"/></td>
				<td style="width: 10px;border:0px;padding:0px;"><img src="${pageContext.request.contextPath}/images/search.gif" style="margin-top: 6px; cursor: pointer;" id="orderQuery"/></td>
			</tr>
			<tr style="border:0px;">
				<td style="width: 60px;border:0px;text-align: right;padding:0px;" id="captionScm">供应商名称</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;"></td>
				<td style="width: 150px;border:0px;padding:0px;"><input type="text" style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;" readonly="readonly"/></td>
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">物料编码</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;">*</td>
				<td style="width: 150px;border:0px;padding:0px;"><input type="text" style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;" readonly="readonly" /></td>
				<td style="width: 10px;border:0px;padding:0px;"><img src="${pageContext.request.contextPath}/images/search.gif" style="margin-top: 6px; cursor: pointer;"/></td>
			</tr>
			<tr style="border:0px;">
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">物料名称</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;"></td>
				<td style="width: 150px;border:0px;padding:0px;"><input readonly="readonly" type="text" style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;"/></td>
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">规格型号</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;"></td>
				<td style="width: 150px;border:0px;padding:0px;"><input readonly="readonly" type="text" style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;"/></td>
			</tr>
			<tr style="border:0px;">
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">计量单位</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;"></td>
				<td style="width: 150px;border:0px;padding:0px;"><input readonly="readonly" type="text" style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;"/></td>
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">入库数量</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;">*</td>
				<td style="width: 150px;border:0px;padding:0px;"><input type="text" style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;border-color:red;"/></td>
			</tr>
			<tr style="border:0px;">
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">每件包装数</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;"></td>
				<td style="width: 150px;border:0px;padding:0px;"><input type="text" style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;border-color:red;"/></td>
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">件数</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;"></td>
				<td style="width: 150px;border:0px;padding:0px;"><input type="text" style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;"  readonly="readonly"/></td>
			</tr>
			<tr style="border:0px;">
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">仓库名称</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;"></td>
				<td style="width: 150px;border:0px;padding:0px;">
					<select style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;border-color:red;" >
						<c:forEach var="stock" items="${stockList}">
							<option value="${stock.id}">${stock.name}</option>
						</c:forEach>
					</select>
				</td>
				<td style="width: 60px;border:0px;text-align: right;padding:0px;">备注</td>
				<td style="width: 3px;border:0px;color:red;padding:0px;"></td>
				<td style="width: 150px;border:0px;padding:0px;"><input type="text" style="height:25px; width:100%;padding-top:0px;padding-bottom: 0px;border-color:red;"/></td>
			</tr>
			<tr style="border:0px;text-align:center; ">
				<td colspan="6" style="border:0px;text-align:center;">
					<button class="btn btn-small btn-danger " data-toggle="button" type="button" onclick="">保存</button>
					<button class="btn btn-small btn-danger " data-toggle="button" type="button" onclick="">取消</button>
				</td>
			</tr>
		</table>
	</div>
