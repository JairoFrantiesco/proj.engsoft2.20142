<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script
    src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
<script>
	var map;
	function initialize() {
	  var mapOptions = {
	    zoom: 10,
	    center: new google.maps.LatLng(-30.0746, -51.0171)
	  };
	  map = new google.maps.Map(document.getElementById('map-canvas'),
	      mapOptions);
	  
	  setmarker(map); 
	  setmarker2(map);
	}
	
	function setmarker(map){  
        
        var lat = -30.0746;  
        var lng = -51.0171;  
          
        var marker = new google.maps.Marker({  
              
            position: new google.maps.LatLng(lat, lng),  
            map: map,  
            title: "endere�o",  
            clickable: true 
              
        });  
          
    }  
	
	function setmarker2(map){  
        
        var lat = -30.0746;  
        var lng = -51.0560;  
          
        var marker = new google.maps.Marker({  
              
            position: new google.maps.LatLng(lat, lng),  
            map: map,  
            title: "endere�o",  
            clickable: true 
              
        });  
          
    }  

	google.maps.event.addDomListener(window, 'load', initialize);
</script>
</head>
<body>
	<c:import url="cabecalho.jsp" />
	<c:import url="menu.jsp" />

 <div id="map-canvas" style="height:450px; width:800px"></div>
 
 <c:import url="rodape.jsp" />
</body>
</html>