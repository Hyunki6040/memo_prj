<%@page import="poly.util.CmmUtil"%>
<%@page import="org.springframework.ui.Model"%>
<%@page import="javafx.scene.control.Alert"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<% 
	String url = CmmUtil.nvl((String)request.getAttribute("url"));
	String contents = CmmUtil.nvl((String)request.getAttribute("contents"));
	String user_id = CmmUtil.nvl((String)request.getAttribute("user_id"));
%>
<script type="text/javascript">
	window.location.href = '<%=url%>'
</script>

</head>
<body>
	
</body>
</html>