/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcos
 */
public class Conection {

    public static ResultSet res;
    // public static Connection conn;
    public static PreparedStatement pst;
    public static Statement sta;
    public static String sSQL;
    public static String url = "jdbc:mysql://localhost:3306/dbvendas?characterEncoding=utf-8";
    private static boolean iniciado = false;

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = url;
    private static final String USER = "root";
    private static final String PASS = "87987954";

    public static Connection getConnection() {

        try {
            Class.forName(DRIVER);
            return java.sql.DriverManager.getConnection(URL, USER, PASS);

        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conecção com servidor: ", ex);
        }
    }

    public static void closeConnection(Connection con) {

        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            //menssagem
        }

    }

    public static void closeConnection(Connection con, PreparedStatement stmt) {

        closeConnection(con);

        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            //menssagem
        }

    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {

        closeConnection(con, stmt);

        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            //menssagem
        }

    }

    public static void Fazbackup(Connection con, String diretorio) {

        Date hoje = new Date();
        SimpleDateFormat df;
        df = new SimpleDateFormat("dd/MM/yyyy");
        String data = df.format(hoje);
        data = data.replaceAll("/", "-");
        try {

            String backupdirectory = diretorio + "/backups/" + data;
            CallableStatement cs = con.prepareCall("CALL SYSCS_UTIL.SYSCS_BACKUP_DATABASE (?)");
            cs.setString(1, backupdirectory);
            cs.execute();
            cs.close();
            JOptionPane.showMessageDialog(null, "Backup feito com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro no backup" + ex);
        }

    }

    public static void LerBackup(String diretorio) {

        String a = diretorio;

        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            DriverManager.getConnection("jdbc:derby:BancoGerenciador;restoreFrom=" + a);
            JOptionPane.showMessageDialog(null, "Backing Up Realizado com sucesso!");
        } catch (InstantiationException ex) {
            JOptionPane.showMessageDialog(null, "Erro no backing Up" + ex);
        } catch (IllegalAccessException ex) {
            JOptionPane.showMessageDialog(null, "Erro no backing Up" + ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro no backing Up" + ex);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro no backing Up" + ex);
        }

    }

}
