/*
 * 老师的方法
 */
function addCourse(form_id) {
		
	$.ajax({
		type: "POST",
		url:$("#" + form_id).attr("action"),
		data:$('#' + form_id).serialize(),
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

function getTime(weekday, period) {
	var time = "";
	if(weekday == "Mon") {
		time += "周一";
	} else if(weekday == "Tue") {
		time += "周二";
	} else if(weekday == "Wed") {
		time += "周三";
	} else if(weekday == "Thu") {
		time += "周四";
	} else if(weekday == "Fri") {
		time += "周五";
	}
	time += " ";
	if(period == "One") {
		time += "8:00~9:50";
	} else if(perriod == "Two") {
		time += "10:10~12:00";
	} else if(perriod == "Three") {
		time += "13:30~15:20";
	} else if(perriod == "Four") {
		time += "15:30~17:20";
	} else if(perriod == "Five") {
		time += "18:30~20:20";
	}
	return time;
}
//学生的方法

function uploadPic(form_id, imgFile) {
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
}