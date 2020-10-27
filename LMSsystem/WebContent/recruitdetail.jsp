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
    <script type="text/javascript" src="js/jquery-1.12.4.js"></script>
	<script type="text/javascript" src="js/jquery.bxslider.js"></script>
    <script type="text/javascript" src="js/menu.js"></script>
<script type="text/javascript">
   $(document).ready(function() {
	  var content = document.getElementById("contentArea");
	  content.innerHTML = "${bean.postContent }";

   });
   
</script>
</head>
<body>
<%@include file="template/header.jspf" %>
<%@include file="template/menu.jspf" %>
	<!--content start  -->
	<div class="content-main">
   <span class="title_bar"></span>
		<div class="title_txt">취업 지원 센터</div>
   	<div class="board">
   	
   		<table class="table">
			<tr class="row">
				<th colspan="2" class="thd" style="width:150px; ">${bean.postTitle }</th>
			</tr>
			<tr class="row">
				<th class="thd">작성자</th>
				<td class="tsub">${bean.writerName }</td>
			</tr>
			<tr class="row">
				<th class="thd">작성일</th>
				<td class="tsub">${bean.writtenDate }</td>
			</tr>
			<c:if test="${bean.fileSrc ne null and bean.fileSrc ne 'null' }">
			<tr class="row">
				<th class="thd">첨부파일</th>
				<td class="tsub"><button class="buttn" type="button" style="margin-top: 5px; margin-bottom: 5px;"><a class="an" href="download.bit?file=${bean.fileSrc }&mainpage=${bean.recruitmentboardIdx}">다운로드</a></button></td>
			</c:if>
			</tr>
			<tr class="row">
				<td class="tsub" colspan="2" height="1000px"><div id="contentArea"></div></td>
			</tr>
			<%
				StudentDto student = (StudentDto)session.getAttribute("stuBean");
				if(student != null) System.out.println(student.getClassIdx() + ", " + student.getName());
				
				pageContext.setAttribute("student", student);
			%>
				<form method="post" >
				<input type="hidden" id="boardIdx" name="boardIdx" value="${bean.recruitmentboardIdx }"/>
			<tr>	
				<td colspan="2" class="tsub">
					<button class="btn" type="submit">지원하기</button>
				</td>
				</form>
			</tr>
   		</table>
   	</div>
	<!--content end  -->    
<%@ include file="template/footer.jspf" %> 
</body>
</html>