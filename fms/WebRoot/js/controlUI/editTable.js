var tag = 0;

function getModifyData(){
	var dataArr = [];
	$("#sample-table-1 tr").each(function() {
			var arr = [];
			var inputcount = $(this).find("input").length;
			if(undefined!=inputcount && ""!=inputcount && inputcount>1){
				$(this).find("input").each(function() {
					var objectValue = $(this).val();
					if(undefined!=objectValue && ""!=objectValue){
						var objectValue =objectValue=="　"?null:objectValue.replace(/　/g,"").replace(/\s/g,"").replace("　","");//去除中文全角空格
						arr.push(encodeURI(encodeURI(objectValue)));//把中文字符转换成特定编码，如：%45%56...类型,以防乱码！
						//alert(encodeURI(encodeURI(objectValue)));
					}else{
						arr.push("empty");
					}
				});
				dataArr.push(arr);
			}
		});
	
	if(typeof JSON == undefined){
		$('head').append($("<script type='text/javascript' src='${pageContext.request.contextPath}/js/public/json2.js'>"));
	}
	if(dataArr.length<=0){
		return "";
	}else{
		var jsonstr = JSON.stringify(dataArr); //返回字符串
		return jsonstr;
	}
}

function showTableEdit(obj, array) {
	var tr = obj.parentNode.parentNode;
	var index = array.split(",");
	var tdarr = tr.childNodes;
	var j = 0;
	for ( var i = 0; i < tdarr.length; i++) {
		if (tdarr[i].nodeName == "TD") {
			j++;
			for ( var k = 0; k < index.length; k++) {
				if (index[k] == j) {
					var inputcounts = tdarr[i].getElementsByTagName("input").length;
					if(inputcounts>0){
						return;
					}
					var value = tdarr[i].innerHTML;
					var currWidth = tdarr[i].offsetWidth;
					var currHeight = tdarr[i].offsetHeight;
					if (tag > 0 && currWidth != tag) {
						currWidth = tag;
					}
					tdarr[i].style.padding = "0px";
					tdarr[i].style.paddingTop = "4px";
					tdarr[i].innerHTML = "<input type='text' value='"
							+ value.trim()
							+ "' id='"
							+ i
							+ "_"
							+ j
							+ "' style='height:36px;width:"
							+ (currWidth+35)
							+ "px;margin:0px;padding:0px;background-color: #FFFFCC;font-size:12px;color:black;'/>";
					var ele = $("#" + i + "_" + j);
					// ele.css("width",currWidth);
					/*
					 * ele.css("height","25"); ele.css("margin","0");
					 * ele.css("padding","0");
					 */
					tag = currWidth;
				}
			}
		}
	}
	obj.disabled=false;
}

function closeTableEdit(obj, array) {
	var tr = obj.parentNode.parentNode;
	var index = array.split(",");
	var tdarr = tr.childNodes;
	var j = 0;
	for ( var i = 0; i < tdarr.length; i++) {
		if (tdarr[i].nodeName == "TD") {
			j++;
			for ( var l = 0; l < tdarr[i].childNodes.length; l++) {
				if (tdarr[i].childNodes[l].nodeName == "INPUT") {
					for ( var k = 0; k < index.length; k++) {
						if (index[k] == j) {
							var value = tdarr[i].childNodes[l].value;
							tdarr[i].innerHTML = "<td>" + value + "</td>";
						}
					}
				}
			}
		}
	}
}

function closeAllEdit(obj, array) {
	var inputs = document.getElementById(obj).getElementsByTagName("input");
	if (undefined != inputs && "" != inputs) {
		for ( var i = 0; i < inputs.length;i++) {
			if (inputs[i].type == "text") {
				var tdobj = inputs[i].parentNode;
				var value = inputs[i].value;
				tdobj.innerHTML = "<td>" + value + "</td>";
			}
		}
		inputs = document.getElementById(obj).getElementsByTagName("input");
		if (undefined != inputs && "" != inputs) {
			closeAllEdit(obj, array);
		}
	}
	
}