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
	
	
	function addStu() {
		$("#addStu").show();
		$("#addTea").hide();
	}
	function addTea() {
		$("#addStu").hide();
		$("#addTea").show();
	}
	
	function addColumn() {
		
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>管理员界面</title>
</head>
<body align="center">
	<p id="abc" >您好，管理员</p>
	<button value="button" onclick="addStu()">添加学生</button>
	<button value="button" onclick="addTea()">添加老师</button> <br/>
	<div id="message" align="left">
	<form action="student/add" id="addStu" style="display: none" method="post">
		<table boder=1 >
			<tr>
				<td>
					学号
				</td>
				<td>
					姓名
				</td>
			</tr>
			<tr>
				<td>
					<input id="student_id"type="text" name="id"/>
				</td>
				<td>
					<input id="student_name" type="text" name="name"/>
				</td>
			</tr>
			<tr>
				<td align="center">
					<input id="addColum" type="button" onclick="addColumn()" value="增加一栏">
				</td>
				<td align="center">
					<input id="addStudent" type="button" onclick="addStudent1()" value="提交"/>
				</td>
			</tr>
		</table>
	</form>
	</div>

<script type="text/javascript">
	//$(document).ready(function(){
		function addStudent1() {
			$.ajax({
                type: "POST",
                url:$("#addStu").attr("action"),
                data:$('#addStu').serialize(),// 你的formid
                error: function() {
                    alert("Connection error");
                },
                success: function(data) {
                	$("#student_id").attr("value", "");
                	$("#student_name").attr("value", "");
                }
            });
			$("#addTea").hide();
		}
//	});

</script>

</body>
</html>