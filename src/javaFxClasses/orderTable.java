/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFxClasses;

import controller.commandeController;
import e.book.EBook;
import entity.Commande;
import entity.User;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaFxController.panelAdminController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.TableView;

/**
 *
 * @author nizar
 */
public class orderTable {
    
    

    private int commandeID;

    private double prixTotal;

    private int clientID;

    private int livreID;

    private Date dateCommande;

    private String fullName;

    private String bookTitle;

    private String state;

    private Button action;
    
    

    public orderTable() {
    }

    
    
    commandeController c = new commandeController();
    panelAdminController pac = new panelAdminController();
    EBook ebook = new EBook();
    
    

    public int getCommandeID() {
        return commandeID;
    }

    public void setCommandeID(int commandeID) {
        this.commandeID = commandeID;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getLivreID() {
        return livreID;
    }

    public void setLivreID(int livreID) {
        this.livreID = livreID;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public orderTable(int commandeID, double prixTotal, int clientID, int livreID, Date dateCommande, String fullName, String bookTitle, String state) {
        FileInputStream input = null;
        try {
            this.commandeID = commandeID;
            this.prixTotal = prixTotal;
            this.clientID = clientID;
            this.livreID = livreID;
            this.dateCommande = dateCommande;
            this.fullName = fullName;
            this.bookTitle = bookTitle;
            this.state = state;
            input = new FileInputStream("C:/Users/nizar/Desktop/E-book/src/images/delete.png");
            Image image = new Image(input);
            ImageView imageView = new ImageView(image);
            this.action = new Button("", imageView);
            imageView.setFitHeight(20);
            imageView.setFitWidth(20);
            this.action.setStyle("-fx-background-color: transparent; ");
            this.action.setMaxSize(100, 200);
            //add action to button on click to delete commande from data base
            this.action.setOnAction(e -> {
                c.supprimerCommande(commandeID);
                pac.order.getItems().clear();
                pac.setOrderStats();
                pac.fillOrderTable();
                
                
                
            });
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(orderTable.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                input.close();
            } catch (IOException ex) {
                Logger.getLogger(orderTable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public Button getAction() {
        return action;
    }

    public void setAction(Button action) {
        this.action = action;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
