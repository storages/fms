  	function findUserName(){
	  	var username = $("#username").val();
	  	var password = $("#password").val();
	  	var forget = $("#remenber").attr("checked");
	  	if(username==""){
	  		$("#mess").html("用户名不能为空");
	  		return;
	  	}
	  	if(password==""){
	  		$("#mess").html("密码不能为空");
	  		return;
	  	}
	  	//$("#mess").html("");
  		var url = "${pageContext.request.contextPath}/users_loginUser.action?userName="+username+"&password="+password+"&forget="+forget;
  		$.ajax({
	     type: "POST",
	     url:url,
	     async: false,
	     cache: false,
	     success:function(data){
	     	if("false"==data){
	     		$("#mess").html("用户名或密码不正确！");
	     		return;
	     	}else{
	           window.location.href="/fms/pages/main.jsp";
	           $("#mess").html("");
	     	};
	     },error:function(){
	        	$("#mess").html("登录异常，请重新启动程序！");
	      }
	  	});
  	}
