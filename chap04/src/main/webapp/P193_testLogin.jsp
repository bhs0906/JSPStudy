
<%@page import="java.net.URLEncoder" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String id="bg";
String pwd="1234";
String name="bg";
request.setCharacterEncoding("UTF-8");
if(id.equals(request.getParameter("id")) &&
		pwd.equals(request.getParameter("pwd"))) {
	response.sendRedirect("P193_main.jsp?name=" +
		URLEncoder.encode(name, "UTF-8"));
}
else{
	response.sendRedirect("P193_login.jsp");
}
%>
