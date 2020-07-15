<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
var aTotalJson = new Array();
var aSapArray = new Array();
var aJson = new Object();
 
aJson.korName = "삽잡이";
aJson.engName = "shovelMan";
 
aSapArray.push(aJson);
 
var aZapArray = new Array();
aJson = new Object();
alert(JSON.stringify(aSapArray));
aJson.korName = "김모군";
aJson.engName = "Kim";
 
aZapArray.push(aJson);



</script>
</body>
</html>