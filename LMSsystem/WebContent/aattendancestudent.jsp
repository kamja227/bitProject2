<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.bit.model.dto.AttendanceDto, java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>학생별 출석 관리 페이지</title>
<link rel="stylesheet" type="text/css" href="../css/butan.css"/>
<link rel="stylesheet" type="text/css" href="../css/atemplate.css"/>
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Noto+Sans+KR:wght@100;500&display=swap" rel="stylesheet"/>
<style rel="stylesheet" type="text/css">
	
	#content{
		margin-top:20px;
		text-align: center;
		width: 1000px;
	}
	
	#etc{
		padding:0px;
		height: 50px;
		
	}
	input{
		margin-top:10px;
	}
	
	
</style>
<script type="text/javascript" src="../js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="../js/buttonjs.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#back').on('click',function(){
		window.history.back();
	});
});
</script>
</head>

<body>
<%@ include file="../template/adminheader.jspf" %>
<%@ include file="../template/adminmenu.jspf" %>

	<%
		String classTitle = (String)request.getAttribute("classTitle");
		ArrayList<AttendanceDto> list = (ArrayList<AttendanceDto>)request.getAttribute("list");

	%>

<div id="content">
<!-- content start -->
	<h2>학생별 출석 관리 페이지</h2>
	<h3>${classTitle } - ${studentName }</h3>
	<c:if test="${attendRate >= 80.0 }"><span style="color: blue;">출석률: ${attendRate }</span></c:if>
	<c:if test="${attendRate < 80.0 }"><span style="color: red;">출석률: ${attendRate }</span></c:if>
	<table class="table" style="width: 90%;">
	 <tr class="title row">
	 	<th class="thd">날짜</th>
	 	<th class="thd">출석상태</th>
	 </tr>
	 	<c:forEach items="${list }" var="bean">
	 	<c:url value="astudentdetail.bit" var="student">
	 	<c:param name="studentIdx" value="${bean.studentIdx }"/>
	 	</c:url>
		<tr class="row">
			<td class="tsub"><a href="${student }" class="an">${bean.attendDate }</a></td>
			<td class="tsub"><a href="${student }" class="an">${bean.attendanceStatus }</a></td>
		</tr>
		</c:forEach>
	</table>
	
	 <div class="bt">
	 	<button type="button" class="buttn" id="back" style="cursor: pointer;">뒤로</button>
	 		<c:url value="aattendanceedit.bit" var="edit">
			<c:param name="studentIdx" value="${bean.studentIdx }"/>
			</c:url>
	 	<button type="button" class="buttn"><a class="an" href="${edit }">수정</a></button>
	 </div>
<!-- content end -->
</div>

<%@ include file="../template/adminfooter.jspf" %>
</body>
</html>