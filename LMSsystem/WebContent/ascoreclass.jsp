<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.bit.model.dto.StudentDto, java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>성적 관리 페이지</title>
<link rel="stylesheet" type="text/css" href="../css/butan.css"/>
<link rel="stylesheet" type="text/css" href="../css/atemplate.css"/>
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Noto+Sans+KR:wght@100;500&display=swap" rel="stylesheet"/>
<style rel="stylesheet" type="text/css">
	#content{
		text-align: center;
		width: 1000px;
	}
	input{
		margin-top:10px;
	}
	#insert{
		display: inline-flex;	
		width: 730px;
		height: 50px;
		margin-left: 200px;
		margin-top: 20px;
	}
   	.buttn{
   		margin-top: 0px;
   		margin-left: 80px;
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
		ArrayList<StudentDto> list = (ArrayList<StudentDto>)request.getAttribute("list");
		
	%>

<div id="content">
<!-- content start -->
	<div id="insert">
	<h2>성적 관리 페이지 - ${classTitle }</h2>

	<button type="button" class="buttn">
		<a class="an" href="addscoreinsert.bit?classIdx=${classIdx }&classTitle=${classTitle }">성적 입력
		</a></button>
	</div>
	
	<table class="table" style="width: 90%;">
	
	 <tr class="title row">
	 	<th class="thd">학생이름</th>
	 	<th class="thd">과목명</th>
	 	<th class="thd">시험일</th>
	 	<th class="thd">점수</th>
	 </tr>
	 	<c:forEach items="${list }" var="bean">
		<tr class="row">
			<td class="tsub"><a href="${detail }" class="an">${bean.studentName }</a></td>
			<td class="tsub"><a href="${detail }" class="an">${bean.subjectTitle }</a></td>
			<td class="tsub"><a href="${detail }" class="an">${bean.testDate }</a></td>
			<td class="tsub"><a href="${detail }" class="an">${bean.score }</a></td>
		</tr>
		</c:forEach>
	</table>
	
	 <div class="bt">
	 	<button type="button" class="buttn" id="back" style="cursor: pointer;">뒤로</button>
	 		<c:url value="ascoreedit.bit" var="edit">
				<c:param name="classIdx" value="${classIdx }"/>
				<c:param name="classTitle" value="${classTitle }"/>
			</c:url>
	 	<button type="button" class="buttn"><a class="an" href="${edit }">수정</a></button>
	 </div>
<!-- content end -->
</div>

<%@ include file="../template/adminfooter.jspf" %>
</body>
</html>