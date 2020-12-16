/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFxController;

import controller.ReclamationController;
import entity.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Spingler-PC
 */
public class AffichageReclamationController implements Initializable {
    @FXML
    private TableView<Reclamation> table_id;

    @FXML
    private AnchorPane rootPanel;
    @FXML
    private TableColumn<Reclamation, Integer> reclmationCol;
    @FXML
    private TableColumn<Reclamation, String> sujet_Col;
    @FXML
    private TableColumn<Reclamation, Integer> user_idCol;
    @FXML
    private TableColumn<Reclamation, Date> dateCol;
    @FXML
    private TableColumn<Reclamation, String> statusCol;

    /**
     * Initializes the controller class.
     */ 
    ObservableList<Reclamation> oblist = FXCollections.observableArrayList();
    
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ReclamationController r = new ReclamationController();
        
        
        for(Reclamation r1 : r.listerReclamations()){
            
            oblist.add(new Reclamation(r1.getId(), r1.getSujet(),r1.getDescription(), r1.getUser_id(), r1.getStatus(), r1.getCreated_at()));
           
        }
        reclmationCol.setCellValueFactory(new PropertyValueFactory("id"));
        sujet_Col.setCellValueFactory(new PropertyValueFactory("sujet"));
        user_idCol.setCellValueFactory(new PropertyValueFactory("user_id"));
        dateCol.setCellValueFactory(new PropertyValueFactory("created_at"));
        statusCol.setCellValueFactory(new PropertyValueFactory("status"));
        table_id.setItems(oblist);
    }    

    @FXML
    private void handlemouseclicked(MouseEvent event) throws IOException {
        Reclamation r = table_id.getSelectionModel().getSelectedItem();
        String description=r.getDescription();
        String sujet=r.getSujet();
        Date Date=r.getCreated_at();
        String status=r.getStatus();
        int id =r.getId();
        
        System.out.println("redirection");
            FXMLLoader loader=new FXMLLoader(getClass().getResource("../javaFxInterface/detailsReclamation.fxml"));
            Parent root2=loader.load();
             
            detailsReclamationController pc2= loader.getController();
            
            pc2.setSujet(sujet);
            pc2.setDescription(description);
            pc2.setIdentifiant(id);
//            pc2.setRes_description(description);
//            pc2.setRes_date(date);
            
            table_id.getScene().setRoot(root2);
    }

    
}
