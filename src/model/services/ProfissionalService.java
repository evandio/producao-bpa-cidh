/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.services;

import java.util.List;
import model.dao.CboDao;
import model.dao.DaoFactory;
import model.dao.ProfissionalDao;
import model.entities.Cbo;
import model.entities.Profissional;

/**
 *
 * @author evandio.pereira
 */
public class ProfissionalService {

    private ProfissionalDao dao = DaoFactory.createProfissionalDao();
    private CboDao daoCbo = DaoFactory.createCboDaoBpa();

    public List<Profissional> localizarTodos() {
        return dao.localizarTodos();
    }

    public List<Profissional> localizarProfissionais(String nome) {
        return dao.localizarProfissionais(nome);
    }

    public Profissional localizarProfissional(String nome) {
        return dao.localizarProfissional(nome);
    }
    
    public Profissional localizarProfissional(Integer isnProfissional) {
        return dao.localizarProfissional(isnProfissional);
    }

    public List<Cbo> todosCbosBpa() {
        return daoCbo.listaTodosCbosBpa();
    }
}
