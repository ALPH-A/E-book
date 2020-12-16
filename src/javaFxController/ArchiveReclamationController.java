/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFxController;

import controller.ReclamationController;
import entity.Reclamation;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Spingler-PC
 */
public class ArchiveReclamationController implements Initializable {

    
    
    
    
    
    
    @FXML
    private TableView<Reclamation> table_id;
    
    @FXML
    private TableColumn<Reclamation, Integer> reclmationIdCol;
    @FXML
    private TableColumn<Reclamation, String> sujet_Col;
    
  
    @FXML
    private TableColumn<Reclamation, Date> dateStartCol;
    @FXML
    private TableColumn<Reclamation, Date> dateFinishCol;
    
    
    public static ObservableList<Reclamation> list = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Reclamation, Integer> userid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ReclamationController r = new ReclamationController();
        
        
        for(Reclamation r1 : r.listerReclamationsDone()){
            System.out.println(r1);
            System.out.println(r1.getStatus());
            list.add(new Reclamation(r1.getId(), r1.getSujet(), r1.getUser_id(), r1.getCreated_at()));

           
        }
        
         
        reclmationIdCol.setCellValueFactory(new PropertyValueFactory("id"));
       sujet_Col.setCellValueFactory(new PropertyValueFactory("sujet"));
       userid.setCellValueFactory(new PropertyValueFactory("user_id"));
        //dateStartCol.setCellValueFactory(new PropertyValueFactory("Created_at"));
       
        table_id.setItems(list);
        
    }  
    }    
    

