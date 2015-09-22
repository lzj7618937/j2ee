(function ($) {
    var longPolling = function (url, callback) {
        $.ajax({
            url: url,
            async: true,
            cache: false,
            global: false,
            timeout: 30 * 1000,
            dataType: "json",
            success: function (data, status, request) {
                callback(data);
                data = null;
                status = null;
                request = null;
                setTimeout(
                    function () {
                        longPolling(url, callback);
                    },
                    10
                );
            },
            error: function (xmlHR, textStatus, errorThrown) {
                xmlHR = null;
                textStatus = null;
                errorThrown = null;

                setTimeout(
                    function () {
                        longPolling(url, callback);
                    },
                    30 * 1000
                );
            }
        });
    };

    var startPolling = function () {

        var pollingUrl = "admin/polling";
        longPolling(pollingUrl, function (data) {
            if (data) {
                console.dir(data);
                if (data.unreadMessageCount) {
                    //message.update(data.unreadMessageCount);
                }
                if (data.notifications) {
                    //notification.update(data.notifications);
                }
            }
        });
    };

    var initTripCity = function () {
        //想去的城市
        $('#trip-place').magicSuggest({
            noSuggestionText: "",
            data: 'dest/suggestByName',
            name: 'course_dests',
            displayField: 'name',
            valueField: 'id',
            editable: true,
        });


        $('#trip-place-m').magicSuggest({
            noSuggestionText: "",
            data: 'dest/suggestByName',
            name: 'course_dests',
            displayField: 'name',
            valueField: 'id',
            editable: true,
        });
    };


    $(function () {
        initTripCity();
    });
})(jQuery);

$(function () {
    $(".nav3").mousemove(function () {
        $(".nav3-list").show();
    }).mouseleave(function () {
        $(".nav3-list").hide();
    });
    $(".nav2").click(function () {
        var width = Math.round((window.screen.width - 800) / 2);
        var height = Math.round((window.screen.height - 600) / 2);
    });
});
<!--=E 尾部=-->
(function ($) {
    var loadImage = function () {
        $('[data-original]').each(function (i, e) {
            $(e).attr('src', $(e).data('original'));
        });
    };

    var playVideo = function () {
        //video play
        var video = document.getElementById("J_videoshow");
        $(video).on('canplay', function () {
            video.play();
            video.style.visibility = 'visible';
        });
    };

    $(function () {
        loadImage();
        playVideo();
    });
})(jQuery);

$(function () {
    $("#layout-bottom_close").click(function () {
        $(".bottomBar").hide();
    });

    $(".showweixin").mouseleave(function () {
        $(".weixinimg").hide();
    });

    $(".showweixin").mousemove(function () {
        $(".weixinimg").show();
    });
});

(function ($) {
    $(function () {
        $(".header-help").mousemove(function () {
            $(".help-list").show();
        });
        $(".header-help").mouseleave(function () {
            $(".help-list").hide();
        })
    });
})(jQuery);

(function ($) {
    $('#reg-username').mailAutoComplete();
    $('#login-username').mailAutoComplete();
})(jQuery);

$(function () {
    var slider = new TouchSlider('slider', {
        duration: 800,
        interval: 3000,
        direction: 0,
        autoplay: true,
        mousewheel: false,
        mouse: true,
        fullsize: true
    });
    $("#prev").click(function () {
        slider.prev();
    });
    $("#next").click(function () {
        slider.next();
    });
});
