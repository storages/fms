<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/datepicker/jquery-ui-1.8.16.custom.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/dialog/dialogCss.css" type="text/css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/utils/chinese-of-spell.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datepicker/jquery.ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datepicker/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datepicker/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dialog/dialog.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/public/public.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/controlUI/splitLine.js"></script>
<style type="text/css">
  body{padding:0px;margin:0px;}
  #bill_main,#bill_head,#bill_item,#toolbar{margin: 0px;padding:0px;width:100%;}
  #bill_main{height:100%;position:relative;}
  #bill_head{
	border-bottom: 2px solid black;
	position:absolute; 
    top:0;left:0; 
    width:100%;  
}
  #bill_item{border-top: 2px solid black;}

</style>

<div id="bill_main">
	<div class="page-header position-relative" style="margin: 0px; height:10px;line-height: 25px;">
		<span style="font-size: 14px; font-weight: bold;margin-left:5px; padding:3px 3px 0px 3px; border:solid 1px gray; border-bottom: 0px;">申请单</span>
	</div>
		<div id="bill_head">
			<div id="toolbar">
				<div id="splitline" style="width:100%;height:5px;background-color: gray;border:solid 1px gray; cursor:row-resize;"></div>
			</div>
		</div>
		<div id="bill_item"></div>
	</div>

	<script type="text/javascript">
	var main_height;
		$(function(){
			main_height = $('#bill_main').height();
			var head_height =$('#bill_main').height()/3-25;
			var item_height = main_height/2-25;
			$('#bill_head').css('height',head_height);
			$('#bill_item').css('height',item_height);
			//绑定需要拖拽改变大小的元素对象 
			$('#splitline').css("position","absolute"); 
			$('#splitline').css('top',head_height+ 'px');
			bindResize(document.getElementById('bill_head'),$('#splitline')); 
		});
</script> 