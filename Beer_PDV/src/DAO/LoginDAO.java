package DAO;

import connection.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.AuthenticationException;
import model.login;

public class LoginDAO {

    public login log(String usuario, String pass) throws AuthenticationException {
        login login = new login();
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND pass = ?";

        try (Connection conexao = ConnectionManager.abrirConexao(); PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setString(1, usuario);
            pst.setString(2, pass);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    login.setId(rs.getInt("id"));
                    login.setNome(rs.getString("nome"));
                    login.setUsuario(rs.getString("usuario"));
                    login.setPass(rs.getString("pass"));
                    login.setRol(rs.getString("rol"));
                } else {
                    System.out.println("Usuário ou senha inválidos.");
            }
        }
    } catch (SQLException e) {
        System.out.println("Erro ao conectar ao banco de dados: " + e.toString());
    }

        return login;
    }

    public boolean Registrar(login reg) {
        String sql = "INSERT INTO usuarios (nome, usuario, pass, rol) VALUES (?,?,?,?)";
        try (Connection conexao = ConnectionManager.abrirConexao(); PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setString(1, reg.getNome());
            pst.setString(2, reg.getUsuario());
            pst.setString(3, reg.getPass());
            pst.setString(4, reg.getRol());
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public List ListarUsuarios() {
        List<login> Lista = new ArrayList();
        String sql = "SELECT * FROM usuarios";
        try (Connection conexao = ConnectionManager.abrirConexao(); PreparedStatement pst = conexao.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                login lg = new login();
                lg.setId(rs.getInt("id"));
                lg.setNome(rs.getString("nome"));
                lg.setUsuario(rs.getString("usuario"));
                lg.setRol(rs.getString("rol"));
                Lista.add(lg);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return Lista;
    }
}
