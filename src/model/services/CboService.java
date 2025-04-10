/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.services;

import java.util.List;
import model.dao.CboDao;
import model.dao.DaoFactory;
import model.entities.Cbo;

/**
 *
 * @author evandio.pereira
 */
public class CboService {

    private CboDao dao = DaoFactory.createCboDaoBpa();

    public List<Cbo> todosCbos() {
        return dao.listaTodosCbosBpa();
    }
}
