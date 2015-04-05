<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<style type="text/css">
.bg_lock {
	width:100%;
	height:100%;
	background:#000;
	color:white;
	position:absolute;
	left:0px;
	top:0px;
	filter: Alpha(opacity=0.3);
	-moz-opacity:.3;
	opacity:0.3;
	z-index:8888;
	display:none;
}
.dialog{
	width:60%;
	height:60%;
	border:solid #999 2px;
	position:absolute;
	left:auto;
	top:auto;
	background-color:#FFF;
	z-index:9999;
	display:none;
}
.dg_title{
	width:100%;
	height:40px;
	border-bottom:solid 2px #999;
	text-align:left
	margin-left:5px;
	line-height:40px;
}
.ti_css{
	width:auto;
	height:100%;
	font-size:16px;
	font-weight:bold;
	line-height:40px;
}
.dg_content{
	width:100%;
	height:78%;
	overflow:auto;
	text-align:center;
}
.dg_toolbar{
	width:100%;
	height:40px;;
	border-top:solid 1px #999;
	text-align:center;
	line-height:40px;
}
#ico_close{
	float:right;
	width:25px;
	line-height:40px;
	cursor:pointer;
}
</style>
<script type="text/javascript">
	function showDialog(title,loadJsp){
		var dgForm = document.getElementsByName("dg_div");
		for(var i=0;i<dgForm.length;i++){
			dgForm[i].style.display = "block";
		}
		document.getElementById("ti_mess").innerHTML = title;
		var _height = window.screen.availHeight-220;
		var _width = document.body.scrollWidth-220;
		var dg_height = document.getElementById("dialog_div").offsetHeight;
		var dg_width = document.getElementById("dialog_div").offsetWidth;
		document.getElementById("dialog_div").style.marginLeft = (_width-dg_width)/2+"px";
		document.getElementById("dialog_div").style.marginTop = (_height-dg_height)/2+"px";
		
		//加载dialog要显示的内容
		var url = "${pageContext.request.contextPath}/pages/dialoghtml/"+loadJsp;
		$("#dg_content").load(url);
	}
	
	function closeDialog(){
		var dgForm = document.getElementsByName("dg_div");
		for(var i=0;i<dgForm.length;i++){
			dgForm[i].style.display = "none";
		}
	}
	
	$("#btnCancel").click(function(){
		closeDialog();
	});
</script>
<!--<input type="text" value="" id="mes"/><input type="button" value="查询" id="btnQuery" onclick="query('测试Dialog');"/>-->
<div class="bg_lock" name="dg_div"></div>
 <div class="dialog" id="dialog_div"  name="dg_div">
 	<div class="dg_title"><span class="ti_css" id="ti_mess"></span><span id="ico_close" onclick="closeDialog();">X</span></div>
    <div class="dg_content" id="dg_content">
    	
    </div>
    <div class="dg_toolbar">
    	<input type="button"  value="确定" id="btnSure"/>
        <input type="button"  value="取消" id="btnCancel"/>
    </div>
 </div>
