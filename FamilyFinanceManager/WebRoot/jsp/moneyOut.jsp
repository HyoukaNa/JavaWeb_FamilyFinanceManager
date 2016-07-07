<%@ page import="java.util.*,com.team30.model.Cost" contentType="text/html" pageEncoding="UTF-8" %>
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
    <title>支出信息维护</title>
    <link rel="stylesheet" type="text/css" href="/FamilyFinanceManager/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/FamilyFinanceManager/jedate/skin/jedate.css"> 
    <link rel="stylesheet" type="text/css" href="/FamilyFinanceManager/css/common.css">
    <link rel="stylesheet" type="text/css" href="/FamilyFinanceManager/css/main.css">
    <link rel="stylesheet" type="text/css" href="/FamilyFinanceManager/css/money.css">
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
                        <li><a href="/FamilyFinanceManager/jsp/financial_system.html">证券账户管理</a></li>
                        <li><a href="/FamilyFinanceManager/jsp/stock_list.html">持股管理</a></li>
                        <li><a href="/FamilyFinanceManager/jsp/securitiesItem.html">证券流水帐管理</a></li>
                    </ul>
                </li>
                <li>
                    <span><a href="/FamilyFinanceManager/jsp/report.html"><img src="/FamilyFinanceManager/img/report.png"> 报 表 管 理</a></span>
                </li>
            </ul>
        </div>
        
        <div id=right>
                <nav>
                <span class="nav-first"><img src="/FamilyFinanceManager/img/home.png"><a href="/FamilyFinanceManager/jsp/main.jsp"> 主页</a></span>
                <span class="nav-second">> <a href="">支出信息维护</a></span>
                <span class="nav-third"></span>
                </nav>
                <ul class=third-level>
                    <li class="current" alt="outCome-list"><a href="javascript:;">支出列表</a></li>
                    <li class="" alt="modify"><a href="javascript:;">修改支出条目</a></li>
                    <li class="" alt="add"><a href="javascript:;">添加支出条目</a></li>
                    <li class="fix"></li>
                </ul>
                <div id="what">
                    <div name="outCome-list">
                        <br><br>
                        <div align="center">
                        <form id="form1" action="/FamilyFinanceManager/CostManager" method="post">
                        	<input name="service"  value="search" type="text" style="display:none;">
                            <input class="date" type="text" name="form1-date" id="form1-date" placeholder="按日期搜索" readonly="readonly">
                            <input type="text" name="form1-name" id="form1-name" placeholder="按姓名搜索" >
                            <input type="submit" value="搜索" class="submit-btn">
                        </form>
                            </div>
                        <table class="table table-striped">                          
                             <caption><h3 style="text-align: center;">收入明细</h3></caption>
                            <thread>
                                <tr>
                                    <th>记录编号</th>
                                    <th>支出类型</th>
                                    <th>支出者</th>
                                    <th>支出金额</th>
                                    <th>支出日期</th>
                                    <th>支出用途</th>
                                    <th>相关备注</th>
                                     <th>操作</th>
                                </tr>
                            </thread>
                            <%
                            ArrayList<Cost> lists = (ArrayList<Cost>) request.getAttribute("searchlists");
                            if(lists == null) {
                            	out.println("结果为空");
                            } else if (lists.size() == 0) {
                            	out.println("没有相应的记录");
                            } else  {
                            	int i = 0;
                            	for(i = 0;i < lists.size();i ++) {
                            	%><tbody ><tr id=" <%=lists.get(i).getCostId()%>">
                                	<td><%=lists.get(i).getCostId() %></td>
                                	<td><%=lists.get(i).getCostType() %></td>
                                	<td><%=lists.get(i).getCostOwner().getUserName() %></td>
                                	<td><%=lists.get(i).getCostCount() %></td>
                               		<td><%=lists.get(i).getCostDate() %></td>
                            		<td><%=lists.get(i).getCostSource() %></td>
                           			<td><%=lists.get(i).getCostDetails() %></td>
                               		<td><a class="modify" href="javascript:;">修改</a> 
                             		<a href="javascript:;" class="delete">删除</a></td>
                               		</tr></tbody><%
								}
                               }
							%>
                        </table>
                        </div>
                    <div name="modify">
                        <div align="center">
                        <form id="form3"  action="/FamilyFinanceManager/CostManager" method="post">
                            <fieldset>
                            <legend><h4>修改记录</h4></legend>
                            支出类型:<select name="type" id="form3-type">
                                                <option value="1" selected>税收</option>
                                                <option value="2">衣食住行</option>
                                                <option value="3">医疗</option>
                                                <option value="4">其他</option>
                                            </select><span style="color: red"> *</span><br><br>
                            记录编号:<input type="text" name="ID" id="form3-ID" placeholder="记录编号"></input><span style="color: red"> *</span><br><br>
                            支出人员:<input type="text" name="name" id="form3-name" placeholder="支出人员"></input><span style="color: red"> *</span><br><br>
                            支出金额:<input type="text" name="num" id="form3-num" placeholder="金额"></input><span style="color: red"> *</span><br><br>
                            支出日期:<input class="date" type="text" name="date" id="form3-date" placeholder="支出日期" readonly="readonly"><span style="color: red"> *</span><br><br>
                            支出用途:<input type="text" name="source" id="form3-source"  placeholder="支出用途"></input><span style="color: red"> *</span><br><br>
                            相关备注:<input type="text" name="remark" id="form3-remark" placeholder="相关备注"></input><span style="color: red"> *</span><br><br>
                                <p class="wrong-tip"></p>
                                <p class="right-tip"></p>
                                <input type="submit" value="提交" class="submit-btn" ></input>
                            </fieldset>
                        </form>
                            </div>
                    </div>
                    <div name="add">
                        <div align="center">
                    <form id="form2" action="/FamilyFinanceManager/CostManager" method="post">
                        <fieldset>
                            <legend><h4>添加记录</h4></legend>
                            支出类型:<select name="type" id="form2-type">
                                                <option value="1">税收</option>
                                                <option value="2">衣食住行</option>
                                                <option value="3">医疗</option>
                                                <option value="4">其他</option>
                                            </select><span style="color: red"> *</span><br><br>
                            支出人员:<input type="text" name="name" id="form2-name"  placeholder="支出人员"></input><span style="color: red"> *</span><br><br>
                            支出金额:<input type="text" name="num" id="form2-num" placeholder="金额"></input><span style="color: red"> *</span><br><br>
                            支出日期:<input class="date" type="text" name="date" id="form2-date" placeholder="支出日期" readonly="readonly"><span style="color: red"> *</span><br><br>
                            支出用途:<input type="text" name="source" id="form2-source" placeholder="支出用途"></input><span style="color: red"> *</span><br><br>
                            相关备注:<input type="text" name="remark" id="form2-remark"  placeholder="相关备注"></input><span style="color: red"> *</span><br><br>
                                <p class="wrong-tip"></p>
                                <p class="right-tip"></p>
                            <input type="submit" value="提交" class="submit-btn" ></input>
                            </fieldset>
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
    <script type="text/javascript" src="/FamilyFinanceManager/js/moneyOut.js"></script>
    <script type="text/javascript">
        jeDate({
            dateCell:".date",
            format:"YYYY-MM-DD",
            isTime:false, 
            minDate:"1975-00-00"
        })
    </script>
    </body>
 </html>