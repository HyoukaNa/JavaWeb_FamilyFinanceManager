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
    <title>报表管理</title>
    <link rel="stylesheet" type="text/css" href="/FamilyFinanceManager/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/FamilyFinanceManager/jedate/skin/jedate.css"> 
    <link rel="stylesheet" type="text/css" href="/FamilyFinanceManager/css/common.css">
    <link rel="stylesheet" type="text/css" href="/FamilyFinanceManager/css/main.css">
    <!--[if lte IE 9]>
    <script src="http://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="../js/selectivizr-master/selectivizr.js"></script>
    <![endif]-->
    <style >
        .datainp{ width:15%;height: 30px;border: 1px #ccc solid;}
        .datep{ margin-bottom: 40px; }
        .lab{width: 15%; }
        .button{margin-right: 0px}
        input.submit-btn{
            width: 80px;;
            height: 2em;
            border: 1px solid lightgrey;
            background-color: cadetblue;
            border-radius: 0.2em;
            transition: all 0.2s linear;
            color: white;
        }
        input.submit-btn:hover{
            background-color: steelblue;
        }
    </style>
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
                        <li><a href="/FamilyFinanceManager/jsp/userManager.jsp">用户个人信息管理</a></li>
                        <li><a href="/FamilyFinanceManager/jsp/user.jsp">用户管理</a></li>
                    </ul>
                </li>
                <li>
                    <span><img src="/FamilyFinanceManager/img/money.png"> 收 支 管 理</span>
                    <ul class="tow-level">
                            <li><a href="/FamilyFinanceManager/jsp/moneyIn.jsp">收入信息维护</a></li>
                            <li><a href="/FamilyFinanceManager/jsp/moneyOut.jsp">支出信息维护</a></li>
                    </ul>
                </li>
                <li>
                    <span><img src="/FamilyFinanceManager/img/finance.png"> 财 务 管 理</span>
                    <ul class="tow-level">
                        <li><a href="/FamilyFinanceManager/jsp/financial_system.jsp">证券账户管理</a></li>
                        <li><a href="/FamilyFinanceManager/jsp/stock_list.jsp">持股管理</a></li>
                        <li><a href="/FamilyFinanceManager/jsp/securitiesItem.jsp">证券流水帐管理</a></li>
                    </ul>
                </li>
                <li>
                    <span><a href="/FamilyFinanceManager/jsp/report.jsp"><img src="/FamilyFinanceManager/img/report.png"> 报 表 管 理</a></span>
                </li>
            </ul>
        </div>
        
        <div id=right>
                <nav>
                <span class="nav-first"><img src="/FamilyFinanceManager/img/home.png"><a href="/FamilyFinanceManager/jsp/main.jsp"> 主页</a></span>
                <span class="nav-second">> <a href="">报表管理</a></span>
                <span class="nav-third"></span>
                </nav>
                <ul class=third-level>
                    <li class="current" alt="report"><a href="javascript:;">报表管理</a></li>
                    <li class="fix"></li>
                </ul>
                <div id="what">
                    <div name="report">
                        <div id="reportForm-header">
                            <h2 align="center">报表管理</h2>
                        </div>
                        <br>
                        <div align="center">
                        <form id="reportForm-chooseDate" >
                            <label class="lab" >查询月份</label>
                            <input class="datainp" id="indate_1" type="text" value="" placeholder="选择时间" readonly >


                            <input  type="radio" name="query_search" value="query_in" >收入查询
                            <input  type="radio" name="query_search" value="query_out" >支出查询
                            <input  type="radio" name="query_search" value="query_inout" checked="checked"> 收支查询
                            <input class="submit-btn" type="submit" value="查询" >
                        </form>
                            </div>
                        <br>
                        <div id="reportForm-table">
                            <table class="table table-striped">
                                <thread>
                                    <tr>
                                        <th>收支类型</th>
                                        <th>收入来源\支出去向</th>
                                        <th>收支者</th>
                                        <th>收支时间</th>
                                        <th>收支金额</th>
                                        <th>备注</th>
                                    </tr>
                                </thread>

                                <tbody>
                                    <tr>
                                        <td>工资收入</td>
                                        <td>华科</td>
                                        <td>小明</td>
                                        <td>2016-1-6</td>
                                        <td>+7000</td>
                                        <td>无</td>
                                    </tr>
                                </tbody>

                                <tbody>
                                    <tr>
                                        <td>其他</td>
                                        <td>旅游</td>
                                        <td>小花</td>
                                        <td>2016-1-7</td>
                                        <td>-3000</td>
                                        <td>无</td>
                                    </tr>
                                </tbody>

                                <tbody>
                                    <tr>
                                        <td colspan="6" align="right">总收支:+4000</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>

                        <div align="center">
                            <input type="submit" value="打印" class="submit-btn">
                        </div>
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
    <script type="text/javascript">
        jeDate({
            dateCell:"#indate_1",
            format:"YYYY-MM ",
            isTime:true,
            minDate:"2016-1-1 00:00:00"
        })
        </script>
    </body>
    </html>