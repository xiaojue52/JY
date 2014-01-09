
function cleanCabinetMes(){
	$("#cabNumber").val("");
    $("#simNumber").val("");
    $("#simSNumber").val("");
    $("#terminal").val("自动生成，跟终端匹配");
    $("#cabinetTime").val("自动生成");
    $("#cabinetStatus").val("-1");
    document.getElementById("cabinetStatusCheckBox").checked = false;
}
function cleanDeviceMes(){
	$("#deviceNote").val("");
    $("#devicePositionNumber").val("");
    $("#devicePositionNumber").removeAttr("readonly");
    $("#deviceNumber").val("");
    $("#deviceName").val("");
}
var DeviceManager = {};
DeviceManager.windowTree = {};
DeviceManager.currentNode = {};

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
    	DeviceManager.currentNode = node;
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
    	                $("#cabinetNote").val(obj.cabinet.note);
    	                $("#cabinetTime").val(obj.dateTime);
    	                $("#cabinetTitle").text("柜体设备");
    	                //$("#cabNumber").attr("readonly","readonly");
    	                $("#cabinetAddBtn").hide();
    	                $("#cabinetUpdateBtn").show();
    	                $("#cabinetId").val(node.id);
    	                $("#terminal").val(node.id);
    	                $("#userGroup").val(obj.cabinet.userGroup.id);
    	                $("#cabinetStatus").val(obj.cabinet.status);
    	                $("#alarmTypeCollect").val(obj.cabinet.alarmTypeCollect.id);
    	                $("#type1Id").val(obj.cabinet.alarmTypeCollect.alarmType1.id);
    	                $("#type2Id").val(obj.cabinet.alarmTypeCollect.alarmType2.id);
    	                $("#type3Id").val(obj.cabinet.alarmTypeCollect.alarmType3.id);
    	                $("#type4Id").val(obj.cabinet.alarmTypeCollect.alarmType4.id);

    	                if (obj.cabinet.status==1){
    	                	document.getElementById("cabinetStatusCheckBox").checked = true;
    	                }else
    	                	document.getElementById("cabinetStatusCheckBox").checked = false;
    	                //$("#cabinetPage form").attr({"action":"updateCabinet.action?cabinet.cabId="+node.id});
    	                
    	                if (obj.cabinet.alarmTypeCollect.id == "-1"){
    	                	DeviceManager.removeChecked(1);
    	                	DeviceManager.removeChecked(2);
    	                	DeviceManager.removeChecked(3);
    	                	DeviceManager.removeChecked(4);
    	                }else
    	                {
    	                	if (obj.cabinet.alarmTypeCollect.alarmType1.enable==0){
    	                		DeviceManager.removeChecked(1);
    	                		$("#input1").val(obj.cabinet.alarmTypeCollect.alarmType1.value);
    	                	}else
    	                	{
    	                		DeviceManager.addChecked(1);
    	                		$("#input1").val(obj.cabinet.alarmTypeCollect.alarmType1.value);
    	                	}
    	                	if (obj.cabinet.alarmTypeCollect.alarmType2.enable==0){
    	                		DeviceManager.removeChecked(2);
    	                		$("#input2").val(obj.cabinet.alarmTypeCollect.alarmType2.value);
    	                	}
    	                	else
    	                	{
    	                		DeviceManager.addChecked(2);
    	                		$("#input2").val(obj.cabinet.alarmTypeCollect.alarmType2.value);
    	                	}
    	                	if (obj.cabinet.alarmTypeCollect.alarmType3.enable==0){
    	                		DeviceManager.removeChecked(3);
    	                		$("#input3").val(obj.cabinet.alarmTypeCollect.alarmType3.value);
    	                	}
    	                	else
    	                	{
    	                		DeviceManager.addChecked(3);
    	                		$("#input3").val(obj.cabinet.alarmTypeCollect.alarmType3.value);
    	                	}
    	                	if (obj.cabinet.alarmTypeCollect.alarmType4.enable==0){
    	                		DeviceManager.removeChecked(4);
    	                		$("#input4").val(obj.cabinet.alarmTypeCollect.alarmType4.subValue);
    	                		$("#input5").val(obj.cabinet.alarmTypeCollect.alarmType4.value);
    	                	}
    	                	else
    	                	{
    	                		DeviceManager.addChecked(4);
    	                		//alert(obj.cabinet.alarmTypeCollect.alarmType4.subValue+":::"+obj.cabinet.alarmTypeCollect.alarmType4.value);
    	                		$("#input4").val(obj.cabinet.alarmTypeCollect.alarmType4.subValue);
    	                		$("#input5").val(obj.cabinet.alarmTypeCollect.alarmType4.value);
    	                	}
    	                }
    	                
    	                $(".page").hide();
    	                $("#cabinet-page").html('');
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
    	                //alert(txt);
    	                $("#deviceNumber").val(obj.device.deviceNumber);
    	                $("#deviceName").val(obj.device.name);
    	                $("#deviceCabinetId").val(node.parentNode.id);
    	                $("#cabinet").val(obj.device.cabinet.cabNumber+obj.device.cabinet.cabType.value);
    	                $("#deviceNote").val(obj.device.note);
    	                $("#deviceTime").val(obj.dateTime);
    	                $("#deviceTitle").text("变送器");
    	                $("#deviceAddBtn").hide();
    	                $("#deviceUpdateBtn").show();
    	                $("#devicePositionNumber").val(obj.device.positionNumber);
    	                $("#devicePositionNumber").attr("readonly","readonly");
    	                $("#deviceId").val(node.id);
    	                //$("#devicePage form").attr({"action":"updateDevice.action?device.deviceId="+node.id});
    	                $(".page").hide();
    	                $("#device-page").html('');
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
    	                $("#detectorUserGroup").val(obj.detector.device.cabinet.userGroup.groupName);
    	                $(".page").hide();
    	    			$("#detectorPage").show();
    	            }  
    	        });
    			
    			break;
    		default:
    			break;
    	}
    });  
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
    	DeviceManager.currentNode = this.node;
    	//alert(this.node.parentNode);
    	if (this.menuId==0){
    		switch(this.node.attributes.level){
    			case 0:
    				$(".page").hide();
    				$("#line-page").html('');
	    			$("#linePage").show();
    				break;
    			case 1:
    				$("#line").val(this.node.text);
    				$("#lineId").val(this.node.id);
    				cleanCabinetMes();
	                //$("#cabNumber").removeAttr("readonly");
	                DeviceManager.removeChecked(1);
	                DeviceManager.removeChecked(2);
	                DeviceManager.removeChecked(3);
	                DeviceManager.removeChecked(4);
	                $("#cabinetTitle").text("增加柜体设备");
	                //$("#cabinetPage form").attr({"action":"addCabinet.action"});
	                $("#cabinetAddBtn").show();
	                $("#cabinetUpdateBtn").hide();
    				$(".page").hide();
    				$("#cabinet-page").html('');
	    			$("#cabinetPage").show();
    				break;	
    			case 2:
    				
	                $("#cabinet").val(this.node.text);
	                $("#deviceCabinetId").val(this.node.id);
	                cleanDeviceMes();
	                $("#deviceTitle").text("增加变送器");
	                $("#deviceAddBtn").show();
	                $("#deviceUpdateBtn").hide();
	                //$("#devicePage form").attr({"action":"addDevice.action"});
    				$(".page").hide();
    				$("#device-page").html('');
	    			$("#devicePage").show();
    				break;	
	
    			default:
    				break;
    		}
    	}else if (this.menuId==1){
    		switch(this.node.attributes.level){
    			case 1:
    				//window.location = "deleteLine.action?line.lineId="+this.node.id;
    				DeviceManager.deleteDevice("deleteLine.action?line.lineId="+this.node.id);
    				break;
    			case 2:
    				//window.location = "deleteCabinet.action?cabinet.cabId="+this.node.id;
    				DeviceManager.deleteDevice("deleteCabinet.action?cabinet.cabId="+this.node.id);
    				break;
    			case 3:
    				//window.location = "deleteDevice.action?device.deviceId="+this.node.id;
    				DeviceManager.deleteDevice("deleteDevice.action?device.deviceId="+this.node.id);
    				break;
    			default:
    				break;
    		}
    	}
    }
   
    
    if (tag==0){
    	tree.root.reload();
    }
    tree.render(Ext.get("tree_div")); 
    DeviceManager.windowTree = tree;
    DeviceManager.currentNode = DeviceManager.windowTree.root;
      
});
DeviceManager.queryDevice = function(){
	//alert(windowTree.loader.baseParams.id);
	var queryLine = $('#queryLine').val();
	var queryType = $('#queryType').val();
	var queryNumber = $('#queryNumber').val();
	var queryUserGroup = $('#queryUserGroup').val();
	//alert(queryLine);
	if (queryLine.length==0)queryLine='-1';
	if (queryType.length==0)queryType='-1';
	if (queryNumber.length==0)queryNumber='-1';
	if (queryUserGroup.length==0)queryUserGroup='-1';
	if (queryLine=="-1"&&queryType=='-1'&&queryNumber=='-1'&&queryUserGroup=='-1'){
		DeviceManager.windowTree.loader.baseParams.tag = 0;
	}else
		DeviceManager.windowTree.loader.baseParams.tag = 1;
	DeviceManager.windowTree.loader.baseParams.queryLine = queryLine;
	DeviceManager.windowTree.loader.baseParams.queryType = queryType;
	DeviceManager.windowTree.loader.baseParams.queryNumber = queryNumber;
	DeviceManager.windowTree.loader.baseParams.queryUserGroup = queryUserGroup;
	
	//alert(queryLine.length);
	DeviceManager.windowTree.root.reload();
};
DeviceManager.checkBoxSwitch = function(id){
	if (document.getElementById("checkbox"+id).checked==true){
		$("#input"+id).removeClass("readonly");
		$("#input"+id).removeAttr("readonly");
		$("#enable"+id).val(1);
		if (id==4){
			$("#input5").removeClass("readonly");
			$("#input5").removeAttr("readonly");
		}
	}
	else
	{
		$("#input"+id).addClass("readonly");
		$("#input"+id).attr("readonly","readonly");
		$("#enable"+id).val(0);
		if (id==4){
			$("#input5").addClass("readonly");
			$("#input5").attr("readonly","readonly");
		}
	}
};
DeviceManager.addChecked = function(id){
	document.getElementById("checkbox"+id).checked = true;
	$("#input"+id).removeClass("readonly");
	$("#input"+id).removeAttr("readonly");
	$("#enable"+id).val(1);
	if (id==4){
		$("#input5").removeClass("readonly");
		$("#input5").removeAttr("readonly");
	}
};
DeviceManager.removeChecked = function(id){
	document.getElementById("checkbox"+id).checked = false;
	$("#input"+id).addClass("readonly");
	$("#input"+id).attr("readonly","readonly");
	$("#enable"+id).val(0);
	if (id==4){
		$("#input5").addClass("readonly");
		$("#input5").attr("readonly","readonly");
	}
};
DeviceManager.checkValue = function(){
	if ($("#input1").val().length==0)
		$("#input1").val(0);
	if ($("#input2").val().length==0)
		$("#input2").val(0);
	if ($("#input3").val().length==0)
		$("#input3").val(0);
	if ($("#input4").val().length==0)
		$("#input4").val(0);
	if ($("#input5").val().length==0)
		$("#input5").val(0);
};
DeviceManager.Line = {};
DeviceManager.Line.line = function(){
	$.ajax( {
		type : "post",
		url : "addLine.action",
		data:"line.name="+$("#lineName").val(),
		dataType:'text',
		success : function(returnData) {
			var obj = eval("("+returnData+")");
			if(obj.data=="0")
				alert("操作失败");
			else{
				//alert(obj.data);
				DeviceManager.currentNode.reload();		
			}
		},
		error:function(){
			alert("操作失败");
		}
	});
};
DeviceManager.Cabinet = {};
DeviceManager.Cabinet.cabinet = function(order){
	//alert("4:"+$("#enable4").val()+"--"+"3:"+$("#enable3").val())
	if(DeviceManager.currentNode==null){
		alert("设备不存在");
		return;
	}

	var url = "";
	if (order==0)
		url = "addCabinet.action";
	if (order==1)
		url = "updateCabinet.action";
	$.ajax( {
		type : "post",
		url : url,
		data:"cabinet.cabType.id="+$("#cabType").val()+
		"&cabinet.cabNumber="+$("#cabNumber").val()+
		"&cabinet.powerLevel.id="+$("#powerLevel").val()+
		"&cabinet.note="+$("#cabinetNote").val()+
		"&cabinet.simNumber="+$("#simNumber").val()+
		"&cabinet.simSNumber="+$("#simSNumber").val()+
		"&cabinet.alarmTypeCollect.alarmType1.value="+$("#input1").val()+
		"&cabinet.alarmTypeCollect.alarmType1.enable="+$("#enable1").val()+
		"&cabinet.alarmTypeCollect.alarmType2.value="+$("#input2").val()+
		"&cabinet.alarmTypeCollect.alarmType2.enable="+$("#enable2").val()+
		"&cabinet.alarmTypeCollect.alarmType3.value="+$("#input3").val()+
		"&cabinet.alarmTypeCollect.alarmType3.enable="+$("#enable3").val()+
		"&cabinet.alarmTypeCollect.alarmType4.value="+$("#input5").val()+
		"&cabinet.alarmTypeCollect.alarmType4.subValue="+$("#input4").val()+
		"&cabinet.alarmTypeCollect.alarmType4.enable="+$("#enable4").val()+
		"&cabinet.line.lineId="+$("#lineId").val()+
		"&cabinet.cabId="+$("#cabinetId").val()+
		"&cabinet.status="+$("#cabinetStatus").val()+
		"&cabinet.userGroup.id="+$("#userGroup").val()+
		"&cabinet.alarmTypeCollect.id="+$("#alarmTypeCollect").val()+
		"&cabinet.alarmTypeCollect.alarmType1.id="+$("#type1Id").val()+
		"&cabinet.alarmTypeCollect.alarmType2.id="+$("#type2Id").val()+
		"&cabinet.alarmTypeCollect.alarmType3.id="+$("#type3Id").val()+
		"&cabinet.alarmTypeCollect.alarmType4.id="+$("#type4Id").val()
		,
		dataType:'text',
		success : function(returnData) {
			var obj = eval("("+returnData+")");
			if(obj.data=="0")
				alert("编号相同，请重新输入");
			else{
				//alert(obj.data);
				if (order==1){
					DeviceManager.currentNode.setText($("#cabNumber").val()+$("#cabType").find("option:selected").text());
				}
				if (order==0){
					cleanCabinetMes();
				}
				DeviceManager.currentNode.reload();	
			}
		},
		error:function(){
			alert("操作失败");
		}
	});
};
DeviceManager.Device = {};
DeviceManager.Device.device = function(order){
	if(DeviceManager.currentNode==null){
		alert("设备不存在");
		return;
	}
	var url = "";
	if (order==0){
		url = "addDevice.action";
	}
	if (order==1){
		url = "updateDevice.action";
	}
	$.ajax( {
		type : "post",
		url : url,
		data:"device.deviceNumber="+$("#deviceNumber").val()+
		"&device.name="+$("#deviceName").val()+
		"&device.positionNumber="+$("#devicePositionNumber").val()+
		"&device.note="+$("#deviceNote").val()+
		"&device.cabinet.cabId="+$("#deviceCabinetId").val()+
		"&device.deviceId="+$("#deviceId").val(),
		dataType:'text',
		success : function(returnData) {
			var obj = eval("("+returnData+")");
			if(obj.data=="0")
				alert("间隔序号重复");
			else{
				//alert(obj.data);
				if (order==1){
					DeviceManager.currentNode.setText($("#deviceName").val());
				}
				if (order==0){
					cleanDeviceMes();
				}
				DeviceManager.currentNode.reload();		
			}
		},
		error:function(){
			alert("操作失败");
		}
	});
};
DeviceManager.deleteDevice = function(url){
	$.ajax( {
		type : "post",
		url : url,
		dataType:'text',
		success : function(returnData) {
			var obj = eval("("+returnData+")");
			if(obj.data=="0")
				alert("操作失败");
			else{
				//alert(obj.data);
				DeviceManager.currentNode.parentNode.reload();	
				DeviceManager.currentNode = null;
				$(".page").hide();
				$("#deletePage").show();
			}
		},
		error:function(){
			alert("操作失败");
		}
	});
};
DeviceManager.switchCabinetStatus = function(arg){
	if (arg.checked==true){
		$("#cabinetStatus").val(1);
	}else
		$("#cabinetStatus").val(0);
};
