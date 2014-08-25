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

  </head>
  
  <body>
    <div class="modal-footer" style="text-align: left;">
		<input type="file" style="height:25px;" class="" name="upfile" id="importfile"/>
		<input class="btn btn-small btn-danger" data-toggle="button" type="button" value="打开文件" style="height:25px; border: 2px; width:55px; margin-top:0px; margin-left: -85px;" onclick="sendfile()"/>
		<input class="btn btn-small btn-danger" data-toggle="button" type="button" value="删除错误信息" style="height:25px; border: 2px; width:65px; margin-top:0px;" onclick="clearErrorData()"/>
		<input class="btn btn-small btn-danger" data-toggle="button" type="button" value="保存" style="height:25px; border: 2px; width:55px; margin-top:0px;" onclick="saveData()"/>
	</div> 
  <div class="row-fluid" >
		<div class="span12">
			<!--PAGE CONTENT BEGINS-->

			<div class="row-fluid">
				<div class="span12">
					<table id="sample-table-1" class="table table-striped table-bordered table-hover"  style=" font-size: 12px;">
						<thead>
							<tr align="center">
								<th class="center">错误信息</th>
								<th class="center">编号</th>
								<th class="center">仓库名称</th>
								<th class="hidden-480 center">备注</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach var="stock" items="${tlist}">
							<c:if test="${stock.errorInfo!=null}">
								<tr style="color: red;">
									<td style="text-align: left;">${stock.errorInfo}　</td>
									<td class="center">${stock.code}　</td>
									<td class="center">${stock.name}　</td>
									<td class="hidden-480 center">${stock.note}　</td>
								</tr>
							</c:if>
							<c:if test="${stock.errorInfo==null}">
								<tr>
									<td class="center">${stock.errorInfo}　</td>
									<td class="center">${stock.code}　</td>
									<td class="center">${stock.name}　</td>
									<td class="hidden-480 center">${stock.note}　</td>
								</tr>
							</c:if>
							</c:forEach>
					</table>
				</div>
			</div>

		</div>
		<!--PAGE CONTENT ENDS-->
	</div>
	<script type="text/javascript">
		function sendfile(){
			var filePath = $("#importfile").val();
			if(filePath==""){
				alert("请选择文件!");
				return;
			}
			var url = "${pageContext.request.contextPath}/stock_importData.action";
			toMain(url);
		}
		
		function clearErrorData(){
			var url = "${pageContext.request.contextPath}/stock_clearErrorData.action";
			toMain(url);
		}
		
		function saveData(){
			var url = "${pageContext.request.contextPath}/stock_saveExcelData.action";
			$.ajax({
		     type: "POST",
		     url:url,
		     async: false,
		     cache: false,
		     success:function(data){
		    	var result=jQuery.parseJSON(data);
		    	if(!result.success){
		    		alert(result.msg);
		    		return;
		    	}
		    	alert(result.msg);
		     },error:function(){
		        	$("#mess").html("程序异常，请重新启动程序！");
		        	return false;
		      }
		  	});
		}
	</script>
  </body>
</html>
