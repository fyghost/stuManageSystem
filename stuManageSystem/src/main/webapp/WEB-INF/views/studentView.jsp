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
	
	var id = '${student.id}';
	
	function changePass() {
		$("#pass_form").show();
		$("#pic_form").hide();
	}
	function changePic() {
		$("#pass_form").hide();
		$("#pic_form").show();
	}
	function setting() {
		$("#settings").show();
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>ѧ�����˹������</title>
</head>
<body align="center">
	<div class="mydiv">
		<h1>
			���ã�${student.name}!
			<img class="myImg" src="/resources/img/${student.img}" />
		</h1>
	</div>
	<div style="float:left;margin-left:20px">
		<a onclick="listCourses()" class="button pill">�鿴���пγ�</a><br />
		<a onclick="selectedCourses()" class="button pill">�鿴��ѡ�γ�</a><br/>
	</div>
	<div id="list_courses" style="float:left; margin-left:20px">
	</div>
	
	<div style="float:right; margin-left:20px">
		<button class="button" onclick="setting()">��������</button><br>
		<a class="button" href="login" >�ص���ҳ</a>
	</div>
	<div id="settings"style="display:none;float:right; margin-left:20px">
		<button class="button" onclick="changePass()">��������</button><br>
		<button class="button" onclick="changePic()" >����ͷ��</button>
	</div>
	<div style="float:right;margin-right:20px">
		<form action="student/picture/${student.id }" style="display:none" id="pic_form"method="post" enctype="multipart/form-data">  
			�ϴ�ͷ��<br>
			<table class="hovertable">
				<tr><td><a class="file"><input name="imgFile" id="img_file" type="file" >����ϴ�</a></td></tr>
				<tr><td><input class="button"type="button" onclick="uploadPic('pic_form', 'img_file')" value="ȷ��"/></td></tr>
			</table> 
		</form>  
		<form style="display: none" id="pass_form" action="student/password/${student.id}">
			<table class="hovertable">
				<tr><td>�����룺</td><td><input class="text1" type="password" name="passwordNew"></td></tr>
				<tr><td>����ȷ�ϣ�</td><td><input class="text1" type="password" name="passwordConfirm"></td></tr>
				<tr>
					<td id="status"></td>
					<td><input type="button" class="button"onclick="changePassword()" value="ȷ��"></td>
				</tr>
			</table>
		</form>
		<label id="change_success"></label>
	</div>
	
	

<script type="text/javascript">
	//$(document).ready(function(){
	function selectCourse(course_id) {
		var listUrl = "select/course/" + id + "/" + course_id;
		console.log(listUrl);
		$.ajax({
			type: "GET",
			url: listUrl,
			error: function() {
				alert("Connection error");
			},
			success: function(data) {
				message = "��ѡ <input class='button' type=button onclick='deleteCourse(" +
						course_id + ")' value='ɾ��'>";
				$("#course" + course_id).html(message);
			}
		});
	}
	
	function listCourses() {
		var listUrl = "student/courses/" + id;
		$.ajax({
			type: "GET",
			url: listUrl,
			error: function() {
			    alert("Connection error");
			},
			success: function(data) {
				var table = "<h4>�γ�</h4><br><table class='hovertable'><tr><th>�γ̺�</th>" + 
							"<th>����</th><th>��ʦ</th><th>�Ͽ�ʱ��</th><th>ѡ�����</th></tr>";
	            console.log(data.length);
	            var message;
	            for(i = 0; i < data.length; i++){
	                //json.id
	                var time = getTime(data[i].weekday, data[i].period);
	                if(data[i].selected == 0) {
	                	message = "<input class='button' type=button onclick='selectCourse(" +
	                			data[i].id + ")' value='ѡ��'>";
	                } else {
	                	message = "��ѡ <input class='button' type=button onclick='deleteCourse(" + 
	                			data[i].id + ")' value='ɾ��'>";
	                }
	            	table += "<tr><td>" + data[i].id + "</td><td>" + data[i].name + 
	            			"</td><td>" + data[i].teacher_name + "</td><td>" + time +
	            			"</td><td id=course" + data[i].id + ">" + message + "</td></tr>";
	        	}
	            table += "</table>";
	            table += "<label id='select_success'></label>"
	            console.log(table);
	            
	            $("#list_courses").html(table); 
			}
        });
	}
	
	function selectedCourses() {
		var listUrl = "student/course/" + id;
		$.ajax({
			type: "GET",
			url: listUrl,
			error: function() {
			    alert("Connection error");
			},
			success: function(data) {
				var table = "<h4>��ѡ�γ�</h4><br><table class='hovertable'><tr>" + 
							"<th>�γ̺�</th><th>����</th><th>��ʦ</th><th>����</th></tr>";
	            console.log(data.length);
	            var message;
	            for(i=0;i<data.length;i++){
	            	var score = " ";
	            	if(data[i].score != 0)
	            		score = data[i].score;
	            	table += "<tr><td>" + data[i].course_id + "</td><td>" +
	            			data[i].course_name+"</td><td>" + data[i].teacher_name +
	            			"</td><td>" + score + "</td></tr>";
	        	}
	            table += "</table>";
	            console.log(table);
	            
	            $("#list_courses").html(table); 
			}
		});
	}
	
	function deleteCourse(course_id) {
		var listUrl = "delete/course/" + id + "/" + course_id;
		console.log(listUrl);
		$.ajax({
			type: "GET",
			url: listUrl,
			error: function() {
				alert("Connection error");
			},
			success: function(data) {
				message = "<input class='button' type=button onclick='selectCourse(" +
							course_id + ")' value='ѡ��'>";
				$("#course" + course_id).html(message);
			}
		});
	}
	
/* 	function uploadPic(form_id, imgFile) {
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