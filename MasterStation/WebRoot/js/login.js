
var Login = {}
Login.init = function(){
	if (window.parent.document.getElementById("content_iframe") != null) {
		window.parent.location = "index.jsp";
	}
}
Login.checkInput = function() {
	var username = document.getElementById("username");
	var pass = document.getElementById("password");
	if (username.value == "") {
		alert("请输入用户名");
		username.focus();
		return false;
	}
	if (pass.value == "") {
		alert("请输入密码");
		return false;
	}
	return true;
}
Login.verify = function() {
	
	if (Login.checkInput()) {
		// alert("username="+$("#username").val()+"&password="+$("#password").val());
		// document.getElementById("loginForm").submit();
		$.ajax( {
			type : "post",
			url : "login.action",
			data : "username=" + $("#username").val() + "&password="
					+ $("#password").val(),
			dataType : 'text',
			success : function(returnData) {
				var obj = eval("(" + returnData + ")");
				if (obj.data == "0") {
					$("#error").text("用户名密码错误！");
				} else {
					window.location = "main.jsp";
				}
			},
			error : function() {
				alert("内部错误！");
			}
		});
	}
}
Login.init();