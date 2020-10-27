<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>이력서 작성</title>
	<link href="css/butan.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="css/jquery.bxslider.css"/>
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Noto+Sans+KR:wght@100;500&display=swap" rel="stylesheet"/>
    <link href="css/atemplate.css" rel="stylesheet" type="text/css"/>
    <style rel="stylesheet" type="text/css">	
	   	h2{
	      	text-align: center;
	     	width: 135px;
	     	margin-top: 20px;
	    }	
    	/* input{
    	 width: 100%;
    	 height: 100%;
    	} */
    	textarea{
	    	width: 100%;
	    	height: 100%;
	    	resize: none;
	    	border: 0px;
    	}
   		 th,td,tr,input,textarea{
		     font-size: 20px;
	     }
        /*  table{
		    width: 900px;
		    position: absolute;
		    top: 45%;
		    left: 50%;
	      	transform:translate(-50%, -45%);
		}	
        .buttn{
      		width: 100px;
     		height: 50px;
      		position: absolute;
         	top: 80%;
    		left: 43%;
     		transform:translate(-50%, -50%);
     	}
       .buttn:last-child{
      		width: 100px;
     		height: 50px;
     	 	position: absolute;
         	top: 80%;
    		left: 55%;
     		transform:translate(-50%, -50%);
    	 } */
    </style>

</head>
<body>
<%@ include file="template/myheader.jspf" %>
<%@ include file="template/mymenu.jspf" %>
	<h2>이력서 작성</h2>

	<div id="content">
		<!--content start  -->
		<div class="resume">
		<form method="post">
		<table class="table">
			<tr class="row" >
				<th class="thd" style="height: 20px;">제목</th>
				<td class="tsub"><input type="text" name="resumeTitle" placeholder="제목을 입력해주세요"></td>
	    </tr>
	    <tr class="row">
	    <th class="thd" style="height: 650px;">이력서 내용</th>
				<td class="tsub"><textarea name="resumeContent" placeholder="이력서 내용을 입력해주세요"></textarea></td>
		</tr>
		<tr class="row" style="text-align: center;">
			<td colspan="2">
				<button class="buttn" type="submit">입력</button>
				<button class="buttn" type="reset">취소</button>
			</td>
		</tr>
		
		  
			</table>
		</form>
		</div>
		<!--content end  -->
	</div>
<%@ include file="template/myfooter.jspf" %>
</body>
</html>
