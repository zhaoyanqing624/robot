
$(function(){
	if(navigator.userAgent.indexOf('Trident') > -1){//IE内核
		getInformation();
	}else if(navigator.userAgent.indexOf('Presto') > -1){//opera内核
		
	}else if(navigator.userAgent.indexOf('AppleWebKit') > -1){//苹果、谷歌内核
		
	}else if(navigator.userAgent.indexOf('Firefox') > -1){//火狐内核Gecko
		
	}
});
function getInformation(){
	var informationOfHardware = getInformationOfHardware();
		var macAddress = informationOfHardware.macAddress;
		var ipAddress = informationOfHardware.ipAddress;
		var equipmentModel = informationOfHardware.equipmentModel;
		var equipmentTime = informationOfHardware.equipmentTime;
		var CPU = informationOfHardware.CPU;
		var RAM = informationOfHardware.RAM;
		var memoryBank = informationOfHardware.memoryBank;
		var hardDrive = informationOfHardware.hardDrive;
		var hardDriveModel = informationOfHardware.hardDriveModel;
		var networkCard = informationOfHardware.networkCard;
		var motherBoard = informationOfHardware.motherBoard;
		var osName = informationOfHardware.osName;
		var osType = informationOfHardware.osType;
		var osVersion = informationOfHardware.osVersion;
		var osId = informationOfHardware.osId;
		var graphicCard = informationOfHardware.graphicCard;
		var audioCard = informationOfHardware.audioCard;
		var softWare = informationOfHardware.software;
		var path = informationOfHardware.path;
	$.ajax({
		type:"POST",
		url:"/org.xjtusicd3.partner/getInformation.html",
		traditional: true,
		data:{
			"macAddress":macAddress,
			"ipAddress":ipAddress,
			"equipmentModel":equipmentModel,
			"equipmentTime":equipmentTime,
			"CPU":CPU,
			"RAM":RAM,
			"memoryBank":memoryBank,
			"hardDrive":hardDrive,
			"hardDriveModel":hardDriveModel,
			"networkCard":networkCard,
			"motherBoard":motherBoard,
			"osName":osName,
			"osType":osType,
			"osVersion":osVersion,
			"osId":osId,
			"graphicCard":graphicCard,
			"audioCard":audioCard,
			"softWare":softWare,
			"path":path
		},
		dataType:"json",
		success:function(data){
			
			alert("success");
		}
	})
}
//获取设备硬件信息
function getInformationOfHardware(){
    var macAddress;
    var ipAddress;
    var equipmentModel;
    var equipmentTime;
    var CPU;
    var RAM=0;
    var memoryBank="";
    var hardDrive="";
    var hardDriveModel;
    var networkCard;
    var motherBoard;
    var osName;
    var osType;
    var osVersion;
    var osId;
    var graphicCard="";
    var audioCard = "";
    var pathInformation="";
    var softWareInformation="";
	var locator = new ActiveXObject ("WbemScripting.SWbemLocator");  
	var service = locator.ConnectServer(".");
	//获取MAC、IP地址
	var a = new Enumerator (service.ExecQuery("SELECT * FROM Win32_NetworkAdapterConfiguration"));
	for (; !a.atEnd(); a.moveNext()) {
	    var p = a.item();
	    
	    if (p.IPAddress==null) {
	         continue;
	    }else{
	    	if(p.Description.substring(0,8)=="Intel(R)"){
	    		
	            macAddress = p.MACAddress;
	            ipAddress = p.IPAddress(0);
	    	}
	    }                           
    }
	if(document.getElementById("equipment")!=null){
		document.getElementById("equipment").href='personal3.html?e='+macAddress;
	}
	//获取计算机型号
	var b = new Enumerator (service.ExecQuery("SELECT * FROM Win32_ComputerSystem")); 
	for (; !b.atEnd(); b.moveNext()) {
	    var p = b.item();
	    equipmentModel = p.Model;
    }
	//获取BIOS的时间
	var q =new Enumerator (service.ExecQuery("SELECT * FROM Win32_BIOS"));
	for (; !q.atEnd(); q.moveNext()) {
	    var p = q.item();
	    equipmentTime = p.ReleaseDate.substring(0,8);
	}
	//获取CPU
	var c = new Enumerator (service.ExecQuery("SELECT * FROM Win32_Processor"));
	for (; !c.atEnd(); c.moveNext()) {
	    var p = c.item();
	    CPU = p.Name;
    }
	//获取内存、内存条编号及大小
	var d = new Enumerator (service.ExecQuery("SELECT * FROM Win32_PhysicalMemory"));
	for (; !d.atEnd(); d.moveNext()) {
	    var p = d.item();
	    RAM = RAM + p.Capacity/(1024 * 1024 * 1024);
	    if(p.SerialNumber!=null){
	    	memoryBank = memoryBank + p.SerialNumber+"/"+p.Capacity/(1024 * 1024 * 1024)+";";
	    }
    }
	RAM = Math.round(RAM)+"GB";
	//获取硬盘大小、硬盘型号
	var e = new Enumerator (service.ExecQuery("SELECT * FROM Win32_DiskDrive"));
	for (; !e.atEnd(); e.moveNext()) {
	    var p = e.item();
	    hardDrive = Math.round(p.Size/(1024 * 1024 * 1024))+"GB";
	    hardDriveModel = p.Model;
	}
	//获取网卡
	var h =new Enumerator (service.ExecQuery("SELECT * FROM Win32_NetworkAdapter"));
	for (; !h.atEnd(); h.moveNext()) {
	    var p = h.item();
	    if(p.Description.substring(0,8)=="Intel(R)"){
	    	networkCard = p.Name;
	    }
	}
	//获取主板信息
	var f = new Enumerator (service.ExecQuery("SELECT * FROM Win32_BaseBoard"));
	for (; !f.atEnd(); f.moveNext()) {
	    var p = f.item();
	    motherBoard = p.Manufacturer+" "+p.Product;
	}
	//获取操作系统
	var g =new Enumerator (service.ExecQuery("SELECT * FROM Win32_OperatingSystem"));
	for (; !g.atEnd(); g.moveNext()) {
	    var p = g.item();
	    osName = p.Caption;
	    osType = p.OSArchitecture;
	    osVersion = p.Version;
	    osId = p.SerialNumber;
	}
	//获取显卡
	var h =new Enumerator (service.ExecQuery("SELECT * FROM Win32_VideoController"));
	for (; !h.atEnd(); h.moveNext()) {
	    var p = h.item();
	    graphicCard = graphicCard+p.Description+"("+p.DriverVersion+");";
	}
	//读取声卡
	var j =new Enumerator (service.ExecQuery("SELECT * FROM Win32_SoundDevice"));
	for (; !j.atEnd(); j.moveNext()) {
	    var p = j.item();
	    if(audioCard.indexOf(p.Description)<0){
	    	audioCard = audioCard+p.Description+";";
	    }
	}
	
	//获取补丁
	var path = new Array();
	var k =new Enumerator (service.ExecQuery("SELECT * FROM Win32_QuickFixEngineering"));
	for (; !k.atEnd(); k.moveNext()) {
	    var p = k.item();
	    pathInformation = p.Description+";"+p.HotFixID+";"+p.InstalledOn;
	    path.push(pathInformation);
	}
	
	//获取应用程序信息
	var software = new Array();
	var l = new Enumerator (service.ExecQuery("SELECT * FROM Win32_Product"));
    for (; !l.atEnd(); l.moveNext()) {
	    var p = l.item();
	    softWareInformation=p.Name+";"+p.Version;
	    software.push(softWareInformation);
    }
	return{
		macAddress:macAddress,ipAddress:ipAddress,equipmentModel:equipmentModel,equipmentTime:equipmentTime,CPU:CPU,RAM:RAM,memoryBank:memoryBank,hardDrive:hardDrive,hardDriveModel:hardDriveModel,networkCard:networkCard,motherBoard:motherBoard,osName:osName,osType:osType,osVersion:osVersion,osId:osId,graphicCard:graphicCard,audioCard:audioCard,software:software,path:path
	}
}
