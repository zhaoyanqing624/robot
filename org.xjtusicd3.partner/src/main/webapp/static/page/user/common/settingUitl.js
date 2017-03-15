define(function(require, exports, module){
    
    require('/org.xjtusicd3.partner/static/lib/layer/1.6.0/layer.min.js');
    require('/org.xjtusicd3.partner/static/lib/layer/1.6.0/skin/layer.css');
    
    module.exports = {
        init: function (){
            
            if(this.isTpl) return;
            $('body').append(this.modaltpl());
            this.isTpl = true;
        },
        
        isTpl: false,
        
        modaltpl: function (){
            var str = '';
                str += "<div id='Prompt-layer' class='Prompt-layer'>";
                    str += '<div class="ibox">';
                        str += "<i class='icon-tick2'></i>";
                    str += '</div>';
                    str += "<span class='Prompt-layer-span'>保存成功</span>";
                str += "</div>";
            
            return str;
        },
        
        error: function(selector,msg) {
            if (msg) {
                selector.next('.errtip').html('<i class="icon-point fl" style="margin: 10px 2px 0 0;"></i> <span class="fl">' + msg + '</span>');
                $("body,html").animate({scrollTop:selector.offset().top-10});
                $('.js-add-submit').val('发布 (Ctrl+Enter)')
            }
            else {
                $(selector).empty();
            }
        },
        
        layerInfo : function (html){
            this.init()
            $('#Prompt-layer').find('.Prompt-layer-span').html(html)
            $('#Prompt-layer').find('i').removeClass('icon-point').addClass('icon-tick2');
            this._layer()
        },
        
        layerError: function (html){
            this.init()
            $('#Prompt-layer').find('.Prompt-layer-span').html(html)
            $('#Prompt-layer').find('i').removeClass('icon-tick2').addClass('icon-point');
            this._layer()
        },
        _layer : function (){
            var _layer = $.layer({
                type: 1,
                title: false,
                closeBtn: false,
                bgcolor: false,
                border: false,
                shade: [0 , '#000', true],
                area: ['130','auto'],
                page: {
                        dom: '#Prompt-layer'
                }
            })
            $('#Prompt-layer').css('display', 'inline-block')
            
            setTimeout(function (){
                $('#Prompt-layer').fadeOut('hide', function (){
                    layer.close(_layer);
                })
            }, 1500)
        },
        errortipshow: function (that, val){
            $(that).next('.error').html(val)
        },
        errortiphide: function (that){
            $(that).next('.error').html('')
        }
    };
})