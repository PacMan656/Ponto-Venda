package DAO;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import connection.ConnectionManager;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.filechooser.FileSystemView;
import model.Detalle;
import model.Venda;

public class VendaDao {

    ConnectionManager Con;
    int r;
    private Connection conexao = null;

    public void setConnection(Connection conexao) {
        this.conexao = conexao;
    }

    public int IdVenta() throws SQLException {
        int id = 0;
        String sql = "SELECT MAX(id) FROM vendas";
        try (Connection conexao = Con.abrirConexao(); PreparedStatement pst = conexao.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return id;
    }

    public int RegistrarVenda(Venda v) {
        String sql = "INSERT INTO vendas (cliente, vendedor, total, dataVenda) VALUES (?,?,?,?)";
        try (Connection conexao = ConnectionManager.abrirConexao(); PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setInt(1, v.getCliente());
            pst.setString(2, v.getVendedor());
            pst.setDouble(3, v.getTotal());
            pst.setString(4, v.getDataVenda());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                conexao.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return r;
    }

    public int RegistrarDetalle(Detalle Dv) {
        String sql = "INSERT INTO detalle (id_pro, cantidad, precio, id_venda) VALUES (?,?,?,?)";
        try (Connection conexao = ConnectionManager.abrirConexao(); PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setInt(1, Dv.getId_pro());
            pst.setInt(2, Dv.getCantidad());
            pst.setDouble(3, Dv.getPreco());
            pst.setInt(4, Dv.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                conexao.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return r;
    }

    public boolean ActualizarStock(int cant, int id) {
        String sql = "UPDATE productos SET stock = ? WHERE id = ?";
        try (Connection conexao = ConnectionManager.abrirConexao(); PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setInt(1, cant);
            pst.setInt(2, id);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public List Listarvendas() {
        List<Venda> ListaVenta = new ArrayList();
        String sql = "SELECT c.id AS id_cli, c.nome, v.* FROM clientes c INNER JOIN vendas v ON c.id = v.cliente";
        try (Connection conexao = ConnectionManager.abrirConexao(); PreparedStatement pst = conexao.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Venda vent = new Venda();
                vent.setId(rs.getInt("id"));
                vent.setNome_cli(rs.getString("nome"));
                vent.setVendedor(rs.getString("vendedor"));
                vent.setTotal(rs.getDouble("total"));
                ListaVenta.add(vent);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaVenta;
    }

    public Venda BuscarVenta(int id) {
        Venda cl = new Venda();
        String sql = "SELECT * FROM vendas WHERE id = ?";
        try (Connection conexao = ConnectionManager.abrirConexao(); PreparedStatement pst = conexao.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
            pst.setInt(1, id);
            if (rs.next()) {
                cl.setId(rs.getInt("id"));
                cl.setCliente(rs.getInt("cliente"));
                cl.setTotal(rs.getDouble("total"));
                cl.setVendedor(rs.getString("vendedor"));
                cl.setDataVenda(rs.getString("dataVenda"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return cl;
    }

    public void pdfV(int idventa, int Cliente, double total, String usuario) {
        try {
            Date date = new Date();
            FileOutputStream archivo;
            String url = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
            File salida = new File(url + "venta.pdf");
            archivo = new FileOutputStream(salida);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            Image img = Image.getInstance(getClass().getResource("/Img/logo_pdf.png"));
            //dataVenda
            Paragraph dataVenda = new Paragraph();
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
            dataVenda.add(Chunk.NEWLINE);
            dataVenda.add("Vendedor: " + usuario + "\nFolio: " + idventa + "\ndataVenda: "
                    + new SimpleDateFormat("dd/MM/yyyy").format(date) + "\n\n");
            PdfPTable Encabezado = new PdfPTable(4);
            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0);
            float[] columnWidthsEncabezado = new float[]{20f, 30f, 70f, 40f};
            Encabezado.setWidths(columnWidthsEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
            Encabezado.addCell(img);
            Encabezado.addCell("");
            //info empresa
            String config = "SELECT * FROM config";
            String mensaje = "";
            try (Connection conexao = ConnectionManager.abrirConexao(); PreparedStatement pst = conexao.prepareStatement(config); ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    mensaje = rs.getString("mensaje");
                    Encabezado.addCell("Ruc:    " + rs.getString("ruc") + "\nnome: " + rs.getString("nome") + "\nTeléfono: " + rs.getString("telefono") + "\nDirección: " + rs.getString("direccion") + "\n\n");
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
            //
            Encabezado.addCell(dataVenda);
            doc.add(Encabezado);
            //cliente
            Paragraph cli = new Paragraph();
            cli.add(Chunk.NEWLINE);
            cli.add("DATOS DEL CLIENTE" + "\n\n");
            doc.add(cli);

            PdfPTable provedor = new PdfPTable(3);
            provedor.setWidthPercentage(100);
            provedor.getDefaultCell().setBorder(0);
            float[] columnWidthsCliente = new float[]{50f, 25f, 25f};
            provedor.setWidths(columnWidthsCliente);
            provedor.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell cliNom = new PdfPCell(new Phrase("nome", negrita));
            PdfPCell cliTel = new PdfPCell(new Phrase("Telefone", negrita));
            PdfPCell cliDir = new PdfPCell(new Phrase("Dirección", negrita));
            cliNom.setBorder(Rectangle.NO_BORDER);
            cliTel.setBorder(Rectangle.NO_BORDER);
            cliDir.setBorder(Rectangle.NO_BORDER);
            provedor.addCell(cliNom);
            provedor.addCell(cliTel);
            provedor.addCell(cliDir);
            String prove = "SELECT * FROM clientes WHERE id = ?";
            try (Connection conexao = ConnectionManager.abrirConexao(); PreparedStatement pst = conexao.prepareStatement(prove); ResultSet rs = pst.executeQuery()) {
                pst.setInt(1, Cliente);
                if (rs.next()) {
                    provedor.addCell(rs.getString("nome"));
                    provedor.addCell(rs.getString("telefone"));
                    provedor.addCell(rs.getString("direccion") + "\n\n");
                } else {
                    provedor.addCell("Publico en General");
                    provedor.addCell("S/N");
                    provedor.addCell("S/N" + "\n\n");
                }

            } catch (SQLException e) {
                System.out.println(e.toString());
            }
            doc.add(provedor);

            PdfPTable tabla = new PdfPTable(4);
            tabla.setWidthPercentage(100);
            tabla.getDefaultCell().setBorder(0);
            float[] columnWidths = new float[]{10f, 50f, 15f, 15f};
            tabla.setWidths(columnWidths);
            tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell c1 = new PdfPCell(new Phrase("Cant.", negrita));
            PdfPCell c2 = new PdfPCell(new Phrase("Descripción.", negrita));
            PdfPCell c3 = new PdfPCell(new Phrase("P. unt.", negrita));
            PdfPCell c4 = new PdfPCell(new Phrase("P. Total", negrita));
            c1.setBorder(Rectangle.NO_BORDER);
            c2.setBorder(Rectangle.NO_BORDER);
            c3.setBorder(Rectangle.NO_BORDER);
            c4.setBorder(Rectangle.NO_BORDER);
            c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tabla.addCell(c1);
            tabla.addCell(c2);
            tabla.addCell(c3);
            tabla.addCell(c4);
            String product = "SELECT d.id, d.id_pro,d.id_venda, d.preco, d.cantidad, p.id, p.nome FROM detalle d INNER JOIN productos p ON d.id_pro = p.id WHERE d.id_venda = ?";
            try (Connection conexao = ConnectionManager.abrirConexao(); PreparedStatement pst = conexao.prepareStatement(product); ResultSet rs = pst.executeQuery()) {
                pst.setInt(1, idventa);
                while (rs.next()) {
                    double subTotal = rs.getInt("cantidad") * rs.getDouble("preco");
                    tabla.addCell(rs.getString("cantidad"));
                    tabla.addCell(rs.getString("nome"));
                    tabla.addCell(rs.getString("preco"));
                    tabla.addCell(String.valueOf(subTotal));
                }

            } catch (SQLException e) {
                System.out.println(e.toString());
            }
            doc.add(tabla);
            Paragraph info = new Paragraph();
            info.add(Chunk.NEWLINE);
            info.add("Total S/: " + total);
            info.setAlignment(Element.ALIGN_RIGHT);
            doc.add(info);
            Paragraph firma = new Paragraph();
            firma.add(Chunk.NEWLINE);
            firma.add("Cancelacion \n\n");
            firma.add("------------------------------------\n");
            firma.add("Firma \n");
            firma.setAlignment(Element.ALIGN_CENTER);
            doc.add(firma);
            Paragraph gr = new Paragraph();
            gr.add(Chunk.NEWLINE);
            gr.add(mensaje);
            gr.setAlignment(Element.ALIGN_CENTER);
            doc.add(gr);
            doc.close();
            archivo.close();
            Desktop.getDesktop().open(salida);
        } catch (DocumentException | IOException e) {
            System.out.println(e.toString());
        }
    }

}
