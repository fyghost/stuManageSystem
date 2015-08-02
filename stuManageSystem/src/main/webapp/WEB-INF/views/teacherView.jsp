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
<script type="text/javascript" src="resources/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="resources/ajaxfileupload.js"></script>
<script type="text/javascript" src="resources/myFunction.js"></script>
<link href="resources/css/mystyle.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
	var id = '${teacher.id}';
	var count = true;
	function addC() {
		$("#list_course").hide();
		if(count){
			$("#add_course").show();
			
		} else {
			$("#add_course").hide();
		}
		count = !count;	
	}
	function setting() {
		$("#settings").show();
	}
	
	function changePass() {
		$("#pass_form").show();
		$("#pic_form").hide();
	}
	function changePic() {
		$("#pass_form").hide();
		$("#pic_form").show();
	}
	function updateScore(i, id) {
		var message = "<input id='score" + i + "' type='text' name='" + id + "' />";
		$("#s"+id).html(message);

	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>�̹��������</title>
</head>
<body align="center">
	<div class="mydiv">
		
		<h1 >
			���ã�${teacher.name}!
			<img class="myImg" src="/resources/img/${teacher.img}">
		</h1>
	</div>
	<div style="float:left;margin-left:20px">
		<button onclick="addC()" class="button">��ӿγ�</button><br>
		<button onclick="listCourses()" class="button">�鿴�γ�</button><br>
	</div>
	<div id="add_course"  style="display:none;float:left;margin-left:20px">
		<form action="teacher/course/${teacher.id}"  id="add_form" method="post">
			<table class="hovertable">
				<tr>
					<th>�γ���</th><th>�Ͽ�����</th><th>�Ͽ�ʱ��</th>
				</tr>
				<tr onmouseover="this.style.backgroundColor='#3072b3';" onmouseout="this.style.backgroundColor='#d4e3e5';">
					<td><input id="course_name" name="course_name" class="text1" type="text" placeholder=" �γ���"></td>
					<td>
						<select name="weekday" class="shortselect">
							<option value="Mon" select="selected">��һ</option>
							<option value="Tue">�ܶ�</option>
							<option value="Wed">����</option>
							<option value="Thu">����</option>
							<option value="Fri">����</option>
						</select>
					</td>
					<td>
						<select name="period" class="shortselect">
							<option value="One" select="selected">8:00~9:50</option>
							<option value="Two">10:10~12:00</option>
							<option value="Three">13:30~15:20</option>
							<option value="Four">15:30~17:20</option>
							<option value="Five">18:30~20:20</option>
						</select>
					</td>
				</tr>
				<tr onmouseover="this.style.backgroundColor='#3072b3';" onmouseout="this.style.backgroundColor='#d4e3e5';">
					<td id="add_success"></td>
					<td></td>
					<td><input type="button" class="button" onclick="addCourse('add_form')" value="�ύ"></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="list_course" style="float:left; margin-left:20px">
	</div>
	<div id="list_student" style="float:left; margin-left:20px">
	</div>
	<div style="float:right; margin-left:20px">
		<button class="button" onclick="setting()">��������</button><br>
		<a class="button" href="login" >�ص���ҳ</a>
	</div>
	<div id="settings" style="display:none;float:right; margin-left:20px">
		<button class="button" onclick="changePass()">��������</button><br>
		<button class="button" onclick="changePic()">����ͷ��</button>
	</div>
	<div style="float:right; margin-left:20px" >
		<form id="pass_form" style="display:none" action="teacher/password/${teacher.id }">	
			<table class="hovertable" >
				<tr>
					<td>�����룺</td><td><input class="text1" type="password" name="passwordNew"></td>
				</tr>
				<tr>
					<td>����ȷ�ϣ�</td><td><input class="text1" type="password" name="passwordConfirm"></td>
				</tr>
				<tr>
					<td id="status"></td>	
					<td><input type="button" class="button" onclick="changePassword()"value="ȷ��"></td>
				</tr>
			</table>		
		</form>
		<form action="teacher/picture/${teacher.id }" id="pic_form" method="post" style="display:none"enctype="multipart/form-data"> 
			<p>�ϴ�ͷ��</p>
			<table class="hovertable"> 
				
				<tr><td><input name="imgFile" id="img_file" type="file" /></td></tr>
				<tr><td><input type="button" class="button" onclick="uploadPic('pic_form', 'img_file')" value="ȷ��"/></td></tr> 
				<!-- <input type="submit" value="ȷ��"/>  -->
			</table>
		 </form>  
	</div>
	

<script type="text/javascript">
	var s_id;
	var s_name;
	
	function addCourse(form_id) {
		
		$.ajax({
			type: "POST",
			url: $("#" + form_id).attr("action"),
			data: $('#' + form_id).serialize(),
			error: function() {
				alert("Connection error!");
			},
			success: function(data) {
				$("#course_name").attr("value", "");
				$("#add_success").html(data);
			}
		})
	}
	
	function listCourses() {
		count=true;
		var listUrl = "teacher/courses/" + id;
		$("#add_course").hide();
		$("#list_course").show();
		$("#list_student").html("");
		$.ajax({
			type: "GET",
			url: listUrl,
			error: function() {
			    alert("Connection error");
			},
			success: function(data) {
				var table = "<h4>�γ�</h4><br><table class='hovertable'><tr><th>�γ̺�</th>" + 
							"<th>����</th><th>�Ͽ�ʱ��</th><th>����</th></tr>";
	            console.log(data.length);
	            for(i=0;i<data.length;i++){
	            	var time = getTime(data[i].weekday, data[i].period);
	            	table += "<tr><td>" + data[i].id + "</td><td>" + data[i].name +
	            			"</td><td>" + time + 
	            			"</td><td><input type=button class='button' onclick='listStudents(" + 
	            			data[i].id + ",\" " +data[i].name + "\")' value='�鿴ѧ��'></td></tr>"; 
	        	}
	            table += "</table>";
	            console.log(table);
	            
	            $("#list_course").html(table); 
			}
	    });
	}
	function listStudents(course_id, course_name) {
		$("#list_student").show();
		s_id = course_id;
		s_name = course_name;
		var listUrl = "student/list/" + course_id;
		$.ajax({
			type: "GET",
			url: listUrl,
			error: function() {
			    alert("Connection error");
			},
			success: function(data) {
				var table = "";
				if(data.length != 0) {
					table = "<h4>�γ̣�" + course_name + "   �γ̺ţ�" + course_id + 
						"</h4><br><form id='student_table'><table class='hovertable'><tr><th>ѧ��</th><th>����</th><th>�ɼ�</th></tr>";
		            console.log(data.length);
		            for(i = 0; i < data.length; i++){
		            	var message;
		            	if(data[i].score == 0 || data[i].score == null){
		            		message = "<input id='score" + i + "' type='text' class='text1' name='" + data[i].id + "' />";
		            	} else {
		            		message = data[i].score + "<input type='button' class='button' onclick='updateScore(" + i + ", " + data[i].id + ")' value='���ķ���'";
		            	}
		            		
		            	table += "<tr><td>" + data[i].student_id + "</td><td>" + data[i].student_name + "</td><td id='s" + data[i].id + "'>" + message +"</td></tr>";
		        	}
		            table += "</table></form>";
		            table += "<button value='button' class='button' onclick='setScores(" + data[0].course_id + ", " + data.length + ")' >�ύ</button>";
		            table += "<button value='button' class='button' onclick='listCourses()' >����</button><br/>";
		           
				}
			 	console.log(table);
	            $("#list_student").html(table);
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
				listStudents(s_id, s_name);
			}
		})
	}
	
	/* function uploadPic(form_id, imgFile) {
		console.log(form_id);
		console.log(imgFile);
		$.ajaxFileUpload({
			url: $("#" + form_id).attr("action"),
			secureuri: false,
			fileElementId: imgFile,
			dataType: 'text',
			success: function(data) {
				window.location.reload();				
			}
		})
	} */
	
//	});
</script>

</body>
</html>