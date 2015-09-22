function animate(node) {
	$(node).css({
		position : 'relative'
	});
	for (var x = 1; x <= 2; x++) {
		$(node).animate({
			left : (10 * -1)
		}, (((100 / 2) / 4))).animate({
			left : 10
		}, ((100 / 2) / 2)).animate({
			left : 0
		}, (((100 / 2) / 4)));
	}
}
function chooseCity(obj) {
	var name = $(obj).attr('name');
	var id = $(obj).attr('destid');
	var dest = $("#" + id).val();
	if (typeof (dest) != "undefined") {
		var node = $("#s" + id);
		animate(node);
		return;
	}
	var length = name.length;
	var html = "<div class=\"ms-sel-item \" id=\"s"
			+ id
			+ "\">"
			+ name
			+ "<span class=\"ms-close-btn\" onclick=\"deleteDest(this)\"></span><input type=\"hidden\" class='places' name=\"courses[0].places\" id=\""
			+ id + "\" value=\"" + id + "\"></div>";
	var width = $("#city").attr('widthNum');
	var size = $(".ms-sel-item ").size() + 1;
	if (size > 5) {
		return;
	}
	var th = width - (length * 12) - 37;
	if (th < 0) {
		th = 350 - (length * 12) - 37;
	}
	$("#city").attr('widthNum', th);
	document.getElementById("city").style.width = th + "px";
	$("#city").before(html);
}

function deleteDest(obj) {
	$(obj).parent().remove();
	var width = $("#city").attr('widthNum');
	var th = parseInt(width) + 60;
	if (th >= 350) {
		th = 10;
	}
	$("#city").attr('widthNum', th);
	document.getElementById("city").style.width = th + "px";
}
(function($) {
	var ajaxSubmit = function() {
		if (!$(".submit").hasClass("loading")) {
			$(".submit").addClass("loading").html("提交中...");
			$.ajax({
				type : "POST",
				dataType : "text",
				url : "plan/ct",
				data : $('#form_plan').serialize(),
				success : function(data) {
					if (data = "success") {
						_hmt.push(['_trackPageview', '/plan/createSuccess']);//百度统计
						slider.next();
					}
				},
				error : function(data) {
					alert("error:" + data.responseText);
				}
			});
		}
	}
	// 手机激活码验证＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
	function valid_show() {
		$(".model").show();
		$(".block").show();
	}
	function valid_hide() {
		$(".model").hide();
		$(".block").hide();
	}
	//人数验证
	var initMetaNumber = function(){
	
		$("#num").keyup(function(){  //keyup事件处理 
	        $(this).val($(this).val().replace(/\D|^0/g,''));
	    }).bind("paste",function(){  //CTR+V事件处理 
	        $(this).val($(this).val().replace(/\D|^0/g,'')); 
	    })
	};
	
	//游玩天数验证
	var initDayNumber= function(){
	
		$("#playDay").keyup(function(){  //keyup事件处理 
	        $(this).val($(this).val().replace(/\D|^0/g,''));
	    }).bind("paste",function(){  //CTR+V事件处理 
	        $(this).val($(this).val().replace(/\D|^0/g,'')); 
	    })
	};
	// 发送激活码
	var sendCode = function() {
		var phone = $('#phone').val();
		$(".mobile").html(phone);
		$.ajax({
			url : 'register/phone/login/resend',
			data : {
				phone : phone
			},
			async: false,
			dataType : 'json',
			type : 'POST',
			success : function(data) {
				if (data && data.success) {
					valid_show();
					$(".submit").removeClass("loading").html("提交")
				} else {
				}
			},
			error : function(data) {
				alert("error:" + data);
			}
		});
	};
	var tempCode;
	// 检测动态输入验证码
	var checkCode = function(dom) {
		var length = $(dom).val().length;
		if (length == 4) {
			$('#mobile_code').attr("readOnly", "readOnly");
			tempCode = $(dom).val();
			var code = $('#mobile_code').val();
			var phone = $('#phone').val();
			$.ajax({
				url : 'register/phone/login',
				data : {
					code : code,
					phone : phone
				},
				async: false,
				dataType : 'json',
				type : 'POST',
				success : function(data) {
					if (data && data.success) {
						valid_hide();
						ajaxSubmit();
					} else {
						animate($('#mobile_code'));
						$('#mobile_code').val("");
						sendCode();
						$(".code_error").css("display", "block");
					}
					$('#mobile_code').removeAttr("readOnly");
				},
				error : function(data) {
					alert("error:"+data);
				}
			});
		} else if (length <= 4) {
			tempCode = $(dom).val();
		} else {
			$(dom).val(tempCode);
		}
	}
	// 验证激活码
	var initVerify = function() {
		$("#mobile_code").keyup(function() { // keyup事件处理
			checkCode($(this));
		}).bind("paste", function() { // CTR+V事件处理
			checkCode($(this));
		})
	};
	/**
	 * 动态加减菜单，支持长按
	 */
	var initMenu=function(dom,id,method){
		if(method=="add"){
			$(dom).click(function() {
				var value;
				if($(id).val()!=""){
					value=parseInt($(id).val());
				}else{
					value=1;
				}
				$(id).val(value+1);
			});
			$(dom).bind("mousedown", function() {
				timeout_add = setTimeout(function() {
					auto_add=setInterval(function(){
						var value;
						if($(id).val()!=""){
							value=parseInt($(id).val());
						}else{
							value=1;
						}
						$(id).val(value+1);
					},188);
				}, 688);
			});
			$(dom).bind("mouseup", function() {
				clearTimeout(timeout_add);
				clearInterval(auto_add);
			});
		}else if(method=="del"){
			$(dom).click(function() {
				var value;
				if($(id).val()!=""&&$(id).val()!="1"){
					value=parseInt($(id).val());
					$(id).val(value-1);
				}else{
					value=1;
					$(id).val(value);
				}
			});
			$(dom).bind("mousedown", function() {
				timeout_del = setTimeout(function() {
					auto_del=setInterval(function(){
						var value;
						if($(id).val()!=""&&$(id).val()!="1"){
							value=parseInt($(id).val());
							$(id).val(value-1);
						}else{
							value=1;
							$(id).val(value);
						}
					},188);
				}, 688);
			});
			$(dom).bind("mouseup", function() {
				clearTimeout(timeout_del);
				clearInterval(auto_del);
			});
		}
	}
	$(function() {
		initMetaNumber();
		initDayNumber();
		initVerify();
		$(".close").click(function() {
			$(".model").hide();
			$(".block").hide();
		});

		$("#city,.down").click(function() {
			$(".dest").show();
		});
		$("#playDay").click(function() {
			$(".menu_right").hide();
			$(".menu_left").show();
		});
		$("#num").click(function() {
			$(".menu_left").hide();
			$(".menu_right").show();
		});
		initMenu(".menu_left>.add","#playDay","add");
		initMenu(".menu_left>.del","#playDay","del");
		initMenu(".menu_right>.add","#num","add");
		initMenu(".menu_right>.del","#num","del");
		//检测tab事件
		$("#num").keydown(function() {
			if(event.keyCode == 9) {
				return false;
			}
		});
		//检测tab事件
		$("#remark").keydown(function() {
			if(event.keyCode == 9) {
				return false;
			}
		});
		$("body")
				.click(
						function(e) {
							e = e || window.event;
							var src = e.target || e.srcElement;
							if ($(src).hasClass("city")
									|| $(src).attr("id") == "city"
									|| $(src).attr("id") == "playDay"
									|| $(src).attr("id") == "num"
									|| $(src).hasClass("down")
									|| $(src).hasClass("a")
									|| src.tagName == "SPAN") {
							} else {
								$(".dest").hide();
								$(".menu").hide();
							}
						});
		$(".ok").click(function() {
			$(".dest").hide();
		});
		$('#beginDate,.bgDate').click(function() {
			obj = {
				date : new Date(),
				year : -1,
				month : -1,
				priceArr : []
			};
			$.get("product/price-calender/49", function(data) {
				pickerEvent.setPriceArr(eval("(" + data + ")"));
				pickerEvent.Init("beginDate", function(date, price) {
				});
			});
		});

		$("#phone").keyup(function() { // keyup事件处理 /\D|^0/g
			$(this).val($(this).val().replace(/\D+/g, ''));
		}).bind("paste", function() { // CTR+V事件处理
			$(this).val($(this).val().replace(/\D+/g, ''));
		}).css("ime-mode", "disabled");
		var flag = false;
		// 第一页
		$("#next").click(function() {
			$(".error").hide();
			var bl = false;
			var warn = "_warn";
			var city = $(".places").val();
			var startDate = $("#beginDate").val();
			var playDay = $("#playDay").val();
			var num = $("#num").val();
			if (typeof (city) == "undefined" || city == "") {
				bl = true;
				$("#city" + warn).show();
				// 线框标红
			}
			if (startDate == "") {
				bl = true;
				$("#beginDate" + warn).show();
			}
			if (playDay == "") {
				bl = true;
				$("#playDay" + warn).show();
			}
			if (num == "") {
				bl = true;
				$("#num" + warn).show();
			}
			if (!bl) {
				flag = true;
				slider.next();
			}
		});
		// 上一步
		$("#prev").click(function() {
			if (!$(".submit").hasClass("loading")) {
				slider.prev();
			}
		});
		$("#submit").click(function() {
				if (flag) {// 如果第一步成功才让提交
								$(".error").hide();
								var bl = false;
								var warn = "_warn";
								var username = $("#username").val();
								var sex = $("input:radio:checked").val();
								var phone = $("#phone").val();
								var weChat = $("#WechatQQ").val();
								var remark = $("#remark").val();
								if (username == '') {
									bl = true;
									$("#username" + warn).show();
								}
								if (phone == '') {
									bl = true;
									$("#phone" + warn).show();
								}
								if (weChat == '') {
									bl = true;
									$("#weChat" + warn).show();
								}
								if (!bl) {
									if (!$(".submit").hasClass("loading")) {
										$(".submit").addClass("loading").html(
												"提交中...");
										var mobile = phone;
										$.ajax({
											type : "POST",
											cache : false,
											url : 'plan/checkMobiles?mobile='
													+ mobile,// 提交的URL
											data : '',
											dataType : "text",
											success : function(data) {
												if (data == "success") {
													$(".submit").removeClass(
															"loading").html(
															"提交")
													ajaxSubmit();// 异步表单
												} else {
													sendCode();
												}
											},
											error : function(data) {
												alert("error"+data);
											}
										});
									}
								}
							} else {
								slider.prev();
								$("#previous").click();
							}
			});
	});
})(jQuery);
