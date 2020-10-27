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
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Noto+Sans+KR:wght@100;500&display=swap" rel="stylesheet">
  <script type="text/javascript" src="js/jquery-1.12.4.js">
    </script>
    <script type="text/javascript">
        $(document).ready(function() {
            $('#menu>ul>ol>li').hide();
            
            $('#menu>ul>ol').mouseenter(function(){
                $(this).css('cursor','Default');
                $(this).find('li').css('visibility','visible');
                $(this).find('li').stop().slideDown(1000);
            }).mouseleave(function(){
                $(this).find('li').css('visibility','visible');
                $(this).find('li').stop().slideUp(1000);
            });
            $('#menu>ul>ol>li>a').on('mouseenter',function(){
                $(this).css('background-color','#F5F5F5').css('border-radius','10px');
            });
            $('#menu>ul>ol>li>a').on('mouseleave',function(){
                    $(this).css('background-color','white').css('border-radius','10px');
            });
        });
    </script>


</head>
<style rel="stylesheet" type="text/css">
        
        #content{
        	height: 1000px;
        }
        .content1{
        font-family: 'Noto Sans KR', sans-serif;
      width:400px;
       margin: 0px auto;
       position:relative;
      float: left;
       font-size: 14px;
        }
            .content2{
        font-family: 'Noto Sans KR', sans-serif;
       width:400px;
       margin: 0px auto;
       position:relative;
       float: left;
        font-size: 14px;
  
       padding-top: 20px;
        }
            .content3{
             font-family: 'Noto Sans KR', sans-serif;
       width:400px;
       margin: 0px auto;
       position:relative;
       float: left;
       font-size: 14px;
        }
        
      .content1,.content2,.content3{
      padding: 117px;
      }
        b{
         font-size: 30px;
            font-family: 'Noto Sans KR', sans-serif;
        }
     li{
     list-style: none;
    
      font-size: 13px;
     }
    .content1>ul>li>img{
       width: 300px;
     height: 300px;
      
        }
         .content2>ul>li>img{
      width: 300px;
     height: 300px;
      
        }
   
        .content3>ul>li>img{
        width: 300px;
     height: 300px;
      
        }
        
         .content1>ul>li:nth-last-child(2){
        
            font-family: 'Noto Sans KR', sans-serif;
        font-size: 15px;
        font-weight:bold;
        
        }
         .content2>ul>li:nth-last-child(2){
               font-family: 'Noto Sans KR', sans-serif;
        font-size: 20px;
         }
          .content3>ul>li:nth-last-child(2){
                font-family: 'Noto Sans KR', sans-serif;
        font-size: 20px;
         }
       
    </style>
 
<body>
  <%@include file="template/header.jspf" %>
<%@include file="template/menu.jspf" %>
   <!--content start  -->
   <div id="content">
    <div class="content1">
        <ul class="title">
            <li><img src="img/t1.jpg" alt=""/></li>
            <li>
          
                <b>설민석 강사</b>
            </li>
            <li>
                외우지 말고[이해]하세요.<br>이해하고 코딩은 [익숙]해지세요.<br>
                익숙 할 때 까지 [존버] 하세요.
            </li>
            <li>
                나폴레옹이 이렇게 말했다 합니다. “오늘 나의 불행은 언젠가 내가 잘못 보낸 시간의 보복이다.”
                <br>
                읽을 때 마다. 소름이 오싹 돋습니다. 교육기간 동안 잘못 보낸 시간이 아닌 목표를 향해 최선을 다하는
                <br>
                시간이 될 수 있도록 강의 하겠습니다. 수강생 여러분도 최선을 다해주세요.
            </li>
        </ul>
        </div>
        <div class="content2">
        <ul class="title">
            <li><img src="img\t2.jpg" alt=""/></li>
            <li>
           
                <b>정승제 강사</b>
            </li>
            <li>
                개발이란 남들을 이해하는 과정입니다.
            </li>
            <li>
                개발을 잘한다는 것은 무엇일까요? 특정 언어를 잘 다루는 것? 프로그래밍이란 프로그램 언어란
                <br>
                도구를 가지고 무언가를 만드는 과정입니다. 그러니 가장 중요한 것은 무엇을 어떻게 만들 것인가에
                <br>
                대한 질문입니다. 항상 사용자를 고민하는 개발자, 항상 보다 나은 방법을 고민하는 개발자가 되려고 노력하세요.
                <br>
            </li>
        </ul>
       </div>
       <div class="content3">
        <ul class="title">
            <li><img src="img/t3.jpg" alt=""/></li>
            <li>
          
                <b>황현필 강사</b>
            </li>
            <li>
                IT를 통해 인생을 변화시키세요
            </li>
            <li>
                누구한테나 공부는 어려울 때가 종종 있습니다. 스스로를 안심시키며 꾸준히 매일 보이지 않는 노력을 해주시길 당부 드립니다.
                <br>
                이곳에서 공부하는 성실한 노력은 우리가 개발자로서 자리 잡게 해주고 혹시 우리 운명이 다른 곳으로 이끌어도
                <br>
                그곳에서 성공의 자양분이 될 것을 확신합니다. <br>
</li>
</ul>
           </div>
   </div>
   <!--content end  -->
   
<%@include file="template/footer.jspf" %>
 
</body>

</html>
