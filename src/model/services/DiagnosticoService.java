/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.services;

import java.util.List;
import model.dao.DaoFactory;
import model.dao.DiagnosticoDao;
import model.entities.Diagnostico;

/**
 *
 * @author evandio.pereira
 */
public class DiagnosticoService {

    private DiagnosticoDao dao = DaoFactory.createDiagnosticoDao();

    public List<Diagnostico> todosDiagnostico() {
        return dao.todosDiagnostico();
    }

    public Diagnostico getDiagnostico(Integer isn_diag) {
        return dao.getDiagnostico(isn_diag);
    }

    public void inserirOuAtualizar(Diagnostico obj) {
        if (obj.getIsnDiagnostico() == null) {
            int chave = (int) new GeradorChaveService().getProximaChave("tb_diagnostico");
            obj.setIsnDiagnostico(chave);
            dao.inserir(obj);
        } else {
            dao.atualizar(obj);
        }
    }
}
