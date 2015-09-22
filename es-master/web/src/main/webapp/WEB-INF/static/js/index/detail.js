/**
 * Created by jaylee on 15-9-22.
 */

(function ($) {
    var updateClock = function () {
        $(".it-endtime").each(function (i, e) {
            var $e = $(e);
            var vEndDate = $e.data('enddate');
            if (vEndDate) {

                var endDate = new Date(vEndDate);
                var now = new Date();

                var leftTime = endDate.getTime() - now.getTime();
                var leftsecond = parseInt(leftTime / 1000);

                var day = Math.floor(leftsecond / (60 * 60 * 24));
                var hour = Math.floor((leftsecond - day * 24 * 60 * 60) / 3600);
                var minute = Math.floor((leftsecond - day * 24 * 60 * 60 - hour * 3600) / 60);
                var second = Math.floor(leftsecond - day * 24 * 60 * 60 - hour * 3600 - minute * 60);


                //var rd = moment(endDate, "YYYY-MM-DD").fromNow(true);
                $e.find('.it-relativetime').html('<span class="t">' + day + '</span> 天 <span class="t">' + hour + '</span> 时 <span class="t">' + minute + '</span> 分 <span class="t">' + second + '</span> 秒');
            }
        });
    };
    var startClock = function () {
        updateClock();
        window.setInterval(updateClock, 1000);
    };


    var loadImage = function () {
        $('[data-original]').each(function (i, e) {
            $(e).attr('src', $(e).data('original'));
        });
    };

    //人数的加号
    var initIncrease = function () {
        $('#it-number-increase').click(function () {
            var intVal = 0;
            var val = $('#metaNumber-1').val();
            var maxPeopleNum = $("#maxPeopleNum").val();
            if (val) {
                try {
                    intVal = parseInt(val);
                } catch (ignore) {
                }
            }
            if (intVal < parseInt(maxPeopleNum)) {
                intVal++;
                $('#metaNumber-1').val(intVal);
            }
            clacPrice();
        });
    };

    //人数的减号
    var initRreduce = function () {
        $('#it-number-reduce').click(function () {
            var intVal = 2;
            var val = $('#metaNumber-1').val();
            if (val) {
                try {
                    intVal = parseInt(val);
                } catch (ignore) {
                    //忽略异常，数字会变成1
                }
            }
            ;
            intVal--;
            $('#metaNumber-1').val(intVal < 1 ? 1 : intVal);
            clacPrice();
        });
    };

    //人数
    var initMetaNumber = function () {

        $("#metaNumber-1").keyup(function () {  //keyup事件处理
            $(this).val($(this).val().replace(/\D|^0/g, ''));
            var maxPeopleNum = $("#maxPeopleNum").val();
            var num = $(this).val();
            if (parseInt(maxPeopleNum) < parseInt(num)) {
                $(this).val("");
            }
            clacPrice();
        }).bind("paste", function () {  //CTR+V事件处理
            $(this).val($(this).val().replace(/\D|^0/g, ''));
            var maxPeopleNum = $("#maxPeopleNum").val();
            var num = $(this).val();
            if (parseInt(maxPeopleNum) < parseInt(num)) {
                $(this).val("");
            }
        }).css("ime-mode", "disabled");  //CSS设置输入法不可用
    };

    //价格日历初始化
    var initPriceCalender = function () {

        $('#beginDate-1').click(function () {
            $.get("product/price-calender/99", function (data) {
                pickerEvent.setPriceArr(eval("(" + data + ")"));
                pickerEvent.Init("beginDate-1", function (date, price) {

                    priceOneDay = price;

                    clacPrice();

                });
            });
        });
    };

    var initBookBtn = function () {
        $('#it-book-btn').click(function () {

            if ($("#beginDate-1").val().length > 0) {
                $("#beginDate-2").val($("#beginDate-1").val());
                $("#beginDate-txt").html($("#beginDate-1").val());
            }
            else {
                swal("您需要选择起程日期哦～");
                return;
            }

            if ($("#metaNumber-1").val().length > 0 && $("#metaNumber-1").val() > 0) {
                $("#metaNumber-2").val($("#metaNumber-1").val());
                $("#metaNumber-txt").html($("#metaNumber-1").val());
            }
            else {
                swal("您需要指定人数哦～");
                return;
            }

            $("#book-model").modal("toggle");


        });
    };

    //每天的价格
    var priceOneDay = 99;

    //计算价格
    var clacPrice = function () {
        //天数
        var days = $("#metaNumber-1").val();
        if (days <= 0) {
            days = 1;
        }

        var price = priceOneDay * days;

        //显示出来
        $("#product-price").html(price);

    }


    $(function () {
        $("#beginDate-1").on("change", function () {

        });

        $("#metaNumber-1").on("change", function () {
            $("#metaNumber-2").val($("#metaNumber-1").val());
        });


        initPriceCalender();


        loadImage();
        startClock();
        initIncrease();
        initRreduce();
        initMetaNumber();
        initBookBtn();
        clacPrice();
    });
})(jQuery);
