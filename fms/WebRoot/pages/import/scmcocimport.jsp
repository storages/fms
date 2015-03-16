<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.io.File" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'stockimport.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=path%>/js/utils/jquery.tmpl.min.js"> </script>
  
  </head>
   <script type="text/javascript">
   var resultdata="";
	$(function(){
	//上传
	$("#uploadbutton").click(function(){
		var filePath = $("#importfile").val();
			if(filePath==""){
				alert("请选择文件!");
				return;
			}
			posigif();
			$("#waitdiv").show();
	 $("#uploadform").submit();
	});
	    
	 $("#mysaveData").click(function(){
	 	posigif();
		$("#waitdiv").show();
		var paremt={};
		var scmFlag = $("#scmFlag").val();
		paremt["sendStr"]=JSON.stringify(resultdata);
	      var url = Global+"/scmcoc_saveExcelData.action?isScmcoc="+scmFlag;
	      $.post(url,paremt,function(data){
		    	var result=jQuery.parseJSON(data);
		    	if(!result.success){
		    		$("#waitdiv").hide();
		    		alert(result.msg);
		    		return;
		    	}
		    	$("#waitdiv").hide();
		    	alert(result.msg);
		     });
			
		});
		//下载样本 
	    $("#download").click(function(){
	    	window.location.href="${pageContext.request.contextPath}/fileDownload.action?fileFlag=scmcocTemp";
	    });
		
		
		
	    var excelupload= document.getElementById("excelupload");
		//图片上传回调函数  //判断IE 解决兼容问题
		if(excelupload.attachEvent){ // IE  
			excelupload.attachEvent('onload',function(){
			var html= document.frames["excelupload"].document.body.innerHTML;
			if(html&&html!=""){
		         if(json.success){
			          $("#waitdiv").hide();
			          //SXrow
			         var mylist= json.obj;
			         resultdata=mylist;
			         for(var x=0; x<mylist.length; x++){
			         if(mylist[x].errorInfo||mylist[x].errorInfo==''){
			             mylist[x].erroris=true;
			              }
			         }
			          $("#SXrow").tmpl(json.obj).appendTo("#tbodyscmcoc");  
			          }else{
			    	       $("#waitdiv").hide();
			    	       alert("解析文件错误！原因："+json.msg);
			          }
			}else{
			//不做任何处理
			}
		       
			});  
	    }else{
	    	excelupload.onload=function(){
	    		$("#tbodyscmcoc tr").remove();
			      var thisDocument=this.contentDocument||this.contentWindow.document; 
		          var html=  $(thisDocument.body).find("pre").html();
		          var json= jQuery.parseJSON(html);
		          if(json.success){
		          $("#waitdiv").hide();
		          //SXrow
		         var mylist= json.obj;
		         resultdata=mylist;
		         for(var x=0; x<mylist.length; x++){
		         if(mylist[x].errorInfo||mylist[x].errorInfo==''){
		             mylist[x].erroris=true;
		              }
		         }
		          $("#SXrow").tmpl(json.obj).appendTo("#tbodyscmcoc");  
		          }else{
		    	       $("#waitdiv").hide();
		    	       alert("解析文件错误！原因："+json.msg);
		          }
		};
	    }
	    
	    
			function sendfile(){
				var filePath = $("#importfile").val();
				if(filePath==""){
					alert("请选择文件!");
					return;
				}
				var url = "${pageContext.request.contextPath}/scmcoc_importData.action";
				toMain(url);
			}
			
			$("#clearData").click(function(){
				var url = "${pageContext.request.contextPath}/scmcoc_clearErrorData.action";
				var paremt={};
				paremt["sendStr"]=JSON.stringify(resultdata);
				$.post(url,paremt,function(data){
					var result=jQuery.parseJSON(data);
			    	if(result.success){
			    	 resultdata=result.obj;
			    	$("#tbodyscmcoc tr").remove();
			    		//--------------
			    		var list = result.obj;
			    		$("#SXrow").tmpl(list).appendTo("#tbodyscmcoc"); 
			    		//--------------
			    	}
				});
			}); 
			
			function posigif(){
			 var w =  50;     //宽度offsetWidth
			 var h = 50;   //高度
			 var t = (screen.height-h)/2-80; //离顶部距离
			 var l = (screen.width-w)/2; //离左边距离
			 document.getElementById("waitgif").style.marginLeft = l+"px";
			 document.getElementById("waitgif").style.marginTop = t+"px";
			}
		
		
		
	});
	
	</script>
  <iframe id="excelupload" name="excelupload" style="display: none;"></iframe>
  <input type="hidden" value="${isScmcoc}" id="scmFlag">
    <div class="modal-footer" style="text-align: left; padding-bottom: 0px;">
		<form id="uploadform" action="scmcoc_importData.action" target="excelupload" method="post" enctype="multipart/form-data" style="height:25px; width:160px; float: left;" >
		<input type="file" style="height:25px; width:160px;" class="" name="uploadFile" id="importfile"/></form>
		
		<input class="btn btn-small btn-danger" data-toggle="button" type="button" id="uploadbutton" value="打开文件" style="height:25px; border: 2px; width:65px; margin-top:0px; float: left;" />
		<input class="btn btn-small btn-danger" data-toggle="button" type="button" value="删除错误" style="height:25px; border: 2px; width:65px; margin-top:0px; float: left;" id="clearData"/>
		<input class="btn btn-small btn-danger" data-toggle="button" type="button" value="保存" id="mysaveData" style="height:25px; border: 2px; width:55px; margin-top:0px; float: left;" />
		<input class="btn btn-small btn-danger" data-toggle="button" type="button" id="download" value="下载样本" style="height:25px; border: 2px; width:65px; margin-top:0px; float: left;" />
	</div> 
  <div class="row-fluid">
		<div class="span12">
			<!--PAGE CONTENT BEGINS-->

			<div class="row-fluid">
				<div class="span12" >
					<table id="sample-table-1" class="table table-striped table-bordered table-hover"  style=" font-size: 12px; ">
						<thead>
							<tr align="center">
								<th class="center">错误信息</th>
								<th class="center">编号</th>
								<c:if test="${isScmcoc=='true'}" >
									<th class="center">供应商名称</th>
									<th class="hidden-480 center">供应商联系人</th>
									<th class="hidden-480 center">结算方式</th>
									<th class="hidden-480 center">供应商联系电话</th>
									<th class="hidden-phone center">供应商网络联系方式</th>
									<th class="hidden-480 center">供应商地址</th>
								</c:if>
								<c:if test="${isScmcoc=='false'}" >
									<th class="center">客户名称</th>
									<th class="hidden-480 center">客户联系人</th>
									<th class="hidden-480 center">结算方式</th>
									<th class="hidden-480 center">客户联系电话</th>
									<th class="hidden-phone center">客户网络联系方式</th>
									<th class="hidden-480 center">客户地址</th>
								</c:if>
								<th class="hidden-480 center">约定结算日期</th>
								<th class="hidden-480 center">备注</th>
							</tr>
						</thead>

						<tbody id="tbodyscmcoc">
					</table>
				</div>
			</div>

		</div>
		<!--PAGE CONTENT ENDS-->
		<!-- WAIT FLASH ACTIVE -->
		<div id="waitdiv" class="waitcss" style="display: none;">
			<img src="${pageContext.request.contextPath}/imges/loading.gif" class="waitgif" id="waitgif"></img>
		</div>
	</div>
  </body>
  <script id="SXrow" type="text/x-jquery-tmpl">
	<tr {{if erroris}} style="color: red;" {{/if}}>
		<td class="center"  style="text-align: left;">{{= errorInfo}}　</td>
		<td class="center">{{= code}}　</td>
		<td class="center">{{= name}}　</td>
		<td class="hidden-480 center">{{= linkMan}}　</td>
		<td class="hidden-480 center">{{= settlement.name}}　</td>
		<td class="hidden-480 center">{{= linkPhone}}　</td>
		<td class="hidden-480 center">{{= networkLink}}　</td>
		<td class="hidden-480 center">{{= address}}　</td>
		<td class="hidden-480 center">{{if endDate!=""}}每月{{= endDate}}日{{/if}}　</td>
		<td class="hidden-480 center">{{= note}}　</td>
	</tr>
</script>
 
</html>
