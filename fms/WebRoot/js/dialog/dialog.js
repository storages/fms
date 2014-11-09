//绑定dialog事件
$(function(){
	$('#showDialogForm').click(function(){
		showDialog();
	});
	$('#showDialogForm1').click(function(){
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
	var htmltext = $('#dialog').html();
	var str = '<div id="titleele" style="height:35px;width:100%;background-color:#99CCFF;"></div>'
	if(num==''){
		firstele = $('#dialog'); 
		firstele.append(str);
	}else if(num>0){
		firstele = $('#dialog').children(":first");
		firstele.before(str);
		$('#dialog').html(str+"<div style='width:99%;height:89%;overflow:auto;padding:2px;'>"+ htmltext +"</div>");
	}
}

//显示关闭叉叉按钮
function addTitleClose(text){
	var titleText = $('#dialog').attr("title");
	if(titleText==undefined){
		titleText = "";
	}
	$('#titleele').append('<span style="float:left;margin-left:5px; width:auto;height:100%;line-height:35px;font-size:14px; font-weight:bold;">'+ titleText +'</span><span style="float:right; width:auto;height:100%;line-height:35px;margin-right:5px;cursor: pointer;font-weight:bold;color:red; font-size:14px;" id="close-button" onclick="closeListenler();">×</span>');
}

//添加关闭叉叉按钮的事件
function closeListenler(){
	$('#lockdiv').remove();
	$('#titleele').remove();
	$('#dialog').hide();
	
}

