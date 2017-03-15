$(function () {
var start = {
    format: 'YYYY-MM-DD hh:mm:ss',
    minDate: '2014-06-16 23:59:59', //设定最小日期为当前日期
    festival:true,
    //isinitVal:true,
    maxDate: $.nowDate(0), //最大日期
    choosefun: function(elem,datas){
        end.minDate = datas; //开始日选好后，重置结束日的最小日期
    }
};
var end = {
    format: 'YYYY年MM月DD日 hh:mm:ss',
    minDate: $.nowDate(0), //设定最小日期为当前日期
    festival:true,
    //isinitVal:true,
    maxDate: '2099-06-16 23:59:59', //最大日期
    choosefun: function(elem,datas){
        start.maxDate = datas; //将结束日的初始值设定为开始日的最大日期
    }
};
$("#inpstart").jeDate(start);
$("#inpend").jeDate(end);


$("#date01").jeDate({
    isinitVal:true,
    festival:true,
    ishmsVal:false,
    minDate: '2016-06-16 23:59:59',
    maxDate: $.nowDate(0),
    format:"YYYY-MM-DD hh:mm:ss",
    zIndex:3000,
})
$("#date02").jeDate({
    isinitVal:true,
    festival:true,
    ishmsVal:false,
    minDate: '2016-06-16 23:59:59',
    maxDate: $.nowDate(0),
    format:"YYYY-MM-DD hh:mm",
    zIndex:3000,
})
$("#date03").jeDate({
    isinitVal:true,
    festival:true,
    ishmsVal:false,
    minDate: '2016-06-16 23:59:59',
    maxDate: $.nowDate(0),
    format:"YYYY-MM-DD",
    zIndex:3000,
})
$("#date04").jeDate({
    isinitVal:true,
    festival:true,
    ishmsVal:false,
    minDate: '2016-06-16 23:59:59',
    maxDate: $.nowDate(0),
    format:"YYYY-MM",
    zIndex:3000,
})
$("#date05").jeDate({
    isinitVal:true,
    festival:true,
    ishmsVal:false,
    minDate: '2016-06-16 23:59:59',
    maxDate: $.nowDate(0),
    format:"hh:mm:ss",
    zIndex:3000,
})


    function testShow(elem){
        $.jeDate(elem,{
            insTrigger:false,
            isinitVal:true,
            festival:true,
            ishmsVal:false,
            minDate: '2016-06-16 23:59:59',
            maxDate: $.nowDate(0),
            format:"hh:mm",
            zIndex:3000,
        })
    }
});