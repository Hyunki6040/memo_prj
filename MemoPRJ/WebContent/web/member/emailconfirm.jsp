<%@page import="java.io.Console"%>
<%@page import="poly.util.CmmUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id =  CmmUtil.nvl((String)session.getAttribute("id"));
	String temp_id = CmmUtil.nvl((String)session.getAttribute("temp_id"));
	System.out.println(id);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

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
	<h1><img src="/resources/images/logo_loign.png"></h1>
	

	<div class="w3ls-login box box--big">
		<!-- form starts here -->
		

		<form action="<%=request.getContextPath()%>/member/emailProc.do" method="POST">
			<div class="agile-field-txt">
				<label>
					<i class="fa fa-user" aria-hidden="true"></i> 인증번호 입력 </label>
				<input type="text" name="num" placeholder="인증번호 입력.. " required="" />
				<input type="hidden" name="id" value=<%=temp_id%>>
			</div>
			
			<!-- //script ends here -->
			<div class="w3ls-bot">
				<div class="switch-agileits">
					<label class="switch">
						
						<span></span>
						
					</label>
				</div>
				<div class="form-end">
					<input type="submit" value="확인">
					<a href="/">다른 계정으로 로그인</a>
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