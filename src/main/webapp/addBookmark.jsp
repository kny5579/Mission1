<%@ page import="java.util.HashSet" %>
<%@ page import="bookmark.BookmarkGroup_Info" %>
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
	String group_id=request.getParameter("bookmarkname");
	String wifiname=request.getParameter("wifiname");
	String distance=request.getParameter("distance");
	String detail_id=request.getParameter("id");
	if(group_id!=null&&distance!=null&&detail_id!=null&&wifiname!=null){
		connectDB insertbookmark=new connectDB();
		Bookmark_Info bookmarkinfo=new Bookmark_Info();
		bookmarkinfo.setGroup_id(Integer.parseInt(group_id));
		bookmarkinfo.setWifiname(wifiname);
		bookmarkinfo.setDetail_id(detail_id);
		bookmarkinfo.setDistance(Double.parseDouble(distance));
		insertbookmark.InsertBookmark(bookmarkinfo);
		response.sendRedirect("SeeBookmark.jsp");
	}
%>
</body>
</html>