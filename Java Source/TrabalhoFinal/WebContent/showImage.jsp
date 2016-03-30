<%@page import="GestAl.GestorP"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	String produto = request.getParameter("produto");
	if(produto != null) {
		%>
		<%byte[] imagem = GestorP.criarImagem(produto); %>
		<img alt="Embedded Image" src="data:image/png;base64,<%= new String(imagem, "ISO-8859-1")%>"/>
		<%
	}
	%>
</body>
</html>