/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.imp;

import db.DbException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.dao.GeradorDeChaveDao;

/**
 *
 * @author evandio.pereira
 */
public class GeradorDeChaveDaoJDBC implements GeradorDeChaveDao {

    private static final byte INCREMENTO = 1;
    private Connection conn;
    private String tabela;
    private long proximaChave;
    private long maximaChave;

    public GeradorDeChaveDaoJDBC(Connection conn) {
        this.conn = conn;
        this.proximaChave = 0;
        this.maximaChave = 0;

        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            StringBuffer msg = new StringBuffer("Não foi possível desligar a confirmação automática!");
            msg.append("\nMotivo: " + e.getMessage());
            throw new DbException(msg.toString());
        }

    }

    private void reservarChave() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        long proximaChaveNova;

        String sql = "SELECT sequencia FROM bpa_cidh.tb_sequecias WHERE tabela = ? FOR UPDATE";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, tabela);
            rs = stmt.executeQuery();
            rs.next();

            proximaChaveNova = rs.getLong("sequencia");

        } catch (SQLException e) {
            StringBuffer msg = new StringBuffer("Não foi possível gerar a próxima Chave!");
            msg.append("\nMotivo: " + e.getMessage());
            throw new DbException(msg.toString());
        }

        long maximaChaveNova = proximaChaveNova + INCREMENTO;

        try {
            sql = "UPDATE bpa_cidh.tb_sequecias set sequencia = ? WHERE tabela = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, maximaChaveNova);
            stmt.setString(2, tabela);
            stmt.executeUpdate();
            conn.commit();
            proximaChave = proximaChaveNova;
            maximaChave = maximaChaveNova;

            
            rs.close();
            stmt.close();
            conn.close();;
            
        } catch (SQLException e) {
            StringBuffer msg = new StringBuffer("Não foi possível gerar a chave!");
            msg.append("\nMotivo: " + e.getMessage());
            throw new DbException(msg.toString());
        }

    }

    @Override
    public synchronized long getProximoCodigo(String tabela) {
        this.tabela = tabela;
        if (proximaChave == maximaChave){
            reservarChave();
        }
        
        return proximaChave;
    }

}
