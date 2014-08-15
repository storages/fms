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
	for(var x=1;x<=MAXAGE;x++){
		$("#age").append("<option value='"+x+"'>"+x+"</option>");
	}
	$("#gender").val(selectgender);
	$("#age").val(selectAge);
	$("#nation").val(selectnation);
	$("#diploma").val(selectDiploma);
	$("#department").val(selectDepartment);
	//上传按钮
  $("#uploadButton").click(function(){
	  initcanvas();
  });
  //取消上传
  $("#closecanvas").click(function(){
	  closecanvas();
  });
  //新增
  $("#empreset").click(function(){
	  initEmpColums();
  });
  
  /**
	 * 循环显示年龄
	 */
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
  /**
   * 关闭帆布
   */
  function closecanvas(){
	var $canvas =$("#canvas");
	var $div=$("#ulploddiv");
	$canvas.css("display","none");
	$("body").css("overflow","");
	$("#ulploddiv input[type='file']").val("");
	 $div.css("display","none");
  }
  
	$("#saveEmpl").click(function(){
		//检索字段
		if(verifyFrom()){
		$("form:first").submit();
		}
	});
	
	$("#fileUpload").click(function(){
		//xxx
		$("form[name='uploadImg']").submit();
	});
	
	function verifyFrom(){
		var code=$("#code").val(); 
		if(code.trim().length<=0){
			alert("员工编号不能为空");
			return false;
		}
		
		var name=$("#name").val();//名称
		if(name.trim().length<=0){
			alert("姓名不能为空");
			return false;
		}
		
	
		var nickName=$("#nickName").val();
		
		
		var gender =$("#gender option:selected").val(); //性别
		if(gender==-1){
			alert("请选择性别");
			return false;
		} 
		var age=$("#age option:selected").val();
		if(age==-1){
			alert("请选择年龄");
			return false;
		}
		var nation =$("#nation option:selected").val();//名族
		var diploma=$("#diploma option:selected").val();//学历
		var department=$("#department option:selected").val();//部门
		if(department==-1){
			alert("请选择部门");
		return false;}
		
		var isloginUser=$("#isloginUser").prop("checked");//是否用户
		if(isloginUser){
			var loginName=$("#loginName").val();
			if(loginName.trim().length<=3){
				alert("请输入正确的登陆名  登陆名不能少于3位数");
				return false;
			}
			var password=$("#password").val();
			if(password.trim().length<=5){
				alert("登陆密码不能为空 并且不能少于六位数");
				return false;
			}
			var passwordto=$("#passwordto").val();
			if(password.trim()!=passwordto.trim()){
				alert("两次输入的密码不一致 重新输入");
				return false;
			}
			
		}
		
		
		var identityCard=$("#identityCard").val();
		if(identityCard.trim().length<=0){
			alert("请输入身份证号码");
			return false;
		}
		if(identityCard.trim().length<=14){
			alert("请输入正确的身份证号码");
			return false;
		}
		var origo=$("#origo").val();
		var address=$("#address").val();
		var currentResidence=$("#currentResidence").val();
		if(currentResidence.trim().length<=0){
			alert("请输入现居住地址");
			return false;
		}
		var linkPhone=$("#linkPhone").val();
		if(linkPhone.trim().length<=0){
			alert("请输入联系电话");
			return false;
		}
		if(linkPhone.trim().length<=10){
			alert("请输入正确的联系电话");
			return false;
		}
		var networkLink=$("#networkLink").val();
		var positionName=$("#positionName").val();
		if(positionName.trim().length<=0){
			alert("请输入职称");
			return false;
		}
		var note=$("#note").val();
		
		var birthday=$("#birthday").val();
		if(birthday.length<=0){
			alert("请选择出生日期");
			return false;
		}
		
		var entryDate=$("#entryDate").val(); 
		if(entryDate.length<=0){
			alert("请选择入职日期");
			return false;
		}
		return true;
	}
	 // 异步提交员工IFrame 
	var saveEmplframe= document.getElementById("saveEmpframe");
	//异步上传图片iframe
	var uploadimgframe= document.getElementById("uploadimgframe");
	//判断IE 解决兼容问题
	if(saveEmplframe.attachEvent){ // IE  
		saveEmplframe.attachEvent('onload', emplsaveload);  
    }else{
	    saveEmplframe.onload=emplsaveload;
    }
	//保存员工回调函数
	var emplsaveload=function(){
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
	//图片上传回调函数  //判断IE 解决兼容问题
	if(uploadimgframe.attachEvent){ // IE  
		uploadimgframe.attachEvent('onload', uploadimgload);  
    }else{
    	uploadimgframe.onload=uploadimgload;
    }
	
	var uploadimgload=function(){
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
	$("#name").change(function(){
		if($("#isloginUser").prop("checked")){
			     var name=$("#name").val();
			     $("#loginName").val(codefans_net_CC2PY(name).toLowerCase()); 
		}
	});
$("#isloginUser").click(function(){
	var $tag=$(this);
	var tr= $("#loginuserTR");
	var td= $("#loginuserTR td");
	td.css("background","#EFF3F8");
	td.css("color","red");
	if($tag.prop("checked")){
		var name=$("#name").val();
		if(name){
			$("#loginName").val(codefans_net_CC2PY(name).toLowerCase());
		}
		$("#password").val(123456);
		$("#passwordto").val(123456);
		tr.css("display","");
	}else{
		tr.css("display","none");	
	}
});
/**
 * 重置INput
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