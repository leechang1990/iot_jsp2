<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="<%=rootPath%>/ui/css/login.css">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/common/header.jspf" flush="false" />
	<div class="wrapper fadeInDown">
		<div id="formContent">
			<!-- Tabs Titles -->
			<h2 class="active">Sign In</h2>
			<!-- <h2 class="inactive underlineHover">Sign Up</h2> -->

			<!-- Icon -->
			<div class="fadeIn first">
				<img src="http://danielzawadzki.com/codepen/01/icon.svg" id="icon"
					alt="User Icon" />
			</div>

			<!-- Login Form -->
			<form>
				<input type="text" id="userId" class="fadeIn second" name="userId" placeholder="login"> 
				<input type="text" id="userPwd" class="fadeIn third" name="userPwd" placeholder="password"> 
				<input class="btn btn-lg btn-primary btn-block" type="button" id="loginBtn" value="login" onclick="checkValue()">
			</form>

			<!-- Remind Passowrd -->
			<div id="formFooter">
				<a href="/view/user/signin" class="btn btn-info" role="button">회원가입
				</a>
			</div>

		</div>
	</div>
</body>
<script>

function checkValue(){
	alert(1);
	var objs = $(".container");
	var userId = $("#userId").val().trim();
	var userPwd = $("#userPwd").val().trim();
	if(userId.length<3){
		alert("유저아이디 확인해!!");
		$("#userId").focus();
		return;
	}
	if(userPwd.length<3){
		alert("비밀번호 확인해!!");
		$("#userPwd").focus();
		return;
	}
	var param = {uiId:userId, uiPwd:userPwd};
	
	param = "param=" + encodeURIComponent(JSON.stringify(param));
	$.ajax({
		url : '<%=rootPath%>/user/login',
		data : param,
		type :'get',
		success:function(res){
			var obj = JSON.parse(res);
			alert(obj.msg);
			if(obj.login=="ok"){
				location.href="<%=rootPath%>/";
			}
		}
	})
}
</script>
</html>
