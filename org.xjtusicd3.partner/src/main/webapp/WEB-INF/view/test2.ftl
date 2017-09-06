<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript">
//系统信息获取 
function getSysInfo(){
	var zhao;
  var locator = new ActiveXObject ("WbemScripting.SWbemLocator");  
  var service = locator.ConnectServer(".");  
  //CPU信息 
  var cpu = new Enumerator (service.ExecQuery("SELECT * FROM Win32_Processor")).item();  
  var cpuType=cpu.Name,hostName=cpu.SystemName; 
  //内存信息 
  var memory = new Enumerator (service.ExecQuery("SELECT * FROM Win32_PhysicalMemory")); 
  for (var mem=[],i=0;!memory.atEnd();memory.moveNext()) mem[i++]={cap:memory.item().Capacity/1024/1024,speed:memory.item().Speed} 
  //系统信息 
  var system=new Enumerator (service.ExecQuery("SELECT * FROM Win32_ComputerSystem")).item(); 
  var physicMenCap=Math.ceil(system.TotalPhysicalMemory/1024/1024),curUser=system.UserName,cpuCount=system.NumberOfProcessors 
  //获取Mac地址
  var e =new Enumerator (service.ExecQuery("SELECT * FROM Win32_NetworkAdapterConfiguration"));
  for (; !e.atEnd(); e.moveNext()) {
      var p = e.item();
      if (p.IPAddress==null) {
         continue;
      }else{
    	  if(p.Description.substring(0,8)=="Intel(R)"){
              document.write(p.IPAddress(0));
              document.write("<br/>");  
              document.write(p.MACAddress);
              document.write("<br/>");
              zhao = p.MACAddress;
    	  }
      }
  }
  alert(zhao);
  return {cpuType:cpuType,cpuCount:cpuCount,hostName:hostName,curUser:curUser,memCap:physicMenCap,mem:mem} 
}
var a = getSysInfo();
//alert(a.macaddress);
function Button4_onclick() {//主板信息
    var locator = new ActiveXObject ("WbemScripting.SWbemLocator");
    var service = locator.ConnectServer(".");
    var properties = service.ExecQuery("SELECT * FROM Win32_BaseBoard");
    var e = new Enumerator (properties);
    document.write("<table border=1>");
    for (;!e.atEnd();e.moveNext ())
    {
          var p = e.item ();
          document.write("<tr>");
          document.write("<td>" + p.HostingBoard + "</td>");
          document.write("<td>" + p.Manufacturer + "</td>");
          document.write("<td>" + p.PoweredOn + "</td>");
          document.write("<td>" + p.Product + "</td>");
          document.write("<td>" + p.SerialNumber + "</td>");
          document.write("<td>" + p.Version + "</td>");
          document.write("</tr>");
    }
    document.write("</table>");
}
Button4_onclick();
function button1_onclick() {//cpu 信息
    var locator = new ActiveXObject ("WbemScripting.SWbemLocator");
    var service = locator.ConnectServer(".");
    var properties = service.ExecQuery("SELECT * FROM Win32_Processor");
    var e = new Enumerator (properties);
    document.write("<table border=1>");
    for (;!e.atEnd();e.moveNext ())
    {
          var p = e.item ();
          document.write("<tr>");
          document.write("<td>" + p.Caption + "</td>");
          document.write("<td>" + p.DeviceID + "</td>");
          document.write("<td>" + p.Name + "</td>");
          document.write("<td>" + p.CpuStatus + "</td>");
          document.write("<td>" + p.Availability + "</td>");
          document.write("<td>" + p.Level + "</td>");
          document.write("<td>" + p.ProcessorID + "</td>");
          document.write("<td>" + p.SystemName + "</td>");
          document.write("<td>" + p.ProcessorType + "</td>");
          document.write("</tr>");
    }
    document.write("</table>");
}
button1_onclick();
</script>
</head>
<body>
<div id="elInfo" >
<a>zhaoyq</a>
</div>
</body>
</html>