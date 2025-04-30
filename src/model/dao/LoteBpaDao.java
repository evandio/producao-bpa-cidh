/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.entities.LoteBpa;

/**
 *
 * @author evandio.pereira
 */
public interface LoteBpaDao {

    List<LoteBpa> listaBpa();

    void saveOrUpdateLote(LoteBpa obj);

    void inserir(LoteBpa obj);

    void atualizar(LoteBpa obj);

    void closeLote(Object obj);

    void openLote(Object obj);

}
