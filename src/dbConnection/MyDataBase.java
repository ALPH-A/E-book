/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nizar
 */
public class MyDataBase {
    static String url="jdbc:mysql://localhost:3306/ebook";
    static String login="root";
    static String pwd="";
    Connection cnx;
    public  MyDataBase()  {
        try {
            cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("connexion etablie!");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
        
        
    }

    public Connection getCnx() {
        return cnx;
    }
    
}
