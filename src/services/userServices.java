/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dbConnection.MyDataBase;
import entity.Commande;
import entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nizar
 */
public class userServices {
    public User getUserById(int clientID){
         try {
            String requete= "SELECT * FROM user WHERE id="+clientID;
            Statement st = new MyDataBase().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                
                 User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10));
                
                return user;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
        return null;
        }
    }
    

