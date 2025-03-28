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
import model.entities.Profissional;

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
                + "from "
                + " seguranca.t_usuario a, seguranca.t_formacao b, seguranca.t_usuario_especialidade c, seguranca.t_especialidade d "
                + "where "
                + " a.isn_formacao > 0 "
                + "and a.flg_situacao = 'S' "
                + "and a.isn_formacao = b.isn_formacao "
                + "and a.isn_usuario = c.isn_usuario_especialidade "
                + "and c.isn_especialidade = d.isn_especialidade "
                + "order by a.dsc_usuario ";

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
    public List<Profissional> localizarNome(String nome) {
        PreparedStatement st = null;
        ResultSet rs = null;
        
        String sql = "select * "
                + "from "
                + " seguranca.t_usuario a, seguranca.t_formacao b, seguranca.t_usuario_especialidade c, seguranca.t_especialidade d "
                + "where "
                + " a.isn_formacao > 0 "
                + "and a.flg_situacao = 'S' "
                + "and a.isn_formacao = b.isn_formacao "
                + "and a.isn_usuario = c.isn_usuario_especialidade "
                + "and c.isn_especialidade = d.isn_especialidade "
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
    

    private Profissional instatiateProfissional(ResultSet rs) throws SQLException {
        Profissional prof = new Profissional();
        prof.setIsn_usuario(rs.getInt("isn_usuario"));
        prof.setDsc_usuario(rs.getString("dsc_usuario"));
        prof.setIns_formacao(rs.getInt("isn_formacao"));
        prof.setDsc_formacao(rs.getString("dsc_formacao"));
        prof.setDsc_especialidade(rs.getString("dsc_especialidade"));

        return prof;
    }

}
