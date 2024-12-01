/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import models.Personnes.Adresse;
import models.Personnes.Chauffeur;
import models.Personnes.Client;
import models.Personnes.Gerant;
import models.vehicules.Vehicule;

import java.sql.Date;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class GestionClientsController  {

    @FXML
    private TableView<Client> tableClient;
    @FXML
    private TableColumn<Client, String> colCin;
    @FXML
    private TableColumn<Client, String> colNom;
    @FXML
    private TableColumn<Client, String> colPrenom;
    @FXML
    private TableColumn<Client, String> colTel;
    @FXML
    private TableColumn<Client, String> colEmail;
    @FXML
    private TableColumn<Client, String> colSociete;
    @FXML
    private TableColumn<Client, String> colCarteCredit;
    @FXML
    private TableColumn<Client, String> colNumPermis;
    @FXML
    private TableColumn<Client, String> colDatePermis;
    @FXML
    private TableColumn<Client, String> colLieuPermis;
    @FXML
    private TableColumn<Client, Void> colActions;
    @FXML
    private Label statusBar;

    private final ObservableList<Client> clients=FXCollections.observableArrayList();
    public void initialize() {
        // Initialize table columns
        colCin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colSociete.setCellValueFactory(new PropertyValueFactory<>("societe"));
        colCarteCredit.setCellValueFactory(new PropertyValueFactory<>("carteCredit"));
        colNumPermis.setCellValueFactory(new PropertyValueFactory<>("numPermis"));
        colDatePermis.setCellValueFactory(new PropertyValueFactory<>("datePermis"));
        colLieuPermis.setCellValueFactory(new PropertyValueFactory<>("lieuPermis"));

        // Add action buttons dynamically
        /*colActions.setCellFactory(param -> new TableCell<>() {
            private final Button viewLocationsButton = new Button("Voir Locations");

            {
                viewLocationsButton.setOnAction(event -> {
                    Client client = getTableView().getItems().get(getIndex());
                    showLocationsForClient(client);
                });
            }*/

        tableClient.setItems(clients);

    }
    public void AjouterClient(){
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

        DatePicker dateNaissanceField = new DatePicker();
        dateNaissanceField.setPromptText("Date de naissance");

        TextField nationaliteField = new TextField();
        nationaliteField.setPromptText("Nationalité");

        // Adresse
        TextField rueField = new TextField();
        rueField.setPromptText("Rue");

        TextField villeField = new TextField();
        villeField.setPromptText("Ville");

        TextField codePostalField = new TextField();
        codePostalField.setPromptText("Code Postal");

        TextField paysField = new TextField();
        paysField.setPromptText("Pays");

        // Champs spécifiques au client
        TextField societeField = new TextField();
        societeField.setPromptText("Société");

        TextField carteCreditField = new TextField();
        carteCreditField.setPromptText("Numéro de carte de crédit");

        TextField numPermisField = new TextField();
        numPermisField.setPromptText("Numéro de permis");

        DatePicker datePermisField = new DatePicker();
        datePermisField.setPromptText("Date de délivrance du permis");

        TextField lieuPermisField = new TextField();
        lieuPermisField.setPromptText("Lieu de délivrance du permis");

        // Formulaire complet
        VBox form = new VBox(10,
                new Label("Nom:"), nomField,
                new Label("Prénom:"), prenomField,
                new Label("CIN:"), cinField,
                new Label("Numéro de téléphone:"), telephoneField,
                new Label("Email:"), emailField,
                new Label("Date de naissance:"), dateNaissanceField,
                new Label("Nationalité:"), nationaliteField,
                new Label("Adresse:"),
                new Label("Rue:"), rueField,
                new Label("Ville:"), villeField,
                new Label("Code Postal:"), codePostalField,
                new Label("Pays:"), paysField,
                new Label("Société:"), societeField,
                new Label("Numéro de carte de crédit:"), carteCreditField,
                new Label("Numéro de permis:"), numPermisField,
                new Label("Date de délivrance du permis:"), datePermisField,
                new Label("Lieu de délivrance du permis:"), lieuPermisField
        );

        ScrollPane scrollPane = new ScrollPane(form);
        scrollPane.setFitToWidth(true);

        dialog.getDialogPane().setContent(scrollPane);

        // Boutons
        ButtonType ajouterButtonType = new ButtonType("Ajouter", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(ajouterButtonType, ButtonType.CANCEL);

        // Gestion de l'ajout
        dialog.setResultConverter(buttonType -> {
            if (buttonType == ajouterButtonType) {
                try {
                    String nom = nomField.getText();
                    String prenom = prenomField.getText();
                    double cin = Double.parseDouble(cinField.getText());
                    double telephone = Double.parseDouble(telephoneField.getText());
                    String email = emailField.getText();
                    LocalDate dateNaissance = dateNaissanceField.getValue();
                    String nationalite = nationaliteField.getText();

                    Adresse adresse = new Adresse(rueField.getText(), villeField.getText(), codePostalField.getText(), paysField.getText());

                    String societe = societeField.getText();
                    String carteCredit = carteCreditField.getText();
                    String numPermis = numPermisField.getText();
                    LocalDate datePermis = datePermisField.getValue();
                    String lieuPermis = lieuPermisField.getText();

                    // Validation des champs obligatoires
                    if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || nationalite.isEmpty() ||
                            adresse.rue().isEmpty() || adresse.ville().isEmpty() || societe.isEmpty()) {
                        throw new IllegalArgumentException("Tous les champs obligatoires doivent être remplis.");
                    }
                    Date dateCin=null;
                    String lieuCin="-";

                    // Création de l'objet Client
                    return new Client(
                             societe,  carteCredit,  cin,  nom,  prenom,
                     telephone,  email,  adresse, java.sql.Date.valueOf(dateNaissance), nationalite,  dateCin, lieuCin,
                             numPermis,  lieuPermis
                    );

                } catch (Exception e) {
                    System.err.println("Erreur lors de l'ajout du client : " + e.getMessage());
                }
            }
            return null;
        });

        // Affichage de la boîte de dialogue
        dialog.showAndWait().ifPresent(client -> {
            Gerant.getInstance().ajouterClient(client);
            clients.add(client);
            tableClient.refresh();
        });
    }

    public void ModifierClient() {
        Client client = tableClient.getSelectionModel().getSelectedItem();
        if (client == null) {
            // Afficher un message d'erreur si aucun client n'est sélectionné
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucun client sélectionné");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un client à modifier.");
            alert.showAndWait();
            return;
        }
        System.out.println("ModifierClient appelé pour le client : " + client.getNom() + " " + client.getPrenom());

        Dialog<Client> dialog = new Dialog<>();
        dialog.setTitle("Modifier les informations du client");

        dialog.getDialogPane().setPrefWidth(600);
        dialog.getDialogPane().setPrefHeight(400);

        // Champs à modifier
        TextField telephoneField = new TextField(String.valueOf(client.getTel()));
        TextField emailField = new TextField(client.getEmail());
        TextField carteCreditField = new TextField(client.getCarteCredit());
        TextField lieuPermisField = new TextField(client.getLieuPermis());

        // Formulaire limité aux champs modifiables
        VBox form = new VBox(10,
                new Label("Numéro de téléphone:"), telephoneField,
                new Label("Email:"), emailField,
                new Label("Numéro de carte de crédit:"), carteCreditField,
                new Label("Lieu de délivrance du permis:"), lieuPermisField
        );

        ScrollPane scrollPane = new ScrollPane(form);
        scrollPane.setFitToWidth(true);
        dialog.getDialogPane().setContent(scrollPane);

        // Boutons
        ButtonType saveButtonType = new ButtonType("Sauvegarder", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == saveButtonType) {
                try {
                    // Mise à jour des champs modifiables du client
                    client.setTel(Double.parseDouble(telephoneField.getText()));
                    client.setEmail(emailField.getText());
                    client.setCarteCredit(carteCreditField.getText());
                    client.setLieuPermis(lieuPermisField.getText());

                    return client;
                } catch (Exception e) {
                    System.err.println("Erreur lors de la modification du client : " + e.getMessage());
                }
            }
            return null;
        });

        // Affichage de la boîte de dialogue
        dialog.showAndWait().ifPresent(updatedClient -> {
            tableClient.refresh(); // Met à jour la TableView
            Gerant.getInstance().setClients(new ArrayList<>(clients)); // Met à jour la liste des clients dans le gestionnaire
        });
    }

    @FXML
    public void SupprimerClient() {
        // Récupérer le chauffeur sélectionné dans la TableView
        Client selectedClient = tableClient.getSelectionModel().getSelectedItem();

        if (selectedClient != null) {
            // Afficher une boîte de confirmation
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation de suppression");
            confirmationAlert.setHeaderText("Êtes-vous sûr de vouloir supprimer ce chauffeur ?");
            confirmationAlert.setContentText("Nom : " + selectedClient.getNom() + "\nPrénom : " + selectedClient.getPrenom());

            // Si l'utilisateur confirme, supprimer le chauffeur
            confirmationAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // Suppression du chauffeur de la liste
                    clients.remove(selectedClient);

                    // Mise à jour de la TableView
                    tableClient.refresh();

                    // Mise à jour du message dans la status bar
                    statusBar.setText("Chauffeur '" + selectedClient.getNom() + "' supprimé avec succès !");
                }
            });

        } else {
            // Si aucun chauffeur n'est sélectionné, afficher un message d'erreur avec votre méthode showAlert
            System.out.println("Aucun chauffeur sélectionné");
            showAlert("Erreur", "Aucun chauffeur sélectionné", "Sélectionnez un chauffeur à supprimer !", Alert.AlertType.ERROR);
        }
    }
    private void showAlert(String title, String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }



}