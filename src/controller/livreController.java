/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dbConnection.MyDataBase;
import entity.Livre;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author amine
 */
public class livreController {

    public livreController() {
    }
   
    public void ajouterLivre(Livre l){
        try {

            String requete = "INSERT INTO livre (titre,description,auteur,prix,genre) "
                    + "VALUES (?,?,?,?,?)";
            
            PreparedStatement pst = new MyDataBase().getCnx()
                                           .prepareStatement(requete);
            pst.setString(1, l.getTitre());
            pst.setString(2, l.getDescription());
            pst.setString(3, l.getAuteur());
            pst.setDouble(4, l.getPrix());
            pst.setString(5, l.getGenre());

            pst.executeUpdate();
            System.out.println("Livre ajouté!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
    }
    
     public List<Livre> listerLivres(){
            List<Livre> myList = new ArrayList<Livre>();
         try {
            String requete= "SELECT * FROM livre";
            Statement st = new MyDataBase().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Livre l = new Livre();
                l.setId(rs.getInt(1));
                l.setTitre(rs.getString("Titre"));
                l.setDescription(rs.getString(3));
                l.setAuteur(rs.getString(4));
                l.setPrix(rs.getDouble(5));
                l.setGenre(rs.getString(6));

                myList.add(l);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
        }
   public void modifierLivre(Livre l,int id ) {
       try {
            String requete = "UPDATE livre SET titre = ? , "
                + "description = ?, "
                     + "auteur = ? ,"
                     + "prix = ?, "
                    +"genre= ? "
                + "WHERE id = ?";
            PreparedStatement pst = new MyDataBase().getCnx()
            .prepareStatement(requete);
       

 
            // set the corresponding param
            pst.setString(1, l.getTitre());
            pst.setString(2, l.getDescription());
            pst.setString(3, l.getAuteur());
            pst.setDouble(4, l.getPrix());
            pst.setString(5, l.getGenre());

            pst.setInt(6,id);
            // update 
            pst.executeUpdate();
            System.out.println("Livre modifié!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

     
     public boolean supprimerLivre(Livre l){
        boolean etat = false;
        try {
            String requete = "DELETE FROM Livre WHERE id=?";
            PreparedStatement pst = new MyDataBase().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1,l.getId());
            pst.executeUpdate();
            System.out.println("Livre supprimé");
            etat=true;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            etat = false;
        }
        return etat;
     }   
    
}
