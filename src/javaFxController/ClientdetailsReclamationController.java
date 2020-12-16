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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Spingler-PC
 */
public class ClientdetailsReclamationController implements Initializable {

    @FXML
    private TextField sujet;
    @FXML
    private TextArea description;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_update;
    @FXML
    private Button btn_retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setSujet(String message) {
        this.sujet.setText(message);
    }

    public void setDescription(String message) {
        this.description.setText(message);
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        System.out.println("redirection");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../javaFxInterface/clientReclamation.fxml"));
        Parent root2 = loader.load();

        description.getScene().setRoot(root2);
    }

    @FXML
    private void delete(ActionEvent event) throws IOException {
        try {
            System.out.println("redirection");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../javaFxInterface/clientReclamation.fxml"));
            Parent root2 = loader.load();

            String requete = "delete from  reclamation where user_id =1";
            PreparedStatement pst = new MyDataBase().getCnx()
                    .prepareStatement(requete);

            pst.executeUpdate();
            System.out.println("reclamation modifier!");

            description.getScene().setRoot(root2);
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    @FXML
    private void update(ActionEvent event) throws SQLException, IOException {

        System.out.println("reclamation modifier!");
        System.out.println("redirection");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../javaFxInterface/clientReclamation.fxml"));
        Parent root2 = loader.load();

        String requete = "update reclamation set sujet=?,description=?" + "where id = ? ";
        PreparedStatement pst = new MyDataBase().getCnx()
                .prepareStatement(requete);
        pst.setString(1, sujet.getText());
        pst.setString(2, description.getText());

        pst.setInt(3, 1);

        pst.executeUpdate();

        description.getScene().setRoot(root2);
    }

}
