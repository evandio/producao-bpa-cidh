/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.entities.Profissional;

/**
 *
 * @author evandio.pereira
 */
public interface ProfissionalDao {

    List<Profissional> localizarTodos();

    List<Profissional> localizarProfissionais(String nome);
    
    Profissional localizarProfissional(String nome);
    
    Profissional localizarProfissional(Integer prontuario);
}
