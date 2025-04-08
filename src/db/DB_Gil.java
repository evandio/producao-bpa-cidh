/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import model.entities.Profissional;
import model.services.ProfissionalService;

/**
 *
 * @author evandio.pereira
 */
public class DB_Gil {

    private static Connection conn = null;

    public static Connection getConnection() {

        if (conn == null) {
            try {
                Properties props = loadProperties();
                String drv = props.getProperty("jdbc.driver");
                String url = props.getProperty("jdbc.url");
                String user = props.getProperty("jdbc.user");
                String pass = props.getProperty("jdbc.pass");

                Class.forName(drv);

                conn = DriverManager.getConnection(url, user, pass);

            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            } catch (ClassNotFoundException e) {
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }

    private static void closeConnection() {
        if (conn != null) {

            try {
                conn.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }

        }
    }

    private static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("databaseGilNovo.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }

    public static void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {

        //Chama a interface que implementa o Gerador de Chave
        //GeradorDeChaveDao daoChave = DaoFactory.createGeradorDeChaveDao();
        //Chama o metodo pelo dao
        //long chave = daoChave.getProximoCodigo("tb_lote");
        //System.out.println("Sequencia: " + chave);
        
        ProfissionalService service = new ProfissionalService();
        List<Profissional> lista =  service.localizarTodos();
        
        for (Profissional p : lista){
            System.out.print(p.getDscUsuario() + " - ");
            System.out.print(p.getDscFormacao()+ " - ");
            System.out.print(p.getNumConselho()+ " - ");
            System.out.print(p.getSglConselho()+ " - ");
            System.out.println("");
        }

    }
}
