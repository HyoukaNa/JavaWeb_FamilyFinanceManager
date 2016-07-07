<%@ page import="java.util.*" contentType="text/html" pageEncoding="UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="/FamilyFinanceManager/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/FamilyFinanceManager/css/common.css">
    <!--[if lte IE 9]>
    <script src="http://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="../js/selectivizr-master/selectivizr.js"></script>
    <![endif]-->
</head>
<body>
    <header>
<!--         <div id="logo"><img src="../img/logo.png" alt=""></div>
 -->        <div id="system-title">家庭理财系统</div>
    </header>
    <div id=center>
        <form id="register-form" action="userManager.jsp" method="get" autocomplete="off">
             <input type="text" id="username" placeholder="请输入用户名" tabindex="2"></input><br>
             <input type="password" id="password" placeholder="请输入密码" tabindex="2"></input><br>
             <input type="password" id="password-again" placeholder="再次输入密码" tabindex="2"></input><br> 
             <input type="text" id="userID" placeholder="身份证号" tabindex="2">
            <p class="wrong-tip"></p>
            <input type="submit" value="注 册" id="submit"></input>
        </form>
    </div>
    <footer>
        <div>power by LZAHYY</div>
    </footer>
    <script type="text/javascript" src="/FamilyFinanceManager/js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="/FamilyFinanceManager/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $("#password-again,#password").blur(function(){
             if($("#password").val()!=$("#password-again").val()){
                $("#register-form p.wrong-tip").text("两次密码输入不同");
            }else{
                $("#register-form p.wrong-tip").text("");
            }
        });
        $("#register-form").submit(function(event){
            if(!$("#username").val()||!$("#userID").val()){
               $("#register-form p.wrong-tip").text("必填项不能为空");
                return false 
            }
            if($("#password").val()!=$("#password-again").val()){
                $("#register-form p.wrong-tip").text("两次密码输入不同");
                return false;
            }
        });
    </script>
</body>
</html>