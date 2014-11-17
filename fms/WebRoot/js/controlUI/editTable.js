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
						tdarr[i].innerHTML="<input type='text' value='"+ value +"'/>";
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