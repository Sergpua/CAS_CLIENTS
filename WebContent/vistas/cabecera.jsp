<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div class="izq">
<h2>Gestion de Clientes</h2>
</div>
<div class="der">
<%if (session.getAttribute("login")==null){ %>
<form action="" method="POST">
	<table>
		<tr>
				<td>Login:</td>
				<td><input type="text" name="login" maxlength="8"></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="pass" maxlength="8"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Login"></td>
			</tr>
	</table>
</form>
<%}else{ %>
<h5>Usario conectado: -</h5>
<%} %>
</div>
<div class="clear"></div>



</body>
</html>