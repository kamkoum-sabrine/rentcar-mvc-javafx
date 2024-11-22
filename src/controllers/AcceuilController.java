/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;

/**
 *
 * @author LENOVO
 */
public class AcceuilController {

    @FXML
    private StackPane contentArea;

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
}
