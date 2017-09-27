function queryMoreTop(){ 
	var startnum = document.getElementById("tplWrapper").childElementCount;	
	$.ajax({
			type:"post",
			url:"/org.xjtusicd3.partner/getMoreFaqCommend.html",
			data:{
				"startnum":startnum
			},
			dataType:"json",
			success:function(data){
				for(var i in data.faqlists){
					var html = document.getElementById("tplWrapper").innerHTML;
				//	var time = data.faqlist[i].faqModifytime.substring(0,10).replace(/-/,'/');
					document.getElementById("tplWrapper").innerHTML = htmls+ '<div class="topList clearfix"><div class="imagesPotion"><a href="/detail/dc_143417.html" target="_blank"><img src="images/test/2'+data.faqlists[i]_index+1.jpg+'" alt=""></a></div>'
					+ '<ul class="topcontent">'
                    + '<li><p class="title"><a href="faq3.html?q=' + data.faqlists[i].FAQQUESTIONID +' " target="_blank">'+data.faqlists[i].FAQTITLE +'</a></p></li>'
                    + '<li class="clearfix"><span class="userPic"><img src="'+userImage+'"></span><span class="username">'+userName +'</span><span class="dot">-</span><span class="time">' + data.faqlists[i].MODIFYTIME + '</span><span class="line">|</span><span class="showCount">'+ data.faqlists[i].SCAN  + '</span><span class="message">34</span><span class="collection">'+ data.faqlists[i].COLLECTION + '</span></li>'
                    + '<li class="content">' + data.faqlists[i].FAQDESCRIPTION + '</li>'
                    + '</ul> </div>'
                    }
				if(data.startnum<data.totalnum){
					startnum = data.startnum;
				}else{
					document.getElementById("querymorelink").remove();
				}
			}
		});
}



$('#querymorelink').click(function() {
				var startnumber = document.getElementById("tplWrapper").childElementCount;

				$.ajax({
					type:"POST",
					url:"/org.xjtusicd3.partner/getMoreFaq.html",
					data:{
						"startnumber":startnumber				
					},
					dataType:"json",
					success:function(data){
						if(data.value=="0"){
							self.location='login.html';
						}else if(data.value=="1"){
							for(var i in data.faqlists){
								if(data.faqlists[i].userId==null){									
										var htmls = document.getElementById("tplWrapper").innerHTML;
										document.getElementById("tplWrapper").innerHTML = htmls+ '<div class="topList clearfix"><div class="imagesPotion"><a href="/detail/dc_143417.html" target="_blank"><img src="images/test/2'+data.faqlists[i]_index+1.jpg+'" alt=""></a></div>'
										+ '<ul class="topcontent">'
			                            + '<li><p class="title"><a href="/detail/dc_143417.html" target="_blank">'+data.faqlists[i].FAQTITLE +'</a></p></li>'
			                            + '<li class="clearfix"><span class="userPic"><img src="new/front/images/avatar.jpg"></span><span class="username">Iknow</span><span class="dot">-</span><span class="time">' + data.faqlists[i].MODIFYTIME + '</span><span class="line">|</span><span class="showCount">'+ data.faqlists[i].SCAN  + '</span><span class="message">34</span><span class="collection">'+ data.faqlists[i].COLLECTION + '</span></li>'
			                            + '<li class="content">' + data.faqlists[i].FAQDESCRIPTION + '</li>'
			                            + '</ul> </div>'
												                            
								}else{
									var htmls = document.getElementById("tplWrapper").innerHTML;
									document.getElementById("tplWrapper").innerHTML = htmls+ '<div class="topList clearfix"><div class="imagesPotion"><a href="/detail/dc_143417.html" target="_blank"><img src="images/test/2'+data.faqlists[i]_index+1.jpg+'" alt=""></a></div>'
									+ '<ul class="topcontent">'
		                            + '<li><p class="title"><a href="/detail/dc_143417.html" target="_blank">'+data.faqlists[i].FAQTITLE +'</a></p></li>'
		                            + '<li class="clearfix"><span class="userPic"><img src="new/front/images/avatar.jpg"></span><span class="username">Iknow</span><span class="dot">-</span><span class="time">' + data.faqlists[i].MODIFYTIME + '</span><span class="line">|</span><span class="showCount">'+ data.faqlists[i].SCAN  + '</span><span class="message">34</span><span class="collection">'+ data.faqlists[i].COLLECTION + '</span></li>'
		                            + '<li class="content">' + data.faqlists[i].FAQDESCRIPTION + '</li>'
		                            + '</ul> </div>'
								}
							}
							if(data.endnumber<data.totalnumber){
								startnumber = data.endnumber;
							}else{
								document.getElementById("querymorelink").remove();
							}
						}
					}
				})
			});