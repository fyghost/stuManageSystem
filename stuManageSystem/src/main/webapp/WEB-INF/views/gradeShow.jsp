<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<?xml version="1.0" encoding="UTF-8" ?>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>成绩查询</title>
</head>
<body align="center">
<h1>您的成绩</h1>
<table border="1px" cellpadding="0" cellspacing="0"  align="center">
<thead>
<tr>
<th width="10%">姓名</th><th width="15%">学号</th><th width="10%">分数</th>
</tr>
</thead>
<tbody>
<tr>
	<td>${student.name }</td>
	<td>${student.id }</td>
	<td>${student.grade }</td>
</tr>
</tbody>
</table>

<p><a href="${pageContext.request.contextPath}/index.html">Home page</a></p>

</body>
</html>