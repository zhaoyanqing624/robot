
$(function(){
	getInformation();
});
function getInformation(){
	alert(1);
	var informationOfHardware = getInformationOfHardware();
	var macAddress = informationOfHardware.macAddress;
	var ipAddress = informationOfHardware.ipAddress;
	alert(macAddress);
	alert(ipAddress);
	$.ajax({
		type:"POST",
		url:"/org.xjtusicd3.partner/getInformation.html",
		data:{
			"macAddress":macAddress,
			"ipAddress":ipAddress
		},
		dataType:"json",
		success:function(data){
			
			alert("success");
		}
	})
}
//获取设备硬件信息
function getInformationOfHardware(){
	var locator = new ActiveXObject ("WbemScripting.SWbemLocator");  
	var service = locator.ConnectServer(".");  
	var a = new Enumerator (service.ExecQuery("SELECT * FROM Win32_NetworkAdapterConfiguration"));
    var macAddress;
    var ipAddress;
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
	return{
		macAddress:macAddress,ipAddress:ipAddress
	}
}
//获取设备软件信息
function getInformationOfSoftware(){
	var locator = new ActiveXObject ("WbemScripting.SWbemLocator");  
	var service = locator.ConnectServer(".");  
	var a = new Enumerator (service.ExecQuery("SELECT * FROM Win32_Product"));
    for (; !a.atEnd(); a.moveNext()) {
	    var p = a.item();
	    document.write(p.Name+p.Version);                          
    }
}