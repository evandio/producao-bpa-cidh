/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.entities.Procedimento;
import model.entities.RelacaoProcedCbo;

/**
 *
 * @author evandio.pereira
 */
public interface ProcedimentoDao {

    List<Procedimento> listaProcedimentoGil();

    List<RelacaoProcedCbo> listaProcedCboGil();

    void gravarProcedimento(Procedimento proced);

    void gravarProcedimentos(List<Procedimento> listaProced);
    
    void gravarProcedCbo(List<RelacaoProcedCbo> lista);
}
