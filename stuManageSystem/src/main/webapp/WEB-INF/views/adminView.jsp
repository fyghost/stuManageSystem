<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
<script type="text/javascript" src="resources/jquery-1.7.1.min.js"></script>


<script type="text/javascript">
	function addU() {
		$("#list_user").hide();
		$("#main_area").show();
	}
</script>

<link href="resources/css/mystyle.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; utf-8">
<title>管理员界面</title>
</head>
<body align="center">
	<div class="mydiv">
		<h1>
			您好，管理员 <img class="myImg" src="/resources/img/default.jpg">
		</h1>
	</div>
	<button class="button" onclick="addU()">添加用户</button>
	<br />
	<button class="button" onclick="listStudent()">删除学生</button>
	<button class="button" onclick="listTeacher()">删除老师</button>
	<br />
	<div id="main_area" style="display: none" align="center">
		<form action="user/add" id="addUser" method="post">
			<table class="hovertable">
				<tr>
					<td>学号/教工号</td>
					<td>姓名</td>
				</tr>
				<tr>
					<td><input class="text1" id="id" type="text" name="id" /></td>
					<td><input class="text1" id="name" type="text" name="name" /></td>
				</tr>
				<tr>
					<td><input type="radio" name="user" value="student"
						checked="checked">学生 <input type="radio" name="user"
						value="teacher">老师</td>
					<td><input class="button" id="addButton" type="button"
						onclick="addUser()" value="提交" /></td>
				</tr>
			</table>
		</form>
		<label id="add_success"></label>
	</div>
	
	<div id="list_user"></div>
	<div align="right">
		<a class="button" href="login">回到主页</a>
	</div>

	<script type="text/javascript">
		function addUser() {
			$.ajax({
				type : "POST",
				url : $("#addUser").attr("action"),
				data : $('#addUser').serialize(),
				error : function() {
					alert("Connection error");
				},
				success : function(data) {
					$("#id").attr("value", "");
					$("#name").attr("value", "");
					$("#addButton").attr("value", "继续添加");
					$("#add_success").html(data);
				}
			});
		}

		function listStudent() {
			$("#main_area").hide();
			$("#list_user").show();
			$.ajax({
				type : "GET",
				url : "student/list",
				error : function() {
					alert("Connection error");
				},
				success : function(data) {
					var table = "<h4>学生列表</h4><br><table class='hovertable' border='1' align='center'>"
							+ "<tr><th>学号</th><th>姓名</th><th></th></tr>";
					console.log(data.length);
					for (i = 0; i < data.length; i++) {
						//json.id
						table += "<tr><td>"
								+ data[i].id
								+ "</td><td>"
								+ data[i].name
								+ "</td><td><button class='button' value='button' onclick='deleteStudent(\""
								+ data[i].id + "\")'>删除</td></tr>";
					}
					table += "</table>";
					console.log(table);

					$("#list_user").html(table);
				}
			});
		}
		function listTeacher() {
			$("#main_area").hide();
			$("#list_user").show();
			$.ajax({
				type : "GET",
				url : "teacher/list",
				error : function() {
					alert("Connection error");
				},
				success : function(data) {
					var table = "<h4>教师列表</h4><br><table class='hovertable' id='teacher_table' border='1' align='center'>"
							+ "<tr><th>教工号</th><th>姓名</th><th></th></tr>";
					console.log(data.length);
					for (i = 0; i < data.length; i++) {
						//json.id
						table += "<tr><td>"
								+ data[i].id
								+ "</td><td>"
								+ data[i].name
								+ "</td><td><button class='button' value='button' onclick='deleteTeacher(\""
								+ data[i].id + "\")'>删除</td></tr>";
					}
					table += "</table>";
					console.log(table);
					$("#list_user").html(table);
				}
			});
		}
		function deleteTeacher(id) {
			$.ajax({
				type : "GET",
				url : "teacher/delete/" + id,
				error : function() {
					alert("Connection error");
				},
				success : function(data) {
					listTeacher();
				}
			})
		}

		function deleteStudent(id) {
			$.ajax({
				type : "GET",
				url : "student/delete/" + id,
				error : function() {
					alert("Connection error");
				},
				success : function(data) {
					listStudent();
				}
			})
		}
	</script>

</body>
</html>