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
import model.dao.ProfissionalDao;
import model.entities.Cbo;
import model.entities.CboProfissional;
import model.entities.Profissional;
import model.services.CboProfissionalService;

/**
 *
 * @author evandio.pereira
 */
public class ProfissionalDaoJDBC implements ProfissionalDao {

    private Connection conn;

    public ProfissionalDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Profissional> localizarTodos() {
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "select * "
                + " from "
                + " seguranca.t_usuario a, seguranca.t_formacao b, seguranca.t_conselho c  "
                + " where "
                + " a.isn_formacao > 0 "
                + " and a.flg_situacao = 'S' "
                + " and a.isn_formacao = b.isn_formacao "
                + " and a.isn_conselho = c.isn_conselho "
                + " order by a.dsc_usuario ";

        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();

            List<Profissional> list = new ArrayList<>();

            while (rs.next()) {
                list.add(instatiateProfissional(rs));
            }

            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

    }

    @Override
    public List<Profissional> localizarProfissionais(String nome) {
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "select * "
                + "from "
                + " seguranca.t_usuario a, seguranca.t_formacao b,  seguranca.t_conselho c "
                + "where "
                + " a.isn_formacao > 0 "
                + "and a.flg_situacao = 'S' "
                + "and a.isn_formacao = b.isn_formacao "
                + "and a.isn_conselho = c.isn_conselho "
                + "and a.dsc_usuario LIKE ? "
                + "order by a.dsc_usuario ";

        try {
            st = conn.prepareStatement(sql);
            st.setString(1, nome.toUpperCase());
            rs = st.executeQuery();

            List<Profissional> list = new ArrayList<>();

            while (rs.next()) {
                list.add(instatiateProfissional(rs));
            }

            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Profissional localizarProfissional(String nome) {
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "select * "
                + "from "
                + " seguranca.t_usuario a, seguranca.t_formacao b, seguranca.t_conselho c "
                + "where "
                + " a.isn_formacao > 0 "
                + "and a.flg_situacao = 'S' "
                + "and a.isn_formacao = b.isn_formacao "
                + "and a.isn_conselho = c.isn_conselho "
                + "and a.dsc_usuario LIKE ? "
                + "order by a.dsc_usuario ";

        try {
            st = conn.prepareStatement(sql);
            st.setString(1, nome.toUpperCase());
            rs = st.executeQuery();

            Profissional prof = new Profissional();

            while (rs.next()) {
                prof = instatiateProfissional(rs);
            }

            return prof;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    private Profissional instatiateProfissional(ResultSet rs) throws SQLException {
        Profissional prof = new Profissional();
        prof.setIsnUsuario(rs.getInt("isn_usuario"));
        prof.setDscUsuario(rs.getString("dsc_usuario"));
        prof.setInsFormacao(rs.getInt("isn_formacao"));
        prof.setDscFormacao(rs.getString("dsc_formacao"));
        prof.setNumConselho(rs.getString("num_conselho"));
        prof.setSglConselho(rs.getString("sgl_conselho"));
        

        //Tenrar relacionar o Profissional com o CBO
        //--->Buscar o Cbo
        CboProfissionalService service = new CboProfissionalService();
        CboProfissional objCbo = service.localizarCboProfissional(prof);
        
        prof.setCboProf(objCbo);
        
        
        return prof;
    }

}
