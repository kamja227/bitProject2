<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.bit.model.dto.ResumeDto"%>
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
             width: 125px;
             margin-top: 20px;
             text-align: center;
              
        }
         .table{
         margin-left: 15%;
         }   
         #content{
         	width:1000px;
         	text-align: center;
         	
         }      
              
		.buttn{
		 
		   width:145px;
			margin-left: 35%;
		}
 
    </style>
    <script type="text/javascript" src="js/jquery-1.12.4.js"></script>
    <script type="text/javascript" src="js/buttonjs.js"></script>
</head>
<%@ include file="template/myheader.jspf" %>
<%@ include file="template/mymenu.jspf" %>
<body>
 	 <h2>나의 이력서</h2>
	<!--content start  -->
	<div id="content">
	<%
		ArrayList<ResumeDto> list = (ArrayList<ResumeDto>)request.getAttribute("list");
	%>
 <table class="table" style="width: 85%;">
 <tr class="row">
	<th class="thd">이력서 제목</th>
	<th class="thd">작성일</th>
	</tr>
	<c:forEach items= "${resumelist}" var="bean">
		<c:url value="myresumedetail.bit" var="detail">
			<c:param name="resumeIdx" value="${bean.resumeIdx}"/>		
		</c:url>
	<tr class="row">
	    <td class="tsub"><a class="an" href="${detail }" name="resumeTitle">${bean.resumeTitle}</a></td>
	    <td class="tsub">${bean.writtenDate}</td>
	</tr>
	</c:forEach>
	
	<tr class="row">
		<td colspan="2">
   		<button class="buttn" style="margin: 0px auto; margin-top: 10px;"><a class="an" href="myaddresume.bit">새이력서 작성하기</a></button>
		</td>
	</tr>
	
    </table>
   
   
	<!--content end  -->
</div>
</body>
</html>
