/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.services;

import model.dao.DaoFactory;
import model.dao.PacienteDao;
import model.entities.Paciente;

/**
 *
 * @author evandio.pereira
 */
public class PacienteService {

    private PacienteDao dao = DaoFactory.createPacienteDao();

    public Paciente localizarPaciente(String numProntuario) {
        return dao.encontrarPaciente(numProntuario);
    }
}
