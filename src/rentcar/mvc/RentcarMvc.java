/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package rentcar.mvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import models.Personnes.Gerant;
import models.vehicules.ContratLocation;

/**
 *
 * @author LENOVO
 */
public class RentcarMvc extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        try {
            Gerant gerant = Gerant.getInstance();
            gerant.setNom("Sabrine");
            gerant.setPrenom("Kamkoum");
            gerant.setVehicules(new ArrayList<>());
            gerant.setClients(new ArrayList<>());
            gerant.setEntretiens(new ArrayList<>());
            gerant.setChauffeurs(new ArrayList<>());
            gerant.setLocations(new ArrayList<ContratLocation>());
            gerant.setAssurances(new ArrayList<>());

            Parent root = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
          

            Scene scene = new Scene(root, 818, 614);
              scene.getStylesheets().add(getClass().getResource("/views/style.css").toExternalForm());
            primaryStage.setTitle("RentCar");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(RentcarMvc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
