/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.services;

import java.util.List;
import model.dao.CboDao;
import model.dao.CboProfissionalDao;
import model.dao.DaoFactory;
import model.entities.Cbo;
import model.entities.CboProfissional;
import model.entities.Profissional;

/**
 *
 * @author evandio.pereira
 */
public class CboProfissionalService {

    private CboProfissionalDao daoCboProf = DaoFactory.createCboProfissional();
    private CboDao daoCbo = DaoFactory.createCboDaoBpa();

    public CboProfissional localizarCboProfissional(Profissional obj) {
        return daoCboProf.buscarCboProfissional(obj);
    }
    
    public CboProfissional localizarCboProfissional(String isnCbo) {
        return daoCboProf.buscarCboProfissional(isnCbo);
    }

    public void saveOrUpdate(Profissional objProf) {
        if (objProf.getObjCboProf().getIsnProfissional() == null) {
            daoCboProf.gravarCboProf(objProf);
        } else {
            daoCboProf.updateCboProf(objProf);
        }
    }

    public List<Cbo> todosCbos() {
        return daoCbo.listaTodosCbosBpa();
    }
    
   

}
