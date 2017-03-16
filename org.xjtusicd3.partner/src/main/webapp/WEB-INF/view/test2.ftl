<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="utf-8"> 
<head>
<title>百度地图上传定位信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="new/front/style/reset2.css" />
    <link rel="stylesheet" type="text/css" href="new/front/style/util2.css" />
    <link href="css/main.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/css.css" rel="stylesheet">
    <link rel="stylesheet" href="css/all.css">
<style type="text/css">
body, html{width: 100%;height: 100%;overflow: hidden;margin:0;}
#l-map {height: 90%;overflow: hidden;width:82%;    margin-left: 7px;}
#result {border-left:1px dotted #999;height:100%;width:295px;position:absolute;top:35px;right:0px;font-size:12px;}
dl,dt,dd,ul,li{
    margin:0;
    padding:0;
    list-style:none;
}
dt{
    font-size:14px;
    font-family:"微软雅黑";
    font-weight:bold;
    border-bottom:1px dotted #000;
    padding:5px 0 5px 5px;
    margin:5px 0;
}
dd{
    padding:5px 0 0 5px;
}
li{
    line-height:28px;
}


	
	.cityList{height: 320px;width:372px;overflow-y:auto;}
    .sel_container{z-index:9999;font-size:12px;position:absolute;right:300px;top:0px;width:140px;background:rgba(255,255,255,0.8);height:30px;line-height:30px;padding:5px;}
    .map_popup {position: absolute;z-index: 200000;width: 382px;height: 344px;right:320px;top:40px;}
    .map_popup .popup_main { background:#fff;border: 1px solid #8BA4D8;height: 100%;overflow: hidden;position: absolute;width: 100%;z-index: 2;}
    .map_popup .title {background: url("http://map.baidu.com/img/popup_title.gif") repeat scroll 0 0 transparent;
    color: #6688CC;font-size: 12px;font-weight: bold;height: 24px;line-height: 25px;padding-left: 7px;}
    .map_popup button {background: url("http://map.baidu.com/img/popup_close.gif") no-repeat scroll 0 0 transparent;
    border: 0 none;cursor: pointer;height: 12px;position: absolute;right: 4px;top: 6px;width: 12px;}	

</style>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ZQVKW0dvz0tgMy6ZytmY5RxZ"></script>
<!-- 加载百度地图样式信息窗口 -->
<script type="text/javascript" src="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js"></script>
<link rel="stylesheet" href="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.css" />
<!-- 加载城市列表 -->
<script type="text/javascript" src="http://api.map.baidu.com/library/CityList/1.2/src/CityList_min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/TextIconOverlay/1.2/src/TextIconOverlay_min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/MarkerClusterer/1.2/src/MarkerClusterer_min.js"></script>

<title>叠加麻点图Demo</title>
</head>
<body>
		<div class="header" id="head">      
        <div class="loginRegistHead" role="banner">
            <div class="content clearfix">
                <div class="header_top_wrap_left">
		            <ul>
		                <li><a class="new_a" href="" data-pos="categorys_1_1">智能小朵</a></li>
		                <li><a class="new_a" href="" data-pos="categorys_1_1">知识库</a></li>
		                <li><a class="new_a" href="" data-pos="categorys_1_1">问题中心</a></li>
		                <li>
		                    <a class="new_a" href="">关于我们</a>
		                </li>
		            </ul> 
                </div>
                <div class="header_top_wrap_right">
		              <ul>
		                <div class="unlogin">
		                    <li class="loginLinkLi"><span class="person_icon"></span></li>
		                    <li class="loginLinkLi" id="userNameText">您好：zhao</li>
		                    	<li class="left_margin my_center loginLinkLi" id="my_center" onmouseover="Util.showPersonCenter()" onmouseout="Util.hidePersonCenter()">个人中心<span class="v_center_arrow"></span>
		                        <div class="my_service_list" style="display: none; height: 116px; padding-top: 0px; margin-top: 0px; padding-bottom: 0px; margin-bottom: 0px;">
		                            <div class="top_icon"></div>
		                            <ul class="ul_list">
		                                <li><a ">个人信息</a></li>
		                                <li><a ">我的主页</a></li>
		                                </li>
		                            </ul>
		                        </div>
		                    </li>
		                    <li class="left_margin loginLinkLi"><a id="headExit">退出</a>
		                    </li>
		                </div>
		            </ul> 
                </div>
            </div>
        </div>
    </div>
	<div id="l-map"></div>
	<div id="result">
		 <dl>
    	<dt>基本操作</dt>
        <dd>
            <ul>
                <li><button id="open">打开定位</button><button id="close">关闭定位</button>
                <button id="fanhui" onclick="fanhui()" >返回首页</button>
                <input type="text" id="jiansuo1" title="请输入关键字进行查询" ></input>
                <button id="open1" onclick="refresh()" title="请输入关键字进行查询">查询</button>
             	<dt>更改地图底色</dt>
                <select id="diseSerach" onchange="refresh()">
                	<option value = "">默认</option>
                	<option value = "dark">高端大气黑</option>
                	<option value = "light">深海忧郁蓝</option>
                </select>   
             
            </ul>
        </dd>
	</div>
	
	<!--城市列表-->
	<input type="text" id="jiansuo" value=""></input>
	<div class="sel_container"><strong id="curCity">北京市</strong> [<a id="curCityText" href="javascript:void(0)">更换城市</a>]</div>
	<div class="map_popup" id="cityList" style="display:none;">
		<div class="popup_main">
			<div class="title">城市列表</div>
			<div class="cityList" id="citylist_container"></div>
			<button id="popup_close"></button>
		</div>
	</div>
   
</body>
<!-- </html> -->
<script type="text/javascript">
function fanhui(){
	location.href = basePath+"/admin/index.action";
}
function refresh(){
	window.location.reload();
}
a();
function a(){
// 百度地图API功能
var aa = $('#jiansuo1').val();
 var diseSerach = $('#diseSerach').val();
if(aa=='请输入关键字进行查询'){
	aa='';
}
var map = new BMap.Map("l-map");          // 创建地图实例
var point = new BMap.Point(116.403694,39.927552);  // 创建点坐标
map.centerAndZoom(point, 5);                 // 初始化地图，设置中心点坐标和地图级别

/* var polyline = new BMap.Polyline([
                                  new BMap.Point(111.386601, 41.480484),
                                  new BMap.Point(117.402517, 41.314242),
                                  new BMap.Point(118.359177, 36.78359)
                                ], {strokeColor:"blue", strokeWeight:6, strokeOpacity:0.5});
                                map.addOverlay(polyline); */

map.enableScrollWheelZoom();
/* map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件 */
	map.addControl(new BMap.MapTypeControl({mapTypes: [BMAP_NORMAL_MAP,BMAP_HYBRID_MAP]}));     //2D图，卫星图

	map.addControl(new BMap.MapTypeControl({anchor: BMAP_ANCHOR_TOP_LEFT}));    //左上角，默认地图控件
	/* map.setCurrentCity("北京");   //由于有3D图，需要设置城市哦 */
var  mapStyle ={      //此段设置地图的底色样式
        features: [],//隐藏地图上的poi
        style : ''+diseSerach+''  //设置地图风格为高端黑
    }
map.setMapStyle(mapStyle); 
/* var MAX = 10;
var markers = [];
var pt = null;
var i = 0;
for (; i < MAX; i++) {
   pt = new BMap.Point(Math.random() * 40 + 85, Math.random() * 30 + 21);
   markers.push(new BMap.Marker(pt));
}
 var markerClusterer = new BMapLib.MarkerClusterer(map, {markers:markers});  */
var customLayer;
function addCustomLayer(keyword) {
    if (customLayer) {
        map.removeTileLayer(customLayer);
    }
    customLayer=new BMap.CustomLayer({
        geotableId: 72216,
        q: ''+aa+'', //检索关键字
        tags: '', //空格分隔的多字符串
        filter: '' //过滤条件,参考http://developer.baidu.com/map/lbs-geosearch.htm#.search.nearby
    });
    map.addTileLayer(customLayer);
    customLayer.addEventListener('hotspotclick',callback);
}
addCustomLayer();

function callback(e)//单击热点图层
{
        var customPoi = e.customPoi;//poi的默认字段
        var contentPoi=e.content;//poi的自定义字段
        var content = '<p style="width:280px;margin:0;line-height:20px;">地址：' + contentPoi.customerName + '销售:'+customPoi.title+'<br/>产品:'+contentPoi.productName+'     演示日期:'+contentPoi.yanshiDate+'</p>';
        var searchInfoWindow = new BMapLib.SearchInfoWindow(map, content, {
            title: customPoi.title, //标题
            width: 290, //宽度
            height: 40, //高度
            panel : "panel", //检索结果面板
            enableAutoPan : true, //自动平移
            enableSendToPhone: true, //是否显示发送到手机按钮
         /*    searchTypes :[
                BMAPLIB_TAB_SEARCH,   //周边检索
                BMAPLIB_TAB_TO_HERE,  //到这里去
                BMAPLIB_TAB_FROM_HERE //从这里出发
            ] */
        });
        var point = new BMap.Point(customPoi.point.lng, customPoi.point.lat);
        var marker = new BMap.Marker(point);
        searchInfoWindow.open(marker);
}


document.getElementById("open").onclick = function(){
	addCustomLayer();
};

document.getElementById("close").onclick = function(){
	if (customLayer) {
        map.removeTileLayer(customLayer);
    }
};

// 创建CityList对象，并放在citylist_container节点内
var myCl = new BMapLib.CityList({container : "citylist_container", map : map});

// 给城市点击时，添加相关操作
myCl.addEventListener("cityclick", function(e) {
	// 修改当前城市显示
	document.getElementById("curCity").innerHTML = e.name;

	// 点击后隐藏城市列表
	document.getElementById("cityList").style.display = "none";
});

// 给“更换城市”链接添加点击操作
document.getElementById("curCityText").onclick = function() {
	var cl = document.getElementById("cityList");
	if (cl.style.display == "none") {
		cl.style.display = "";
	} else {
		cl.style.display = "none";
	}	
};

// 给城市列表上的关闭按钮添加点击操作
document.getElementById("popup_close").onclick = function() {
	var cl = document.getElementById("cityList");
	if (cl.style.display == "none") {
		cl.style.display = "";
	} else {
		cl.style.display = "none";
	}	
};
}

	
</script>
</body>
</html>
