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
import java.util.Date;
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
    public void inserir(LoteBpa obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO bpa_cidh.tb_lote "
                    + "(nr_lote, isn_profissional, dsc_profissional, data_atendimento, turno, qtd_atendimento) "
                    + "VALUES (?, ?, ?, ?, ?, ?)"
            );
            st.setInt(1, obj.getLoteBpa());
            st.setInt(2, obj.getProfissional().getIsnUsuario());
            st.setString(3, obj.getProfissional().getDscUsuario());
            st.setTimestamp(4, new java.sql.Timestamp(obj.getDataAtendimento().getTime()));
            st.setInt(5, "Manhã".equals(obj.getTurno()) ? 1 : 2); // 1 = Manhã, 2 = Tarde
            st.setInt(6, obj.getQtdAtendimento() != null ? obj.getQtdAtendimento() : 0);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao inserir Lote BPA: " + e.getMessage());
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                throw new DbException("Erro ao fechar statement: " + e.getMessage());
            }
        }
    }

    @Override
    public void atualizar(LoteBpa obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE bpa_cidh.tb_lote "
                    + "SET isn_profissional = ?, dsc_profissional = ?, data_atendimento = ?, turno = ?, qtd_atendimento = ? "
                    + "WHERE nr_lote = ?"
            );
            st.setInt(1, obj.getProfissional().getIsnUsuario());
            st.setString(2, obj.getProfissional().getDscUsuario());
            st.setTimestamp(3, new java.sql.Timestamp(obj.getDataAtendimento().getTime()));
            st.setInt(4, obj.getTurno()); // 1 = Manhã, 2 = Tarde
            st.setInt(5, obj.getQtdAtendimento() != null ? obj.getQtdAtendimento() : 0);
            st.setInt(6, obj.getLoteBpa());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao atualizar Lote BPA: " + e.getMessage());
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                throw new DbException("Erro ao fechar statement: " + e.getMessage());
            }
        }
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

    @Override
    public boolean existsLoteBpa(Integer isnProfissional, Date dataAtendimento, int turno, Integer nrLote) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT COUNT(*) AS total FROM bpa_cidh.tb_lote "
                    + "WHERE isn_profissional = ? AND DATE(data_atendimento) = DATE(?) AND turno = ? "
                    + (nrLote != null ? "AND nr_lote != ?" : ""));

            st.setInt(1, isnProfissional);
            st.setDate(2, new java.sql.Date(dataAtendimento.getTime()));
            st.setInt(3, turno);
             if (nrLote != null) {
                st.setInt(4, nrLote);
            }

            rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
            return false;
        } catch (SQLException e) {
            throw new DbException("Erro ao verificar existência de Lote BPA: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                throw new DbException("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }

}
