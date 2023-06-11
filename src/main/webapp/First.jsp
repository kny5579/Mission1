<%@ page import="his.history_Info" %>
<%@ page import="wifi.connectDB" %>
<%@ page import="java.util.List" %>
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
			font-size:13px;
			font-weight:bold;
		}
		a {
		font-size: 14px;
		}
		
	</style>
</head>

<body>
<h2>와이파이 정보 구하기</h2>
<a href="First.jsp">홈</a> | 
<a href="History.jsp">위치 히스토리 목록</a> | 
<a href="API.jsp">Open API 와이파이 정보 가져오기</a> | 
<a href="SeeBookmark.jsp">북마크 보기</a> | 
<a href="GroupOfBookmark.jsp">북마크 그룹 관리</a><br><br>

<form action="List.jsp" method="post">
    <label>LAT: </label>
    <input type="text" id="lat" name="lat" value="0.0">
    <label>, LNT: </label>
    <input type="text" id="lnt" name="lnt" value="0.0">
    <button onclick="myLocation(); return false;">내 위치 가져오기</button>
    <input type="submit" value="근처 WIFI 정보 보기"><br><br>
</form>

<table>
    <thead>
    <tr>
        <th>거리(Km)</th>
        <th>관리번호</th>
        <th>자치구</th>
        <th>와이파이명</th>
        <th>도로명주소</th>
        <th>상세주소</th>
        <th>설치위치(층)</th>
        <th>설치유형</th>
        <th>설치기관</th>
        <th>서비스구분</th>
        <th>망종류</th>
        <th>설치년도</th>
        <th>실내외구분</th>
        <th>WIFI접속환경</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>작업일자</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td colspan="17"><br>위치 정보를 입력한 후에 조회해 주세요.<br><br></td>
    </tr>
    </tbody>
</table>

<script>
    function myLocation() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function (position) {
                    var latitude = position.coords.latitude;
                    var longitude = position.coords.longitude;

                    document.getElementById("lat").value = latitude;
                    document.getElementById("lnt").value = longitude;
                }
            )
        } else {
            alert("위치 확인 오류 발생")
        }
    }

</script>
</body>
</html>