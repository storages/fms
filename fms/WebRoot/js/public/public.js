//转换字符编码，以防把参数【中文】传到后台变乱码
function parse(str){
	return encodeURI(encodeURI(str));  
}
function delList(){
	var splitStr = "";
	$('input[name="uid"]:checked').each(function(){
		splitStr+=$(this).val()+',';
	  }); 
	if(splitStr==""){
		alert("请选择要删除的内容!");
		return;
	}
	
	splitStr = splitStr.substring(0, splitStr.length-1);
	if(confirm("你确认要删除吗？")){
		var url = "${pageContext.request.contextPath}/users_deleteUser.action?ids="+splitStr;
		toMain(url);
	}
}

function delSingleObject(ids){
	if(confirm("你确认要删除吗？")){
		var url = "${pageContext.request.contextPath}/users_deleteUser.action?ids="+ids;
		toMain(url);
	}
}

function delSingleScmcoc(ids,flag){
	if(confirm("你确认要删除吗？")){
		var url = "${pageContext.request.contextPath}/scmcoc_del.action?ids="+ids+"&isCustom="+flag;
		toMain(url);
	}
}

function delMoreScmcoc(flag){
	var splitStr = "";
	$('input[name="sid"]:checked').each(function(){
		splitStr+=$(this).val()+',';
	  }); 
	if(splitStr==""){
		alert("请选择要删除的内容!");
		return;
	}
	splitStr = splitStr.substring(0, splitStr.length-1);
	if(confirm("你确认要删除吗？")){
		var url = "${pageContext.request.contextPath}/scmcoc_del.action?ids="+splitStr+"&isCustom="+flag;
		toMain(url);
	}
}

function delSingleDept(ids){
	if(confirm("你确认要删除吗？")){
		var url = "${pageContext.request.contextPath}/dept_deleteDept.action?ids="+ids;
		toMain(url);
	}
}

function delMoreDept(){
	var splitStr = "";
	$('input[name="sid"]:checked').each(function(){
		splitStr+=$(this).val()+',';
	  }); 
	if(splitStr==""){
		alert("请选择要删除的内容!");
		return;
	}
	splitStr = splitStr.substring(0, splitStr.length-1);
	if(confirm("你确认要删除吗？")){
		var url = "${pageContext.request.contextPath}/dept_deleteDept.action?ids="+splitStr;
		toMain(url);
	}
}


function delSingleStock(ids){
	if(confirm("你确认要删除吗？")){
		var url = "${pageContext.request.contextPath}/stock_deleteStock.action?ids="+ids;
		toMain(url);
	}
}

function delMoreStock(){
	var splitStr = "";
	$('input[name="sid"]:checked').each(function(){
		splitStr+=$(this).val()+',';
	}); 
	if(splitStr==""){
		alert("请选择要删除的内容!");
		return;
	}
	splitStr = splitStr.substring(0, splitStr.length-1);
	if(confirm("你确认要删除吗？")){
		var url = "${pageContext.request.contextPath}/stock_deleteStock.action?ids="+splitStr;
		toMain(url);
	}
}