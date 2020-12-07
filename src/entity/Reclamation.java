/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import dbConnection.MyDataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Spingler-PC
 */
public class Reclamation {
    private int id;
    private String sujet,description;
    private int user_id ;
    Connection cnx;

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", sujet=" + sujet + ", description=" + description + ", user_id=" + user_id + '}';
    }

    public Reclamation(int id, String sujet, String description, int user_id) {
        this.id = id;
        this.sujet = sujet;
        this.description = description;
        this.user_id = user_id;
        cnx= MyDataBase.getInstance().getCnx();
    }

    public Reclamation() {
        cnx= MyDataBase.getInstance().getCnx();
    }
    
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
     public void ajouterReclamation(){
        try {
            String requete = "INSERT INTO reclamation(sujet,description) VALUES"
                    + " ('Ben Mabrouk','Fedi')";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("reclamation ajoutée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
     
     
     public void ajouterReclamation(Reclamation p){
        try {
//            String requete = "INSERT INTO personne(nom,prenom) "
//                    + "VALUES ('"+p.getNom()+"','"+p.getPrenom()+"')";
            
            String requete = "INSERT INTO reclamation(sujet,description) "
                    + "VALUES (?,?)";
            
            PreparedStatement pst =cnx.prepareStatement(requete);
            pst.setString(1, p.getSujet());
            pst.setString(2, p.getDescription());
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
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Reclamation p = new Reclamation();
                p.setId(rs.getInt(1));
                p.setSujet(rs.getString("nom"));
                p.setDescription(rs.getString(3));
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
        }
     
     public boolean supprimerReclamation(Reclamation p){
        boolean etat = false;
        try {
            String requete = "DELETE FROM reclamation WHERE id=?";
            PreparedStatement pst = cnx
                    .prepareStatement(requete);
            pst.setInt(1,p.getId());
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
            PreparedStatement pst = cnx
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
}
    


