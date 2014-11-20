var tag = 0;
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
					console.info("text框有"+inputcounts+"个");
					if(inputcounts>0){
						return;
					}
					var value = tdarr[i].innerHTML;
					var currWidth = tdarr[i].offsetWidth;
					var currHeight = tdarr[i].offsetHeight;
					if (tag > 0 && currWidth != tag) {
						currWidth = tag;
					}
					tdarr[i].style.padding = "4px";
					tdarr[i].innerHTML = "<input type='text' value='"
							+ value
							+ "' id='"
							+ i
							+ "_"
							+ j
							+ "' style='height:25px;width:"
							+ currWidth
							+ ";margin:0px;padding:0px;background-color: #FFFFCC;font-size:11px;'/>";
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