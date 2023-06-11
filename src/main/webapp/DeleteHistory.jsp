<%@ page import="wifi.connectDB" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("id");
	connectDB delhistory=new connectDB();
	delhistory.DeleteHistory(id);
	response.sendRedirect("History.jsp");
%>