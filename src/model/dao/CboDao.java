/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.entities.Cbo;

/**
 *
 * @author evandio.pereira
 */
public interface CboDao {

    List<Cbo> listaCbosGil();

    void gravarCbo(Cbo cbo);

    void gravarCbo(List<Cbo> listaCbos);

    List<Cbo> listaTodosCbosBpa();

}
