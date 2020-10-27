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
<title>취업 공고 상세 페이지</title>
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
    		width:400px;
    	}
    	.buttns{
    		text-align: center;	
    	}
    	.tsub{
    		width:300px;
    	}
    	
    	
    </style>
	<script type="text/javascript" src="../js/jquery-1.12.4.js"></script>
	<script type="text/javascript" src="../js/menu.js"></script>
	<script type="text/javascript" src="../js/buttonjs.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
	var bean = "${bean.postContent }";
			
			bean = bean.split('<br/>').join("\r\n");

			$('#postContent').val(bean);
			
			$('.buttns>button').on('click',function(){
				window.history.back();
			});
		});
	</script>
</head>
<body>
<%@ include file="../template/adminheader.jspf" %>
<%@ include file="../template/adminmenu.jspf" %>
	<div id="content">
	<h2>취업 공고 상세 페이지</h2>
		<!--content start  -->
		<form method="post" enctype="multipart/form-data">
		<table class="table">
		<input type="hidden" name="recruitmentboardIdx" value="${bean.recruitmentboardIdx }"/>
		<input type="hidden" name="writerIdx" value="${bean.writerIdx }"/>
		<tr class="row">
			<th class="thd">기업명</th>
			<td class="tsub"><input type="text" value="${bean.postTitle }" name="postTitle"/></td>
		</tr>
		<tr class="row">
			<th class="thd">글쓴이</th>
			<td class="tsub"><input type="text" value="${employee.name }" readonly="readonly"/></td>
		</tr>
		<tr class="row">
			<th class="thd">등록 일자</th>
			<td class="tsub"><input type="date" name="writtenDate" value="${bean.writtenDate }"/></td>
		</tr>
		<tr class="row">
			<th class="thd">모집 내용</th>
			<td class="tsub"><textarea id="postContent" name="postContent" cols="20" rows="10" style="resize: none;" required></textarea></td>
		</tr>
		<c:if test="${bean.fileSrc ne null and bean.fileSrc ne \"null\"}">
		<tr class="row">
			<th class="thd">첨부 파일</th>
			<td class="tsub"><button class="buttn" type="button"><a class="an" href="../download.bit?file=${bean.fileSrc }&adminpage=${bean.recruitmentboardIdx}">다운로드</a></button>
							 <button class="buttn" type="button"><a class="an" href="filedelete.bit?file=${bean.fileSrc }&idx=${bean.recruitmentboardIdx}">삭제</a></button>
			</td>
		</tr>
		</c:if>
		<c:if test="${bean.fileSrc eq null }">
			<th class="thd">첨부 파일</th>
			<td class="tsub"><input type="file" id="file1" name="file1"/></td>
		</c:if>
		</table>
		<div class="buttns">
			<button class="buttn" type="button">뒤로</button>
			<button class="buttn" type="submit">수정</button>
		</div>
		</form>
		
		<!-- content end  -->
	</div>
<%@ include file="../template/adminfooter.jspf" %>
</body>
</html>