/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFxController;

import entity.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javaFxInterface.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jdk.nashorn.internal.ir.BreakNode;
import services.userServices;

/**
 * FXML Controller class
 *
 * @author saifeddinebensassi
 */
public class LoginController implements Initializable {

    @FXML
    private Button signinid;
    @FXML
    private Button signupid;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginid;
    @FXML
    private Label labelmsg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    }

    @FXML
    private void interfacelogin(ActionEvent event) {
    }

    @FXML
    private void oninscription(ActionEvent event) {

        try {

            URL fxmlUrl = this.getClass().getClassLoader().getResource("javaFxInterface/InscriptionInterface.fxml");
            Parent root = FXMLLoader.load(fxmlUrl);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ex.getMessage());
        }

    }

    @FXML
    private void login(ActionEvent event) {
        userServices ser = new userServices();
        User u = new User(password.getText(), username.getText());
        String n = username.getText();
        System.out.println(username.getText());
        try {
            boolean connexion = ser.connexion(u);
            System.out.println(connexion);
            if (username.getText().equals("saifbensassi@admin") && password.getText().equals("admin")) {
                
                try {
                    URL fxmlUrl = this.getClass().getClassLoader().getResource("javaFxInterface/panelAdminInterface.fxml");
                    Parent root = FXMLLoader.load(fxmlUrl);
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();

                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(ex.getMessage());
                }
            }
            if (connexion) {

                try {
                    URL fxmlUrl = this.getClass().getClassLoader().getResource("javaFxInterface/panelAdminInterface.fxml");
                    Parent root = FXMLLoader.load(fxmlUrl);
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();

                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(ex.getMessage());
                }

            } else {
                labelmsg.setVisible(true);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

}
