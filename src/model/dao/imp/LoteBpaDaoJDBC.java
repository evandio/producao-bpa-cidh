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
import java.util.ArrayList;
import java.util.List;
import model.entities.LoteBpa;
import model.entities.Profissional;
import model.services.ProfissionalService;
import model.dao.LoteBpaDao;

/**
 *
 * @author evandio.pereira
 */
public class LoteBpaDaoJDBC implements LoteBpaDao {

    Connection conn;

    public LoteBpaDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<LoteBpa> listaBpa() {
        PreparedStatement st = null;
        ResultSet rs = null;

        List<LoteBpa> lista = new ArrayList<>();

        String sql = "select "
                + "     a.nr_lote, a.data_atendimento, a.isn_profissional, a.dsc_profissional, a.turno, a.qtd_atendimento "
                + " from "
                + "     bpa_cidh.tb_lote a ";

        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                lista.add(instantiateLoteBpa(rs));
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

        return lista;

    }

    @Override
    public void saveOrUpdateLote(LoteBpa obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void inserir(LoteBpa obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizar(LoteBpa obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void closeLote(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void openLote(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private LoteBpa instantiateLoteBpa(ResultSet rs) throws SQLException {
        LoteBpa entity = new LoteBpa();

        entity.setLoteBpa(rs.getInt("nr_lote"));
        entity.setDataAtendimento(new java.util.Date(rs.getTimestamp("data_atendimento").getTime()));
        entity.setQtdAtendimento(rs.getInt("qtd_atendimento"));
        entity.setTurno(rs.getInt("turno"));

        Profissional entityProf = new ProfissionalService().localizarProfissional(rs.getInt("isn_profissional"));
        entity.setProfissional(entityProf);

        return entity;

    }

}
