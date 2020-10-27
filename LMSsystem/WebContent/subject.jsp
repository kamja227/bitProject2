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
    <style rel="stylesheet" type="text/css">
     
   /*     #bx{
       
       margin: 0px auto;
       display: block;
        padding-top: 150px;
         padding-left: 28%;
         height: 1000px;
       } */
        
        
        ul>li>img{
       width: 40%;
    height: 40%;  
       margin:10%;
       position: relative;
            top:30%;
            left: 10%;
        padding: 20px;
        }
        
        
        
        .buttons>input{
          width: 100px;
           height: 100px;
           background-color: blue;
           border: solid 1px black;
           color: white;
        }
        
        .buttons{
          position: absolute;
           top: 20%;
           right: 52%;
        
        }
        
    </style>

    <script type="text/javascript" src="js/jquery-1.12.4.js"></script>
	<script type="text/javascript" src="js/jquery.bxslider.js"></script>
    <script type="text/javascript" src="js/menu.js"></script>
     <script type="text/javascript">

     $(function() {
    	 $('ul>li>img').eq(0).show();
    	 $('ul>li>img').eq(1).hide();
		 $('ul>li>img').eq(2).hide();
    	 $('input').eq(0).click(function() {
    		 $('ul>li>img').eq(0).show();
    		 $('ul>li>img').eq(1).hide();
    		 $('ul>li>img').eq(2).hide();
    		 
			});
    	 $('input').eq(1).click(function() {
    		 $('ul>li>img').eq(1).show();
    		 $('ul>li>img').eq(0).hide();
    		 $('ul>li>img').eq(2).hide();
    		 
			});
    	 $('input').eq(2).click(function() {
    		 $('ul>li>img').eq(2).show();
    		 $('ul>li>img').eq(1).hide();
    		 $('ul>li>img').eq(0).hide();
    		 
			});


     });
     

  
    </script>


    
   
</head>

<body>
<%@include file="template/header.jspf" %>
<%@include file="template/menu.jspf" %>

	<!—content start  —>
     
     <div id="content">
     
 <ul>
<li><img src="img/java.jpg" /></li> 
<li><img src="img/javascript.jpg"/></li> 
<li><img src="img/web.jpg" /></li> 
</ul>

</div>
<div class="buttons">
<input type="button" value="JAVA">
<input type="button" value="JAVA SCRIPT">
<input type="button" value="WEB">
</div>
	<!—content end  —>    
    <%@ include file="template/footer.jspf" %>
   
</body></html>