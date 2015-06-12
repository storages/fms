
$(function(){
	//获取屏幕的高度   让HTML 兼容IE 浏览器
	var clientHeight=window.screen.availHeight;
	$("#maincentent").css("height",clientHeight+"px");
	//初始化判断是否保存了cookie
	if(getCookie("username")!=null){
		$("input[name='username']").val(getCookie("username"));
	}
	if(getCookie("password")!=null){
		$("input[name='password']").val(getCookie("password"));
		$("#remenber").prop("checked",true);
	}
	
	$("#uname").keyup(function(e){
		if(e.keyCode==13){
			$("#pass").focus();
		}
	});
	//添加回车自动登录
	$("#pass").keyup(function(e){
		if(e.keyCode==13){
			findUserName();
		}
	});
	
	document.onkeydown = function(e){ 
	    var ev = document.all ? window.event : e;
	    if(ev.keyCode==13) {
	    	findUserName();
	     }
	}
	
	 // 登陆按钮
	$("#login").click(findUserName);  
	function findUserName(){
		  	var username = $("input[name='username']").val();
		  	var password = $("input[name='password']").val();
		  	var forget = $("#remenber").is(":checked");
		  	
		  	if(username.trim().length<=0||username.trim()==""){
		  		$("#error").html("用户名不能为空");
		  		$("#uname").focus();
		  		return;
		  	}
		  	if(password.trim().length<=0||password.trim()==""){
		  		$("#error").html("密码不能为空");
		  		$("#pass").focus();
		  		return;
		  	}
		  	if(forget){//保存
		  		addCookie("username",username);
		  		addCookie("password" ,username);
		  	}else{//删除
		  		delCookie("username");
		  		delCookie("password");
		  	}
		  	//$("#mess").html("");
	  		var url = "${pageContext.request.contextPath}/users_loginUser.action?userName="+username+"&password="+password+"&forget="+forget;
	  		$.ajax({
		     type: "POST",
		     url:url,
		     async: false,
		     cache: false,
		     success:function(data){
		    var result=jQuery.parseJSON(data);

		     	if(!result.success){
		     		$("#error").html(result.msg);
		     		$("#pass").select();
		     		return;
		     	}else{
		           window.location.href="/fms/pages/main.jsp";
		           $("#error").html("");
		     	};
		     },error:function(){
		        	$("#error").html("登录异常，请重新启动程序！");
		      }
		  	});
	  	}
});
String.prototype.trim=function(){
    return this.replace(/(^\s*)|(\s*$)/g, "");
 };
 String.prototype.ltrim=function(){
    return this.replace(/(^\s*)/g,"");
 };
 String.prototype.rtrim=function(){
    return this.replace(/(\s*$)/g,"");
 };