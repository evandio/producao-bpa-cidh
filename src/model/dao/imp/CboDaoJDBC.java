/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.imp;

import java.sql.Connection;
import java.util.List;
import model.dao.CboDao;
import model.entities.Cbo;


/**
 *
 * @author evandio.pereira
 */
public class CboDaoJDBC implements CboDao{

    private Connection conn;

    public CboDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Cbo> listaCbosGil() {
        
    }
    
    
    
    
    
}
