package DAO;


import connection.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Config;
import model.Productos;
import model.Provedor;

public class ProductosDao {

    private Connection conexao = null;

    public void setConnection(Connection conexao) {
        this.conexao = conexao;
    }

    ConnectionManager Con;

    public boolean RegistrarProductos(Productos pro) {
        String sql = "INSERT INTO productos (codigo, nome, provedor, stock, preco) VALUES (?,?,?,?,?)";
        try (Connection conexao = Con.abrirConexao(); PreparedStatement pst = conexao.prepareStatement(sql)) {

            pst.setString(1, pro.getCodigo());
            pst.setString(2, pro.getNome());
            pst.setInt(3, pro.getProvedor());
            pst.setInt(4, pro.getStock());
            pst.setDouble(5, pro.getPrecio());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public List ListarProductos() {
        List<Productos> Listapro = new ArrayList();
        String sql = "SELECT pr.id AS id_provedor, pr.nome AS nome_provedor, p.* FROM provedor pr INNER JOIN productos p ON pr.id = p.provedor ORDER BY p.id DESC";
        try (Connection conexao = Con.abrirConexao(); PreparedStatement pst = conexao.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Productos pro = new Productos();
                pro.setId(rs.getInt("id"));
                pro.setCodigo(rs.getString("codigo"));
                pro.setNome(rs.getString("nome"));
                pro.setProvedor(rs.getInt("id_provedor"));
                pro.setProvedorPro(rs.getString("nome_provedor"));
                pro.setStock(rs.getInt("stock"));
                pro.setPrecio(rs.getDouble("preco"));
                Listapro.add(pro);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return Listapro;
    }

    /**
     * Método para eliminar produtos do banco de dados e atualizar a lista
     * visual.
     *
     * @param id O identificador do produto a ser excluído.
     * @return true se a operação for bem-sucedida, false em caso de falha.
     */
    public boolean EliminarProductos(int id) {
        String sql = "DELETE FROM productos WHERE id = ?";
        try (Connection conexao = Con.abrirConexao(); PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setInt(1, id);
            int affectedRows = pst.executeUpdate();
            if (affectedRows > 0) {
                ListarProductos();
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir produto: " + e.getMessage());
            return false;
        }
    }

    /**
     * Atualiza um produto na base de dados.
     *
     * @param pro Objeto representando o produto a ser atualizado.
     * @return true se a operação for bem-sucedida, false caso contrário.
     */
    public boolean ModificarProductos(Productos pro) throws SQLException {
        // Comando SQL para atualizar o produto, o id é utilizado apenas como condição WHERE.
        String sql = "UPDATE productos SET codigo=?, nome=?, provedor=?, stock=?, preco=? WHERE id=?";
        try (Connection conexao = Con.abrirConexao(); PreparedStatement pst = conexao.prepareStatement(sql)) {
            // Prepara os dados para atualização
            pst.setString(1, pro.getCodigo());
            pst.setString(2, pro.getNome());
            pst.setInt(3, pro.getProvedor());
            pst.setInt(4, pro.getStock());
            pst.setDouble(5, pro.getPrecio());
            pst.setInt(6, pro.getId()); // Utiliza o ID para identificar o produto a ser atualizado.

            // Executa o update e verifica se alguma linha foi afetada
            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao modificar produto: " + e.getMessage());
            return false;
        }
    }

    public Productos BuscarPro(String cod) {
        Productos producto = new Productos();
        String sql = "SELECT * FROM productos WHERE codigo = ?";
        try (Connection conexao = Con.abrirConexao(); PreparedStatement pst = conexao.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
            pst.setString(1, cod);
            if (rs.next()) {
                producto.setId(rs.getInt("id"));
                producto.setNome(rs.getString("nome"));
                producto.setPrecio(rs.getDouble("preco"));
                producto.setStock(rs.getInt("stock"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return producto;
    }

    public Productos BuscarId(int id) {
        Productos pro = new Productos();
        String sql = "SELECT pr.id AS id_provedor, pr.nome AS nome_provedor, p.* FROM provedor pr INNER JOIN productos p ON p.provedor = pr.id WHERE p.id = ?";
        try (Connection conexao = Con.abrirConexao(); PreparedStatement pst = conexao.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
            pst.setInt(1, id);
            if (rs.next()) {
                pro.setId(rs.getInt("id"));
                pro.setCodigo(rs.getString("codigo"));
                pro.setNome(rs.getString("nome"));
                pro.setProvedor(rs.getInt("provedor"));
                pro.setProvedorPro(rs.getString("nome_provedor"));
                pro.setStock(rs.getInt("stock"));
                pro.setPrecio(rs.getDouble("preco"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return pro;
    }

    public Provedor BuscarProvedor(String nome) {
        Provedor pr = new Provedor();
        String sql = "SELECT * FROM provedor WHERE nome = ?";
        try (Connection conexao = Con.abrirConexao(); PreparedStatement pst = conexao.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
            pst.setString(1, nome);
            if (rs.next()) {
                pr.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return pr;
    }

    public Config BuscarDados() {
        Config conf = new Config();
        String sql = "SELECT * FROM config";
        try (Connection conexao = Con.abrirConexao(); PreparedStatement pst = conexao.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {
                conf.setId(rs.getInt("id"));
                conf.setRuc(rs.getString("ruc"));
                conf.setNome(rs.getString("nome"));
                conf.setTelefone(rs.getString("telefone"));
                conf.setDireccion(rs.getString("direccion"));
                conf.setMensaje(rs.getString("mensaje"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return conf;
    }

    public boolean ModificarDatos(Config conf) {
        String sql = "UPDATE config SET ruc=?, nome=?, telefone=?, direccion=?, mensaje=? WHERE id=?";
        try (Connection conexao = Con.abrirConexao(); PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setString(1, conf.getRuc());
            pst.setString(2, conf.getNome());
            pst.setString(3, conf.getTelefone());
            pst.setString(4, conf.getDireccion());
            pst.setString(5, conf.getMensaje());
            pst.setInt(6, conf.getId());
            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao modificar cliente: " + e.getMessage());
            return false;
        }
    }
}
