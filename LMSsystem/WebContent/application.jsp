<%@page import="com.bit.model.dto.ApplicationDto"%>
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
    	select{
    		text-align:center;
    		width:60px;
    		height:30px;
    	}
    	.buttn{
    		width:60px;
    		margin:0px;
    		height: 30px;
    	}
    	.page{
    		
    		text-align: center;
    	}
    	
    	.pagean{
    		font-size:15px;
    		display: inline-block;
    		margin: 7px;
    	}
    	.seach-box{
    		width:1150px;
    		text-align: right;
    	}
    	.page{
    		
    		text-align: center;
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
	<script type="text/javascript">
		$(document).ready(function(){
			function getParam(sname) {
				
			    var params = location.search.substr(location.search.indexOf("?") + 1);
			    var sval = "";
			    params = params.split("&");
			    for (var i = 0; i < params.length; i++) {
			        temp = params[i].split("=");
			        if ([temp[0]] == sname) { sval = temp[1]; }
			    }
			    return sval;
			}
			
		});
	</script>
</head>
<body>
<%@ include file="../template/adminheader.jspf" %>
<%@ include file="../template/adminmenu.jspf" %>
	<div id="content">
	<h2>취업 지원 현황</h2>
	<br/>
	<br/>
		<!--content start  -->
		<div class="seach-box">
		<form method="get">
			<select>
				<option>이름</option>
			</select>
			<input style="height:24px;" type="text" name="name"/>
			<button class="buttn" type="submit">검색</button>
		
		</form>
		</div>
		<table class="table">
		<tr class="row">
			<th class="thd" style="width:50px;">번호</th>
			<th class="thd">학생명</th>
			<th class="thd">기업명</th>
			<th class="thd">지원 현황</th>
		</tr>
		<% String name= request.getParameter("name");
			if(name==null)name=name;
		%>
		
		<c:forEach items="${applicationAll }" var="applicationAll">
			<tr class="row">
			<td class="tsub" style="width:50px;"><a class="an" href="applicationdetail.bit?idx=${applicationAll.applicantIdx }&aidx=${applicationAll.applicationIdx}">
			${applicationAll.applicationIdx }</a></td>
		<c:forEach items="${studentAll }" var="studentAll">
		<c:if test="${applicationAll.applicantIdx eq studentAll.studentIdx }">
				<td class="tsub"><a class="an" href="applicationdetail.bit?idx=${applicationAll.applicantIdx }&aidx=${applicationAll.applicationIdx}">
				${studentAll.name }</a></td>
		</c:if>
		</c:forEach>
		<c:forEach items="${recruitmentAll }" var="recruitmentAll">
		<c:if test="${applicationAll.recruitmentBoardIdx eq recruitmentAll.recruitmentboardIdx }">
				<td class="tsub"><a class="an" href="applicationdetail.bit?idx=${applicationAll.applicantIdx }&aidx=${applicationAll.applicationIdx}">
				${recruitmentAll.postTitle }</a></td>		
		</c:if>
		</c:forEach>
				<td class="tsub"><a class="an" href="applicationdetail.bit?idx=${applicationAll.applicantIdx }&aidx=${applicationAll.applicationIdx}">
				${applicationAll.status }</a></td>
			</tr>
		</c:forEach>
		</table>
		<div class="page">
		<% 
		List<ApplicationDto> list=null;
		if(name==null||name.equals("null")){
			
			list = (List<ApplicationDto>)request.getAttribute("applicationSelectAll");
		}else if(name!=null&&!(name.equals("null"))){
			list = (List<ApplicationDto>)request.getAttribute("Amount");
		}
			int cnt=0;
			for(int i=0;i<list.size();i++){cnt=i/10+1;
			if(i%10==0){
		%>	
			<a class="an pagean" href="application.bit?idx=<%=(i/10)%>&name=<%=name%>"><%=(i/10)+1 %></a>
		<%}} %>
		</div>
		<!-- <table class="table detail">
			<tr class="row">
				<th class="thd">학생명</th>
				<td></td>
			</tr>
			<tr class="row">
				<th class="thd">모집 공고명</th>
			</tr>
			<tr class="row">
				<th class="thd">이력서</th>
			</tr>
			<tr class="row">
				<th class="thd">지원현황</th>
			</tr>
		</table> -->
		<!--content end  -->
	</div>
<%@ include file="../template/adminfooter.jspf" %>
</body>
</html>