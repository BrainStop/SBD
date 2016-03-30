<%@page import="GestAl.GestorP"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="main_style.css">
<title>Funcionario</title>
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
		String funcionario = request.getParameter("funcionario");
		String armazem = request.getParameter("armazem");
		String validade = request.getParameter("validade");
		String precodecompra = request.getParameter("precodecompra");
		String quantidade = request.getParameter("quantidade");
		String maquina = request.getParameter("nserie");
		
		if(quantidade!=null && armazem!=null && funcionario!=null && precodecompra!=null && validade!=null){
			if(!quantidade.equals("") && !armazem.equals("") && !funcionario.equals("") && !precodecompra.equals("") && !validade.equals("")){
				GestorP.abastecerViatura(quantidade, armazem, funcionario, precodecompra, validade);
			}
		}
		else if(quantidade!=null && maquina!=null && funcionario!=null && precodecompra!=null && validade!=null){
			if(!quantidade.equals("") && !maquina.equals("") && !funcionario.equals("") && !precodecompra.equals("") && !validade.equals("")){
				GestorP.abastecerMaquina(quantidade, maquina, funcionario, precodecompra, validade);
			}
		}
		
		funcionario = null;
		armazem=null;
		validade=null;
		precodecompra=null;
		quantidade=null;
		maquina = null;
		
	%>
	
	<script type="text/javascript">
		function abastecerviatura() {
			document.getElementById('query').innerHTML = '<form>Funcionario:<input type="text" name="funcionario"><br>Armazem:<input type="text" name="armazem"><br><input type="button" Value="Validade" onclick="mydate();" /><input type="date" id="dt" name="validade" onchange="mydate1();" hidden/><input type="text" id="ndt" onclick="mydate();" hidden /><br>Preço de Compra<input type="text" name="precodecompra"><br>Quantidade:<input type="text" name="quantidade"><br><input type="submit"></form>';	
		}
		
		function abastecermaquina() {
			document.getElementById('query').innerHTML = '<form>Funcionario:<input type="text" name="funcionario"><br>Número de Série:<input type="text" name="nserie"><br><input type="button" Value="Validade" onclick="mydate();" /><input type="date" id="dt" name="validade" onchange="mydate1();" hidden/><input type="text" id="ndt" onclick="mydate();" hidden /><br>Preço de Compra<input type="text" name="precodecompra"><br>Quantidade:<input type="text" name="quantidade"><br><input type="submit"></form>';
		}
	
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
	<button onclick="abastecerviatura()">Abastecer Viatura</button>
	<button onclick="abastecermaquina()">Abastecer Máquina</button>
	</main>
	<footer></footer>
</body>
</html>