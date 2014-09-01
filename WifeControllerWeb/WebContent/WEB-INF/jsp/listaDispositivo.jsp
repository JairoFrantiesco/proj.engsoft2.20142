<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Lista de Estoques</title>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>
	<c:import url="cabecalho.jsp" />
	<c:import url="menu.jsp" />
	
	<a href="/WifeControllerWeb/dispositivo/new">Novo Dispositivo</a>
	<table width="99%" align="center" class="tabela">
		<thead>
			<tr>
				<td>IMEI</td>
				<td>NOME DISPOSITIVO</td>
			</tr>
		</thead>
		<c:if test="${ empty objLista }">
			<tr>
				<td colspan="6">Nenhum registro cadastrado.</td>
			</tr>
		</c:if>
		<c:forEach var="estoque" items="${objLista}">
			<tr bgcolor="#${estoque.id % 2 == 0 ? 'aaee88' : 'ffffff' }" >
				
				<td>${estoque.imei}</td>
				<td>${estoque.nmDispositivo}</td>
			</tr>
		</c:forEach>			
	</table>
	<c:import url="rodape.jsp" />
</body>
</html>