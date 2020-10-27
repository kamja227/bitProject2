<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Insert title here</title>
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Noto+Sans+KR:wght@100;500&display=swap" rel="stylesheet"/>
    <link href="css/atemplate.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="css/butan.css">
    <style rel="stylesheet" type="text/css">
    
    h2{
     	width: 95px;
    	margin-top: 20px;
    	text-aign: center;
    }
    .center>table {
	    width: 1000px;
	    height:200px;   
	   	position: absolute;
		top: 50%;
		left: 50%;
      	transform:translate(-50%, -30%);
      	top: 25%;
      
 	}
    th,td,tr{
     	font-size: 20px;
    }

   
    </style>

    <script type="text/javascript" src="js/jquery-1.12.4.js"></script>
	<script type="text/javascript" src="js/jquery.bxslider.js"></script>
    <script type="text/javascript" src="js/menu.js"></script>
    <script>

    </script>
</head>

<body>
<%@include file="template/myheader.jspf" %>
<%@include file="template/mymenu.jspf" %>
 	<h2>성적확인</h2>
	<div id="content">
	<!--content start  -->
<div class="center">
 <table>
	<th class="thd">평가일</th>
	<th class="thd">평가 과목명</th>
	<th class="thd">평가 점수</th>
		<c:forEach items="${myscore}" var="bean"> 
	<tr class="row">
	    <td class="tsub">${bean.testDate}</td>
	     <td class="tsub">${bean.subjectTitle}</td>
	     <td class="tsub">${bean.score}</td>
	     </tr>
	</c:forEach>
    </table>
</div>
	<!--content end  -->
	</div>
</body>
</html>
