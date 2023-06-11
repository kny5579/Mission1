<%@ page import="his.history_Info" %>
<%@ page import="wifi.connectDB" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
			padding-top: 7px;
    		padding-bottom: 7px;
			font-size:13px;
			font-weight:bold;
		}
		a {
		font-size: 14px;
		}
		
	</style>
</head>
<body>
<h2>위치 히스토리 목록</h2>
<a href="First.jsp">홈</a> | 
<a href="History.jsp">위치 히스토리 목록</a> | 
<a href="API.jsp">Open API 와이파이 정보 가져오기</a> | 
<a href="SeeBookmark.jsp">북마크 보기</a> | 
<a href="GroupOfBookmark.jsp">북마크 그룹 관리</a><br><br>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>조회일자</th>
        <th>비고</th>
    </tr>
    </thead>
    <!--  수정 시작 -->
    
    <tbody>
        <%
        	connectDB historyload=new connectDB();
        	ArrayList<history_Info> list=historyload.LoadHistory();
        	for(history_Info info:list){
        %>
        <tr>
        <td><%= info.getId() %></td>
        <td><%= info.getX() %></td>
        <td><%= info.getY() %></td>
        <td><%= info.getSearchTime() %></td>
        <td align="center">
        <form method="post" action="DeleteHistory.jsp" onsubmit="return confirm('삭제하시겠습니까?');">
	        <input type="hidden" name="id" value="<%=info.getId()%>">
	        <button type="submit">삭제</button>
        </form>
        </td>
	    </tr>
	    <% } %>
        
    </tbody>
</table>
</body>
</html>