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
import java.util.List;
import model.entities.Paciente;
import model.dao.PacienteDao;

/**
 *
 * @author evandio.pereira
 */
public class PacienteDaoJDBC implements PacienteDao {

    private Connection conn;

    public PacienteDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Paciente> encontrarPacientes(String dscNome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Paciente encontrarPaciente(String numProntuario) {
        PreparedStatement st = null;
        ResultSet rs;

        String sql = " SELECT "
                + "     a.isn_paciente, a.num_prontuario, a.dsc_nome, a.num_cpf, a.dat_nascimento, a.dat_cadastro, a.isn_cor, a.num_cns, a.isn_sexo, "
                + "     b.isn_tipo_logradouro, b.dsc_endereco, b.dsc_complemento, b.dsc_bairro, b.isn_municipio, b.isn_uf, b.num_cep, b.num_ddd_1, b.num_telefone_1, b.num_ddd_2, b.num_telefone_2,"
                + "     b.isn_escolaridade, b.isn_situacao_familiar, "
                + "     c. dsc_cor, d.sgl_sexo, e.dsc_municipio, f.dsc_tipo_logradouro, g.sgl_uf "
                + "FROM  "
                + "	medpoint.t_paciente a, medpoint.t_internacao b, medpoint.t_cor c, medpoint.t_sexo d, medpoint.t_municipio e, medpoint.t_tipo_logradouro f, medpoint.t_uf g "
                + "WHERE "
                + "	a.num_prontuario = ? "
                + "and a.isn_paciente = b.isn_paciente "
                + "and a.isn_cor = c.isn_cor "
                + "and a.isn_sexo = d.isn_sexo "
                + "and b.isn_municipio = e.isn_municipio "
                + "and b.isn_tipo_logradouro = f.isn_tipo_logradouro "
                + "and b.isn_uf = g.isn_uf "
                + "ORDER BY b.isn_internacao DESC limit 1 ";

        Paciente p = new Paciente();

        try {
            st = conn.prepareStatement(sql);
            st.setString(1, numProntuario);
            rs = st.executeQuery();

            while (rs.next()) {
                p = instantiatePaciente(rs);
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

        return p;

    }

    @Override
    public Paciente encontrarPaciente(Integer isnPaciente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Paciente instantiatePaciente(ResultSet rs) throws SQLException {

        Paciente p = new Paciente();

        p.setIsnPaciente(rs.getInt("isn_paciente"));
        p.setNumProntuario(rs.getString("num_prontuario"));
        p.setDscPaciente(rs.getString("dsc_nome"));
        p.setNumCpf(rs.getString("num_cpf"));
        p.setDatNascimento(rs.getDate("dat_nascimento"));
        p.setDatCadastro(rs.getDate("dat_cadastro"));
        p.setIsnCor(rs.getInt("isn_cor"));
        p.setNumCns(rs.getString("num_cns"));
        p.setIsnSexo(rs.getInt("isn_sexo"));
        p.setDscSexo(rs.getString("sgl_sexo"));
        p.setIsnTipoLogradouro(rs.getInt("isn_tipo_logradouro"));
        p.setDscMunicipio(rs.getString("dsc_municipio"));

        String endereco = "";
        endereco += rs.getString("dsc_tipo_logradouro") + " ";
        endereco += rs.getString("dsc_endereco") + " - ";
        endereco += rs.getString("dsc_complemento");
        p.setDscEndereco(endereco);
        p.setSglUf(rs.getString("sgl_uf"));

        p.setDscBairro(rs.getString("dsc_bairro"));
        p.setIsnMunicipio(rs.getInt("isn_municipio"));
        p.setIsnUf(rs.getInt("isn_uf"));
        p.setNumCep(rs.getString("num_cep"));
        p.setIsnEscolaridade(rs.getInt("isn_escolaridade"));
        p.setIsnSituacaoFamiliar(rs.getInt("isn_situacao_familiar"));
        p.setDscCor(rs.getString("dsc_cor"));

        String contato = "";
        contato += rs.getString("num_ddd_1") + "-";
        contato += rs.getString("num_telefone_1") + "-";
        contato += rs.getString("num_ddd_2") + "-";
        contato += rs.getString("num_telefone_2");
        p.setTelefone(contato);

        return p;
    }
}
