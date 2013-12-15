var Control = {};

$(document).ready(function() {
    $(".numberInput").keydown(function(event) {
        // Allow: backspace, delete, tab, escape, enter and .
        if ( $.inArray(event.keyCode,[46,8,9,27,13,190]) !== -1 ||
             // Allow: Ctrl+A
            (event.keyCode == 65 && event.ctrlKey === true) || 
             // Allow: home, end, left, right
            (event.keyCode >= 35 && event.keyCode <= 39)) {
                 // let it happen, don't do anything
                 return;
        }
        else {
            // Ensure that it is a number and stop the keypress
            if (event.shiftKey || (event.keyCode < 48 || event.keyCode > 57) && (event.keyCode < 96 || event.keyCode > 105 )) {
                event.preventDefault(); 
            }   
        }
    });
    $('.floatNumber').keypress(function(event) {
  	  if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
  	    event.preventDefault();
  	  }
  	});
});


Control.checkNumber = function(){
	var numberRegex = /^[+-]?\d+(\.\d+)?([eE][+-]?\d+)?$/;
	var str = $('.numberInput').val();
	if(numberRegex.test(str)) {
	   $('#numberForm').submit();
	}
	else
		alert("请输入数字！");
}
Control.checkInput = function(id,cl,ecl) {
	
	var ret = 0;
	$('.'+cl).each(function(){
		if ($(this).val()==""){
			ret = 1;
		}
	});
	if (ret==1){
		//alert("带*为必填项！请填写完整！");
		$("#"+id).html("带*为必填项！请填写完整！");
		return false;
	}
	$('.'+ecl).each(function(){
		if ($(this).val()==""){
			$(this).val('--');
		}
	});
	return true;
}
Control.checkInputLength = function(id,cl,le){
	var ret = 0;
	$('.'+cl).each(function(){
		if ($(this).val().length<le){
			ret = 1;
		}
	});
	if (ret==1){
		//alert("带*为必填项！请填写完整！");
		$("#"+id).html("带*为必填项！请填写完整！长度>"+le);
		return false;
	}
	return true;
}
Control.checkInputFixedLength = function(id,cl,le){
	var ret = 0;
	$('.'+cl).each(function(){
		if ($(this).val().length!=le){
			ret = 1;
		}
	});
	if (ret==1){
		//alert("带*为必填项！请填写完整！");
		$("#"+id).html("带*为必填项！请填写完整！长度="+le);
		return false;
	}
	return true;
}
Control.orderByColumn = function(action,column){
	window.location = action+"?orderColumn="+column;
}

