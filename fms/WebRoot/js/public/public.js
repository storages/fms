function delList(){
	var splitStr = "";
	$('input[name="uid"]:checked').each(function(){
		splitStr+=$(this).val()+',';
	  }); 
	if(splitStr==""){
		alert("请选择要删除的内容!");
		return;
	}
	/*for(var i = 0 ; i < arr.length ; i++){
		splitStr+=ids[i].value+",";
	}*/
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