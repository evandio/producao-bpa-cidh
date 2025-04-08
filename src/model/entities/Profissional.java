/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author evandio.pereira
 */
public class Profissional implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer isnUsuario;
    private String dscUsuario;
    private Integer insFormacao;
    private String dscFormacao;
    private String numConselho;
    private String sglConselho;

    public Integer getIsnUsuario() {
        return isnUsuario;
    }

    public void setIsnUsuario(Integer isn_usuario) {
        this.isnUsuario = isn_usuario;
    }

    public String getDscUsuario() {
        return dscUsuario;
    }

    public void setDscUsuario(String dsc_usuario) {
        this.dscUsuario = dsc_usuario;
    }

    public Integer getInsFormacao() {
        return insFormacao;
    }

    public void setInsFormacao(Integer ins_formacao) {
        this.insFormacao = ins_formacao;
    }

    public String getDscFormacao() {
        return dscFormacao;
    }

    public void setDscFormacao(String dsc_formacao) {
        this.dscFormacao = dsc_formacao;
    }
    
    public String getNumConselho() {
        return numConselho;
    }

    public void setNumConselho(String numConselho) {
        this.numConselho = numConselho;
    }

    public String getSglConselho() {
        return sglConselho;
    }

    public void setSglConselho(String sglConselho) {
        this.sglConselho = sglConselho;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Profissional other = (Profissional) obj;
        if (!Objects.equals(this.isnUsuario, other.isnUsuario)) {
            return false;
        }
        return true;
    }

    public Profissional() {
    }

    public Profissional(Integer isnUsuario, String dscUsuario, Integer insFormacao, String dscFormacao, String numConselho, String sglConselho) {
        this.isnUsuario = isnUsuario;
        this.dscUsuario = dscUsuario;
        this.insFormacao = insFormacao;
        this.dscFormacao = dscFormacao;
        this.numConselho = numConselho;
        this.sglConselho = sglConselho;
    }

    @Override
    public String toString() {
        return "Profissional{" + "isnUsuario=" + isnUsuario + ", dscUsuario=" + dscUsuario + ", insFormacao=" + insFormacao + ", dscFormacao=" + dscFormacao + ", numConselho=" + numConselho + ", sglConselho=" + sglConselho + '}';
    }

}
