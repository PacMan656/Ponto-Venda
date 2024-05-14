
package DAO;


import connection.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.login;

public class LoginDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public login log(String usuario, String pass){
        login l = new login();
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND pass = ?";
        try (Connection conexao = ConnectionManager.abrirConexao();PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setString(1, usuario);
            pst.setString(2, pass);
            rs= pst.executeQuery();
            if (rs.next()) {
                l.setId(rs.getInt("id"));
                l.setNome(rs.getString("nome"));
                l.setUsuario(rs.getString("usuario"));
                l.setPass(rs.getString("pass"));
                l.setRol(rs.getString("rol"));
                
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return l;
    }
    
    public boolean Registrar(login reg){
        String sql = "INSERT INTO usuarios (nome, usuario, pass, rol) VALUES (?,?,?,?)";
        try (Connection conexao = ConnectionManager.abrirConexao();PreparedStatement pst = conexao.prepareStatement(sql)) {
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
    
    public List ListarUsuarios(){
       List<login> Lista = new ArrayList();
       String sql = "SELECT * FROM usuarios";
       try (Connection conexao = ConnectionManager.abrirConexao();PreparedStatement pst = conexao.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
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
