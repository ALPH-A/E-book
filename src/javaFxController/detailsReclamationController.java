/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFxController;

import dbConnection.MyDataBase;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Spingler-PC
 */
public class detailsReclamationController implements Initializable {
    private int  identifiant;

    public int getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }
    

    @FXML
    private TextField sujet_id;
    @FXML
    private TextField description_id;
    @FXML
    private Button btn_retour;
    @FXML
    private Button btn_valider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setSujet(String message) {
        this.sujet_id.setText(message);
    }

    public void setDescription(String message ) {
        this.description_id.setText(message);
    }
    
//    public void setRes_description(String message) {
//        this.tf_description.setText(message);
//    }
//
//    public void setRes_date(String message ) {
//        this.tf_date.setText(message);
//    }
    @FXML
    private void retour(ActionEvent event) throws IOException {
      System.out.println("redirection");
            FXMLLoader loader=new FXMLLoader(getClass().getResource("../javaFxInterface/affichageReclamation.fxml"));
            Parent root2=loader.load();
            
            description_id.getScene().setRoot(root2);
    }

    @FXML
    private void valider(ActionEvent event) throws IOException {
        try{
         System.out.println("redirection");
            FXMLLoader loader=new FXMLLoader(getClass().getResource("../javaFxInterface/affichageReclamation.fxml"));
            Parent root2=loader.load();
            
          
            String requete = "update reclamation set status='traiter' where id ="+this.getIdentifiant();
            PreparedStatement pst = new MyDataBase().getCnx()
                    .prepareStatement(requete);
            
            
            
            pst.executeUpdate();
            System.out.println("reclamation modifier!");

        
            
            description_id.getScene().setRoot(root2);
        } catch ( SQLException e){
            System.err.println(e.getLocalizedMessage());
            
            
            
        }
    }
    
    
    
    
    
}
