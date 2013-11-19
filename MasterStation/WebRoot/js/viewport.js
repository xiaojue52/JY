Ext.onReady(function() {
	var viewport = new Ext.Viewport( {
		layout : 'border',
		items : [ {
			region : 'north',
			contentEl : 'north-div',
			border : true,
			split : true,
			height : 80,
			maxSize : 120,
			minSize : 50,
			frame : true
		}, {
			region : 'south',
			contentEl : 'south-div',
			split : true,
			width : 50,
			height : 30,
			maxSize : 120,
			minSize : 50,
			frame : true,
		}, {
			region : 'center',
			contentEl : 'center-div',
			split : true,
			border : true,
			collapsible : false,
			title : '欢迎光临！',
			maxSize : 120,
			minSize : 50,
			height : 500,
			loadMask : {
				msg : '正在加载数据，请稍侯……'
			},
			frame : true
		}, {
			region: 'west',
            id: 'west-panel', // see Ext.getCmp() below
            title: '功能清单',
            split: true,
            width: 200,
            minSize: 175,
            maxSize: 400,
            collapsible: true,
            margins: '0 0 0 5',
            layout: {
                type: 'accordion',
                animate: true
            },
            items: [{
            	contentEl : 'monitor_div',
                title: '系统监控',
                border: false,
            }, {
                title: '系统管理',
                contentEl : 'system_div',
                border: false,
                //iconCls: 'settings'
            },
            {
                title: '用户管理',
                contentEl : 'user_div',
                border: false,
                //iconCls: 'settings'
            },
            {
                title: '历史记录',
                contentEl : 'history_div',
                border: false,
                //iconCls: 'settings'
            },{
                title: '数据对比',
                contentEl : 'data_div',
                border: false,
                //iconCls: 'settings'
            }]
		} ]
	});
});