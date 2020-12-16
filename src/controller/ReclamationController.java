/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dbConnection.MyDataBase;
import entity.Reclamation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Spingler-PC
 */
public class ReclamationController {
   
    
    
        public void ajouterReclamation(String sujet, String description ,int id ){
        try {
//            String requete = "INSERT INTO personne(nom,prenom) "
//                    + "VALUES ('"+p.getNom()+"','"+p.getPrenom()+"')";
            
            String requete = "INSERT INTO reclamation(`sujet`,`description`,`user_id`) "
                    + "VALUES (?,?,?)";
            
            PreparedStatement pst =new MyDataBase().getCnx().prepareStatement(requete);
            pst.setString(1, sujet);
            pst.setString(2, description);
            pst.setInt(3, id);
            
            pst.executeUpdate();
            System.out.println("reclamation ajoutée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
    }
    
     public List<Reclamation> listerReclamations(){
         List<Reclamation> myList = new ArrayList<Reclamation>();
         try {
            String requete= "SELECT * FROM reclamation";
            Statement st = new MyDataBase().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Reclamation p = new Reclamation();
                p.setId(rs.getInt(1));
                p.setSujet(rs.getString(2));
                p.setDescription(rs.getString(3));
                p.setUser_id(rs.getInt(4));
                p.setStatus(rs.getString(5));
                p.setCreated_at(rs.getDate(6));
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
        }
     public List<Reclamation> listerReclamationsDone(){
         
         List<Reclamation> myList = new ArrayList<Reclamation>();
         try {
            String requete= "SELECT * FROM reclamation where status='traiter'";
            Statement st = new MyDataBase().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Reclamation p = new Reclamation(rs.getInt(1), rs.getString(2),rs.getInt(4), rs.getDate(6));
              
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
         
     }
      public List<Reclamation> listerReclamationsByClient(int id){
         
         List<Reclamation> myList = new ArrayList<Reclamation>();
         try {
            String requete= "SELECT * FROM reclamation where id="+id;
            Statement st = new MyDataBase().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Reclamation p = new Reclamation(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4), rs.getDate(6));
              
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
         
     }
     
     
     
     public boolean supprimerReclamation(int id ){
        boolean etat = false;
        try {
            String requete = "DELETE FROM reclamation WHERE id=?";
            PreparedStatement pst = new MyDataBase().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1,id);
            pst.executeUpdate();
            System.out.println("Personne supprimée");
            etat=true;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            etat = false;
        }
        return etat;
     }
     
     
     public void modifierReclamation(Reclamation r,int id) {
        try {
            String requete = "update reclamation set sujet=?,description=?"+"where id = ? ";
            PreparedStatement pst = new MyDataBase().getCnx()
                    .prepareStatement(requete);
             pst.setString(1, r.getSujet());
            pst.setString(2, r.getDescription());
            
            pst.setInt(3, id);
            
            pst.executeUpdate();
            System.out.println("reclamation modifier!");

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
     
     
     
//      @Override
//    public ObservableList<Reclamation> readAll() throws SQLException {
//        ObservableList<Reclamation> listReclamation = FXCollections.observableArrayList();
//        ResultSet rs = new MyDataBase().getCnx().executeQuery("SELECT * from `user`");
//        while (rs.next()) {
//            int id = rs.getInt(1);
//            String sujet = rs.getString(2);
//            String desription = rs.getString(3);
//            int user_id = rs.getInt(4);
//            String status = rs.getString(5);
//            Date created_at = rs.getDate(6);
//            
//            Reclamation r = new Reclamation (id, sujet, desription, user_id, status, created_at);
//           
//            listReclamation.add(r);
//        }
//        return listReclamation;
//    }
    
}
