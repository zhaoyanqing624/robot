<!DOCTYPE html>

<html lang="en"> 
<head>

	<!-- Basic Page Needs
  ================================================== -->
	<meta charset="utf-8" />
	<title>Imperial</title>
    <meta name="robots" content="index, follow" />
    <meta name="keywords" content="" />
    <meta name="description" content="" />
	<meta name="author" content="" />

	<!-- CSS
  ================================================== -->
    <link rel="stylesheet" type="text/css" href="zhao/lunbo_1/styles/slider.css" />
    <link rel="stylesheet" type="text/css" href="zhao/lunbo_1/styles/skitter.styles.css" media="all" />

	
   

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>

<body>

       
        <!-- SLIDER -->
        <div id="outerslider" >
        	<div id="slidercontainer">
            	<section id="slider">
                <div class="box_skitter box_skitter_large">
                    <ul>
                        
                        <li>
                            <img src="zhao/lunbo_1/images/content/slide1.jpg" style="width:100%;height:100%" alt="" />
                            <div class="label_text">
                                <span>钢铁侠</span>
                            </div>
                        </li>
                        <li>
                            <img src="zhao/lunbo_1/images/content/slide2.jpg"  style="width:100%;height:100%" alt="" />
                            <div class="label_text">
                                <span>超人</span>
                            </div>
                        </li>
                        <li>
                            <img src="zhao/lunbo_1/images/content/slide3.jpg"  style="width:100%;height:100%" alt="" />
                            <div class="label_text">
                                <span>绿巨人</span>
                            </div>
                        </li>
                    </ul>
                </div>
                </section>
            </div>
        </div>
        <!-- END SLIDER -->
        
       
        

<!-- Javascript
================================================== -->
<script type="text/javascript" src="zhao/lunbo_1/js/jquery-1.6.4.min.js"></script>


<!-- Slider -->
<script type="text/javascript" src="zhao/lunbo_1/js/jquery.animate-colors-min.js"></script>
<script type="text/javascript" src="zhao/lunbo_1/js/jquery.skitter.js"></script>
<script type="text/javascript" src="zhao/lunbo_1/js/jquery.easing.1.3.js"></script>
<script type="text/javascript">
jQuery(document).ready(function(){

	//=================================== SLIDESHOW ===================================//
	jQuery(".box_skitter_large").skitter({
		animation: "random",
		interval: 3000,
		numbers: false, 
		numbers_align: "right", 
		hideTools: false,
		controls: false,
		focus: false,
		focus_position: true,
		width_label:'1000px', 
		enable_navigation_keys: true,   
		progressbar: false
	});

});
</script>

</body>
</html>
