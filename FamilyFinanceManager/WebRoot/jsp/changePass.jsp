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
    <title>个人信息管理</title>
    <link rel="stylesheet" type="text/css" href="/FamilyFinanceManager/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/FamilyFinanceManager/jedate/skin/jedate.css"> 
    <link rel="stylesheet" type="text/css" href="/FamilyFinanceManager/css/common.css">
    <link rel="stylesheet" type="text/css" href="/FamilyFinanceManager/css/main.css">
    <link rel="stylesheet" type="text/css" href="/FamilyFinanceManager/css/user.css">
    <!--[if lte IE 9]>
    <script src="http://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="../js/selectivizr-master/selectivizr.js"></script>
    <![endif]-->
</head>
<body>
    <header>
<!--         <div id="logo"><img src="../img/logo.png" alt=""></div>
 -->        <div id="system-title">家庭理财系统</div>
        <div id="sign-out"><a href="/FamilyFinanceManager/jsp/login.jsp"><img src="/FamilyFinanceManager/img/sign_out.png"> 注销</a></div>
    </header>
    <div id=center>
        <div id="content">
        <div id="left">
            <ul class="one-level">
                <li>
                    <span><img src="/FamilyFinanceManager/img/person.png"> 用 户 管 理</span>
                    <ul class="tow-level">
                        <li><a href="./userManager.jsp">用户个人信息管理</a></li>
                        <li><a href="./user.jsp">用户管理</a></li>
                    </ul>
                </li>
                <li>
                    <span><img src="/FamilyFinanceManager/img/money.png"> 收 支 管 理</span>
                    <ul class="tow-level">
                            <li><a href="./moneyIn.jsp">收入信息维护</a></li>
                            <li><a href="./moneyOut.jsp">支出信息维护</a></li>
                    </ul>
                </li>
                <li>
                    <span><img src="/FamilyFinanceManager/img/finance.png"> 财 务 管 理</span>
                    <ul class="tow-level">
                        <li><a href="./financial_system.jsp">证券账户管理</a></li>
                        <li><a href="./stock_list.jsp">持股管理</a></li>
                        <li><a href="./securitiesItem.jsp">证券流水帐管理</a></li>
                    </ul>
                </li>
                <li>
                    <span><a href="./report.jsp"><img src="/FamilyFinanceManager/img/report.png"> 报 表 管 理</a></span>
                </li>
            </ul>
        </div>
        <div id=right>
                <nav>
                <span class="nav-first"><img src="/FamilyFinanceManager/img/home.png"><a href="/FamilyFinanceManager/jsp/main.jsp"> 主页</a></span>
                <span class="nav-second">> <a href="">个人信息管理</a></span>
                <span class="nav-third"></span>
                </nav>
                <ul class=third-level>
                    <li class=""><a href="/FamilyFinanceManager/jsp/userManager.jsp">用户信息修改</a></li>
                    <li class="current" alt="changePass"><a href="">密码修改</a></li>
                    <li class="fix"></li>
                </ul>
                <div id="what">
                    <div name="changePass">
                    <br><br>               
                    <form action="/FamilyFinanceManager/ChangePass" method="post" id="form-change-pass">
                        <div align="center">
                            新的密码:<input type="password" name="newPassword" ><span style="color: red;"> *</span><br><br>
                        </div>
                        <div align="center">
                            确认密码:<input type="password" name="rePassword" ><span style="color: red;"> *</span><br><br>
                        </div>
                        <div align="center">                     
                        	<%
                        		String rs = (String) request.getAttribute("resultOfChangePass");
                        		if ( rs != null && rs.equals("success") ) {
                        			%>
                        			<p class="right-tip"> 修改成功 </p>
                        			<%
                        		} else if ( rs != null && rs.equals("fail") ){
                        		
                        			%>
                        			<p class="wrong-tip"> 修改失败 </p>
                        			<%
                        		}  else {
                  					%>
                        			<p>  </p>
                        			<%
                        		}
                        	 %>
                            <input type="submit" name="submit" value="修改密码" class="submit-btn">
                        </div>
                        
                    </form>

                </div>
                </div>
        </div>
        </div>
    </div>
    <br><br>
    <footer>
        <div>power by LZAHYY</div>
    </footer>

    <script type="text/javascript" src="/FamilyFinanceManager/js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="/FamilyFinanceManager/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/FamilyFinanceManager/jedate/jedate.js"></script>
    <script type="text/javascript" src="/FamilyFinanceManager/js/main.js"></script>
    <script type="text/javascript" src="/FamilyFinanceManager/js/money.js"></script>
    <script type="text/javascript">
        jeDate({
            dateCell:"#form1-date",
            format:"YYYY-MM-DD",
            isTime:false, 
            minDate:"1975-00-00"
        })
    </script>
    </body>
    </html>