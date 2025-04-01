/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import db.DB_Bpa;
import model.dao.imp.ProfissionalDaoJDBC;
import db.DB_Vitae;
import model.dao.imp.GeradorDeChaveDaoJDBC;

/**
 *
 * @author evandio.pereira
 */
public class DaoFactory {

    public static ProfissionalDao createProfissionalDao() {
        return new ProfissionalDaoJDBC(DB_Vitae.getConnection());
    }

    public static GeradorDeChaveDao createGeradorDeChaveDao() {
        return new GeradorDeChaveDaoJDBC(DB_Bpa.getConnection());
    }

}
