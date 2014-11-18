var tag = 0;
function showTableEdit(obj,array){
	var tr = obj.parentNode.parentNode;
	var index = array.split(",");
	var tdarr = tr.childNodes;
	var j = 0;
		for(var i=0;i<tdarr.length;i++){
			if(tdarr[i].nodeName=="TD"){
				j++;
				for(var k=0;k<index.length;k++){
					if(index[k]==j){
						var value = tdarr[i].innerHTML;
						var currWidth = tdarr[i].offsetWidth; 
						var currHeight = tdarr[i].offsetHeight;
						if(tag>0&&currWidth!=tag){
							currWidth = tag;
						}
						tdarr[i].style.padding = "4px";
						tdarr[i].innerHTML="<input type='text' value='"+ value +"' id='"+i+"_"+j+"' style='height:25px;width:"+ currWidth +";margin:0px;padding:0px;background-color: #FFFFCC;'/>";
						var ele = $("#"+i+"_"+j);
						//ele.css("width",currWidth);
						/*ele.css("height","25");
						ele.css("margin","0");
						ele.css("padding","0");*/
						tag = currWidth;
					}
				}
		}
	}
}

function closeTableEdit(obj,array){
	var tr = obj.parentNode.parentNode;
	var index = array.split(",");
	var tdarr = tr.childNodes;
	var j = 0;
		for(var i=0;i<tdarr.length;i++){
			if(tdarr[i].nodeName=="TD"){
				j++;
				for(var l=0;l<tdarr[i].childNodes.length;l++){
					if(tdarr[i].childNodes[l].nodeName=="INPUT"){
						for(var k=0;k<index.length;k++){
							if(index[k]==j){
								var value = tdarr[i].childNodes[l].value;
								tdarr[i].innerHTML="<td>"+ value +"</td>";
							}
						}
					}
			}
		}
	}
}