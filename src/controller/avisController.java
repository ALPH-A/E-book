/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dbConnection.MyDataBase;
import entity.Avis;
import entity.Commande;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nizar
 */
public class avisController {

    public void ajouterAvis(String description, int clientID, int livreID) {
        try {
            String requete = "INSERT INTO `avis`(`description`,  `userID`, `livreID`) "
                    + "VALUES (?,?,?)";

            PreparedStatement pst = new MyDataBase().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, description);

            pst.setInt(2, clientID);
            pst.setInt(3, livreID);
            pst.executeUpdate();
            System.out.println("avis ajoutée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimerAvis(int commandeId) {

        try {
            String requete = "DELETE FROM avis WHERE id=?";
            PreparedStatement pst = new MyDataBase().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, commandeId);
            pst.executeUpdate();
            System.out.println("avis supprimée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

    public void modifierCommande(String description, int clientID, int livreID, int commandeId) {

        try {
            String requete = "UPDATE `avis` SET `description`=?,`clientID`=?,`livreID`=? WHERE `id`=?";

            PreparedStatement pst = new MyDataBase().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, description);
            pst.setInt(2, clientID);
            pst.setInt(3, livreID);
            pst.setInt(4, commandeId);
            pst.executeUpdate();
            System.out.println("Commande modifier!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Avis> listerCommandes() {
        List<Avis> myList = new ArrayList<Avis>();
        try {
            String requete = "SELECT * FROM avis";
            Statement st = new MyDataBase().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {

                Avis c = new Avis(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));

                myList.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

}
