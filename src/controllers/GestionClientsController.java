/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Personnes.Adresse;
import models.Personnes.Chauffeur;
import models.Personnes.Client;
import models.Personnes.Gerant;
import models.vehicules.Vehicule;
import java.time.LocalDate;
import java.sql.Date;


public class GestionClientsController {
    @FXML
    private TableView<Client> tableViewClients;

    @FXML
    private TableColumn<Client, Double> colCin;

    @FXML
    private TableColumn<Client, String> colNom;

    @FXML
    private TableColumn<Client, String> colPrenom;

    @FXML
    private TextField tfNom;

    @FXML
    private TextField tfPrenom;

    @FXML
    private TextField tfCin;

    @FXML
    private TextField tfTelephone;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfSociete;

    @FXML
    private TextField tfCarteCredit;

    @FXML
    private Label statusBar;

    private ObservableList<Client> clientList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colCin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        clientList = FXCollections.observableArrayList();
        tableViewClients.setItems(clientList);
    }

    @FXML
    private void ajouterClient() {
        Dialog<Client> dialog = new Dialog<>();
        dialog.setTitle("Ajouter un client");

        dialog.getDialogPane().setPrefWidth(600);
        dialog.getDialogPane().setPrefHeight(700);

        // Champs de base pour le client
        TextField nomField = new TextField();
        nomField.setPromptText("Nom");

        TextField prenomField = new TextField();
        prenomField.setPromptText("Prénom");

        TextField cinField = new TextField();
        cinField.setPromptText("CIN");

        TextField telephoneField = new TextField();
        telephoneField.setPromptText("Numéro de téléphone");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        TextField societeField = new TextField();
        societeField.setPromptText("Société");

        TextField carteCreditField = new TextField();
        carteCreditField.setPromptText("Carte de crédit");

        TextField rueField = new TextField();
        rueField.setPromptText("Rue");

        TextField villeField = new TextField();
        villeField.setPromptText("Ville");

        TextField codePostalField = new TextField();
        codePostalField.setPromptText("Code Postal");

        TextField paysField = new TextField();
        paysField.setPromptText("Pays");

        Adresse adresse = new Adresse(rueField.getText(), villeField.getText(), codePostalField.getText(), paysField.getText()); // Assuming the Adresse constructor will take care of this

        DatePicker dateNaissanceField = new DatePicker();
        dateNaissanceField.setPromptText("Date de naissance");

        TextField nationaliteField = new TextField();
        nationaliteField.setPromptText("Nationalité");

        DatePicker dateCinField = new DatePicker();
        dateCinField.setPromptText("Date de délivrance du CIN");

        TextField lieuCinField = new TextField();
        lieuCinField.setPromptText("Lieu de délivrance du CIN");

        VBox form = new VBox(10,
                new Label("Nom:"), nomField,
                new Label("Prénom:"), prenomField,
                new Label("CIN:"), cinField,
                new Label("Numéro de téléphone:"), telephoneField,
                new Label("Email:"), emailField,
                new Label("Société:"), societeField,
                new Label("Carte de crédit:"), carteCreditField,
                new Label("Date de naissance:"), dateNaissanceField,
                new Label("Nationalité:"), nationaliteField,
                new Label("Date de délivrance du CIN:"), dateCinField,
                new Label("Lieu de délivrance du CIN:"), lieuCinField
        );

        ScrollPane scrollPane = new ScrollPane(form);
        scrollPane.setFitToWidth(true);
        dialog.getDialogPane().setContent(scrollPane);

        ButtonType ajouterButtonType = new ButtonType("Ajouter", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(ajouterButtonType, ButtonType.CANCEL);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == ajouterButtonType) {
                try {
                    String nom = nomField.getText();
                    String prenom = prenomField.getText();
                    double cin = Double.parseDouble(cinField.getText());
                    double telephone = Double.parseDouble(telephoneField.getText());
                    String email = emailField.getText();
                    String societe = societeField.getText();
                    String carteCredit = carteCreditField.getText();
                    LocalDate dateNaissance = dateNaissanceField.getValue();
                    String nationalite = nationaliteField.getText();
                    LocalDate dateCin = dateCinField.getValue();
                    String lieuCin = lieuCinField.getText();

                    // Validation des champs obligatoires
                    if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || societe.isEmpty() || carteCredit.isEmpty() ||
                            adresse.rue().isEmpty() || adresse.ville().isEmpty() ||
                            dateNaissance == null || dateCin == null || lieuCin.isEmpty()) {
                        throw new IllegalArgumentException("Tous les champs obligatoires doivent être remplis.");
                    }

                    return new Client(
                            societe,
                            carteCredit,
                            cin,
                            nom,
                            prenom,
                            telephone,
                            email,
                            adresse,
                            java.sql.Date.valueOf(dateNaissance),
                            nationalite,
                            java.sql.Date.valueOf(dateCin),
                            lieuCin
                    );

                } catch (Exception e) {
                    System.err.println("Erreur lors de l'ajout du client : " + e.getMessage());
                }
            }
            return null;
        });

        dialog.showAndWait().ifPresent(client -> {
            Gerant.getInstance().ajouterClient(client);
            clientList.add(client);
            tableViewClients.refresh();
        });
    }

    public void modifierClient() {
        Client client = tableViewClients.getSelectionModel().getSelectedItem();
        if (client == null) {
            // Afficher un message d'erreur si aucun client n'est sélectionné
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucun client sélectionné");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un client à modifier.");
            alert.showAndWait();
            return;
        }

        Dialog<Client> dialog = new Dialog<>();
        dialog.setTitle("Modifier le client");

        dialog.getDialogPane().setPrefWidth(400);
        dialog.getDialogPane().setPrefHeight(300);

        // Champs spécifiques à modifier
        DatePicker dateNaissanceField = new DatePicker(client.getDateNaissance().toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDate());

        TextField telephoneField = new TextField(String.valueOf(client.getTel()));
        TextField societeField = new TextField(client.getSociete());
        TextField carteCreditField = new TextField(client.getCarteCredit());

        VBox form = new VBox(10,
                new Label("Date de naissance:"), dateNaissanceField,
                new Label("Numéro de téléphone:"), telephoneField,
                new Label("Société:"), societeField,
                new Label("Carte de crédit:"), carteCreditField
        );

        ScrollPane scrollPane = new ScrollPane(form);
        scrollPane.setFitToWidth(true);
        dialog.getDialogPane().setContent(scrollPane);

        ButtonType saveButtonType = new ButtonType("Sauvegarder", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == saveButtonType) {
                try {
                    // Validation et mise à jour des champs modifiés
                    client.setDateNaissance(java.sql.Date.valueOf(dateNaissanceField.getValue()));
                    client.setTel(Double.parseDouble(telephoneField.getText()));
                    client.setSociete(societeField.getText());
                    client.setCarteCredit(carteCreditField.getText());

                    return client;
                } catch (Exception e) {
                    System.err.println("Erreur dans les données : " + e.getMessage());
                    showAlert("Erreur", "Données invalides", "Vérifiez les données saisies.", Alert.AlertType.ERROR);
                }
            }
            return null;
        });

        dialog.showAndWait().ifPresent(updatedClient -> {
            tableViewClients.refresh(); // Rafraîchit la TableView après modification
            System.out.println("Client mis à jour : " + updatedClient);
        });
    }

    @FXML
    private void supprimerClient() {
        // Récupérer le client sélectionné dans la TableView
        Client selectedClient = tableViewClients.getSelectionModel().getSelectedItem();

        if (selectedClient != null) {
            // Afficher une boîte de confirmation
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation de suppression");
            confirmationAlert.setHeaderText("Êtes-vous sûr de vouloir supprimer ce client ?");
            confirmationAlert.setContentText("Nom : " + selectedClient.getNom() + "\nPrénom : " + selectedClient.getPrenom());

            // Si l'utilisateur confirme, supprimer le client
            confirmationAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // Suppression du client de la liste
                    clientList.remove(selectedClient);

                    // Mise à jour de la TableView
                    tableViewClients.refresh();

                    // Mise à jour du message dans la status bar
                    statusBar.setText("Client '" + selectedClient.getNom() + "' supprimé avec succès !");
                }
            });

        } else {
            // Si aucun client n'est sélectionné, afficher un message d'erreur avec votre méthode showAlert()
            showAlert("Erreur", "Aucun client sélectionné", "Veuillez sélectionner un client à supprimer.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void refresh() {
        // Rafraîchir la TableView pour actualiser les données
        clientList.clear();
        clientList.addAll(Gerant.getInstance().getClients());
        tableViewClients.refresh();
    }

    private void showAlert(String title, String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

