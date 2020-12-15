/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFxController;

import entity.User;
import java.net.URL;
import java.util.ResourceBundle;
import javaFxInterface.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import services.userServices;

/**
 * FXML Controller class
 *
 * @author saifeddinebensassi
 */
public class InscriptionController implements Initializable {

    @FXML
    private Button signinid;
    @FXML
    private Button signupid;
    @FXML
    private Button Inscriptionid;
    @FXML
    private Label labelmsg;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField numeroTele;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField confirmpassword;

    /**
     * Initializes the controller class.
     */
    
    public void initialize(URL url, ResourceBundle rb) {
       
        
    }
    

    @FXML
    private void interfacelogin(ActionEvent event) {
        try {
                    FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../javaFxInterface/LoginInterface.fxml"));
                    Parent root = (Parent) fxmlloader.load();
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.DECORATED);
                    stage.setTitle("Inscription");
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (Exception e) {
                    System.out.println("can't load new window");
                }
    }

    @FXML
    private void oninscription(ActionEvent event) {
    }

    @FXML
    private void Inscription(ActionEvent event) {
        String check = "";
        User u = new User();
        try {
            if (password.getText().equals(confirmpassword.getText())) {
                u.setNom(nom.getText());
                u.setPrenom(prenom.getText());
                u.setNum_telephone(Integer.parseInt(numeroTele.getText()));
                u.setEmail(email.getText());
                u.setPassword(password.getText());
                u.setRole("client");
                u.setVerification_account(1);
                userServices ser = new userServices();
                if (email.getText().contains("@")) {
                    ser.ajouter(u);
                    JOptionPane.showMessageDialog(null, "User Add succes");
                    check = "+";

                } else {
                    
                   
                    
                    email.setText("");
                    JOptionPane.showMessageDialog(null, "can't Add user verify your email please");
                    System.out.println("1111");
                    
                    System.out.println("222222");
                    
                    check = "-";

                }
//                if (check.equals("+")) {
//                    try {
//                        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../javaFxInterface/LoginInterface.fxml"));
//                        Parent root = (Parent) fxmlloader.load();
//                        Stage stage1 = new Stage();
//                        stage1.initStyle(StageStyle.DECORATED);
//                        stage1.setTitle("Inscription");
//                        stage1.setScene(new Scene(root));
//                        Stage stage = (Stage) signinid.getScene().getWindow();
//                        stage1.close();
//                        stage.show();
//                    } catch (Exception e) {
//                        System.out.println("can't load new window");
//                    }
//                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

}
