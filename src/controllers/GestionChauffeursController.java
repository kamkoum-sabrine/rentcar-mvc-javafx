package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import models.Personnes.Adresse;
import models.Personnes.Chauffeur;
import models.Personnes.Gerant;
import models.Personnes.Permis;
import models.vehicules.Vehicule;

import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;


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


    private  ObservableList<Chauffeur> chauffeurList= FXCollections.observableArrayList();
    @FXML
    public void initialize() {
        colCin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colDisponibilite.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));

        chauffeurList = FXCollections.observableArrayList();
        tableViewChauffeurs.setItems(chauffeurList);
    }

  @FXML
  private void ajouterChauffeur() {
      Dialog<Chauffeur> dialog = new Dialog<>();
      dialog.setTitle("Ajouter un chauffeur");

      dialog.getDialogPane().setPrefWidth(600);
      dialog.getDialogPane().setPrefHeight(700);

      // Champs de base pour le chauffeur
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

      DatePicker dateCinField = new DatePicker();
      dateCinField.setPromptText("Date de délivrance du CIN");

      TextField lieuCinField = new TextField();
      lieuCinField.setPromptText("Lieu de délivrance du CIN");

      // Adresse
      TextField rueField = new TextField();
      rueField.setPromptText("Rue");

      TextField villeField = new TextField();
      villeField.setPromptText("Ville");

      TextField codePostalField = new TextField();
      codePostalField.setPromptText("Code Postal");

      TextField paysField = new TextField();
      paysField.setPromptText("Pays");

      // Champs spécifiques au chauffeur
      TextField numeroPermisField = new TextField();
      numeroPermisField.setPromptText("Numéro de permis");

      ComboBox<Permis> categoriePermisField = new ComboBox<>();
      categoriePermisField.getItems().addAll(Permis.values());
      categoriePermisField.setPromptText("Catégorie de permis");

      DatePicker datePermisField = new DatePicker();
      datePermisField.setPromptText("Date de délivrance du permis");

      TextField lieuPermisField = new TextField();
      lieuPermisField.setPromptText("Lieu de délivrance du permis");

      TextField experienceField = new TextField();
      experienceField.setPromptText("Années d'expérience");

      TextField prixJourField = new TextField();
      prixJourField.setPromptText("Prix par jour");

      CheckBox disponibiliteField = new CheckBox("Disponible");

      // Formulaire complet
      VBox form = new VBox(10,
              new Label("Nom:"), nomField,
              new Label("Prénom:"), prenomField,
              new Label("CIN:"), cinField,
              new Label("Numéro de téléphone:"), telephoneField,
              new Label("Email:"), emailField,
              new Label("Date de naissance:"), dateNaissanceField,
              new Label("Nationalité:"), nationaliteField,
              new Label("Date de délivrance du CIN:"), dateCinField,
              new Label("Lieu de délivrance du CIN:"), lieuCinField,
              new Label("Adresse:"),
              new Label("Rue:"), rueField,
              new Label("Ville:"), villeField,
              new Label("Code Postal:"), codePostalField,
              new Label("Pays:"), paysField,
              new Label("Numéro de permis:"), numeroPermisField,
              new Label("Catégorie de permis:"), categoriePermisField,
              new Label("Date de délivrance du permis:"), datePermisField,
              new Label("Lieu de délivrance du permis:"), lieuPermisField,
              new Label("Années d'expérience:"), experienceField,
              new Label("Prix par jour:"), prixJourField,
              disponibiliteField
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
                  // Récupération et validation des données
                  String nom = nomField.getText();
                  String prenom = prenomField.getText();
                  double cin = Double.parseDouble(cinField.getText());
                  double telephone = Double.parseDouble(telephoneField.getText());
                  String email = emailField.getText();
                  LocalDate dateNaissance = dateNaissanceField.getValue();
                  String nationalite = nationaliteField.getText();
                  LocalDate dateCin = dateCinField.getValue();
                  String lieuCin = lieuCinField.getText();

                  Adresse adresse = new Adresse(rueField.getText(), villeField.getText(), codePostalField.getText(), paysField.getText());

                  String numeroPermis = numeroPermisField.getText();
                  Permis categoriePermis = categoriePermisField.getValue();
                  LocalDate datePermis = datePermisField.getValue();
                  String lieuPermis = lieuPermisField.getText();
                  int experience = Integer.parseInt(experienceField.getText());
                  double prixJour = Double.parseDouble(prixJourField.getText());
                  boolean disponibilite = disponibiliteField.isSelected();

                  // Validation des champs obligatoires
                  if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || nationalite.isEmpty() ||
                          adresse.rue().isEmpty() || adresse.ville().isEmpty() ||
                          categoriePermis == null || datePermis == null || lieuPermis.isEmpty()) {
                      throw new IllegalArgumentException("Tous les champs obligatoires doivent être remplis.");
                  }

                  // Création de l'objet Chauffeur
                  return new Chauffeur(
                          disponibilite,
                          experience,
                          numeroPermis,
                          java.sql.Date.valueOf(datePermis),
                          lieuPermis,
                          categoriePermis,
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
                  System.err.println("Erreur lors de l'ajout du chauffeur : " + e.getMessage());
              }
          }
          return null;
      });

      // Affichage de la boîte de dialogue
      dialog.showAndWait().ifPresent(chauffeur -> {
          Gerant.getInstance().ajouterChauffeur(chauffeur);
          chauffeurList.add(chauffeur);
          tableViewChauffeurs.refresh();
      });
  }


    public void modifierchauffeur() {
        Chauffeur chauffeur = tableViewChauffeurs.getSelectionModel().getSelectedItem();
        if (chauffeur == null) {
            // Afficher un message d'erreur si aucun chauffeur n'est sélectionné
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucun chauffeur sélectionné");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un chauffeur à modifier.");
            alert.showAndWait();
            return;
        }

        System.out.println("onEditChauffeur appelé pour le chauffeur : " + chauffeur.getCin());

        Dialog<Chauffeur> dialog = new Dialog<>();
        dialog.setTitle("Modifier le chauffeur");

        dialog.getDialogPane().setPrefWidth(400);
        dialog.getDialogPane().setPrefHeight(300);

        // Champs spécifiques à modifier
        DatePicker dateValiditePermisField = new DatePicker(chauffeur.getDatePermis().toLocalDate());
        TextField experienceField = new TextField(String.valueOf(chauffeur.getExperience()));
        CheckBox disponibleField = new CheckBox("Disponible");
        disponibleField.setSelected(chauffeur.isDisponibilite());

        // Conteneur pour les champs
        VBox form = new VBox(10,
                new Label("Date de validité du permis:"), dateValiditePermisField,
                new Label("Années d'expérience:"), experienceField,
                disponibleField
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
                    // Validation et mise à jour des champs modifiés
                    chauffeur.setDatePermis(java.sql.Date.valueOf(dateValiditePermisField.getValue()));
                    chauffeur.setExperience(Integer.parseInt(experienceField.getText()));
                    chauffeur.setDisponibilite(disponibleField.isSelected());

                    return chauffeur;
                } catch (Exception e) {
                    System.err.println("Erreur dans les données : " + e.getMessage());
                    showAlert("Erreur", "Données invalides", "Vérifiez les données saisies.", Alert.AlertType.ERROR);
                }
            }
            return null;
        });

        dialog.showAndWait().ifPresent(updatedChauffeur -> {
            tableViewChauffeurs.refresh(); // Rafraîchit la TableView après modification
            System.out.println("Chauffeur mis à jour : " + updatedChauffeur);
        });
    }








    // Fonction utilitaire pour afficher une boîte de dialogue d'alerte
    private void showAlert(String title, String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }


    @FXML
    private void supprimerChauffeur() {
        // Récupérer le chauffeur sélectionné dans la TableView
        Chauffeur selectedChauffeur = tableViewChauffeurs.getSelectionModel().getSelectedItem();

        if (selectedChauffeur != null) {
            // Afficher une boîte de confirmation
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation de suppression");
            confirmationAlert.setHeaderText("Êtes-vous sûr de vouloir supprimer ce chauffeur ?");
            confirmationAlert.setContentText("Nom : " + selectedChauffeur.getNom() + "\nPrénom : " + selectedChauffeur.getPrenom());

            // Si l'utilisateur confirme, supprimer le chauffeur
            confirmationAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // Suppression du chauffeur de la liste
                    chauffeurList.remove(selectedChauffeur);

                    // Mise à jour de la TableView
                    tableViewChauffeurs.refresh();

                    // Mise à jour du message dans la status bar
                    statusBar.setText("Chauffeur '" + selectedChauffeur.getNom() + "' supprimé avec succès !");
                }
            });

        } else {
            // Si aucun chauffeur n'est sélectionné, afficher un message d'erreur avec votre méthode showAlert
            System.out.println("Aucun chauffeur sélectionné");
            showAlert("Erreur", "Aucun chauffeur sélectionné", "Sélectionnez un chauffeur à supprimer !", Alert.AlertType.ERROR);
        }
    }}




