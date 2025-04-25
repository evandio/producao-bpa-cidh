

import db.DB_Bpa;
import model.dao.imp.GeradorDeChaveDaoJDBC;
import model.entities.Diagnostico;

import model.services.DiagnosticoService;




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
        DiagnosticoService service = new DiagnosticoService();
        
        Diagnostico d = new Diagnostico();
        d.setDscDiagnostico("HAS");
        
        
        service.inserirOuAtualizar(d);
        
        System.out.println("");
        
    }
}
