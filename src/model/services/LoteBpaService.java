/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.entities.LoteBpa;
import model.entities.Profissional;

/**
 *
 * @author evandio.pereira
 */
public class LoteBpaService {

    
    
    
    
    
    
    
    //Mockar os dados 
    public List<LoteBpa> localizarTodos() {

        Date data = null;
        try {
            SimpleDateFormat st = new SimpleDateFormat("dd/MM/yyyy");
            data = new SimpleDateFormat("dd/MM/yyyy").parse("31/03/2025");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<LoteBpa> list = new ArrayList<>();

        //list.add(new LoteBpa(1, data, new Profissional(1, "Evandio Pereira", 1, "SUPERIOR", "001", "PROGRAMADOR"), 10, 1  ) );
        //list.add(new LoteBpa(2, data, new Profissional(2, "Antonio", 1, "SUPERIOR", "002", "ENFERMEIRO"), 4,  1));
        //list.add(new LoteBpa(3, data, new Profissional(3, "Pereira", 1, "SUPERIOR", "003", "ANALISTA"), 6, 1));
        //list.add(new LoteBpa(4, data, new Profissional(4, "Sinval", 1, "MÉDICO", "CLIICO"), 20, 2));
        //list.add(new LoteBpa(5, data, new Profissional(5, "Shiguetaka", 1, "MÉDICO", "CARDIOLOGISTA"), 21, 2));
        //list.add(new LoteBpa(6, data, new Profissional(6, "Marilia", 1, "SUPERIOR", "NUTRICIONISTA"), 13, 1));
        
        
        return list;
    }
}
