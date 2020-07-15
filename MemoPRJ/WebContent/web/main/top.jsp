<%@page import="poly.util.CmmUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	String id =  CmmUtil.nvl((String)session.getAttribute("id"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>	
<% if("".equals(id)){ %>
	<script>location.href = "/member/login.do"; </script>
<%}else{%>
		
		
	<head>
		<title>Pinball Website Template | Home :: w3layouts</title>
		<link href="/resources/css/style.css" rel='stylesheet' type='text/css' />
		<meta name="viewport" content="width=device-width, initial-scale=1" charset="utf-8">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="shortcut icon" type="image/x-icon" href="/resources/images/fav-icon.png" />
		<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
		</script>
		<!----webfonts---->
		<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'>
		<!----//webfonts---->
		<!-- Global CSS for the page and tiles -->
  		<link rel="stylesheet" href="/resources/css/main.css">
  		<!-- //Global CSS for the page and tiles -->
  		
  		<!-- 메모 스크립트 -->
  		<link href="/resources/css/font-awesome.css" rel="stylesheet">
		<link href="/resources/css/style_memo.css" rel='stylesheet' type='text/css' />
		 
  		
		<!---start-click-drop-down-menu----->
		<script src="/resources/js/jquery.min.js"></script>
        <!----start-dropdown--->
         <script type="text/javascript">
         
			var $ = jQuery.noConflict();
				$(function() {
					$('#activator').click(function(){
						$('#box').animate({'top':'0px'},500);
					});
					$('#boxclose').click(function(){
					$('#box').animate({'top':'-700px'},500);
					});
				});
				$(document).ready(function(){
				//Hide (Collapse) the toggle containers on load
				$(".toggle_container").hide(); 
				//Switch the "Open" and "Close" state per click then slide up/down (depending on open/close state)
				$(".trigger").click(function(){
					$(this).toggleClass("active").next().slideToggle("slow");
						return false; //Prevent the browser jump to the link anchor
				});					
				});
		</script>
		
		<!-- input text 자동 크기 -->
		<style>
		textarea.autosize { min-height: 50px; }
		</style>
        <!----//End-dropdown--->
		<!---//End-click-drop-down-menu----->
	</head>
	<body>
	
	<!-- input text 자동 크기 -->
	<script>
	function resize(obj) {
	  obj.style.height = "1px";
	  obj.style.height = (12+obj.scrollHeight)+"px";
	}
	
	function emulAcceptCharset(form) {
	    if (form.canHaveHTML) { // detect IE
	        document.charset = form.acceptCharset;
	    }
	    return true;
	}
	</script>
	
		<!---start-header---->
			<div class="header">
				<div class="wrap">
				<div class="logo">
					<a href="/main/main.do"><img src="/resources/images/logo.png" title="pinbal" /></a>
				</div>
				<div class="nav-icon">
					 <a href="#" class="right_bt" id="activator"><span> </span> </a>
				</div>
				 <div class="box" id="box">
					 <div class="box_content">        					                         
						<div class="box_content_center">
						 	<div class="form_content">
								<div class="menu_box_list">
									<ul>
										<li><a href="/main/main.do"><span>메인</span></a></li>
										<li><a href="/main/video_rec.do"><span>영상 추천</span></a></li>
										<li><a href="contact.html"><span>문의</span></a></li>
										<div class="clear"> </div>
									</ul>
								</div>
								<a class="boxclose" id="boxclose"> <span> </span></a>
							</div>                                  
						</div> 	
					</div> 
				</div>       	  
				<div class="top-searchbar">
					<form action="<%=request.getContextPath()%>/memo/memoRecProc" method="post">
						<input type="text" /><input type="submit" value="" />
					</form>
				</div>
				<div class="userinfo">
					<div class="user">
						<ul>
							<li><a href="#"><img src="/resources/images/user-pic.png" title="user-name" /><span><%=id%></span></a></li>
							<%if(id != ""){%>
							<li><a href="/member/logout.do">로그아웃</a>
							<%}%>
						</ul>
					</div>		
				</div>
				<div class="clear"> </div>
				<div class="memo" align="center">
			<div class="agile-field-txt">
			<form action="/memo/memoRegProc.do" method="post" accept-charset="UTF-8" onsubmit="emulAcceptCharset(this)">
				<textarea name="contents" class="autosize" rows="5" cols="70" onkeydown="resize(this)" onkeyup="resize(this)" id="contents" placeholder="메모..." required="" onKeypress="auto_size(this,this.size)"></textarea>
				<input type="hidden" name="id" value=<%=id%>>
				<input type="submit" value="저장">
			</form>
			</div>
				</div>
			</div>
		</div>
		<%}%>
		<!---//End-header---->
</body>
</html>