/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import java.util.Objects;

/**
 *
 * @author evandio.pereira
 */
public class Cbo {
    private String isn_cbo;
    private String dsc_cbo;

    public Cbo(String isn_cbo, String dsc_cbo) {
        this.isn_cbo = isn_cbo;
        this.dsc_cbo = dsc_cbo;
    }

    public Cbo() {
    }

    public String getIsn_cbo() {
        return isn_cbo;
    }

    public void setIsn_cbo(String isn_cbo) {
        this.isn_cbo = isn_cbo;
    }

    public String getDsc_cbo() {
        return dsc_cbo;
    }

    public void setDsc_cbo(String dsc_cbo) {
        this.dsc_cbo = dsc_cbo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.isn_cbo);
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
        final Cbo other = (Cbo) obj;
        if (!Objects.equals(this.isn_cbo, other.isn_cbo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getDsc_cbo() ;
    }
    
    
}
