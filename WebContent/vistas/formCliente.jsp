<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%ArrayList<String> paises = (ArrayList)request.getAttribute("paises");%>
<form action="grabar.do" method="POST">
	<table>
		<tr>
			<td>ID Cliente:</td>
			<td><input type="text" name="idCliente" maxlength="5"></td>
		</tr>
		<tr>
			<td>Nombre:</td>
			<td><input type="text" name="nombre" maxlength="20"></td>
		</tr>
		<tr>
			<td>Pais:</td>
			<td>
				<select name="pais">
					<%for(int i = 0; i<paises.size(); i++){%><option><%=paises.get(i).toString()%></option><%}%>
				</select>
			</td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Grabar"></td>
		</tr>
	</table>

</form>
</body>
</html>