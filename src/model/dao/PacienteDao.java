/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.entities.Paciente;

/**
 *
 * @author evandio.pereira
 */
public interface PacienteDao {

    //Nao implementado
    List<Paciente> encontrarPacientes(String dscNome);

    Paciente encontrarPaciente(String numProntuario);

    //Nao implementado
    Paciente encontrarPaciente(Integer isnPaciente);

}
