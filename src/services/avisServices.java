/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dbConnection.MyDataBase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nizar
 */
public class avisServices {
    public String countAvis() {
        String numberRow="";
        try {
            String requete = "SELECT COUNT(*) FROM avis";
            PreparedStatement pst = new MyDataBase().getCnx()
                    .prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
             numberRow = rs.getString("count(*)");
    }
            return numberRow;
        } catch (SQLException ex) {
            Logger.getLogger(commandServices.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return "0";
        
    }
    
}
