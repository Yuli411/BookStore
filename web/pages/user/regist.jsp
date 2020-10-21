<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>
	<%@include file="/pages/common/header.jsp"%>

<!--写base标签固定到工程目录下!-->
	<script>
		$(function () {
			$("#sub_btn").click(function () {
				//获取用户输入的内容
				//生成正则表达式
				//验证
				var username = $("input[name=username]").val();
				var usernameReg = /^\w{5,12}$/;
				if (!usernameReg.test(username)){
					//不匹配提示用户
					$("span.errorMsg").text("用户名不合法");
					return false;
				}

				var password = $("#password").val();
				var passReg = /^\w{5,12}$/;
				if (!passReg.test(password)){
					//不匹配提示用户
					$("span.errorMsg").text("密码不合法");
					return false;
				}


				//重复输入密码确认和密码相同
				var password2 = $("#repwd").val();
				if (password != password2){
					$("span.errorMsg").text("密码不相同");
					return false;
				}
				//邮箱
				var email = $("#email").val();

				var emailReg = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
				if (!emailReg.test(email)){
					$("span.errorMsg").text("邮箱格式不合法");
					return false;
				}

				//验证码处是否有内容
				var code = $("#code").val();

				//去掉前后空格
				code = $.trim(code);
				if (code == null || code == ""){
					$("span.errorMsg").text("验证码不能为空");
					return false;
				}
				$("span.errorMsg").text("");

			});

			$("#img").click(function () {
				//this是正在响应事件的dom对象
				this.src="http://10.220.89.160:8080/book/kaptcha.jpg?d="+ new Date();
			});

			$("#username").blur(function () {
				var username = $("#username").val();
				$.getJSON("http://10.220.89.160:8080/book/user","action=existUserName&username="+username,function (data) {

					if (data.isExist){
						$("span.errorMsg").text("用户名不可用！");
					}else{
						$("span.errorMsg").text("用户名可用！");
					}
				})


			})
		})
	</script>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">
<%--									<%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%>--%>
                                    ${empty requestScope.msg?"":requestScope.msg}
								</span>
							</div>
							<div class="form">
								<form action="user" method="post">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" id="username"
										   value="${empty requestScope.username?"":requestScope.username}" >
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" id="email"
									value="${empty requestScope.email?"":requestScope.email}">
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 150px;" name="code" id="code" value="${empty requestScope.code?"":requestScope.code}">
									<img alt="" src="kaptcha.jpg" id="img" style="float: right; margin-right: 40px ; height: 23px; width: 100px">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									<input type="hidden" name="action" value="register">


								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%@include file="/pages/common/footer.jsp"%>
</body>
</html>