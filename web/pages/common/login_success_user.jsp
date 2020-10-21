<%--
  Created by IntelliJ IDEA.
  User: Yhurri
  Date: 2020/5/13
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<base href="http://10.220.89.160:8080/book/">
<body>


<div>
    <span>欢迎<span class="um_span">${sessionScope.username}</span>光临尚硅谷书城</span>
    <a href="orderServlet?action=queryMyOrders">我的订单</a>
    <a href="user?action=logout">注销</a>&nbsp;&nbsp;
    <a href="index.jsp">返回</a>
</div>

</body>
</html>
