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
    
   private String isnCodProcedimento;
   private String dscProcedimento;

    public Procedimento() {
    }

    public Procedimento(String isnCodProcedimento, String dscProcedimento) {
        this.isnCodProcedimento = isnCodProcedimento;
        this.dscProcedimento = dscProcedimento;
    }

    public String getIsnCodProcedimento() {
        return isnCodProcedimento;
    }

    public void setIsnCodProcedimento(String isnCodProcedimento) {
        this.isnCodProcedimento = isnCodProcedimento;
    }

    public String getDscProcedimento() {
        return dscProcedimento;
    }

    public void setDscProcedimento(String dscProcedimento) {
        this.dscProcedimento = dscProcedimento;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.isnCodProcedimento);
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
        if (!Objects.equals(this.isnCodProcedimento, other.isnCodProcedimento)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Procedimento{" + "isnCodProcedimento=" + isnCodProcedimento + ", dscProcedimento=" + dscProcedimento + '}';
    }
   
    
}
