/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbConnection;

import static com.oracle.nio.BufferSecrets.instance;
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
    public static MyDataBase instance;
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
    public static MyDataBase getInstance(){
        if(instance==null) {
            instance = new MyDataBase();
        }
        return instance;
    }
    
}
