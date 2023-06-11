<%@ page import="his.history_Info" %>
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
<h2>와이파이 정보 구하기</h2>
<a href="First.jsp">홈</a> | 
<a href="History.jsp">위치 히스토리 목록</a> | 
<a href="API.jsp">Open API 와이파이 정보 가져오기</a> | 
<a href="SeeBookmark.jsp">북마크 보기</a> | 
<a href="GroupOfBookmark.jsp">북마크 그룹 관리</a><br><br>
<%
	request.setCharacterEncoding("euc-kr");
	String lat=request.getParameter("lat");
	String lnt=request.getParameter("lnt");
	if(lat!="0.0"&&lnt!="0.0"&&lat!=null&&lnt!=null){
		connectDB inserthistory=new connectDB();
		history_Info hisinfo=new history_Info();
		hisinfo.setX(lat);
		hisinfo.setY(lnt);
		inserthistory.InsertHistory(hisinfo);
	}
%>
<form action="List.jsp" method="post">
    <label>LAT: </label>
    <input type="text" id="lat" name="lat" value="<%=lat%>">
    <label>, LNT: </label>
    <input type="text" id="lnt" name="lnt" value="<%=lnt%>">
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
       <%
        	connectDB loaddata=new connectDB();
        	ArrayList<Wifi_Info> list=loaddata.LoadData(Double.parseDouble(lat),Double.parseDouble(lnt));
        	for(int i=0;i<list.size();i++){
        %>
        <tr>
        <td><%= list.get(i).getDistance() %></td>
        <td><%= list.get(i).getX_SWIFI_MGR_NO() %></td>
        <td><%= list.get(i).getX_SWIFI_WRDOFC() %></td>
        <td><a href="Detail.jsp?id=<%= list.get(i).getX_SWIFI_MGR_NO() %>&distance=<%=list.get(i).getDistance() %>"><%= list.get(i).getX_SWIFI_MAIN_NM() %></a></td>
        <td><%= list.get(i).getX_SWIFI_ADRES1() %></td>
        <td><%= list.get(i).getX_SWIFI_ADRES2() %></td>
        <td><%= list.get(i).getX_SWIFI_INSTL_FLOOR() %></td>
        <td><%= list.get(i).getX_SWIFI_INSTL_TY() %></td>
        <td><%= list.get(i).getX_SWIFI_INSTL_MBY() %></td>
        <td><%= list.get(i).getX_SWIFI_SVC_SE() %></td>
        <td><%= list.get(i).getX_SWIFI_CMCWR() %></td>
        <td><%= list.get(i).getX_SWIFI_CNSTC_YEAR() %></td>
        <td><%= list.get(i).getX_SWIFI_INOUT_DOOR() %></td>
        <td><%= list.get(i).getX_SWIFI_REMARS3() %></td>
        <td><%= list.get(i).getLAT() %></td>
        <td><%= list.get(i).getLNT() %></td>
        <td><%= list.get(i).getWORK_DTTM() %></td>
        
    </tr>
    <% } %>
    </tbody>
</table>

<%-- <h1><%= lat %>=lat / <%= lnt %>=lnt</h1> --%>


<script>
    function myLocation() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function (position) {
                    var latitude = position.coords.latitude;
                    var longitude = position.coords.longitude;

                    document.getElementById("lat").value = latitude;
                    document.getElementById("lnt").value = longitude;
                    console.log("lat : " + latitude);
                    console.log("lnt : " + longitude);
                }
            )
        } else {
            alert("위치 확인 오류 발생")
        }
    }

</script>
</body>
</html>