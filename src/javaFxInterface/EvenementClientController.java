/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFxInterface;

import dbConnection.MyDataBase;
import entity.Evenement;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class EvenementClientController implements Initializable {

    @FXML
    private Button btn_retour;
    @FXML
    private Button btnSettings;
    @FXML
    private TableView<Evenement> table;
    @FXML
    private TableColumn<Evenement, String> col_titre;
    @FXML
    private TableColumn<Evenement, String> col_lieu;
    @FXML
    private TableColumn<Evenement, String> col_description;
    @FXML
    private TableColumn<Evenement, String> col_date;
    ObservableList<Evenement> oblist = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Evenement, String> col_image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showEvents();
    }    
    
    private void showEvents() {
        try {
            Connection cnx;
            cnx = MyDataBase.getInstance().getCnx();
            String requete = "SELECT * FROM evenement2";
            Statement st = cnx
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                oblist.add(new Evenement(rs.getInt("id"),
                        rs.getString("titre"), rs.getString("lieu"), rs.getString("description"), rs.getString("date"),rs.getString("image")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        col_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        col_lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_image.setCellValueFactory(new PropertyValueFactory<>("image"));
        table.setItems(oblist);
    }

    @FXML
    private void retourPage(ActionEvent event) {
    }

    @FXML
    private void handlemouseclicked(MouseEvent event) throws IOException {
        Evenement e = table.getSelectionModel().getSelectedItem();
        String description=e.getDescription();
        String lieu=e.getLieu();
        String titre=e.getTitre();
        String date=e.getDate();
        String imagepath=e.getImage();
        System.out.println("redirection");
            FXMLLoader loader=new FXMLLoader(getClass().getResource("DetailsEvenement.fxml"));
            Parent root2=loader.load();
            DetailsEvenementController pc2= loader.getController();
            
            pc2.setRes_titre(titre);
            
            pc2.setDesc(description,lieu,date);
            pc2.setPath(imagepath);
            pc2.setImagevie(imagepath);
            
            table.getScene().setRoot(root2);
    }
    
}
