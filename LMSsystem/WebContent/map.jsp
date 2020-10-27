<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비트캠프</title>
<link rel="stylesheet" type="text/css" href="css/template.css"/>
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Noto+Sans+KR:wght@100;500&display=swap" rel="stylesheet"/>
     <style rel="stylesheet" type="text/css">
      #content{
         height: 1000px;
      }
      .title_bar {
         background-color:#041d79;width:4px;height:19px;display:inline-block;vertical-align:middle;
      }
      .title_txt {
         color:#041d79;font-size:18px;font-weight:900;margin-left:20px;display:inline-block;vertical-align:middle;
      }
      #maparea{
         margin: 150px 450px;
      }
      #map{
         margin-top:18px;
         margin-bottom: 20px;
      }
      #maparea>a{
         text-decoration: none;
         color:#041d79;
         font-size: 18px;
         font-weight:900;
         text-align: center;
      }
      #maparea>a:hover{
      	text-decoration: underline;
      	color: orange;
      }
    </style>
      <script type="text/javascript" src="js/jquery-1.12.4.js"></script>
   <script type="text/javascript" src="js/jquery.bxslider.js"></script>
    <script type="text/javascript" src="js/menu.js"></script>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c954ec3ced724076c8e3816584ff4afc&libraries=services,clusterer,drawing"></script>
<script type="text/javascript">
   $(document).ready(function() {
      var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
       mapOption = {
           center: new kakao.maps.LatLng(33.306055, 126.289577), // 지도의 중심좌표
           level: 3 // 지도의 확대 레벨
       };  

   // 지도를 생성합니다    
   var map = new kakao.maps.Map(mapContainer, mapOption); 

   // 주소-좌표 변환 객체를 생성합니다
   var geocoder = new kakao.maps.services.Geocoder();

   // 주소로 좌표를 검색합니다
   geocoder.addressSearch('제주특별자치도 서귀포시 안덕면 신화역사로 15', function(result, status) {

       // 정상적으로 검색이 완료됐으면 
        if (status === kakao.maps.services.Status.OK) {

           var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

           // 결과값으로 받은 위치를 마커로 표시합니다
           var marker = new kakao.maps.Marker({
               map: map,
               position: coords
           });
          
           // 인포윈도우로 장소에 대한 설명을 표시합니다
           var infowindow = new kakao.maps.InfoWindow({
               content: '<div style="width:150px;text-align:center;padding:6px 0;">비트캠프 제주</div>'
           });
           infowindow.open(map, marker);

           // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
           map.setCenter(coords);
       } 
   });    
   });
</script>
</head>
<body>
   <%@ include file="template/header.jspf" %>
   <%@ include file="template/menu.jspf" %>
    <div id="content">
   <!--content start  -->
   <div id="maparea">
      <span class="title_bar"></span>
      <div class="title_txt">비트캠프 제주</div>
      <div id="map" style="width:800px; height:640px;"></div>
      <a href="https://map.kakao.com/link/map/비트캠프 제주,33.306055, 126.289577">☞&nbsp;&nbsp;비트캠프 제주 오시는 길</a>
   </div>
   <!--content end  -->
   </div>
   <%@ include file="template/footer.jspf"%>
</body>
</html>