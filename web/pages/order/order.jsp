<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
	<%@include file="/pages/common/header.jsp"%><style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">我的订单</span>
			<%@ include file="/pages/common/login_success_user.jsp"%>
	</div>
	
	<div id="main">
		
		<table>

			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
			</tr>
			<c:if test="${not empty requestScope.myOrders}">
				<c:forEach items="${requestScope.myOrders}" var="order">
					<tr>
						<td>${order.create_time}</td>
						<td>${order.price}</td>
						<c:choose>
							<c:when test="${order.status==0}">
								<td>订单准备中</td>
							</c:when>
							<c:when test="${order.status==1}">
								<td>订单已发送</td>
							</c:when>
							<c:when test="${order.status==2}">
								<td>订单已签收</td>
							</c:when>
						</c:choose>
						<td><a href="orderServlet?action=queryOrderDetails&orderid=${order.order_id}">查看详情</a></td>
					</tr>
				</c:forEach>
			</c:if>

		</table>
		
	
	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>