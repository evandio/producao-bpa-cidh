/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author evandio.pereira
 */
public class Paciente implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer isnPaciente;
    private String numProntuario;
    private String dscPaciente;
    private String numCpf;
    private Integer isnSexo;
    private String dscSexo;
    private Date datNascimento;
    private Date datCadastro;
    private String numCns;
    private Integer isnCor;
    private String dscCor;
    private String dscEndereco;
    private String dscBairro;
    private Integer isnMunicipio;
    private String dscMunicipio;
    private Integer isnSituacaoFamiliar;
    private Integer isnEscolaridade;
    private Integer isnTipoLogradouro;
    private Integer isnUf;
    private String sglUf;
    private String numCep;
    private String telefone;

    public Paciente() {
    }

    public Paciente(Integer isnPaciente, String numProntuario, String dscPaciente, String numCpf, Integer isnSexo, String dscSexo, Date datNascimento, Date datCadastro, String numCns, Integer isnCor, String dscCor, String dscEndereco, String dscBairro, Integer isnMunicipio, String dscMunicipio, Integer isnSituacaoFamiliar, Integer isnEscolaridade, Integer isnTipoLogradouro, Integer isnUf, String sglUf, String numCep, String telefone) {
        this.isnPaciente = isnPaciente;
        this.numProntuario = numProntuario;
        this.dscPaciente = dscPaciente;
        this.numCpf = numCpf;
        this.isnSexo = isnSexo;
        this.dscSexo = dscSexo;
        this.datNascimento = datNascimento;
        this.datCadastro = datCadastro;
        this.numCns = numCns;
        this.isnCor = isnCor;
        this.dscCor = dscCor;
        this.dscEndereco = dscEndereco;
        this.dscBairro = dscBairro;
        this.isnMunicipio = isnMunicipio;
        this.dscMunicipio = dscMunicipio;
        this.isnSituacaoFamiliar = isnSituacaoFamiliar;
        this.isnEscolaridade = isnEscolaridade;
        this.isnTipoLogradouro = isnTipoLogradouro;
        this.isnUf = isnUf;
        this.sglUf = sglUf;
        this.numCep = numCep;
        this.telefone = telefone;
    }

    public Integer getIsnPaciente() {
        return isnPaciente;
    }

    public void setIsnPaciente(Integer isnPaciente) {
        this.isnPaciente = isnPaciente;
    }

    public String getNumProntuario() {
        return numProntuario;
    }

    public void setNumProntuario(String numProntuario) {
        this.numProntuario = numProntuario;
    }

    public String getDscPaciente() {
        return dscPaciente;
    }

    public void setDscPaciente(String dscPaciente) {
        this.dscPaciente = dscPaciente;
    }

    public String getNumCpf() {
        return numCpf;
    }

    public void setNumCpf(String numCpf) {
        this.numCpf = numCpf;
    }

    public Integer getIsnSexo() {
        return isnSexo;
    }

    public void setIsnSexo(Integer isnSexo) {
        this.isnSexo = isnSexo;
    }

    public String getDscSexo() {
        return dscSexo;
    }

    public void setDscSexo(String dscSexo) {
        this.dscSexo = dscSexo;
    }

    public Date getDatNascimento() {
        return datNascimento;
    }

    public void setDatNascimento(Date datNascimento) {
        this.datNascimento = datNascimento;
    }

    public Date getDatCadastro() {
        return datCadastro;
    }

    public void setDatCadastro(Date datCadastro) {
        this.datCadastro = datCadastro;
    }

    public String getNumCns() {
        return numCns;
    }

    public void setNumCns(String numCns) {
        this.numCns = numCns;
    }

    public Integer getIsnCor() {
        return isnCor;
    }

    public void setIsnCor(Integer isnCor) {
        this.isnCor = isnCor;
    }

    public String getDscCor() {
        return dscCor;
    }

    public void setDscCor(String dscCor) {
        this.dscCor = dscCor;
    }

    public String getDscEndereco() {
        return dscEndereco;
    }

    public void setDscEndereco(String dscEndereco) {
        this.dscEndereco = dscEndereco;
    }

    public String getDscBairro() {
        return dscBairro;
    }

    public void setDscBairro(String dscBairro) {
        this.dscBairro = dscBairro;
    }

    public Integer getIsnMunicipio() {
        return isnMunicipio;
    }

    public void setIsnMunicipio(Integer isnMunicipio) {
        this.isnMunicipio = isnMunicipio;
    }

    public String getDscMunicipio() {
        return dscMunicipio;
    }

    public void setDscMunicipio(String dscMunicipio) {
        this.dscMunicipio = dscMunicipio;
    }

    public Integer getIsnSituacaoFamiliar() {
        return isnSituacaoFamiliar;
    }

    public void setIsnSituacaoFamiliar(Integer isnSituacaoFamiliar) {
        this.isnSituacaoFamiliar = isnSituacaoFamiliar;
    }

    public Integer getIsnEscolaridade() {
        return isnEscolaridade;
    }

    public void setIsnEscolaridade(Integer isnEscolaridade) {
        this.isnEscolaridade = isnEscolaridade;
    }

    public Integer getIsnTipoLogradouro() {
        return isnTipoLogradouro;
    }

    public void setIsnTipoLogradouro(Integer isnTipoLogradouro) {
        this.isnTipoLogradouro = isnTipoLogradouro;
    }

    public Integer getIsnUf() {
        return isnUf;
    }

    public void setIsnUf(Integer isnUf) {
        this.isnUf = isnUf;
    }

    public String getNumCep() {
        return numCep;
    }

    public void setNumCep(String numCep) {
        this.numCep = numCep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSglUf() {
        return sglUf;
    }

    public void setSglUf(String sglUf) {
        this.sglUf = sglUf;
    }

    @Override
    public String toString() {
        return "Paciente{" + "isnPaciente=" + isnPaciente + ", numProntuario=" + numProntuario + ", dscPaciente=" + dscPaciente + ", numCpf=" + numCpf + ", isnSexo=" + isnSexo + ", dscSexo=" + dscSexo + ", datNascimento=" + datNascimento + ", datCadastro=" + datCadastro + ", numCns=" + numCns + ", isnCor=" + isnCor + ", dscCor=" + dscCor + ", dscEndereco=" + dscEndereco + ", dscBairro=" + dscBairro + ", isnMunicipio=" + isnMunicipio + ", dscMunicipio=" + dscMunicipio + ", isnSituacaoFamiliar=" + isnSituacaoFamiliar + ", isnEscolaridade=" + isnEscolaridade + ", isnTipoLogradouro=" + isnTipoLogradouro + ", isnUf=" + isnUf + ", sglUf=" + sglUf + ", numCep=" + numCep + ", telefone=" + telefone + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.isnPaciente);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Paciente other = (Paciente) obj;
        if (!Objects.equals(this.isnPaciente, other.isnPaciente)) {
            return false;
        }
        return true;
    }

}
