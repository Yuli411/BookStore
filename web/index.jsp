<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
	<%@include file="/pages/common/header.jsp"%>
	<script type="text/javascript">
		$(function () {
			location.href="http://10.220.89.160:8080/book/client/bookServlet?action=page";
		})
	</script>
</head>

</html>