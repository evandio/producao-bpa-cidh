/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.entities.CboProfissional;
import model.entities.Profissional;

/**
 *
 * @author evandio.pereira
 */
public interface CboProfissionalDao {

    CboProfissional buscarCboProfissional(Profissional obj);
    
    CboProfissional buscarCboProfissional(String isnCbo);

    void gravarCboProf(Profissional objProf);

    void updateCboProf(Profissional objProf);

}
