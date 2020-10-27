<%@page import="java.util.ArrayList"%>
<%@page import="com.bit.model.dto.ClassDto"%>
<%@page import="java.util.List"%>
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
    <link href="../css/atemplate.css" rel="stylesheet" type="text/css"/>
    <link href="../css/butan.css" rel="stylesheet" type="text/css"/>
    <style rel="stylesheet" type="text/css">
    	h2{
    		text-align: center;
    		margin-top: 20px;
    	}
    	.page{
    		
    		text-align: center;
    	}
    	.buttn{
    		margin-left:5%;
    	}
    	.pagean{
    		font-size:15px;
    		display: inline-block;
    		margin: 7px;
    	}
    	.table{
    		width:1200px;
    	}
    	
    	
    	@media screen and (max-width: 1605px) {
    		.table{
	    		width:900px;
	    		
	    	}
	    	.thd{
	    		width: 120px;
	    	}
	    	.tsub{
	    		width:300px;
    		}
	
    </style>
	<script type="text/javascript" src="../js/jquery-1.12.4.js"></script>
	<script type="text/javascript" src="../js/menu.js"></script>
	<script type="text/javascript" src="../js/buttonjs.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
				$('.Dselect').hide();
			
			$('#delete').on('click',function(){
				$('.Dselect').show(function(){
				$('#delete').on('click',function(){
					if(confirm('삭제하시겠습니까?')){
						$('#delete').attr("type","submit").stop().location.reload(true);
					}else{
						location.reload(true).stop();
					}
				});
				});
			});
		});
	</script>
</head>
<body>
<%@ include file="../template/adminheader.jspf" %>
<%@ include file="../template/adminmenu.jspf" %>
	<div id="content">
	<h2>교육 과정 관리 페이지</h2>
		<!--content start  -->
		<form method="post">
				<button id="delete" class="buttn" type="button">삭제</button>
		<table class="table">
		<tr class="row">
			<th class="thd Dselect" style="width:100px;" >선택</th>
			<th class="thd" style="width:100px;">글번호</th>
			<th class="thd">교육 과정명/과목</th>
			<th class="thd">시작일</th>
			<th class="thd">종료일</th>
			<th class="thd">신청 인원</th>
			<th class="thd">상태</th>
		</tr>
		
		<c:forEach items="${page }" var="bean">
		<tr class="row">
			<td class="tsub Dselect" style="width:100px;"><input type="checkbox" name="${bean.classIdx }"/></td>
			<td class="tsub" style="width:100px;"><a class="an" href="acoursedetail.bit?idx=${bean.classIdx }">${bean.classIdx}</a></td>
			<td class="tsub"><a class="an" href="acoursedetail.bit?idx=${bean.classIdx }">${bean.classTitle}</a></td>
			<td class="tsub"><a class="an" href="acoursedetail.bit?idx=${bean.classIdx }">${bean.startDate}</a></td>
			<td class="tsub"><a class="an" href="acoursedetail.bit?idx=${bean.classIdx }">${bean.endDate }</a></td>
			<td class="tsub"><a class="an" href="acoursedetail.bit?idx=${bean.classIdx }">${bean.cnt }</a></td>
			<c:if test="${bean.status eq 0}"><td class="tsub"><a class="an" href="#">모집 전</a></td></c:if>
			<c:if test="${bean.status eq 1}"><td class="tsub"><a class="an" href="#">모집 중</a></td></c:if>
			<c:if test="${bean.status eq 2}"><td class="tsub"><a class="an" href="#">모집 마감</a></td></c:if>
		</tr>
		</c:forEach>
		
			
		</table>
		</form>
		<!--content end  -->
	</div>
	<div class="page">
		<% List<ClassDto> list =(List<ClassDto>)request.getAttribute("classlist");
			for(int i=0;i<list.size();i++){
				if(i%10==0){
			
		%>
			<a class="an pagean" href="acourse.bit?idx=<%=i/10%>"><%=(i/10)+1 %></a>
		<% }}%>
	</div>
	
<%@ include file="../template/adminfooter.jspf" %>
</body>
</html>