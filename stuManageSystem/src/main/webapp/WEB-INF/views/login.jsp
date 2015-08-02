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
</body>
</html>