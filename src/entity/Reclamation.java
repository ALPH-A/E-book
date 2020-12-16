/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import dbConnection.MyDataBase;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Spingler-PC
 */
public class Reclamation {
    private int id;
    private String sujet,description;
    private  int user_id ;
    private String status;
    private Date Created_at;
    
    Connection cnx;

    

    public Reclamation() {
        cnx= MyDataBase.getInstance().getCnx();
    }

    public Reclamation(int id, String sujet, int user_id, Date created_at) {
        this.id = id;
        this.sujet = sujet;
        
        this.user_id = user_id;
        
        this.Created_at = Created_at;
        
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", sujet=" + sujet + ", description=" + description + ", user_id=" + user_id + ", status=" + status + ", Created_at=" + Created_at ;
    }
    

    public Reclamation(int id, String sujet, String description, int user_id, String status, Date Created_at) {
        this.id = id;
        this.sujet = sujet;
        this.description = description;
        this.user_id = user_id;
        this.status = status;
        this.Created_at = Created_at;
    }
    public Reclamation(int id, String sujet, String description,String status, Date Created_at) {
        this.id = id;
        this.sujet = sujet;
        this.description = description;
        this.status = status;
        this.Created_at = Created_at;
    }
   
    
    
     public Reclamation(int id, String sujet,int user_id,String status, Date Created_at) {
        this.id = id;
        this.sujet = sujet;
        this.user_id=user_id;
        this.status = status;
        this.Created_at = Created_at;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreated_at() {
        return Created_at;
    }

    public void setCreated_at(Date Created_at) {
        this.Created_at = Created_at;
    }

    public Connection getCnx() {
        return cnx;
    }

    public void setCnx(Connection cnx) {
        this.cnx = cnx;
    }
    
    
    
    
    

    
    
     
}
    


