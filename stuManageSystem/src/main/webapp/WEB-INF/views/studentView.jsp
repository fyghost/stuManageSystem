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
<title>ѧ�����˹������</title>
</head>
<body align="center">
	<p >���ã�${student.name}!</p>
	<div align="right">
		<button value="button" onclick="changePass()">��������</button>
		<form style="display: none" id="pass_form" action="student/password/${student.id}">
			�����룺<input type="password" name="passwordNew"><br>
			����ȷ�ϣ�<input type="password" name="passwordConfirm"><br>
			<input type="button" onclick="changePassword()" value="ȷ��">
		</form>
		<label id="change_success"></label>
	</div>
	<div align="left" >
		<button value="button" onclick="listCourses()">�鿴���пγ�</button><br />
		<button value="button" onclick="selectedCourses()" >�鿴��ѡ�γ�</button><br/>
	</div>
	<div id="main_area" align="center">
	</div>
	<div id="list_user" align="right">
		<a href="login" >�ص���ҳ</a>
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
				var table = "<h4>�γ�</h4><br><table border='1' align='center'><tr><th>�γ̺�</th><th>����</th><th>��ʦ</th><th>ѡ�����</th></tr>";
	            console.log(data.length);
	            var message;
	            for(i=0;i<data.length;i++){
	                //json.id
	                if(data[i].selected == 0) {
	                	message = "<input type=button onclick='selectCourse(" + data[i].id + ")' value='ѡ��'>";
	                } else {
	                	message = "��ѡ <input type=button onclick='deleteCourse(" + data[i].id + ")' value='ɾ��'>";
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
				message = "��ѡ <input type=button onclick='deleteCourse(" + course_id + ")' value='ɾ��'>";
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
				var table = "<h4>��ѡ�γ�</h4><br><table border='1' align='center'><tr><th>�γ̺�</th><th>����</th><th>��ʦ</th><th>����</th></tr>";
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
				message = "<input type=button onclick='selectCourse(" + data[i].id + ")' value='ѡ��'>";
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
					$("#change_success").html("���ĳɹ�");
				}
				else if(data == 0) {
					$("#change_success").html("��������ȷ��ȷ������");
				}
			}
		})
	}
//	});
</script>

</body>
</html>