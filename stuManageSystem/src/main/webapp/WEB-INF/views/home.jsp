
<?xml version="1.0" encoding="UTF-8" ?>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>学生成绩管理主页</title>
</head>
<body align="center">
<h1>
	这里是主页
</h1>
<p>${message} <br/>

<a href="${pageContext.request.contextPath}/add">添加学生分数</a><br/>
<a href="${pageContext.request.contextPath }/student/gradeReq">查看成绩</a>
</p>
</body>
</html>
