/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFxClasses;

import entity.Commande;
import entity.User;
import java.sql.Date;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

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

    public orderTable(int commandeID, double prixTotal, int clientID, int livreID,Date dateCommande, String fullName, String bookTitle, String state) {
        this.commandeID = commandeID;
        this.prixTotal = prixTotal;
        this.clientID = clientID;
        this.livreID = livreID;
        this.dateCommande = dateCommande;
        this.fullName = fullName;
        this.bookTitle = bookTitle;
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    

    

}
