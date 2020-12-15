/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dbConnection.MyDataBase;
import entity.Evenement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ahmed
 */
public class EvenementController {
    Connection cnx;
    public EvenementController() {
        cnx=MyDataBase.getInstance().getCnx();
    }
    public void ajouterEvenement(Evenement e)  {
        try {
            String requete = "INSERT INTO evenement (titre,lieu,description,date) values (?,?,?,?)";

            PreparedStatement pst = cnx
                    .prepareStatement(requete);
            pst.setString(1, e.getTitre());
            pst.setString(2, e.getLieu());
            pst.setString(3, e.getDescription());
            pst.setString(4, e.getDate());
            
            pst.executeUpdate();
            System.out.println("Evenement ajouté!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public boolean supprimerEvenement(Evenement e){
        boolean etat;
        try {
            String requete ="Delete from evenement where id=?";
            PreparedStatement pst = cnx
                    .prepareStatement(requete);
            pst.setInt(1, e.getId());
            pst.executeUpdate();
            System.out.println("event supprime");
            etat=true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            etat=false;
        }
        return etat;
    }
    
    public List<Evenement> listerEvenement(){
         List<Evenement> myList = new ArrayList<Evenement>();
         try {
            String requete= "SELECT * FROM evenement";
            Statement st = cnx
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Evenement e = new Evenement();
                e.setId(rs.getInt(1));
                e.setTitre(rs.getString("titre"));
                e.setLieu(rs.getString("lieu"));
                e.setDescription(rs.getString("description"));
                myList.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
        }
    
    public void modifierEvenement(Evenement e,int id){
        try {
            String requete ="Update evenement set titre=?,lieu=?,description=? where id=?";
            PreparedStatement pst = cnx
                    .prepareStatement(requete);
            
            pst.setString(1, e.getTitre());
            pst.setString(2, e.getLieu());
            pst.setString(3, e.getDescription());
            pst.setInt(3, id);
            pst.executeUpdate();
            System.out.println("Evenement modifiéS");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
