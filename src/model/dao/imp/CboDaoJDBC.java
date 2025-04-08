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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.dao.CboDao;
import model.entities.Cbo;

/**
 *
 * @author evandio.pereira
 */
public class CboDaoJDBC implements CboDao {

    private Connection conn;

    public CboDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Cbo> listaCbosGil() {
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "SELECT cod_cbos, ds_cbo_reduzida from cbos ";

        List<Cbo> lista = new ArrayList<>();

        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                lista.add(instantiateCboGil(rs));
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

        return lista;
    }

    @Override
    public void gravarCbo(Cbo cbo) {
        PreparedStatement st = null;

        String sql = "INSERT INTO bpa_cidh.tb_cbos "
                + " (isn_cbo, dsc_cbos)"
                + " BALUES "
                + " (?, ?)";

        try {
            st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, cbo.getIsn_cbo());
            st.setString(2, cbo.getDsc_cbo());
            st.execute();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB_Bpa.closeStatement(st);
        }
    }

    @Override
    public void gravarCbo(List<Cbo> listaCbos) {
        PreparedStatement st = null;

        String sql = "INSERT INTO bpa_cidh.tb_cbos "
                + " (isn_cbo, dsc_cbos)"
                + " VALUES "
                + " (?, ?)";

        for (Cbo c : listaCbos) {
            try {
                System.out.println(c.getDsc_cbo());
                st = conn.prepareStatement(sql);
                st.setString(1, c.getIsn_cbo());
                st.setString(2, c.getDsc_cbo().toUpperCase());
                st.execute();

            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            } finally {
                DB_Bpa.closeStatement(st);
            }

        }
    }

    private Cbo instantiateCboGil(ResultSet rs) throws SQLException {
        Cbo cbo = new Cbo();
        cbo.setIsn_cbo(rs.getString("cod_cbos"));
        cbo.setDsc_cbo(rs.getString("ds_cbo_reduzida"));

        return cbo;
    }
}
