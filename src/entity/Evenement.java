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
public class Evenement {

    private int id;
    private String description;
    private String lieu;
    private String titre;
    private String date;
    private String image;

    public Evenement() {
    }

    public Evenement(String titre, String lieu, String description) {
        this.description = description;
        this.lieu = lieu;
        this.titre = titre;
    }

    public Evenement(String titre, String lieu, String description, String date) {
        this.description = description;
        this.lieu = lieu;
        this.titre = titre;
        this.date = date;
    }
    public Evenement(String titre, String lieu, String description, String date,String image) {
        this.description = description;
        this.lieu = lieu;
        this.titre = titre;
        this.date = date;
        this.image = image;
    }
    
    public Evenement(int id,String titre, String lieu, String description, String date,String image) {
        this.id = id;
        this.description = description;
        this.lieu = lieu;
        this.titre = titre;
        this.date = date;
        this.image = image;
    }

    

    public Evenement(int id, String titre, String lieu, String description) {
        this.id = id;
        this.description = description;
        this.lieu = lieu;
        this.titre = titre;
    }

    public Evenement(int id, String titre, String lieu, String description, String date) {
        this.id = id;
        this.description = description;
        this.lieu = lieu;
        this.titre = titre;
        this.date = date;
    }

    

    public void setId(int id) {
        this.id = id;
    }

    

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setImage(String image) {
        this.image = image;
    }

    

    public int getId() {
        return id;
    }

    

    public String getDescription() {
        return description;
    }

    public String getLieu() {
        return lieu;
    }

    public String getTitre() {
        return titre;
    }

    public String getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }
    
    

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", description=" + description + ", lieu=" + lieu + ", titre=" + titre + ", date=" + date + '}';
    }

    

    
}
