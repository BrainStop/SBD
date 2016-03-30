<%@page import="GestAl.GestorP"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="main_style.css">
<title>Administração</title>
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
	<%
	String nserie = request.getParameter("nserie");
	String modelo = request.getParameter("modelo");
	String pontodevenda = request.getParameter("pontodevenda");
	System.out.println(request.getParameter("escolha.value"));
	if(nserie!=null && modelo!=null && pontodevenda!=null)
		GestorP.novaMaquina(nserie, modelo, pontodevenda);
	else if(nserie!=null && modelo==null && pontodevenda!=null)
		GestorP.alterarMaquinadevenda(nserie, pontodevenda);
	else if(nserie==null && modelo!=null && pontodevenda!=null)
		GestorP.removerMaquina(nserie);
	nserie=null;
	modelo=null;
	pontodevenda=null;
	%>
	<main id="query">
			<button onclick="novamaquina()">Criar Nova Maquina</button>
			<button onclick="movermaquina()">Mover Maquina</button>
			<button onclick="removermaquina()">Remover Maquina</button>
			
		<script>
		function novamaquina() {
			document.getElementById('query').innerHTML = '<form name="novaMaquina">Número de Serie:<input type="text" name="nserie"><br>Modelo:<input type="text" name="modelo"><br>Ponto de Venda:<input type="text" name="pontodevenda"><br><input type="submit"></form>';
		}
		function movermaquina() {
			document.getElementById('query').innerHTML = '<form name="removerMaquina">Número de Serie:<input type="text" name="nserie"><br>Modelo:<input type="text" name="modelo"><br><input type="submit"></form>';
		}
		function removermaquina() {
			document.getElementById('query').innerHTML = '<form name="removerMaquina">Número de Serie:<input type="text" name="nserie"><br><input type="submit"></form>';
		}
		</script>
	</main>
	<footer></footer>
</body>
</html>