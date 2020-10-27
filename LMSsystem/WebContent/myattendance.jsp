
<%@page import="java.util.ArrayList"%>
<%@page import="com.bit.model.dao.AttendanceDao"%>
<%@page import="com.bit.model.dto.AttendanceDto"%>
<%@page import="com.bit.model.dto.StudentDto"%>
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
        	text-align: center;
       }           
      /* .center>table {
	    width: 550px;
	    height:400px;
      	position: absolute;
      	top:35%;
      	left: 50%;
      	transform:translate(-50%, -45%);
  	} */
     th,td,tr{
     	font-size: 20px;
     }
  /* .page{
	   	float: left;
	   	width: 25px;
	   	height:50px;
  	   	margin-top: 470px;
  		position: relative;
   		left: 39%;
   		font-size: 20px;
  } */
  .page{
		text-decoration: none;
		color: gray;
		font-size: 20px;
	}
    </style>
     <script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<body>
<%@ include file="template/myheader.jspf" %>
<%@ include file="template/mymenu.jspf" %>
 	<h2>출결확인</h2>
 	<div id="content">
   <!--content start  -->
<div class="center">

 <table class="table">
    <tr class="row">
    
   <th class="thd">날짜</th>
   <th class="thd">출석확인</th>
    </tr>
 <%
 HttpSession ses= request.getSession();
 StudentDto studentDto= (StudentDto)ses.getAttribute("stuBean");
 
 AttendanceDao dao=new AttendanceDao();

 int count=dao.selectCnt(studentDto.getStudentIdx()); 

ArrayList<AttendanceDto>  list= (ArrayList<AttendanceDto>)request.getAttribute("attendancelist");
   

 %>
 <%
for(int i=0; i<list.size(); i++){
 %>

   
   <tr class="row">
       <td  class="tsub"><%=list.get(i).getAttendDate()%></td>
        <td  class="tsub"><%=list.get(i).getAttendanceStatus() %></td>
        </tr>

<%

}

%>
    </table>
    
    
    <%
  
       
  /*  
  로우수/10
  10 /10 = 1
  
  33 / 10 
 if (총로수우%10) != 0 
    left = 1
 else
   left = 0   
  
   
   
   33 - 33%10 = 30
  
   페이지 링크 수 =( 총 로우수 -로우수%10)10 + left 을 더해줌
  */     
   
     /*  for(int i=0; i<=count; i++){
        
        
  		   start 0                                   page 1 2 3 4 5 
               11                                    
               21
               31
               41
               
               (clickP-1)*10+1          
        
        
        
        if(i%10==0){ */
    %> 
  
    <div style="text-align: center;">
	 <c:if test="${startPage ne 1 }"><a href="myattendance.bit?page=${page-1}" class="page">[이전]</a></c:if>
	 <c:forEach var="i" begin="0" end="4" step="1">
	 <c:if test="${startPage+i <= lastPage }">
	 <a href="myattendance.bit?page=${startPage+i}" class="page" 
	 		<c:if test="${startPage+i eq page }"> style="text-decoration: underline;"</c:if>>${startPage+i}</a>
	 </c:if>
	 </c:forEach>
	 <c:if test="${(lastPage - startPage) > 5 }"><a href="myattendance.bit?page=${page+1}" class="page">[다음]</a></c:if>
	 </div>
    <%
    /*
    }}
    */
    %>
</div>
   <!--content end  -->
  <%@ include file="template/myfooter.jspf" %>
</body>
</html>
