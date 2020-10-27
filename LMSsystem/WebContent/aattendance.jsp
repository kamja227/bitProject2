<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.text.SimpleDateFormat, java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>출석 관리 페이지</title>
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Noto+Sans+KR:wght@100;500&display=swap" rel="stylesheet"/>
    <link href="../css/atemplate.css" rel="stylesheet" type="text/css"/>
    <link href="../css/butan.css" rel="stylesheet" type="text/css"/>
    <style rel="stylesheet" type="text/css">
    	 h2{
    		text-align: center;
    		margin-top: 20px;
    	} 

    </style>
	<script type="text/javascript" src="../js/jquery-1.12.4.js"></script>
	<script type="text/javascript" src="../js/menu.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			
		});
	</script>
</head>
<body>
<%@ include file="../template/adminheader.jspf" %>
<%@ include file="../template/adminmenu.jspf" %>
	<div id="content">
	<h2>출석 관리 페이지</h2> <!-- 반 선택 -->
	<%
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Date time = new Date();
		String today = format1.format(time);
		
		pageContext.setAttribute("today", today);
	%>
		<!--content start  -->
		<table class="table" style="width: 90%;">
			<tr clas="title row">
				<th class="thd">출석데이터를 확인 하실 교육과정/반을 선택해주세요.</th>
			</tr>
			<c:forEach items="${list }" var="bean">
			<c:url value="aattendanceclass.bit" var="detail">
				<c:param name="classIdx" value="${bean.classIdx }"/>
				<c:param name="classTitle" value="${bean.classTitle }"/>
				<c:param name="today" value="${today }"/>
			</c:url>
			<tr class="title row">
				<td class="tsub" style="width: 30px;"><a href="${detail }" class="an">${bean.classTitle}</a></td>
			</tr>
		</c:forEach>
		</table>
		
		<!--content end  -->
	</div>
<%@ include file="../template/adminfooter.jspf" %>
</body>
</html>