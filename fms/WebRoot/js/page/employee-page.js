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
	//上传按钮
  $("#uploadButton").click(function(){
	  initcanvas();
  });
  $("#closecanvas").click(function(){
	  closecanvas();
  });
  $("#empreset").click(function(){
	  initEmpColums();
  });
	//初始化帆布】
  function initcanvas(){
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
  function closecanvas(){
	var $canvas =$("#canvas");
	var $div=$("#ulploddiv");
	$canvas.css("display","none");
	$("body").css("overflow","");
	$("#ulploddiv input[type='file']").val("");
	 $div.css("display","none");
  }
  
  
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
	$("#fileUpload").click(function(){
		//xxx
		$("form[name='uploadImg']").submit();
	});
	document.getElementById("saveEmpframe").onload=function(){
		 var thisDocument=this.contentDocument||this.contentWindow.document; 
	      var html=  $(thisDocument.body).find("pre").html();
	       var json= jQuery.parseJSON(html);
	       if(json.success){
	    	   if(json.msg=="add"){
	    	   var rlt=confirm("保存成功:是否继续添加");
	    	   if(rlt){	
	    		   initEmpColums();
	    	   }else{
	    		   toMain(Global+"/empl_employees.action");
	    	   }
	    	   }else{
	    		   alert("保存成功");
	    		   toMain(Global+"/empl_employees.action");
	    	   }
	       }else{
	    	   alert("保存失败"+json.msg);
	       }

	};
	document.getElementById("uploadimgframe").onload=function(){
		 var thisDocument=this.contentDocument||this.contentWindow.document; 
	      var html=  $(thisDocument.body).find("pre").html();
	       var json= jQuery.parseJSON(html);
	       if(json.success){
	    	   $("#photoImg").attr("src",domloadPath+json.msg);
	    	   $("#emplphoto").val(json.msg);
	    	   closecanvas();
	    	   alert("上传成功");
	       }else{
	    	   alert("上传失败"+json.msg);
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
/**
 * 
 */
function initEmpColums(){
	//获取所有input
	var gender =$("#gender").val(-1); //性别
	var age=$("#age").val(-1);//年龄
	var nation =$("#nation").val(-1);//名族
	var diploma=$("#diploma").val(-1);//学历
	var department=$("#department").val(-1);
	
	var code=$("#code").val(""); //登录名
	var name=$("#name").val("");//名称
	var isloginUser=$("#isloginUser").prop("checked",false);//是否用户
	var photoImg=$("#photoImg").attr("src",Global+"/images/defaulttopimg.jpg");//头像
	var nickName=$("#nickName").val("");
	var loginName=$("#loginName").val("");
	var password=$("#password").val("");
	var passwordto=$("#passwordto").val("");
	var identityCard=$("#identityCard").val("");
	var origo=$("#origo").val("");
	var address=$("#address").val("");
	var currentResidence=$("#currentResidence").val("");
	var linkPhone=$("#linkPhone").val("");
	var networkLink=$("#networkLink").val("");
	var positionName=$("#positionName").val("");
	var note=$("#note").val();
	
	var birthday=$("#birthday").val(""); 
	var entryDate=$("#entryDate").val(""); 
};	
	

$("a[edit-emp]").click(function(){
	var id= $(this).attr("edit-emp");
	toMain(Global+"/empl_editEmployee.action?emid="+id);
});
});