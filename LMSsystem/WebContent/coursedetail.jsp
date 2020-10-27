<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.bit.model.dto.StudentDto"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/template.css"/>
    <link rel="stylesheet" type="text/css" href="css/template.css"/>
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Noto+Sans+KR:wght@100;500&display=swap" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="css/jquery.bxslider.css"/>
    <link rel="stylesheet" type="text/css" href="css/butan.css"/>
    <style rel="stylesheet" type="text/css">
     <style rel="stylesheet" type="text/css">
        #content{
          height: 1000px;
          padding: 100px;
        }
        .content-main { 
        	padding:20px;
        	border-right:1px solid #c7c7c7; 
        	margin-top: 100px;
        }
        .title_bar {
			background-color:#041d79;width:4px;height:19px;display:inline-block;vertical-align:middle;
			margin-left: 350px;

		}
		.title_txt {
			color:#041d79;font-size:18px;font-weight:900;margin-left:20px;display:inline-block;vertical-align:middle;
		}
        .btn{
           margin:10px;
           border:none;
            border-radius:10px;
            background-color: black;
            color: white;
            width: 230px;
            height: 40px;
            cursor: pointer;
        }
        .btn>a{
            color: white;
            text-decoration: none;
            display: block;
        }
        .btn>a:hover{
            color:white;
        }
       .table{
       		margin-top: 50px;
       		width: 50%;;
       }
       table tr.row:hover{
         background-color: #FFFFFF;
      }
    </style>
    </style>
    <script type="text/javascript" src="js/jquery-1.12.4.js"></script>
	<script type="text/javascript" src="js/jquery.bxslider.js"></script>
    <script type="text/javascript" src="js/menu.js"></script>
<script type="text/javascript">
   $(document).ready(function() {

            
   });
</script>
</head>
<body>
<%@include file="template/header.jspf" %>
<%@include file="template/menu.jspf" %>
	<!--content start  -->
	<div class="content-main">
   <span class="title_bar"></span>
		<div class="title_txt">${bean.classTitle }</div>
   	<div class="board">
   		<table class="table">
			<tr class="row">
				<th class="thd" style="width:150px; ">교육과정명</th>
				<td class="tsub">${bean.classTitle }</td>
			</tr>
			<tr class="row">
				<th class="thd">과목명</th>
				<td class="tsub">${bean.subjectTitle }</td>
			</tr>
			<tr class="row">

				<th class="thd">담임강사</th>
				<td class="tsub">${teacher.name }</td>
			</tr>
			<tr class="row">
				<th class="thd">교육 시작일</th>
				<td class="tsub">${bean.startDate }</td>
			</tr>
			<tr class="row">
				<th class="thd">교육 종료일</th>
				<td class="tsub">${bean.endDate }&nbsp;</td>
			</tr>
			<tr class="row">
				<th class="thd">현재 신청 인원</th>
				<td class="tsub">${bean.cnt }</td>
			</tr>
			<tr class="row">
				<td class="thd">설&nbsp;&nbsp;명</td>
				<td class="tsub"><p>${bean.etc }</p></td>
			</tr>
			<tr class="row">
			<%
				StudentDto student = (StudentDto)session.getAttribute("stuBean");
				if(student != null) System.out.println(student.getClassIdx() + ", " + student.getName());
				
				pageContext.setAttribute("student", student);
			%>
				<form method="post" >
				<input type="hidden" id="classIdx" name="classIdx" value="${bean.classIdx }"/>
				<input type="hidden" id="cnt" name="cnt" value="${bean.cnt }"/>
				<td colspan="2" class="tsub">
			
					<c:choose>
					<c:when test="${bean.status ne 1}">
						<button class="btn" type="button" style="background-color:gray; cursor:default; color:lightgray;" disabled="disabled">수강신청
						</button>
					</c:when>
					<c:when test="${student.classIdx == bean.classIdx and bean.status eq 1}">
						<input type="submit" class="btn" value="수강취소" />
					</c:when>		
					<c:otherwise>
						<input type="submit" class="btn" value="수강신청"/>
					</c:otherwise>
					</c:choose>
				</td>
				</form>
			</tr>
   		</table>
   	</div>
	<!--content end  -->    
<%@ include file="template/footer.jspf" %> 
</body>
</html>