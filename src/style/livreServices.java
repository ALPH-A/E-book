/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package style;

import dbConnection.MyDataBase;
import entity.Livre;
import entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author nizar
 */
public class livreServices {
    public Livre getLivreById(int livreID){
         try {
            String requete= "SELECT * FROM livre WHERE id="+livreID;
            Statement st = new MyDataBase().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                
                 Livre livre = new Livre(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6));
                
                return livre;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
        return null;
        }
    
    
}
