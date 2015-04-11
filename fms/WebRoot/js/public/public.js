//转换字符编码，以防把参数【中文】传到后台变乱码
function parse(str){
	return encodeURI(encodeURI(str));  
}

/**
 * 获取浏览器类型
 */
function getBrowserType(){
	var isChrome = navigator.userAgent.toLowerCase().match(/chrome/) != null;//判断是否是谷歌浏览器
	if($.browser.msie) {  
	    //IE浏览器
		return "IE";
	 }else if($.browser.opera) {  
	    //opera浏览器
	    return "OPERA";
	 }else if($.browser.mozilla) {  
	    //火狐浏览器
	    return "MOZILLA";
	 }else if(isChrome){
		 //谷歌浏览器
		 return "chrome";
	 }
}


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
		var url = "${pageContext.request.contextPath}/materInfo_deleteMaterial.action?";
		$.ajax({
			  type: 'POST',
			  url: url,
			  data: {ids:ids,imgExgFlag:flag},
			  success: function(args){
				  var result=jQuery.parseJSON(args);
			    	if(!result.success){
			    		alert(result.msg);
			    		return;
			    	}
			    	alert(result.msg);
			    	toMain("${pageContext.request.contextPath}/materInfo_findAllMaterial.action?imgExgFlag=I");
			     },error:function(){
			        	$("#mess").html("程序异常，请重新启动程序！");
			        	return false;
			      } 
			});
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
		var url = "${pageContext.request.contextPath}/materInfo_deleteMaterial.action";
		$.ajax({
			  type: 'POST',
			  url: url,
			  data: {ids:splitStr,imgExgFlag:flag},
			  success: function(args){
				  var result=jQuery.parseJSON(args);
			    	if(!result.success){
			    		alert(result.msg);
			    		return;
			    	}
			    	alert(result.msg);
			    	toMain("${pageContext.request.contextPath}/materInfo_findAllMaterial.action?imgExgFlag=I");
			     },error:function(){
			        	$("#mess").html("程序异常，请重新启动程序！");
			        	return false;
			      } 
			});
		//toMain(url);
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
	}
	/*else if(ids!=""){
		splitStr = ids+",";
	}*/
	splitStr = splitStr.substring(0, splitStr.length-1);
	var resultUrl = getUrl(flag);
	var url = resultUrl[0];
	var mess = "";
	if(flag=="BomExg"){
		mess = "你确认要删除吗？,这样会将该BOM下的所有物料Bom以及版本号全部删除!";
	}else{
		mess = "你确认要删除吗？";
	}
	if(confirm(mess)){
		$.ajax({
		     type: "POST",
		     url:url,
		     data:{ids:splitStr},
		     async: false,
		     cache: false,
		     success:function(args){
		    	var result=jQuery.parseJSON(args);
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
		if('AppBillItem'==flag){
			window.location.href = refreshUrl;
		}else{
			toMain(refreshUrl);
		}
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
			url[0] = "${pageContext.request.contextPath}/settl_delSettlById.action";
			url[1] = "${pageContext.request.contextPath}/settl_findAllSett.action";
			break;
			//删除用户
		case "AclUser":
			url[0] = "${pageContext.request.contextPath}/users_deleteUser.action";
			url[1] = "${pageContext.request.contextPath}/users_findAllUser.action";
			break;
			//删除部门
		case "Department":
			url[0] = "${pageContext.request.contextPath}/dept_deleteDept.action";
			url[1] = "${pageContext.request.contextPath}/dept_findAllDept.action";
			break;
			//删除仓库
		case "Stock":
			url[0] = "${pageContext.request.contextPath}/stock_deleteStock.action";
			url[1] = "${pageContext.request.contextPath}/stock_findAllStock.action";
			break;
			//删除交易货币
		case "Curr":
			url[0] = "${pageContext.request.contextPath}/currencies_del.action";
			url[1] = "${pageContext.request.contextPath}/currencies_findAllCurrencies.action";
			break;
			//删除计量单位
		case "Unit":
			url[0] = "${pageContext.request.contextPath}/unit_deleteUnit.action";
			url[1] = "${pageContext.request.contextPath}/unit_findAllUnit.action";
			break;
			//删除物料类型
		case "MaterialType":
			url[0] = "${pageContext.request.contextPath}/mater_deleteMaterialType.action";
			url[1] = "${pageContext.request.contextPath}/mater_findAllMaterialType.action";
			break;
			//删除报价单
		case "Quotation":
			url[0] = "${pageContext.request.contextPath}/quotation_deleteQuotation.action";
			url[1] = "${pageContext.request.contextPath}/quotation_findQuotations.action";
			break;
			//删除申请单表头
		case "AppBillHead":
			url[0] = "${pageContext.request.contextPath}/appbill_deleteAppBillHead.action";
			url[1] = "${pageContext.request.contextPath}/appbill_findAppBillHeads.action";
			break;
			//删除申请单表体
		case "AppBillItem":
			var id = $('#head').val();
			url[0] = "${pageContext.request.contextPath}/appbill_deleteAppBillItem.action";
			url[1] = "${pageContext.request.contextPath}/appbill_findItemByHid.action?ids="+id;
		case "BomExg":
			url[0] = "${pageContext.request.contextPath}/bom_delBomExg.action";
			url[1] = "${pageContext.request.contextPath}/bom_findBomExg.action";
			break;
		case "BomImg":
			var verNo = $("#form-field-select-1").find("option:selected").text();
			url[0] = "${pageContext.request.contextPath}/bom_delBomImg.action?verNo="+verNo;
			url[1] = "${pageContext.request.contextPath}/bom_detailList.action?ids="+$("#hid").val()+"&verNo="+verNo;
			break;
	}
	return url;
}

//获取浏览器高
function getTotalHeight(){
	if($.browser.msie){
		return document.compatMode == "CSS1Compat"? document.documentElement.clientHeight : document.body.clientHeight;
	}else {
		return self.innerHeight;
	}
}

//获取浏览器宽
function getTotalWidth (){
	if($.browser.msie){
		return document.compatMode == "CSS1Compat"? document.documentElement.clientWidth : document.body.clientWidth;
	}else{
		return self.innerWidth;
	}
}

//等待加载，转圈gif样式脚本
function loadgif(){
	
}

//列表全选功能
$("#checkAll").click(function(){
	if($("#checkAll").attr("checked")){
		$("input[name='sid']").attr("checked",'true');//全选 
	}else{
		$("input[name='sid']").removeAttr("checked");//取消全选
	}
});
