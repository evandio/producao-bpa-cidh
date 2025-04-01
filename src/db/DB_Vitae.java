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
import model.dao.DaoFactory;
import model.dao.ProfissionalDao;
import model.entities.Profissional;

/**
 *
 * @author evandio.pereira
 */
public class DB_Vitae {

    private static Connection conn = null;

    public static Connection getConnection() {

        if (conn == null) {
            try {
                Properties props = loadProperties();
                String url = props.getProperty("jdbc.url");
                String user = props.getProperty("jdbc.username");
                String pass = props.getProperty("jdbc.password");

                conn = DriverManager.getConnection(url, user, pass);
                 
            } catch (SQLException e) {
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
        try (FileInputStream fs = new FileInputStream("databaseVitaePostgres.properties")) {
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

  
}
