<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>

	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@ include file="/pages/common/header.jsp"%>
	<script>
		$(function () {
			$("#submit").click(function () {
				var val = $("#pn_input").val();
				location.href="http://10.220.89.160:8080/book/${requestScope.page.url}&pageNo="+val;
			});

			$("#s_btn").click(function () {
				var min = $("#min").val();
				var max = $("#max").val();

				if (min.length==0 || max.length ==0){
					alert("区间不能为空");
					return false;
				}
			});

			$("button.addToCart").click(function () {
				var itemid = $(this).attr("itemid");
				// $.getJSON("http://10.220.89.160:8080/book/cartServlet","action=addItem&id="+itemid,function (data) {
				// 	console.log(data);
				// 	$("span.showName").text("您刚刚将"+data.lastName+"加入了购物车");
				// 	$("#total").text("您的购物车中有"+data.total+"件商品");
				// })
				location.href = "http://10.220.89.160:8080/book/cartServlet?action=addItem&id="+itemid;

			})


		})
	</script>

</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">网上书城</span>
			<div>
				<c:if test="${empty sessionScope.username}">

					<a href="pages/user/login.jsp">登录</a>
					<a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
					<a href="pages/cart/cart.jsp">购物车</a>
					<a href="pages/manager/manager.jsp">后台管理</a>
				</c:if>
				<c:if test="${not empty sessionScope.username}">
					<span>欢迎<span class="um_span">${sessionScope.username}</span>光临尚硅谷书城</span>
					<a href="pages/cart/cart.jsp">购物车</a>
					<a href="pages/manager/manager.jsp">后台管理</a>
					<a href="user?action=logout">注销</a>
				</c:if>
			</div>
	</div>

	<div id="main">
		<div id="book">
			<div class="book_cond">
				<form action="client/bookServlet" method="get">
					<input type="hidden" name="action" value="pagePrice">
					价格：<input id="min" type="text" name="min" value="${requestScope.min}"> 元 -
						<input id="max" type="text" name="max" value="${requestScope.max}"> 元
						<input type="submit" value="查询" id="s_btn" />
				</form>
			</div>
			<div style="text-align: center">
				<c:if test="${not empty sessionScope.cart.items}">
					<span id="total">您的购物车中有${sessionScope.cart.itemsTotal}件商品</span>
					<div >
						<span style="color: red" class="showName">您刚刚将${sessionScope.lastItem.name}加入了购物车</span>
					</div>
				</c:if>
				<c:if test="${empty sessionScope.cart.items}">
					<span id="total"> </span>
					<div>
						<span style="color: red" class="showName">当前购物车为空</span>
					</div>
				</c:if>

			</div>

			<c:forEach items="${requestScope.page.items}" var="book">
				<div class="b_list">
					<div class="img_div">
						<img class="book_img" alt="" src="static/img/default.jpg" />
					</div>
					<div class="book_info">
						<div class="book_name">
							<span class="sp1">书名:</span>
							<span class="sp2">${book.name}</span>
						</div>
						<div class="book_author">
							<span class="sp1">作者:</span>
							<span class="sp2">${book.author}</span>
						</div>
						<div class="book_price">
							<span class="sp1">价格:</span>
							<span class="sp2">￥${book.price}</span>
						</div>
						<div class="book_sales">
							<span class="sp1">销量:</span>
							<span class="sp2">${book.sales}</span>
						</div>
						<div class="book_amount">
							<span class="sp1">库存:</span>
							<span class="sp2">${book.stock}</span>
						</div>
						<div class="book_add">
							<button class="addToCart" itemid="${book.id}">加入购物车</button>
						</div>
					</div>
				</div>
			</c:forEach>


		</div>
		
		<%@include file="/pages/common/page_nav.jsp"%>
	
	</div>

	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>

</body>
</html>