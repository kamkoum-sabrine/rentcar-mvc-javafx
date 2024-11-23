/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import models.Personnes.Gerant;
import models.vehicules.Vehicule;
import models.vehicules.VoitureCommerciale;
import models.vehicules.VoitureFamiliale;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class GestionVehiculesController  {

    /**
     * Initializes the controller class.
     */
    @FXML
private TableView<Vehicule> tableVoitures;

@FXML
private TableColumn<Vehicule, String> colMatricule;

@FXML
private TableColumn<Vehicule, String> colMarque;

@FXML
private TableColumn<Vehicule, String> colModele;

@FXML
private TableColumn<Vehicule, String> colType;

@FXML
private TableColumn<Vehicule, Double> colCoutParJour;

// Colonnes spécifiques aux VoitureFamiliale
@FXML
private TableColumn<VoitureFamiliale, Integer> colNombrePlaces;

@FXML
private TableColumn<VoitureFamiliale, Boolean> colSiegeBebe;

// Liste observable des véhicules
private final ObservableList<Vehicule> vehicules = FXCollections.observableArrayList();

@FXML
public void initialize() {
    // Initialiser les colonnes génériques
    colMatricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
    colMarque.setCellValueFactory(new PropertyValueFactory<>("marque"));
    colModele.setCellValueFactory(new PropertyValueFactory<>("modele"));
    colType.setCellValueFactory(new PropertyValueFactory<>("type"));
    colCoutParJour.setCellValueFactory(new PropertyValueFactory<>("coutParJour"));

    // Configurer les colonnes spécifiques
    colNombrePlaces.setCellValueFactory(cellData -> {
        Vehicule vehicule = cellData.getValue();
        if (vehicule instanceof VoitureFamiliale vf) {
            return new SimpleIntegerProperty(vf.getNombrePlaces()).asObject();
        }
        return null; // Pas applicable pour les autres types
    });

    colSiegeBebe.setCellValueFactory(cellData -> {
        Vehicule vehicule = cellData.getValue();
        if (vehicule instanceof VoitureFamiliale vf) {
            return new SimpleBooleanProperty(vf.getSiegeBebeDisponible());
        }
        return null; // Pas applicable pour les autres types
    });

    // Charger des données initiales
    vehicules.addAll(getVehiculesInitiaux());
    tableVoitures.setItems(vehicules);
}


private List<Vehicule> getVehiculesInitiaux() {
    List<Vehicule> list = new ArrayList<>();
    list.add(new VoitureFamiliale("1234AB", "Toyota", "Corolla", "90cv", "Essence", 2020, 15000.0,
            true, true, false, true, true, true, 5, true, true, "Voiture Familiale", 50.0, null));
    list.add(new VoitureCommerciale("5678CD", "Renault", "Kangoo", "110cv", "Diesel", 2019, 30000.0,
            true, true, true, true, true, true, 1000, false, true, "Voiture Commerciale", 70.0, null));
    list.add(new Vehicule("9101EF", "Ford", "Focus", "100cv", "Essence", 2018, 20000.0,
            true, true, true, true, true, true, "Vehicule", 60.0, null));
    return list;
}


   @FXML
public void onAjouterVoitureClick(ActionEvent event) {
    Dialog<Vehicule> dialog = new Dialog<>();
    dialog.setTitle("Ajouter une voiture");

    // Configuration du choix du type de véhicule
       ComboBox<String> typeBox = new ComboBox<>();
    typeBox.getItems().addAll("Voiture Familiale", "Voiture Commerciale");
    typeBox.setPromptText("Type de véhicule");

    // Champs de base pour tous les véhicules
    TextField matriculeField = new TextField();
    matriculeField.setPromptText("Matricule");

    TextField marqueField = new TextField();
    marqueField.setPromptText("Marque");

    TextField modeleField = new TextField();
    modeleField.setPromptText("Modèle");

    TextField coutParJourField = new TextField();
    coutParJourField.setPromptText("Coût par jour");

    // Conteneur pour champs spécifiques
    VBox champsSpecifiques = new VBox(10);
    
    // Listener pour ajuster les champs selon le type sélectionné
    typeBox.valueProperty().addListener((obs, oldValue, newValue) -> {
        champsSpecifiques.getChildren().clear(); // Réinitialiser les champs spécifiques

        if ("Voiture Familiale".equals(newValue)) {
            TextField nombrePlacesField = new TextField();
            nombrePlacesField.setPromptText("Nombre de places");

            CheckBox siegeBebeField = new CheckBox("Siège bébé disponible");
            CheckBox coffreField = new CheckBox("Grand coffre");

            champsSpecifiques.getChildren().addAll(
                new Label("Nombre de places:"), nombrePlacesField,
                siegeBebeField,
                coffreField
            );
        }
        // Ajoutez des champs pour d'autres types si nécessaire
    });

    // Formulaire complet
    VBox form = new VBox(10, 
        new Label("Type de véhicule:"), typeBox,
        new Label("Matricule:"), matriculeField,
        new Label("Marque:"), marqueField,
        new Label("Modèle:"), modeleField,
        new Label("Coût par jour:"), coutParJourField,
        champsSpecifiques
    );

    dialog.getDialogPane().setContent(form);

    // Boutons de la boîte de dialogue
    ButtonType ajouterButtonType = new ButtonType("Ajouter", ButtonBar.ButtonData.OK_DONE);
    dialog.getDialogPane().getButtonTypes().addAll(ajouterButtonType, ButtonType.CANCEL);

    // Gestion de l'ajout
    dialog.setResultConverter(buttonType -> {
        if (buttonType == ajouterButtonType) {
            String type = typeBox.getValue();
            if ("Voiture Familiale".equals(type)) {
                // Créez un objet VoitureFamiliale avec des champs spécifiques
                int nombrePlaces = Integer.parseInt(((TextField) champsSpecifiques.getChildren().get(1)).getText());
                boolean siegeBebe = ((CheckBox) champsSpecifiques.getChildren().get(2)).isSelected();
                boolean coffre = ((CheckBox) champsSpecifiques.getChildren().get(3)).isSelected();

                return new VoitureFamiliale(
                    matriculeField.getText(),
                    marqueField.getText(),
                    modeleField.getText(),
                    "", "", 2023, 0.0, true, true, true, true, true, true, // Valeurs par défaut
                    nombrePlaces, siegeBebe, coffre, type,
                    Double.parseDouble(coutParJourField.getText()), null
                );
            }
            // Ajoutez un cas pour d'autres types
        }
        return null;
    });

    // Ajouter le véhicule à la liste et actualiser la TableView
    dialog.showAndWait().ifPresent(vehicule -> {
        vehicules.add(vehicule);
        tableVoitures.refresh();
    });
}
}