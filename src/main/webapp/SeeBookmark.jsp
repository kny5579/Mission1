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
    <style>
		table{
			width:100%;
			border-collapse: collapse;
			
		}
		tr:nth-child(even){background-color: #f2f2f2;}
		tr:hover {background-color: #ddd;}
		th{
			border:solid 1px lightgray;
			background-color:#04AA6D;
			font-size:13px;
			padding-top: 11px;
    		padding-bottom: 11px;
			color:white;
		}
		td{
			border:solid 1px lightgray;
			text-align: center;
			font-size:13px;
			padding-top: 11px;
    		padding-bottom: 11px;
			font-weight:bold;
		}
		a {
		font-size: 14px;
		}
	</style>
</head>
<body>
<h2>북마크 목록</h2>
<a href="First.jsp">홈</a> | 
<a href="History.jsp">위치 히스토리 목록</a> | 
<a href="API.jsp">Open API 와이파이 정보 가져오기</a> | 
<a href="SeeBookmark.jsp">북마크 보기</a> | 
<a href="GroupOfBookmark.jsp">북마크 그룹 관리</a><br><br>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>북마크 이름</th>
        <th>와이파이명</th>
        <th>등록일자</th>
        <th>비고</th>
    </tr>
    </thead>
    <tbody>
    <!-- 상세정보 페이지로 이동하기 위해서는 id, lat, lnt가 필요한데.. detail에서 id,lat,lnt를 가져와서 db에 저장시키기? -->
        <%
        	connectDB bookmarkload=new connectDB();
        	ArrayList<Bookmark_Info> list=bookmarkload.LoadBookmark();
        	for(int i=0;i<list.size();i++){
        %>
        <tr>
        <td><%= list.get(i).getId() %></td>
        <td><%= list.get(i).getBookmarkname() %></td>
        <td><a href="Detail.jsp?id=<%= list.get(i).getDetail_id() %>&distance=<%=list.get(i).getDistance() %>"><%= list.get(i).getWifiname() %></a></td>
        <td><%= list.get(i).getRegistertime() %></td>
        <td align="center"><a href="deleteBookmark.jsp?id=<%=list.get(i).getId()%>&Bookmarkname=<%= list.get(i).getBookmarkname() %>&Wifiname=<%= list.get(i).getWifiname() %>&time=<%= list.get(i).getRegistertime() %>">삭제</a></td>
    </tr>
    <% } %>
        
    </tbody>
</table>
</body>
</html>