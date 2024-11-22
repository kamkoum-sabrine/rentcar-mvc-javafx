/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**added*/
public class AcceuilController {

    @FXML
    private StackPane contentArea;
    
     @FXML
    private TextField usernameField;
    
     private MediaPlayer mediaPlayer;
    private MediaView mediaView;
    
       private MediaView backgroundImage;

 
    // Méthodes pour chaque gestion
    @FXML
    private void showGestionVehicules() {
        loadView("/views/GestionVehicules.fxml");
    }

    @FXML
    private void showGestionClients() {
        loadView("/views/GestionClients.fxml");
    }

    @FXML
    private void showGestionLocations() {
        loadView("/views/GestionLocations.fxml");
    }

    @FXML
    private void showGestionFactures() {
        loadView("/views/GestionFactures.fxml");
    }

    @FXML
    private void showGestionAssurances() {
        loadView("/views/GestionAssurances.fxml");
    }

    @FXML
    private void showGestionGarage() {
        loadView("/views/GestionGarage.fxml");
    }

    @FXML
    private void showGestionEntretien() {
        loadView("/views/GestionEntretien.fxml");
    }
    
   
     public void logout(ActionEvent event) {
        // Affichage d'une boîte de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Déconnexion");
        alert.setHeaderText("Êtes-vous sûr de vouloir vous déconnecter ?");
        alert.setContentText("Toutes les données non sauvegardées seront perdues.");

        // Gestion de la réponse de l'utilisateur
        if (alert.showAndWait().get() == ButtonType.OK) {
            // Fermer la fenêtre actuelle
            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            // Redirection vers la fenêtre de connexion
            showLoginScreen();
        }
    }

    // Méthode pour afficher la fenêtre de connexion
    private void showLoginScreen() {
        try {
               
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/views/Login.fxml"));
            javafx.scene.Parent root = loader.load();

            // Créer une nouvelle fenêtre pour la connexion
            Stage loginStage = new Stage();
            loginStage.setTitle("Connexion");
             Scene scene = new Scene(root,818, 614);
            loginStage.setScene(scene);
            loginStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors du chargement de la fenêtre de connexion.");
        }
    }

    // Méthode pour charger une vue FXML dans la zone centrale
     private void loadView(String fxmlPath) {
        try {
            // Charger la vue spécifiée
            System.out.println("path "+fxmlPath);
            Parent view = FXMLLoader.load(getClass().getResource(fxmlPath));
            contentArea.getChildren().clear(); // Effacer le contenu actuel
            contentArea.getChildren().add(view); // Ajouter le nouveau contenu
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Afficher une alerte d'erreur
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    private void handleButtonHover(Event event) {
        Button button = (Button) event.getSource();
        button.setStyle("-fx-background-color: #2980b9; -fx-text-fill: white;");
    }

    // Cette méthode est appelée lorsque le curseur quitte le bouton
    @FXML
    private void handleButtonExit(Event event) {
        Button button = (Button) event.getSource();
        button.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");
    }

}
