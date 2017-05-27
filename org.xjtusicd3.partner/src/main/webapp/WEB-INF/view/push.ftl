
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<script type="text/javascript" src="ajax-pushlet-client.js"></script>         
        <script type="text/javascript">  
            PL._init();   
            PL.joinListen('/cuige/he');  
            function onData(event) {   
                alert(event.get("hw"));   
                // 离开  
                // PL.leave();  
            }  
        </script>  <h1>  
            my first pushlet!  
        </h1>  
        </center> 
</body>
</html>
