(function(){
    var enbale1=$("#enable1").val();
	var enbale2=$("#enable2").val();
	var enbale3=$("#enable3").val();
	if (enbale1==1)document.getElementById("alarmTypeEnable1").checked = true;else document.getElementById("alarmTypeEnable1").checked = false;
	if (enbale2==1)document.getElementById("alarmTypeEnable2").checked = true;else document.getElementById("alarmTypeEnable2").checked = false;
	if (enbale3==1)document.getElementById("alarmTypeEnable3").checked = true;else document.getElementById("alarmTypeEnable3").checked = false;
})();
function setEnable(id){
	if(document.getElementById("alarmTypeEnable"+id).checked == true)
		$("#enable"+id).val(1);
	else
		$("#enable"+id).val(0);
}

