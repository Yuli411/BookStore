<%@ page import="com.atguigu.pojo.Page" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

<meta charset="UTF-8">
<title>图书管理</title>
	<%@include file="/pages/common/header.jsp"%>
	<script type="text/javascript">
		$(function () {
			$("a.delete_butts").click(function () {
				return confirm("确认删除图书"+$(this).parent().parent().find("td:first").text()+"吗");
			});

			$("#submit").click(function () {
				var val = $("#pn_input").val();
				var pageTotal = ${requestScope.page.pageTotal};
				if (val > pageTotal || val < 1){
					alert("您输入的页面不存在")
				}else{
					location.href="http://10.220.89.160:8080/book/${requestScope.page.url}&pageNo="+val;
				}
			})

		})
	</script>

</head>
<body>
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
		<%@include file="/pages/common/manage_menu.jsp"%>

	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>
				<c:forEach items="${requestScope.page.items}" var="book">
			<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a href="manager/bookQuery?action=showUpdateBookInfo&id=${book.id}&method=updateBook&pageNo=${requestScope.page.pageNo}">修改</a></td>
					<td><a href="manager/bookQuery?action=deleteBook&id=${book.id}&pageNo=${requestScope.page.pageNo}" class="delete_butts">删除</a></td>
			</tr>
				</c:forEach>


			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?method=addBook&pageNo=${requestScope.page.pageTotal}">添加图书</a></td>
			</tr>
		</table>
	</div>

	<%@include file="/pages/common/page_nav.jsp"%>

	<%@include file="/pages/common/footer.jsp"%>
</body>

</html>