function showPage() {
	
	$("#BgDiv").css( {
		display : "block",
		height : $(document).height()
	});
	var yscroll = document.documentElement.scrollTop;
	$("#DialogDiv").css("top", "100px");
	$("#DialogDiv").css("display", "block");
	document.documentElement.scrollTop = 0;
}
function closePage() {
	$("#BgDiv").css("display", "none");
	$("#DialogDiv").css("display", "none");
}
function verify() {
	if (document.getElementById("newPassword").value == document
			.getElementById("verifyPassword").value) {
		if (confirm("确定修改？")) {
			var password = $('#password').val();
			var newPassword = $('#newPassword').val();
			//alert(password+":"+newPassword);
			updateUser(password,newPassword);
		}
		return false;
	} else
		alert("密码不一致");
}
function updateUser(password,newPassword) {
	
			$.ajax( {
				type : "post",
				url : "updatePassword.action?password="+password+"&newPassword="+newPassword,
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				success : function(returnData) {
					var obj = eval("(" + returnData + ")");
					//alert(obj.ret);
					if (obj.ret==0){
						alert("密码修改成功！请重新登录！");
						window.location = 'logout.action';
					}else
					{
						$(".errorMessage").html("密码错误，请重新输入！");
					}
				},
				error : function() {
					// alert("aa");
			}
			});
}
