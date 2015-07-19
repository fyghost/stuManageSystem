<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body>
<p align="center">${message}</p>
<h4 align="center">登录</h4>
	<form  action="user/login" method="post">
		<table border=1 align="center">
		<tbody>
			<tr>
				<td>账号：</td>
				<td><input type="text" name="id"/></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="password" name="password"/></td>
			</tr>
			<tr>
				<td>
					<select name="user">
						<option value="admin">管理员</option>
						<option value="student" selected="selected">学生</option>
					</select>
				</td>
				<td>
					<input type="submit" value="确定">
				</td>
			</tr>
		</tbody>			
		</table>
	</form>
</body>
</html>