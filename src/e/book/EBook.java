/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.book;

import controller.commandeController;
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

import entity.Commande;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import services.commandServices;
import services.livreServices;
import services.userServices;

/**
 *
 * @author ahmed
 */
public class EBook extends Application {

    private static Stage stage;

    public void start(Stage primaryStage) {
        try {

            stage = primaryStage;
            URL fxmlUrl = this.getClass().getClassLoader().getResource("javaFxInterface/panelAdminInterface.fxml");

            Parent root = FXMLLoader.load(fxmlUrl);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ex.getMessage());
        }
    }

    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        commandServices c = new commandServices();
        System.out.println(c.countCommandesOnHold());
//        livreServices l = new livreServices();
//        System.out.println(l.getLivreById(1));

        // long millis=System.currentTimeMillis();  
        //java.sql.Date currentDate=new java.sql.Date(millis);
        //System.out.println(currentDate);
//        commandeController c = new commandeController();
//        for(Commande p1 : c.listerCommandes()){
//            System.out.println(p1.toString());
//        }
//        MyDataBase md =new MyDataBase();
//        livreController lc = new livreController();
//        Livre l = new Livre (1,"LA VIE EN ROSE","il est magnifique","jean ",15.5,"romantique");
//
//        long millis=System.currentTimeMillis();  
//        java.sql.Date currentDate=new java.sql.Date(millis);
//        System.out.println(currentDate);
//        commandeController c = new commandeController();
//        for(Commande p1 : c.listerCommandes()){
//            System.out.println(p1.toString());
//        }
        //lc.ajouterLivre(l);
        //Livre l2 = new Livre (1,"LA VIE "," magnifique","jean ",20,"romantique");
        //  lc.modifierLivre(l2, 1);
        // for (Livre p1 : lc.listerLivres()) {
        //  System.out.println(p1);}
        //    lc.supprimerLivre(l);
    }

}
