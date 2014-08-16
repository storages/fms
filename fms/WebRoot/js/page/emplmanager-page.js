
$(function(){
	var PageClick = function(pageclickednumber,sumcount){
		$("#pager").pager({
			pagenumber:pageclickednumber,
			pagecount:sumcount,
			pagerows:DATA_ROWS,
			pageSum:maxPageCount,
			buttonClickCallback:PageClick
		});
		//提供的外方接口用于重写
		var url=Global+"/empl_employeesAjax.action";
		var data={};
		data["pageindex"]=pageclickednumber;
		data["pageReows"]=DATA_ROWS;
		//alert(pageclickednumber);
		$.post(url, data, function(result){
		 var json=$.parseJSON(result);
		 if(json.success){
			 $("#tbody tr").remove();
				 $("#SXrow").tmpl(json.obj).appendTo("#tbody");  
				  initEditfun();
			
		 }else{
			 alert("加载失败");
		 }
		
		});
		
		
		
	};
	
	$("#pager").pager({
		pagenumber:1,
		pageRows:DATA_ROWS,
		pageSum:maxPageCount,
		buttonClickCallback:PageClick
	});
	
	
	$("#addEmpl").click(function(){
		 toMain(Global+"/empl_addEmployee.action");
		 });
	function initEditfun(){
		$("a[edit-emp]").click(function(){
			var id= $(this).attr("edit-emp");
			toMain(Global+"/empl_editEmployee.action?emid="+id);
		});
	}

	
});
