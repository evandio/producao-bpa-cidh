/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.Date;
import java.util.List;
import model.entities.LoteBpa;

/**
 *
 * @author evandio.pereira
 */
public interface LoteBpaDao {

    List<LoteBpa> listaBpa();

    void inserir(LoteBpa obj);

    void atualizar(LoteBpa obj);

    boolean existsLoteBpa(Integer isnProfissional, Date dataAtendimento, int turno, Integer nrLote);

    void closeLote(Object obj);

    void openLote(Object obj);

}
