<%@ page import="bookmark.BookmarkGroup_Info" %>
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
<h2>북마크 그룹</h2>
<a href="First.jsp">홈</a> | 
<a href="History.jsp">위치 히스토리 목록</a> | 
<a href="API.jsp">Open API 와이파이 정보 가져오기</a> | 
<a href="SeeBookmark.jsp">북마크 보기</a> | 
<a href="GroupOfBookmark.jsp">북마크 그룹 관리</a><br><br>
<button onclick="nameAdd();">북마크 그룹 이름 추가</button>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>북마크 이름</th>
        <th>순서</th>
        <th>등록일자</th>
        <th>수정일자</th>
        <th>비고</th>
    </tr>
    </thead>
    <tbody>
    <%
        	connectDB loadgroup=new connectDB();
        	ArrayList<BookmarkGroup_Info> list=loadgroup.LoadBookmarkGroup();
        	for(int i=0;i<list.size();i++){
        %>
        <tr>
        <td><%= list.get(i).getId() %></td>
        <td><%= list.get(i).getName() %></td>
        <td><%= list.get(i).getOrder() %></td>
        <td><%= list.get(i).getRegisterTime() %></td>
        <td><%= list.get(i).getEditTime()==null?" ":list.get(i).getEditTime()%></td>
        <td><a href="GroupOfBookmark_edit.jsp?id=<%=list.get(i).getId()%>&name=<%= list.get(i).getName() %>&order=<%= list.get(i).getOrder() %>">수정</a>
        	<a href="GroupOfBookmark_delete.jsp?id=<%=list.get(i).getId()%>&name=<%= list.get(i).getName() %>&order=<%= list.get(i).getOrder() %>">삭제</a></td>
    </tr>
    <% } %>
    </tbody>
</table>

<script>
	function nameAdd(){
		window.location.href="GroupOfBookmark_add.jsp";
	}
</script>

</body>
</html>