/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dbConnection.MyDataBase;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entity.Commande;

/**
 *
 * @author nizar
 */
public class commandeController {

    public void ajouterCommande(float prixtotal, Date datecommande, int clientID, int livreID) {
        try {
            String requete = "INSERT INTO `commande`(`prixtotal`, `datecommande`, `clientID`, `livreID`) "
                    + "VALUES (?,?,?,?)";

            PreparedStatement pst = new MyDataBase().getCnx()
                    .prepareStatement(requete);
            pst.setFloat(1, prixtotal);
            pst.setDate(2, datecommande);
            pst.setInt(3, clientID);
            pst.setInt(4, livreID);
            pst.executeUpdate();
            System.out.println("Commande ajoutée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void supprimerCommande(int commandeId) {

        try {
            String requete = "DELETE FROM commande WHERE id=?";
            PreparedStatement pst = new MyDataBase().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, commandeId);
            pst.executeUpdate();
            System.out.println("Personne supprimée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }
    public void modifierCommande(float prixtotal, Date datecommande, int clientID, int livreID,int commandeId) {

        
    try {
            String requete = "UPDATE `commande` SET `prixtotal`=?,`datecommande`=?,`clientID`=?,`livreID`=? WHERE `id`=?";

            PreparedStatement pst = new MyDataBase().getCnx()
                    .prepareStatement(requete);
            pst.setFloat(1, prixtotal);
            pst.setDate(2, datecommande);
            pst.setInt(3, clientID);
            pst.setInt(4, livreID);
            pst.setInt(5, commandeId);
            pst.executeUpdate();
            System.out.println("Commande ajoutée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public List<Commande> listerCommandes(){
         List<Commande> myList = new ArrayList<Commande>();
         try {
            String requete= "SELECT * FROM Commande";
            Statement st = new MyDataBase().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                
                Commande c = new Commande(rs.getInt(1), rs.getFloat(2), rs.getDate(3), rs.getInt(4), rs.getInt(5));
                
                myList.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
        }
}
