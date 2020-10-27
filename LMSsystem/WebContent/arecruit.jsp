<%@page import="com.bit.model.dto.RecruitmentBoardDto"%>
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
<title>취업 지원 센터</title>
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Noto+Sans+KR:wght@100;500&display=swap" rel="stylesheet"/>
    <link href="../css/atemplate.css" rel="stylesheet" type="text/css"/>
    <link href="../css/butan.css" rel="stylesheet" type="text/css"/>
    <style rel="stylesheet" type="text/css">
    	h2{
    		text-align: center;
    		margin-top: 20px;
    	}
    	.buttn{
    		margin-left:15%;
    	}
    	.table{
    		width:600px;
    	}
    	.thd .tsub{
    		width:200px;
    	}
    	.page{
    		
    		text-align: center;
    	}
    	.buttn{
    		margin-left:5%;
    	}
    	.pagean{
    		display:block;
    		text-align: center;
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
	<script type="text/javascript">
		$(document).ready(function(){
			
		});
	</script>
</head>
<body>
<%@ include file="../template/adminheader.jspf" %>
<%@ include file="../template/adminmenu.jspf" %>
	<div id="content">
	<h2>취업 지원 센터</h2>
		<!--content start  -->
		<button class="buttn"><a style="color:white;" class="an" href="addrecruit.bit">글쓰기</a></button>
		<table class="table">
		<tr class="row">
			<th class="thd">글번호</th>
			<th class="thd">기업명</th>
			<th class="thd">모집 마감일</th>
		</tr>
		<c:forEach items="${page }" var="recruitlist">
			<tr class="row">
				<td class="tsub"><a class="an" href="arecruitdetail.bit?idx=${recruitlist.recruitmentboardIdx }">${recruitlist.recruitmentboardIdx}</a></td>
				<td class="tsub"><a class="an" href="arecruitdetail.bit?idx=${recruitlist.recruitmentboardIdx }">${recruitlist.postTitle }</a></td>
				<td class="tsub"><a class="an" href="arecruitdetail.bit?idx=${recruitlist.recruitmentboardIdx }">${recruitlist.writtenDate }</a></td>
			</tr>
		</c:forEach>
		</table>
		<div class="page">
		
		<% List<RecruitmentBoardDto> list =(List<RecruitmentBoardDto>)request.getAttribute("recruitlist"); 
			for(int i=0;i<list.size();i++){
				if(i%10==0){
		%>
			<a class="pagean an" href="arecruit.bit?idx=<%=i/10 %>" ><%=(i/10)+1 %></a>
		<%}} %>
		</div>
		<!--content end  -->
	</div>
<%@ include file="../template/adminfooter.jspf" %>
</body>
</html>