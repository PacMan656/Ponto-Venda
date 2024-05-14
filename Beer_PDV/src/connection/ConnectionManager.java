/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

/**
 *
 * @author marco
 */
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String URL = "jdbc:mysql://localhost:3306/dbvendas?characterEncoding=utf-8"; // Substitua pelo seu URL de conexão
    private static final String USUARIO = "root"; // Substitua pelo seu usuário
    private static final String SENHA = "87987954"; // Substitua pela sua senha

    public static Connection abrirConexao() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    public static void fecharConexao(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException e) {
            }
        }
    }

    public static void init(File config_file) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
