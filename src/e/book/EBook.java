/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.book;


import controller.livreController;
import dbConnection.MyDataBase;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import entity.Livre;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 *
 * @author ahmed
 */
public class EBook extends Application {
    
   
   public void start(Stage primaryStage) {
        try {
            URL fxmlUrl = this.getClass().getClassLoader().getResource("javaFxInterface/gestionLivre.fxml");
            Parent root = FXMLLoader.load(fxmlUrl);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(ex.getMessage());
        }
    }
     public static void main(String[] args) {
        launch(args);
        
    }
    /**
     * @param args the command line arguments
     */
    //public static void main(String[] args) {
     //  launch(args);
       // long millis=System.currentTimeMillis();  
        //java.sql.Date currentDate=new java.sql.Date(millis);
        //System.out.println(currentDate);
//        commandeController c = new commandeController();
//        for(Commande p1 : c.listerCommandes()){
//            System.out.println(p1.toString());
//        }
       // MyDataBase md =new MyDataBase();
       // livreController lc = new livreController();
       // Livre l = new Livre (1,"LA VIE EN ROSE","il est magnifique","jean ",15.5,"romantique");
        
        //lc.ajouterLivre(l);
                //Livre l2 = new Livre (1,"LA VIE "," magnifique","jean ",20,"romantique");
              //  lc.modifierLivre(l2, 1);
       // for (Livre p1 : lc.listerLivres()) {
         //  System.out.println(p1);}
      //    lc.supprimerLivre(l);
          
   // }
    
}
