<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
</head>
<body>
 <H2>secondpage.jsp</H2>
하나의 페이지 속성 : <%= pageContext.getAttribute("name") %> <br>
하나의 요청 속성 : <%= request.getAttribute("name") %> <br>
하나의 세션 속성 : <%= session.getAttribute("name") %> <br>
하나의 요청 속성 : <%= application.getAttribute("name") %> <br>

<a href="P210_thirdpage.jsp"> 또 다른 페이지</a>
</body>
</html>