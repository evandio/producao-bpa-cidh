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
public class LoteBpa implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private Integer loteBpa;
    private Date dataAtendimento;
    private Profissional profissional;
    private Integer qtdAtendimento;
    private Integer turno;
    
    //private List<Atendimentos> atendimentos;

    public Integer getLoteBpa() {
        return loteBpa;
    }

    public void setLoteBpa(Integer loteBpa) {
        this.loteBpa = loteBpa;
    }

    public Date getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(Date dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }
    
    public Integer getQtdAtendimento() {
        return qtdAtendimento;
    }

    public void setQtdAtendimento(Integer qtdAtendimento) {
        this.qtdAtendimento = qtdAtendimento;
    }

    public Integer getTurno() {
        return turno;
    }

    public void setTurno(Integer turno) {
        this.turno = turno;
    }
    
    
 
    public LoteBpa() {
    }

    public LoteBpa(Integer loteBpa, Date dataAtendimento, Profissional profissional, Integer qtdAtendimento, Integer turno) {
        this.loteBpa = loteBpa;
        this.dataAtendimento = dataAtendimento;
        this.profissional = profissional;
        this.qtdAtendimento = qtdAtendimento;
        this.turno = turno;
    }

  
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.loteBpa);
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
        final LoteBpa other = (LoteBpa) obj;
        if (!Objects.equals(this.loteBpa, other.loteBpa)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LoteBpa{" + "loteBpa=" + loteBpa + ", dataAtendimento=" + dataAtendimento + ", profissional=" + profissional + ", qtdAtendimento=" + qtdAtendimento + ", turno=" + turno + '}';
    }

   
}
