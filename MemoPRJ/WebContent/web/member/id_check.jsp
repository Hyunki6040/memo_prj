<%@page import="poly.util.CmmUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<head>
	<title>아이디 중복 확인</title>
	<!-- Meta-Tags -->
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="utf-8">
	<meta name="keywords" content="Clear login Form a Responsive Web Template, Bootstrap Web Templates, Flat Web Templates, Android Compatible Web Template, Smartphone Compatible Web Template, Free Webdesigns for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design">
	
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
	
	
	<div class="w3ls-login box box--big">
		<!-- form starts here -->
		<form action="/member/idCheck.do" onsubmit="return tocheckpw2()" data-ajax="false" method="post">
		<div class="agile-field-txt">
			<label>
				<i class="fa fa-user" aria-hidden="true"></i> 이메일 </label>
			<input type="text" name="id" placeholder="Enter your email " required="" />
		</div>
					
			<!-- //script ends here -->
			<div class="w3ls-bot">
				<div class="switch-agileits">
					<label class="switch">
						<input type="checkbox">
						<span class="slider round"></span>
					</label>
				</div>
				<div class="form-end">
					<input type="submit" value="중복 확인">
				</div>
				<div class="clearfix"></div>
			</div>
		</form>
		
	</div>
	<!-- //form ends here -->
	<!--copyright-->
	<div class="copy-wthree">
		
	</div>
	<!--//copyright-->
</body>

</html>