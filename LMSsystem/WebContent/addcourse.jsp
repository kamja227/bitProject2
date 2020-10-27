<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>교육 과정 입력 페이지</title>
<link rel="stylesheet" type="text/css" href="../css/butan.css"/>
<link rel="stylesheet" type="text/css" href="../css/atemplate.css"/>
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Noto+Sans+KR:wght@100;500&display=swap" rel="stylesheet"/>
<style rel="stylesheet" type="text/css">
		h2{
    		text-align: center;
    		margin-top: 20px;
    	}
    	.table{
    		width:400px;
    	}
    	.buttns{
    		text-align: center;	
    	}
    	.tsub{
    		width:200px;
    	}
    	.bt{
    		text-align: center;
    	}
</style>
<script type="text/javascript" src="../js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="../js/buttonjs.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var today=function getToday(){
		var date = new Date();
		return date.getFullYear() + "-" +("0"+(date.getMonth()+1)).slice(-2)+"-"+("0"+date.getDate()).slice(-2);
	}
	$('#startDate').val(today);
	
	$('.cancel').on('click',function(){
		window.history.back();
	});
});


</script>
</head>

<body>
<%@ include file="../template/adminheader.jspf" %>
<%@ include file="../template/adminmenu.jspf" %>
<div id="content">
<!-- content start -->
<h2>교육 과정 입력 페이지</h2>
<form method="post">
	<table class="table">
		<tr class="row">
			<th class="thd">교육 과정명</th>
			<td class="tsub"><input type="text" name="classTitle" id="classTitle"/></td>
		</tr>
		<tr class="row">
			<th class="thd">과정 시작일</th>
			<td class="tsub"><input type="date" name="startDate" id="startDate"/></td>
		</tr>
		<tr class="row">
			<th class="thd">과정 종료일</th>
			<td class="tsub"><input type="date" name="endDate" id="endDate"/></td>
		</tr>
		<tr class="row">
			<th class="thd">담당 강사</th>
			<td class="tsub">
			<select name="teacherId">
			<c:forEach items="${ableGangsa }" var="ableGangsa">
			<option value="${ableGangsa.employeeIdx }" >${ableGangsa.name}</option>
			</c:forEach>
			
			</select>
			
			</td>
		</tr>
		<tr class="row">
			<th class="thd">담당 매니저</th>
			<td class="tsub">
			<select name="managerId">
			<c:forEach items="${ableManager }" var="ableManager">
			<option value="${ableManager.employeeIdx }" >${ableManager.name}</option>
			</c:forEach>
			</select>
			</td>
		</tr>
		<tr class="row">
			<th class="thd">강의실</th>
			<td class="tsub">
			<select name="classRoom">
			<c:forEach items="${classroom }" var="canuseroom">
				<c:if test="${canuseroom.inUse eq 1}">
					<option value="${canuseroom.roomNum }"  selected="selected">${canuseroom.roomNum }</option>
				</c:if>
			</c:forEach>
			</select>
			</td>
		</tr>
		<tr class="row">
			<th class="thd">교육 과목</th>
			<td class="tsub">
			<select name="subjectTitle">
			<option value="자바" >자바</option>
			<option value="웹" >웹</option>
			<option value="프레임" >프레임</option>
			
			</select>
			</td>
		</tr>
		<tr class="row">
			<th class="thd">기타</th>
			<td class="tsub"><textarea name="etc" id="etc"></textarea></td>
		</tr>
	</table>
	<div class="bt">
		<button class="buttn" type="submit">입력</button>
		<button class="buttn cancel" type="reset">취소</button>
	</div>
	</form>
<!-- content end -->
</div>

<%@ include file="../template/adminfooter.jspf" %>
</body>
</html>