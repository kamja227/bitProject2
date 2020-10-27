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
           /* .buttn{
      width: 100px;
     height: 50px;
      position: absolute;
         top: 60%;
    left: 43%;
     transform:translate(-50%, -20%);
     }
                 
    
       .buttn:last-child{
      width: 100px;
     height: 50px;
      position: absolute;
         top: 60%;
    left: 55%;
     transform:translate(-50%, -20%);
     } */
     
     
     input{
     font-size: 20px;
     border:0px;
     width: 100%;
     height: 100%;
     }
     
     th,td,tr{
     font-size: 20px;
     }
    

    .table{
  
	    width: 70%;
	  height: 450px;
	  margin-top: 0px;
        /* position: absolute;
         top: 35%;
    left: 50%;
      transform:translate(-50%, -35%); */
      
    }
    
    </style>
        <script type="text/javascript" src="js/jquery-1.12.4.js"></script> 
          
     
    <script type="text/javascript" src="js/buttonjs.js"></script>
    <script type="text/javascript">
    
       $(document).ready(function() {
    	  
    		  $('.buttn').eq(0).on('click',function(){
  		    	 alert('회원정보가 수정되었습니다.').one();
  		     });
    	   
    	  
    
    	   $('.buttn').eq(0).attr("disabled", false);
    	   $('input').eq(0).click(function() {
				alert('이름은 변경할수없습니다.');
			});
        	$('input').keyup(function() {
        		 var pass1=$('#password1').val();
                 var pass2=$('#password2').val();
            if(pass1 !=""||pass2 !=""){
            
            if(pass1==pass2){
            	  $('h5').html('비밀번호 일치합니다.').css('color','blue');
            	  $('.buttn').eq(0).attr("disabled", false);
               }else if(pass1!=pass2||pass2!=pass1){
            	   $('h5').html('비밀번호 일치하지 않음').css('color','red');
            	   $('.buttn').eq(0).attr("disabled", true);
               }
            }
            

            keyup1();
            keyup2();
            keyup3();

            function keyup1() {
            	var contactRegExp=RegExp(/[^0-9/-]$/);
                var contactval=$('#contact').val();
                if(contactRegExp.test(contactval)){
                    $("#contact").val("")
               	alert('핸드폰번호를기입하시오.').one();
               }
			}
           
            function keyup2() {
            	var emailRegExp=RegExp(/[^a-zA-Z0-9/@/./s$]$/);
                var emailval=$('#email').val();
                if(emailRegExp.test(emailval)){
                    $("#email").val("")
               	alert('이메일 양식에맞게작성해주세요').one();
               }
			}
            
            
            function keyup3() {
            	var passwordRegExp=RegExp(/[^a-zA-Z0-9/s$]$/);
                var passwordval=$('#password1').val();
                if(passwordRegExp.test(passwordval)){
                    $("#password1").val("")
               	alert('영어대소문자,숫자만사용가능합니다.').one();
               }
			}

			});
  
        });
        	
        	</script>
</head>
<%@include file="template/myheader.jspf" %>
<%@include file="template/mymenu.jspf" %>
<body>
	<h2>정보수정</h2>
	<div id="content">
	<!--content start  -->
 <form class="send" method="post">
 <table class="table">
<c:forEach  items="${student}" var="bean">
<tr class="row">
<th class="thd">이름</th>
<td class="tsub"><input type="text" name="name" id="name" readonly="readonly" value="${bean.name}" /></td>
</tr>
<tr class="row">
<th class="thd">연락처</th>
<td class="tsub"><input type="text"  name="contact"  id="contact" value="${bean.contact}" maxlength="13"/></td>
</tr>
<tr class="row">
<th class="thd">이메일</th>
<td class="tsub"><input type="text" name="email"  id="email" value="${bean.email}" maxlength="35"/></td>
</tr>
<tr class="row">
<th class="thd">변경할<br/> 비밀번호</th>
<td class="tsub"><input type="password" name="password"  id="password1" value="" placeholder="비밀번호 최대12자 " maxlength="12"/></td>
</tr>
<tr class="row">
<th class="thd">변경할 <br/>비밀번호 <br/>확인</th>
.<td class="tsub"><input type="password" id="password2" value="" placeholder="비밀번호 확인" maxlength="12""/><font id="Notice" size="2"></font></td>
</tr>
</c:forEach>
<tr style="text-align: center;">
	<td colspan="2">
		<button  class="buttn" type="submit" style="margin-right: 10px;">수정</button>
		<button  class="buttn"  type="reset" style="margin-left: 10px;">취소</button>
	</td>
</tr>
</table>
</form>
	<!--content end  -->
</div>
</body>
</html>
