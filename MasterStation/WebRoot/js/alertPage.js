$(function() {
	$("#btnShow").click(function() {
		// var str = "我是弹出对话框";
			// $(".form").html(str);
			$("#BgDiv").css( {
				display : "block",
				height : $(document).height()
			});
			var yscroll = document.documentElement.scrollTop;
			$("#DialogDiv").css("top", "100px");
			$("#DialogDiv").css("display", "block");
			$("#page1").show();
			$("#page2").hide();
			document.documentElement.scrollTop = 0;
		});
	$("#btnShowGprs").click(function() {
		// var str = "我是弹出对话框";
		// $(".form").html(str);
		$("#BgDiv").css( {
			display : "block",
			height : $(document).height()
		});
		var yscroll = document.documentElement.scrollTop;
		$("#DialogDiv").css("top", "100px");
		$("#DialogDiv").css("display", "block");
		$("#page2").show();
		$("#page1").hide();
		document.documentElement.scrollTop = 0;
	});
	$("#btnClose").click(function() {
		$("#BgDiv").css("display", "none");
		$("#DialogDiv").css("display", "none");
	});
});

function setDeviceBox(id,owner){
	$("#BgDiv").css("display", "none");
	$("#DialogDiv").css("display", "none");
	//alert($("#owner"));
	//var owner = $("#"+id+" .owner").text();
	//alert(owner.length);
	$("#owner").val(owner);
	$("#deviceBox").val(id);
	
}

function setGprs(id,owner,deviceBoxId){
	$("#BgDiv").css("display", "none");
	$("#DialogDiv").css("display", "none");
	$("#gprs").val(id);
	$("#owner").val(owner);
	$("#deviceBox").val(deviceBoxId);
	
}
var tips = "";  
var clr = "#cccccc";  
jQuery(function(){  
    jQuery("#search").focus(function(){  
        // 获得焦点情况  
        if(jQuery(this).val() == tips)  
            jQuery(this).val("").css("color","");  
        else if(jQuery(this).val() != "")  
            jQuery(this).css("color","");  
    }).blur(function(){  
        // 失去焦点情况  
        if(jQuery(this).val() == "")  
            jQuery(this).val(tips).css("color",clr);  
    }).keyup(function(){  
        // 按键触发快速查询事件  
        var search = jQuery.trim(jQuery(this).val());  
        if(search == "" || search == tips)  
            return false;  
        if(search != jQuery("#searchv").val()){  
            // 重新排序table的内容  
            jQuery("tr[sel=sel]").each(function(i){  
                var current = jQuery(this).attr("vl");  
                if(current.indexOf(search) != -1){  
                    // 获取当前tr的第一行  
                    var first = jQuery("tr[sel=sel]:eq(0)");  
                    // 防止查询第一个无节点时无法移动  
                    if(first.attr("vl") != current){  
                        // 把当前tr移动到第一行  
                        jQuery(this).insertBefore(first);  
                    }  
                }  
            });  
            jQuery("#searchv").val(search);  
        }  
    });  
});  

