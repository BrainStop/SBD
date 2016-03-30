package GestAl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

/**
 * @author: Engº Porfírio Filipe
 */

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Base64;
import java.util.Calendar;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;

/**
 * Implementação do protótipo de um gestor de inscrições de alunos em
 * disciplinas
 * 
 * @author Engº Porfírio Filipe
 * 
 */
public class GestorP {
		
	static String condAprov = " nota is not null and nota >=10 and nota <=20 ";

	static Configura cfg = new Configura();

	static Manipula dados = new Manipula(cfg);
	
	//Minhas Implementações
	
	public static String gestor7() {
		boolean ok = false;
		try {
			Connection con=dados.getLigacao();
			con.setAutoCommit(false);
			con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String tabela = "";
		String directiva = "SELECT * FROM PRODUTO";
		Manipula local = new Manipula(new Configura());
		ResultSet rs = local.getResultado(directiva);
		try {
			tabela += "<fieldset><table><tr><th>Nome</th><th>Imagem</th><th>Informação</th><th>Fornecedor</th></tr>";
			while (rs != null && rs.next()) {
				tabela += "<tr>";
				for(int i = 1; i < 5; i++) {
					if(i != 2 && i != 3)
						tabela += "<td>" + rs.getString(i) + "</td>";
					else if(i == 3)  
						tabela += "<td>" + tabNutricional(rs.getString(1)) + "</td>";
					else if(i == 2)
						tabela += "<td><a target='_blank' href='showImage.jsp?produto=" + rs.getString(1) + "'/>Imagem</a></td>";
				}
				tabela += "</tr>";
			}
			tabela += "</table></fieldset>";
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("SQLException: " + e.getMessage());
		}
		
		return tabela;
	}
	
	public static byte[] criarImagem(String nome) throws UnsupportedEncodingException {
		boolean ok = false;
		try {
			Connection con=dados.getLigacao();
			con.setAutoCommit(false);
			con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		byte[] imagem = null;
		String directiva = "SELECT imagem FROM PRODUTO WHERE nome='"+ nome + "'";
		Manipula local = new Manipula(new Configura());
		ResultSet rs = local.getResultado(directiva);
		try {
			if (rs != null && rs.next()) {
				imagem = rs.getBytes(1);
				System.out.println(imagem.length);
				imagem = Base64.getEncoder().encode(imagem);
				System.out.println(imagem.length);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("SQLException: " + e.getMessage());
		}
		return imagem;
	}
	
	
	public static String tabNutricional(String id) {
		boolean ok = false;
		try {
			Connection con=dados.getLigacao();
			con.setAutoCommit(false);
			con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String tabela = "<table><tr>";
		String directiva = "SELECT informacao FROM PRODUTO WHERE nome='"+id+"'";
		Manipula local = new Manipula(new Configura());
		ResultSet rs = local.getResultado(directiva);
		try {
			if (rs != null && rs.next()) {
				DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			    InputSource is = new InputSource();
			    is.setCharacterStream(new StringReader(rs.getString(1)));
			    Document doc = db.parse(is);
			    Element e = doc.getDocumentElement();
			    NamedNodeMap list = e.getAttributes();
			    for (int i = 0; i < list.getLength(); i++) {
			    	if(!list.item(i).getNodeName().equals("id"))
			    		tabela += "<td>"+ list.item(i).getNodeName() +"</td>";
			    }
			    tabela += "</tr><tr>";
			    for (int i = 0; i < list.getLength(); i++) {
			    	if(!list.item(i).getNodeName().equals("id"))
			    		tabela += "<td>" + list.item(i).getNodeValue() + "</td>";
			    }
			    tabela += "</tr></table>";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("SQLException: " + e.getMessage());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return tabela;
	}
	
	public static String gestor1() {
		boolean ok = false;
		try {
			Connection con=dados.getLigacao();
			con.setAutoCommit(false);
			con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String tabela = "";
		String directiva = "SELECT * FROM PRODUTO";
		Manipula local = new Manipula(new Configura());
		ResultSet rs = local.getResultado(directiva);
		try {
			tabela += "<fieldset><table><tr><th>Nome</th><th>Imagem</th><th>Informação</th><th>Fornecedor</th></tr>";
			while (rs != null && rs.next()) {
				tabela += "<tr>";
				for(int i = 1; i < 5; i++) {
					if(i != 2 && i != 3)
						tabela += "<td>" + rs.getString(i) + "</td>";
					else if(i == 3)  
						tabela += "<td>" + tabNutricional(rs.getString(1)) + "</td>";
					else if(i == 2)
						tabela += "<td><a target='_blank' href='showImage.jsp?produto=" + rs.getString(1) + "'/>Imagem</a></td>";
				}
				tabela += "</tr>";
			}
			tabela += "</table></fieldset>";
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("SQLException: " + e.getMessage());
		}
		
		return tabela;
	}
	
	public static String gestor6() {
		boolean ok = false;
		try {
			Connection con=dados.getLigacao();
			con.setAutoCommit(false);
			con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String tabela = "";
		String directiva = "SELECT refmaquinadevenda, refviatura, refarmazem, validade FROM LOTE "
				+"INNER JOIN STOCKARMAZEM ON LOTE.precodecompra=STOCKARMAZEM.refloteprecodecompra AND LOTE.validade=STOCKARMAZEM.reflotevalidade "
				+"INNER JOIN STOCKDAVIATURA ON LOTE.precodecompra=STOCKDAVIATURA.refloteprecodecompra AND LOTE.validade=STOCKDAVIATURA.reflotevalidade "
				+"INNER JOIN STOCKDAMAQUINADEVENDA ON LOTE.precodecompra=STOCKDAMAQUINADEVENDA.refloteprecodecompra AND LOTE.validade=STOCKDAMAQUINADEVENDA.reflotevalidade "
				+"WHERE VALIDADE < '2016-03-15'";
		Manipula local = new Manipula(new Configura());
		ResultSet rs = local.getResultado(directiva);
		try {
			tabela += "<fieldset><table><tr><th>Maquina</th><th>Viatura</th><th>Armazem</th><th>Validade</th></tr>";
			while (rs != null && rs.next()) {
				tabela += "<tr>";
				for(int i = 1; i < 5; i++) {
					tabela += "<td>" + rs.getString(i) + "</td>";
				}
				tabela += "</tr>";
			}
			tabela += "</table></fieldset>";
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("SQLException: " + e.getMessage());
		}
		
		return tabela;
	}
	
	public static String gestor5() {
		boolean ok = false;
		try {
			Connection con=dados.getLigacao();
			con.setAutoCommit(false);
			con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String tabela = "";
		String directiva = "SELECT refpais, refregiao, reflocaldevenda, nserie, SUM(precodevenda*quantidade) as lucro FROM MAQUINADEVENDA INNER JOIN TABELADEPRECOS ON refmaquinadevenda=nserie INNER JOIN QUANTIDADEDEVENDA ON QUANTIDADEDEVENDA.refmaquinadevenda=nserie INNER JOIN PONTODEVENDA ON refpontodevenda=idalphanumerico inner join LOCALDEVENDA ON reflocaldevenda=idnumerico inner join REGIAO on refregiao=nome GROUP BY refpais, refregiao, reflocaldevenda, nserie ORDER BY lucro DESC";
		Manipula local = new Manipula(new Configura());
		ResultSet rs = local.getResultado(directiva);
		try {
			tabela += "<fieldset><table><tr><th>País</th><th>Região</th><th>Local de Venda</th><th>Maquina</th><th>Lucro</th></tr><tr><th>";
			while (rs != null && rs.next()) {
				tabela += "<tr>";
				for(int i = 1; i < 6; i++) {
					tabela += "<td>" + rs.getString(i) + "</td>";
				}
				tabela += "</tr>";
			}
			tabela += "</table></fieldset>";
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("SQLException: " + e.getMessage());
		}
		
		return tabela;
	}
	
	public static String gestor4() {
		boolean ok = false;
		try {
			Connection con=dados.getLigacao();
			con.setAutoCommit(false);
			con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String catalogo = "";
		String directiva = "SELECT SUM(precodevenda*quantidade) AS lucro, refproduto FROM TABELADEPRECOS INNER JOIN QUANTIDADEDEVENDA ON TABELADEPRECOS.refmaquinadevenda=QUANTIDADEDEVENDA.refmaquinadevenda GROUP BY refproduto ORDER BY lucro DESC";
		Manipula local = new Manipula(new Configura());
		ResultSet rs = local.getResultado(directiva);
		float aux, max = 0;
		String resultado = null;
		try {
			if (rs != null && rs.next()) {
				resultado = "O Produto mais lucrativo é o " + rs.getString(2) + " com um lucro de " + rs.getString(1) + "€.";
			}
			return resultado;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("SQLException: " + e.getMessage());
		}
		
		return resultado;
	}
	
	public static String gestor3(String nserie, String matricula, String idarmazem) {
		int escolha = 0;
		if(nserie==null && matricula==null && idarmazem==null)
			escolha = 1;
		else if(nserie!=null)
			escolha = 2;
		else if(matricula!=null)
			escolha = 3;
		else if(idarmazem!=null)
			escolha = 4;
		
		boolean ok = false;
		try {
			Connection con=dados.getLigacao();
			con.setAutoCommit(false);
			con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String stock = "";
		String directiva = "";
		
		switch (escolha) {
			
			case 0:
				return null;
			case 1:
				System.out.println("entrou aqui!");
				directiva = "Select STOCKARMAZEM.quantidade, refarmazem, STOCKDAMAQUINADEVENDA.quantidade, refmaquinadevenda, STOCKDAVIATURA.quantidade, refviatura, STOCKARMAZEM.refloteprecodecompra, STOCKARMAZEM.reflotevalidade from STOCKARMAZEM inner join STOCKDAMAQUINADEVENDA on STOCKARMAZEM.refloteprecodecompra=STOCKDAMAQUINADEVENDA.refloteprecodecompra and STOCKARMAZEM.reflotevalidade=STOCKDAMAQUINADEVENDA.reflotevalidade inner join STOCKDAVIATURA ON STOCKARMAZEM.refloteprecodecompra=STOCKDAVIATURA.refloteprecodecompra AND STOCKARMAZEM.reflotevalidade=STOCKDAVIATURA.reflotevalidade";
				break;
			case 2:
				directiva = "SELECT * FROM STOCKDAMAQUINADEVENDA WHERE refmaquinadevenda='"+nserie+"'";
				break;
			case 3:
				directiva = "Select * from STOCKDAVIATURA WHERE refviatura='"+matricula+"'";
				break;
			case 4:
				directiva = "Select * from STOCKARMAZEM  where refarmazem='"+idarmazem+"'";
				break;
		}
		

		Manipula local = new Manipula(new Configura());
		ResultSet rs = local.getResultado(directiva);
		if(escolha!=1){
			try {
				stock += "<fieldset><table><tr><th>Quantidade</th><th>Preço de Compra</th><th>Validade</th></tr>";
				while (rs != null && rs.next()) {
					stock += "<tr><td>"+rs.getString("quantidade")+"</td><td>"+rs.getString("refloteprecodecompra")+"</td><td>"+rs.getString("reflotevalidade")+"</td></tr>";
				}
				stock += "</table></fieldset>";
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("SQLException: " + e.getMessage());
			}
		}else {
			System.out.println("aqui tb");
			try {
				stock += "<fieldset><table><tr><th>Armazem</th><th>Quantidade</th><th>Viatura</th><th>Quantidade</th><th>Maquina<th>Quantidade</th></tr>";
				while (rs != null && rs.next()) {
					stock += "<tr><td>"+rs.getString(2)+"</td><td>"+rs.getString(1)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(3)+"</td></tr>";
				}
				stock += "</table></fieldset>";
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("SQLException: " + e.getMessage());
			}
		}
		System.out.println(stock);
		return stock;
	}

	
	public static String gestor2(String data, String matricula) {
		boolean ok = false;
		try {
			Connection con=dados.getLigacao();
			con.setAutoCommit(false);
			con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String directiva = "SELECT idempresa, bi, nome FROM CONDUTOR inner join FUNCIONARIO on idempresa=reffuncionario where data='"+data+"' and refviatura='"+matricula+"'";
		Manipula local = new Manipula(new Configura());
		ResultSet rs = local.getResultado(directiva);
		try {
			if (rs != null && rs.next()) {
				
				return "<fieldset><table><tr><th>ID</th><th>Nome</th><th>BI</th></tr><tr><th>"+rs.getString("idempresa")+"</th><th>"+rs.getString("nome")+"</th><th>"+rs.getString("bi")+"</th></tr></table></fieldset>";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("SQLException: " + e.getMessage());
		}
		System.out.println("nao deu");
		return null;
	}
	
	public static void novaMaquina(String nserie, String modelo, String refpontodevenda) {
		boolean ok = false;
		try {
			Connection con=dados.getLigacao();
			con.setAutoCommit(false);
			con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Consola.writeLine("Inserção de dados de uma nova máquina:");
		String directiva = "insert into MAQUINADEVENDA VALUES ("+ nserie + ", "+ modelo + ", '" + refpontodevenda +"')";
		
		ok = dados.xDirectiva(directiva);

		try {
			if (ok) {
				dados.getLigacao().commit();
				Consola.writeLine("Criação da nova máquina com o nº série = "+ nserie + " com sucesso.");
			} else {
				dados.getLigacao().rollback();
				Consola
				.writeLine("Não foi possivel criar a máquina com os dados:");
				Consola.writeLine("  Nº Serie: '" + nserie + "'");
				Consola.writeLine("  Modelo: '" + modelo + "'");
				Consola.writeLine("  Ponto de Venda: " + refpontodevenda);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			dados.getLigacao().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//TODO Saber se temos que alterar o nserie ou nao
	public static boolean alterarMaquinadevenda(String nserie, String refpontodevenda) {
		if (!dados.xDirectiva("update MAQUINADEVENDA set refpontodevenda='" + refpontodevenda + "' where nserie=" + nserie))
			return false;
		Consola.writeLine("Dados do aluno após alteração do nome:");
		infoMaquina(nserie.toString());
		return true;
	}	
	
	public static boolean removerMaquina(String nserie) {
		String directiva = "DELETE FROM MAQUINADEVENDA WHERE nserie=" + nserie + "";
		if (!dados.xDirectiva(directiva))
			return false;
		Consola.writeLine("A máquina foi removida com sucesso.");
		return true;
	}
	
	public static boolean abastecerViatura(String quat, String armID,String funcionario, String precodecompra, String validade) {
		ResultSet rs;
		String directiva, matricula = "";
		Integer quantidadearmazem = 0;
		Manipula local = new Manipula(new Configura());
		Integer quantidade = Integer.parseInt(quat);
		rs = local.getResultado("SELECT quantidade FROM STOCKARMAZEM WHERE refloteprecodecompra='"+precodecompra+"'and reflotevalidade='"+validade+"'");
		try {
			if (rs != null && rs.next()) {
				quantidadearmazem = Integer.parseInt(rs.getString("quantidade"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("SQLException: " + e.getMessage());
		}
		directiva = "UPDATE STOCKARMAZEM SET quantidade=" + (quantidadearmazem - quantidade) + " "
				+ "FROM STOCKARMAZEM "+ "WHERE "
				+ "refloteprecodecompra=" +precodecompra+ " and reflotevalidade='"+validade+"'";
		dados.xDirectiva(directiva);
		
		directiva = "SELECT matricula from VIATURA WHERE reffuncionario='"+funcionario+"'";
		
		rs = local.getResultado(directiva);
		try {
			if (rs != null && rs.next()) {
				matricula = rs.getString("matricula");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("SQLException: " + e.getMessage());
		}
		
		directiva = "SELECT quantidade FROM STOCKDAVIATURA WHERE refloteprecodecompra='"+precodecompra+"' and reflotevalidade='"+validade+"' and refviatura='"+matricula+"'";
		
		rs = local.getResultado(directiva);
		try {
			if (rs != null && rs.next()) {
				Integer oldquantidade = Integer.parseInt(rs.getString("quantidade"));
				directiva =  "UPDATE STOCKDAVIATURA SET quantidade="+(quantidade + oldquantidade)+" WHERE refloteprecodecompra='"+precodecompra+"' and reflotevalidade='"+validade+"' and refviatura='"+matricula+"'";
			} else {
				directiva = "INSERT INTO STOCKDAVIATURA VALUES ('"+quantidade+"','"+matricula+"','"+validade+"','"+precodecompra+"')";
			}
			Consola.writeLine(directiva);
			if (!dados.xDirectiva(directiva))
				return false;
		
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("SQLException: " + e.getMessage());
		}
		
		local.desligar();
		
		return true;
	}
	
	
	public static boolean abastecerMaquina(String quat, String maquina, String funcionario, String precodecompra, String validade) {
		ResultSet rs;
		Integer quantidade = Integer.parseInt(quat);
		Manipula local = new Manipula(new Configura());
		Integer quatidadeViatura = 0;

		String directiva = "SELECT STOCKDAVIATURA.quantidade FROM STOCKDAVIATURA"
					+" INNER JOIN VIATURA ON VIATURA.matricula=STOCKDAVIATURA.refviatura INNER JOIN LOTE ON refloteprecodecompra=precodecompra"
					+" and reflotevalidade=validade WHERE VIATURA.reffuncionario='"+funcionario+"' and reflotevalidade='"
					+validade+"' and refloteprecodecompra='"+precodecompra+"'";
		
		Consola.writeLine(directiva);
		
		rs = local.getResultado(directiva);
		try {
			if (rs != null && rs.next()) {
				quatidadeViatura = Integer.parseInt(rs.getString("quantidade"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("SQLException: " + e.getMessage());
		}
		directiva = "UPDATE STOCKDAVIATURA SET quantidade=" + (quatidadeViatura - quantidade) + " "
				+ "FROM STOCKDAVIATURA INNER JOIN VIATURA ON VIATURA.matricula=STOCKDAVIATURA.refviatura INNER JOIN LOTE ON refloteprecodecompra=precodecompra"
					+" and reflotevalidade=validade WHERE VIATURA.reffuncionario='"+funcionario+"' and reflotevalidade='"
					+validade+"' and refloteprecodecompra='"+precodecompra+"'";

		dados.xDirectiva(directiva);
		
		directiva = "SELECT quantidade FROM STOCKDAMAQUINADEVENDA where refmaquinadevenda='"+maquina+"' and reflotevalidade='"+validade+"' and refloteprecodecompra='"+precodecompra+"'";
				
		rs = local.getResultado(directiva);
		try {
			if (rs != null && rs.next()) {
				Integer oldquantidade = Integer.parseInt(rs.getString("quantidade"));
				directiva =  "UPDATE STOCKDAMAQUINADEVENDA SET quantidade="+(quantidade + oldquantidade)+" WHERE refloteprecodecompra='"+precodecompra+"' and reflotevalidade='"+validade+"' and refmaquinadevenda='"+maquina+"'";
			} else {
				directiva = "INSERT INTO STOCKDAMAQUINADEVENDA VALUES ('"+quantidade+"','"+maquina+"','"+validade+"','"+precodecompra+"')";
			}
			Consola.writeLine(directiva);
			if (!dados.xDirectiva(directiva))
				return false;
		
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("SQLException: " + e.getMessage());
		}
		
		local.desligar();
		
		return true;
	}

	
	/**
	 * Apresenta a informação da maquina
	 * 
	 * @param nserie
	 */
	public static void infoMaquina(String nserie) {
		Manipula local = new Manipula(new Configura());
		String directiva = "select nserie, modelo, refpontodevenda from MAQUINADEVENDA where nserie="
			+ nserie;
		try {
			ResultSet rs = local.getResultado(directiva);
			if (rs != null && rs.next()) {
				Consola.writeLine("  ==================>");
				Consola.writeLine("  nºSérie: " + rs.getString("nserie") + "");
				Consola.writeLine("  Modelo: " + rs.getString("modelo") + "");
				Consola.writeLine("  Ponto de Venda: " + rs.getString("refpontodevenda") + "");
				Consola.writeLine("  <==================");
			} else
				Consola.writeLine("Não foi encontrada a máquina com esse número série: "
						+ nserie + ".");
			local.desligar();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("SQLException: " + e.getMessage());
	}
	}
	
	
	//###########

}