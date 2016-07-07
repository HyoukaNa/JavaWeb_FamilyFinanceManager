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
    <title>家庭理财系统 登录</title>
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
        <form action="/FamilyFinanceManager/Login" id="login-form" method="post" autocomplete="off">
            <input type="text" name="username" id="username" placeholder="请输入用户名" tabindex="2"><br>
            <input type="password" name="password" id="password" placeholder="请输入密码" tabindex="2"><br>
            <%--
            String rs = (String) request.getAttribute("result");
             --%>
            <p class="wrong-tip"></p>
            <%=(String) request.getAttribute("result") %>
            <input type="submit" value="登    录" id="submit">
            <br>
            <a href="/FamilyFinanceManager/jsp/register.jsp">新用户?  点此注册</a>
       </form>
    </div>
    <footer>
        <div>power by LZAHYY</div>
    </footer>
    <script type="text/javascript" src="/FamilyFinanceManager/js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="/FamilyFinanceManager/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $("#login-form").submit(function(){
            if(!$("#username").val()||!$("#password").val()){
                $("#login-form p.wrong-tip").text("用户名或密码不能为空");
                return false;
            }
        });
    </script>
</body>
</html>