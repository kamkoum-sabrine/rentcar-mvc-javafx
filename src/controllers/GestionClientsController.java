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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import models.Personnes.Adresse;
import models.Personnes.Chauffeur;
import models.Personnes.Client;
import models.Personnes.Gerant;
import java.sql.Date;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import javafx.scene.layout.HBox;

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

    private ObservableList<Client> clients =  FXCollections.observableArrayList();
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

        //clients.addAll(getClientsInitiaux());
        tableClient.setItems(clients);
        
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
                    Client Client = getTableView().getItems().get(getIndex());
                    System.out.println("CIN du Client "+ Client.getCin());
                    try {
                        onEditClient();
                    } catch (Exception e) {
                        System.err.println("Erreur lors de l'édition : " + e.getMessage());
                        e.printStackTrace();
                    }
                });

                deleteButton.setOnAction(event -> {
                    /*Garage Garage = getTableView().getItems().get(getIndex());
                    onDeleteGarage(Garage);*/
                    Client selectedClient = getTableView().getItems().get(getIndex());
                    //onDeleteClient(selectedClient);
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
    public void onEditClient() {
        Client client = tableClient.getSelectionModel().getSelectedItem();
        if (client == null) {
            // Afficher un message d'erreur si aucun chauffeur n'est sélectionné
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucun client sélectionné");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un client à modifier.");
            alert.showAndWait();
            return;
        }
        Dialog<Client> dialog = new Dialog<>();
        dialog.setTitle("Modifier un client");

        dialog.getDialogPane().setPrefWidth(600);
        dialog.getDialogPane().setPrefHeight(700);

        // Pre-filled fields with current client details
        TextField nomField = new TextField(client.getNom());
        nomField.setPromptText("Nom");

        TextField prenomField = new TextField(client.getPrenom());
        prenomField.setPromptText("Prénom");

        TextField cinField = new TextField(String.valueOf(client.getCin()));
        cinField.setPromptText("CIN");
        cinField.setDisable(true); // CIN shouldn't be editable

        TextField telephoneField = new TextField(String.valueOf(client.getTel()));
        telephoneField.setPromptText("Numéro de téléphone");

        TextField emailField = new TextField(client.getEmail());
        emailField.setPromptText("Email");

        /*DatePicker dateNaissanceField = new DatePicker(client.getDateNaissance().ToLocalDate());
        dateNaissanceField.setPromptText("Date de naissance");*/

        TextField nationaliteField = new TextField(client.getNationalite());
        nationaliteField.setPromptText("Nationalité");

        // Adresse fields
        TextField rueField = new TextField(client.getAdresse().rue());
        rueField.setPromptText("Rue");

        TextField villeField = new TextField(client.getAdresse().ville());
        villeField.setPromptText("Ville");

        TextField codePostalField = new TextField(client.getAdresse().codePostal());
        codePostalField.setPromptText("Code Postal");

        TextField paysField = new TextField(client.getAdresse().pays());
        paysField.setPromptText("Pays");

        // Client-specific fields
        TextField societeField = new TextField(client.getSociete());
        societeField.setPromptText("Société");

        TextField carteCreditField = new TextField(client.getCarteCredit());
        carteCreditField.setPromptText("Numéro de carte de crédit");

        TextField numPermisField = new TextField(client.getNumPermis());
        numPermisField.setPromptText("Numéro de permis");

        /*DatePicker datePermisField = new DatePicker(client.getDatePermis().ToLocalDate());
        datePermisField.setPromptText("Date de délivrance du permis");*/

        TextField lieuPermisField = new TextField(client.getLieuPermis());
        lieuPermisField.setPromptText("Lieu de délivrance du permis");

        // Form layout
        VBox form = new VBox(10,
                new Label("Nom:"), nomField,
                new Label("Prénom:"), prenomField,
                new Label("CIN:"), cinField,
                new Label("Numéro de téléphone:"), telephoneField,
                new Label("Email:"), emailField,
                /*new Label("Date de naissance:"), dateNaissanceField,*/
                new Label("Nationalité:"), nationaliteField,
                new Label("Adresse:"),
                new Label("Rue:"), rueField,
                new Label("Ville:"), villeField,
                new Label("Code Postal:"), codePostalField,
                new Label("Pays:"), paysField,
                new Label("Société:"), societeField,
                new Label("Numéro de carte de crédit:"), carteCreditField,
                new Label("Numéro de permis:"), numPermisField,
                /*new Label("Date de délivrance du permis:"), datePermisField,*/
                new Label("Lieu de délivrance du permis:"), lieuPermisField
        );

        ScrollPane scrollPane = new ScrollPane(form);
        scrollPane.setFitToWidth(true);

        dialog.getDialogPane().setContent(scrollPane);

        // Buttons
        ButtonType saveButtonType = new ButtonType("Sauvegarder", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        // Handle result
        dialog.setResultConverter(buttonType -> {
            if (buttonType == saveButtonType) {
                try {
                    client.setNom(nomField.getText());
                    client.setPrenom(prenomField.getText());
                    client.setTel(Double.parseDouble(telephoneField.getText()));
                    client.setEmail(emailField.getText());
                    /*client.setDateNaissance(java.sql.Date.valueOf(dateNaissanceField.getValue()));*/
                    client.setNationalite(nationaliteField.getText());

                    Adresse adresse = new Adresse(
                            rueField.getText(),
                            villeField.getText(),
                            codePostalField.getText(),
                            paysField.getText()
                    );
                    client.setAdresse(adresse);

                    client.setSociete(societeField.getText());
                    client.setCarteCredit(carteCreditField.getText());
                    client.setNumPermis(numPermisField.getText());
                    /*client.setDatePermis(java.sql.Date.valueOf(datePermisField.getValue()));*/
                    client.setLieuPermis(lieuPermisField.getText());

                    return client;
                } catch (Exception e) {
                    System.err.println("Erreur lors de l'édition du client : " + e.getMessage());
                }
            }
            return null;
        });

        // Show dialog and handle updated client
        dialog.showAndWait().ifPresent(updatedClient -> {
            tableClient.refresh(); // Refresh table to reflect updated data
        });
    }

    





}

