package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import models.Personnes.Adresse;
import models.Personnes.Chauffeur;
import models.Personnes.Gerant;
import models.Personnes.Permis;

import java.awt.event.ActionEvent;
import java.time.LocalDate;

public class GestionChauffeursController {
    @FXML
    private TableView<Chauffeur> tableViewChauffeurs;

    @FXML
    private TableColumn<Chauffeur, Double> colCin;

    @FXML
    private TableColumn<Chauffeur, String> colNom;

    @FXML
    private TableColumn<Chauffeur, String> colPrenom;

    @FXML
    private TableColumn<Chauffeur, Boolean> colDisponibilite;

    @FXML
    private TextField tfNom;

    @FXML
    private TextField tfPrenom;

    @FXML
    private TextField tfCin;

    @FXML
    private CheckBox cbDisponibilite;

    @FXML
    private Label statusBar;

    private ObservableList<Chauffeur> chauffeurList;

  /*  @FXML
    public void initialize() {
        chauffeurList = FXCollections.observableArrayList();

        // Liaison des colonnes
        colCin.setCellValueFactory(data -> data.getValue().cinProperty().asObject());
        colNom.setCellValueFactory(data -> data.getValue().nomProperty());
        colPrenom.setCellValueFactory(data -> data.getValue().prenomProperty());
        colDisponibilite.setCellValueFactory(data -> data.getValue().isDisponibilite().asObject());

        tableViewChauffeurs.setItems(chauffeurList);
    }*/

  @FXML
  private void ajouterChauffeur() {
      Dialog<Chauffeur> dialog = new Dialog<>();
      dialog.setTitle("Ajouter un chauffeur");

      dialog.getDialogPane().setPrefWidth(500);
      dialog.getDialogPane().setPrefHeight(600);

      // Champs de base pour le chauffeur
      TextField nomField = new TextField();
      nomField.setPromptText("Nom");

      TextField prenomField = new TextField();
      prenomField.setPromptText("Prénom");

      TextField cinField = new TextField();
      cinField.setPromptText("CIN");

      TextField numeroPermisField = new TextField();
      numeroPermisField.setPromptText("Numéro de permis");

      DatePicker dateNaissanceField = new DatePicker();
      dateNaissanceField.setPromptText("Date de naissance");

      DatePicker dateExpirationPermisField = new DatePicker();
      dateExpirationPermisField.setPromptText("Date d'expiration du permis");

      TextField rueField = new TextField();
      rueField.setPromptText("Rue");

      TextField villeField = new TextField();
      villeField.setPromptText("Ville");

      TextField codePostalField = new TextField();
      codePostalField.setPromptText("Code Postal");

      TextField paysField = new TextField();
      paysField.setPromptText("Pays");

      TextField telephoneField = new TextField();
      telephoneField.setPromptText("Numéro de téléphone");

      CheckBox disponibleField = new CheckBox("Disponible");

      // Formulaire complet
      VBox form = new VBox(10,
              new Label("Nom:"), nomField,
              new Label("Prénom:"), prenomField,
              new Label("CIN:"), cinField,
              new Label("Numéro de permis:"), numeroPermisField,
              new Label("Date de naissance:"), dateNaissanceField,
              new Label("Date d'expiration du permis:"), dateExpirationPermisField,
              new Label("Rue:"), rueField,
              new Label("Ville:"), villeField,
              new Label("Code Postal:"), codePostalField,
              new Label("Pays:"), paysField,
              new Label("Numéro de téléphone:"), telephoneField,
              disponibleField
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
                  // Récupération des données
                  String nom = nomField.getText();
                  String prenom = prenomField.getText();
                  String cin = cinField.getText();
                  String numeroPermis = numeroPermisField.getText();
                  LocalDate dateNaissance = dateNaissanceField.getValue();
                  LocalDate dateExpirationPermis = dateExpirationPermisField.getValue();
                  String rue = rueField.getText();
                  String ville = villeField.getText();
                  String codePostal = codePostalField.getText();
                  String pays = paysField.getText();
                  String telephone = telephoneField.getText();
                  boolean disponible = disponibleField.isSelected();

                  // Validation des données
                  if (nom.isEmpty() || prenom.isEmpty() || cin.isEmpty() || numeroPermis.isEmpty()
                          || dateNaissance == null || dateExpirationPermis == null || rue.isEmpty() || ville.isEmpty()
                          || codePostal.isEmpty() || pays.isEmpty() || telephone.isEmpty()) {
                      throw new IllegalArgumentException("Tous les champs doivent être remplis.");
                  }

                  // Créer l'objet Adresse
                  Adresse adresse = new Adresse(rue, ville, codePostal, pays);

                  // Créer l'objet Chauffeur
                  Chauffeur chauffeur = new Chauffeur(
                          disponible, // Disponibilité
                          0, // Expérience (initiale à 0, ou selon votre logique)
                          numeroPermis,
                          java.sql.Date.valueOf(dateExpirationPermis), // Convert LocalDate to SQL Date if needed
                          "Lieu permis", // Provide a value for lieuPermis
                          Permis.A, // Correct usage of the enum value
                          Double.parseDouble(cin),
                          nom, prenom,
                          Double.parseDouble(telephone),
                          "email@domain.com", // Placeholder for email
                          adresse,
                          java.sql.Date.valueOf(dateNaissance), // Convert LocalDate to SQL Date if needed
                          "Nationalité", // Placeholder for nationality
                          java.sql.Date.valueOf(dateNaissance), // Placeholder for CIN date (adjust accordingly)
                          "Lieu CIN" // Placeholder for CIN location
                  );

                  // Return the Chauffeur object for further processing (e.g., saving to database, list, etc.)
                  return chauffeur;

              } catch (Exception e) {
                  System.err.println("Erreur dans les données : " + e.getMessage());
              }
          }
          return null;
      });

      // Afficher la boîte de dialogue
      dialog.showAndWait().ifPresent(chauffeur -> {
          // Process the added chauffeur (e.g., save it to a list or database)
          System.out.println("Chauffeur ajouté: " + chauffeur);
      });
  }





    @FXML
    private void modifierChauffeur() {
        Chauffeur selectedChauffeur = tableViewChauffeurs.getSelectionModel().getSelectedItem();
        if (selectedChauffeur == null) {
            statusBar.setText("Sélectionnez un chauffeur à modifier !");
            return;
        }

        String nom = tfNom.getText();
        String prenom = tfPrenom.getText();
        boolean disponibilite = cbDisponibilite.isSelected();

        selectedChauffeur.setNom(nom);
        selectedChauffeur.setPrenom(prenom);
        selectedChauffeur.setDisponibilite(disponibilite);

        tableViewChauffeurs.refresh();
        clearFields();
        statusBar.setText("Chauffeur modifié avec succès !");
    }

    @FXML
    private void supprimerChauffeur() {
        Chauffeur selectedChauffeur = tableViewChauffeurs.getSelectionModel().getSelectedItem();
        if (selectedChauffeur == null) {
            statusBar.setText("Sélectionnez un chauffeur à supprimer !");
            return;
        }

        chauffeurList.remove(selectedChauffeur);
        statusBar.setText("Chauffeur supprimé avec succès !");
    }

    private void clearFields() {
        tfCin.clear();
        tfNom.clear();
        tfPrenom.clear();
        cbDisponibilite.setSelected(false);
    }
}
