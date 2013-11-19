$(function() {
	$("#btnShow").click(function() {
		// var str = "我是弹出对话框";
			// $(".form").html(str);
		$("#addUser_dialogDiv").show();
		$("#userDetails_dialogDiv").hide();
			$("#BgDiv").css( {
				display : "block",
				height : $(document).height()
			});
			var yscroll = document.documentElement.scrollTop;
			$("#DialogDiv").css("top", "100px");
			$("#DialogDiv").css("display", "block");
			document.documentElement.scrollTop = 0;
		});
	$("#btnClose").click(function() {
		$("#BgDiv").css("display", "none");
		$("#DialogDiv").css("display", "none");
	});
});

function getUserDetails(id){
	$.ajax({
		type:"post",
		url:"showUser.action?user.userId="+id,
		contentType:"application/x-www-form-urlencoded;charset=utf-8", 
		success:function(returnData){
			var obj = eval("("+returnData+")");
			var user = obj.user;
			$("#user_username").val(user.username);
			$("#user_company").val(user.company);
			$("#user_contact").val(user.contact);
			$("#user_jobLevel").val(user.jobLevel);
			$("#user_userId").val(user.userId);
			if (user.userLevel=="user"){
				$("#user_userLevel").html("<option value='user'>普通用户</option><option value='com_admin'>普通管理员</option>");
			}else
				$("#user_userLevel").html("<option value='com_admin'>普通管理员</option><option value='user'>普通用户</option>");
			
			$("#addUser_dialogDiv").hide();
			$("#userDetails_dialogDiv").show();
			$("#BgDiv").css( {
				display : "block",
				height : $(document).height()
			});
			var yscroll = document.documentElement.scrollTop;
			$("#DialogDiv").css("top", "100px");
			$("#DialogDiv").css("display", "block");
			document.documentElement.scrollTop = 0;
		},
		error:function(){
			//alert("aa");
		}
	});	
}
