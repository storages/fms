<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/utils/jquery.tmpl.min.js"> </script>
   <script type="text/javascript">
   var resultdata="";
	$(function(){
		var actionUrl = "${pageContext.request.contextPath}/materInfo_importData.action";//
		$("#uploadform").attr("action", actionUrl);
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
	      var url = Global+"/materInfo_saveExcelData.action";//
	      $.post(url,paremt,function(data){
		    	var result=jQuery.parseJSON(data);
		    	if(!result.success){
		    		$("#waitdiv").hide();
		    		alert(result.msg);
		    		return;
		    	}
		    	$("#waitdiv").hide();
		    	alert(result.msg);
		    	var returnUrl = Global +"/materInfo_findAllMaterial.action?imgExgFlag="+$("#flag").val();//
		    	toMain(returnUrl);
		     });
			
		});
		  
	    $("#download").click(function(){
	    	window.location.href=Global+"/fileDownload.action?fileFlag=materTemp";//
	    });
	});
	var excelupload= document.getElementById("excelupload");
	  //判断IE 解决兼容问题
	if(excelupload.attachEvent){ // IE  
		excelupload.attachEvent('onload',function(){
		var html= document.frames["excelupload"].document.body.innerHTML;
		if(html&&html!=""){
			  var json= jQuery.parseJSON(html);
			  if(json.success){
		          $("#waitdiv").hide();
		          //stock   SXrow
		         var mylist= json.obj;
		         resultdata=mylist;
		         for(var x=0; x<mylist.length; x++){
			         if(mylist[x].errorInfo||mylist[x].errorInfo==''){
			             mylist[x].erroris=true;
			         }
		         }
		          $("#SXrow").tmpl(json.obj).appendTo("#tbodymater");  
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
    	$("#tbodymater tr").remove();
		      var thisDocument=this.contentDocument||this.contentWindow.document; 
	          var html=  $(thisDocument.body).find("pre").html();
	          var json= jQuery.parseJSON(html);
	          if(json.success){
		          $("#waitdiv").hide();
		          //stock   SXrow
		         var mylist= json.obj;
		         resultdata=mylist;
		         for(var x=0; x<mylist.length; x++){
			         if(mylist[x].errorInfo||mylist[x].errorInfo==''){
			             mylist[x].erroris=true;
			         }
		         }
		          $("#SXrow").tmpl(json.obj).appendTo("#tbodymater");  
	          }else{
	    	       $("#waitdiv").hide();
	    	       alert("解析文件错误！原因："+json.msg);
	          }
	};
    }
    
		
		function clearErrorData(){
			//alert(resultdata);
			var url =Global+"/materInfo_clearErrorData.action";//
			var paremt={};
			paremt["sendStr"]=JSON.stringify(resultdata);
			$.post(url,paremt,function(data){
				var result=jQuery.parseJSON(data);
		    	if(result.success){
		    	 resultdata=result.obj;
		    	$("#tbodymater tr").remove();
		    		//--------------
		    		var list = result.obj;
		    		$("#SXrow").tmpl(list).appendTo("#tbodymater"); 
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
	<input type="hidden" value="${imgExgFlag}" id="flag"/>
  <iframe id="excelupload" name="excelupload" style="display: none;"></iframe>
  <body>
  <input type="hidden" id="hid" value="${hid}"> 
  <input type="hidden" id="verNo" value="${verNo}"> 
    <div class="modal-footer" style="text-align: left; padding-bottom: 0px;">
		<form id="uploadform" action="" target="excelupload" method="post" enctype="multipart/form-data" style="height:25px; width:160px; float: left;" >
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
								<th class="center" style="width:220px;">错误信息</th>
								<!-- <th class="center" style="width:70px;">bom版本号</th> -->
								<th class="center">物料标记</th>
								<th class="center">物料编码</th>
								<th class="center">物料名称</th>
								<th class="center">物料规格</th>
								<th class="center">颜色</th>
								<th class="center">物料类别</th>
								<th class="center">计量单位</th>
								<th class="center">批次号</th>
								<th class="center">最低库存</th>
							</tr>
						</thead>

						<tbody id="tbodymater">
					</table>
				</div>
			</div>

		</div>
		<!--PAGE CONTENT ENDS-->
	</div>
  </body>
  <script id="SXrow" type="text/x-jquery-tmpl">
            <tr  {{if erroris}} style="color: red;" {{/if}}>
				<td style="text-align: left;">{{= errorInfo}}　</td>
				<td class="center">{{= imgExgFlag}}　</td>
				<td class="center">{{= hsCode}}　</td>
				<td class="center">{{= hsName}}　</td>
				<td class="center">{{= model}}　</td>
				<td class="center">{{= color}}　</td>
				<td class="center">{{= materialType}}　</td>
				<td class="center">{{= unit}}　</td>
				<td class="center">{{= batchNO}}　</td>
				<td class="center">{{= lowerQty}}　</td>
				</tr>
       </script>
