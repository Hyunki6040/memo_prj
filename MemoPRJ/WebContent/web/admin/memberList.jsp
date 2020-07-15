<%@page import="poly.dto.MemberDTO"%>
<%@page import="java.util.List"%>
<%@taglib prefix= "c" uri= "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	List<MemberDTO> mList = (List<MemberDTO>)request.getAttribute("mList");
%>
<html>
	<head>
		<title>Pinball Website Template | single-page :: w3layouts</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<link href="/resources/css/style.css" rel='stylesheet' type='text/css' />
		<meta name="viewport" content="width=device-width, initial-scale=1" charset="utf-8">
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
		<!-- 리스트 검색 -->
		<script>
			$(function(){
				
				$('#btn').click(function(){
					var search = $('#search').val();
					$.ajax({
						url : '/member/searchList.do',
						method : 'post',
						data : {'search': search},
						success : function(data){
							console.log(data);
							if(data.length > 0){
								$('#list').html('');
								var content = '';
								content += '<table style="width:100%">';
								content += '<tr bgcolor="#00D8FF">';
								content += '<td width="100">회원번호</td>';
								content += '<td width="100">id</td>';
								content += '<td width="100">이름</td>';
								content += '<td width="100">권한</td>';
								content += '<td width="100">등록일</td>';
								content += '</tr>';
								$.each(data, function(key, value){
									content += '<tr>';
									content += '<td>'+value.member_id+'</td>';
									content += '<td>'+value.id+'</td>';
									content += '<td>'+value.member_name+'</td>';
									content += '<td>'+value.auth+'</td>';
									content += '<td>'+value.regday+'</td>';
									content += '</tr>';
								});
								content += '</table>';
								
								$('#list').html(content);
		
							}else{
								alert('검색결과가 없습니다.');
							}
							
						}
					});
				})
				
				$('#r').click(function(){
					$.ajax({
						url : '/member/rList.do',
						method : 'post',
						success : function(data){
							console.log(data);
							if(data.length > 0){
								$('#list').html('');
								var content = '';
								content += '<table style="width:100%">';
								content += '<tr bgcolor="#00D8FF">';
								content += '<td width="100">회원번호</td>';
								content += '<td width="100">id</td>';
								content += '<td width="100">이름</td>';
								content += '<td width="100">권한</td>';
								content += '<td width="100">등록일</td>';
								content += '</tr>';
								$.each(data, function(key, value){
									content += '<tr>';
									content += '<td>'+value.member_id+'</td>';
									content += '<td>'+value.id+'</td>';
									content += '<td>'+value.member_name+'</td>';
									content += '<td>'+value.auth+'</td>';
									content += '<td>'+value.regday+'</td>';
									content += '</tr>';
								});
								content += '</table>';
								
								$('#list').html(content);
		
							}
						}
					});
				})
		
			})
		
		</script>
		
        <!----//End-dropdown--->
	</head>
	<body>
		<!---start-wrap---->
		<%@include file ="../main/top.jsp" %>
		<!---start-content---->
		<div class="content">
			<div class="wrap">
			<div class="single-page">
							<table style="height: 100%; width: 100%">
		<tr height="7%" bgcolor="#00D8FF">
			<td>
			</td>
		</tr>
		<tr bgcolor="">
			<td>
				<div id="list">
				<table style="width:100%">
					<tr bgcolor="#00D8FF">
						<td width="100">회원 번호</td>
						<td width="100">id</td>
						<td width="100">이름</td>
						<td width="100">권한</td>
						<td width="100">등록일</td>
					</tr>
					
					<c:forEach var="member" items="${mList}">
						<tr>
							<td>${member.member_id}</td>
							<td>${member.id}</td>
							<td>
								<a href="/member/memberDetail.do?memberId=${member.member_id}">
									${member.member_name}
								</a>
							</td>
							<td>${member.auth}</td>
							<td>${member.regday}</td>
						</tr>
					</c:forEach>
					
					
				</table>
				</div>
				<input type="text" id="search"><input type="button" id="btn" value="검색">
				<br/>
				<input type="button" id="r" value="되돌리기">
			</td>
		</tr>
		<tr height="7%" bgcolor="#00000F">
			<td>
			</td>
		</tr>
	</table>
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
</html>