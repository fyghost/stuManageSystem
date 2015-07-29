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
	
	var id = ${teacher.id};
	function addC() {
		$("#addCourse").show();
		$("#list_user").hide();
	}
	function changePass() {
		$("#pass_form").show();
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>�̹��������</title>
</head>
<body align="center">
	<p >���ã�${teacher.name}!</p> 
	<div align="right">
		<button value="button" onclick="changePass()">��������</button>
		<form style="display: none" id="pass_form" action="teacher/password/${teacher.id}">
			�����룺<input type="password" name="passwordNew"><br>
			����ȷ�ϣ�<input type="password" name="passwordConfirm"><br>
			<input type="button" onclick="changePassword()" value="ȷ��">
		</form>
		<label id="change_success"></label>
	</div>
	<div align="left" >
		<button value="button" onclick="addC()">��ӿγ�</button><br />
		<button value="button" onclick="listCourses()" >�鿴�γ�</button><br/>
	</div>
	<div id="main_area" align="center">
	<form action="course/add/${teacher.id }"  id="addCourse" style="display: none" method="post">
		<table border=1 >
			<tr>
				<td>
					�γ���
				</td>
				<td>
					<input id="course_name" type="text" name="course_name">
				</td>
			</tr>
			<tr>
				<td align="center">
					<input type="button" onclick="addCourse()" value="ȷ��"/>
				</td>
			</tr>
		</table>
	</form>
	<div id="add_success" style="display:none">��ӳɹ�</div>
	</div>
	<div id="list_user">
	</div>
	<div align="right">
		<a href="login" >�ص���ҳ</a>
	</div>

<script type="text/javascript">
	var s_id;
	var s_name;
	function addCourse() {
		
		$.ajax({
			type: "POST",
			url:$("#addCourse").attr("action"),
			data:$('#addCourse').serialize(),
			error: function() {
			    alert("Connection error");
			},
			success: function(data) {
				/* $("#name").attr("value", "");
				$("#add_success").show(); */
				alert(data);
			}
		});
	}
	
	function listCourses() {
		var listUrl = "course/list/" + id;
		$("#addCourse").hide();
		$("#list_user").show();
		$.ajax({
			type: "GET",
			url: listUrl,
			error: function() {
			    alert("Connection error");
			},
			success: function(data) {
				var table = "<h4>�γ�</h4><br><table border='1' align='center'><tr><th>�γ̺�</th><th>����</th><th></th></tr>";
	            console.log(data.length);
	            for(i=0;i<data.length;i++){
	                //json.id
	            	table += "<tr><td>"+data[i].id+"</td><td>"+data[i].name+"</td><td><input type=button onclick='listStudents(" + 
	            			data[i].id + ",\" " +data[i].name + "\")' value='�鿴ѧ��'></td></tr>"; 
	        	}
	            table += "</table>";
	            console.log(table);
	            
	            $("#list_user").html(table); 
			}
        });
	}
	function listStudents(course_id, course_name) {
		s_id = course_id;
		s_name = course_name;
		var listUrl = "student/list/" + s_id;
		$("#addCourse").hide();
		$("#list_user").show();
		$.ajax({
			type: "GET",
			url: listUrl,
			error: function() {
			    alert("Connection error");
			},
			success: function(data) {
				var table = "<h4>�γ̣�" + course_name + "   �γ̺ţ�" + course_id + "</h4><br><form id='student_table'><table border='1' align='center'><tr><th>ѧ��</th><th>����</th><th>�ɼ�</th></tr>";
	            console.log(data.length);
	            for(i=0;i<data.length;i++){
	            	var message;
	            	if(data[i].score == 0 || data[i].score == null){
	            		message = "<input id='score" + i + "' type='text' name='" + data[i].id + "' />";
	            	} else {
	            		message = data[i].score + "<input type='button' onclick='updateScore(" + i + ", " + data[i].id + ")' value='���ķ���'";
	            	}
	            		
	            	table += "<tr><td>" + data[i].student_id + "</td><td>" + data[i].student_name + "</td><td id='s" + data[i].id + "'>" + message +"</td></tr>";
	        	}
	            table += "</table></form>";
	            table += "<button value='button' onclick='setScores(" + data[0].course_id + ", " + data.length + ")' >�ύ</button>"
	            table += "<button value='button' onclick='listCourses()' >����</button><br/>"
	            console.log(table);
	            $("#list_user").html(table);
			}
        });
	}
	function setScores(course_id, size) {
		var ary = new Array();
		var form = $("#student_table");
		for(var i = 0; i < size; i++) {
			var score_id = $("#score"+i).attr("name");
			var score = $("#score"+i).attr("value")
			if(score_id != null && score != null) {
				var tmp = {"score_id": score_id, "score": score};
				ary.push(tmp);
			}
		}
		$.ajax({
			type: "POST",
			url:"set/scores/"+ course_id,
			data: JSON.stringify(ary),
			contentType : 'application/json;charset=utf-8',
			error: function() {
				alert("Connection error");
			},
			success: function(data) {
				/* for(var i = 0; i < size; i ++) {
					var message;
	            	if(data[i].score == 0 || data[i].score == null){
	            		message = "<input id='score" + i + "' type='text' name='" + data[i].id + "' />";
	            	} else {
	            		message = data[i].score + "<input type='button' onclick='updateScore(" + i + "," + data[i].id + ")' value='���ķ���'";
	            	}
	            	$("#s" + data[i].id).html(message);
				} */
				listStudents(s_id, s_name);
			}
		})
	}
	function updateScore(i, id) {
		var message = "<input id='score" + i + "' type='text' name='" + id + "' />";
		$("#s"+id).html(message);
	
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