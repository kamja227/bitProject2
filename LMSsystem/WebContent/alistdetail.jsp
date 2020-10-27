<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>관리자 수</title>
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
    		width:700px;
    	}
    	input,select{
    		width: 100%;
    	}
    	
    	@media screen and (max-width: 1605px) {
    		.table{
	    		width:700px;
	    		
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
    </style>
	<script type="text/javascript" src="../js/jquery-1.12.4.js"></script>
	<script type="text/javascript" src="../js/menu.js"></script>
	<script type="text/javascript">
	</script>
</head>
<body>
<%@ include file="../template/adminheader.jspf" %>
<%@ include file="../template/adminmenu.jspf" %>
	<div id="content">
		<!--content start  -->
		<div class="content">
		  	<h2>관리자 계정 수정</h2>
	
			
			<c:set var="bean" value="${AdminDetailList}" />
			<form method="post">
				<table class="table">
					<tr class="row">
						<th class="thd Dselect" style="width:30px; height: 30px;">이름</th>
						<td class="tsub"><input type ="text" id="name" name="name" value="${bean.name}"></td>
					</tr>
					<tr class="row">
						<th class="thd">ID</th>
						<td class="tsub"><input type ="text" id="id" name="id" value="${bean.userId}" readonly="readonly"></td>
					</tr>
					<tr class="row">
						<th class="thd">비밀번호</th>
						<td class="tsub"><input type ="text" id="password" name="password" value="${bean.password}"></td>
					</tr>
					<tr class="row">
						<th class="thd">연락처</th>
						<td class="tsub"><input type ="text" id="contact" name=contact value="${bean.contact}"></td>
					</tr>
					<tr class="row">
						<th class="thd">이메일</th>
						<td class="tsub"><input type ="text" id="email" name="email" value="${bean.email}"></td>
					</tr>	
					<tr class="row">
						<th class="thd">부서</th>
						<td class="tsub">							
							<select name="department">
								<option value="행정부" <c:if test="${bean.department eq '행정부'}">selected</c:if>>행정부</option>
								<option value="강사"<c:if test="${bean.department eq '강사'}">selected</c:if>>강사</option>
								<option value="영업부"<c:if test="${bean.department eq '영업부'}">selected</c:if>>영업부</option>
								<option value="취업부"<c:if test="${bean.department eq '취업부'}">selected</c:if>>취업부</option>	
							</select>
						</td>
					</tr>									
					<tr class="row">
						<th class="thd">권한</th>
						<td class="tsub">
							<select name="level">
								<option value="2" <c:if test="${bean.level eq '2'}">selected</c:if>>2</option>
								<option value="3" <c:if test="${bean.level eq '3'}">selected</c:if>>3</option>
								<option value="4" <c:if test="${bean.level eq '4'}">selected</c:if>>4</option>
							</select>
						</td>
					</tr>
					<tr class="row" style="text-align: center;">
						<td colspan="2">
							<button class="buttn" type="submit" style="margin-right: 3px"><a class="an">수정</a></button>
							<button class="buttn" type="reset" style="margin-left: 0px">삭제</button>		
						</td>
					</tr>
				</table>				 
					
					
			</form>
		</div>	
		<!--content end  -->
	</div>
<%@ include file="../template/adminfooter.jspf" %>
</body>
</html>