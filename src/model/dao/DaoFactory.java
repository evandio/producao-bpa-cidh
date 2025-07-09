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
import model.dao.imp.LoteBpaDaoJDBC;
import model.dao.imp.CboDaoJDBC;
import model.dao.imp.CboProfissionalDaoJDBC;
import model.dao.imp.DiagnosticoDaoJDBC;
import model.dao.imp.GeradorDeChaveDaoJDBC;
import model.dao.imp.PacienteDaoJDBC;
import model.dao.imp.ProcedimentoJDBC;

/**
 *
 * @author evandio.pereira
 */
public class DaoFactory {

    public static ProfissionalDao createProfissionalDao() {
        return new ProfissionalDaoJDBC(DB_Vitae.getConnection());
    }

    public static GeradorChaveDao createGeradorChaveDao() {
        return new GeradorDeChaveDaoJDBC(DB_Bpa.getConnection());
    }

    public static CboDao createCboDaoGil() {
        return new CboDaoJDBC(DB_Gil.getConnection());
    }

    public static CboDao createCboDaoBpa() {
        return new CboDaoJDBC(DB_Bpa.getConnection());
    }

    public static ProcedimentoDao createProcedimentoDaoGil() {
        return new ProcedimentoJDBC(DB_Gil.getConnection());
    }

    public static ProcedimentoDao createProcedimentoDaoAppBpa() {
        return new ProcedimentoJDBC(DB_Bpa.getConnection());
    }

    public static CboProfissionalDao createCboProfissional() {
        return new CboProfissionalDaoJDBC(DB_Bpa.getConnection());
    }

    public static PacienteDao createPacienteDao() {
        return new PacienteDaoJDBC(DB_Vitae.getConnection());
    }

    public static DiagnosticoDao createDiagnosticoDao() {
        return new DiagnosticoDaoJDBC(DB_Bpa.getConnection());
    }

    public static LoteBpaDao createBpaDao() {
        return new LoteBpaDaoJDBC(DB_Bpa.getConnection());
    }

}
