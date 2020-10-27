<%@page import="com.bit.model.dto.ClassDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <title> Title </title>
    <link rel="stylesheet" type="text/css" href="css/template.css"/>
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Noto+Sans+KR:wght@100;500&display=swap" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="css/jquery.bxslider.css"/>
    <link rel="stylesheet" type="text/css" href="css/butan.css"/>
    <style rel="stylesheet" type="text/css">
        #content{
        
          height: 1600px;
        }
        .slider>div>img{
            margin-top: 100px;
            
            width: 100%;
            height: 300px;
        }
        .login{
        	border:1px solid;
        	float:right;
        	text-align:center;
        	margin-right:10%;
            display: inline-block;
            font-family: 'Noto Sans KR', sans-serif;
            border-color: silver;
            width: 280px;
            background-color: #F5F5F5;
            border-radius:5px;
        }
        .login>h2{
        	margin:10px;
            text-align: center;
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
            outline: 0;
        }
        
        .btn>a{
            color: white;
            text-decoration: none;
            display: block;
        }
        .btn>a:hover{
            color:white;
        }
        #id{
        	margin-left:15px;
        }
        #id,#password{
        	padding: 10px;
        	
        }
        .miniclass{
        	text-align:center;
        	border:2px groove;
        	border-color:#D5D5D5;
        	display:inline-block;
        	width:280px;
        	height:350px;
        	border-radius:5px;
        	margin:10px;
        	margin-bottom: 25px;
        }
        .miniclass>img{
        	margin:0px auto;
        	width:278px;
        	height: 200px;
        }
        .content{
        	float:left;
        	width:980px;
        	height:1000px;
        	margin-right:0px;
        	margin-left:20%;
        }
        h3{
        	height:27.41px; 
        	
        }
        .more{
        	background-color:#F6F6F6;
        	border:1px;
        	border-radius:10px;
        	height:50px;
        	font-size:20px;
        	margin:10px;
        	display: block;
        	width:900px;
        	outline: 0;
        }
        .more:ACTIVE{
        	border:1.5px solid #6799FF;
        }
        .more:visited{
        	border:1.5px solid #6799FF;
        }
		
    </style>

    <script type="text/javascript" src="js/jquery-1.12.4.js"></script>
	<script type="text/javascript" src="js/jquery.bxslider.js"></script>
    <script type="text/javascript" src="js/menu.js"></script>
   <% 
   		com.bit.model.dto.StudentDto stuBean = (com.bit.model.dto.StudentDto)request.getAttribute("stuBean"); 
   		com.bit.model.dto.EmployeeDto empBean = (com.bit.model.dto.EmployeeDto)request.getAttribute("empBean");
	%>
<script type="text/javascript">
   $(document).ready(function() {
	   
      $('.slider').bxSlider({
          mode:'fade',
          captions:true
       });
      $('.more').on('mouseenter',function(){
    	 $(this).css('border','1.5px solid #6799FF').on('mouseleave',function(){
        	 $(this).css('border','1px');
         });
      })
      var cnt=0;
      $('.more').on('click',function(){
    	  $('.miniclass').css('display','inline-block');
    	  $('#content').css('height','2200px');
    	  
      });
      
      $('form').submit(function(){
         var id = $('#id').val();
         var password = $('#password').val();
         
         if(id == '') {
             alert("아이디를 입력해주세요");
                $("#id").focus();
         }
         if(id != '' && password== '') {
             alert("비밀번호를 입력해주세요");
                $("#password").focus();
         }
         if(id != '' && password != '') {
            var param = "id=" + id + "&password=" + password;
            $.ajax({
               "type" : "post",
                   "data" : param,
                   "url" : "login.bit",
                   "success" : function(data) {
                	 var fail =$(data).find('fail').text();
					 if(fail == 'loginFail') {
                         alert("존재하지 않는 계정입니다.\n아이디와 비밀번호를 다시 입력해주세요.");   
                     }else{
                       window.location.replace("index.bit"); 
                     }
                   }, // success
                   "error": function(request, status, error){
                       //alert("code:" + request.status + "\n" + "error:" + error);
                      alert("존재하지 않는 계정입니다.\n아이디와 비밀번호를 다시 입력해주세요.");
                   } // error
            }); // ajax
         }
         
         
         return false;   
      }); // submit
    
   });
</script>
</head>

<body>
   <%@ include file="template/header.jspf" %>
   <%@ include file="template/menu.jspf" %>
    <div id="content">
   <!--content start  -->
   <div class="slider">
        <div><img src="img/1.jpg"/></div>
        <div><img src="img/2.jpg"/></div>
        <div><img src="img/3.jpg"/></div>
        <div><img src="img/4.jpg"/></div>
   </div>
   <div class="content">
   <button class="more" type="button">교육 과정 더보기</button> 
   
   <%
   		List<ClassDto> list = (List<ClassDto>)request.getAttribute("classAll");
   		for(int i=0;i<list.size();i++){
   %>
   <% if(i<6){ %>
   <div class="miniclass" onClick="location.href ='http://localhost:8080/LMSsystem/coursedetail.bit?classIdx=<%=list.get(i).getClassIdx() %>'" style="cursor:pointer;">
   		<%
   			String miniImg="";
   			if(list.get(i).getSubjectTitle().equals("자바")){
   				miniImg="java1";
   			}else if(list.get(i).getSubjectTitle().equals("프레임")){
   				miniImg="framework";
   			}else if(list.get(i).getSubjectTitle().equals("웹")){
   				miniImg="web1";
   		%>
   		<%} %>
   		<img src="img/<%=miniImg %>.jpg"/>
   		<br/>
   		<br/>
   		<h3><%=list.get(i).getClassTitle() %></h3>
   		<br/>
   		<span>교육기간 : <%=list.get(i).getStartDate() %>~ <%=list.get(i).getEndDate() %></span>
   </div>
   <%}else if(i<13){ %>
   <div class="miniclass" onClick="location.href ='http://localhost:8080/LMSsystem/coursedetail.bit?classIdx=<%=list.get(i).getClassIdx() %>'"style="display: none;">
   		<%
   			String miniImg="";
   			if(list.get(i).getSubjectTitle().equals("자바")){
   				miniImg="java1";
   			}else if(list.get(i).getSubjectTitle().equals("프레임")){
   				miniImg="framework";
   			}else if(list.get(i).getSubjectTitle().equals("웹")){
   				miniImg="web1";
   		%>
   		<%} %>
   		<img src="img/<%=miniImg %>.jpg"/>
   		<br/>
   		<br/>
   		<h3><%=list.get(i).getClassTitle() %></h3>
   		<br/>
   		<span>교육기간 : <%=list.get(i).getStartDate() %>~ <%=list.get(i).getEndDate() %></span>
   </div>
   <%}else break; %>
   <% if((i+1)%3==0){%>
   			<br/>
   		<%} %>
   <%} %>
   </div>
    
      <c:if test="${stuBean eq null and empBean eq null}">
         <form action="login.bit" method="post">
            <div class="login">
                   <h2>비트캠프 로그인</h2>
                   <br/>
                   <div>
                      <label for="id">아이디</label>
                    <input type="text" id="id" placeholder="아이디를 입력하세요" />
                 <br/><br/>
                    <label for="password">비밀번호</label>
                    <input type="password" id="password" placeholder="비밀번호를 입력하세요"/><br/><br/>
                   </div>
                <div>
                 <button class="btn lbtn" type="submit">로그인</button>
                 <button class="btn" type="button"><a class="an" href="join.bit">회원가입</a></button>
                 <button class="btn" type="button"><a class="an" href="find">아이디 / 비밀번호 찾기</a></button>          
                </div>
                
          </div>
      </form>
      </c:if>
      
      <c:if test="${stuBean ne null}">
         <div class="login">
            <h2>${stuBean.name }님 환영합니다.</h2>
            <button class="btn" type="button"><a href="myattendance.bit" class="an">마이페이지</a></button>
            <button class="btn" type="button"><a href="logout.bit" class="an">로그아웃</a></button>
         </div>
      </c:if>
      <c:if test="${empBean ne null}">
         <div class="login">
            <h2>${empBean.name }님 환영합니다.</h2>
            <button class="btn" type="button"><a href="admin/ahome.bit" class="an">관리자페이지</a></button>
            <button class="btn" type="button"><a href="logout.bit" class="an">로그아웃</a></button>
         </div>
      </c:if>
   <!—content end  —>
   </div>
   <%@ include file="template/footer.jspf"%>

</body>
</html>