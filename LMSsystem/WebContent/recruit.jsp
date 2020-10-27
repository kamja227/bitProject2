
<%@page import="com.bit.model.dto.ClassDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.bit.model.dto.RecruitmentBoardDto"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비트캠프</title>
<link rel="stylesheet" type="text/css" href="css/template.css"/>
      <link rel="stylesheet" type="text/css" href="css/template.css"/>
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Noto+Sans+KR:wght@100;500&display=swap" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="css/jquery.bxslider.css"/>
    <link rel="stylesheet" type="text/css" href="css/butan.css"/>
    <style rel="stylesheet" type="text/css">
        #content{
          height: 1000px;
          padding: 100px;
        }
        .content-main { 
        	padding:20px;
        	border-right:1px solid #c7c7c7; 
        }
        .title_bar {
			background-color:#041d79;width:4px;height:19px;display:inline-block;vertical-align:middle;
			margin-left: 250px;
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
        .pages{
			text-decoration: none;
			color: gray;
			font-size: medium;
		}
    </style>

    <script type="text/javascript" src="js/jquery-1.12.4.js"></script>
    <script type="text/javascript" src="js/menu.js"></script>
<script type="text/javascript">
   $(document).ready(function() {

            
   });
</script>
</head>
<body>
   <%@ include file="template/header.jspf" %>
   <%@ include file="template/menu.jspf" %>
    <div id="content">
   <!--content start  -->
   <div class="content-main">
   <span class="title_bar"></span>
		<div class="title_txt">취업 지원 센터</div>
   	<div class="board">
   		<table class="table">
			<tr class=row>
				<th class="thd" style="width: 30px;">번호</th>
				<th class="thd">제&nbsp;&nbsp;목</th>
				<th class="thd">작성자</th>
				<th class="thd">작성일</th>
			</tr>
		<%ArrayList<RecruitmentBoardDto> list = (ArrayList<RecruitmentBoardDto>)request.getAttribute("list");%>
	<c:forEach items="${list }" var="bean">
		<c:url value="recruitdetail.bit" var="detail">
			<c:param name="boardIdx" value="${bean.recruitmentboardIdx }"/>
		</c:url>
		<tr class="row">
			<td style="width: 30px;" class="tsub"><a href="${detail }" class="an">${bean.rownum}</a></td>
			<td class="tsub"><a href="${detail }" class="an">${bean.postTitle }</a></td>
			<td class="tsub"><a href="${detail }" class="an">${bean.writerName }</a></td>
			<td class="tsub"><a href="${detail }" class="an">${bean.writtenDate }</a></td>
		</tr>
	</c:forEach>
   		</table>
   	</div>
   	
   	 <div style="text-align: center;">
	 <c:if test="${startPage ne 1 }"><a href=recruit.bit?nowPage=${nowPage-1}" class="pages">[이전]</a></c:if>
	 <c:forEach var="i" begin="0" end="4" step="1">
	 <c:if test="${startPage+i <= lastPage }">
	 <a href="recruit.bit?nowPage=${startPage+i}" class="pages" 
	 		<c:if test="${startPage+i eq nowPage }"> style="text-decoration: underline;"</c:if>>${startPage+i}</a>
	 </c:if>
	 </c:forEach>
	 <c:if test="${(lastPage - startPage) > 5 }"><a href="recruit.bit?nowPage=${nowPage+1} class="pages">[다음]</a></c:if>
	 </div>
	 
   	</div>
   <!--content end  -->
   </div>
   <%@ include file="template/footer.jspf"%>
</body>
</html>