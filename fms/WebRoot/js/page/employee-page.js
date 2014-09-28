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
		saveEmplframe.attachEvent('onload', function(){
			var html= document.frames["saveEmpframe"].document.body.innerHTML;
			if(html&&html!=""){
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
			}else{
				//不做任何处理
			}
		});  
    }else{
	    saveEmplframe.onload=function(){
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
    }
	//图片上传回调函数  //判断IE 解决兼容问题
	if(uploadimgframe.attachEvent){ // IE  
		uploadimgframe.attachEvent('onload',function(){
		var html= document.frames["uploadimgframe"].document.body.innerHTML;
		if(html&&html!=""){
			  var json= jQuery.parseJSON(html);
			  if(json.success){
	    	       $("#photoImg").attr("src",domloadPath+json.msg);
	    	       $("#emplphoto").val(json.msg);
	    	       closecanvas();
	    	       alert("上传成功");
	          }else{
	    	       alert("上传失败"+json.msg);
	          }
		}else{
		//不做任何处理
		}
	       
		});  
    }else{
    	uploadimgframe.onload=function(){
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
    }
	
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
	//性别
	if($("#gender").attr("data-val")){
		var gender =$("#gender").val($("#gender").attr("data-val"));
	}else{
		var gender =$("#gender").val(-1);
	}
	//年龄
	if($("#age").attr("data-val")){
		var age=$("#age").val($("#age").attr("data-val"));
	}else{
		var age=$("#age").val(-1);
	}
	//名族
	if($("#nation").attr("data-val")){
		var nation =$("#nation").val($("#nation").attr("data-val"));
	}else{
		var nation =$("#nation").val(-1);
	}
	//学历
	if($("#diploma").attr("data-val")){
		$("#diploma").val($("#diploma").attr("data-val"));
	}else{
		var diploma=$("#diploma").val(-1);
	}
	if($("#department").attr("data-val")){
		var department=$("#department").val($("#department").attr("data-val"));
	}else{
		var department=$("#department").val(-1);
	}
	
	if($("#code").attr("data-val")){
		var code=$("#code").val($("#code").attr("data-val")); 
	}else{
		var code=$("#code").val("");
	}
	if($("#name").attr("data-val")){
		var code=$("#name").val($("#name").attr("data-val")); 
	}else{
		var code=$("#name").val("");
	}
	//是否用户
	if($("#isloginUser").attr("data-val")){
		var isloginUser=$("#isloginUser").prop("checked",$("#isloginUser").attr("data-val"));
	}else{
		var isloginUser=$("#isloginUser").prop("checked",false);
	}
	if($("#emplphoto").attr("data-val")){
		var photoImg=$("#photoImg").attr("src",domloadPath+$("#emplphoto").attr("data-val"));//头像
	}else{
		var photoImg=$("#photoImg").attr("src",Global+"/images/defaulttopimg.jpg");//头像
	}
	
	if($("#nickName").attr("data-val")){
		var nickName=$("#nickName").val($("#nickName").attr("data-val"));
	}else{
		var nickName=$("#nickName").val("");
	}
    if($("#loginName").attr("data-val")){
    	var loginName=$("#loginName").val($("#loginName").attr("data-val"));
    }else{
    	var loginName=$("#loginName").val("");
    }
   if($("#password").attr("data-val")){
		var password=$("#password").val($("#password").attr("data-val"));
   }else{
		var password=$("#password").val("");
   }
   
  if($("#passwordto").attr("data-val")){
	  var passwordto=$("#passwordto").val($("#passwordto").attr("data-val"));
   }else{
	   var passwordto=$("#passwordto").val("");
    }
  
	if($("#identityCard").attr("data-val")){
		var identityCard=$("#identityCard").val($("#identityCard").attr("data-val"));
	}else{
		var identityCard=$("#identityCard").val("");
	}
    if($("#origo").attr("data-val")){
    	var origo=$("#origo").val($("#origo").attr("data-val"));
    }else{
    	var origo=$("#origo").val("");
    }	
    if($("#address").attr("data-val")){
    	var address=$("#address").val($("#address").attr("data-val"));
    }else{
    	var address=$("#address").val("");
    }
	if($("#currentResidence").attr("data-val")){
		var currentResidence=$("#currentResidence").val($("#currentResidence").attr("data-val"));
	}else{
		var currentResidence=$("#currentResidence").val("");
	}
	if($("#linkPhone").attr("data-val")){
		var linkPhone=$("#linkPhone").val($("#linkPhone").attr("data-val"));
	}else{
		var linkPhone=$("#linkPhone").val("");
	}
	if($("#networkLink").attr("data-val")){
		var networkLink=$("#networkLink").val($("#networkLink").attr("data-val"));
	}else{
		var networkLink=$("#networkLink").val("");
	}
    if($("#positionName").attr("data-val")){
    	var positionName=$("#positionName").val($("#positionName").attr("data-val"));
    }else{
    	var positionName=$("#positionName").val("");
    }
	if($("#note").attr("data-val")){
		var note=$("#note").val($("#note").attr("data-val"));
	}else{
		var note=$("#note").val("");
	}
	
	$("#loginuserTR").css("display","none");
	if($("#birthday").attr("data-val")){
		var birthday=$("#birthday").val($("#birthday").attr("data-val")); 
	}else{
		var birthday=$("#birthday").val(""); 
	}
	if($("#entryDate").attr("data-val")){
		var entryDate=$("#entryDate").val($("#entryDate").attr("data-val")); 
	
	}else{
		var entryDate=$("#entryDate").val(""); 
	}
	
}

$("a[edit-emp]").click(function(){
	var id= $(this).attr("edit-emp");
	toMain(Global+"/empl_editEmployee.action?emid="+id);
});
});