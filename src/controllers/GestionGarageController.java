
package controllers;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import models.Personnes.Adresse;
import models.Personnes.Gerant;
import models.management.Garage;

import java.util.HashSet;
import java.util.Set;


public class GestionGarageController {
    @FXML
    private TableView<Garage> tableViewGarages;

    @FXML
    private TableColumn<Garage, Integer> colIdGarage;

    @FXML
    private TableColumn<Garage, String> colNom;

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
    private TextField tfIdGarage;

    @FXML
    private TextField tfNom;

    @FXML
    private TextField tfNumeroTelephone;

    @FXML
    private TextField tfCapacite;

    @FXML
    private TextField tfHorairesOuverture;

    @FXML
    private TextField tfServicesDisponibles;

    @FXML
    private Label statusBar;

    private ObservableList<Garage> garageList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colIdGarage.setCellValueFactory(new PropertyValueFactory<>("idGarage"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        colNumeroTelephone.setCellValueFactory(new PropertyValueFactory<>("numeroTelephone"));
        colCapacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        colHorairesOuverture.setCellValueFactory(new PropertyValueFactory<>("horairesOuverture"));
        colServicesDisponibles.setCellValueFactory(new PropertyValueFactory<>("servicesDisponibles"));

        garageList = FXCollections.observableArrayList();
        tableViewGarages.setItems(garageList);
    }

    @FXML
    private void ajouterGarage() {
        Dialog<Garage> dialog = new Dialog<>();
        dialog.setTitle("Ajouter un garage");

        dialog.getDialogPane().setPrefWidth(500);
        dialog.getDialogPane().setPrefHeight(400);
        // Adresse
        TextField rueField = new TextField();
        rueField.setPromptText("Rue");

        TextField villeField = new TextField();
        villeField.setPromptText("Ville");

        TextField codePostalField = new TextField();
        codePostalField.setPromptText("Code Postal");

        TextField paysField = new TextField();
        paysField.setPromptText("Pays");
        // Champs de base pour le garage
        TextField idGarageField = new TextField();
        idGarageField.setPromptText("ID Garage");

        TextField nomField = new TextField();
        nomField.setPromptText("Nom");

        TextField numeroTelephoneField = new TextField();
        numeroTelephoneField.setPromptText("Numéro de téléphone");

        TextField capaciteField = new TextField();
        capaciteField.setPromptText("Capacité");

        TextField horairesOuvertureField = new TextField();
        horairesOuvertureField.setPromptText("Horaires d'ouverture");

        TextField servicesDisponiblesField = new TextField();
        servicesDisponiblesField.setPromptText("Services Disponibles (comma-separated)");

        VBox form = new VBox(10,
                new Label("ID Garage:"), idGarageField,
                new Label("Nom:"), nomField,
                new Label("Numéro de téléphone:"), numeroTelephoneField,
                new Label("Capacité:"), capaciteField,
                new Label("Horaires d'ouverture:"), horairesOuvertureField,
                new Label("Services Disponibles:"), servicesDisponiblesField
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
                    Adresse adresse = new Adresse(rueField.getText(), villeField.getText(), codePostalField.getText(), paysField.getText());
                    int idGarage = Integer.parseInt(idGarageField.getText());
                    String nom = nomField.getText();
                    String numeroTelephone = numeroTelephoneField.getText();
                    int capacite = Integer.parseInt(capaciteField.getText());
                    String horairesOuverture = horairesOuvertureField.getText();
                    Set<String> servicesDisponibles = Set.of(servicesDisponiblesField.getText().split(",\\s*"));

                    if (nom.isEmpty() || numeroTelephone.isEmpty() || horairesOuverture.isEmpty() || servicesDisponibles.isEmpty()) {
                        throw new IllegalArgumentException("Tous les champs obligatoires doivent être remplis.");
                    }

                    // Création de l'objet Garage
                    return new Garage(idGarage, nom,  adresse, numeroTelephone, capacite, horairesOuverture, servicesDisponibles);

                } catch (Exception e) {
                    System.err.println("Erreur lors de l'ajout du garage : " + e.getMessage());
                }
            }
            return null;
        });

        dialog.showAndWait().ifPresent(garage -> {
            Gerant.getInstance().ajouterGarage(garage);
            garageList.add(garage);
            tableViewGarages.refresh();
        });
    }

    public void modifierGarage() {
        Garage garage = tableViewGarages.getSelectionModel().getSelectedItem();
        if (garage == null) {
            // Afficher un message d'erreur si aucun garage n'est sélectionné
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucun garage sélectionné");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un garage à modifier.");
            alert.showAndWait();
            return;
        }

        Dialog<Garage> dialog = new Dialog<>();
        dialog.setTitle("Modifier le garage");

        dialog.getDialogPane().setPrefWidth(500);
        dialog.getDialogPane().setPrefHeight(400);

        // Champs spécifiques à modifier
        TextField nomField = new TextField(garage.getNom());
        TextField numeroTelephoneField = new TextField(garage.getNumeroTelephone());
        TextField capaciteField = new TextField(String.valueOf(garage.getCapacite()));
        TextField horairesOuvertureField = new TextField(garage.getHorairesOuverture());
        TextField servicesDisponiblesField = new TextField(String.join(", ", garage.getServicesDisponibles()));

        VBox form = new VBox(10,
                new Label("Nom:"), nomField,
                new Label("Numéro de téléphone:"), numeroTelephoneField,
                new Label("Capacité:"), capaciteField,
                new Label("Horaires d'ouverture:"), horairesOuvertureField,
                new Label("Services Disponibles:"), servicesDisponiblesField
        );

        ScrollPane scrollPane = new ScrollPane(form);
        scrollPane.setFitToWidth(true);
        dialog.getDialogPane().setContent(scrollPane);

        // Boutons
        ButtonType saveButtonType = new ButtonType("Sauvegarder", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        // Gestion de la sauvegarde
        dialog.setResultConverter(buttonType -> {
            if (buttonType == saveButtonType) {
                try {
                    garage.setNom(nomField.getText());
                    garage.setNumeroTelephone(numeroTelephoneField.getText());
                    garage.setCapacite(Integer.parseInt(capaciteField.getText()));
                    garage.setHorairesOuverture(horairesOuvertureField.getText());
                    garage.setServicesDisponibles(Set.of(servicesDisponiblesField.getText().split(",\\s*")));

                    return garage;
                } catch (Exception e) {
                    System.err.println("Erreur dans les données : " + e.getMessage());
                    showAlert("Erreur", "Données invalides", "Vérifiez les données saisies.", Alert.AlertType.ERROR);
                }
            }
            return null;
        });

        dialog.showAndWait().ifPresent(updatedGarage -> {
            tableViewGarages.refresh(); // Rafraîchit la TableView après modification
            System.out.println("Garage mis à jour : " + updatedGarage);
        });
    }

    @FXML
    private void supprimerGarage() {
        Garage selectedGarage = tableViewGarages.getSelectionModel().getSelectedItem();

        if (selectedGarage != null) {
            // Afficher une boîte de confirmation
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation de suppression");
            confirmationAlert.setHeaderText("Êtes-vous sûr de vouloir supprimer ce garage ?");
            confirmationAlert.setContentText("Nom : " + selectedGarage.getNom());

            // Si l'utilisateur confirme, supprimer le garage
            confirmationAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // Suppression du garage de la liste
                    garageList.remove(selectedGarage);

                    // Mise à jour de la TableView
                    tableViewGarages.refresh();

                    // Mise à jour du message dans la status bar
                    statusBar.setText("Garage '" + selectedGarage.getNom() + "' supprimé avec succès !");
                }
            });

        } else {
            // Si aucun garage n'est sélectionné, afficher un message d'erreur avec votre méthode showAlert
            System.out.println("Aucun garage sélectionné");
            showAlert("Erreur", "Aucun garage sélectionné", "Sélectionnez un garage à supprimer !", Alert.AlertType.ERROR);
        }
    }

    // Fonction utilitaire pour afficher une boîte de dialogue d'alerte
    private void showAlert(String title, String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
