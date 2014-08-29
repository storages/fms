
$(function(){
	initEditfun();
	initDeletefun();
	initLogunButton();
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
		if(names&&names!=''){data["names"]=names;}
		//alert(pageclickednumber);
		$.post(url, data, function(result){
		 var json=$.parseJSON(result);
		 if(json.success){
			 $("#tbody tr").remove();
				 $("#SXrow").tmpl(json.obj).appendTo("#tbody");  
				  initEditfun();
				  initDeletefun();
				  initLogunButton();
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
	$("#searchbutton").click(function(){
		var val= $("#search").val();
		if(val&&val!=''){
			var url=Global+"/empl_employees.action?names="+val;
			toMain(url);
		}else{
			toMain(Global+"/empl_employees.action");
		}

	});
	//清空按钮
	function initLogunButton(){
		$("a[create-loginUser]").click(function(){
		 $("#emptyId").val($(this).attr("create-loginuser"));
			initcanvas();
		});

		$("a[cancel-loginUser]").click(function(){
			var url=Global+"/empl_cancelLoUs.action";
			var id=$(this).attr("cancel-loginUser");
		    var data={};
		    data["ids"]=id;
		    $.post(url,data,function(result){
		           var json=$.parseJSON(result);
		            if(json.success){
		            	   $(".pgCurrent").click();
			             alert("操作成功");
		            }else{
			            alert(json.msg);
		                }
	             });
		});
	}
	$("#closecanvas").click(function(){
		closecanvas();
	});
	/**
	 * 保存用户
	 */
	$("#saveuserbutton").click(function(){
		//获取数据
		var emid= $("#emptyId").val();
		var loginName= $("#loginName").val();
		var password= $("#password").val();
		var passwordto= $("#passwordto").val();
		//发送请求保存数据
		var url= Global+"/empl_updateisLous.action"; 
		var param={};
		if(password!=passwordto){
			alert("两次密码不一致");
			return false;
		}
		param["user.employee.id"]=emid;
		param["user.loginName"]=loginName;
		param["user.password"]=password;
		param["passwordto"]=passwordto;
		$.post(url,param,function(data){
		   var json= $.parseJSON(data);
		   if(json.success){
			   $(".pgCurrent").click();
			   alert("创建成功");
			   closecanvas();
			 
		   }else{
			   alert("操作失败了"+json.msg);
		   }
		});
		
		//关闭帆布
	});
	
	//初始化帆布】
  function initcanvas(){
	  //alert("123");
	  //获取屏幕高端   宽度  上传文件DIV 宽度 和高度
		 var $canvas =$("#canvas");
		 var $div=$("#ulploddiv");
	 var avheight= window.screen.availHeight; 
	 var avwidth = window.screen.availWidth; 
	 var divheight= parseInt($div.css("height"));
	 var divwidth = parseInt($div.css("width"));
	 var topsize= (avheight-divheight)/2;
	 var leftsize= (avwidth-divwidth)/2;
	 $canvas.css("height",avheight+"px");
	 $canvas.css("width",avwidth+"px");
	 $canvas.css("display","block");
	 $("body").css("overflow","hidden");
	 $div.css("display","block");
	 $div.css("top",topsize+"px");
	 $div.css("left",leftsize+"px");
	 
  }
  /**
   * 关闭帆布
   */
  function closecanvas(){
	var $canvas =$("#canvas");
	var $div=$("#ulploddiv");
	$canvas.css("display","none");
	$("body").css("overflow","");
	$("#ulploddiv #loginName").val("");
	$("#ulploddiv #password").val("");
	$("#ulploddiv #passwordto").val("");
	 $div.css("display","none");
  }
  
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
