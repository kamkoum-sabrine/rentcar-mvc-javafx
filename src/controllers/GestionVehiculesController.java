/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import models.vehicules.Vehicule;

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

    private final ObservableList<Vehicule> vehicules = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialiser les colonnes de la TableView
        colMatricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        colMarque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        colModele.setCellValueFactory(new PropertyValueFactory<>("modele"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colCoutParJour.setCellValueFactory(new PropertyValueFactory<>("coutParJour"));

        // Charger des données initiales
        vehicules.addAll(getVehiculesInitiaux());
        tableVoitures.setItems(vehicules);
    }

    private List<Vehicule> getVehiculesInitiaux() {
        // Exemple de données initiales
        List<Vehicule> list = new ArrayList<>();
        list.add(new Vehicule("1234AB", "Toyota", "Corolla", "90cv", "Essence", 2020, 15000.0,
                true, true, false, true, true, true, "Voiture Familiale", 50.0, null));
        list.add(new Vehicule("5678CD", "Renault", "Kangoo", "110cv", "Diesel", 2019, 30000.0,
                true, true, true, true, true, true, "Voiture Commerciale", 70.0, null));
        return list;
    }

    @FXML
    public void onAjouterVoitureClick(ActionEvent event) {
        // Créer une boîte de dialogue pour ajouter un véhicule
        Dialog<Vehicule> dialog = new Dialog<>();
        dialog.setTitle("Ajouter une voiture");

        // Configuration des champs du formulaire
        TextField matriculeField = new TextField();
        matriculeField.setPromptText("Matricule");

        TextField marqueField = new TextField();
        marqueField.setPromptText("Marque");

        TextField modeleField = new TextField();
        modeleField.setPromptText("Modèle");

        TextField typeField = new TextField();
        typeField.setPromptText("Type");

        TextField coutParJourField = new TextField();
        coutParJourField.setPromptText("Coût par jour");

        VBox form = new VBox(10, new Label("Matricule:"), matriculeField,
                new Label("Marque:"), marqueField,
                new Label("Modèle:"), modeleField,
                new Label("Type:"), typeField,
                new Label("Coût par jour:"), coutParJourField);
        dialog.getDialogPane().setContent(form);

        ButtonType ajouterButtonType = new ButtonType("Ajouter", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(ajouterButtonType, ButtonType.CANCEL);

        // Gestion de l'ajout
        dialog.setResultConverter(buttonType -> {
            if (buttonType == ajouterButtonType) {
                return new Vehicule(
                        matriculeField.getText(),
                        marqueField.getText(),
                        modeleField.getText(),
                        "", // Champ laissé vide pour simplifier
                        "", // Champ laissé vide pour simplifier
                        2023, // Année par défaut
                        0.0, // Kilométrage par défaut
                        true, true, true, true, true, true, // Valeurs par défaut
                        typeField.getText(),
                        Double.parseDouble(coutParJourField.getText()),
                        null // Coordonnées GPS par défaut
                );
            }
            return null;
        });

        dialog.showAndWait().ifPresent(vehicule -> vehicules.add(vehicule));
    }
    
}
