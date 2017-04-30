define(function(require, exports, module){
    require('common');
    $(function(){

        $(".menuContent .item").mouseover(function(){
            $(".submenu").hide();
            var selector = $(this).attr('data-id');
            $('.'+selector).show();
        })
        $(".menuContent .item , .submenu").mouseleave(function(){
            $(".submenu").hide();

        })
        $(".submenu ").mouseover(function(){
            $(".submenu").hide();
            $(this).show();
        })

        // 鼠标放上去动画效果
        var animateDoing = false;
        var time = 0;
        $(document).delegate('.js-upAni',"mouseenter",function(e){
            var self = this;
            time = setTimeout(function(){
                // console.log('=================')
                // $(this).css('-webkit-box-shadow','0 5px 20px 0 rgba(0, 0, 0, 0.1)').css("-moz-box-shadow","0 5px 20px 0 rgba(0, 0, 0, 0.1)").css("box-shadow","0 5px 20px 0 rgba(0, 0, 0, 0.1)")
                $(self).find(".js-upBox").css({'top':'77px'});
                $(self).find(".title").css({'height':'auto','max-height':'48px','overflow':'hidden'});
                $(self).find(".summary").css({'margin':'0px'});
                if(!animateDoing) {
                    $(self).find(".js-upBox").addClass("doing");
                    var dom =  $(self).find(".js-upBox");
                    $(self).find(".js-upBox").animate({top:"22px"},100,function(){
                        $(self).find(".js-upBox").addClass("box_body_hover");
                        animateDoing = false;
                        if(!dom.hasClass("doing")){
                            dom.css('top','77px');
                        }else{
                        }
                    });
                }
                return false;
            },200)
        })

        $(document).delegate('.js-upAni',"mouseleave",function(e){
            //$(".js-upBox").css('heihgt','64px')
            window.clearInterval(time);
            $(this).find(".js-upBox").removeClass("box_body_hover");
            // console.log("leave--------------")
            $(this).find(".title").css('height','24px');
            $(this).find(".summary").css({'margin':'2px'});
            $(".doing").css('top','77px');
            $(".doing").removeClass("doing");
            $(this).find(".js-upBox").css({'top':'77px'});
            var ele = $(this).find(".js-upBox");
            return false;

        })


    })


    $("#js-header-login").click(function(){
        require.async("login_sns",function(mod){
            mod.init();
        });
    });
    $("#js-header-register").click(function(){
        require.async("login_sns",function(mod){
            mod.signup();
        });
    });





    // 首页轮播图-渐变
    (function(){
        var banner = $('.g-banner'),
            slides = banner.find('.banner-slide'),
            dotContainer = banner.find('.banner-dots'),
            dotTpl = '',
            dots,
            total = slides.length,
            index = -1,
            duration = 500,
            interval = 5000,
            timer = null;

        // 一张图 不执行轮播
        if(total == 1) {
            next();
            return;
        }

        dotTpl = '<span></span>';

        $.each(slides, function(i, el){
            dotContainer.append(dotTpl);
        });

        dots = dotContainer.find('span');

        function show(i){
            var cur = slides.filter('.slide-active');

            slides.stop(true, true);

            cur.removeClass('slide-active').fadeOut(600);

            slides.eq(i).addClass('slide-active').fadeIn(800);

            dots && dots.removeClass('active').eq(i).addClass('active');
        }
        function prev(){
            index--;

            index = index < 0 ? total - 1 : index;

            show(index);
        }

        function next(){
            index++;
            index = index > total - 1 ? 0 : index;

            show(index);
        }

        function autoPlay(){
            if(timer) clearInterval(timer);

            timer = setInterval(function(){
                next();
            }, interval);
        }

        banner.find('.banner-anchor').removeAttr('style');

        banner.on('click', '.prev', function(e){
            prev();
        }).on('click', '.next', function(e){
            next();
        }).on('click', '.banner-dots span', function(e){
            if($(this).hasClass('active')) return;

            var i = $(this).index();

            index = i;

            show(i);
        });

        banner.on('mouseenter', function(e){
            if(timer) clearInterval(timer);
        }).on('mouseleave', function(e){
            autoPlay();
        });

        next();

        autoPlay();

    })();
    // 默认滚动，触发动画检测
    $(window).trigger('scroll');

});
