$(function() {
	$("#btnShow").click(function() {
		// var str = "我是弹出对话框";
			// $(".form").html(str);
		$("#add-user-page").html('');
		$("#user-details-page").html('');
		$("#addUser_dialogDiv").show();
		$("#userDetails_dialogDiv").hide();
			$("#BgDiv").css( {
				display : "block",
				height : $(document).height()
			});
			//var yscroll = document.documentElement.scrollTop;
			$("#DialogDiv").css("top", "100px");
			$("#DialogDiv").css("display", "block");
			document.documentElement.scrollTop = 0;
			return false;
		});
	$(".btnClose").click(function() {
		$("#BgDiv").css("display", "none");
		$("#DialogDiv").css("display", "none");
	});
	
});

function getUserGroupDetails(id){
	$.ajax({
		type:"post",
		url:"showUserGroup.action?userGroup.id="+id,
		contentType:"application/x-www-form-urlencoded;charset=utf-8", 
		success:function(returnData){
			var obj = eval("("+returnData+")");
			var userGroup = obj.userGroup;
			$("#add-group-page").html('');
			$("#group-details-page").html('');
			$("#groupName").val(userGroup.groupName);
			$("#leaderName").val(userGroup.leaderName);
			$("#groupId").val(userGroup.id);
			$("#groupNote").val(userGroup.note);
			$("#addUser_dialogDiv").hide();
			$("#userDetails_dialogDiv").show();
			$("#BgDiv").css( {
				display : "block",
				height : $(document).height()
			});
			//var yscroll = document.documentElement.scrollTop;
			$("#DialogDiv").css("top", "100px");
			$("#DialogDiv").css("display", "block");
			document.documentElement.scrollTop = 0;
		},
		error:function(){
			//alert("aa");
		}
	});	
}
