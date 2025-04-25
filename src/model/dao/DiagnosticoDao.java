/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.entities.Diagnostico;

/**
 *
 * @author evandio.pereira
 */
public interface DiagnosticoDao {

    List<Diagnostico> todosDiagnostico();

    Diagnostico getDiagnostico(Integer isnDiagnostico);

    void inserir(Diagnostico obj);

    void atualizar(Diagnostico obj);
}
