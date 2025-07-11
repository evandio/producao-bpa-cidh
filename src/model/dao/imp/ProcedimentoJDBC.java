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
import model.entities.Procedimento;
import model.dao.ProcedimentoDao;
import model.entities.RelacaoProcedCbo;

/**
 *
 * @author evandio.pereira
 */
public class ProcedimentoJDBC implements ProcedimentoDao {

    private Connection conn;

    public ProcedimentoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Procedimento> listaProcedimentoGil() {
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "select codproc, desproc from procedim ";

        List<Procedimento> lista = new ArrayList<>();

        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                lista.add(instantiateProcedimento(rs));
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

        return lista;

    }

    //apagar depois
    @Override
    public List<RelacaoProcedCbo> listaProcedCboGil() {
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "SELECT a.CODPROC, a.CODCBOS FROM RL_PROCCBOS a ";

        List<RelacaoProcedCbo> lista = new ArrayList<>();

        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                RelacaoProcedCbo r = new RelacaoProcedCbo();
                r.setCbo(rs.getString("CODCBOS"));
                r.setProcedimento(rs.getString("CODPROC"));

                lista.add(r);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return lista;
    }

    @Override
    public void gravarProcedimento(Procedimento proced) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void gravarProcedimentos(List<Procedimento> listaProced) {
        PreparedStatement st = null;

        List<Procedimento> lista = listaProced;

        String sql = "INSERT INTO bpa_cidh.tb_proced "
                + " (isn_cod_proced, dsc_proced)"
                + " VALUES "
                + " (?, ?)";

        for (Procedimento p : lista) {
            try {
                st = conn.prepareStatement(sql);
                st.setString(1, p.getIsnCodProcedimento());
                st.setString(2, p.getDscProcedimento().toUpperCase());

                st.execute();

            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            } finally {
                DB_Bpa.closeStatement(st);
            }

        }//Fim for

    }

    @Override
    public void gravarProcedCbo(List<RelacaoProcedCbo> lista) {
        PreparedStatement st = null;
        
        String sql = "INSERT INTO bpa_cidh.rl_procedcbos (isn_cod_proced, isn_cbo) "
                + "VALUES (?, ?)";

        for (RelacaoProcedCbo p : lista) {
            try {
                st = conn.prepareStatement(sql);
                st.setString(1, p.getProcedimento());
                st.setString(2, p.getCbo());

                st.execute();

            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            } finally {
                DB_Bpa.closeStatement(st);
            }

        }//Fim for
    }
    
    private Procedimento instantiateProcedimento(ResultSet rs) throws SQLException {
        Procedimento p = new Procedimento();

        p.setIsnCodProcedimento(rs.getString("codproc"));
        p.setDscProcedimento(rs.getString("desproc"));

        return p;
    }

}
