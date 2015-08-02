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
<title>教工管理界面</title>
</head>
<body align="center">
	<div class="mydiv">
		
		<h1 >
			您好，${teacher.name}!
			<img class="myImg" src="/resources/img/${teacher.img}">
		</h1>
	</div>
	<div style="float:left;margin-left:20px">
		<button onclick="addC()" class="button">添加课程</button><br>
		<button onclick="listCourses()" class="button">查看课程</button><br>
	</div>
	<div id="add_course"  style="display:none;float:left;margin-left:20px">
		<form action="teacher/course/${teacher.id}"  id="add_form" method="post">
			<table class="hovertable">
				<tr>
					<th>课程名</th><th>上课日期</th><th>上课时段</th>
				</tr>
				<tr onmouseover="this.style.backgroundColor='#3072b3';" onmouseout="this.style.backgroundColor='#d4e3e5';">
					<td><input id="course_name" name="course_name" class="text1" type="text" placeholder=" 课程名"></td>
					<td>
						<select name="weekday" class="shortselect">
							<option value="Mon" select="selected">周一</option>
							<option value="Tue">周二</option>
							<option value="Wed">周三</option>
							<option value="Thu">周四</option>
							<option value="Fri">周五</option>
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
					<td><input type="button" class="button" onclick="addCourse('add_form')" value="提交"></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="list_course" style="float:left; margin-left:20px">
	</div>
	<div id="list_student" style="float:left; margin-left:20px">
	</div>
	<div style="float:right; margin-left:20px">
		<button class="button" onclick="setting()">个人设置</button><br>
		<a class="button" href="login" >回到主页</a>
	</div>
	<div id="settings" style="display:none;float:right; margin-left:20px">
		<button class="button" onclick="changePass()">更改密码</button><br>
		<button class="button" onclick="changePic()">更改头像</button>
	</div>
	<div style="float:right; margin-left:20px" >
		<form id="pass_form" style="display:none" action="teacher/password/${teacher.id }">	
			<table class="hovertable" >
				<tr>
					<td>新密码：</td><td><input class="text1" type="password" name="passwordNew"></td>
				</tr>
				<tr>
					<td>密码确认：</td><td><input class="text1" type="password" name="passwordConfirm"></td>
				</tr>
				<tr>
					<td id="status"></td>	
					<td><input type="button" class="button" onclick="changePassword()"value="确认"></td>
				</tr>
			</table>		
		</form>
		<form action="teacher/picture/${teacher.id }" id="pic_form" method="post" style="display:none"enctype="multipart/form-data"> 
			<p>上传头像</p>
			<table class="hovertable"> 
				
				<tr><td><input name="imgFile" id="img_file" type="file" /></td></tr>
				<tr><td><input type="button" class="button" onclick="uploadPic('pic_form', 'img_file')" value="确定"/></td></tr> 
				<!-- <input type="submit" value="确定"/>  -->
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
				var table = "<h4>课程</h4><br><table class='hovertable'><tr><th>课程号</th>" + 
							"<th>课名</th><th>上课时间</th><th>操作</th></tr>";
	            console.log(data.length);
	            for(i=0;i<data.length;i++){
	            	var time = getTime(data[i].weekday, data[i].period);
	            	table += "<tr><td>" + data[i].id + "</td><td>" + data[i].name +
	            			"</td><td>" + time + 
	            			"</td><td><input type=button class='button' onclick='listStudents(" + 
	            			data[i].id + ",\" " +data[i].name + "\")' value='查看学生'></td></tr>"; 
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
					table = "<h4>课程：" + course_name + "   课程号：" + course_id + 
						"</h4><br><form id='student_table'><table class='hovertable'><tr><th>学号</th><th>姓名</th><th>成绩</th></tr>";
		            console.log(data.length);
		            for(i = 0; i < data.length; i++){
		            	var message;
		            	if(data[i].score == 0 || data[i].score == null){
		            		message = "<input id='score" + i + "' type='text' class='text1' name='" + data[i].id + "' />";
		            	} else {
		            		message = data[i].score + "<input type='button' class='button' onclick='updateScore(" + i + ", " + data[i].id + ")' value='更改分数'";
		            	}
		            		
		            	table += "<tr><td>" + data[i].student_id + "</td><td>" + data[i].student_name + "</td><td id='s" + data[i].id + "'>" + message +"</td></tr>";
		        	}
		            table += "</table></form>";
		            table += "<button value='button' class='button' onclick='setScores(" + data[0].course_id + ", " + data.length + ")' >提交</button>";
		            table += "<button value='button' class='button' onclick='listCourses()' >返回</button><br/>";
		           
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