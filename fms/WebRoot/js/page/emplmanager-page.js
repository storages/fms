
$(function(){
	initEditfun();
	initDeletefun();
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
				  initDeletefun();
			
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
	$("#deleteAll").click(function(){
		ids=[];
		$("input[name='sid']").each(function(){
			var $tag=$(this);
			if($tag.prop("checked")){
				ids[ids.length]=$tag.val();
				
			}
			
		});
		if(ids.length<=0){
			alert("请至少选择一个要删除的员工");
			return false;
		}else{
			//进行删除   锁屏
			deletemeth(ids);
		}
	});
 
	function initDeletefun(){
		var ids=[];
		$("a[delete-emp]").click(function(){
		  if(confirm("确定删除此员工吗？同时会删除它的登录权限")){
			var id= $(this).attr("delete-emp");
			ids[0]=id;
			deletemeth(ids);
		  }
		});
		
	}
	function deletemeth(ids){
		var data={};
		data["ids"]=ids.toString();
		var url=Global+"/empl_deleteEmpl.action";
		$.post(url,data,function(result){
			var json= $.parseJSON(result);
			if(json.success){
				//刷新列表当页
				$(".pgCurrent").click();
				alert("删除成功！");
			}else{
				alert("删除失败稍后再试");
				$(".pgCurrent").click();
			}
		});
	} 
	
});
