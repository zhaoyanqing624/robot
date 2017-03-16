define(function(require, exports, module) {
    var formatStr=function(uInfo){
        var cardStr='<div class="card-inner">\
                        <div class="card-top">\
                            <a href="/u/'+uInfo.uid+'"><img src="'+uInfo.img+'" alt="'+uInfo.nickname+'" class="l"></a>\
                            <a href="/u/'+uInfo.uid+'"><span class="name text-ellipsis">'+uInfo.nickname+'</span></a>\
                            <p class="meta">\
                <a href="/u/'+uInfo.uid+'/experience">经验<b id="js-user-mp">'+(uInfo.mp?uInfo.mp:0)+'</b></a>\
                <a href="/u/'+uInfo.uid+'/credit">积分<b id="js-user-credit">'+(uInfo.credit?uInfo.credit:0)+'</b></a></p>\
                            <a href="/mall/index" class="setup linkToMall">积分商城</a>\
                        </div>';

        if(uInfo.last_learning){
            cardStr+='<div class="card-history">\
                            <span class="history-item">\
                                <span class="tit text-ellipsis">'+uInfo.last_learning.course.name+'</span>\
                                <span class="media-name text-ellipsis">'+uInfo.last_learning.chapter.seqid+'-'+uInfo.last_learning.media.seqid+' '+uInfo.last_learning.media.name+'</span>\
                                <i class="icon-clock"></i>'
            if (uInfo.last_learning.is_shizhan && uInfo.last_learning.is_shizhan == 1){
                cardStr+='<a href="http://coding.imooc.com/lesson/'+uInfo.last_learning.course.id+'.html#mid='+uInfo.last_learning.media.id+'" class="continue">继续</a>'
            }else{
                cardStr+='<a href="/video/'+uInfo.last_learning.media.id+'" class="continue">继续</a>'
            }
            
            cardStr+='</span></div>'            
        }


        cardStr+='<div class="card-sets clearfix">\
                            <a href="/user/setprofile" target="_blank" class="l mr30">个人设置</a>\
                            <a href="/passport/user/logout?referer=http://www.imooc.com" class="r">退出</a>\
                        </div>\
            </div>\
            <i class="card-arr"></i>'
        return cardStr;
    }

    var init=function(){

        if(isLogin){
            $.ajax({
                url: '/u/card ',
                type: 'get',
                dataType: 'json'
            })
            .done(function(res) {
                if(res.result==0){
                    $(".js-header-avator img").attr("src",res.data.img)
                    $('.g-user-card').html(formatStr(res.data)).show()
                }
            })
        }
    }
    init()
});