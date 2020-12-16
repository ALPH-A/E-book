/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFxController;

import entity.User;
import java.io.File;
import java.net.MalformedURLException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
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
    @FXML
    private Button browseid;
    @FXML
    private ImageView photoid;
    private Image image;
    private FileChooser fileChooser;
    private File file;

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
    private void Inscription(ActionEvent event) throws MalformedURLException {
        User u = new User();
        String imagepath=file.toURI().toURL().toString();
        try {
            if (password.getText().equals(confirmpassword.getText())) {
                u.setNom(nom.getText());
                u.setPrenom(prenom.getText());
                u.setNum_telephone(Integer.parseInt(numeroTele.getText()));
                u.setEmail(email.getText());
                u.setPassword(password.getText());
                u.setRole("client");
                u.setVerification_account("1");
                u.setImage(imagepath);
                userServices ser = new userServices();
                if (email.getText().contains("@")) {
                    ser.ajouter(u);
                    JOptionPane.showMessageDialog(null, "User Add succes");
                } else {

                    email.setText("");
                    JOptionPane.showMessageDialog(null, "can't Add user verify your email please");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    @FXML
    private void importer(ActionEvent event) {
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );

        file = fileChooser.showOpenDialog(null);
        if (file != null) {
            image = new Image(file.toURI().toString());

            photoid.setImage(image);

        }
    }

}
