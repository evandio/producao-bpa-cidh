/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.services;

import model.dao.CboProfissionalDao;
import model.dao.DaoFactory;
import model.entities.CboProfissional;
import model.entities.Profissional;

/**
 *
 * @author evandio.pereira
 */
public class CboProfissionalService {

    private CboProfissionalDao dao = DaoFactory.createCboProfissional();

    public CboProfissional localizarCboProfissional(Profissional obj) {
        return dao.buscarCboProfissional(obj);
    }
}
