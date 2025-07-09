

import db.DB_Bpa;
import java.util.List;
import model.dao.imp.GeradorDeChaveDaoJDBC;
import model.entities.Diagnostico;
import model.entities.Paciente;
import model.entities.RelacaoProcedCbo;

import model.services.DiagnosticoService;
import model.services.PacienteService;
import model.services.ProcedimentoService;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author evandio.pereira
 */
public class Teste {

    public static void main(String[] args) {
        
        ProcedimentoService service = new ProcedimentoService();
        
        List<RelacaoProcedCbo> r = service.listaProcedCboGil();
        
        service.gravarProcedCbo(r);
        System.out.println("finalizado");
        
    }
}
