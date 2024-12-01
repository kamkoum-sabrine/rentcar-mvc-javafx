package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import models.management.Entretien;

import java.time.LocalDate;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class GestionEntretienController {

    @FXML
    private TableView<Entretien> tableViewEntretiens;

    @FXML
    private TableColumn<Entretien, Integer> colIdEntretien;

    @FXML
    private TableColumn<Entretien, LocalDate> colDateEntretien;

    @FXML
    private TableColumn<Entretien, String> colTypeEntretien;

    @FXML
    private TableColumn<Entretien, Double> colCout;

    @FXML
    private TableColumn<Entretien, Integer> colKilometrage;

    @FXML
    private TableColumn<Entretien, String> colTechnicien;

    @FXML
    private TableColumn<Entretien, String> colNotes;

    @FXML
    private TableColumn<Entretien, String> colVehicule;

    @FXML
    private TableColumn<Entretien, String> colGarage;

    private ObservableList<Entretien> entretiens;

    @FXML
    public void initialize() {
        // Configuration des colonnes avec les propriétés de la classe Entretien
        colIdEntretien.setCellValueFactory(new PropertyValueFactory<>("idEntretien"));
        colDateEntretien.setCellValueFactory(new PropertyValueFactory<>("dateEntretien"));
        colTypeEntretien.setCellValueFactory(new PropertyValueFactory<>("typeEntretien"));
        colCout.setCellValueFactory(new PropertyValueFactory<>("cout"));
        colKilometrage.setCellValueFactory(new PropertyValueFactory<>("kilometrage"));
        colTechnicien.setCellValueFactory(new PropertyValueFactory<>("technicien"));
        colNotes.setCellValueFactory(new PropertyValueFactory<>("notes"));
        colVehicule.setCellValueFactory(new PropertyValueFactory<>("vehicule"));
        colGarage.setCellValueFactory(new PropertyValueFactory<>("garage"));

        // Initialisation de la liste des entretiens (exemple statique pour démarrer)
        entretiens = FXCollections.observableArrayList(

        );

        // Liaison de la liste avec la TableView
        tableViewEntretiens.setItems(entretiens);
    }

    public void ajouterEntretien() {
        Dialog<Entretien> dialog = new Dialog<>();
        dialog.setTitle("Ajouter un entretien");

        dialog.getDialogPane().setPrefWidth(600);
        dialog.getDialogPane().setPrefHeight(700);

        // Champs pour l'entretien
        DatePicker dateEntretienField = new DatePicker();
        dateEntretienField.setPromptText("Date d'entretien");

        TextField typeEntretienField = new TextField();
        typeEntretienField.setPromptText("Type d'entretien");

        TextField coutField = new TextField();
        coutField.setPromptText("Coût");

        TextField kilometrageField = new TextField();
        kilometrageField.setPromptText("Kilométrage");

        TextField technicienField = new TextField();
        technicienField.setPromptText("Nom du technicien");

        TextField notesField = new TextField();
        notesField.setPromptText("Notes");

        TextField vehiculeField = new TextField();
        vehiculeField.setPromptText("Véhicule");

        TextField garageField = new TextField();
        garageField.setPromptText("Garage");

        // Formulaire complet
        VBox form = new VBox(10,
                new Label("Date d'entretien:"), dateEntretienField,
                new Label("Type d'entretien:"), typeEntretienField,
                new Label("Coût:"), coutField,
                new Label("Kilométrage:"), kilometrageField,
                new Label("Technicien:"), technicienField,
                new Label("Notes:"), notesField,
                new Label("Véhicule:"), vehiculeField,
                new Label("Garage:"), garageField
        );

        dialog.getDialogPane().setContent(form);

        // Boutons
        ButtonType ajouterButtonType = new ButtonType("Ajouter", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(ajouterButtonType, ButtonType.CANCEL);

        // Gestion de l'ajout
        dialog.setResultConverter(buttonType -> {
            if (buttonType == ajouterButtonType) {
                try {
                    LocalDate dateEntretien = dateEntretienField.getValue();
                    String typeEntretien = typeEntretienField.getText();
                    double cout = Double.parseDouble(coutField.getText());
                    int kilometrage = Integer.parseInt(kilometrageField.getText());
                    String technicien = technicienField.getText();
                    String notes = notesField.getText();
                    String vehicule = vehiculeField.getText();
                    String garage = garageField.getText();

                    // Validation des champs obligatoires
                    if (typeEntretien.isEmpty() || vehicule.isEmpty() || garage.isEmpty()) {
                        throw new IllegalArgumentException("Les champs obligatoires doivent être remplis.");
                    }

                    // Création de l'objet Entretien
                   /* return new Entretien(
                            entretiens.size() + 1, // ID généré automatiquement
                            dateEntretien,
                            typeEntretien,
                            cout,
                            kilometrage,
                            technicien,
                            notes,
                            vehicule,
                            garage
                    );*/

                } catch (Exception e) {
                    System.err.println("Erreur lors de l'ajout de l'entretien : " + e.getMessage());
                }
            }
            return null;
        });

        // Affichage de la boîte de dialogue
        dialog.showAndWait().ifPresent(entretien -> {
            entretiens.add(entretien);
            tableViewEntretiens.refresh();
        });
    }

}

