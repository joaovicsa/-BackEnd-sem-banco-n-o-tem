/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.model.util;

/**
 *
 * @author joao_
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectorBD {

	// Usuario
	private static String usuario = "loja";

	// Senha
	private static String senha = "loja";

	// URL
	private static final String url = "jdbc:mysql://localhost:1433/loja";

	/*
	 * Conexão com banco de dados
	 */

	public static Connection createConnectionToMySQL() throws ClassNotFoundException, SQLException {

		// Faz com que a classe seja carregada pela JVM
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Não foi pocivel carregar o Driver");

		}
		// Cria a conexão com banco de dados
		Connection connection = DriverManager.getConnection(url, usuario, senha);
		return connection;
	}

	public static void main(String[] args) throws Exception {

		// Recuperar uma conexão com banco de dados
		Connection con = createConnectionToMySQL();

		// Testa se a coneão é nula
		if (con != null) {
			System.out.println("Conexão obitida com suceso!");
			con.close();
		}

	}

}
