/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.dao.imp.ProfissionalDaoJDBC;
import db.DB;

/**
 *
 * @author evandio.pereira
 */
public class DaoFactory {

    public static ProfissionalDao createProfissionalDao() {
        return new ProfissionalDaoJDBC(DB.getConnection());
    }

}
