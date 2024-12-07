/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Personnes.Gerant;
import models.vehicules.Assurance;
import models.vehicules.Vehicule;
import models.vehicules.VoitureCommerciale;
import models.vehicules.VoitureFamiliale;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class GestionAssurancesController {

    /**
     * Initializes the controller class.
     */
  
    @FXML
    private TableView<Assurance> tableAssurance;

    @FXML
    private TableColumn<Assurance, Integer> colIdAssurance;

    @FXML
    private TableColumn<Assurance, String> colNomAssureur;
    
    @FXML
    private TableColumn<Assurance, String> colTypeAssurance;

    @FXML
    private TableColumn<Assurance, Date> colDateDebut;

    @FXML
    private TableColumn<Assurance, Date> colDateFin;

    @FXML
    private TableColumn<Assurance, Double> colCoutAssurance;

  
    // Liste observable des véhicules
    private final ObservableList<Assurance> assurances = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Assurance, Void> colActions;
     @FXML
    public void initialize() {

        // Initialiser les colonnes génériques
        colIdAssurance.setCellValueFactory(new PropertyValueFactory<>("idAssurance"));
        colNomAssureur.setCellValueFactory(new PropertyValueFactory<>("nomAssureur"));
        colTypeAssurance.setCellValueFactory(new PropertyValueFactory<>("typeAssurance"));
        colDateDebut.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        colDateFin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));        
        colCoutAssurance.setCellValueFactory(new PropertyValueFactory<>("coutAssurance"));
          // Charger des données initiales
        assurances.addAll(getAssurancesInitiaux());
        tableAssurance.setItems(assurances);

      // Ajouter des boutons pour la colonne d'actions
        colActions.setCellFactory(param -> new TableCell<>() {
            private final HBox actionBox = new HBox(10); // Conteneur horizontal pour les icônes
            private final Button editButton = new Button();
            private final Button deleteButton = new Button();

            {
                editButton.setText("Éditer");
                deleteButton.setText("Supprimer");

                editButton.setStyle("-fx-background-color: #F3C623; -fx-text-fill: white; -fx-font-weight: bold;");
                deleteButton.setStyle("-fx-background-color: #B8001F; -fx-text-fill: white; -fx-font-weight: bold;");

                // Ajout des actions
                editButton.setOnAction(event -> {
                    System.out.println("edit !!");
                    Assurance assurance = getTableView().getItems().get(getIndex());
                    System.out.println("Id assurance "+ assurance.getIdAssurance());
                    try {
                        onEditAssurance(assurance);
                    } catch (Exception e) {
                        System.err.println("Erreur lors de l'édition : " + e.getMessage());
                        e.printStackTrace();
                    }
                });

                deleteButton.setOnAction(event -> {
                    Assurance assurance = getTableView().getItems().get(getIndex());
                    onDeleteAssurance(assurance);
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
    public List<Assurance> getAssurancesInitiaux() {
        // Récupère les véhicules stockés dans Gerant
        return Gerant.getInstance().getAssurances();
       
    }


    private void onEditAssurance(Assurance assurance) {
        System.out.println("onEditAssurance appelé pour l'assurance : " + assurance.getIdAssurance());

        // Configuration de la boîte de dialogue
        Dialog<Assurance> dialog = new Dialog<>();
        dialog.setTitle("Modifier l'assurance");
        dialog.getDialogPane().setPrefWidth(600);
        dialog.getDialogPane().setPrefHeight(700);

        // Champs communs
        TextField idAssuranceField = new TextField(String.valueOf(assurance.getIdAssurance()));
        TextField nomAssureurField = new TextField(assurance.getNomAssureur());
        TextField typeAssuranceField = new TextField(assurance.getTypeAssurance());

        // Utilisation de DatePicker pour les dates de début et de fin
        DatePicker dateDebutField = new DatePicker(toLocalDate(assurance.getDateDebut()));
        dateDebutField.setPromptText("Date de début");

        DatePicker dateFinField = new DatePicker(toLocalDate(assurance.getDateFin()));
        dateFinField.setPromptText("Date de fin");

        TextField coutAssuranceField = new TextField(String.valueOf(assurance.getCoutAssurance()));

        // Conteneur pour le formulaire
        VBox form = new VBox(10,
            new Label("Id Assurance :"), idAssuranceField,
            new Label("Nom Assureur :"), nomAssureurField,
            new Label("Type Assurance :"), typeAssuranceField,
            new Label("Date de début :"), dateDebutField,
            new Label("Date de fin :"), dateFinField,
            new Label("Coût de l'assurance :"), coutAssuranceField
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
                    // Mise à jour des champs de l'objet Assurance
                    assurance.setIdAssurance(Integer.parseInt(idAssuranceField.getText()));
                    assurance.setNomAssureur(nomAssureurField.getText());
                    assurance.setTypeAssurance(typeAssuranceField.getText());

                    // Conversion de LocalDate en Date pour dateDebut
                    LocalDate localDateDebut = dateDebutField.getValue();
                    if (localDateDebut != null) {
                        Date dateDebut = Date.from(localDateDebut.atStartOfDay(ZoneId.systemDefault()).toInstant());
                        assurance.setDateDebut(dateDebut);
                    }

                    // Conversion de LocalDate en Date pour dateFin
                    LocalDate localDateFin = dateFinField.getValue();
                    if (localDateFin != null) {
                        Date dateFin = Date.from(localDateFin.atStartOfDay(ZoneId.systemDefault()).toInstant());
                        assurance.setDateFin(dateFin);
                    }

                    assurance.setCoutAssurance(Double.parseDouble(coutAssuranceField.getText()));

                    return assurance; // Retourne l'objet modifié
                } catch (Exception e) {
                    System.err.println("Erreur dans les données : " + e.getMessage());
                    showErrorAlert("Erreur", "Les données saisies sont incorrectes.");
                }
            }
            return null;
        });

        // Affiche la boîte de dialogue et met à jour la table si l'utilisateur a cliqué sur Sauvegarder
        dialog.showAndWait().ifPresent(updatedAssurance -> {
            tableAssurance.refresh(); // Met à jour la TableView
            Gerant.getInstance().setAssurances(new ArrayList<>(assurances)); // Met à jour la liste des assurances dans le gestionnaire
        });
    }

    // Méthode pour convertir java.util.Date en LocalDate
    private LocalDate toLocalDate(Date date) {
        if (date != null) {
            return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }
        return null;
    }

    // Affichage d'une alerte en cas d'erreur
    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    
    // Méthode pour gérer la suppression d'un véhicule
    private void onDeleteAssurance(Assurance assurance) {
        Gerant gerant = Gerant.getInstance(); // Obtenez l'instance unique de Gerant

        boolean supprimé = gerant.supprimerAssurance(assurance);
        if (supprimé) {
            System.out.println("Assurance supprimé : " + assurance.getIdAssurance());
            assurances.remove(assurance); // Met à jour la liste observable si utilisée dans l'interface
            tableAssurance.refresh(); // Rafraîchit la table
        } else {
            System.out.println("Échec de la suppression : Assurance introuvable.");
        }
    }


    public void onAjouterAssuranceClick(){
        Dialog<Assurance> dialog = new Dialog<>();
        dialog.setTitle("Ajouter une assurance");

        dialog.getDialogPane().setPrefWidth(600);
        dialog.getDialogPane().setPrefHeight(700);
        TextField idAssuranceField = new TextField();
        idAssuranceField.setPromptText("Id assurance");

        TextField nomAssuranceField = new TextField();
        nomAssuranceField.setPromptText("Nom assurance");

        TextField typeAssuranceField = new TextField();
        typeAssuranceField.setPromptText("Type assurance");

        DatePicker dateDebutField = new DatePicker();
        dateDebutField.setPromptText("Date de début");

        DatePicker dateFinField = new DatePicker();
        dateFinField.setPromptText("Date de fin");

        TextField coutAssuranceField = new TextField();
        coutAssuranceField.setPromptText("Cout");
        
        // Conteneur pour champs spécifiques
       
        // Formulaire complet
        VBox form = new VBox(10,
            new Label("Id Assurance:"), idAssuranceField,
            new Label("Nom Assurance:"), nomAssuranceField,
            new Label("Type Assurance:"), typeAssuranceField,
            new Label("Date début:"), dateDebutField,
            new Label("Date fin:"), dateFinField,
            new Label("Cout:"), coutAssuranceField
        );

        dialog.getDialogPane().setContent(form);

         // Ajout de la barre de défilement
        ScrollPane scrollPane = new ScrollPane(form);
        scrollPane.setFitToWidth(true); // Adapter à la largeur du dialogue
        scrollPane.setPrefHeight(400); // Hauteur visible avant le défilement
        scrollPane.setPrefWidth(500); // Largeur visible

        dialog.getDialogPane().setContent(scrollPane);
        // Boutons
        ButtonType ajouterButtonType = new ButtonType("Ajouter", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(ajouterButtonType, ButtonType.CANCEL);

        // Gestion de l'ajout
        dialog.setResultConverter(buttonType -> {
            if (buttonType == ajouterButtonType) {
                try {
                    // Champs communs
                    int idAssurance = Integer.parseInt(idAssuranceField.getText());
                    String nomAssurance = nomAssuranceField.getText();
                    String typeAssurance = typeAssuranceField.getText();
                    
                    LocalDate localDateDebut = dateDebutField.getValue();
                    Date dateDebut = Date.from(localDateDebut.atStartOfDay(ZoneId.systemDefault()).toInstant());

                    LocalDate localDateFin = dateFinField.getValue();
                    Date dateFin = Date.from(localDateFin.atStartOfDay(ZoneId.systemDefault()).toInstant());
                    double coutAssurance = Double.parseDouble(coutAssuranceField.getText());

                      return new Assurance(idAssurance, nomAssurance, typeAssurance, 
                                 dateDebut, dateFin, coutAssurance);
                    
                } catch (Exception e) {
                    System.err.println("Erreur dans les données : " + e.getMessage());
                    showErrorAlert("Erreur", "Les données saisies sont incorrectes.");
                }
            }
            return null;
        });
        System.out.println("aaaaaaaaaaa");


        // Afficher la boîte de dialogue
        dialog.showAndWait().ifPresent(assurance -> {
            System.out.println(assurance.toString());
            Gerant.getInstance().ajouterAssurance(assurance);
            assurances.add(assurance);
            tableAssurance.refresh();
        });

        }
}
