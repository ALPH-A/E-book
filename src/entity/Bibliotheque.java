/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;



/**
 *
 * @author ahmed
 */
public class Bibliotheque {
    private int id;
    private String nom;
    private int clientId;
    private int livreId;

    public Bibliotheque(int id, String nom, int clientId, int livreId) {
        this.id = id;
        this.nom = nom;
        this.clientId = clientId;
        this.livreId = livreId;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getClientId() {
        return clientId;
    }

    public int getLivreId() {
        return livreId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setLivreId(int livreId) {
        this.livreId = livreId;
    }

    @Override
    public String toString() {
        return "Bibliotheque{" + "id=" + id + ", nom=" + nom + ", clientId=" + clientId + ", livreId=" + livreId + '}';
    }
    
    
    
}
