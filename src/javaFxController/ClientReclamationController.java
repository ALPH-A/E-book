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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Spingler-PC
 */
public class ClientReclamationController implements Initializable {

    @FXML
    private TableColumn<Reclamation, String> sujetCol;
    @FXML
    private TableColumn<Reclamation, Date> dateCreationCol;
    @FXML
    private TableColumn<Reclamation, String> statusCol;
    
    
    ObservableList<Reclamation> clientList = FXCollections.observableArrayList();
    @FXML
    private TableView<Reclamation> tableId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         ReclamationController r = new ReclamationController();
        
        
        for(Reclamation r1 : r.listerReclamations()){
            clientList.add(new Reclamation(r1.getId(), r1.getSujet(), r1.getDescription(), r1.getStatus(), r1.getCreated_at()));
           
        }
        sujetCol.setCellValueFactory(new PropertyValueFactory("sujet"));
        dateCreationCol.setCellValueFactory(new PropertyValueFactory("Created_at"));
        statusCol.setCellValueFactory(new PropertyValueFactory("status"));


        
        tableId.setItems(clientList);
        
    }    

    @FXML
    private void handlemouseclicked(MouseEvent event) throws IOException {
        
          Reclamation r = tableId.getSelectionModel().getSelectedItem();
        String description=r.getDescription();
        String sujet=r.getSujet();
        Date Date=r.getCreated_at();
        String status=r.getStatus();
        int id =r.getId();
        
        System.out.println("redirection");
            FXMLLoader loader=new FXMLLoader(getClass().getResource("../javaFxInterface/clientdetailsReclamation.fxml"));
            Parent root2=loader.load();
             
            ClientdetailsReclamationController pc2= loader.getController();
            
            pc2.setSujet(sujet);
            pc2.setDescription(description);
            
//            pc2.setRes_description(description);
//            pc2.setRes_date(date);
            
            tableId.getScene().setRoot(root2);
    }
    }    
    

