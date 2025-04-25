/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.imp;

import db.DB_Bpa;
import db.DbException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dao.DiagnosticoDao;
import model.entities.Diagnostico;

/**
 *
 * @author evandio.pereira
 */
public class DiagnosticoDaoJDBC implements DiagnosticoDao {

    Connection conn;

    public DiagnosticoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Diagnostico> todosDiagnostico() {
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM bpa_cidh.tb_diagnostico ";

        List<Diagnostico> lista = new ArrayList<>();

        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                lista.add(instantiateDiagnostico(rs));
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

        return lista;
    }

    @Override
    public Diagnostico getDiagnostico(Integer isnDiagnostico) {
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM bpa_cidh.tb_diagnostico ";

        Diagnostico d = new Diagnostico();

        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                d = instantiateDiagnostico(rs);
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

        return d;
    }

    @Override
    public void inserir(Diagnostico obj) {

        PreparedStatement st = null;

        String sql = "INSERT INTO bpa_cidh.tb_diagnostico "
                + " (isn_diagnostico, dsc_diagnostico) "
                + " VALUES "
                + " (?, ?) ";
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, obj.getIsnDiagnostico());
            st.setString(2, obj.getDscDiagnostico());

            st.execute();

        } catch (SQLException e) {
            e.getStackTrace();
            throw new DbException(e.getMessage());
        } finally {
            DB_Bpa.closeStatement(st);
        }
    }

    @Override
    public void atualizar(Diagnostico obj) {
        PreparedStatement st = null;

        String sql = "UPDATE bpa_cidh.tb_diagnostico SET dsc_diagnostico = ? WHERE isn_diagnosticoo = ? ";

        try {
            st = conn.prepareStatement(sql);
            st.setString(1, obj.getDscDiagnostico());
            st.setInt(2, obj.getIsnDiagnostico());

            st.execute();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB_Bpa.closeStatement(st);
        }
    }

    private Diagnostico instantiateDiagnostico(ResultSet rs) throws SQLException {
        Diagnostico d = new Diagnostico();

        d.setIsnDiagnostico(rs.getInt("isn_diagnostico"));
        d.setDscDiagnostico(rs.getString("dsc_disgnostico"));

        return d;
    }
}
