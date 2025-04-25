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
public class Diagnostico implements Serializable {

    private Integer isnDiagnostico;
    private String dscDiagnostico;

    public Integer getIsnDiagnostico() {
        return isnDiagnostico;
    }

    public void setIsnDiagnostico(Integer isnDiagnostico) {
        this.isnDiagnostico = isnDiagnostico;
    }

    public String getDscDiagnostico() {
        return dscDiagnostico;
    }

    public void setDscDiagnostico(String dscDiagnostico) {
        this.dscDiagnostico = dscDiagnostico;
    }

    public Diagnostico() {
    }

    public Diagnostico(Integer isnDiagnostico, String dscDiagnostico) {
        this.isnDiagnostico = isnDiagnostico;
        this.dscDiagnostico = dscDiagnostico;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.isnDiagnostico);
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
        final Diagnostico other = (Diagnostico) obj;
        if (!Objects.equals(this.isnDiagnostico, other.isnDiagnostico)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Diagnostico{" + "isnDiagnostico=" + isnDiagnostico + ", dscDiagnostico=" + dscDiagnostico + '}';
    }

    
}
