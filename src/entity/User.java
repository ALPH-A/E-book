/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author saifeddinebensassi
 */
public class User {

    private int id;
    private String nom, prenom, email, password, image, adresse, role;
    private int num_telephone;
    private int verification_account;

    public User(int id, String nom, String prenom, String email, String password, int num_telephone, int verification_account, String image, String adresse, String role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.num_telephone = num_telephone;
        this.verification_account = verification_account;
        this.image = image;
        this.adresse = adresse;
        this.password=password;
    }

    public User(int id, String nom, String prenom, String email, String password, int num_telephone, String adresse) {
        this.id=id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password=password;
        this.num_telephone = num_telephone;
        this.adresse = adresse;
    }

    public User(String text, String text0) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getNum_telephone() {
        return num_telephone;
    }

    public void setNum_telephone(int num_telephone) {
        this.num_telephone = num_telephone;
    }

    public int getVerification_account() {
        return verification_account;
    }

    public void setVerification_account(int verification_account) {
        this.verification_account = verification_account;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", adresse=" + adresse + ", role=" + role + ", num_telephone=" + num_telephone + '}';
    }

}
