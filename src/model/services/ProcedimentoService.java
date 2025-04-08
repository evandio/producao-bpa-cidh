/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.services;

import java.util.List;
import model.dao.DaoFactory;
import model.dao.ProcedimentoDao;
import model.entities.Procedimento;

/**
 *
 * @author evandio.pereira
 */
public class ProcedimentoService {

    private ProcedimentoDao daoGil = DaoFactory.createProcedimentoGil();
    private ProcedimentoDao daoBpa = DaoFactory.createProcedimentoBpa();

    public List<Procedimento> listaProcedimentosGil() {
        return daoGil.listaProcedimentoGil();
    }

    public void gravarProcedimentoBpa(List<Procedimento> lista) {
        daoBpa.gravarProcedimento(lista);
    }
}
