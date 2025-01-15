package cadastrobd.model.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConectorBD {
    private static ConectorBD instancia = null;
    private Connection conexao;

    private ConectorBD() {
        // Configurar a conexão com o banco de dados aqui
        String url = "jdbc:sqlserver://localhost:1433;databaseName=loja;encrypt=true;trustServerCertificate=true;";
        String usuario = "loja";
        String senha = "loja";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conexao = DriverManager.getConnection(url, usuario, senha);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public static ConectorBD getInstance() {
        if (instancia == null) {
            instancia = new ConectorBD();
        }
        return instancia;
    }

    public ResultSet getSelect(String sql) throws SQLException {
        Statement stmt = conexao.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }

    public PreparedStatement getPrepared(String sql) throws SQLException {
        return conexao.prepareStatement(sql);
    }

    public void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}