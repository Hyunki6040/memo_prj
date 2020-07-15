<%@page import="java.util.List"%>
<%@page import="org.jsoup.select.Elements"%>
<%@page import="org.jsoup.Jsoup"%>
<%@page import="org.jsoup.nodes.Document"%>
<%@page import="java.util.ArrayList"%>
<%@page import="poly.util.CmmUtil"%>
<%@ page import="poly.keyword.WiseNLUExample"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%
	String id =  CmmUtil.nvl((String)session.getAttribute("id"));
    List list = (List)session.getAttribute("list");
    List label_list = (List)session.getAttribute("label_list");
    System.out.println(id);
	%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<% if("".equals(id)){ %>
	<script>location.href = "/member/login.do"; </script>
<%}else{%>
<head>
		<title>Pinball Website Template | single-page :: w3layouts</title>
		<link href="/resources/css/style.css" rel='stylesheet' type='text/css' />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="shortcut icon" type="image/x-icon" href="/resources/images/fav-icon.png" />
		
		<!-- youtube meta -->
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
		<meta http-equiv="X-UA-Compatible" content="IE=edge" /> 
		
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
	<br/><br/><br/><br/>
	<!-- 메모장 가져오기 -->
	
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

			</div>
				</div>
			</div>
		</div>
		
		<!---//End-header---->
		<!---start-content---->
		
		<div class="content">
			<div class="wrap">
			<div class="single-page">
							<div class="single-page-artical">
								<div class="artical-content">
									<ul>
										<li><jsp:include page="wordcloud_test.jsp" />
									</li>
										<li></li>
										<li><br/><br/><br/><br/><br/><br/><br/><br/></li>
										<li><button type="button" onclick="changeVideoListObjectAndStart2();">다른 영상 불러오기</button><div align="center">
									<div id="player"></div>
								    
								    </div></li>	
									</ul>
									</div>
								   
		  						 <div class="clear"> </div>
							</div>
						</div>
				</div>
		</div>


		
		<script type="text/javascript">																					
		/**
         * Youtube API 로드
         */
        var tag = document.createElement('script');
        tag.src = "http://www.youtube.com/iframe_api";
        var firstScriptTag = document.getElementsByTagName('script')[0];
        firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

        /**
         * onYouTubeIframeAPIReady 함수는 필수로 구현해야 한다.
         * 플레이어 API에 대한 JavaScript 다운로드 완료 시 API가 이 함수 호출한다.
         * 페이지 로드 시 표시할 플레이어 개체를 만들어야 한다.
         */
        var player;
        function onYouTubeIframeAPIReady() {
            player = new YT.Player('player', {
                height: '580',            // <iframe> 태그 지정시 필요없음
                width: '1080',             // <iframe> 태그 지정시 필요없음
                videoId: 'AjWfY7SnMBI',   // <iframe> 태그 지정시 필요없음
                playerVars: {             // <iframe> 태그 지정시 필요없음
                    controls: '2'
                },
                events: {
                    'onReady': onPlayerReady,               // 플레이어 로드가 완료되고 API 호출을 받을 준비가 될 때마다 실행
                    'onStateChange': onPlayerStateChange    // 플레이어의 상태가 변경될 때마다 실행
                }
            });
        }
        function onPlayerReady(event) {
            console.log('onPlayerReady 실행');
        }
        var playerState;
        function onPlayerStateChange(event) {
            playerState = event.data == YT.PlayerState.ENDED ? '종료됨' :
                    event.data == YT.PlayerState.PLAYING ? '재생 중' :
                    event.data == YT.PlayerState.PAUSED ? '일시중지 됨' :
                    event.data == YT.PlayerState.BUFFERING ? '버퍼링 중' :
                    event.data == YT.PlayerState.CUED ? '재생준비 완료됨' :
                    event.data == -1 ? '시작되지 않음' : '예외';

            console.log('onPlayerStateChange 실행: ' + playerState);

            // 재생여부를 통계로 쌓는다.
            collectPlayCount(event.data);
        }
        var played = false;
        function collectPlayCount(data) {
            if (data == YT.PlayerState.PLAYING && played == false) {
                // todo statistics
                played = true;
                console.log('statistics');
            }
        }        
        </script>
        
        <script>
        
        //난수 호출
        function getRandomIntInclusive(min, max) {
		  	return Math.floor(Math.random() * (max - min + 1)) + min;
		}
        //플레이 리스트
        
        var playlist = new Array();
        <%for(int i=0;i<list.size();i++){%>
        playlist[<%=i%>]='<%=list.get(i)%>';
        <%}%>
        
        function changeVideoListObjectAndStart2() {
            player.loadPlaylist({
                'list': playlist[getRandomIntInclusive(0, playlist.length)],
                'listType': 'search',
                'index': 0,
                'startSeconds': 0,
                'suggestedQuality': 'small'
            });
        }
        
        //자동으로 불러옴
        window.onload = function(){
       		changeVideoListObjectAndStart2();
       		changeVideoListObjectAndStart2();
       		changeVideoListObjectAndStart2();
       		changeVideoListObjectAndStart2();
        }
        window.onload = function(){
       		changeVideoListObjectAndStart2();
       		changeVideoListObjectAndStart2();
       		changeVideoListObjectAndStart2();
       		changeVideoListObjectAndStart2();
        }
    </script>

								    
		<!----start-footer--->
		<div class="footer">
			<p>Design by <a href="http://w3layouts.com/">W3layouts</a></p>
		</div>
		<!----//End-footer--->
		<!---//End-wrap---->
	</body>
	<%}%>
</html>
