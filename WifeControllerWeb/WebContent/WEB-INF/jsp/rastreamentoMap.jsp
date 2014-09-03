<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html">
<title>Rastreamento</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script
    src="http://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
<script>
	var map;
	function initialize() {
	  var mapOptions = {
	    zoom: 8,
	    center: new google.maps.LatLng(-30.0746, -51.0171)
	  };
	  map = new google.maps.Map(document.getElementById('map-canvas'),
	      mapOptions);
	  
	  var datahora = "";
	  <c:forEach var="s" items="${objLista}">
	    datahora = "${s.data}" + " - " + "${s.hora}";
	  	setmarker(map, "${s.gpsLat}", "${s.gpsLong}", "${s.dispositivo.nmDispositivo}", datahora); 
	  </c:forEach> 
 
	}
	
	function setmarker(map, lat, lng, dispositivo, datahora){   
          
        var marker = new google.maps.Marker({  
   
            position: new google.maps.LatLng(lat, lng),  
            map: map,  
            title: dispositivo,  
            clickable: true,
            animation: google.maps.Animation.DROP
              
        });  
        
        var infowindow = new google.maps.InfoWindow(), marker;
        
        var contentString = '<h2>' +dispositivo+ '</h2>' 
        	+ '<p>Data-hora: ' +datahora+ '</p>'; 
        	
   	 
    	google.maps.event.addListener(marker, 'click', (function(marker, i) {
    	    return function() {
    	        infowindow.setContent(contentString);
    	        infowindow.open(map, marker);
    	    }
    	})(marker));
          
    }  
	
	

	google.maps.event.addDomListener(window, 'load', initialize);
</script>
</head>
<body>
	<c:import url="cabecalho.jsp" />
	<c:import url="menu.jsp" />

	<table align="center">
			<tr>
				<td>Selecione o Dispositivo:</td>
				<td>
				<select>

					<c:forEach var="s" items="${lstDisp}">
					
					<option value="${s.imei}">${s.nmDispositivo}</option>
	  
	  				</c:forEach>
	
				</select>
				</td>
			</tr>
			<tr></tr>
	</table>


 <div id="map-canvas" style="height:450px; width:800px; left:20%;"></div>
 
 <c:import url="rodape.jsp" />
</body>
</html>
