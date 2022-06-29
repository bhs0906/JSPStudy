<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:set var="now" value="<%= new java.util.Date()%>"></c:set>
<pre>
톰캣 서버의 기본 로케일 : <%= response.getLocale() %>
		  
<fmt:setLocale value="ko_kr"></fmt:setLocale>
로케일을 한국어로 설정 후 로케일 확인 : <%= response.getLocale() %>
통화(currency)  : <fmt:formatNumber value="10000" type="currency"></fmt:formatNumber>
날짜            : <fmt:formatDate value="${now}"></fmt:formatDate>

<fmt:setLocale value="ja_JP"></fmt:setLocale>
로케일을 일본어로 설정 후 로케일 확인 : <%= response.getLocale() %>
통화(currency)  : <fmt:formatNumber value="10000" type="currency"></fmt:formatNumber>
날짜            : <fmt:formatDate value="${now}"></fmt:formatDate>

<fmt:setLocale value="en_US"></fmt:setLocale>
로케일을 영어로 설정 후 로케일 확인 : <%= response.getLocale() %>
통화(currency)  : <fmt:formatNumber value="10000" type="currency"></fmt:formatNumber>
날짜            : <fmt:formatDate value="${now}"></fmt:formatDate>

</pre>
</body>
</html>