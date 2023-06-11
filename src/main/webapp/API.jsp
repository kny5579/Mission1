<%@ page import="wifi.connectDB" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>와이파이 정보 구하기</title>
	<style>
		.ment{
			text-align:center;
		}
		a{
			font-size:15px;
		}
	</style>
</head>
<body>
<%
	connectDB insertapi=new connectDB();
	int totalcnt=insertapi.InsertAPI();
%>	
<div class="ment">
	<h2><%=totalcnt%>개의 WIFI 정보를 정상적으로 저장하였습니다.</h2>
	<a href="First.jsp">홈으로 가기</a>
</div>
</body>
</html>