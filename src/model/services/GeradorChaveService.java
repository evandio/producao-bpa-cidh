/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.services;

import model.dao.DaoFactory;
import model.dao.GeradorChaveDao;

/**
 *
 * @author evandio.pereira
 */
public class GeradorChaveService {

    GeradorChaveDao dao = DaoFactory.createGeradorChaveDao();

    public long getProximaChave(String tabela) {
        return dao.getProximoCodigo(tabela);
    }
}
