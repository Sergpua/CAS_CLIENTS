<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="css/style.css" rel="stylesheet">
</head>
<body>
<%
String contenido = "vistas/saludo.html";
	if(request.getAttribute("contenido")!=null){
		contenido = (String)request.getAttribute("contenido");}
%>
	<div id="contenedor">
		<div id="cabecera"><jsp:include page="vistas/cabecera.jsp"/></div>
		<div id="centro">
			<div id="menu"><jsp:include page="vistas/menu.html"/></div>
			<div id="cuerpo">
				<jsp:include page="<%=contenido%>"/>
			</div>
		</div>
		<div class="clear"></div>
		<div id="pie"><jsp:include page="vistas/pie_pagina.jsp"/></div>
	</div>
</body>
</html>