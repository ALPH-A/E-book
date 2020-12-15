/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFxController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class DetailsLivreController implements Initializable {

    @FXML
    private TextField tfTitre;
    @FXML
    private TextField tfDescription;
    @FXML
    private TextField tfAuteur;
    @FXML
    private TextField tfGenre;
    @FXML
    private TextField tfPrix;
    @FXML
    private Button btn_return;
    @FXML
    private ImageView iv_image;

    /**
     * Initializes the controller class.
     */
    public DetailsLivreController (){}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }    
    public void setRes_titre(String message) {
        this.tfTitre.setText(message);
    }

    public void setRes_description(String message ) {
        this.tfDescription.setText(message);
    }
    public void setRes_auteur(String message) {
        this.tfAuteur.setText(message);
    }

    public void setRes_genre(String message ) {
        this.tfGenre.setText(message);
    } 
     public void setRes_prix(String message ) {
        this.tfPrix.setText(message);
    } 
     public void setImage(String message ) {
      Image  image =new Image(message);
         this.iv_image.setImage(image);
    } 
     
       @FXML
    private void retour(ActionEvent event) throws IOException {
        System.out.println("redirection");
           URL fxmlUrl = this.getClass().getClassLoader().getResource("javaFxInterface/livreClientt.fxml");
            Parent root2 = FXMLLoader.load(fxmlUrl);
            
            tfGenre.getScene().setRoot(root2);  
    } 
}
