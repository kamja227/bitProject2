<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.bit.model.dto.AttendanceDto, java.util.ArrayList, java.text.SimpleDateFormat, java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>반별 출석 관리 페이지</title>
<link rel="stylesheet" type="text/css" href="../css/butan.css"/>
<link rel="stylesheet" type="text/css" href="../css/atemplate.css"/>
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Noto+Sans+KR:wght@100;500&display=swap" rel="stylesheet"/>
<style rel="stylesheet" type="text/css">
	#content{
		margin-top: 20px;
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
	$('#date').change(function(){
		var classIdx = getUrlParameter('classIdx');
		var classTitle = getUrlParameter('classTitle');
		var date = $(this).val();
		
		 location.href="http://localhost:8080/LMSsystem/admin/aattendanceclass.bit?classIdx=" + classIdx + 
				 "&classTitle=" + classTitle + "&today=" + date;
	});
});

var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = window.location.search.substring(1),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
        }
    }
};


</script>
</head>

<body>
<%@ include file="../template/adminheader.jspf" %>
<%@ include file="../template/adminmenu.jspf" %>

	<%
		String classTitle = (String)request.getAttribute("classTitle");
		ArrayList<AttendanceDto> list = (ArrayList<AttendanceDto>)request.getAttribute("list");
	//	System.out.println(list);
		
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Date time = new Date();
		String now = format1.format(time);

		pageContext.setAttribute("now", now);
	%>

<div id="content">
<!-- content start -->
	<h2>반별 출석 관리 페이지 - ${classTitle }</h2>

	<input type="date" value="${today }" min="2019-01-01" max="${now }" id="date">
	<table class="table" style="width: 90%;">
	
	 <tr class="title row">
	 	<th class="thd">날짜</th>
	 	<th class="thd">이름</th>
	 	<th class="thd">출석상태</th>
	 	<th class="thd">출석률</th>
	 </tr>
	 	<c:forEach items="${list }" var="bean">
	 	<c:url value="aattendancestudent.bit" var="student">	 	
	 	<c:param name="classTitle" value="${classTitle }"/>
	 	<c:param name="studentIdx" value="${bean.studentIdx }"/>
	 	<c:param name="studentName" value="${bean.studentName }"/>
	 	</c:url>
		<tr class="row">
			<td class="tsub"><a href="${student }" class="an">${bean.attendDate }</a></td>
			<td class="tsub"><a href="${student }" class="an">${bean.studentName }</a></td>
			<td class="tsub"><a href="${student }" class="an">${bean.attendanceStatus }</a></td>
			<td class="tsub">
			<c:if test="${bean.attendanceRate >= 80.0 }"><a href="${student }" class="an" style="color: blue;">${bean.attendanceRate }</a></c:if>
			<c:if test="${bean.attendanceRate < 80.0 }"><a href="${student }" class="an" style="color: red;">${bean.attendanceRate }</a></c:if>
			</td>
		</tr>
		</c:forEach>
	</table>
	
	 <div class="bt">
	 	<button type="button" class="buttn" id="back" style="cursor: pointer;">뒤로</button>
	 		<c:url value="aattendanceedit.bit" var="edit">
				<c:param name="classIdx" value="${classIdx }"/>
				<c:param name="classTitle" value="${classTitle }"/>
				<c:param name="today" value="${today }"/>
			</c:url>
	 	<button type="button" class="buttn"><a class="an" href="${edit }">수정</a></button>
	 </div>
<!-- content end -->
</div>

<%@ include file="../template/adminfooter.jspf" %>
</body>
</html>