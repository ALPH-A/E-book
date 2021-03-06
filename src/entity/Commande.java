/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


import java.sql.Date;

/**
 *
 * @author nizar
 */
public class Commande {
    private int id;
    private double prixTotal;
    private Date dateCommande;
    private int clientId;
    private int livreId;
    private String state;

    public Commande(double prixTotal, Date dateCommande, int clientId, int livreId) {
        this.prixTotal = prixTotal;
        this.dateCommande = dateCommande;
        this.clientId = clientId;
        this.livreId = livreId;
    }

    public Commande(int id, double prixTotal, Date dateCommande, int clientId, int livreId, String state) {
        this.id = id;
        this.prixTotal = prixTotal;
        this.dateCommande = dateCommande;
        this.clientId = clientId;
        this.livreId = livreId;
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    

    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getLivreId() {
        return livreId;
    }

    public void setLivreId(int livreId) {
        this.livreId = livreId;
    }
    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", prixTotal=" + prixTotal + ", dateCommande=" + dateCommande + ", clientId=" + clientId + ", livreId=" + livreId + '}';
    }
    
    
}
