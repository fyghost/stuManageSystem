<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<?xml version="1.0" encoding="UTF-8" ?>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>添加学生成绩</title>
</head>
<body align="center">
<h1>添加学生成绩</h1>
<p>在这里添加学生成绩.</p>
<form method="post" action="${pageContext.request.contextPath}/grade/add" >
<table align="center">
<tbody>
	<tr>
		<td>姓名:<input type="text" name="name" /></td>
	</tr>
	<tr>
		<td>分数:<input type="text" name="grade" /></td>
	</tr>
	<tr>
		<td><input type="submit" value="添加" /></td>
		<td></td>
	</tr>
</tbody>
</table>
</form>

<p><a href="${pageContext.request.contextPath}/index">主页</a></p>
</body>
</html>