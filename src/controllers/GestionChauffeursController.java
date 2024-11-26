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
          Gerant.getInstance().ajouterChauffeur(chauffeur);
          chauffeurList.add(chauffeur);
          tableViewChauffeurs.refresh();
      });


  }


    @FXML
    private void modifierChauffeur() {
        // Vérifier si un chauffeur est sélectionné dans la TableView
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

        // Créer et configurer la boîte de dialogue de modification
        Dialog<Chauffeur> dialog = new Dialog<>();
        dialog.setTitle("Modifier un chauffeur");

        dialog.getDialogPane().setPrefWidth(500);
        dialog.getDialogPane().setPrefHeight(600);

        // Champs de base pour le chauffeur
        TextField nomField = new TextField(chauffeur.getNom());
        TextField prenomField = new TextField(chauffeur.getPrenom());
        TextField cinField = new TextField(String.valueOf(chauffeur.getCin()));
        TextField numeroPermisField = new TextField(chauffeur.getNumPermis());
        DatePicker dateNaissanceField = new DatePicker(
                chauffeur.getDateNaissance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        );
        DatePicker dateExpirationPermisField = new DatePicker(
                chauffeur.getDatePermis().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        );

        TextField rueField = new TextField(chauffeur.getAdresse().rue());
        TextField villeField = new TextField(chauffeur.getAdresse().ville());
        TextField codePostalField = new TextField(chauffeur.getAdresse().codePostal());
        TextField paysField = new TextField(chauffeur.getAdresse().pays());
        TextField telephoneField = new TextField(String.valueOf(chauffeur.getTel()));
        CheckBox disponibleField = new CheckBox("Disponible");
        disponibleField.setSelected(chauffeur.isDisponibilite());

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
        ButtonType saveButtonType = new ButtonType("Sauvegarder", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == saveButtonType) {
                try {
                    // Mise à jour des données
                    chauffeur.setNom(nomField.getText());
                    chauffeur.setPrenom(prenomField.getText());
                    chauffeur.setCin(Double.parseDouble(cinField.getText()));
                    chauffeur.setNumPermis(numeroPermisField.getText());
                    chauffeur.setDateNaissance(java.sql.Date.valueOf(dateNaissanceField.getValue()));
                    chauffeur.setDatePermis(java.sql.Date.valueOf(dateExpirationPermisField.getValue()));
                    chauffeur.setAdresse(new Adresse(
                            rueField.getText(),
                            villeField.getText(),
                            codePostalField.getText(),
                            paysField.getText()
                    ));
                    chauffeur.setTel(Double.parseDouble(telephoneField.getText()));
                    chauffeur.setDisponibilite(disponibleField.isSelected());

                    return chauffeur; // Retourner le chauffeur mis à jour
                } catch (Exception e) {
                    System.err.println("Erreur dans les données : " + e.getMessage());
                }
            }
            return null;
        });

        // Afficher la boîte de dialogue et rafraîchir la TableView si les données sont modifiées
        dialog.showAndWait().ifPresent(updatedChauffeur -> {
            tableViewChauffeurs.refresh();
            Gerant.getInstance().setChauffeurs(new ArrayList<>(chauffeurList));
        });
    }



    private void showEditDialog(Chauffeur chauffeur) {
        System.out.println("onEditChauffeur appelé pour le chauffeur : " + chauffeur.getCin());

        Dialog<Chauffeur> dialog = new Dialog<>();
        dialog.setTitle("Modifier le chauffeur");

        dialog.getDialogPane().setPrefWidth(500);
        dialog.getDialogPane().setPrefHeight(600);

        // Champs du chauffeur
        TextField idField = new TextField(String.valueOf(chauffeur.getCin()));
        idField.setEditable(false); // L'ID ne doit pas être modifié.
        TextField nomField = new TextField(chauffeur.getNom());
        TextField prenomField = new TextField(chauffeur.getPrenom());
        DatePicker dateNaissanceField = new DatePicker(chauffeur.getDateNaissance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        TextField numeroPermisField = new TextField(chauffeur.getNumPermis());
        DatePicker dateValiditePermisField = new DatePicker(chauffeur.getDatePermis().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        TextField experienceField = new TextField(String.valueOf(chauffeur.getExperience()));
        CheckBox disponibleField = new CheckBox("Disponible");
        disponibleField.setSelected(chauffeur.isDisponibilite());

        // Conteneur principal
        VBox form = new VBox(10,
                new Label("ID du chauffeur:"), idField,
                new Label("Nom:"), nomField,
                new Label("Prénom:"), prenomField,
                new Label("Date de naissance:"), dateNaissanceField,
                new Label("Numéro de permis:"), numeroPermisField,
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

        dialog.setResultConverter(buttonType -> {
            if (buttonType == saveButtonType) {
                try {
                    // Mettre à jour les champs du chauffeur
                    chauffeur.setNom(nomField.getText());
                    chauffeur.setPrenom(prenomField.getText());
                    chauffeur.setDateNaissance(java.sql.Date.valueOf(dateNaissanceField.getValue()));
                    chauffeur.setNumPermis(numeroPermisField.getText());
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
            tableViewChauffeurs.refresh(); // Met à jour la TableView
            System.out.println("Chauffeur mis à jour : " + updatedChauffeur);
        });
    }


    private void showAlert(String title, String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }



    @FXML
    private void supprimerChauffeur() {
        Chauffeur selectedChauffeur = tableViewChauffeurs.getSelectionModel().getSelectedItem();

        if (selectedChauffeur != null) {
            chauffeurList.remove(selectedChauffeur);
            statusBar.setText("Chauffeur supprimé avec succès !");;
        } else {
            System.out.println("Aucun chauffeur sélectionné");
            showAlert("Erreur", "Aucun chauffeur sélectionné", "Sélectionnez un chauffeur à supprimer !", Alert.AlertType.ERROR);
        }


    }

    public void clearFields() {
        tfCin.clear();
        tfNom.clear();
        tfPrenom.clear();
        cbDisponibilite.setSelected(false);
    }}

