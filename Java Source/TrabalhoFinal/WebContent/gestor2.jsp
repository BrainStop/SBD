<%@page import="GestAl.GestorP"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="main_style.css">
<title>Gestor 2</title>
</head>
<body>
	<header>
		<section id="titlesection">
			<h1 id="title">Maquinas de Venda</h1>
			<h4 id="subtitle">Todo o tipo de produtos!</h1>
		</section>
	</header>
	<nav>
		<a href="administrador.jsp">Administrador</a>
		<a href="funcionario.jsp">Funcionario</a>
		<ul>
			<li>
				<a id="gestor">Gestor</a>
				<ul id="dropdown">
					<li><a href="gestor1.jsp">Catalogo</a></li>
					<li><a href="gestor2.jsp">Condutor</a></li>
					<li><a href="gestor3.jsp">Stock</a></li>
					<li><a href="gestor4.jsp">Produto Lucro</a></li>
					<li><a href="gestor5.jsp">Maquina Lucro</a></li>	
					<li><a href="gestor6.jsp">Produto Invalidos</a></li>
					<li><a href="gestor7.jsp">Export XML</a></li>	
				</ul>
			</li>
		</ul>
	</nav>
	<main id="query">
	<%
	String matricula = request.getParameter("matricula");
	String data = request.getParameter("validade");
	String condutor = null;
	if(matricula!= null && data != null) {
		condutor = GestorP.gestor2(data, matricula);
	}
	if(condutor != null) {
	%>
		<%=condutor %>
	<%
	}
	else {
	matricula = null;
	data = null;
	%>
	<script>
	function mydate() {
		document.getElementById("dt").hidden=false;
		document.getElementById("ndt").hidden=true;
	}
	function mydate1() {
		d=new Date(document.getElementById("dt").value);
		dt=d.getDate();
		mn=d.getMonth();
		mn++;
		yy=d.getFullYear();
		document.getElementById("ndt").value=dt+"/"+mn+"/"+yy
		document.getElementById("ndt").hidden=false;
		document.getElementById("dt").hidden=true;
	}
	</script>
	<form>
	Matricula<input type="text" name="matricula"/><br>
	<input type="button" Value="Data" onclick="mydate();" />
	<input type="date" id="dt" name="validade" onchange="mydate1();" hidden/>
	<input type="text" id="ndt" onclick="mydate();" hidden /><br>
	<input type="submit" />
	</form>
	<%
	}
	%>
	</main>
	<footer></footer>
</body>
</html>