<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<?xml version="1.0" encoding="UTF-8" ?>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>查询分数</title>
</head>
<body>
<p align="center">${message}<br/></p>
<h1 align="center">分数查询</h1>
<form method="post" action="${pageContext.request.contextPath}/student/gradeShow">
<table boder="1" align="center">
<tbody>
	<tr>
		<td>姓名：</td>
		<td><input type="text" name="name" /></td>
	</tr>
	<tr>
		<td>学号：</td>
		<td><input type="text" name="id"/></td>
	</tr>
	<tr>
		<td></td>
		<td><input type="submit" value="查询" /></td>
	</tr>
</tbody>
</table>
</form>

<p><a href="${pageContext.request.contextPath}/index.html">主页</a></p>
</body>
</html>