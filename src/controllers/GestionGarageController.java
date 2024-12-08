/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.net.URL;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import models.Personnes.Adresse;
import models.management.Garage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class GestionGarageController  {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Garage> tableGarage;
    @FXML
    private TableColumn<Garage, Integer> colIdGarage;
    @FXML
    private TableColumn<Garage, String> colNomGarage;
    @FXML
    private TableColumn<Garage, Adresse> colAdresse;
    @FXML
    private TableColumn<Garage, String> colNumeroTelephone;
    @FXML
    private TableColumn<Garage, Integer> colCapacite;
    @FXML
    private TableColumn<Garage, String> colHorairesOuverture;
    @FXML
    private TableColumn<Garage, Set<String>> colServicesDisponibles;
    @FXML
    private TableColumn<Garage, Void> colActions;
    @FXML
    private final ObservableList<Garage> garages = FXCollections.observableArrayList();


    
    
    @FXML
    public void onAjouterGarageClick(){
        System.out.println("Gestion des garage chargée !");

    }
    @FXML
    public void initialize() {

        // Initialiser les colonnes génériques

        colIdGarage.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNomGarage.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        colNumeroTelephone.setCellValueFactory(new PropertyValueFactory<>("numtel"));
        colCapacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        colHorairesOuverture.setCellValueFactory(new PropertyValueFactory<>("horaires"));
        colServicesDisponibles.setCellValueFactory(new PropertyValueFactory<>("services"));

        // Charger des données initiales
        garages.addAll();
        tableGarage.setItems(garages);

        // Ajouter des boutons pour la colonne d'actions

        colActions.setCellFactory(param -> new TableCell<>() {
            private final HBox actionBox = new HBox(10); // Conteneur horizontal pour les icônes
            private final Button editButton = new Button();
            private final Button deleteButton = new Button();

            {
                editButton.setText("Éditer");
                deleteButton.setText("Supprimer");

                // (Facultatif) Ajoute des styles pour différencier les boutons
                editButton.setStyle("-fx-background-color: #F3C623; -fx-text-fill: white; -fx-font-weight: bold;");
                deleteButton.setStyle("-fx-background-color: #B8001F; -fx-text-fill: white; -fx-font-weight: bold;");


                // Ajout des actions
                editButton.setOnAction(event -> {
                    System.out.println("edit !!");
                    Garage Garage = getTableView().getItems().get(getIndex());
                    System.out.println("ID Garage "+ Garage.getIdGarage());
                    try {
                        onEditGarage(Garage);
                    } catch (Exception e) {
                        System.err.println("Erreur lors de l'édition : " + e.getMessage());
                        e.printStackTrace();
                    }
                });

                deleteButton.setOnAction(event -> {
                    /*Garage Garage = getTableView().getItems().get(getIndex());
                    onDeleteGarage(Garage);*/
                    Garage selectedGarage = getTableView().getItems().get(getIndex());
                    onDeleteGarage(selectedGarage);
                });

                actionBox.getChildren().addAll(editButton, deleteButton);
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(actionBox);
                }
            }
            });
    }
    private void onEditGarage(Garage Garage) {
            System.out.println("onEditGarage appelé pour le garage : " + Garage.getIdGarage());
            // Configuration de la boîte de dialogue
            Dialog<Garage> dialog = new Dialog<>();
            dialog.setTitle("Modifier le garage");
            dialog.getDialogPane().setPrefWidth(600);
            dialog.getDialogPane().setPrefHeight(700);

            // Champs de saisie dans un GridPane
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20));

            // Champs de saisie
            TextField idGarageField = new TextField();
            idGarageField.setPromptText("ID Garage");
            idGarageField.setText(String.valueOf(Garage.getIdGarage()));  // Valeur de l'ID du garage
            
            // Champs de saisie
            TextField nomGarageField = new TextField();
            nomGarageField.setPromptText("Nom du Garage");
            nomGarageField.setText(String.valueOf(Garage.getNom()));  // Nom du garage
            
            // Fields for Adresse
            TextField rueField = new TextField(Garage.getAdresse().rue());
            rueField.setPromptText("Rue");

            TextField villeField = new TextField(Garage.getAdresse().ville());
            villeField.setPromptText("Ville");

            TextField codePostalField = new TextField(Garage.getAdresse().codePostal());
            codePostalField.setPromptText("Code Postal");

            TextField paysField = new TextField(Garage.getAdresse().pays());
            paysField.setPromptText("Pays");
            
            // Champs de saisie
            TextField numTelGarageField = new TextField();
            numTelGarageField.setPromptText("Num tel Garage");
            numTelGarageField.setText(String.valueOf(Garage.getNumeroTelephone()));  // Num du garage
            
            // Champs de saisie
            TextField capaciteGarageField = new TextField();
            capaciteGarageField.setPromptText("Capacite du Garage");
            capaciteGarageField.setText(String.valueOf(Garage.getCapacite()));  // capacite du garage
            
            TextField horairesGarageField = new TextField();
            horairesGarageField.setPromptText("Horaires du Garage");
            horairesGarageField.setText(String.valueOf(Garage.getHorairesOuverture()));  // Horaires du Garage

            ObservableList<String> allServices = FXCollections.observableArrayList(
            "Réparation", "Peinture", "Diagnostic", "Lavage", "Entretien"
            );  // Example services
            ListView<String> servicesListView = new ListView<>(allServices);
            servicesListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            servicesListView.setPrefHeight(100);

            // Pre-select existing services
            if (Garage.getServicesDisponibles() != null) {
                for (String service : Garage.getServicesDisponibles()) {
                    if (allServices.contains(service)) {
                        servicesListView.getSelectionModel().select(service);
                    }
                }
            }

            // Add fields to the grid
            grid.add(new Label("ID Garage:"), 0, 0);
            grid.add(idGarageField, 1, 0);
            grid.add(new Label("Nom du Garage:"), 0, 1);
            grid.add(nomGarageField, 1, 1);
            grid.add(new Label("Adresse - Rue:"), 0, 2);
            grid.add(rueField, 1, 2);
            grid.add(new Label("Ville:"), 0, 3);
            grid.add(villeField, 1, 3);
            grid.add(new Label("Code Postal:"), 0, 4);
            grid.add(codePostalField, 1, 4);
            grid.add(new Label("Pays:"), 0, 5);
            grid.add(paysField, 1, 5);
            grid.add(new Label("Numéro de Téléphone:"), 0, 6);
            grid.add(numTelGarageField, 1, 6);
            grid.add(new Label("Capacité:"), 0, 7);
            grid.add(capaciteGarageField, 1, 7);
            grid.add(new Label("Horaires d'ouverture:"), 0, 8);
            grid.add(horairesGarageField, 1, 8);
            grid.add(new Label("Services Disponibles:"), 0, 9);
            grid.add(servicesListView, 1, 9);

            // Add the grid to the dialog
            dialog.getDialogPane().setContent(grid);

            // Buttons
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

            // Handle OK action
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == ButtonType.OK) {
                    // Update garage details
                    Garage.setNom(nomGarageField.getText());
                    // Construct and set the Adresse
                    Adresse adresse = new Adresse(
                     rueField.getText(),
                   villeField.getText(),
               codePostalField.getText(),
                   paysField.getText()
                       );
                    Garage.setAdresse(adresse);                    
                    Garage.setNumeroTelephone(numTelGarageField.getText());
                    Garage.setCapacite(Integer.parseInt(capaciteGarageField.getText()));
                    Garage.setHorairesOuverture(horairesGarageField.getText());

                    // Update servicesDisponibles
                    Set<String> selectedServices = Set.copyOf(servicesListView.getSelectionModel().getSelectedItems());
                    Garage.setServicesDisponibles(selectedServices);

                    System.out.println("Garage updated: " + Garage);
                    return Garage;
                }
                return null;
            });

        dialog.showAndWait().ifPresent(updatedGarage -> {
        tableGarage.refresh(); // Met à jour la TableView
    });
    }
    
    private void onDeleteGarage(Garage garage) {
        System.out.println("onDeleteGarage called for garage: " + garage.getIdGarage());

        // Confirmation dialog
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Supprimer le garage");
        confirmationDialog.setHeaderText("Confirmez-vous la suppression de ce garage ?");
        confirmationDialog.setContentText(
            "Garage ID: " + garage.getIdGarage() + "\n" +
            "Nom: " + garage.getNom() + "\n" +
            "Adresse: " + garage.getAdresse() + "\n" +
            "Attention : Cette action est irréversible."
        );

        // Show dialog and capture user response
        Optional<ButtonType> result = confirmationDialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Logic to delete the garage
            // Example: Remove it from the data source (e.g., a database or a list)

            // Notify user of the result
            garages.remove(garage);
            tableGarage.refresh();
        } else {
            System.out.println("Suppression annulée par l'utilisateur.");
        }
    }
    
    public void onAjouterGarageClick(ActionEvent event) {
        // Création de la boîte de dialogue
        Dialog<Garage> dialog = new Dialog<>();
        dialog.setTitle("Ajouter un garage");
        dialog.setHeaderText("Veuillez remplir les informations du garage");

        // Boutons OK et Annuler
        ButtonType okButtonType = new ButtonType("Ajouter", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

        // Champs du formulaire
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        // Champs de saisie
        TextField idField = new TextField();
        idField.setPromptText("ID Garage");

        TextField nomField = new TextField();
        nomField.setPromptText("Nom du garage");

        TextField rueField = new TextField();
        rueField.setPromptText("Rue");

        TextField villeField = new TextField();
        villeField.setPromptText("Ville");

        TextField codePostalField = new TextField();
        codePostalField.setPromptText("Code postal");

        TextField paysField = new TextField();
        paysField.setPromptText("Pays");

        TextField numeroField = new TextField();
        numeroField.setPromptText("Numéro de téléphone");

        TextField capaciteField = new TextField();
        capaciteField.setPromptText("Capacité");

        TextField horairesField = new TextField();
        horairesField.setPromptText("Horaires d'ouverture");

        TextArea servicesField = new TextArea();
        servicesField.setPromptText("Services disponibles (séparés par des virgules)");
        servicesField.setPrefRowCount(3);

        // Ajouter les champs au GridPane
        grid.add(new Label("ID Garage :"), 0, 0);
        grid.add(idField, 1, 0);

        grid.add(new Label("Nom :"), 0, 1);
        grid.add(nomField, 1, 1);

        grid.add(new Label("Rue :"), 0, 2);
        grid.add(rueField, 1, 2);

        grid.add(new Label("Ville :"), 0, 3);
        grid.add(villeField, 1, 3);

        grid.add(new Label("Code postal :"), 0, 4);
        grid.add(codePostalField, 1, 4);

        grid.add(new Label("Pays :"), 0, 5);
        grid.add(paysField, 1, 5);

        grid.add(new Label("Numéro de téléphone :"), 0, 6);
        grid.add(numeroField, 1, 6);

        grid.add(new Label("Capacité :"), 0, 7);
        grid.add(capaciteField, 1, 7);

        grid.add(new Label("Horaires d'ouverture :"), 0, 8);
        grid.add(horairesField, 1, 8);

        grid.add(new Label("Services disponibles :"), 0, 9);
        grid.add(servicesField, 1, 9);

        dialog.getDialogPane().setContent(grid);

        // Désactiver le bouton OK tant que les champs obligatoires ne sont pas remplis
        Node okButton = dialog.getDialogPane().lookupButton(okButtonType);
        okButton.setDisable(true);

        // Validation des champs pour activer le bouton OK
        ChangeListener<Object> changeListener = (observable, oldValue, newValue) -> {
            boolean allFieldsFilled = !idField.getText().trim().isEmpty()
                    && !nomField.getText().trim().isEmpty()
                    && !rueField.getText().trim().isEmpty()
                    && !villeField.getText().trim().isEmpty()
                    && !paysField.getText().trim().isEmpty()
                    && !numeroField.getText().trim().isEmpty()
                    && !capaciteField.getText().trim().isEmpty()
                    && !horairesField.getText().trim().isEmpty();
            okButton.setDisable(!allFieldsFilled);
        };

        idField.textProperty().addListener(changeListener);
        nomField.textProperty().addListener(changeListener);
        rueField.textProperty().addListener(changeListener);
        villeField.textProperty().addListener(changeListener);
        paysField.textProperty().addListener(changeListener);
        numeroField.textProperty().addListener(changeListener);
        capaciteField.textProperty().addListener(changeListener);
        horairesField.textProperty().addListener(changeListener);

        // Résultat de la boîte de dialogue
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                try {
                    // Validate numeric fields
                    if (!isNumeric(idField.getText().trim()) || !isNumeric(capaciteField.getText().trim())) {
                        showAlert("Erreur", "L'ID et la capacité doivent être des nombres.");
                        return null;
                    }

                    // Create normalized strings
                    String nom = normalizeString(nomField.getText().trim());
                    String num = normalizeString(numeroField.getText().trim());
                    String rue = normalizeString(rueField.getText().trim());
                    String ville = normalizeString(villeField.getText().trim());
                    String pays = normalizeString(paysField.getText().trim());

                    // Create the garage
                    return new Garage(
                            Integer.parseInt(idField.getText().trim()),
                            nom,
                            new Adresse(rue, ville, codePostalField.getText().trim(), pays),
                            num,
                            Integer.parseInt(capaciteField.getText().trim()),
                            horairesField.getText().trim(),
                            Set.of(servicesField.getText().trim().split("\\s*,\\s*"))
                    );
                } catch (Exception e) {
                    showAlert("Erreur", "Une erreur inattendue s'est produite.");
                    return null;
                }
            }
            return null;
        });

        // Afficher la boîte de dialogue et récupérer le résultat
        Optional<Garage> result = dialog.showAndWait();

        result.ifPresent(garage -> {
            garages.add(garage);
            tableGarage.refresh();
            System.out.println("Garage ajouté avec succès : " + garage);
        });
    }
    
    private void validateFields(Node okButton, TextField idField, TextField nomField, TextField rueField, 
                            TextField villeField, TextField paysField, TextField numeroField, 
                            TextField capaciteField, TextField horairesField) {
        boolean isDisabled = idField.getText().trim().isEmpty()
            || nomField.getText().trim().isEmpty()
            || rueField.getText().trim().isEmpty()
            || villeField.getText().trim().isEmpty()
            || paysField.getText().trim().isEmpty()
            || numeroField.getText().trim().isEmpty()
            || capaciteField.getText().trim().isEmpty()
            || horairesField.getText().trim().isEmpty()
            || !isNumeric(idField.getText().trim())  // ID must be numeric
            || !isNumeric(capaciteField.getText().trim());  // Capacity must be numeric

        okButton.setDisable(isDisabled);
    }

    // Helper method to check if a string is numeric
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void showAlertOk(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static String normalizeString(String input) {
        if (input == null) return "";
        return Normalizer.normalize(input.trim(), Normalizer.Form.NFD)
                         .replaceAll("\\p{M}", "")  // Remove accents
                         .replaceAll("\\s+", " ")   // Replace multiple spaces with one
                         .toLowerCase();            // Convert to lowercase
    }





        
    
    
}
