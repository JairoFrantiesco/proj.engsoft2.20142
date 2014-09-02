<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Cadastrar Dispositivo</title>
</head>
<body>
	<c:import url="cabecalho.jsp" />
	<c:import url="menu.jsp" />	
	
	<form method="post" action="update">
		<input name="obj" type="hidden" value="estoque"/>
		<input name="acao" type="hidden" value="insere"/>
		<table align="center">
			<tr>
				<td>IMEI:</td>
				<td><input name="imei" type="text"/></td>
			</tr>
			<tr>
				<td>Nome Dispositivo:</td>
				<td><input name="dispositivo" type="text"/></td>
			</tr>
			
			
			<tr>
				<td align="center" colspan="2">
					<input type="submit" value="Salvar"/>
				</td>
			</tr>
		</table>
	</form>
	<c:import url="rodape.jsp" />
</body>
</html>