<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="poly.util.CmmUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <%
	String id =  CmmUtil.nvl((String)session.getAttribute("id"));
    String memo_no =  CmmUtil.nvl((String)session.getAttribute("memo_no"));
    System.out.println(id);
    List label_list = (List)session.getAttribute("label_list");
	%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<% if("".equals(id) || "".equals(memo_no)){ %>
	<script>location.href = "/main/main.do"; </script>
<%}else{%>
<head>
		<title>Pinball Website Template | single-page :: w3layouts</title>
		<link href="/resources/css/style.css" rel='stylesheet' type='text/css' />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="shortcut icon" type="image/x-icon" href="/resources/images/fav-icon.png" />
		
		<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>

		<!----webfonts---->
		<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'>
		<!----//webfonts---->
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
        <!----//End-dropdown--->
		<!---//End-click-drop-down-menu----->
         
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
										<li><a href="/main/video_rec1.do"><span>영상 추천</span></a></li>
										<li><a href="/admin/contact.do"><span>문의</span></a></li>
										<li><a href="/main/trash.do"><span>휴지통</span></a></li>
										<div class="clear"> </div>
									</ul>
								</div>
								<a class="boxclose" id="boxclose"> <span> </span></a>
							</div>                                  
						</div> 	
					</div> 
				</div>       	  
				<div class="top-searchbar">
					<form action="<%=request.getContextPath()%>/main/main.do" method="post">
						<input type="text" name="search" /><input type="submit" value="" />
					</form>
				</div>
				<div class="userinfo">
					<div class="user">
						<ul>
							<li><a href="/member/personal_page.do"><img src="/resources/images/user-pic.png" title="user-name" /><span><%=id%></span></a></li>
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
				<br/>
				<input type="text" name="new_label" placeholder="새로운 라벨..">
				<select name="label">
					<option value=''selected>-- 선택 --</option>
					<c:forEach var="label" items="${label_list}" varStatus="status">
			        <option value="${label}">${label.memo_class}</option>
			        </c:forEach>
				</select>
			</form>
			</div>
				</div>
			</div>
		</div>
		
		<!---//End-header---->
		<!---start-content---->
		<br/><br/><br/><br/><br/><br/>
		<div class="content">
			<div class="wrap">
			<div class="single-page">
							<div class="single-page-artical">
								<div class="artical-content">
									<ul>
										<c:forEach var="result" items="${list}" varStatus="status">
			        					<li>
									<h3>${result.contents}</h3>
									</li>
									<li><br/><br/><br/></li>
									<li><h4>label. ${result.memo_class}</h4></li>
									<li><h4>date. ${result.reg_dt}</h4></li>
									<li><button type="button" onclick="location.href='/memo/memo_modi.do?memo_no=${result.memo_no}' ">수정</button>&nbsp;&nbsp;<button type="button" onclick="location.href='/memo/delete.do' ">삭제</button></li>
							         </c:forEach>
									</ul>
									</div>
								   
		  						 <div class="clear"> </div>
							</div>
						</div>
				</div>
		</div>
        
								    
		<!----start-footer--->
		<div class="footer">
			<p>Design by <a href="http://w3layouts.com/">W3layouts</a></p>
		</div>
		<!----//End-footer--->
		<!---//End-wrap---->
	</body>
	<%}%>
</html>
