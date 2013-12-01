var windowTree;
Ext.onReady(function(){  
    //Ext.BLANK_IMAGE_URL = "ext/resources/images/default/s.gif";  
	
    var rootNode = new Ext.tree.AsyncTreeNode({  
        text : "设备管理",  
        id : "0",  
        //expanded : true , 
        level:0,
        autoScroll:true
    });  
      
    var tree = new Ext.tree.TreePanel({  
        root : rootNode,  
        loader : new Ext.tree.TreeLoader({  
            dataUrl : "getTreeData.action"  
        }),  
        width : 200,  
        height : 480,  
        autoScroll:true
    });  
      
    tree.on("beforeload" , function(node){  
        tree.loader.baseParams.id = node.id;  
        tree.loader.baseParams.level = node.attributes.level; 
    });  
    tree.on("load",function(node){

    		//n.getUI().getIconEl().src = "../images/setting.png"
    });  

    tree.on("click", function(node){  
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
    	                
    	                if (obj.cabinet.alarmTypeCollect.id == "-1"){
    	                	removeChecked(1);
    	                	removeChecked(2);
    	                	removeChecked(3);
    	                }else
    	                {
    	                	if (obj.cabinet.alarmTypeCollect.alarmType1.enable==0){
    	                		removeChecked(1);
    	                	}else
    	                	{
    	                		addChecked(1);
    	                		$("#input1").val(obj.cabinet.alarmTypeCollect.alarmType1.value);
    	                	}
    	                	if (obj.cabinet.alarmTypeCollect.alarmType2.enable==0){
    	                		removeChecked(2);
    	                	}
    	                	else
    	                	{
    	                		addChecked(2);
    	                		$("#input2").val(obj.cabinet.alarmTypeCollect.alarmType2.value);
    	                	}
    	                	if (obj.cabinet.alarmTypeCollect.alarmType3.enable==0){
    	                		removeChecked(3);
    	                	}
    	                	else
    	                	{
    	                		addChecked(3);
    	                		$("#input3").val(obj.cabinet.alarmTypeCollect.alarmType3.value);
    	                	}
    	                }
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
    	                $("#deviceUser").val(obj.device.cabinet.user.username);	
    	             
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
    	                $("#detectorUser").val(obj.detector.device.cabinet.user.username);
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
    	if (node.attributes.level==4)
    		return;
    	if (node.attributes.level==3){
    		rightMenu2.showAt(event.getXY());
        	rightMenu2.items.get(0).node = node;
        	return;
    	}
    	if (node.attributes.level==0){
    		rightMenu1.showAt(event.getXY());
        	rightMenu1.items.get(0).node = node;
        	return;
    	}
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
	                removeChecked(1);
	                removeChecked(2);
	                removeChecked(3);
	                $("#cabinetPage form").attr({"action":"addCabinet.action"});
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
	                $("#devicePage form").attr({"action":"addDevice.action"});
    				$(".page").hide();
	    			$("#devicePage").show();
    				break;	
	
    			default:
    				break;
    		}
    	}else if (this.menuId==1){
    		switch(this.node.attributes.level){
    			case 1:
    				window.location = "deleteLine.action?line.lineId="+this.node.id;
    				break;
    			case 2:
    				window.location = "deleteCabinet.action?cabinet.cabId="+this.node.id;
    				break;
    			case 3:
    				window.location = "deleteDevice.action?device.deviceId="+this.node.id;
    				break;
    			default:
    				break;
    		}
    	}
    }
    var rightMenu = new Ext.menu.Menu({
    	shadow : "sides",
    	items:[{menuId:0,text:"增加子节点",handler:onItemClick},{menuId:1,text:"删除节点",handler:onItemClick}]
    }); 
    var rightMenu1 = new Ext.menu.Menu({
    	items:[{menuId:0,text:"增加子节点",handler:onItemClick}]
    });
    var rightMenu2 = new Ext.menu.Menu({
    	items:[{menuId:1,text:"删除节点",handler:onItemClick}]
    });
    
    if (tag==0){
    	tree.root.reload();
    }
    tree.render(Ext.get("tree_div")); 
    windowTree = tree;
      
});
function queryDevice(){
	//alert(windowTree.loader.baseParams.id);
	var queryLine = $('#queryLine').val();
	var queryType = $('#queryType').val();
	var queryNumber = $('#queryNumber').val();
	var queryUser = $('#queryUser').val();
	//alert(queryLine);
	if (queryLine.length==0)queryLine='-1';
	if (queryType.length==0)queryType='-1';
	if (queryNumber.length==0)queryNumber='-1';
	if (queryUser.length==0)queryUser='-1';
	if (queryLine=="-1"&&queryType=='-1'&&queryNumber=='-1'&&queryUser=='-1'){
		windowTree.loader.baseParams.tag = 0;
	}else
		windowTree.loader.baseParams.tag = 1;
	windowTree.loader.baseParams.queryLine = queryLine;
	windowTree.loader.baseParams.queryType = queryType;
	windowTree.loader.baseParams.queryNumber = queryNumber;
	windowTree.loader.baseParams.queryUser = queryUser;
	
	//alert(queryLine.length);
	windowTree.root.reload();
}
function checkBoxSwitch(id){
	if (document.getElementById("checkbox"+id).checked==true){
		//removeChecked(id);
		$("#input"+id).removeClass("readonly");
		$("#input"+id).removeAttr("readonly");
		$("#checkbox"+id).attr("checked","checked");
		$("#enable"+id).val(1);
	}
	else
	{
		//alert('2');
		//addChecked(id);
		$("#input"+id).addClass("readonly");
		$("#input"+id).attr("readonly","readonly");
		$("#checkbox"+id).removeAttr("checked");
		$("#enable"+id).val(0);
	}
}
function addChecked(id){
	document.getElementById("checkbox"+id).checked = true;
	$("#input"+id).removeClass("readonly");
	$("#input"+id).removeAttr("readonly");
	$("#checkbox"+id).attr("checked","checked");
	$("#enable"+id).val(1);
}
function removeChecked(id){
	document.getElementById("checkbox"+id).checked = false;
	$("#input"+id).addClass("readonly");
	$("#input"+id).attr("readonly","readonly");
	$("#checkbox"+id).removeAttr("checked");
	$("#enable"+id).val(0);
}
