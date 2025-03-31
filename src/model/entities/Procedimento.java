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
public class Procedimento implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Integer lote;
    private String descricao;

    public Integer getLote() {
        return lote;
    }

    public void setLote(Integer lote) {
        this.lote = lote;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Procedimento(Integer lote, String descricao) {
        this.lote = lote;
        this.descricao = descricao;
    }

    public Procedimento() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.lote);
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
        final Procedimento other = (Procedimento) obj;
        if (!Objects.equals(this.lote, other.lote)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Procedimento{" + "lote=" + lote + ", descricao=" + descricao + '}';
    }

}
