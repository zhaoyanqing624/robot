<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>小朵 | 登录/注册</title>
<link href="zhao/login_register/style.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">
<link rel='stylesheet prefetch' href='https://fonts.googleapis.com/icon?family=Material+Icons'>
<link href="zhao/login_register/style.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/validate.css">
<style type="text/css">
#myemail, .newemail, .newemailtitle,#myemail2, .newemail2, .newemailtitle2{ 
 cursor:default;
 line-height:18px;
}
</style>
</head>
<body>

<div id="container">
<div id="anitOut" class="anitOut" style="height:1054px;"> 
	<div class="cotn_principal">
  <div class="cont_centrar">
    <div class="cont_login">
      <div class="cont_info_log_sign_up">
        <div class="col_md_login">
          <div class="cont_ba_opcitiy">
            <h2>LOGIN</h2>
            <p>已有帐号，登录立即体验.</p>
            <button class="btn_login" onclick="cambiar_login()" >登录</button>
          </div>
        </div>
        <div class="col_md_sign_up">
          <div class="cont_ba_opcitiy">
            <h2>SIGN UP</h2>
            <p>还没有帐号？点击注册.</p>
            <button class="btn_sign_up" onclick="cambiar_sign_up()">注册</button>
          </div>
        </div>
      </div>
      <div class="cont_back_info">
        <div class="cont_img_back_grey"> <img src="zhao/login_register/po.jpg" alt="" /> </div>
      </div>
      <div class="cont_forms" >
        <div class="cont_img_back_"> <img src="zhao/login_register/po.jpg" alt="" /> </div>
        <div class="cont_form_login"> <a href="#" onclick="ocultar_login_sign_up()" ><i class="material-icons">&#xE5C4;</i></a>
          <h2>LOGIN</h2>
          <input type="text" placeholder="邮箱" id="me2"/>
          <div>hao</div>
          <input type="password" placeholder="密码" />
          <div>hao</div>
          <button class="btn_login" onclick="cambiar_login()">登录</button>
        </div>
        <div class="cont_form_sign_up"> <a href="#" onclick="ocultar_login_sign_up()"><i class="material-icons">&#xE5C4;</i></a>
          <h2>SIGN UP</h2>
          <input type="text" class="inputElem" id="me" placeholder="邮箱" />
          <div><a class="validate_faqadd spa1"></a></div>
          <input type="text" id="user" placeholder="用户名" />
          <div><a class="validate_faqadd spa2"></a></div>
          <input type="password" id="password" placeholder="密码" />
          <div><a class="validate_faqadd spa3"></a></div>
          <input type="password" id="repassword" placeholder="再次输入密码" />
          <div><a class="validate_faqadd spa4"></a></div>
          <button class="btn_sign_up" onclick="cambiar_sign_up()">注册</button>
        </div>
      </div>
    </div>
  </div>
</div>
</div>
</div>

<script src="js/jquery-1.6.2.js"></script>
<script src="zhao/login_register/login_emaiautocomplete/emailAutoComplete.js"></script>
<script src="zhao/login_register/login_emaiautocomplete/emailAutoComplete2.js"></script>
<script src="js/login_bg/cav.js"></script>
<script src="js/login_bg/getStart.js"></script>
<script src="zhao/login_register/index.js"></script>

</body>
</html>