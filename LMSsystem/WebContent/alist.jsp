<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList,java.util.List ,com.bit.model.dto.EmployeeDto"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>관리자 계정</title>
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
		.button{
			
			width:200px;
			height:50px; 
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
    		
    	.pages{
			text-decoration: none;
			color: gray;
			font-size: 20px;
		}
    </style>
	<script type="text/javascript" src="../js/jquery-1.12.4.js"></script>
	<script type="text/javascript" src="../js/menu.js"></script>
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
<body>
<%@ include file="../template/adminheader.jspf" %>
<%@ include file="../template/adminmenu.jspf" %>
	<div id="content">
	<!--content start  -->
	<%
		ArrayList<EmployeeDto> list = (ArrayList<EmployeeDto>)request.getAttribute("list");
	%>
  <h2><a class="an" href="alist.bit">관리자 계정</a></h2>
  <form method="post">
	 <table class="table">
	 	<tr class="row">
	 		<th class="thd Dselect" style="width:30px;">체크</th>
			<th class="thd">부서명</th>
			<th class="thd">이름</th>
			<th class="thd">아이디</th>
			<th class="thd">연락처</th>
		</tr>
		<c:forEach items= "${adminlist}" var="bean">
			<c:url value="alistdetail.bit" var="detail">
				<c:param name="employeeIdx" value="${bean.employeeIdx}"/>		
			</c:url>
			<c:if test="${bean.level ne '1'}">
				<tr class="row">
					<td class="tsub Dselect" style="width:30px;"><input type="checkbox" id="employeecheck" name="Idx${bean.employeeIdx }" value="${bean.employeeIdx }"></td>				
					<td class="tsub"><a class="an" href="${detail }">${bean.department}</a></td>
					<td class="tsub"><a class="an" href="${detail }">${bean.name}</a></td>
					<td class="tsub"><a class="an" href="${detail }">${bean.userId}</a></td>
					<td class="tsub"><a class="an" href="${detail }">${bean.contact}</a></td>
				</tr>
			</c:if>
	
		</c:forEach>
		<c:if test="${bean.level >=4 }">
		 	<div style="text-align: right; margin-right: auto" id="btn_box">
				<button id="new" type="button" class="buttn"><a class="an" href="addlist.bit">신규 관리자</a></button>
				<button id="delete" class="buttn" type="button" style="">삭제</button>
			</div>
		</c:if>
	</table>
  </form>

	<div style="text-align: center;">
	 <c:if test="${startPage ne 1 }"><a href=astudent.bit?search1=${search1 }&search2=${search2 }&nowPage=${nowPage-1}" class="pages">[이전]</a></c:if>
	 <c:forEach var="i" begin="0" end="4" step="1">
	 <c:if test="${startPage+i <= lastPage }">
	 <a href="astudent.bit?search1=${search1 }&search2=${search2 }&nowPage=${startPage+i}" class="pages" 
	 		<c:if test="${startPage+i eq nowPage }"> style="text-decoration: underline;"</c:if>>${startPage+i}</a>
	 </c:if>
	 </c:forEach>
	 <c:if test="${(lastPage - startPage) > 5 }"><a href="astudent.bit?search1=${search1 }&search2=${search2 }&nowPage=${nowPage+1} class="pages">[다음]</a></c:if>
	 </div>
		<!--content end  -->
	</div>
	
<%@ include file="../template/adminfooter.jspf" %>
</body>
</html>