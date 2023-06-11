<%@ page import="bookmark.BookmarkGroup_Info" %>
<%@ page import="bookmark.Bookmark_Info" %>
<%@ page import="wifi.Wifi_Info" %>
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
			padding-left: 10px;
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
	String id=request.getParameter("id");
	String distance=request.getParameter("distance");
	connectDB loaddata=new connectDB();
	Wifi_Info info=new Wifi_Info();
	info=loaddata.LoadDetail(id, Double.parseDouble(distance));
%>

<form method="post" action="addBookmark.jsp" accept-charset="UTF-8" onsubmit="return validate();">
  <select name="bookmarkname" id="bookmarkname">
  <option value="none">북마크 그룹 이름 선택</option>
  <%
       connectDB loadgroup=new connectDB();
       ArrayList<BookmarkGroup_Info> list=loadgroup.LoadBookmarkGroup();
       for(BookmarkGroup_Info bookmarkinfo:list){
  %>
    <option value="<%= bookmarkinfo.getId() %>"><%=bookmarkinfo.getName() %></option>
  <% } %>
  </select>
    <input type="hidden" name="id" value="<%= id %>">
    <input type="hidden" name="wifiname" value="<%=info.getX_SWIFI_MAIN_NM()%>">
    <input type="hidden" name="distance" value="<%=info.getDistance() %>">
  <button type="submit" onclick="add();">북마크 추가하기</button>
</form>


<table>
    <tr><th>거리(Km)</th><td><%=info.getDistance()%></td></tr>
    <tr><th>관리번호</th><td><%=info.getX_SWIFI_MGR_NO()%></td></tr>
    <tr><th>자치구</th><td><%=info.getX_SWIFI_WRDOFC()%></td></tr>
    <tr><th>와이파이명</th><td><a href="Detail.jsp?id=<%= info.getX_SWIFI_MGR_NO() %>&distance=<%=info.getDistance()%>"><%=info.getX_SWIFI_MAIN_NM()%></a></td></tr>
    <tr><th>도로명주소</th><td><%=info.getX_SWIFI_ADRES1()%></td></tr>
    <tr><th>상세주소</th><td><%=info.getX_SWIFI_ADRES2()%></td></tr>
    <tr><th>설치위치(층)</th><td><%=info.getX_SWIFI_INSTL_FLOOR()%></td></tr>
    <tr><th>설치유형</th><td><%=info.getX_SWIFI_INSTL_TY()%></td></tr>
    <tr><th>설치기관</th><td><%=info.getX_SWIFI_INSTL_MBY()%></td></tr>
    <tr><th>서비스구분</th><td><%=info.getX_SWIFI_SVC_SE()%></td></tr>
    <tr><th>망종류</th><td><%=info.getX_SWIFI_CMCWR()%></td></tr>
    <tr><th>설치년도</th><td><%=info.getX_SWIFI_CNSTC_YEAR()%></td></tr>
    <tr><th>실내외구분</th><td><%=info.getX_SWIFI_INOUT_DOOR()%></td></tr>
    <tr><th>WIFI접속환경</th><%=info.getX_SWIFI_REMARS3()%><td></td></tr>
    <tr><th>X좌표</th><td><%=info.getLAT()%></td></tr>
    <tr><th>Y좌표</th><td><%=info.getLNT()%></td></tr>
    <tr><th>작업일자</th><td><%=info.getWORK_DTTM()%></td></tr>
</table>

<script>

    function add() {
        var bookmarkname = document.getElementById("bookmarkname").value;

        if(bookmarkname!==null&&bookmarkname!=="none"){
		alert("북마크 정보를 추가하였습니다.");
        }
    }
    
    function validate() {
        var bookmarkname = document.getElementById("bookmarkname").value;
        if (bookmarkname === "none") {
          alert("북마크 그룹을 선택해주세요.");
          return false;
        }
        return true;
      }
    
</script>
</body>
</html>