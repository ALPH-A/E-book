/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFxInterface;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class DetailsEvenementController implements Initializable {

    @FXML
    private TextField tf_titre;
    
    @FXML
    private Button btn_return;
    @FXML
    private TextField tf_area;
    @FXML
    private ImageView iv_image;
    @FXML
    private TextField tf_path;
    
    private Image image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }   
    public void setRes_titre(String message) {
        this.tf_titre.setText(message);
    }

    
    public void setDesc(String message1,String message2,String message3){
        this.tf_area.setText(message1+" qui sera organisé au "+message2+" le "+message3);
    }
    
    public void setPath(String message) {
        this.tf_path.setText(message);
    }
    
    public void setImagevie(String message) {
        String t=message;
        image = new Image(t);
        this.iv_image.setImage(image);
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        System.out.println("redirection");
            FXMLLoader loader=new FXMLLoader(getClass().getResource("EvenementClient.fxml"));
            Parent root2=loader.load();
            
            
            tf_area.getScene().setRoot(root2);
    }
    
}
