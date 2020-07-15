<%@page import="poly.util.CmmUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%
		String reg_id = CmmUtil.nvl((String)session.getAttribute("reg_id"));
	%>
<head>
	<title>Clear login Form Responsive Widget Template :: w3layouts</title>
	<!-- Meta-Tags -->
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="utf-8">
	<meta name="keywords" content="Clear login Form a Responsive Web Template, Bootstrap Web Templates, Flat Web Templates, Android Compatible Web Template, Smartphone Compatible Web Template, Free Webdesigns for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design">
	<script>
		addEventListener("load", function () {
			setTimeout(hideURLbar, 0);
		}, false);

		function hideURLbar() {
			window.scrollTo(0, 1);
		}
		
	</script>
	
	<script type="text/javascript">
		function popupOpen(){
		
			var popUrl = "/member/id_check.do";	//팝업창에 출력될 페이지 URL
		
			var popOption = "width=370, height=360, resizable=no, scrollbars=no, status=no;";    //팝업창 옵션(optoin)
		
				window.open(popUrl,"",popOption);
		
			}
</script>
	<!-- //Meta-Tags -->
	<!-- Stylesheets -->
	<link href="/resources/css/font-awesome.css" rel="stylesheet">
	<link href="/resources/css/style_login.css" rel='stylesheet' type='text/css' />
	<!--// Stylesheets -->
	<!--fonts-->
	<!-- title -->
	<!-- body -->
	<link href="//fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i&amp;subset=devanagari,latin-ext" rel="stylesheet">
	<!--//fonts-->
</head>
<body>
	<h1>Clear Reg Form </h1>
	
	<div class="w3ls-login box box--big">
		<!-- form starts here -->
		
		<form action="/member/id_check.do" data-ajax="false" method="post">
			<div class="agile-field-txt">
			<label>
				<i class="fa fa-user" aria-hidden="true"></i> 이메일 </label>
			<button href="javascript:popupOpen();">이메일 중복확인</button>
			</div>
			</form>
			
		<form action="/member/memberRegProc.do" onkeyup="tocheckpw2();" onsubmit="return tocheckpw2();" method="post">
		
		<div class="agile-field-txt">
			<label>
				<i class="fa fa-user" aria-hidden="true"></i> 이메일 </label><br/>
			<input type="hidden" id="id" name="id" value="<%=reg_id%>" required="" />
			<%=reg_id%>
			</div>
		<div class="agile-field-txt">
			<label>
				<i class="fa fa-user" aria-hidden="true"></i> 이름 </label>
			<input type="text" name="name" placeholder="Enter your name " required="" />
		</div>
		
		<div class="agile-field-txt">
			<label>
				<i class="fa fa-envelope" aria-hidden="true"></i> 비밀번호 </label>
			<input type="password" id="password" name="password" placeholder="Enter your password " required=""/>
		</div>
		
		<div class="agile-field-txt">
			<label>
				<i class="fa fa-envelope" aria-hidden="true"></i> 비밀번호 확인</label>
			<input type="password" id="passwordCK" name="passwordCK" placeholder="Enter your password " required=""/>
			 <p id="pwsame" style="color:red;"></p>
		</div>
		
		
			<!-- 비밀번호 확인 -->
			<script type="text/javascript">
			    function tocheckpw2() {
			        var pw = document.getElementById("password").value;
			        var pwck = document.getElementById("passwordCK").value;
			 
			        if (pw != pwck) {
			            document.getElementById('pwsame').innerHTML = '비밀번호가 틀렸습니다. 다시 입력해 주세요';
			            return false;
			        }else if(pw == pwck){
			        	document.getElementById('pwsame').innerHTML = '비밀번호가 일치합니다.';
				            return true;
			        }else {
			        	document.getElementById('pwsame').innerHTML = '비밀번호를 입력해주세요.';
			            return false;
			        }
			    }
			</script>

			
			<!-- script for show password -->
			<script>
				function myFunction() {
					var x = document.getElementById("myInput");
					if (x.type === "password") {
						x.type = "text";
					} else {
						x.type = "password";
					}
				}
			</script>
			<!-- //script ends here -->
			<div class="w3ls-bot">
				<div class="switch-agileits">
					<label class="switch">
						<input type="checkbox">
						<span class="slider round"></span>
						개인정보 이용 약관 동의
						
					</label>
				</div>
				<div class="form-end">
					<input type="submit" value="회원가입">
				</div>
				<div class="clearfix"></div>
			</div>
		</form>
		
	</div>
	<!-- //form ends here -->
	<!--copyright-->
	<div class="copy-wthree">
		<p>© 2018 Clear Login Form. All Rights Reserved | Design by
			<a href="http://w3layouts.com/" target="_blank">W3layouts</a>
		</p>
	</div>
	<!--//copyright-->
</body>

</html>