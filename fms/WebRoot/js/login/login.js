
$(function(){
	//获取屏幕的高度   让HTML 兼容IE 浏览器
	var clientHeight=window.screen.availHeight;
	$("#maincentent").css("height",clientHeight+"px");
	
	//alert(getCookie("password"));
	$("#login").click(findUserName);  
	function findUserName(){
		  	var username = $("input[name='username']").val();
		  	var password = $("input[name='password']").val();
		  	var forget = $("#remenber").is(":checked");
		  	
		  	if(username==""){
		  		$("#error").html("用户名不能为空");
		  		return;
		  	}
		  	if(password==""){
		  		$("#error").html("密码不能为空");
		  		return;
		  	}
		  	if(forget){
		  		addCookie("username",username);
		  		addCookie("password" ,username);
		  	}else{
		  		
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
