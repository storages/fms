//绑定dialog事件
$(function(){
	$('#showDialogForm').click(function(){
		showDialog();
	});
});

//显示dialog
function showDialog(){
	addParentElement();
	addTitle();
	addTitleClose();
	$('#dialog').show();
	
}

//创建锁屏
function addParentElement(){
	$("body").append("<div style='width:100%;height:100%;position:absolute;top:0;left:0;filter: Alpha(opacity=5);-moz-opacity:.5;opacity:0.5;background:#000;z-index:8888;' id='lockdiv'></div>");
}

//给dialog添加标头
function addTitle(){
	var firstele;
	var num = $('#dialog').children().length;
	if(num==''){
		firstele = $('#dialog');
		firstele.append('<div id="titleele" style="height:35px;width:100%;background-color:#99CCFF;margin-bottom:3px;"></div>');
	}else if(num>0){
		firstele = $('#dialog').children(":first");
		firstele.before('<div id="titleele" style="height:35px;width:100%;background-color:#99CCFF;margin-bottom:3px;"></div>');
	}
}

//显示关闭叉叉按钮
function addTitleClose(text){
	$('#titleele').append('<span style="float:right; width:auto;height:100%;line-height:35px;margin-right:5px;cursor: pointer;font-weight:bold;color:red;" id="close-button" onclick="closeListenler();">×</span>');
}

//添加关闭叉叉按钮的事件
function closeListenler(){
	$('#lockdiv').remove();
	$('#titleele').remove();
	$('#dialog').hide();
	
}

