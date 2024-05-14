/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import connection.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Cliente;

/**
 *
 * @author USUARIO
 */
public class ClienteDao {

    private Connection conexao = null;

    public void setConnection(Connection conexao) {
        this.conexao = conexao;
    }

    Connection con;
    ResultSet rs;

    public boolean RegistrarCliente(Cliente cl) {
        String sql = "INSERT INTO clientes (dni, nome, telefone, direccion) VALUES (?,?,?,?)";
        try (Connection conexao = ConnectionManager.abrirConexao(); PreparedStatement pst = conexao.prepareStatement(sql)) {

            pst.setString(1, cl.getDni());
            pst.setString(2, cl.getNome());
            pst.setString(3, cl.getTelefone());
            pst.setString(4, cl.getDireccion());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    public List ListarCliente() {
        List<Cliente> ListaCl = new ArrayList();
        String sql = "SELECT * FROM clientes";
        try (Connection conexao = ConnectionManager.abrirConexao(); PreparedStatement pst = conexao.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Cliente cl = new Cliente();
                cl.setId(rs.getInt("id"));
                cl.setDni(rs.getString("dni"));
                cl.setNome(rs.getString("nome"));
                cl.setTelefone(rs.getString("telefone"));
                cl.setDireccion(rs.getString("direccion"));
                ListaCl.add(cl);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaCl;
    }

    public boolean EliminarCliente(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (Connection conexao = ConnectionManager.abrirConexao(); PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setInt(1, id);
            int affectedRows = pst.executeUpdate();
            if (affectedRows > 0) {
                ListarCliente();
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir cliente: " + e.getMessage());
            return false;
        }
           
    }

    public boolean ModificarCliente(Cliente cl) {
        String sql = "UPDATE clientes SET dni=?, nome=?, telefone=?, direccion=? WHERE id=?";
        try (Connection conexao = ConnectionManager.abrirConexao(); PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setString(1, cl.getDni());
            pst.setString(2, cl.getNome());
            pst.setString(3, cl.getTelefone());
            pst.setString(4, cl.getDireccion());
            pst.setInt(5, cl.getId());
            pst.executeUpdate();
            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao modificar cliente: " + e.getMessage());
            return false;
        }
    }

    public Cliente Buscarcliente(int dni) {
        Cliente cl = new Cliente();
        String sql = "SELECT * FROM clientes WHERE dni = ?";
        try (Connection conexao = ConnectionManager.abrirConexao(); PreparedStatement pst = conexao.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
            pst.setInt(1, dni);
            if (rs.next()) {
                cl.setId(rs.getInt("id"));
                cl.setNome(rs.getString("nome"));
                cl.setTelefone(rs.getString("telefone"));
                cl.setDireccion(rs.getString("direccion"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return cl;
    }

}
