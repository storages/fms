//转换字符编码，以防把参数【中文】传到后台变乱码
function parse(str){
	return encodeURI(encodeURI(str));  
}

/*//全选框
$(function(){
	$("#checkallbox").change(function(){
		if($("#checkallbox").attr("checked")){
			$("#sample-table-1 input[name='sid']").each(function(){
				$(this).attr("checked",true);
			});
		}else{
			$("#sample-table-1 input[name='sid']").each(function(){
				$(this).attr("checked",false);
			});
		}
	});
}*/
	


/*****************************这个独特，另外写的删除*******************/

/**
 * 删除客户或供应商【单条删除】
 * @param ids
 * @param flag
 */
function delSingleScmcoc(ids,flag){
	if(confirm("你确认要删除吗？")){
		var url = "${pageContext.request.contextPath}/scmcoc_del.action?ids="+ids+"&isCustom="+flag;
		toMain(url);
	}
}




/**
 * 删除客户或供应商【批量删除】
 * @param flag
 */
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

/**
 * 删除物料信息【单条删除】
 * @param ids
 * @param flag
 */
function delSingleMaterial(ids,flag){
	if(confirm("你确认要删除吗？")){
		var url = "${pageContext.request.contextPath}/materInfo_deleteMaterial.action?ids="+ids+"&imgExgFlag="+flag;
		toMain(url);
	}
}




/**
 * 删除物料信息【批量删除】
 * @param flag
 */
function delMoreMater(flag){
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
		var url = "${pageContext.request.contextPath}/materInfo_deleteMaterial.action?ids="+splitStr+"&imgExgFlag="+flag;
		toMain(url);
	}
}
/********************************************************************************************************/





/**
 * 删除公共方法
 * @param ids 传过来的id,每行后面的【删除】所传来的id;【批量删除】参数传''即可
 * @param flag 这个参数是为了标识提交的地址，也就是下面getUrl(flag)方法要用到的
 */
function delData(ids,flag){
	var splitStr = "";
	$('input[name="sid"]:checked').each(function(){
		splitStr+=$(this).val()+',';
	}); 
	if(splitStr=="" && ids==""){
		alert("请选择要删除的内容!");
		return;
	}else if(ids!=""){
		splitStr = ids+",";
	}
	splitStr = splitStr.substring(0, splitStr.length-1);
	console.info(splitStr);
	var resultUrl = getUrl(flag);
	var url = resultUrl[0]+splitStr;
	if(confirm("你确认要删除吗？")){
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
		var refreshUrl = resultUrl[1];
		toMain(refreshUrl);
	}
}


/**
 * 根据参数来确定url
 * @param flag 用来判断是哪个模块的删除请求
 * @returns
 */
function getUrl(flag){
	var url = [2];//[0]表示删除请求的地址;    [1]表示刷新页面时所需的数据请求地址
	switch(flag){
		//删除结算方式
		case "Settlement":
			url[0] = "${pageContext.request.contextPath}/settl_delSettlById.action?ids=";
			url[1] = "${pageContext.request.contextPath}/settl_findAllSett.action";
			break;
			//删除用户
		case "AclUser":
			url[0] = "${pageContext.request.contextPath}/users_deleteUser.action?ids=";
			url[1] = "${pageContext.request.contextPath}/users_findAllUser.action";
			break;
			//删除部门
		case "Department":
			url[0] = "${pageContext.request.contextPath}/dept_deleteDept.action?ids=";
			url[1] = "${pageContext.request.contextPath}/dept_findAllDept.action";
			break;
			//删除仓库
		case "Stock":
			url[0] = "${pageContext.request.contextPath}/stock_deleteStock.action?ids=";
			url[1] = "${pageContext.request.contextPath}/stock_findAllStock.action";
			break;
			//删除交易货币
		case "Curr":
			url[0] = "${pageContext.request.contextPath}/currencies_del.action?ids=";
			url[1] = "${pageContext.request.contextPath}/currencies_findAllCurrencies.action";
			break;
			//删除计量单位
		case "Unit":
			url[0] = "${pageContext.request.contextPath}/unit_deleteUnit.action?ids=";
			url[1] = "${pageContext.request.contextPath}/unit_findAllUnit.action";
			break;
			//删除物料类型
		case "MaterialType":
			url[0] = "${pageContext.request.contextPath}/mater_deleteMaterialType.action?ids=";
			url[1] = "${pageContext.request.contextPath}/mater_findAllMaterialType.action";
			break;
		case "Quotation":
			url[0] = "${pageContext.request.contextPath}/quotation_deleteQuotation.action?ids=";
			url[1] = "${pageContext.request.contextPath}/quotation_findQuotations.action";
	}
	return url;
}

//等待加载，转圈gif样式脚本
function loadgif(){
	
}
