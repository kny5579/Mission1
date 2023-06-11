<%@ page import="bookmark.BookmarkGroup_Info" %>
<%@ page import="wifi.connectDB" %>
<%@ page import="java.util.ArrayList" %>
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
<h2>북마크 그룹 삭제</h2>
<a href="First.jsp">홈</a> | 
<a href="History.jsp">위치 히스토리 목록</a> | 
<a href="API.jsp">Open API 와이파이 정보 가져오기</a> | 
<a href="SeeBookmark.jsp">북마크 보기</a> | 
<a href="GroupOfBookmark.jsp">북마크 그룹 관리</a><br><br>
<p>북마크 그룹 이름을 삭제하시겠습니까?</p>

<%
	request.setCharacterEncoding("utf-8");
	String id=request.getParameter("id");
	String name=request.getParameter("name");
	String order=request.getParameter("order");
%>
<form method="post" action="GroupOfBookmark_deleteEnd.jsp" accept-charset="UTF-8">
	<table>
	<tr>
		<th>북마크 이름</th><td>&nbsp;<input type="text" id="name" name="name" value="<%= name %>"readonly></td>
	</tr>
	<tr>
		<th>순서</th><td>&nbsp;<input type="text" id="order" name="order" value="<%= order %>"readonly></td>
	</tr>
	<tr>
		<td align="center" colspan="2">
		<input type="hidden" name="id" value="<%= id %>">
		<a href="#" onclick="goBack();">돌아가기</a> | <button onclick="remove();">삭제</button></td>
	</tr>
	
	</table>
</form>
<script>
	function goBack() {
	  window.history.go(-1);
	}
	
	function remove() {
        var name = document.getElementById("name").value;
        var order = document.getElementById("order").value;
        if(name!=null&&order!=null){
		alert("북마크 그룹 정보를 삭제하였습니다.");
        }
    }
</script>
</body>
</html>