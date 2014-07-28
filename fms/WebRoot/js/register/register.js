function checkInfo(){
	var loginName = $("#loginName").val();
	var userName = $("#userName").val();
	var password = $("#password").val();
	var password2 = $(":input[name=password2]").val();
	var returnval = true;
	clearMessage();
	if(""==loginName){
		$("#loginNamemess").html("*不能为空");
		returnval = false;
	}
	if(""==userName){
		$("#userNamemess").html("*不能为空");
		returnval = false;
	}
	if(""==password){
		$("#passwordmess").html("*不能为空");
		returnval = false;
	}
	if(""==password2){
		$("#password2mess").html("*不能为空");
		returnval = false;
	}
	if(password!=password2){
		$("#password2mess").html("*密码不一致");
		returnval = false;
	}
	return returnval;
}

function clearMessage(){
	$("#loginNamemess").html("*");
	$("#userNamemess").html("*");
	$("#passwordmess").html("*");
	$("#password2mess").html("*");
}