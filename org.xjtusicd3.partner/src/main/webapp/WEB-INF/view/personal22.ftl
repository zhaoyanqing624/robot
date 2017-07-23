<p><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "<a target="_blank" href="http://www.w3.org/TR/html4/loose.dtd">http://www.w3.org/TR/html4/loose.dtd</a>">  
<html>  
 <head>  
  <title> New Document </title>  
  <meta name="Generator" content="EditPlus">  
  <meta name="Author" content="">  
  <meta name="Keywords" content="">  
  <meta name="Description" content="">  
<script type="text/javascript" charset="utf-8" src="js/jquery-3.1.1.min.js"></script>
 </head></p><p> 
 <body>  
<div id="thisMainPanel" style="height:200px;overflow-y: scroll;border:1px solid #f3f3f3;">
    <div class="son-panel">我是类容区域-1</div>
    <div class="son-panel">我是类容区域-2</div>
    <div class="son-panel">我是类容区域-3</div>
    <div class="son-panel">我是类容区域-4</div>
    <div class="son-panel" style="height:160px;">我是类容区域-5</div>
    <div class="son-panel">我是类容区域-6</div>
    <div class="son-panel">我是类容区域-7</div>
    <div class="son-panel">我是类容区域-8</div>
</div>

<script>  
onload = function () {
  //初始化
  scrollToLocation();
};
function scrollToLocation() {
  var mainContainer = $('#thisMainPanel'),
  scrollToContainer = mainContainer.find('.son-panel:last');//滚动到<div id="thisMainPanel">中类名为son-panel的最后一个div处
  //scrollToContainer = mainContainer.find('.son-panel:eq(5)');//滚动到<div id="thisMainPanel">中类名为son-panel的第六个处
  //动画效果
  mainContainer.animate({
    scrollTop: scrollToContainer.offset().top - mainContainer.offset().top + mainContainer.scrollTop()
  }, 2000);//2秒滑动到指定位置
}
</script>  
</html>  
</p>  