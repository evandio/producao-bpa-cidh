/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.imp;

import db.DB_Bpa;
import db.DbException;
import gui.util.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.dao.CboProfissionalDao;
import model.entities.Cbo;
import model.entities.CboProfissional;
import model.entities.Profissional;

/**
 *
 * @author evandio.pereira
 */
public class CboProfissionalDaoJDBC implements CboProfissionalDao {

    private Connection conn;

    public CboProfissionalDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public CboProfissional buscarCboProfissional(Profissional profissional) {
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM bpa_cidh.rl_prof_cbo a where a.isn_profissional = ? ";

        CboProfissional rlProfCbo = new CboProfissional();

        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, profissional.getIsnUsuario());
            rs = st.executeQuery();

            while (rs.next()) {
                rlProfCbo = instantiateCboProf(rs);
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

        return rlProfCbo;
    }

    private CboProfissional instantiateCboProf(ResultSet rs) throws SQLException {
        CboProfissional obj = new CboProfissional();

        Cbo objCbo = new Cbo();
        objCbo.setIsn_cbo(rs.getString("isn_cbo"));
        objCbo.setDsc_cbo(rs.getString("dsc_cbo"));

        obj.setIsnProfissional(rs.getInt("isn_profissional"));
        obj.setCbo(objCbo);

        return obj;
    }

    @Override
    public void gravarCboProf(Profissional objProf, CboProfissional objCbo) {

        Profissional p = objProf;
        CboProfissional c = objCbo;

        PreparedStatement st = null;

        String sql = "INSERT INTO bpa_cidh.rl_prof_cbo (isn_profissional, isn_cbo, dsc_cbo) VALUES (?, ?, ?) ";

        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, p.getIsnUsuario());
            st.setInt(2, Utils.tryParseToInt(c.getCbo().getIsn_cbo()));
            st.setString(3, c.getCbo().getDsc_cbo());

            st.execute();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB_Bpa.closeStatement(st);
        }
    }

    @Override
    public void updateCboProf(Profissional objProf, CboProfissional objCbo) {
        Profissional p = objProf;
        CboProfissional c = objCbo;

        PreparedStatement st = null;

        String sql = "UPDATE bpa_cidh.rl_prof_cbo "
                + " SET isn_cbo = ?, dsc_cbo = ? WHERE isn_profissional = ? ";

        try {
            st = conn.prepareStatement(sql);
            st.setString(1, c.getCbo().getIsn_cbo());
            st.setString(2, c.getCbo().getDsc_cbo());
            st.setInt(3, p.getIsnUsuario());

            st.execute();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB_Bpa.closeStatement(st);
        }
    }

}
