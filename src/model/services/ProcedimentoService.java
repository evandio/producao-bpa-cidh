/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.services;

import java.util.List;
import model.dao.DaoFactory;
import model.entities.Procedimento;
import model.dao.ProcedimentoDao;
import model.entities.RelacaoProcedCbo;

/**
 *
 * @author evandio.pereira
 */
public class ProcedimentoService {

    private ProcedimentoDao daoAppGil = DaoFactory.createProcedimentoDaoGil();
    private ProcedimentoDao daoAppBpa = DaoFactory.createProcedimentoDaoAppBpa();

    public List<Procedimento> listaProcedimentosGil() {
        return daoAppGil.listaProcedimentoGil();
    }
    
    public List <RelacaoProcedCbo> listaProcedCboGil(){
        return daoAppGil.listaProcedCboGil();
    }

    public void gravarProcedimentoBpa(List<Procedimento> lista) {
        daoAppBpa.gravarProcedimentos(lista);
    }
    
    public void gravarProcedCbo(List<RelacaoProcedCbo> lista){
        daoAppBpa.gravarProcedCbo(lista);
    }

}
