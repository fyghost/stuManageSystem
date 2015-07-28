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
	
	
	function addU() {
		$("#list_user").hide();
		$("#main_area").show();
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>����Ա����</title>
</head>
<body align="center">
	<p >���ã�����Ա</p>
	<button value="button" onclick="addU()">����û�</button><br />
	<button value="button" onclick="listStudent()" >ɾ��ѧ��</button>
	<button value="button" onclick="listTeacher()" >ɾ����ʦ</button><br/>
	<div id="main_area" align="center">
	<form action="user/add" id="addUser" method="post">
		<table border=1 >
			<tr>
				<td>
					ѧ��/�̹���
				</td>
				<td>
					����
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
					<input type="radio" name="user" value="student" checked="checked" >ѧ��
					<input type="radio" name="user" value="teacher" >��ʦ
				</td>
				<td align="center">
					<input type="button" onclick="addUser()" value="�ύ"/>
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
				var table = "<h4>ѧ���б�</h4><br><table border='1' align='center'><tr><th>ѧ��</th><th>����</th><th></th></tr>";
	            console.log(data.length);
	            for(i=0;i<data.length;i++){
	                //json.id
	            	table += "<tr><td>"+data[i].id+"</td><td>"+data[i].name+"</td><td><a href='student/delete/" + data[i].id + "'>ɾ��</td></tr>";
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
				var table = "<h4>��ʦ�б�</h4><br><table id='teacher_table' border='1' align='center'><tr><th>�̹���</th><th>����</th><th></th></tr>";
	            console.log(data.length);
	            for(i=0;i<data.length;i++){
	                //json.id
	            	table += "<tr><td>"+data[i].id+"</td><td>"+data[i].name+"</td><td><a href='teacher/delete/" + data[i].id + "'>ɾ��</td></tr>";
	        	}
	            table += "</table>";
	            console.log(table);
	            $("#list_user").html(table);
			}
        });
	}
//	});
</script>

</body>
</html>