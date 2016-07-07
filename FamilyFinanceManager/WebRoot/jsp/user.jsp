<%@ page import="java.util.*,com.team30.model.User" contentType="text/html" pageEncoding="UTF-8" %>
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
    <title>用户管理</title>
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
                <span class="nav-second">> <a href="">用户管理</a></span>
                <span class="nav-third"></span>
                </nav>
                <ul class=third-level>
                    <li class="current" alt="user-list"><a href="javascript:;">用户列表</a></li>
                    <li class="" alt="modify"><a href="javascript:;">修改用户</a></li>
                    <li class="" alt="add"><a href="javascript:;">添加用户</a></li>
                    <li class="fix"></li>
                </ul>
                <div id="what">
                    <div name="user-list">
                    <br><br>
                    <form id="form1" action="/FamilyFinanceManager/UserServlet" method="post">
                        <div align="center">
                        	<input name="service"  value="search" type="text" style="display:none;">
                            <input type="text" name="name" id="form1-name" placeholder="按姓名搜索">
                            <input type="text" name="id"    id="form1-ID" placeholder="身份证号">
                            <input type="submit" value="搜索" class="submit-btn">
                        </div>
                        <table class="table table-striped">
                             <caption><h3 style="text-align: center;">用户名单</h3></caption>
                            <tbody>
                                <tr>
                                    <th>姓名</th>
                                    <th>密码</th>
                                    <th>银行帐号</th>
                                    <th>工资</th>
                                    <th>家庭称呼</th>
                                    <th>年龄</th>
                                    <th>身份证号</th>
                                     <th>操作</th>
                                </tr>
                            </tbody>
                             <%
                            ArrayList<User> lists = (ArrayList<User>) request.getAttribute("searchlists");
                            if(lists == null) {
                            	out.println("结果为空");
                            } else if (lists.size() == 0) {
                            	out.println("没有相应的记录");
                            } else  {
                            	int i = 0;
                            	for(i = 0;i < lists.size();i ++) {
                            	%><tbody ><tr id=" <%=lists.get(i).getIdCard()%>">
                                	<td><%=lists.get(i).getUserName() %></td>
                                	<td><%=lists.get(i).getPassword() %></td>
                                	<td><%=lists.get(i).getCreditCard() %></td>
                                	<td><%=lists.get(i).getSalary() %></td>
                            		<td><%=lists.get(i).getFamilyCall() %></td>
                           			<td><%=lists.get(i).getAge() %></td>
                           			<td><%=lists.get(i).getIdCard() %></td>
                               		<td><a class="modify" href="javascript:;">修改</a> 
                             		<a href="javascript:;" class="delete">删除</a></td>
                               		</tr></tbody><%
								}
                               }
							%>
							</table>
                    	</form>
                    </div>
                    <div name="modify">
                        <div align="center">
                        <form id="form3"  action="/FamilyFinanceManager/UserServlet" method="get">
                            <fieldset>
                            <legend><h4>修改用户</h4></legend>
                            用户姓名:<input type="text" name="form3-name" placeholder="用户姓名"><span style="color: red"> *</span><br><br>
                            用户密码:<input type="text" name="form3-password" placeholder="密码"><span style="color: red"> *</span><br><br>
                            银行帐号:<input type="text" name="form3-account" placeholder=" 银行帐号"><span style="color: red"> *</span><br><br>
                            用户工资:<input type="text" name="form3-wage" placeholder="月薪"><span style="color: red"> *</span><br><br>
                            家庭称呼:<input type="text" name="form3-call" placeholder="家庭称呼"><span style="color: red"> *</span><br><br>
                            用户年龄:<input type="text" name="form3-age"  placeholder="年龄"><span style="color: red"> *</span><br><br>
                            身份证号:<input type="text" name="form3-ID" placeholder="身份证号"><span style="color: red"> *</span><br><br>
                               <%
                                String rs = (String) request.getAttribute("resultOfModify");
                                if( rs != null && rs.equals("success") ){
                                %>
                                	<p class="right-tip">更新成功！</p>
                                <% 
                                } else if ( rs != null){
                                 %>
                                 	<p class="wrong-tip"><%=rs %></p>
                                 <%
                                } else {
                                }
                                 %>
                                <input type="submit" value="提交" class="submit-btn" >
                            </fieldset>
                        </form>
                            </div>
                    </div>
                    <div name="add">
                        <div align="center">
                        <form id="form2"  action="" method="get">
                            <fieldset>
                            <legend><h4>添加用户</h4></legend>
                            用户姓名:<input type="text" id="form2-name" placeholder="用户姓名"><span style="color: red"> *</span><br><br>
                            用户密码:<input type="text" id="form2-password" placeholder="密码"><span style="color: red"> *</span><br><br>
                            银行帐号:<input type="text" id="form2-account" placeholder=" 银行帐号"><span style="color: red"> *</span><br><br>
                            用户工资:<input type="text" id="form2-wage" placeholder="月薪"><span style="color: red"> *</span><br><br>
                            家庭称呼:<input type="text" id="form2-call" placeholder="家庭称呼"><span style="color: red"> *</span><br><br>
                            用户年龄:<input type="text" id="form2-age"  placeholder="年龄"><span style="color: red"> *</span><br><br>
                            身份证号:<input type="text" id="form2-ID" placeholder="身份证号"><span style="color: red"> *</span><br><br>
                                <p class="wrong-tip"></p>
                                <p class="right-tip"></p>
                            <input type="submit" value="提交" class="submit-btn" >
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
    <script type="text/javascript" src="/FamilyFinanceManager/js/user.js"></script>
    <script type="text/javascript">
        jeDate({
            dateCell:"#form1-date",
            format:"YYYY-MM-DD",
            isTime:false, 
            minDate:"1975-00-00"
        })
        $("#manager_form").submit(function () {
            var inputList=$("#manager_form input")
            for(var i=0;i<inputList.length;i++){
                if(!inputList.eq(i).val()){
                    alert("有必填项为空");
                    return false;
                }
            }
        })
        $("form").submit(function () {
            var inputList=$(this).find("input")
            for(var i=0;i<inputList.length;i++){
                if(!inputList.eq(i).val()){
                    alert("有必填项为空");
                    return false;
                }
            }
        })
    </script>
    </body>
    </html>