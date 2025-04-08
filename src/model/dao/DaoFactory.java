/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import db.DB_Bpa;
import db.DB_Gil;
import model.dao.imp.ProfissionalDaoJDBC;
import db.DB_Vitae;
import model.dao.imp.CboDaoJDBC;
import model.dao.imp.GeradorDeChaveDaoJDBC;
import model.dao.imp.ProcedimentoJDBC;
import model.entities.Cbo;

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

    public static CboDao createCboDaoGil() {
        return new CboDaoJDBC(DB_Gil.getConnection());
    }

    public static CboDao createCboDaoBpa() {
        return new CboDaoJDBC(DB_Bpa.getConnection());
    }

    public static ProcedimentoDao createProcedimentoGil() {
        return new ProcedimentoJDBC(DB_Gil.getConnection());
    }

    public static ProcedimentoDao createProcedimentoBpa() {
        return new ProcedimentoJDBC(DB_Bpa.getConnection());
    }

}
