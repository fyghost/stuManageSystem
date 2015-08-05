<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="resources/css/style.css">
<link href="resources/css/mystyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="resources/jquery-1.7.1.min.js"></script>
<script type="text/javascript">
	function errorButton() {
		$("#error_buttons").toggle("middle");
	}
</script>
<title>登录</title>
</head>
<body>
	<section class="container">
		<div class="login">
			<h1>用户登录</h1>
			<form action="user/login" method="post">
				<p>
					<input type="text" name="id" placeholder="账号">
				</p>
				<p>
					<input type="password" name="password" placeholder="密码 默认为学号或教工号">
				</p>
				<p class="remember_me">
					<input type="radio" name="user" value="student" checked="checked">学生
					<input type="radio" name="user" value="teacher">老师 <input
						type="radio" name="user" value="admin">管理员
				</p>
				<p class="submit">
					<input class="button" type="submit" value="确定">
				</p>
			</form>
		
			<p align="center">
				<font color="#FF0000">${message}</font>
			</p>
		</div>
	</section>
	<div align="left">
		<button class="button primary" onclick="errorButton()">点这里点这里</button><br>
	</div>
	<div id="error_buttons"align="left" style="display:none" id="error_buttons">
		<a href="user/login/exception/1" class="button danger">找不到页面！！</a><br>
		<a href="user/login/exception/2" class="button danger">找不到对象！！</a><br>
		<a href="user/login/exception/3" class="button danger">找不到错误！！</a><br>
		
	</div>
</body>
</html>