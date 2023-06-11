<%@ page import="bookmark.BookmarkGroup_Info" %>
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
	String name=request.getParameter("name");
	String order=request.getParameter("order");
	if(name!=""&&order!=""&&id!=""&&name!=null&&order!=null&&id!=null){
		connectDB updategroup=new connectDB();
		updategroup.editBookmarkJoinGroup(name,Integer.parseInt(order),Integer.parseInt(id));
		response.sendRedirect("GroupOfBookmark.jsp");
	}
%>
</body>
</html>