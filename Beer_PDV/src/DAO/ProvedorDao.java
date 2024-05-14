
package DAO;

import connection.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Provedor;

public class ProvedorDao {

        private Connection conexao = null;

    public void setConnection(Connection conexao) {
        this.conexao = conexao;
    }
    ConnectionManager Con;
    
    PreparedStatement ps;
    public boolean RegistrarProvedor(Provedor pr){
        String sql = "INSERT INTO provedor(ruc, nome, telefone, direccion) VALUES (?,?,?,?)";
        try (Connection conexao = Con.abrirConexao();PreparedStatement pst = conexao.prepareStatement(sql)) {
           pst.setString(1, pr.getRuc());
           pst.setString(2, pr.getNome());
           pst.setString(3, pr.getTelefone());
           pst.setString(4, pr.getDireccion());
           pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
    public List ListarProvedor(){
        List<Provedor> Listapr = new ArrayList();
        String sql = "SELECT * FROM provedor";
        try (Connection conexao = Con.abrirConexao();PreparedStatement pst = conexao.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {                
                Provedor pr = new Provedor();
                pr.setId(rs.getInt("id"));
                pr.setRuc(rs.getString("ruc"));
                pr.setNome(rs.getString("nome"));
                pr.setTelefone(rs.getString("telefone"));
                pr.setDireccion(rs.getString("direccion"));
                Listapr.add(pr);
            }
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return Listapr;
    }
    
    public boolean EliminarProvedor(int id){
        String sql = "DELETE FROM provedor WHERE id = ? ";
        try (Connection conexao = Con.abrirConexao();PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.setInt(1, id);
            int affectedRows = pst.executeUpdate();
            if (affectedRows > 0) {
                ListarProvedor();
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir cliente: " + e.getMessage());
            return false;
        }
    }
    
    public boolean ModificarProvedor(Provedor pr){
        String sql = "UPDATE provedor SET ruc=?, nome=?, telefone=?, direccion=? WHERE id=?";
        try (Connection conexao = Con.abrirConexao();PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setString(1, pr.getRuc());
            pst.setString(2, pr.getNome());
            pst.setString(3, pr.getTelefone());
            pst.setString(4, pr.getDireccion());
            pst.setInt(5, pr.getId());
            pst.executeUpdate();
            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao modificar cliente: " + e.getMessage());
            return false;
        }
    }
}
