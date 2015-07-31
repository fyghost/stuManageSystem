<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<script type="text/javascript" src="resources/jquery-1.7.1.min.js"></script>

<script type="text/javascript">
/* 	$(document).ready(function(){
		function addStudent1() {

			alert("dgdfg");
			
		}
	}); */
	
	
	function addU() {
		$("#list_user").hide();
		$("#main_area").show();
	}
</script>
<meta http-equiv="Content-Type" content="text/html; utf-8">
<title>管理员界面</title>
</head>
<body align="center">
	<p >您好，管理员</p>
	<button value="button" onclick="addU()">添加用户</button><br />
	<button value="button" onclick="listStudent()" >删除学生</button>
	<button value="button" onclick="listTeacher()" >删除老师</button><br/>
	<div id="main_area" align="center">
	<form action="user/add" id="addUser" method="post">
		<table border=1 >
			<tr>
				<td>
					学号/教工号
				</td>
				<td>
					姓名
				</td>
			</tr>
			<tr>
				<td>
					<input id="id"type="text" name="id"/>
				</td>
				<td>
					<input id="name" type="text" name="name"/>
				</td>
			</tr>
			<tr>
				<td align="center">
					<input type="radio" name="user" value="student" checked="checked" >学生
					<input type="radio" name="user" value="teacher" >老师
				</td>
				<td align="center">
					<input id="addButton" type="button" onclick="addUser()" value="提交"/>
				</td>
			</tr>
		</table>
	</form>
	<div id="add_success" style="display:none"></div>
	</div>
	<div id="list_user">
	</div>
	<div align="right">
		<a href="login" >回到主页</a>
	</div>

<script type="text/javascript">
	//$(document).ready(function(){
	function addUser() {
		
		$.ajax({
               type: "POST",
               url:$("#addUser").attr("action"),
               data:$('#addUser').serialize(),
               error: function() {
                   alert("Connection error");
               },
               success: function(data) {
               	$("#id").attr("value", "");
               	$("#name").attr("value", "");
               	$("#addButton").attr("value", "继续添加")
               	$("#add_success").html(data);
               	$("#add_success").show();
               }
           });
	}
	
	function listStudent() {
		$("#main_area").hide();
		$("#list_user").show();
		$.ajax({
			type: "GET",
			url:"student/list",
			error: function() {
			    alert("Connection error");
			},
			success: function(data) {
				var table = "<h4>学生列表</h4><br><table border='1' align='center'><tr><th>学号</th><th>姓名</th><th></th></tr>";
	            console.log(data.length);
	            for(i=0;i<data.length;i++){
	                //json.id
	            	table += "<tr><td>"+data[i].id+"</td><td>"+data[i].name+"</td><td><button value='button' onclick='deleteStudent(" + data[i].id + ")'>删除</td></tr>";
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
			type: "GET",
			url:"teacher/list",
			error: function() {
			    alert("Connection error");
			},
			success: function(data) {
				var table = "<h4>教师列表</h4><br><table id='teacher_table' border='1' align='center'><tr><th>教工号</th><th>姓名</th><th></th></tr>";
	            console.log(data.length);
	            for(i=0;i<data.length;i++){
	                //json.id
	            	table += "<tr><td>"+data[i].id+"</td><td>"+data[i].name+"</td><td><button value='button' onclick='deleteTeacher(" + data[i].id + ")'>删除</td></tr>";
	        	}
	            table += "</table>";
	            console.log(table);
	            $("#list_user").html(table);
			}
        });
	}
	function deleteTeacher(id) {
		$.ajax({
			type:"GET",
			url: "teacher/delete/" + id,
			error: function() {
				alert("Connection error");
			},
			success: function(data) {
				listTeacher();
			}
		})
	}
	
	function deleteStudent(id) {
		$.ajax({
			type:"GET",
			url: "student/delete/" + id,
			error: function() {
				alert("Connection error");
			},
			success: function(data) {
				listStudent();
			}
		})
	}
//	});
</script>

</body>
</html>