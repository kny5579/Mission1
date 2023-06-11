<%@ page import="bookmark.Bookmark_Info" %>
<%@ page import="wifi.Wifi_Info" %>
<%@ page import="wifi.connectDB" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
	String id=request.getParameter("id");
	if(id!=""&&id!=null){
		connectDB deletebookmark=new connectDB();
		deletebookmark.DeleteBookmark(Integer.parseInt(id));
		response.sendRedirect("SeeBookmark.jsp");
	}
%>
</body>
</html>