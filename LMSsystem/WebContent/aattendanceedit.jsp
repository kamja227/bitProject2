<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.bit.model.dto.AttendanceDto, java.util.ArrayList, java.text.SimpleDateFormat, java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>출석 수정 페이지</title>
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
		margin-top: 10px;
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
		
		 location.href="http://localhost:8080/LmsSystem/admin/aattendanceedit.bit?classIdx=" + classIdx + 
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
		Object classIdx = request.getAttribute("classIdx");
		String classTitle = (String)request.getAttribute("classTitle");
		ArrayList<AttendanceDto> list = (ArrayList<AttendanceDto>)request.getAttribute("list");
		
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Date time = new Date();
		String now = format1.format(time);

		pageContext.setAttribute("now", now);
		pageContext.setAttribute("list", list);
	%>

<div id="content">
<!-- content start -->
	<h2>출석 수정 페이지 - ${classTitle }</h2>
	<form method="post" accept-charset="utf-8">
	<input type="date" value="${today }" min="2019-01-01" max="${now }" id="date" name="today">
	<input type="hidden" name="classIdx" value="${classIdx }">
	<input type="hidden" name="classTitle" value="${classTitle }">
	<table class="table" style="width: 90%;">
	
	 <tr class="title row">
	 	<th class="thd">이름</th>
	 	<th class="thd">출석상태</th>
	 </tr>
	 	<c:forEach items="${list }" var="bean">
		<tr class="row">
			<input type="hidden" value="${bean.attendanceIdx }" name="Attendance${bean.attendanceIdx }" />
			<td class="tsub"><span>${bean.studentName }</span></td>
			<td class="tsub">
				<input type="radio" name="status${bean.attendanceIdx }" value="출석" id="ra1" <c:if test="${bean.attendanceStatus eq '출석'}">checked</c:if>/>
				<label for="ra1">출석</label>&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="status${bean.attendanceIdx }" value="결석" id="ra2"<c:if test="${bean.attendanceStatus eq '결석'}">checked</c:if>/>
				<label for="ra2">결석</label>&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="status${bean.attendanceIdx }" value="지각" id="ra3"<c:if test="${bean.attendanceStatus eq '지각'}">checked</c:if>/>
				<label for="ra3">지각</label>&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="status${bean.attendanceIdx }" value="조퇴" id="ra4"<c:if test="${bean.attendanceStatus eq '조퇴'}">checked</c:if>/>
				<label for="ra4">조퇴</label>&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="status${bean.attendanceIdx }" value="기타" id="ra5"<c:if test="${bean.attendanceStatus eq '기타'}">checked</c:if>/>
				<label for="ra5">기타</label>
				</select>
			</td>
		</tr>
		</c:forEach>
	</table>
	
	 <div class="bt">
	 	<button type="button" class="buttn" id="back" style="cursor: pointer;">뒤로</button>
	 	<button type="submit" class="buttn" style="cursor: pointer;">수정</button>
	 </div>
	 </form>
<!-- content end -->
</div>

<%@ include file="../template/adminfooter.jspf" %>
</body>
</html>