    	function tongzhishezhi(){
    		document.getElementById("js-setup-popl").style.display="block";
    		document.getElementById("bg_tongzhi").style.display="block";
    	}
    	function notongzhishezhi(){
    		document.getElementById("js-setup-popl").style.display="none";
    		document.getElementById("bg_tongzhi").style.display="none";
    	}
    	//防止mouseover多次触发
    	function contains(parentNode, childNode) 
		{
		    if (parentNode.contains) {
		        return parentNode != childNode && parentNode.contains(childNode);
		    } else {
		        return !!(parentNode.compareDocumentPosition(childNode) & 16);
		    }
		}
		function checkHover(e,target)
		{
		    if (getEvent(e).type=="mouseover")  {
		        return !contains(target,getEvent(e).relatedTarget||getEvent(e).fromElement) && !((getEvent(e).relatedTarget||getEvent(e).fromElement)===target);
		    } else {
		        return !contains(target,getEvent(e).relatedTarget||getEvent(e).toElement) && !((getEvent(e).relatedTarget||getEvent(e).toElement)===target);
		    }
		}
		function getEvent(e){
		    return e||window.event;
		}

		function showdelete(e, obj){
		    if(checkHover(e,obj)){
			event.target.parentNode.getElementsByClassName("del-box clearfix")[0].style.display="block";
		  }
		}
    	function hiddendelete(e, obj){
    		if(checkHover(e,obj)){
			event.target.parentNode.getElementsByClassName("del-box clearfix")[0].style.display="none";
		  }
    	}
		function deletenotice(){
			event.target.parentNode.parentNode.parentNode.parentNode.style.display="none";
			var type = event.target.parentNode.parentNode.parentNode.parentNode.getElementsByClassName("notice-type")[0].innerHTML;
			var id = event.target.parentNode.parentNode.parentNode.parentNode.id;
			var zhao = event.target.parentNode.parentNode.parentNode.getElementsByTagName("p")[0].className;
			var type2 ="";
			if(zhao=="notice-type "){
				type2 = event.target.parentNode.parentNode.parentNode.getElementsByClassName("notice-con")[0].innerHTML.substring(0,4);
			}else{
				type2 = event.target.parentNode.parentNode.parentNode.getElementsByClassName("notice-con pass")[0].innerHTML.substring(0,4);
			}
			$.ajax({
				type:"POST",
				url:"/org.xjtusicd3.partner/deleteNotice.html",
				data:{
					"id":id,
					"type":type,
					"type2":type2
				},
				dataType:"json",
				success:function(data){
					if(data.value=="0"){
						self.location='login.html';
					}
				}
			})
		}
		function updatenotice(){
			var zhao = event.target.parentNode.parentNode.parentNode.getElementsByTagName("p")[0].className;
			if(zhao=="notice-type "){
				var type = event.target.parentNode.parentNode.parentNode.parentNode.getElementsByClassName("notice-type")[0].innerHTML;
				var id = event.target.parentNode.parentNode.parentNode.parentNode.id;
				var type2 = event.target.parentNode.innerHTML.substring(0,4);
				document.getElementById(id).getElementsByClassName("notice-box clearfix")[0].getElementsByClassName("notice-type")[0].style="";
				document.getElementById(id).getElementsByClassName("notice-box clearfix")[0].getElementsByClassName("notice-type")[0].className="notice-type  already-read";
				document.getElementById(id).getElementsByClassName("notice-box clearfix")[0].getElementsByClassName("notice-con ")[0].className="notice-con pass ";
				$.ajax({
					type:"POST",
					url:"/org.xjtusicd3.partner/updateNotice.html",
					data:{
						"id":id,
						"type":type,
						"type2":type2
					},
					dataType:"json",
					success:function(data){
						if(data.value=="0"){
							self.location='login.html';
						}
					}
				})
			}

		}
		