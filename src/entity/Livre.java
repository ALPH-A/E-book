/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author amine
 */
public class Livre {
   
    private int id;
    private String titre;
    private String description; 
    private String auteur ;
    private double prix;
    private String genre;
    private String image;
   private double tauxPromo;
      private double prixSolde;


    public Livre(int id, String titre, String description, String auteur, double prix, String genre) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.auteur = auteur;
        this.prix = prix;
        this.genre = genre;
       
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrixSolde() {
        return prixSolde;
    }

    public void setPrixSolde(double prixSolde) {
        this.prixSolde = prixSolde;
    }
     public Livre(int id, double prix,double tauxPromo) {
        this.id = id;
        this.tauxPromo=tauxPromo;
        this.prix = prix;
    }

   
        
 public Livre( String titre, String description, String auteur, double prix, String genre,double tauxPromo,double prixSolde) {
       
        this.titre = titre;
        this.description = description;
        this.auteur = auteur;
        this.prix = prix;
        this.genre = genre;
        this.tauxPromo=tauxPromo;
        this.prixSolde =prixSolde;
    }
 public Livre( String titre, String description, String auteur, double prix, String genre,double tauxPromo,double prixSolde,String image) {
       
        this.titre = titre;
        this.description = description;
        this.auteur = auteur;
        this.prix = prix;
        this.genre = genre;
        this.tauxPromo=tauxPromo;
        this.prixSolde =prixSolde;
        this.image=image;
    }
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    
    public Livre (){}

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public String getAuteur() {
        return auteur;
    }

    public double getPrix() {
        return prix;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
 public double getTauxPromo() {
        return tauxPromo;
    }

    public void setTauxPromo(double tauxPromo) {
        this.tauxPromo = tauxPromo;
    }

    @Override
    public String toString() {
        return "Livre{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", auteur=" + auteur + ", prix=" + prix + ", genre=" + genre + ", image=" + image + ", tauxPromo=" + tauxPromo + ", prixSolde=" + prixSolde + '}';
    }

    
}
