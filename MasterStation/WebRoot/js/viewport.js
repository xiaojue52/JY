
Ext.onReady(function() {
	
	//alert($('#userLevel').val());
	var menus;
	if ($('#userLevel').val()=="super_admin"){
		menus = [{
	    	contentEl : 'monitor_div',
	        title: '系统监控',
	        border: false,
	        iconCls: "monitor_png"
	    }, {
	        title: '系统管理',
	        contentEl : 'system_div',
	        border: false,
	        iconCls: 'settings_png'
	    },
	    {
	        title: '用户管理',
	        contentEl : 'user_div',
	        border: false,
	        iconCls: 'user_png'
	    },
	    {
	        title: '历史记录',
	        contentEl : 'history_div',
	        border: false,
	        iconCls: 'history_png'
	    },{
	        title: '数据对比',
	        contentEl : 'data_div',
	        border: false,
	        iconCls: 'data_png'
	    }];
	}
	else if($('#userLevel').val()=="com_admin"){
		menus = [{
	    	contentEl : 'monitor_div',
	        title: '系统监控',
	        border: false,
	        iconCls: "monitor_png"
	    }, {
	        title: '系统管理',
	        contentEl : 'system_div',
	        border: false,
	        iconCls: 'settings_png'
	    },
	    {
	        title: '历史记录',
	        contentEl : 'history_div',
	        border: false,
	        iconCls: 'history_png'
	    },{
	        title: '数据对比',
	        contentEl : 'data_div',
	        border: false,
	        iconCls: 'data_png'
	    }];
	}
	else 
	menus = [{
    	contentEl : 'monitor_div',
        title: '系统监控',
        border: false,
        iconCls: "monitor_png"
    }, 
    {
        title: '历史记录',
        contentEl : 'history_div',
        border: false,
        iconCls: 'history_png'
    },{
        title: '数据对比',
        contentEl : 'data_div',
        border: false,
        iconCls: 'data_png'
    }];
	
	new Ext.Viewport( {
		layout : 'border',
		items : [ {
			region : 'north',
			contentEl : 'north-div',
			border : false,
			split : true,
			height : 56,
			maxSize : 56,
			minSize : 56
		}, {
			region : 'south',
			contentEl : 'south-div',
			split : true,
			width : 50,
			height : 30,
			maxSize : 120,
			minSize : 50,
			frame : true
		}, {
			id:"center",
			region : 'center',
			contentEl : 'center-div',
			split : true,
			border : true,
			collapsible : false,
			title : '欢迎光临！',
			loadMask : {
				msg : '正在加载数据，请稍侯……'
			}
			//frame : true
		}, {
			region: 'west',
            id: 'west-panel', // see Ext.getCmp() below
            title: '功能清单',
            split: true,
            border : true,
            width: 200,
            minSize: 175,
            maxSize: 400,
            collapsible: true,
            margins: '0 0 0 5',
            layout: {
                type: 'accordion',
                animate: true
            },
            items: menus
		} ]
	});

	$('.center_frame').attr('src','mainAction.action');
	Ext.getCmp('center').setTitle('欢迎光临！');
});
var ModifyPassword = {};
ModifyPassword.showPage = function() {
	$("#BgDiv").css( {
		display : "block",
		height : $(document).height()
	});
	//var yscroll = document.documentElement.scrollTop;
	$("#DialogDiv").css("top", "100px");
	$("#DialogDiv").css("display", "block");
	document.documentElement.scrollTop = 0;
};
ModifyPassword.closePage = function() {
	$("#BgDiv").css("display", "none");
	$("#DialogDiv").css("display", "none");
};
ModifyPassword.verify = function() {
	if (document.getElementById("newPassword").value == document
			.getElementById("verifyPassword").value) {
		if (confirm("确定修改？")) {
			var password = $('#password').val();
			var newPassword = $('#newPassword').val();
			// alert(password+":"+newPassword);
			ModifyPassword.updateUser(password, newPassword);
		}
		return false;
	} else
		alert("密码不一致");
};
ModifyPassword.updateUser = function(password, newPassword) {

	$.ajax( {
		type : "post",
		url : "updatePassword.action",
		data: "password=" + password + "&newPassword="
			+ newPassword,		
		dataType:'text',
		success : function(returnData) {
			var obj = eval("(" + returnData + ")");
			// alert(obj.ret);
		if (obj.ret == 0) {
			alert("密码修改成功！请重新登录！");
			window.location = 'logout.action';
		} else {
			$(".errorMessage").html("密码错误，请重新输入！");
		}
	},
	error : function() {
		// alert("aa");
	}
	});
};
