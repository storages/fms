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
	$("body").append("<div style='width:100%;height:100%;position:absolute;top:0;left:0;filter: Alpha(opacity=5);-moz-opacity:.5;opacity:0.5;background:#000;z-index:9000;' id='lockdiv'></div>");
}

//给dialog添加标头
function addTitle(){
	var firstele;
	var num = $('#dialog').children().length;
	var htmltext = $('#dialog').html();
	var str = '<div id="titleele" style="height:35px;width:100%;background-color:#99CCFF;"></div>'
	var toolbar = '<div style="height:auto; width:100%;" id="tool"><p style="margin-left: 40%;"><input class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" value="保存"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="btn btn-small btn-danger pull-left" data-toggle="button" type="button" value="取消" style="margin-left: 10px;"  onclick="closeListenler()"/></p></div>';
	if(num==''){
		firstele = $('#dialog'); 
		firstele.append(str);
	}else if(num>0){
		firstele = $('#dialog').children(":first");
		firstele.before(str);
		if($('#outside').length==0){
			$('#dialog').html(str+"<div id='outside' style='width:99%;height:80%;overflow:auto;padding:2px;'>"+ htmltext +"</div>"+toolbar);
		}else if($('#outside').length>0){
			$('#dialog').html(str+ htmltext+toolbar);
		}
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
	$('#tool').remove();
	$('#dialog').hide();
	
}

