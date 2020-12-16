/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFxController;

import controller.ReclamationController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML SendReclamationController class
 *
 * @author Spingler-PC
 */
public class SendReclamationController implements Initializable {

    
    @FXML
    private Button btnEnvoyer;
    @FXML
    private Button btnAnnuler;
    @FXML
    private TextField sujet;
    @FXML
    private TextArea description;
    @FXML
    private Pane pnlNewCustomer;
    @FXML
    private Label pnlNewCustomerTitle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
       
    }    

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
         String sujet1=sujet.getText();
         String description1=description.getText();
        System.out.println(sujet1);
        
        ReclamationController rc=new ReclamationController();
        rc.ajouterReclamation(sujet1, description1,1);
        
        
        
            
        
    }

    @FXML
    private void handleAClicks(ActionEvent event) {
        System.out.println("testtttttttt");
        
        try {
            System.out.println("redirection");
            FXMLLoader loader=new FXMLLoader(getClass().getResource("../javaFxInterface/clientReclamation.fxml"));
            Parent root2=loader.load();
            
            description.getScene().setRoot(root2);;
            
            
            
        } catch (IOException ex) {
            System.out.println("error");;
        }
    }
    
    
}
