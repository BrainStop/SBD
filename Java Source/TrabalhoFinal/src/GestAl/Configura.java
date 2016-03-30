package GestAl;


import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.MissingResourceException;
import java.util.ResourceBundle;


/**
 * Define a configuração usada para acesso à base de dados
 * 
 * @author Engº Porfírio Filipe
 * 
 */

public class Configura {

	/**
	 * Tipo enumerado usado para carecterizar os Sistemas de Gestão de Bases de Dados
	 * @author Engº Porfírio Filipe
	 *
	 */

	public enum SGBD { // http://developers.sun.com/product/jdbc/drivers/browse_all.jsp
		/**
		 * Ligação ao Microsof Access via ponte jdbc-odbc (tipo 1)
		 * http://office.microsoft.com/en-us/access/default.aspx
		 */
		MsAccess,
		/**
		 * Ligação ao Microsof SQL Server via ponte jdbc-odbc (tipo 1)
		 */
		MsSqlServer2000T1,
		/**
		 * Ligação ao Microsof SQL Server via JDBC tipo 4
		 * http://www.microsoft.com/sql/default.mspx
		 * http://www.microsoft.com/downloads/details.aspx?FamilyID=86212d54-8488-481d-b46b-af29bb18e1e5&DisplayLang=en
		 * The driver is a Type 4 JDBC driver that supports a subset of the JDBC 2.0 Optional Package. 
		 * When you install the Microsoft SQL Server 2000 Driver for JDBC, the supporting documentation is optionally installed with it. 
		 * You should refer to that documentation for the most comprehensive information about the driver. Also, see the release manifest for known issues.
		 * Microsoft SQL Server 2000 Driver for JDBC\lib
		 *    msbase.jar
		 *    mssqlser.jar
		 *    msutil.jar
		 * 
		 * set CLASSPATH=install_dir\lib\msbase.jar;install_dir\lib\msutil.jar;install_dir\lib\mssqlserver.jar;%CLASSPATH%
		 * Outros drivers em:
		 * NetDirect:  http://www.j-netdirect.com
         * DataDirect: http://www.datadirect-technologies.com
         * FreeTDS:    http://www.freetds.org
         * 
		 */
		MsSqlServer2000T4,
		/**
		 * http://dev.mysql.com/
		 * mysql-connector-java-5.0.4-bin.jar
		 * http://mmMySQL.sourceforge.net
		 */
		MySQL,
		/**
		 * http://www.oracle.com/database/index.html
		 * http://otn.oracle.com/software/content.html
		 */
		Oracle
	}

	static boolean bRun = false;

	String recurso = "configura"; // configura.properties

	/*
	 * SQL Server 2000 Configuração com JDBC tipo 1  
	  private String drv = "sun.jdbc.odbc.JdbcOdbcDriver";
	  
	  private String url = "jdbc:odbc:BD";
	  
	  private SGBD sgbd = SGBD.MsSqlServer2000T1;
	  
	  private String usr = "userbd";
	  
	  private String pwd = "passbd"; 
	*/

	// Informs the driver to use server a side-cursor,
	// which permits more than one active statement
	// on a connection.
	/*
	 * Configuração para SQL Server 2000 com JDBC tipo 4 ver
	 * SQLServerConnect.java 
	  */
	  private String drv = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	  
	  private String url = "jdbc:sqlserver://BRAIN_STOP-PC\\SQLEXPRESS:1433;databaseName=TRABALHOSBD2;selectMethod=cursor;";
	  
	  private String usr = "BrainStop";
	  
	  private String pwd = "1234";
	  
	  private SGBD sgbd = SGBD.MsSqlServer2000T1;
	 

	/* Configuração para o MS ACCESS com JDBC tipo 1 
	private String drv = "sun.jdbc.odbc.JdbcOdbcDriver";

	private String url = "jdbc:odbc:MSACCESS";

	private SGBD sgbd = SGBD.MsAccess;

	private String usr = "";

	private String pwd = ""; */

	/*
	 * Configuração para o mySQL com JDBC tipo 4 private String drv =
	 * "com.mysql.jdbc.Driver"; String serverName = localhost; String database =
	 * "SIT"; private String url = "jdbc:mysql://"+serverName+"/"+database+"";
	 * SGBD sgbd = SGBD.MySQL; private SGBD sgbd = SGBD.MySQL; private String
	 * usr = ""; private String pwd = "";
	 */

	/*
	 * private String drv = "oracle.jdbc.driver.OracleDriver"; String serverName =
	 * "127.0.0.1"; String portNumber = "1521"; String database = "SIT"; String
	 * url = "jdbc:oracle:thin:@" + serverName + ":" + portNumber + ":" + sid;
	 * 
	 * private SGBD sgbd = SGBD.Oracle;
	 */

	/**
	 * Chama a lista de propriedades do driver
	 * 
	 * @param args
	 *            nenhum
	 */
	public static void main(String[] args) {
		Configura cfg = new Configura();
		cfg.driverProperties();
	}

	/**
	 * Construtor, tenta ler do ficheiro indicado em 'recurso' a configuração do acesso à base de dados
	 */
	public Configura() {
		if (!bRun) {
			try {
				final ResourceBundle rb = ResourceBundle.getBundle(recurso);
				drv = rb.getString("drv");
				url = rb.getString("url");
				usr = rb.getString("usr");
				pwd = rb.getString("pwd");
			} catch (final MissingResourceException e) {
				// System.err.println(e.getMessage());
				// if no resource bundle for the specified base name can be
				// found
				// System.err.println("O ficheiro de configuração '" + recurso+
				// "'
				// não foi encontrado...");
			}
			bRun = true;
		}
	}

	/**
	 * Apresenta as propriedades do driver corrente
	 */
	public void driverProperties() {
		String URL = "jdbc:odbc:"; //"jdbc:odbc:local//GESTAL:1433";
		try {
			System.err.println("Vai carregar o driver (" + URL + ")...");
			Class.forName(drv);

			System.err.println("Vai obter uma instancia do driver...");
			Driver driver = DriverManager.getDriver(URL);

			System.err.println("Vai ler as propriedades do driver...");
			DriverPropertyInfo[] info = driver.getPropertyInfo(URL, null);
			System.err.println("Vai listar as propriedades do driver...");
			for (int i = 0; i < info.length; i++) {
				// Get name of property
				String name = info[i].name;

				// Is property value required?
				boolean isRequired = info[i].required;

				// Get current value
				String value = info[i].value;

				// Get description of property
				String desc = info[i].description;

				// Get possible choices for property; if null, value can be any string
				String[] choices = info[i].choices;
				System.out.println(name + " ("
						+ ((isRequired) ? "Obrigatório" : "Opcional") + ") "
						+ ": " + value + ", " + desc + ", " + choices);
			}
		} catch (ClassNotFoundException e) {
			// Could not find the database driver
		} catch (SQLException e) {
			System.err.println("SQLException" + e.getMessage());
		}
	}
	/**
	 * Formata numa String uma Data SQL
	 * @param dia dia do mês
	 * @param mes mes 1..12
	 * @param ano ano com quatro digitos
	 * @return Data no formato SQL
	 */
	public String fmt(int dia, int mes, int ano) {
		Calendar Cl = Calendar.getInstance();
		Cl.set(ano, mes-1, dia);
		java.sql.Date d=new java.sql.Date(Cl.getTime().getTime());
		return formatar(d);
	}

	/**
	 * Formata numa String uma Data SQL
	 * 
	 * @param data
	 *            Data SQL
	 * @return String formatada para ser usada na clausula 'WHERE'
	 */
	public String formatar(final java.sql.Date data) {
		Calendar C = Calendar.getInstance();
		C.setTime(data);
		SimpleDateFormat formatar = new SimpleDateFormat("MM/dd/yyyy");
		switch (sgbd) {
		case MsAccess:
			return "#" + formatar.format(data) + "#";
		case MsSqlServer2000T1:
			formatar = new SimpleDateFormat("yyyyMMdd");
			return "'" + formatar.format(data) + "'";
		case MsSqlServer2000T4:
			formatar = new SimpleDateFormat("yyyy-MM-dd");
			return "'" + formatar.format(data) + "'";
		case MySQL:
		case Oracle:
		default:
			return "'" + data.toString() + "'"; // yyyy-MM-dd
		}
		/*
		 -- Exemplo de inserção de datas no SQL Server 2000

		 DROP TABLE X 
		 GO 
		 SET NOCOUNT ON
		 CREATE TABLE X(D DATETIME)

		 INSERT INTO X VALUES ('19561030')
		 INSERT INTO X VALUES ('561030')
		 INSERT INTO X VALUES ('10/30/1956')
		 INSERT INTO X VALUES ('10/30/56')
		 INSERT INTO X VALUES ('30 OCT 1956')
		 INSERT INTO X VALUES ('30 OCT 56')
		 INSERT INTO X VALUES ('OCT 30 1956')
		 INSERT INTO X VALUES ('OCT 30, 1956')
		 INSERT INTO X VALUES ('OCT 30, 56')
		 INSERT INTO X VALUES ('OCTOBER 10, 1956')
		 SELECT * FROM X
		 */
	}
	
	/**
	 * Deveolve true se o argumento for um código de um tipo SQL associado a uma
	 * data ou data/hora
	 * 
	 * @param tipo
	 * @return true se o tipo for uma data ou data/hora
	 */
	public boolean isData(int tipo) {
		/* DATE          | java.sql.Date        | getDate( )       | 91
		 * TIME          | java.sql.Time        | getTime( )       | 92
		 * TIMESTAMP     | java.sql.Timestamp   | getTimestamp( )  | 93
		 */
		return tipo == 91 || tipo==92 || tipo==93;
	}

	/**
	 * Deveolve true se a coluna tiver a configuração com a indicação de
	 * 'Update'
	 * 
	 * @param Colunas
	 * 
	 * @param tipo
	 * @return true se o padrão "_u" for encontrado
	 */
	public boolean isEditable(String Colunas) {
		return Colunas.startsWith("_u");
	}

	/**
	 * Deveolve true se a coluna da chave primária tiver a configuração com a
	 * indicação de 'Insert'
	 * 
	 * @param Colunas
	 * 
	 * @param Tabela
	 * @return true se o padrão "_i" ou "_a" for encontrado
	 */
	public boolean isInsertable(String Colunas) {
		return Colunas.startsWith("_i") || Colunas.startsWith("_a");
	}

	/**
	 * Deveolve true se a coluna da chave primária tiver a configuração com a
	 * indicação de 'Delete'
	 * 
	 * @param Colunas
	 * 
	 * @param Tabela
	 * @return true se o padrão "_d" ou "_a" for encontrado
	 */
	public boolean isDeletable(String Colunas) {
		return Colunas.startsWith("_d") || Colunas.startsWith("_a");
	}
	/**
	 * Deveolve true se a coluna tiver a configuração com a indicação de 'Primary Key'
	 * se tiver '_p' indica que a coluna pertence simplesmente á chave primária
	 * se tiver '_a' indica que pertence à chave e que a tabela suporta deletes e inserts
	 * se tiver '_i' indica que pertence à chave e que a tabela suporta inserts
	 * se tiver '_d' indica que pertence à chave e que a tabela suporta deletes
	 * @param Colunas 
	 * 
	 * @param tipo
	 * @return true se o padrão "_p" for encontrado
	 */
	public boolean isPrimaryKey(String Colunas) {
		return Colunas.startsWith("_p") || Colunas.startsWith("_a") || Colunas.startsWith("_i") || Colunas.startsWith("_d");
	}
	/**
	 * Deveolve true se o argumento for um número
	 * 
	 * @param tipo
	 * @return true se o tipo for um número
	 */
	public boolean isNumero(int tipo) {
		/* NUMERIC       | java.math.BigDecimal | getBigDecimal( ) |  2
		 * DECIMAL       | java.math.BigDecimal | getBigDecimal( ) |  3
		 * BIT           | Boolean (boolean)    | getBoolean( )    | -7
		 * TINYINT       | Integer (byte)       | getByte( )       | -6
		 * SMALLINT      | Integer (short)      | getShort( )      |  5
		 * INTEGER       | Integer (int)        | getInt( )        |  4
		 * BIGINT        | Long (long)          | getLong( )       | -5
		 * REAL          | Float (float)        | getFloat( )      |  7
		 * FLOAT         | Double (double)      | getDouble( )     |  6
		 * DOUBLE        | Double (double)      | getDouble( )     |  8
		 */
		return tipo == 2 || tipo==3 || tipo==-7 || tipo==-6 || tipo==5 || tipo==4 || tipo==-5 || tipo==7 || tipo==6 || tipo==8;
	}
	/**
	 * Deveolve true se o argumento for um código de um tipo SQL associado a Strings
	 * 
	 * @param tipo
	 * @return true se o tipo for String
	 */
	public boolean isChar(int tipo) {
		/* SQL Data Type | Java Type            | getXXX( ) Method | Numeric Code
		 * CHAR          | String               | getString( )     |  1
		 * VARCHAR       | String               | getString( )     | 12
		 * LONGVARCHAR   | String               | getString( )     | -1
		 */
		return tipo == 1 || tipo==12 || tipo==-1;
	}
	
	/**
	 * @param valor dado a ser formatado
	 * @param tipo tipo de dados SQL
	 * @param tnome mome do tipo de dados SQL
	 * @return um String formatado para ser escrito na base de dados
	 */
	public String fmTipo(Object valor, int tipo, String tnome) {
		
		/* http://www.oreilly.com/catalog/jentnut2/chapter/ch02.html
		 * SQL Data Type | Java Type            | getXXX( ) Method | Numeric Code
		 * CHAR          | String               | getString( )     |  1
		 * VARCHAR       | String               | getString( )     | 12
		 * LONGVARCHAR   | String               | getString( )     | -1
		 * NUMERIC       | java.math.BigDecimal | getBigDecimal( ) |  2
		 * DECIMAL       | java.math.BigDecimal | getBigDecimal( ) |  3
		 * BIT           | Boolean (boolean)    | getBoolean( )    | -7
		 * TINYINT       | Integer (byte)       | getByte( )       | -6
		 * SMALLINT      | Integer (short)      | getShort( )      |  5
		 * INTEGER       | Integer (int)        | getInt( )        |  4
		 * BIGINT        | Long (long)          | getLong( )       | -5
		 * REAL          | Float (float)        | getFloat( )      |  7
		 * FLOAT         | Double (double)      | getDouble( )     |  6
		 * DOUBLE        | Double (double)      | getDouble( )     |  8
		 * BINARY        | byte[]               | getBytes( )      | -2
		 * VARBINARY     | byte[]               | getBytes( )      | -3
		 * LONGVARBINARY | byte[]               | getBytes( )      | -4
		 * DATE          | java.sql.Date        | getDate( )       | 91
		 * TIME          | java.sql.Time        | getTime( )       | 92
		 * TIMESTAMP     | java.sql.Timestamp   | getTimestamp( )  | 93
		 * BLOB          | java.sql.Blob        | getBlob( )       |
		 * CLOB          | java.sql.Clob        | getClob( )       |
		 * 
		 * */

		// System.out.println("->"+valor.getClass()+"  SQL:"+tipo+" - "+tnome);
		if(valor==null||valor.toString().length()==0)
			return "NULL";
		if(isData(tipo)) {  // ignora a parte das horas
			java.sql.Timestamp t = (java.sql.Timestamp)valor;
			return formatar(new java.sql.Date(t.getTime()));
		}
		if(isChar(tipo))
		  return "'"+valor.toString()+"'";
		return valor.toString();
	}

	/**
	 * Retorna o nome do driver JDBC
	 * 
	 * @return nome do driver JDBC
	 */
	public String getDRV() {
		return drv;
	}

	/**
	 * Retorna a palavra passe do utilizador da base de dados
	 * 
	 * @return palavra passe do utilizador da base de dados
	 */
	public String getPWD() {
		return pwd;
	}

	/**
	 * Retorna o identificador do tipo de gestor de base de dados
	 * 
	 * @return identificador do tipo de gestor de base de dados
	 */
	public SGBD getSGBD() {
		return sgbd;
	}

	/**
	 * Retorn o URL que permite aceder à base de dados
	 * 
	 * @return URL para acesso á base de dados
	 */
	public String getURL() {
		return url;
	}

	/**
	 * Retorna o nome do utilizador da base de dados
	 * 
	 * @return Nome do utilizador da base de dados
	 */
	public String getUSR() {
		return usr;
	}

	/**
	 * Altera o nome do driver JDBC
	 * 
	 * @param str
	 *            Novo driver JDBDC
	 */
	public void setDRV(final String str) {
		if (str != null)
			drv = str;
	}

	/**
	 * Altera a palavra passe do utilizador da base de dados
	 * 
	 * @param str
	 *            Palavra passe do utilzador da base de dados
	 */
	public void setPWD(final String str) {
		if (str != null)
			pwd = str;
	}

	/**
	 * Altera o tipo de sistema de base de dados
	 * 
	 * @param aSGBD
	 *            Tipo de sistema de base de dados
	 */
	public void setSGBD(final SGBD aSGBD) {
		sgbd = aSGBD;
	}

	/**
	 * Altera o URL que permite aceder á base de dados
	 * 
	 * @param str
	 *            Novo URL JDBC
	 */
	public void setURL(final String str) {
		if (str != null)
			url = str;
	}

	/**
	 * Altera o nome do utilizador da base de dados
	 * 
	 * @param str
	 *            Nome do utilizador da base de dados
	 */
	public void setUSR(final String str) {
		if (str != null)
			usr = str;
	}
}
