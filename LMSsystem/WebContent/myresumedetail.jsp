<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>이력서 조회</title>
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
        input{
        	width: 100%;
        	height: 100%;
        	margin: 2px 2px;
        	
        }
        textarea{
        	width: 100%;
        	height: 100%;
        	resize: none;
        }
    </style>
    <script type="text/javascript" src="js/jquery-1.12.4.js"></script>
    <script type="text/javascript" src="js/buttonjs.js"></script>
</head>
<%@ include file="template/myheader.jspf" %>
<%@ include file="template/mymenu.jspf" %>
<body>
	<h2>이력서 조회</h2>
	<div id="content">
	<!--content start  -->
	<form method="post">
		<div>
		<table class="table">
			<c:forEach items= "${ResumeDetailList}" var="bean">
			<tr class="row">
				<th class="thd" style="height: 30px;">제목</th>
				<td class="tsub"><input type="text" name="resumeTitle" id="resumeTitle" value="${bean.resumeTitle}"></td>
			</tr>
			<tr class="row">
				<th class="thd" style="height: 500px;">내용</th>
				<td class="tsub"><textarea id="resumeContent" name = "resumeContent">${bean.resumeContent}</textarea></td>
			</tr>
			</c:forEach>
			<tr class="row" style="text-align: center;">
				<td colspan="2">
					<button type="submit" class="buttn">수정</button>
					<button type="reset" class="buttn">삭제</button>
				</td>
			</tr>	
			
		</table>
		</div>
		<div>
		</div>
			
		
	</form>
	<!--content end  -->
	</div>
</body>
</html>
