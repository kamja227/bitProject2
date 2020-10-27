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
        *{
        	font-family: 'Noto Sans KR', sans-serif;
        }
        #content{
          height: 1700px;
          width: 1500px;
          padding-top: 100px;
          text-align: center;
          padding-left: 180px;
        }
        .Intro1,Intro2{
        	width: 700px;
        	height: 700px;
        }
        .Intro1{
        	width: 800px;
        }
       	.slider>img{
       		margin-left:320px;
            max-width:750px;
            max-height:750px;
            text-align: center;
        }
    </style>
    <script type="text/javascript" src="js/jquery-1.12.4.js"></script>
    <script type="text/javascript" src="js/jquery.bxslider.js"></script>
    <script type="text/javascript" src="js/menu.js"></script>
    <script>  
    $(document).ready(function(){
    	$('.slider').bxSlider({
    		mode:'fade',
    		captions:true	
    	});
    	
    });
    </script>
</head>

<body>
<%@ include file="template/header.jspf" %>
<%@ include file="template/menu.jspf" %>
    <div id="content">
	<!--content start  -->
	
        <img src="img/main-intro.jpg"/>
        
        <hr>
			
			    <h2>대표 인사말</h2>
	        	<span class="Intro1">
	        	
조현정회장<br>
최신 소프트웨어를 개발하고 이를 바탕으로<br>
SI사업과 교육사업을 병행하고 있는 교육센터입니다.<br>
빠르게 발전하는 IT기술분야에 최적의 교육해법을 제안하여 준비된 인재를<br>
양성하는 IT전문 교육센터입니다. IT전문가를 향한 꿈, IT분야 취업의 희망!<br>
교육센터 전 임직원이 최선을 다해 지원해드리겠습니다.<br><br><br>
				</span>
				<span class="Intro2">
				시스템 통합 관련 프로젝트와 소프트웨어 개발·보급·자문, IT관련 소프트웨어 전문 인력 양성에 중점을 두고 있습니다.

스마트폰 개발 기술 및 자바, Web2.0 기술 등을 활용한 시스템 통합과 소프트웨어, 솔루션 개발을 맡아<br>
전방에서 진두지휘하고 그것을 바탕으로 비트캠프를 운영하고 있습니다.<br><br>

특히, 개발자 커뮤니티 대표들과의 최신 서적 저자 등 유망 강사진을 통해 최적의 교육솔루션을 제공하고 있으며, 노동부와 중기청,<br>
한국산업인력공단, 한국산업기술진흥원, 정보통신산업진흥원 등으로부터 인정받아 국내 및 해외취업 과정을 정부지원 하에<br>
수행하고 있습니다. 한편, 현대해상 프로젝트, 하이닉스, 마포구청 등 대규모 SI개발 프로젝트에 우수인력을 파견하고 있고,<br>
이렇게 다양한 SI영역에서의 사업은 비트캠프 출신의 수료생들을 적극 활용하고 있습니다. 실무위주의 교육을 받은 수료생들은<br>
채용은 물론 미취업 시 실무 프로젝트 투입기회 제공 등 다각도의 지원을 하고 있습니다.<br><br>

교육센터는 "회사 구성원 대부분인 IT서적 저자, 개발자, 전문교육 수료생 출신이기 때문에 전문성과<br>
결속력이 뛰어나다"고 생각합니다. 앞으로 우리 교육센터는 "실무중심 교육 및 신기술을 선도하는 교육센터"를 만들어가겠습니다.<br><br>
				</span>
				<hr>
	       		<h3>회사 연혁</h3>
				<div class="slider">
					<img src="img/history0.jpg"/>
					<img src="img/history1.jpg"/>
					<img src="img/history2.jpg"/>
					<img src="img/history3.jpg"/>
					<img src="img/history4.jpg"/>					
				</div>
        
	<!--content end  -->
    </div>
    <%@ include file="template/footer.jspf" %>
   
</body></html>
