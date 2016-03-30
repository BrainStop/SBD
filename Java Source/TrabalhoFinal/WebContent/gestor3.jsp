<%@page import="com.sun.xml.internal.txw2.Document"%>
<%@page import="GestAl.GestorP"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="main_style.css">
<title>Insert title here</title>
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
	String teste = request.getParameter("teste");
	System.out.println(teste);
	String nserie = request.getParameter("maquina");
	String matricula = request.getParameter("viatura");
	String idarmazem = request.getParameter("armazem");
	String stock = null;
	if(nserie!=null || matricula!=null || idarmazem!=null){
		stock = GestorP.gestor3(nserie, matricula, idarmazem);
	}
	System.out.println(nserie+matricula+idarmazem);
	nserie=null;
	matricula=null;
	idarmazem=null;
	if (stock!=null){
	%>
	<%=stock %>
	<%
	} else{
	%>
	
	<script>
		function maquina() {
			document.getElementById('query').innerHTML = '<form>Número da maquina<input type="text" name="maquina"><br><input type="submit"></form>';
		}
		function viatura() {
			document.getElementById('query').innerHTML = '<form>Matricula da Viatura:<input type="text" name="viatura"><br><input type="submit"></form>';
		}
		function armazem() {
			document.getElementById('query').innerHTML = '<form>ID do Armazem:<input type="text" name="armazem"><br><input type="submit"></form>';
		}
		function total(){
			document.getElementById('query').innerHTML = '<%=GestorP.gestor3(nserie, matricula, idarmazem)%>';
			}
	</script>
	
	<button onclick="maquina()">Máquina</button>
	<button onclick="viatura()">Viatura</button>
	<button onclick="armazem()">Armazem</button>
	<button onclick="total()">Total</button>
	<%
	}
	%>
	</main>
	<footer>
	</footer>
</body>
</html>