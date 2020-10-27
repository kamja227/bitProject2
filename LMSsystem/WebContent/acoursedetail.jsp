<%@page import="com.bit.model.dto.ClassDto"%>
<%@page import="com.bit.model.dto.EmployeeDto"%>
<%@page import="com.bit.model.dao.EmployeeDao"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>교육 과정 관리 페이지</title>
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet"/>
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Noto+Sans+KR:wght@100;500&display=swap" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="../css/butan.css"/>
<link rel="stylesheet" type="text/css" href="../css/atemplate.css"/>
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
    		width:100px;
    	}
    	.bt{
    		text-align: center;
    	}
	
</style>
<script type="text/javascript" src="../js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="../js/buttonjs.js"></script>
<script type="text/javascript">
$(document).ready(function(){
var etc="${classdetail.etc }";

etc = etc.split('<br/>').join("\r\n");

	$('#etc').val(etc);
	
	
	$('.back').on('click',function(){
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
	<h2>교육 과정 관리 페이지</h2>
	<form action="acoursedetail.bit" method="post">
	<table class="table">
	<input type="hidden" name="idx" id="idx" value="${classdetail.classIdx }"/>
	 <tr class="row">
	 	<th class="thd">교육 과정명</th>
	 	<td class="tsub"><input type="text" id="classTitle" name="classTitle" value="${classdetail.classTitle }"/></td>
	 </tr>
	 <tr class="row">
	 	<th class="thd">부 제목</th>
	 	<td class="tsub">
	 	<select name="subjectTitle">
	 	
			<option value="${classdetail.subjectTitle }" >${classdetail.subjectTitle }</option>
			<option value="자바">자바</option>
			<option value="웹" >웹</option>
			<option value="프레임" >프레임</option>
		</select>
	 	</td>
	 </tr>
	 <tr class="row">
	 	<th class="thd">시작일</th>
	 	<td class="tsub"><input type="date" id="startDate" name="startDate" value="${classdetail.startDate }"></td>
	 </tr>
	 <tr class="row">
	 	<th class="thd">종료일</th>
	 	<td class="tsub"><input type="date" id="endDate" name="endDate" value="${classdetail.endDate }"></td>
	 </tr>
	 <tr class="row">
	 	<th class="thd">담당 강사</th>
	 	<td class="tsub">
	 		<select name="teacherId">
	 			<c:forEach items="${allgangsaList }" var="gangsa">
	 			
	 			<c:if test="${gangsa.employeeIdx eq classdetail.teacherId }">
	 			<option value="${gangsa.employeeIdx }"<c:if test="${gangsa.employeeIdx eq classdetail.teacherId}">selected="selected"</c:if>>
	 			${gangsa.name }
	 			</option>
	 			</c:if>
	 			</c:forEach>
	 			
	 			
			 	<c:forEach items="${ablegangsaList }" var="gangsa">
	 			<option value="${gangsa.employeeIdx}">${gangsa.name }</option>
			</c:forEach>
			</select>
	 	</td>
	 </tr>
	 <tr class="row">
	 	<th class="thd">담당 매니저</th>
	 <td class="tsub">
	 		<select name="managerId">
			 	<c:forEach items="${managerList }" var="manager">
	 			<option value="${manager.employeeIdx}" <c:if test="${manager.employeeIdx eq classdetail.managerId}">selected="selected"</c:if>>${manager.name }</option>
				</c:forEach>
	 		</select>
	 	</td>
	 </tr>
	 <tr class="row">
	 	<th class="thd">강의실</th>
	 	<td class="tsub">
	 		<select name="classRoom">
	 			<c:forEach items="${classroom }" var="classroom">
	 			<c:if test="${classroom.roomNum eq classdetail.classRoom }">
	 			<option value="${classroom.roomNum }"  <c:if test="${classroom.roomNum eq classdetail.classRoom }">selected="selected"</c:if>>
	 			${classroom.roomNum }</option>
	 			selected="selected"</c:if>
	 			</c:forEach>
	 			<c:forEach items="${classroom }" var="classroom">
	 			<c:if test="${classroom.inUse eq 1 }">
	 			<option value="${classroom.roomNum }">${classroom.roomNum }</option>
	 			</c:if>
	 			</c:forEach>
	 			
	 		</select>
	 	
	 	</td>
	 </tr>
	 <tr class="row">
	 	<th class="thd">상태</th>
	 	<td class="tsub"><select name="status">
            <option value="0"
               <c:if test="${classdetail.status eq 0}">selected</c:if>>모집전
            </option>
            <option value="1"
               <c:if test="${classdetail.status eq 1}">selected</c:if>>모집중
               </option>
            <option value="2"
               <c:if test="${classdetail.status eq 2}">selected</c:if>>모집마감
            </option>
         </select></td>
	 </tr>
	 <tr class="row">
	 	<th class="thd">기타</th>
	 	<td class="tsub" class="area"><textarea id="etc" name="etc" cols="15" rows="10" required style="resize: none;"></textarea></td>
	 </tr>
	</table>
	 <div class="bt">
	 	<button  type="button" class="buttn back">뒤로</button>
	 	<button type="submit" class="buttn">수정</button>
	 	
	 </div>
	</form>
	
<!-- content end -->
</div>

<%@ include file="../template/adminfooter.jspf" %>
</body>
</html>