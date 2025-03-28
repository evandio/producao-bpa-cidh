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

    private Integer isn_usuario;
    private String dsc_usuario;
    private Integer ins_formacao;
    private String dsc_formacao;
    private String dsc_especialidade;

    public Integer getIsn_usuario() {
        return isn_usuario;
    }

    public void setIsn_usuario(Integer isn_usuario) {
        this.isn_usuario = isn_usuario;
    }

    public String getDsc_usuario() {
        return dsc_usuario;
    }

    public void setDsc_usuario(String dsc_usuario) {
        this.dsc_usuario = dsc_usuario;
    }

    public Integer getIns_formacao() {
        return ins_formacao;
    }

    public void setIns_formacao(Integer ins_formacao) {
        this.ins_formacao = ins_formacao;
    }

    public String getDsc_formacao() {
        return dsc_formacao;
    }

    public void setDsc_formacao(String dsc_formacao) {
        this.dsc_formacao = dsc_formacao;
    }

    public String getDsc_especialidade() {
        return dsc_especialidade;
    }

    public void setDsc_especialidade(String dsc_especialidade) {
        this.dsc_especialidade = dsc_especialidade;
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
        if (!Objects.equals(this.isn_usuario, other.isn_usuario)) {
            return false;
        }
        return true;
    }

    public Profissional(Integer isn_usuario, String dsc_usuario, Integer ins_formacao, String dsc_formacao, String dsc_especialidade) {
        this.isn_usuario = isn_usuario;
        this.dsc_usuario = dsc_usuario;
        this.ins_formacao = ins_formacao;
        this.dsc_formacao = dsc_formacao;
        this.dsc_especialidade = dsc_especialidade;
    }

    public Profissional() {
    }

    @Override
    public String toString() {
        return "Profissional{" + "isn_usuario=" + isn_usuario + ", dsc_usuario=" + dsc_usuario + ", ins_formacao=" + ins_formacao + ", dsc_formacao=" + dsc_formacao + ", dsc_especialidade=" + dsc_especialidade + '}';
    }
    
}
