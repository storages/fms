
$(function(){
	$("#login").click(findUserName);  
	function findUserName(){
		  	var username = $("input[name='username']").val();
		  	var password = $("input[name='password']").val();
		  	var forget = $("#remenber").attr("checked");
		  	if(username==""){
		  		$("#error").html("用户名不能为空");
		  		return;
		  	}
		  	if(password==""){
		  		$("#error").html("密码不能为空");
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
