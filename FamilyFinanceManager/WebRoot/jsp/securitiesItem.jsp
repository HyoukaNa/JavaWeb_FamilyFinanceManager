<%@ page language="java" import="java.util.*,com.team30.model.*" pageEncoding="UTF-8"%>
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
    <title>证券流水帐管理</title>
    <link rel="stylesheet" type="text/css" href="/FamilyFinanceManager/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/FamilyFinanceManager/jedate/skin/jedate.css">
    <link rel="stylesheet" type="text/css" href="/FamilyFinanceManager/css/common.css">
    <link rel="stylesheet" type="text/css" href="/FamilyFinanceManager/css/main.css">
    <link rel="stylesheet" type="text/css" href="/FamilyFinanceManager/css/financial.css">

    <!--[if lte IE 9]>
    <script src="http://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="../js/selectivizr-master/selectivizr.js"></script>
    <![endif]-->
</head>
<body>
    <header>
<!--         <div id="logo"><img src="../img/logo.png" alt=""></div>
 -->        <div id="system-title">家庭理财系统</div>
        <div id="sign-out"><a href="login.html"><img src="/FamilyFinanceManager/img/sign_out.png"> 注销</a></div>
    </header>
    <div id="center">
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
                            <li><a href="/FamilyFinanceManager/IncomeManager">收入信息维护</a></li>
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
                <span class="nav-second">> <a href="">证券流水帐管理</a></span>
                <span class="nav-third"></span>
            </nav>
                <ul class=third-level>
                    <li class="current" alt="tradelists-manager"><a href="javascript:;">证券流水账查询</a></li>
                    <li class="" alt="updateaction"><a href="javascript:;">修改证券流水账</a></li>
                    <li class="" alt="createaction"><a href="javascript:;">新建证券流水账</a></li>
                    <li class="fix"></li>
                </ul>
                <div id="what">
                    <div name="tradelists-manager">
                        <h3 style="text-align: center;">证券流水账信息查询</h3>
                            <div align="center">
                            <form id="search-form" action="/FamilyFinanceManager/ItemManager" method="post">
                            <input name="service"  value="query" type="text" style="display:none;">
                                证券账户:<input type="text" id="secuser" placeholder="证券账户名"  name="secuser"><span style="color: red"> *</span><br><br>
                                开始时间:<input class="datainp" id="startline" type="text" placeholder="开始时间" readonly name="startDate"><span style="color: red"> *</span><br><br>
                                截止时间:<input class="datainp" id="endline" type="text" placeholder="截止时间" readonly name="endDate"><span style="color: red"> *</span><br><br>
                                交易类型:<input type="radio" name="type" value="sell" checked="checked"> 卖出
                                        <input type="radio" name="type" value="buy"> 购入<br>

                                <input type="submit" id="search" class="submit-btn"value="查询"><a href="javascript:;" id="reset-btn">重置</a>
                            </form>
                                </div>
                            <table align="center" class="table table-striped">
                            <caption><h3 style="text-align: center;">交易明细</h3></caption>
                            <thread>
                                <tr>

                                    <th>交易单号</th>
                                    <th>证券账户</th>
                                    <th>股票名称</th>
                                    <th>交易类型</th>
                                    <th>股票价格</th>
                                    <th>交易股数</th>
                                    <th>交易时间</th>
                                    <th>记录操作</th>
                                </tr>
                            </thread>

                            <%
                                    List<SecuritiesItem> securitiesList = null;
                                    securitiesList = (ArrayList<SecuritiesItem>) request.getAttribute("securitiesItemList");
                                    if(securitiesList == null||securitiesList.size() == 0) {
                                     } else {
                                        for(int i=0;i<securitiesList.size();i++){
                                            SecuritiesItem securitiesItem = (SecuritiesItem)securitiesList.get(i);
                                            %>

                            <tbody>
                            <tr id="<%=securitiesItem.getItemId()%>">
                                <td><%=securitiesItem.getItemId()%></td>
                                <td><%=securitiesItem.getItemOwner().getSecuritiesUserId()%></td>
                                <td><%=securitiesItem.getItemStockName()%></td>
                                <td><%=securitiesItem.getItemTradeTypeString()%></td>
                                <td><%=securitiesItem.getItemStockPrice()%></td>
                                <td><%=securitiesItem.getItemStockCount()%></td>
                                <td><%=securitiesItem.getItemTradeDate()%></td>
                                <td>
                                    <a href="javascript:;" class="modify">修改</a>
                                    <a href="javascript:;" class="delete" >删除</a>
                                </td>
                            </tr>
                            <tr id="resultOfSearch"></tr>
                            </tbody>
                            <% } }%>

                        </table>
                    </div>
                    <div name="updateaction">
                        <caption><h3 style="text-align: center;">修改证券流水账</h3></caption>
                        <div align="center">
                        <form id="modify-form" action="/FamilyFinanceManager/SecuritiesItemManager" method="post">
                                     <input name="service"  value="modify" type="text" style="display:none;">
                                  
                                    交易单号:<input type="text" name="modify_ID" placeholder="交易单号" value=""><span style="color: red"> *</span><br><br>
                                    证券账户:<input type="text" name="modify_account" placeholder="证券账户" value=""><span style="color: red"> *</span><br><br>
                                    股票名称:<input type="text" name="modify_stock" placeholder="股票名称" value=""><span style="color: red"> *</span><br><br>
                                    股票价格:<input type="text" name="modify_price" placeholder="股价" value=""><span style="color: red"> *</span><br><br>
                                    交易股数:<input type="text" name="modify_count" placeholder="交易量" value=""><span style="color: red"> *</span><br><br>
                                    交易时间:<input type="text" name="modify_date" class="datainp" id="tradetime1" placeholder="选择时间" readonly="readonly" value=""><span style="color: red"> *</span><br><br>
                                    交易类型:<input type="radio" name="modify_type" value="卖出" checked="checked"> 卖出
                                         <input type="radio" name="add_type" value="购入"> 购入<br><br>
                                    <p class="wrong-tip"></p>
                                    <p class="right-tip"></p>
                                    <input type="submit" class="submit-btn" value="确认修改">
                        </form>
                            </div>
                    </div>
                    <div name="createaction">
                        <caption><h3 style="text-align: center;">新建证券流水账</h3></caption>
                        <div align="center">
                        <form id="add-form" action="/FamilyFinanceManager/SecuritiesItemManager" method="post">
                         <input name="service"  value="add" type="text" style="display:none;">
                             交易单号:<input type="text" name="add_ID" placeholder="交易单号" ><span style="color: red"> *</span><br><br>
                            证券账户:<input type="text" name="add_account" placeholder="证券账户"><span style="color: red"> *</span><br><br>
                            股票名称:<input type="text" name="add_stock" placeholder="股票名称"><span style="color: red"> *</span><br><br>
                            股票价格:<input type="text" name="add_price" placeholder="股价"><span style="color: red"> *</span><br><br>
                            交易股数:<input type="text" name="add_count" placeholder="交易量"><span style="color: red"> *</span><br><br>
                            交易时间:<input type="text" name="add_date" class="datainp" id="tradetime2" placeholder="选择时间"><span style="color: red"> *</span><br><br>
                            交易类型:<input type="radio" name="add_type" value="sell" checked="checked"> 卖出
                                    <input type="radio" name="add_type" value="buy"> 购入<br><br>
                                    <p class="wrong-tip"></p>
                                    <p class="right-tip"></p>
                                    <input type="submit" class="submit-btn" value="确认提交">
                        </form>
                            </div>
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
    <script type="text/javascript" src="/FamilyFinanceManager/js/securitiesItem.js"></script>
    <script type="text/javascript">
        jeDate({
            dateCell:"#startline",
            format:"YYYY-MM-DD hh:mm",
            //isinitVal:true,
            isTime:true,
            minDate:"2016-01-01 00:00",
            maxDate:"2049-12-31 00:00"
        });
        jeDate({
            dateCell:"#endline",
            format:"YYYY-MM-DD hh:mm",
            //isinitVal:true,
            // isTime:true,
            minDate:"2016-01-01 00:00",
            maxDate:"2049-12-31 00:00"
        });
        jeDate({
            dateCell:"#tradetime1",
            format:"YYYY-MM-DD hh:mm",
            //isinitVal:true,
            isTime:true,
            minDate:"2016-01-01 00:00",
            maxDate:"2049-12-31 00:00"
        });
         jeDate({
            dateCell:"#tradetime2",
            format:"YYYY-MM-DD hh:mm",
            //isinitVal:true,
            isTime:true,
            minDate:"2016-01-01 00:00",
            maxDate:"2049-12-31 00:00"
        })
    </script>

    </body>
    </html>