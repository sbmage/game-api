/* png */
function setPng24(obj) {
	obj.width = obj.height = 1;
	obj.className = obj.className.replace(/\bpng24\b/i,'');
	obj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+ obj.src +"',sizingMethod='image');"
	obj.src = '';
	return '';
}


$(window).load(function(){

	$(".btn_prev").click(function(){
	    event.preventDefault();
	    history.back();
	});

	$(".btn_close").click(function(){
	    event.preventDefault();
	    window.location.href= "uniwebview://close";
	    //window.location.href= "http://www.naver.com";
	    //window.close();
	});

	$(".btn_top").click(function(){
		event.preventDefault();
		$("html, body").animate({ scrollTop: 0 }, "slow");
		return false;
	});

	$(".main_list_ty01 .faq a").click(function(){
		if(!$(this).data("click")){
			$(".faq").removeClass("on").addClass("on");
			$(this).parent().parent().parent().parent().find("#faq").css("display", "block");
			$(this).data("click", true);
			$(".faq").data("click", false);
		}else{
			$(".faq").removeClass("on");
			$(this).parent().parent().parent().parent().find("#faq").css("display", "none");
			$(this).data("click", false);
		}
	});

	$(".main_list_ty01 .notice a").click(function(){
		if(!$(this).data("click")){
			$(".notice").removeClass("on").addClass("on");
			$(this).parent().parent().parent().parent().find("#notice").css("display", "block");
			$(this).data("click", true);
			$(".notice").data("click", false);
		}else{
			$(".notice").removeClass("on");
			$(this).parent().parent().parent().parent().find("#notice").css("display", "none");
			$(this).data("click", false);
		}
	});

	$(".main_list_ty04 li a").click(function(){
		if(!$(this).data("click")){
			$(this).parent('li').removeClass("on").addClass('on');
			$(this).data("click", true);
			$("li").data("click", false);
		}else{
			$(this).parent('li').removeClass("on");
			$(this).data("click", false);
		}
	});

	/* ���� ���� �� */
	$(".customer_list li a").click(function(){
	    event.preventDefault();
		if(!$(this).data("click")){
			$(this).parent().find("a").removeClass("on").addClass("on");
			$(this).parent().find("p.cont").css("display", "block");
			$(this).data("click", true);
			$(".customer_list2 li").data("click", false);
		}else{
			$(this).parent().find("a").removeClass("on");
			$(this).parent().find("p.cont").css("display", "none");
			$(this).data("click", false);
		}
	});

	/* �������� */
	$(".stit_ty02 .btn_more a").click(function(){
		    event.preventDefault();
		if(!$(this).data("click")){
			$(".stit_ty02 .btn_more").removeClass("on").addClass("on");
			$(this).parent().parent().parent().parent().find(".customer_list2 ul.more").css("display", "block");
			$(this).data("click", true);
			$(".customer_list2 li").data("click", false);
		}else{
			$(".stit_ty02 .btn_more").removeClass("on");
			$(this).parent().parent().parent().parent().find(".customer_list2 ul.more").css("display", "none");
			$(this).data("click", false);
		}
	});

	// CHECK BOX
	$("span.ch_ty1").click(function(){
		var checkBox = $(this).parent().find("input");
		//alert(checkBox.attr("checked"));
		checkBox.trigger("click");
	});
	
	$("input.ch_ty1").change(function(){
		var cover = $(this).parent().find("span");
		if(this.checked){
			cover.addClass("on");
		}else{
			cover.removeClass("on");
		}
	});


	/* Interaction : 'Slider'
	$('#sliderMain1').cycle({
		fx: 'scrollHorz',
		speed: 'fast',
		timeout: 0,
		prev: '#sliderMainPrev1',
		next: '#sliderMainNext1'
	});
	$('#sliderMain2').cycle({
		fx: 'scrollHorz',
		speed: 'fast',
		timeout: 0,
		prev: '#sliderMainPrev2',
		next: '#sliderMainNext2'
	});*/

	
});


function replace($this, state){
	var src = $this.attr("src");
	if(state == "on")
		src = src.replace(/_off/, "_on");
	else
		src = src.replace(/_on/, "_off");

	$this.attr("src", src);
}


function popOpen(layerName){
	document.getElementById(layerName).style.display = 'block';
	return false;
}
function popClose(layerName){
	document.getElementById(layerName).style.display = 'none';
	return false;
}
var isMobile = {
	    Android: function() {
	        return navigator.userAgent.match(/Android/i);
	    },
	    BlackBerry: function() {
	        return navigator.userAgent.match(/BlackBerry/i);
	    },
	    iOS: function() {
	        return navigator.userAgent.match(/iPhone|iPad|iPod/i);
	    },
	    Opera: function() {
	        return navigator.userAgent.match(/Opera Mini/i);
	    },
	    Windows: function() {
	        return navigator.userAgent.match(/IEMobile/i);
	    },
	    any: function() {
	        return (isMobile.Android() || isMobile.BlackBerry() || isMobile.iOS() || isMobile.Opera() || isMobile.Windows());
	    }
	};
