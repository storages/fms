var MAXAGE=100;
$(function(){
	$("#birthday").datepicker({
		changeYear: true,
		changeMonth: true,
		yearRange: '1900:', 
		dateFormat: 'yy-mm-dd'
	});
	$("#entryDate").datepicker({
		changeYear: true,
		changeMonth: true,
		yearRange: '1900:', 
		dateFormat: 'yy-mm-dd'
	});
	/**
	 * 循环显示年龄
	 */
	for(var x=1;x<=MAXAGE;x++){
		$("#age").append("<option value='"+x+"'>"+x+"</option>");
	}
	$("#saveEmpl").click(function(){
		//检索字段
		$("form:first").submit();
	});
	document.getElementById("saveEmpframe").onload=function(){
		 var thisDocument=this.contentDocument||this.contentWindow.document; 
	      var html=  $(thisDocument.body).find("pre").html();
	       var json= jQuery.parseJSON(html);
	       if(json.success){
	    	   alert("保存成功");
	       }else{
	    	   alert("保存失败"+json.msg);
	       }

	};
	
$("#isloginUser").click(function(){
	var $tag=$(this);
	var tr= $("#loginuserTR");
	var td= $("#loginuserTR td");
	td.css("background","#EFF3F8");
	td.css("color","red");
	if($tag.prop("checked")){
		tr.css("display","");
	}else{
		tr.css("display","none");	
	}
});
	
	
});