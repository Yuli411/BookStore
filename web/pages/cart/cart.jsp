<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%@include file="/pages/common/header.jsp"%>
	<script type="text/javascript">
		$(function () {
			$("a.deleteItem").click(function () {
				var msg = $(this).parent().parent().find("td:first").text();
				if (confirm("确定要删除商品"+msg+"吗")){
					return true;
				}else{
					return false
				}
			});
			$("a.clearCart").click(function () {
				if (confirm("确定要清空购物车吗？？")){
					return true;
				}else{
					return false;
				}
			});
			<!--省去自己判断内容是否相同的过程 !-->
			$("input.count").change(function () {
					var msg = $(this).parent().parent().find("td:first").text();
					if (confirm("您是否确认修改商品"+msg+"个数为"+$(this).val())){
							location.href="http://10.220.89.160:8080/book/cartServlet?action=updateItem&id="+$(this).attr("itemid")+"&count="+$(this).val();

						}else{
							this.value = this.defaultValue;
					}
				onclick="window.location.href = '${PATH}/admin/delete?id=${admin.id }'"

			})
		})
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<%@ include file="/pages/common/login_success_user.jsp"%>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:if test="${not empty sessionScope.cart.items}">
				<c:forEach items="${sessionScope.cart.items}" var="item">
					<tr>
						<td>${item.value.name}</td>
						<td><input type="text" style="width: 80px" name="count" value="${item.value.count}" class="count"  itemid="${item.value.id}"></td>
						<td><fmt:formatNumber type="number" value="${item.value.price}" pattern="0.00" maxFractionDigits="2"/></td>
						<td><fmt:formatNumber type="number" value="${item.value.totalPrice}" pattern="0.00" maxFractionDigits="2"/></td>
						<td><a href="cartServlet?action=deleteItem&id=${item.value.id}" class="deleteItem">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>

		</table>
		<c:choose>
			<c:when test="${not empty sessionScope.cart.items}">
				<div class="cart_info">
					<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.itemsTotal}</span>件商品</span>
					<span class="cart_span">总金额<span class="b_price"><fmt:formatNumber type="number" value="${sessionScope.cart.priceTotal}" pattern="0.00" maxFractionDigits="2"/></span>元</span>
					<span class="cart_span"><a href="cartServlet?action=clearCart" class="clearCart">清空购物车</a></span>
					<span class="cart_span"><a href="orderServlet?action=placeAnOrder">去结账</a></span>
				</div>
			</c:when>
			<c:when test="${empty sessionScope.cart.items}">
					<span class="cart_span" style="text-align: center; display: block;font-size: large"><a href="index.jsp" style="color: red">亲！购物车为空！快去浏览商品吧！</a></span>
			</c:when>
		</c:choose>

	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>