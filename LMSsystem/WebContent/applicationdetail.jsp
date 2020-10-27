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
<title>취업 지원 현황</title>
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Noto+Sans+KR:wght@100;500&display=swap" rel="stylesheet"/>
    <link href="../css/atemplate.css" rel="stylesheet" type="text/css"/>
    <link href="../css/butan.css" rel="stylesheet" type="text/css"/>
    <style rel="stylesheet" type="text/css">
    	h2{
    		text-align: center;
    		margin-top: 20px;
    	}
    	.table{
    		width: 500px;
    	}
    	.thd{
    		width: 100px;
    	}
    	.tsub{
    		width:200px;
    	}
    	.back{
    		margin-left:20%
    	}
    	.content{
    		height: 200px;
    	}
    	select{
    		width: 200px;
    		height: 40px;
    		text-align: center;
    		font-size: 20px;
    	}
    	
    </style>
	<script type="text/javascript" src="../js/jquery-1.12.4.js"></script>
	<script type="text/javascript" src="../js/menu.js"></script>
	<script type="text/javascript" src="../js/buttonjs.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
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
	<h2>취업 지원 현황</h2>
	<button type="button" class="buttn back">뒤로</button>
		<!--content start  -->
		<!-- <button class="buttn"><a class="an" href="addrecruit.bit">글쓰기</a></button> -->
		<form method="post">
		 <table class="table detail">
			<tr class="row">
				<th class="thd">학생명</th>
				<td colspan="2" class="tsub">
				<input type="hidden" name="applicationIdx" id="" value="${application.applicationIdx }"/>
				${studentname.name }
				</td>
			</tr>
			<tr class="row">
				<th class="thd">모집 공고명</th>
				<td colspan="2" class="tsub">${title.postTitle}</td>
			</tr>
			<tr class="row">
				<th class="thd">이력서 내용</th>
				<td colspan="2" class="tsub content">${content.resumeContent}</td>
			</tr>
			<tr class="row">
				<th class="thd">지원현황</th>
				<td class="tsub">
				<select name="status">
					<option selected="selected">${application.status}</option>
					<option >지원완료</option>
					<option >면접대기</option>
					<option >취업완료</option>
				</select>
				
				</td>
				<td><button type="submit" class="buttn">변경</button></td>
			</tr>
		</table> 
		</form>
		<!--content end  -->
	</div>
<%@ include file="../template/adminfooter.jspf" %>
</body>
</html>