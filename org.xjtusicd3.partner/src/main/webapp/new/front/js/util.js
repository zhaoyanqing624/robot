// JavaScript Document
/**
* 知识库公共js代码
* @username zhangxiaogeng
*/

var ajaxTimeout = 180000;
var sysPath = "";		//调用服务路径
var navListObj={};
var knowledgeData={};
var countriesArray = {};

$(function(){

});

function autocompleteQuery(){
     Data.ajaxPostCall("/api/FullTextSearch/suggest","search="+$('#keyWordText').val(),function(data){
        return data;
    });
}

// function total(curTitle,playerNameAll,loginType) {
//     console.log("调取函数"+curTitle+"11:"+playerNameAll+"类型："+loginType);
//     var defaults = {
//         src: 'http://miaoooooo.com:3000/add',
//         data: {
//             project: '导航登录注册统计',
//             page: curTitle,           //当前页面title
//             openid: playerNameAll,         //用户账号
//             type: 'click',
//             name: loginType            //登录/注册/退出事件
//         }
//     }
//     var img = new Image(1, 1);
//     var item = '';
//     for (var i in defaults.data) {
//         item += i + '=' + defaults.data[i] + '&';
//     }
//     rnd_id="_img_"+Math.random();
//     window[rnd_id]=img;
//     img.onload =img.onerror=img.onabort= function () {};
//     img.src = defaults.src + "?" + item;
//     // img.src = 'http://miaoooooo.com:3000/add?project=导航登录注册统计&page=首页&openid=123&type=click&name=login';
//     console.log(img.src)
// }  //统计代码js

function total(curTitle, playerNameAll, loginType) {
    //console.log("调取函数");
    var defaults = {
        src: 'http://miaoooooo.com:3000/add',
        data: {
            project: '导航登录注册统计',
            page: curTitle,           //当前页面title
            openid: playerNameAll,         //用户账号
            type: 'click',
            name: loginType            //登录/注册/退出事件
        }
    }

    if (!!window.ActiveXObject || "ActiveXObject" in window) {
        // console.log("It is IE");
        $.ajax({
            url: 'http://miaoooooo.com:3000/add',
            type: 'GET',
            data: defaults.data,
            success: function (res) {
                console.log(res);
            }
        })
    }else {
        // console.log("这不是IE");
        var img = new Image(1, 1);
        var item = '';
        for (var i in defaults.data) {
            item += i + '=' + defaults.data[i] + '&';
        }
        // img.src = defaults.src + "?" + item;
        rnd_id = "_img_" + Math.random();
        window[rnd_id] = img;
        img.onload = img.onerror = img.onabort = function () {
            // alert(this.src)
        };
        img.src = defaults.src + "?" + item.substr(0, item.length - 1);
        // console.log("img src = " + img.src)
    }
        // img.src = 'http://miaoooooo.com:3000/add?project=导航登录注册统计&page=首页&openid=123&type=click&name=login';
}  //统计代码js

/**
	工具类，公用的方法
*/
var Util = {
	/**
	 * 判断字符串或者对象是否为空
	 * @param data {String|Object} 判断的对象
	 */
	isNull: function(data) {
		if(data == undefined || data == '' || data == ' ' || data == null) {
			return true;
		}
		return false;
	},
	/**
	 * 模板转换为html元素
	 * @param targetEle {DOM} 目标元素
	 * @param templateId 模板ID
	 * @param data 模板中的动态数据源
	 */
	tpl2html: function(targetEle, templateId, data) {
		var htmlStr = template.render(templateId, data);
		targetEle.html(htmlStr);
		return htmlStr;
	},

	//设置公共的头部和尾部右侧栏
	setPublicInfo: function(){
		Util.setFooter();
		Util.setHead();
        $(".friendContent").mouseover(function(){
            // alert("dd");
            $(".toggleFriend").removeClass("hidden");
       }).mouseout(function(){
            $(".toggleFriend").addClass("hidden");
       });
        //自动搜索
        $("#keyWordText").autocomplete({
            serviceUrl: '/api/FullTextSearch/suggest',
            extraParams: {email:$.trim($("#keyWordText").val())},
            paramName: 'search',
            transformResult: function (response) {
                //解析服务器传过来的json字符串
                var obj = $.parseJSON(response);
                return {
                    suggestions: $.map(obj.result, function (dataItem) {
                        // console.log(dataItem);
                        return {value: dataItem};
                    })
                };
            }
        });

        Data.ajaxGetCallSync("/Login/logindis",function(data){
            var curTitle = $("title").text();
            var playerNameAll = "";
            if(data.statusCode == 4020){
                alert("您的账号已冻结！");
                return false;
            }
            if(data.statusCode == 200){
                $("#headLogin").attr("href",data.login+'&lenovoid.qrstate=2');
                $("#headRegister").attr("href",data.regist);
                $("#headExit").attr("href",data.out);

                if(data.user_name || data.user_id){
                    playerNameAll = data.user_name;
                    $(".unloginLinkLi").addClass("hidden");
                    $(".loginLinkLi").removeClass("hidden");
                    if(data.nick_name){
                        $("#userNameText").text("Hi："+data.nick_name);
                    }else {
                        $("#userNameText").text("Hi："+data.user_name);
                    }
                }
                if(!data.LoginType){
                    localStorage.event =100;
                }else if(data.LoginType == "0" && localStorage.event && localStorage.event == 100){  //登录
                    localStorage.event = 0;
                    localStorage.userName = playerNameAll;
                    total(curTitle,playerNameAll,"登录");
                }else if(data.LoginType == "1" && localStorage.event && localStorage.event == 100){    //注册
                    localStorage.event = 0;
                    localStorage.userName = playerNameAll;
                    total(curTitle,playerNameAll,"注册");
                }else if(data.LoginType == "2" && localStorage.event && localStorage.event == 0){  //退出
                    localStorage.event = 100;
                    total(curTitle,localStorage.userName ,"退出");
                }
            }
        });
		//Util.setRightBar();
	},

	/**
	* 设置头部模板
	*/
	setHead: function(){
		var url_nav = "/header.html";
		if(!StorageUtil.get("navListArrSession")){
			Util.getNavList();
		}else {
			navListObj = JSON.parse(StorageUtil.get("navListArrSession"));
		}
		Data.getHtmlByReadFile(url_nav,"head",navListObj);

	},

	/**
	* 设置尾部模板
	*/
	setFooter: function(){
		var	url_footer = "/footer.html";
		Data.getHtmlByReadFile(url_footer, "foot");
	},

    //我的贡献右侧栏
    setContribution: function(){
        var url_contribution = "/contributionBar.html";
        Data.getHtmlByReadFile(url_contribution, "contribution");
    },

    //知识专区右侧栏
    setKnowledge: function(){
        if($("#knowledgeBox").length){
            var url_knowledge = "/knowledgeBar.html";
            if(!StorageUtil.get("knowledgeArrSession")){
                Util.getKnowledgeData();
            }else {
                knowledgeData = JSON.parse(StorageUtil.get("knowledgeArrSession"));
            }
            Data.getHtmlByReadFile(url_knowledge, "knowledgeBox",knowledgeData);
        }
    },

    //用户动态
    setdynamic:function(){
        if($("#dynamicBox").length){
            Data.ajaxGetCall("/Api/UserDynamic/dynamicList",function(data){

                if(data.result){
                    var dynamicData = data.result, dynamicString = '<h3 class="box-title">用户动态</h3><ul class="dynamicList">',i=0,dynamicLength = dynamicData.length;
                    if(dynamicLength - 5 > 0){
                        dynamicLength = 5;
                    }
                    for(;i<dynamicLength;i++){
                        dynamicString += '<li class="dynamicItem"><p class="dynamicTime">'+dynamicData[i].create_time+'</p><p class="dynamicDesc">'+dynamicData[i].dynamic+'</p></li>';
                    }
                    dynamicString += '</ul>';
                    $("#dynamicBox").html(dynamicString);
                }
            });
        }
    },
   //用户原创
    setOriginalDoc:function(){
        if($("#userBox").length){
            Data.ajaxGetCall("/Api/knowledge/getUserCreate",function(data){
                 if(data.res){
                    var str="";
                    var li="";
                    str+='<h3 class="box-title">用户原创</h3>';
                    str+='<a class="more" href="/list/o.html">更多 ></a>';
                    str+='<ul>'
                    $.each(data.res,function(k,v){
                        var num=parseInt(k+1);
                        li+='<li><span>'+num+'</span><a href="/detail/dc_'+v["doc_code"]+'.html" style="margin-left:10px;margin-right: 8px;">'+v["title"]+'</a><label>'+v["username"]+'</label></li>';
                    });
                    str+=li;
                    str+='</ul>';
                    $("#userBox").html(str);
                }
            });
        }
    },

    //设置公共右侧栏
    setRightBar: function(){
        //Util.setContribution();
        Util.setKnowledge();
        Util.setdynamic();
        Util.setOriginalDoc();
    },

	//获取首页一级、二级菜单
	getNavList: function(){
		var navListArr = [],arrData = [];
        Data.ajaxGetCallSync("/api/category/getMenuLineCategory",function(data){
            if(data.result){
                arrData = data.result,alength = arrData.length,i=0;
                for(; i < alength; i++){
                    var settings = {};
                    settings.admin_id = arrData[i].admin_id;
                    settings.check_group_id = arrData[i].check_group_id;
                    settings.create_time = arrData[i].create_time;
                    settings.deleted = arrData[i].deleted;
                    settings.is_show = arrData[i].is_show;
                    settings.line_category_id = arrData[i].line_category_id;
                    settings.line_category_name = arrData[i].line_category_name;
                    settings.parent_id = arrData[i].parent_id;
                    settings.sequence = arrData[i].sequence;
                    Data.ajaxGetCallSync("/api/category/getMenuLineCategory?id="+arrData[i].line_category_id,function(data){
                        settings.subnav = data.result;
                        navListArr.push(settings);
                    });
                }
                navListObj.rows = navListArr;
                StorageUtil.set("navListArrSession",JSON.stringify(navListObj));
            }
        });
	},

    //获取知识专区内容
    getKnowledgeData: function(){
        Data.ajaxGetCallSync("/api/category/getMenuIssueCategory",function(data){
            if(data.result){
                knowledgeData = data;
                StorageUtil.set("knowledgeArrSession",JSON.stringify(data));
            }
        });
    },

	/**
	* 显示二级菜单
	*/
	showSubList: function(id){
        $("#"+id).children(".subnav").removeClass("hidden");
    },

    /**
	* 隐藏二级菜单
	*/
    hideSubList: function(id){
        $("#"+id).children(".subnav").addClass("hidden");
    },

    showExit: function(){
        $("#headExit").removeClass("hidden");
    },

    hideExit:function(){
        $("#headExit").addClass("hidden");
    },

    /**
    * 页面跳转
    * @param url 跳转路径
    */
    location: function(url){
    	window.location.href = url;
    },

    /**
    * 打开新窗口
    */
    openUrl: function(url){
        window.open(url);
    },

    /**
     * 返回上一步
     */
    goPrevious: function() {
        window.history.go(-1);
    },

    /**
	* 回到首页
	*/
    goIndex: function(){
    	Util.location("/");
    	// window.location.href = "index.html";
    },

    /**
    * 去登录页面
    */
    goLogin: function(){
    	Util.location("login.html");
    },

    /**
    * 去注册页面
    */
    goRegister: function(){
    	Util.location("register.html");
    },

    /**
    * 获取索引
    */
    getIndexId: function(gridId){
        var opts = $("#"+gridId).bootstrapTable("getOptions");
        return (opts.pageNumber-1)*parseInt(opts.pageSize);
    },

    /**
    * 获取每页条数
    */
    getPageSize:function(gridId){
        var opts = $("#"+gridId).bootstrapTable("getOptions");
        var pageSize = opts.pageSize;
        if(isNaN(pageSize) || Util.isNull(pageSize)) {
            pageSize = 10;
        }
        return pageSize;
    },
    showqrcode: function(){
        $(".chat_link").children(".authority_chat_img").stop().slideDown(300);
    },
    hideqrcode: function(){
        $(".chat_link").children(".authority_chat_img").stop().slideUp(300);
    },
    showPersonCenter: function(){
        $("#my_center").children(".my_service_list").stop().slideDown(300);
    },
    hidePersonCenter: function(){
        $("#my_center").children(".my_service_list").stop().slideUp(300);
    }

}

var Data = {
	/**
		交易处理Ajax通用方法，发送POST请求，数据格式为JSON
	*/
	_ajaxPostUtil:function(url,parameter,async,fun_success, fun_error, fun_complete, fun_before){
			var urlStr = sysPath + url;
		$.ajax({
			url : encodeURI(urlStr),
			//data : encodeURI(parameter || '').replace(/\+/g,'%2B'),
            data : parameter,
			//data : encodeURI(params || ''),
			type : "POST",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			dataType : "json",
			timeout : ajaxTimeout,
			async: async,
	        cache: false,
			beforeSend: function(xhr, settings) {
	        	return fun_before && fun_before(xhr);
	        },
			success: function(data, status, xhr) {
				if(!data) {
					//alert("该请求无响应数据！");
	        		return;
	        	}
				if(data.statusCode == "200" || data.statusCode == 200) {	//交易成功
					return fun_success && fun_success(data, status, xhr);
				}else if (data.statusCode == "4009" || data.statusCode == 4009){
                    alert("您目前没有登录，请去登录！");
                    Util.location($("#headLogin").attr("href"));
                    return false;
                }else if(data.statusCode == 4020 || data.statusCode == "4020"){
                    alert("您的账号已冻结！");
                    return false;
                }else if(data.statusCode == 4004 || data.statusCode == "4004"){
                    window.location.href ="/error.html";
                }else{
					if(fun_error) {
						return fun_error && fun_error(data);
					}else {
                        if (typeof data.meg == 'undefined') {
                            //alert(data.result);
                        } else {
                            //alert(data.meg);
                        }

					}
					return;
				}
			},
			complete: function(xhr, status) {
				if(fun_complete){
					return fun_complete(xhr,status);
				}else{
					if(status == "timeout"){
						//alert("网络错误，系统请求数据超时!");
					}
				}
			},

			error: function(xhr, type, error) {
				//alert("请求失败！");
				return;
			}
		});
	},
	/**
	 * 交易处理Ajax通用方法，发送POST请求，数据格式为json
	 * @param params 请求中的参数
	 * @param fun_success 请求成功的回调函数
	 * @param fun_error 请求失败的回调函数
	 */
	ajaxPostCall: function(url,params, fun_success, fun_error,fun_complete, fun_before)											    {
		Data._ajaxPostUtil(url, params, true, fun_success, fun_error, fun_complete, fun_before);

	},

	ajaxPostCallSync: function(url,params, fun_success, fun_error,fun_complete, fun_before)											    {
		Data._ajaxPostUtil(url, params, false, fun_success, fun_error, fun_complete, fun_before);

	},

	/**
		交易处理Ajax通用方法，发送GET请求，数据格式为JSON
	*/
	_ajaxGetUtil:function(url,async,fun_success, fun_error, fun_complete, fun_before){
		var urlStr = sysPath + url;
		$.ajax({
			url : encodeURI(urlStr),
			type : "GET",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			dataType : "json",
			timeout : ajaxTimeout,
			async: async,
	        cache: false,
			beforeSend: function(xhr) {
	        	return fun_before && fun_before(xhr);
	        },
			success: function(data, status, xhr) {
				if(!data) {
				//	alert("该请求无响应数据！");
	        		return;
	        	}
				if(data.statusCode == "200" || data.statusCode == 200) {	//交易成功
					return fun_success && fun_success(data, status, xhr);
				}else if (data.statusCode == "4009" || data.statusCode == 4009){
                    alert("您目前没有登录，请去登录！");
                    Util.location($("#headLogin").attr("href"));
                    return false;
                }else if(data.statusCode == 4020 || data.statusCode == "4020"){
                    alert("您的账号已冻结！");
                    return false;
                }else{
					if(fun_error) {
						return fun_error && fun_error(data);
					} else {
					//	alert("错误码：" + data.statusCode + "错误信息：" + data.meg);
					}
					return;
				}
			},

			complete: function(xhr, status) {
				if(fun_complete){
					return fun_complete(xhr,status);
				}else{
					if(status == "timeout"){
					//	alert("网络错误，系统请求数据超时!");
					}
				}
			},

			error: function(xhr, type, error) {
			//	alert("请求失败！");
				return;
			}
		});
	},

	/**
		Get方式请求的异步请求
	*/
	ajaxGetCall:function(url,fun_success, fun_error, fun_complete, fun_before){
		Data._ajaxGetUtil(url,true, fun_success, fun_error, fun_complete, fun_before);
	},

	/**
		GET方式请求的同步请求
	*/
	ajaxGetCallSync:function(url,fun_success, fun_error, fun_complete, fun_before){
		Data._ajaxGetUtil(url,false, fun_success, fun_error, fun_complete, fun_before);
	},

	/**
	 * 读取文件以设置节点的html
	 * @param url 文件路径
	 * @param targetId {String} 组件id
	 * @param callbackFun {Function} 回调函数
	 */
	getHtmlByReadFile:function(url, targetId, pageSource, callbackFun){
		var target = $("#"+targetId);
		var innerHTML = StorageUtil.get(targetId + 'HTML');
		if(Util.isNull(innerHTML)){	//读取文件
			$.get(url, function(data) {
				StorageUtil.set(targetId + 'HTML', data);
				target.html(data);
				var htmlStr = Util.tpl2html(target, targetId + 'Tpl', pageSource);
				target.html(htmlStr);
				callbackFun && callbackFun();
			}, 'text');
		}else {
			target.html(innerHTML);
			var htmlStr = Util.tpl2html(target, targetId + 'Tpl', pageSource);
			target.html(htmlStr);

			callbackFun && callbackFun();
		}

	}

}
/**
 * 本地存储数据公共封装，IE6、IE7采用userData存储。
 */
var StorageUtil = {
//	    hname:location.hostname?location.hostname:'localStatus',  //用此写法可能会提示数据无效
	hname: 'userData_knowlege',
    isSessionStorage:window.sessionStorage?true:false,
    dataDom:null,

    initDom:function(){ //初始化userData
        if(!this.dataDom){
            try{
                this.dataDom = document.createElement('input');//创建隐藏input元素
                this.dataDom.type = 'hidden';
                this.dataDom.style.display = "none";
                this.dataDom.addBehavior('#default#userData');//userData的语法
                document.body.appendChild(this.dataDom);
                var exDate = new Date();
                exDate.setDate(exDate.getDate()+30);
                this.dataDom.expires = exDate.toUTCString();//设定过期时间默认为30天
            }catch(ex){
                return false;
            }
        }
        return true;
    },
    set:function(key,value){
        if(this.isSessionStorage){
            window.sessionStorage.setItem(key,value);
        }else{
            if(this.initDom()){
                this.dataDom.load(this.hname);
                this.dataDom.setAttribute(key,value);
                this.dataDom.save(this.hname);
            }
        }
    },
    get:function(key){
        if(this.isSessionStorage){
            return window.sessionStorage.getItem(key);
        }else{
            if(this.initDom()){
                this.dataDom.load(this.hname);
                var val = this.dataDom.getAttribute(key);
                if(Util.isNull(val)) {    //ie6下clientToken使用全局变量
                	if(key == 'clientToken') {
                		return $.__token;
                	}
                }
                return val;
            }
        }
    },
    remove:function(key){
        if(this.isSessionStorage){
        	sessionStorage.removeItem(key);
        }else{
            if(this.initDom()){
                this.dataDom.load(this.hname);
                this.dataDom.removeAttribute(key);
                this.dataDom.save(this.hname);
            }
        }
    },
    removeAll:function(){
        if(this.isSessionStorage){
        	window.sessionStorage.clear();
        }
    }
};

/**
* 公共验证类
*/

var ValidRules = {

     //正整数验
     vBaseNum : function(value) {
        return /^([1-9]\d*)$/.test(value);
     },

     //邮箱
     vBaseEmail: function(value){
        return /^\w+([-\+\.]\w+)*@\w+([-\.]\w+)*\.\w+([-\.]\w+)*$/.test(value);
     },

     //IP
     vBaseIp: function(value){
        return /^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])((,(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9]))*)$/.test(value);
     },

     //固定电话或手机号
     vBasePhone: function(value){
        return /^([0-9]{3,4}-)?([0-9]{7,8}){1}(-[0-9]{1,5})?$|^1[3|5|8|7][0-9]\d{8}$/.test(value);
     }
}

//根据关键字查询知识
function queryKnowledgeByKey(){
	var keyWord = $("#keyWordText").val();
	if(Util.isNull(keyWord) || keyWord == "输入关键字"){
		alert("搜索关键字不能为空！");
		return;
	}

	var str = keyWord.replace(/\//g, '、');
    Util.location("/search/"+str+".html");
}

//点击一级产线菜单跳转到一级分类页面
function goFristKnowledge(fristLineId,fristLineName){
    Util.location("fristList.html?fristKnowledgeId="+fristLineId+"&fristKnowledgeName="+escape(fristLineName)+"&type=production");
}

//点击知识分区跳转到问题的一级分类页面
function goFristSpecialKnowledge(fristSpecialId,fristSpecialName){
    Util.location("fristList.html?fristKnowledgeId="+fristSpecialId+"&fristKnowledgeName="+escape(fristSpecialName)+"&type=specialCategory");
}

//头部二级菜单点击跳转操作
function goSecondKnowledge(subProductionId,subProductionName,fristProductionName,fristProductionId){
    if(Util.isNull(subProductionId)){      //查看更多，跳转一级知识页面
        Util.location("fristList.html?fristKnowledgeId="+fristProductionId+"&fristKnowledgeName="+escape(fristProductionName)+"&type=production");
    }else {     //跳转二级知识页面
        Util.location("secondList.html?productionId="+subProductionId+"&fristProductionName="+escape(fristProductionName)+"&subProductionName="+escape(subProductionName)+"&type=production");
    }
}

//从一级页面跳转到二级页面
function goSecondFronFrist(id,name,type){
    Util.location("secondList.html?productionId="+id+"&fristProductionName="+escape($("#productionName").text())+"&subProductionName="+escape(name)+"&type="+type);
}

//根据id查看知识详情
function showKnowledgeDeatil(id, doc_code){
    var searchId = $("#searchId").val();
    if (searchId) {
        Data.ajaxPostCall("/KnowledgeDetails/knowledgeDetails","doc_code="+doc_code+"&search_id="+searchId,function(data){
            Util.openUrl("knowledgeDetail.html?doc_code=" + doc_code + "&&search_id=" + searchId);
        },function(data){
            Util.openUrl("error.html");
        });
    } else {
        Data.ajaxPostCall("/KnowledgeDetails/knowledgeDetails","doc_code="+doc_code,function(data){
            Util.openUrl("knowledgeDetail.html?doc_code=" + doc_code);
        },function(data){
            Util.openUrl("error.html");
        });
    }
}

//创建知识
function goCreateKnowledge(){
    Data.ajaxGetCallSync("/Login/logindis",function(data){
        if(data.black == 2){
            alert('您的账户已冻结');
            return;
        }
        if(data.user_id){
            if(data.editTop == 1){
                StorageUtil.set("isTask","0");
                StorageUtil.set("writePageType","add"); //创建知识
                Util.location("/writePage/doc.html");
            }else if(data.editTop == 2){
                alert("您今天撰写的文档数量已达到上限，请您改天再写，非常感谢！");
                return;
            }
        }else {
            Util.location(data.login);
        }
    });
}

//跳转个人中心页面
function goPersonCenter(){
    Data.ajaxGetCallSync("/Login/logindis",function(data){
        if(data.black == 2){
            alert('您的账户已冻结');
            return;
        }
        if(data.user_id){
            Util.location("/personalCenter.html");
        }else{
            Util.location(data.login);
        }
    });
}

//跳转商城页面
function goChangMall(){
    Data.ajaxGetCallSync("/Login/logindis",function(data){
        if(data.user_id){
            Util.location("/mall.html");
        }else{
            Util.location(data.login);
        }
    });
}

//跳转任务页面
function goTakeTask(){
    Data.ajaxGetCallSync("/Login/logindis",function(data){
        if(data.black == 2){
            alert('您的账户已冻结');
            return;
        }
        if(data.user_id){
            Util.location("/taskList.html");
        }else{
            Util.location(data.login);
        }
    });
}

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}

//获取地址栏参数
function getUrlParamArr(){
     var sUrl = window.location.href;
     var paramStr = "";
    if(sUrl.lastIndexOf("/") > -1){
        var oStartPosition = sUrl.lastIndexOf("/") + 1;
        var oEndPosition = sUrl.lastIndexOf(".");
        paramStr = sUrl.substring(oStartPosition,oEndPosition);
        var arrParam = paramStr.split("_");
        return arrParam;
    }else {
        return null;
    }
}
