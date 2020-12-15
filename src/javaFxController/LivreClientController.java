/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFxController;

import javaFxController.DetailsLivreController;
import controller.livreController;
import entity.Livre;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class LivreClientController implements Initializable {

    private Stage primaryStage;
    private AnchorPane rootLayout;
    @FXML
    private TableView<Livre> tvBooks;
    @FXML
    private TableColumn<Livre, Integer> colId;
    @FXML
    private TableColumn<Livre, String> colTitre;
    @FXML
    private TableColumn<Livre, String> colDescription;
    @FXML
    private TableColumn<Livre, String> colAuteur;
    @FXML
    private TableColumn<Livre, String> colGenre;
    @FXML
    private TableColumn<Livre, Double> colPrix;
     ObservableList<Livre> observableList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                showBooks();

    }    

    
  @FXML
    private void handlemouseclicked(MouseEvent event) throws IOException
    {
       Livre l;
        l = tvBooks.getSelectionModel().getSelectedItem();
        String titre=l.getTitre();
        String description =l.getDescription();
        String auteur =l.getAuteur();
        String genre=l.getGenre();
        double prix= l.getPrix();
        String t = l.getImage();
        System.out.println("redirection");
         FXMLLoader loader=new FXMLLoader(this.getClass().getClassLoader().getResource("javaFxInterface/DetailsLivre.fxml"));
            Parent root2=loader.load();
            DetailsLivreController pc= loader.getController();
            pc.setRes_titre(titre);
            pc.setRes_genre(genre);
            pc.setRes_description(description);
            pc.setRes_auteur(auteur);
            pc.setRes_prix(prix+"");  
            pc.setImage(t);
//  URL fxmlUrl = this.getClass().getClassLoader().getResource("javaFxInterface/DetailsLivre.fxml");
          //  Parent root2 = FXMLLoader.load(fxmlUrl);     
       
            tvBooks.getScene().setRoot(root2);    
        
            
       
       
       
            
       
            
          
            
            
          
            
         
    }

    @FXML
    private void showBooks() {
          livreController l = new livreController();
        List<Livre> myList = new ArrayList<Livre>();
        myList = l.listerLivres();
        observableList = FXCollections.observableList(myList);
        colId.setCellValueFactory(new PropertyValueFactory<Livre, Integer>("id"));
        colTitre.setCellValueFactory(new PropertyValueFactory<Livre, String>("titre"));
        colDescription.setCellValueFactory(new PropertyValueFactory<Livre, String>("description"));
        colAuteur.setCellValueFactory(new PropertyValueFactory<Livre, String>("auteur"));
        colGenre.setCellValueFactory(new PropertyValueFactory<Livre, String>("genre"));
        colPrix.setCellValueFactory(new PropertyValueFactory<Livre, Double>("prix"));
        tvBooks.setItems(observableList);
        
    }
    
}
