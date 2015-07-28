<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<script type="text/javascript" src="http://code.hs-cn.com/jquery/jquery-1.7.1.min.js"></script>

<script type="text/javascript">
/* 	$(document).ready(function(){
		function addStudent1() {

			alert("dgdfg");
			
		}
	}); */
	
	var id = ${student.id};
	/* function addC() {
		$("#addCourse").show();
	}
	function addColumn() {
		
	} */
	function changePass() {
		$("#pass_form").show();
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>学生个人管理界面</title>
</head>
<body align="center">
	<p >您好，${student.name}!</p>
	<div align="right">
		<button value="button" onclick="changePass()">更改密码</button>
		<form style="display: none" id="pass_form" action="student/password/${student.id}">
			新密码：<input type="password" name="passwordNew"><br>
			密码确认：<input type="password" name="passwordConfirm"><br>
			<input type="button" onclick="changePassword()" value="确认">
		</form>
		<label id="change_success"></label>
	</div>
	<div align="left" >
		<button value="button" onclick="listCourses()">查看所有课程</button><br />
		<button value="button" onclick="selectedCourses()" >查看已选课程</button><br/>
	</div>
	<div id="main_area" align="center">
	</div>
	<div id="list_user" align="right">
		<a href="login" >回到主页</a>
	</div>
	

<script type="text/javascript">
	//$(document).ready(function(){
	
	
	function listCourses() {
		var listUrl = "student/courses/" + id;
		/* $("#addCourse").hide();
		$("#list_user").show(); */
		$.ajax({
			type: "GET",
			url: listUrl,
			error: function() {
			    alert("Connection error");
			},
			success: function(data) {
				var table = "<h4>课程</h4><br><table border='1' align='center'><tr><th>课程号</th><th>课名</th><th>教师</th><th>选课情况</th></tr>";
	            console.log(data.length);
	            var message;
	            for(i=0;i<data.length;i++){
	                //json.id
	                if(data[i].selected == 0) {
	                	message = "<input type=button onclick='selectCourse(" + data[i].id + ")' value='选课'>";
	                } else {
	                	message = "已选 <input type=button onclick='deleteCourse(" + data[i].id + ")' value='删除'>";
	                }
	            	table += "<tr><td>"+data[i].id+"</td><td>"+data[i].name+"</td><td>" + data[i].teacher_name + "</td><td id=" + data[i].id + ">" + message + "</td></tr>";
	        	}
	            table += "</table>";
	            console.log(table);
	            
	            $("#main_area").html(table); 
			}
        });
	}
	
	function selectCourse(course_id) {
		var listUrl = "select/course/0" + id + "/" + course_id;
		console.log(listUrl);
		$.ajax({
			type: "GET",
			url: listUrl,
			error: function() {
				alert("Connection error");
			},
			success: function(data) {
				message = "已选 <input type=button onclick='deleteCourse(" + course_id + ")' value='删除'>";
				$("#" + course_id).html(message);
			}
		});
	}
	
	function selectedCourses() {
		var listUrl = "student/course/0" + id;
		$.ajax({
			type: "GET",
			url: listUrl,
			error: function() {
			    alert("Connection error");
			},
			success: function(data) {
				var table = "<h4>已选课程</h4><br><table border='1' align='center'><tr><th>课程号</th><th>课名</th><th>教师</th><th>分数</th></tr>";
	            console.log(data.length);
	            var message;
	            for(i=0;i<data.length;i++){
	            	var score = " ";
	            	if(data[i].score != 0)
	            		score = data[i].score;
	            	table += "<tr><td>"+data[i].course_id+"</td><td>"+data[i].course_name+"</td><td>" + data[i].teacher_name + "</td><td>" + score + "</td></tr>";
	        	}
	            table += "</table>";
	            console.log(table);
	            
	            $("#main_area").html(table); 
			}
		});
	}
	
	function deleteCourse(course_id) {
		var listUrl = "delete/course/0" + id + "/" + course_id;
		console.log(listUrl);
		$.ajax({
			type: "GET",
			url: listUrl,
			error: function() {
				alert("Connection error");
			},
			success: function(data) {
				message = "<input type=button onclick='selectCourse(" + data[i].id + ")' value='选课'>";
				$("#" + course_id).html(message);
			}
		});
	}
	
	function changePassword() {
		$.ajax({
			type:"POST",
			url:$("#pass_form").attr("action"),
			data:$("#pass_form").serialize(),
			error: function() {
				alert("Connection error");
			},
			success: function(data) {
				if(data == 1) {
					$("#pass_form").hide();
					$("#change_success").html("更改成功");
				}
				else if(data == 0) {
					$("#change_success").html("请输入正确的确认密码");
				}
			}
		})
	}
//	});
</script>

</body>
</html>