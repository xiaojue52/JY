
Ext.onReady(function(){  
    //Ext.BLANK_IMAGE_URL = "ext/resources/images/default/s.gif";  
    var rootNode = new Ext.tree.AsyncTreeNode({  
        text : "设备管理",  
        id : "0",  
        expanded : true , 
        level:0,
        autoScroll:true
    });  
      
    var tree = new Ext.tree.TreePanel({  
        root : rootNode,  
        loader : new Ext.tree.TreeLoader({  
            dataUrl : "createTree.action"  
        }),  
        width : 200,  
        height : 400,  
        autoScroll:true
    });  
      
    tree.on("beforeload" , function(node){  
        tree.loader.baseParams.id = node.id;  
        tree.loader.baseParams.level = node.attributes.level; 
    });  
      
    tree.on("click", function(node){  
    	//alert(node.attributes.level);
    	switch(node.attributes.level){
    		case 2:
    			Ext.Ajax.request({  
    	            url : "showCabinetRecord.action",  
    	            params : {'cabinet.cabId':node.id},  
    	            success : function(res){  
    	                var txt = res.responseText;  
    	                var obj = Ext.decode(txt);  
    	                //alert(obj.cabinet.line.name);
    	                $("#line").val(obj.cabinet.line.name);
    	                $("#lineId").val(node.parentNode.id);
    	                $("#cabNumber").val(obj.cabinet.cabNumber);
    	                $("#simNumber").val(obj.cabinet.simNumber);
    	                $("#simSNumber").val(obj.cabinet.simSNumber);
    	                $("#cabType").val(obj.cabinet.cabType.id);
    	                $("#powerLevel").val(obj.cabinet.powerLevel.id);
    	                $("#user").val(obj.cabinet.user.userId);
    	                $("#cabinetPage form").attr({"action":"updateCabinet.action?cabinet.cabId="+node.id});
    	                $(".page").hide();
    	    			$("#cabinetPage").show();
    	            }  
    	        });
    			
    			break;
    		case 3:
    			Ext.Ajax.request({  
    	            url : "showDeviceRecord.action",  
    	            params : {'device.deviceId':node.id},  
    	            success : function(res){  
    	                var txt = res.responseText;  
    	                var obj = Ext.decode(txt);  
    	                //alert(obj.cabinet.line.name);
    	                $("#deviceNumber").val(obj.device.deviceNumber);
    	                $("#deviceName").val(obj.device.name);
    	                $("#cabinetId").val(node.parentNode.id);
    	                $("#cabinet").val(obj.device.cabinet.cabNumber+obj.device.cabinet.cabType.value);
    	                $("#deviceStatus").val(obj.device.status);
    	                $("#deviceNote").val(obj.device.note);
    	                $("#devicePage form").attr({"action":"updateDevice.action?device.deviceId="+node.id});
    	                $(".page").hide();
    	    			$("#devicePage").show();
    	            }  
    	        });
    			
    			break;
    		case 4:
    			Ext.Ajax.request({  
    	            url : "showDetectorRecord.action",  
    	            params : {'detector.detectorId':node.id},  
    	            success : function(res){  
    	                var txt = res.responseText;  
    	                var obj = Ext.decode(txt);  
    	                //alert(obj.cabinet.line.name);
    	                $("#parentDeviceNumber").val(obj.detector.device.deviceNumber);
    	                $("#parentDeviceName").val(obj.detector.device.name);
    	                $("#parentCabinet").val(obj.detector.device.cabinet.cabNumber+obj.detector.device.cabinet.cabType.value);
    	                $(".page").hide();
    	    			$("#detectorPage").show();
    	            }  
    	        });
    			
    			break;
    		default:
    			break;
    	}
    });  
    tree.on('contextmenu', function(node, event){
    	event.stopEvent();
    	if (node.attributes.level==3||node.attributes.level==4)
    		return;
    	rightMenu.showAt(event.getXY());
    	rightMenu.items.get(0).node = node;
    	rightMenu.items.get(1).node = node;
    }); 
    function onItemClick(){  
    	if (this.menuId==0){
    		switch(this.node.attributes.level){
    			case 0:
    				$(".page").hide();
	    			$("#linePage").show();
    				break;
    			case 1:
    				$("#line").val(this.node.text);
    				$("#lineId").val(this.node.id);
	                $("#cabNumber").val("");
	                $("#simNumber").val("");
	                $("#simSNumber").val("");
    				$(".page").hide();
	    			$("#cabinetPage").show();
    				break;	
    			case 2:
    				$("#deviceNumber").val("");
	                $("#deviceName").val("");
	                $("#cabinet").val(this.node.text);
	                $("#cabinetId").val(this.node.id);
	                $("#deviceStatus").val("离线");
	                $("#deviceNote").val("");
    				$(".page").hide();
	    			$("#devicePage").show();
    				break;	
	
    			default:
    				break;
    		}
    	}else if (this.menuId==1){
    		switch(node.attributes.level){
    		}
    	}
    }
    var rightMenu = new Ext.menu.Menu({
    	items:[{menuId:0,text:"增加子节点",handler:onItemClick},{menuId:1,text:"删除节点",handler:onItemClick}]
    }); 
    
    tree.render(Ext.get("tree_div"));  
      
});
