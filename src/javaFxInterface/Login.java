/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFxInterface;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author saifeddinebensassi
 */
public class Login extends Application{

    @Override
    public void start(Stage stage)  {
      try {
            URL fxmlUrl = this.getClass().getClassLoader().getResource("javaFxInterface/LoginInterface.fxml");
            Parent root = FXMLLoader.load(fxmlUrl);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ex.getMessage());
        }
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
