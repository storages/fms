<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/utils/jquery.tmpl.min.js"> </script>
<script type="text/javascript">var resultdata="";</script>

<iframe id="excelupload" name="excelupload" style="display: none;"></iframe>

<div class="modal-footer" style="text-align: left; padding-bottom: 0px;">
		<form id="uploadform" action="quotation_importData.action" target="excelupload" method="post" enctype="multipart/form-data" style="height:25px; width:160px; float: left;" >
		<input type="file" style="height:25px; width:160px;" class="" name="uploadFile" id="importfile"/></form>
		
		<input class="btn btn-small btn-danger" data-toggle="button" type="button" id="uploadbutton" value="打开文件" style="height:25px; border: 2px; width:65px; margin-top:0px; float: left;" />
		<input class="btn btn-small btn-danger" data-toggle="button" type="button" value="删除错误" style="height:25px; border: 2px; width:65px; margin-top:0px; float: left;" onclick="clearErrorData()"/>
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
								<th class="center" style="width: 300px;">错误信息</th>
								<th class="center">供应商编码</th>
								<th class="hidden-480 center">供应商名称</th>
								<th class="hidden-480 center">物料编码</th>
								<th class="hidden-480 center">物料名称</th>
								<th class="hidden-480 center">单价</th>
							</tr>
						</thead>
						<tbody id="tbodyquotation"></tbody>
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
	
	<script id="SXrow" type="text/x-jquery-tmpl">
            <tr {{if erroris}} style="color: red;" {{/if}}>
				<td style="text-align: left;">{{= errorInfo}}　</td>
				<td class="center">{{= scmcocCode}}　</td>
				<td class="center">{{= scmcocName}}　</td>
				<td class="center">{{= hsCode}}　</td>
				<td class="center">{{= hsName}}　</td>
				<td class="center">{{= price}}　</td>
				</tr>
       </script>
  <script type="text/javascript">
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
		paremt["sendStr"]=JSON.stringify(resultdata);
	      var url = Global+"/quotation_saveExcelData.action";
	      $.post(url,paremt,function(data){
		    	var result=jQuery.parseJSON(data);
		    	if(!result.success){
		    		$("#waitdiv").hide();
		    		alert(result.msg);
		    		return;
		    	}
		    	$("#waitdiv").hide();
		    	alert(result.msg);
		    	url = "${pageContext.request.contextPath}/quotation_findQuotations.action";
		    	toMain(url);
		     });
			
		});
		//下载样本 
	    $("#download").click(function(){
	    	window.location.href="${pageContext.request.contextPath}/fileDownload.action?fileFlag=quotationTemp";
	    });
	});
	var excelupload= document.getElementById("excelupload");
	//文件上传回调函数  //判断IE 解决兼容问题
	if(excelupload.attachEvent){ // IE  
		excelupload.attachEvent('onload',function(){
		var html= document.frames["excelupload"].document.body.innerHTML;
		if(html&&html!=""){
	         if(json.success){
		          $("#waitdiv").hide();
		          //SXrow
		         var mylist= json.obj;
		         resultdata=mylist;
		         alert(mylist.length);
		         for(var x=0; x<mylist.length; x++){
		         if(mylist[x].errorInfo||mylist[x].errorInfo==''){
		             mylist[x].erroris=true;
		              }
		         }
		         $("#tbodyquotation tr").remove();
		          $("#SXrow").tmpl(json.obj).appendTo("#tbodyquotation");  
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
	         $("#tbodyquotation tr").remove();
	          $("#SXrow").tmpl(json.obj).appendTo("#tbodyquotation");  
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
			var url = "${pageContext.request.contextPath}/quotation_importData.action";
			toMain(url);
		}
		
		function clearErrorData(){
			var paremt={};
			paremt["sendStr"]=JSON.stringify(resultdata);
			var url = "${pageContext.request.contextPath}/quotation_clearErrorData.action?";
			paremt["sendStr"]=JSON.stringify(resultdata);
			$.post(url,paremt,function(data){
				var result=jQuery.parseJSON(data);
		    	if(result.success){
		    		//--------------
		    		$("#tbodyquotation tr").remove();
		    		resultdata = result.obj;
		    		//--------------
		    		$("#SXrow").tmpl(result.obj).appendTo("#tbodyquotation"); 
		    		//--------------
		    	}
			});
		}
		
		function posigif(){
		 var w =  50;     //宽度offsetWidth
		 var h = 50;   //高度
		 var t = (screen.height-h)/2-80; //离顶部距离
		 var l = (screen.width-w)/2; //离左边距离
		 document.getElementById("waitgif").style.marginLeft = l+"px";
		 document.getElementById("waitgif").style.marginTop = t+"px";
		}
	</script>