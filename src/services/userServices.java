/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dbConnection.MyDataBase;
import entity.Commande;
import entity.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
//    *******************************************************************************************************************************************************
  private Connection con = MyDataBase.getInstance().getCnx();
    private Statement ste;

    public userServices() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
//***********************************************************************************************************************************************************

    public boolean ajouter(User t) throws SQLException {
        boolean etat = false;
        String requinsert = "INSERT INTO `user` (`id`, `nom`, `prenom`, `email`, `password`, `num_telephone`,`adresse`, `role` ,`verification_account` )"
                + "VALUES (NULL, '" + t.getNom() + "', '" + t.getPrenom() + "', '" + t.getEmail() + "', '" + t.getPassword() + "', " + t.getNum_telephone() + ", '" + t.getAdresse() + "','" + t.getRole() + "',"+ t.getVerification_account()+");";
        int res = ste.executeUpdate(requinsert);
        if (res != 0) {
            etat = true;
        }
        return etat;
    }
//************************************************************************************************************************************************************

    public boolean update(User t) throws SQLException {
        boolean etat = false;
        String requpdate = "UPDATE `user` SET `nom` = '" + t.getNom() + "',`prenom` = '" + t.getPrenom() + "',`email`='" + t.getEmail() + "',`password`='" + t.getPassword()
                + "',`numtelephone`= '" + t.getNum_telephone() + "',`adresse`='" + t.getAdresse() + "',`role`='" + t.getRole() + "'WHERE `user`.`id` = '" + t.getId() + "';";
        int res = ste.executeUpdate(requpdate);
        if (res != 0) {
            etat = true;
        }
        return etat;
    }
//****************************************************************************************************************************************************

    public boolean supprimer(User t) throws SQLException {
        boolean etat = false;
        String requet = "DELETE from `user` WHERE `id`=  " + t.getId();
        int res = ste.executeUpdate(requet);
        if (res != 0) {
            etat = true;
        }
        return etat;
    }
//*********************************************************************************************************************************************

    public boolean connexion(User t) throws SQLException {
        boolean etat = false;
        int a = 0;
        String requet = "select count(*) from `user` WHERE `password` = '" + t.getPassword() + "' and `email` = '" + t.getEmail() + "'";
        System.out.println(requet);
        ResultSet res = ste.executeQuery(requet);
        while (res.next()) {
            a = res.getInt(1);
        }
        if (a != 0) {
            etat = true;
        }
        return etat;
    }

//    ***********************************************************************************************************************************
    public String connexionRole(User t) throws SQLException {
        String role = "";
        String requet = "select `role` from `user` WHERE `password` = '" + t.getPassword() + "' and `email` = '" + t.getEmail() + "'";
        System.out.println(requet);
        ResultSet res = ste.executeQuery(requet);
        while (res.next()) {
            role = res.getString(1);
        }
        return role;
    }

//***********************************************************************************************************************************************************
    public ObservableList<User> readAll() throws SQLException {
        ObservableList<User> listu = FXCollections.observableArrayList();
        ResultSet rs = ste.executeQuery("SELECT * from `user`");
        while (rs.next()) {
            int id = rs.getInt(1);
            String nom = rs.getString(2);
            String prenom = rs.getString(3);
            String email = rs.getString(4);
            String password = rs.getString(5);
            int num_telephone = rs.getInt(6);
            String adresse = rs.getString(7);
            User u = new User(id, nom, prenom, email, password, num_telephone, adresse);
            u.setRole(rs.getString(8));
            listu.add(u);
        }
        return listu;
    }
}
    

