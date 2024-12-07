/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
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
import models.Facture.Remise;
import models.Personnes.Gerant;
import models.vehicules.Assurance;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class GestionRemisesController implements Initializable {

    @FXML
    private TableView<Remise> tableRemise;

    @FXML
    private TableColumn<Remise, Integer> colIdRemise;

    @FXML
    private TableColumn<Remise, String> colDescription;
    
    @FXML
    private TableColumn<Remise, Double> colPourcentageRemise;

    
  
    private final ObservableList<Remise> remises = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Remise, Void> colActions;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        colIdRemise.setCellValueFactory(new PropertyValueFactory<>("idRemise"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPourcentageRemise.setCellValueFactory(new PropertyValueFactory<>("pourcentageRemise"));
        
          // Charger des données initiales
        remises.addAll(getRemisesInitiaux());
        tableRemise.setItems(remises);

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
                    Remise remise = getTableView().getItems().get(getIndex());
                    System.out.println("Id remise "+ remise.getIdRemise());
                    try {
                        onEditRemise(remise);
                    } catch (Exception e) {
                        System.err.println("Erreur lors de l'édition : " + e.getMessage());
                        e.printStackTrace();
                    }
                });

                deleteButton.setOnAction(event -> {
                    Remise remise = getTableView().getItems().get(getIndex());
                    onDeleteRemise(remise);
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
    
     public List<Remise> getRemisesInitiaux() {
        // Récupère les véhicules stockés dans Gerant
        return Gerant.getInstance().getRemises();
       
    }


    private void onEditRemise(Remise remise) {
        System.out.println("onEditRemise appelé pour la remise : " + remise.getIdRemise());

        // Configuration de la boîte de dialogue
        Dialog<Remise> dialog = new Dialog<>();
        dialog.setTitle("Modifier la remise");
        dialog.getDialogPane().setPrefWidth(400);
        dialog.getDialogPane().setPrefHeight(500);

        // Champs communs
        TextField idRemiseField = new TextField(String.valueOf(remise.getIdRemise()));
        TextField descriptionField = new TextField(remise.getDescription());
        TextField pourcentageRemiseField = new TextField(String.valueOf(remise.getPourcentageRemise()));
        
        // Conteneur pour le formulaire
        VBox form = new VBox(10,
            new Label("Id Remise :"), idRemiseField,
            new Label("Description :"), descriptionField,
            new Label("Pourcentage Remise :"), pourcentageRemiseField           
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
                    // Mise à jour des champs de l'objet Remise
                    remise.setIdRemise(Integer.parseInt(idRemiseField.getText()));
                    remise.setDescription(descriptionField.getText());
                    remise.setPourcentageRemise(Double.parseDouble(pourcentageRemiseField.getText()));

                    return remise; // Retourne l'objet modifié
                } catch (Exception e) {
                    System.err.println("Erreur dans les données : " + e.getMessage());
                    showErrorAlert("Erreur", "Les données saisies sont incorrectes.");
                }
            }
            return null;
        });

        // Affiche la boîte de dialogue et met à jour la table si l'utilisateur a cliqué sur Sauvegarder
        dialog.showAndWait().ifPresent(updatedRemise -> {
            tableRemise.refresh(); // Met à jour la TableView
            Gerant.getInstance().setRemises(new ArrayList<>(remises)); // Met à jour la liste des remises dans le gestionnaire
        });
    }
    // Affichage d'une alerte en cas d'erreur
    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    
    // Méthode pour gérer la suppression d'une remise
    private void onDeleteRemise(Remise remise) {
        Gerant gerant = Gerant.getInstance(); // Obtenez l'instance unique de Gerant

        boolean supprimé = gerant.supprimerRemise(remise);
        if (supprimé) {
            System.out.println("Remise supprimée : " + remise.getIdRemise());
            remises.remove(remise); // Met à jour la liste observable si utilisée dans l'interface
            tableRemise.refresh(); // Rafraîchit la table
        } else {
            System.out.println("Échec de la suppression : Remise introuvable.");
        }
    }

     public void onAjouterRemiseClick(){
        Dialog<Remise> dialog = new Dialog<>();
        dialog.setTitle("Ajouter une remise");

        dialog.getDialogPane().setPrefWidth(400);
        dialog.getDialogPane().setPrefHeight(500);
        TextField idRemiseField = new TextField();
        idRemiseField.setPromptText("Id remise");

        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Description");

        TextField pourcentageRemiseField = new TextField();
        pourcentageRemiseField.setPromptText("Pourcentage remise");

        
      
        // Formulaire complet
        VBox form = new VBox(10,
            new Label("Id Remise :"), idRemiseField,
            new Label("Description :"), descriptionField,
            new Label("Pourcentage remise :"), pourcentageRemiseField
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
                    int idRemise = Integer.parseInt(idRemiseField.getText());
                    String description = descriptionField.getText();
                    double pourcentageRemise = Double.parseDouble(pourcentageRemiseField.getText());
                    return new Remise(idRemise, description, pourcentageRemise, false);
                    
                } catch (Exception e) {
                    System.err.println("Erreur dans les données : " + e.getMessage());
                    showErrorAlert("Erreur", "Les données saisies sont incorrectes.");
                }
            }
            return null;
        });
        System.out.println("aaaaaaaaaaa");


        // Afficher la boîte de dialogue
        dialog.showAndWait().ifPresent(remise -> {
            System.out.println(remise.toString());
            Gerant.getInstance().ajouterRemise(remise);
            remises.add(remise);
            tableRemise.refresh();
        });

        }
    
}
