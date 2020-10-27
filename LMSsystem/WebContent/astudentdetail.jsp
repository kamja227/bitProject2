<%@page import="com.bit.model.dto.ClassDto"%>
<%@page import="com.bit.model.dto.EmployeeDto"%>
<%@page import="com.bit.model.dao.EmployeeDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>학생 관리 페이지</title>
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet"/>
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Noto+Sans+KR:wght@100;500&display=swap" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="../css/butan.css"/>
<link rel="stylesheet" type="text/css" href="../css/atemplate.css"/>
<style rel="stylesheet" type="text/css">
	h2{
		margin-top: 20px;
	}
	#content{
		margin-top:100px;
		text-align: center;
		width: 1000px;
	}
	table{
		margin-top:10px;
		margin-left: auto;
	}
	textarea{
		resize: none;
	}
	.tsub{
		text-align: left;
	}
	.tsub>input{
		width: 400px;
		margin-left: 10px;
		margin-right: 10px;
	}
	
</style>
<script type="text/javascript" src="../js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="../js/buttonjs.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#bt').on('click',function(){
		
	});
});


</script>
</head>

<body>
<%@ include file="../template/adminheader.jspf" %>
<%@ include file="../template/adminmenu.jspf" %>
<div id="content">
<!-- content start -->
	<h2>학생 관리 페이지 - ${bean.name }</h2>
	<form method="post">
	<input type="hidden" name="studentIdx" value="${bean.studentIdx }"/>
	<table class="table" style="width: 90%;">
	 <tr class="row">
	 	<th class="thd">이름</th>
	 	<td class="tsub"><input type="text" id="name" name="name" value="${bean.name }"/></td>
	 </tr>
	 <tr class="row">
	 	<th class="thd">수강중인 교육과정</th>
	 	<td class="tsub"><input type="text" id="class" name="class" value="${bean.classTitle }" readonly/></td>
	 </tr>
	 <tr class="row">
	 	<th class="thd">연락처</th>
	 	<td class="tsub"><input type="text" id="contact" name="contact" value="${bean.contact }"/></td>
	 </tr>
	 <tr class="row">
	 	<th class="thd">메모</th>
	 	<td class="tsub"><textarea style="width:400px; height:100px; margin:10px;" id="memo" name="memo">${bean.memo }</textarea></td>
	 </tr>
	</table>
	 <div>
	 	<button type="button" id="bt" class="buttn">뒤로</button>
	 	<input type="submit" class="buttn" value="수정" />
	 </div>
	</form>
<!-- content end -->
</div>

<%@ include file="../template/adminfooter.jspf" %>
</body>
</html>