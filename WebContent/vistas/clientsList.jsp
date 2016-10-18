<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.ArrayList, es.curso.clientes.modelo.Cliente"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table class="table">
<tr>
<th>ID</th>
<th>NOMBRE</th>
<th>PAIS</th>
</tr>
<%
	ArrayList<Cliente> clientes = (ArrayList<Cliente>)request.getAttribute("clientes");
	Cliente cli;
	for(int i = 0; i<clientes.size(); i++){
		cli=clientes.get(i);
%>
	<tr>
	<td><%=cli.getIdcliente()%></td>
	<td><%=cli.getNombre()%></td>
	<td><%=cli.getPais()%></td>
	</tr>
	<%} %>
</table>

</body>
</html>