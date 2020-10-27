$(document).ready(function() {
            $('#menu>ul>ol>li').hide();
            
            $('#menu>ul>ol').mouseenter(function(){
                $(this).css('cursor','Default');
                $(this).find('li').css('visibility','visible');
                $(this).find('li').stop().slideDown(500);
            }).mouseleave(function(){
                $(this).find('li').css('visibility','visible');
                $(this).find('li').stop().slideUp(500);
            });
            $('#menu>ul>ol>li>a').on('mouseenter',function(){
                $(this).css('background-color','#F5F5F5');
            });
            $('#menu>ul>ol>li>a').on('mouseleave',function(){
                    $(this).css('background-color','white');
            });
           
			
        });