/*
 * ************************************************* 
 * File Name  :lnv_frontMonitor.js 
 * Created on :2015-07-21 19:00:00
 * Describe   :User interactive behavior monitoring
 * Encoding   :UTF-8 
 * @Author Shawn xu <xuxy10@lenovo.com>
 * @Lenovo.com (C)2014-2015 
 * ************************************************
 */

var lnv_api = lnv_api || {};

/*
 * @param [Object] config
 *     @argument classname
 *     @argument src
 *     @argument uid
 *     @argument path
 *
 */
 
(function(window,document){

    var FrontMonitor = function(config) {
        this.__config(config);
        this.__init();
    };

    FrontMonitor.prototype = {

        __init: function(){

            this.__bindClick();
            this.__bindTimeCount();
        },

        __config: function(config){
            var me = this,
                def = {
                    classname: 'fm_ele',
                    src : 'http://weixin.lenovo.com.cn/admin/statistic/waiter.png',
                    path: '',
                    uid: '',
                    click: '',
                    stay: ''
                };
            me.config = me.__extend(def, config);
            me.dom = me.config.classname;
            me.src = me.config.src;
            me.data = {
                //page path 微信:win10:首页
                p: me.config.path,
                //cur page 首页
                page: me.config.path? (function(){ var r = me.config.path.split(':'); return r[r.length - 1] }()) : '',
                //openid, sessionid
                user_id: me.config.uid,
                //from url
                ref_page: document.referrer
            }
        },

        /*
         * basic method
         *
         */

        /*
         * [pravite] bind event dynamically
         * @param [node]targetObject 元素node
         * @param [String]evtype 事件类型
         * @param [String]callback 执行函数
         */
         __live: function(classname, evtype, callback){
            var fn = function(event){
                var me = lnv_api.frontMonitor.obj,
                    e = event ? event : window.event,  
                    target = e.srcElement || e.target,
                    parent = me.__isParents(me.dom, me.__parents(target));

                if(e.type == evtype && parent && typeof callback == 'function'){  
                    callback(parent);  
                }  
            };

            if (document.addEventListener) {
                document.addEventListener(evtype, fn, false);
            }else if (document.attachEvent) {
                document.attachEvent('on' + evtype, fn);
            };
            
         },

         /*
         * [pravite]@param [String]node 所有node的父节点
         */
         __parents: function(elem){
            var matched = [elem],
                cur = elem['parentNode'];

            while ( cur && cur.nodeType !== 9 ) {
                if ( cur.nodeType === 1 ) {
                    matched.push( cur );
                };
                cur = cur['parentNode'];
            }
            return matched;
         },

         /*
         * [pravite]@param [String]classname 类名
         * [pravite]@param [String]nodes 待匹配节点集合
         */
         __isParents: function(classname, nodes){
            var is = false, cn = '';

            for (var i = 0, len = nodes.length; i < len; i++) {
                cn = nodes[i].className;

                if(cn.indexOf(classname) > -1){
                    return (is = nodes[i]);
                }
            };

            return false;
         },

         /*
         * [pravite]@param [String]classname 类名
         */
         __selector: function(){ 
            var root = document.body,
                className = this.dom,
                tagName = tagName || "*";

            if (document.getElementsByClassName) {
                return root.getElementsByClassName(className);
            } else {
                var tag = root.getElementsByTagName(tagName);
                var tagAll = [];
                for (var i = 0; i < tag.length; i++) {
                    for (var j = 0, n = tag[i].className.split(' ') ; j < n.length; j++) {   
                        if (n[j] == className) {
                            tagAll.push(tag[i]);
                            break;
                        }
                    }
                }
                return tagAll;
            }
         },

         /*
         * [pravite]get attribute
         *
         */
         __extend: function () {
            var options, name, src, copy, copyIsArray, clone,
                target = arguments[0] || {},
                i = 1,
                length = arguments.length,
                deep = false ;
            
            // Handle a deep copy situation
            if ( typeof target === "boolean" ) {
                deep = target;
                target = arguments[1] || {};
                // skip the boolean and the target
                i = 2;
            }

            if ( typeof target !== "object" && !jQuery.isFunction(target) ) {
                target = {};
            }

            if ( length === i ) {
                target = this ;
                --i;
            }

            for ( ; i < length; i++ ) {
                if ( (options = arguments[ i ]) != null ) {
                    // Extend the base object
                    for ( name in options ) {
                        src = target[ name ];
                        copy = options[ name ];

                        // Prevent never-ending loop
                        if ( target === copy ) {
                            continue ;
                        }

                        if ( deep && copy && ( jQuery.isPlainObject(copy) || (copyIsArray = jQuery.isArray(copy)) ) ) {
                            if ( copyIsArray ) {
                                copyIsArray = false ;
                                clone = src && jQuery.isArray(src) ? src : [];
                            } else {
                                clone = src && jQuery.isPlainObject(src) ? src : {};
                            }
                            target[ name ] = jQuery.extend( deep, clone, copy );
                            // Don't bring in undefined values
                        } else if ( copy !== undefined ) {
                            target[ name ] = copy;
                        }
                    }
                }
            }
            
            // Return the modified object
            return target;
        },

        /*
         * [pravite]get attribute
         *
         */
         __getAttr: function(obj){
            var data = {};
            data.name = obj.attributes['fm-name']? obj.attributes['fm-name'].nodeValue : '';
            data.type = obj.attributes['fm-type']? obj.attributes['fm-type'].nodeValue : '';
            data.operation = obj.attributes['fm-operation']? obj.attributes['fm-operation'].nodeValue : '';
            data.zoon = obj.attributes['fm-zoon']? obj.attributes['fm-zoon'].nodeValue : '';

            return data;
         },

         /*
         * bind click event
         *
         */
         __bindClick: function(){

            var me = this,
                data = me.data, 
                ele = me.dom,
                live = me.__live,
                click = me.config.click,
                log = me.log,
                r = true;

            live(ele, 'click', function(ele){
                f = me.__getAttr(ele);
                f.operation = f.operation || ((click && click.operation)?click.operation:'click');

                if(typeof click.callback == 'function'){
                    r = click.callback(me.__extend(data, f));
                }

                if(r !== false){ 
                    me.log(f);
                } 
            });
                
         },

          /*
         * bind time count
         *
         */
         __bindTimeCount: function(){
            var _st, _et, _o, 
                r = true,
                me = this, 
                data = me.data,
                stay = me.config.stay,
                item = {};
            //enter
            window.onload = function(){
                _st = new Date().getTime();
            };
            //leave
            window.onbeforeunload = function(){
                _o = (stay && stay.operation)?stay.operation:'stay';
                _et = new Date().getTime();

                if(typeof stay.callback == 'function'){
                    r = stay.callback(me.__extend(data, {operation: _o, ext_info: (_et - _st)/1000}));
                }

                if(r !== false){
                    me.log({operation: _o, ext_info: (_et - _st)/1000});
                } 

                setTimeout(function(){},1000);

            };
                
         },

         /*
         * [public]send Log item
         *
         */
         log: function(info){
            var data = this.data, 
                src = this.src,
                item = '',
                img = new Image(); 
            data = this.__extend(data, info);

            for(var i in data){
                item += i + '=' + data[i] + '&';
            }

            item += new Date().getTime();

            img.src = src + '?' + item;
         }
        
    };

    lnv_api.frontMonitor = function() {
        return {
            init : function(config) {
                config = config || '';
                return (lnv_api.frontMonitor.obj = new FrontMonitor(config));
            }
        }
    }();

})(window,document)