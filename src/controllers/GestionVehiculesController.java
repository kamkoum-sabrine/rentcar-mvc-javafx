/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;

import models.Personnes.Gerant;
import models.vehicules.Vehicule;
import models.vehicules.VoitureCommerciale;
import models.vehicules.VoitureFamiliale;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class GestionVehiculesController  {

    /**
     * Initializes the controller class.
     */
    @FXML
private TableView<Vehicule> tableVoitures;

@FXML
private TableColumn<Vehicule, String> colMatricule;

@FXML
private TableColumn<Vehicule, String> colMarque;

@FXML
private TableColumn<Vehicule, String> colModele;

@FXML
private TableColumn<Vehicule, String> colType;

@FXML
private TableColumn<Vehicule, Double> colCoutParJour;

// Colonnes spécifiques aux VoitureFamiliale
@FXML
private TableColumn<VoitureFamiliale, Integer> colNombrePlaces;

@FXML
private TableColumn<VoitureFamiliale, Boolean> colSiegeBebe;
@FXML
private TableColumn<VoitureCommerciale, Integer> colCapaciteCharge;

@FXML
private TableColumn<VoitureFamiliale, Boolean> colGrandCoffre;

@FXML
private TableColumn<VoitureCommerciale, Boolean> colToitOuvrant;

@FXML
private TableColumn<VoitureCommerciale, Boolean> colCameraRecul;

// Liste observable des véhicules
private final ObservableList<Vehicule> vehicules = FXCollections.observableArrayList();

@FXML
private TableColumn<Vehicule, Void> colActions;

@FXML
public void initialize() {
    // Initialiser les colonnes génériques
    colMatricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
    colMarque.setCellValueFactory(new PropertyValueFactory<>("marque"));
    colModele.setCellValueFactory(new PropertyValueFactory<>("modele"));
    colType.setCellValueFactory(new PropertyValueFactory<>("type"));
    colCoutParJour.setCellValueFactory(new PropertyValueFactory<>("coutParJour"));

    // Configurer les colonnes spécifiques
    colNombrePlaces.setCellValueFactory(cellData -> {
        Vehicule vehicule = cellData.getValue();
        if (vehicule instanceof VoitureFamiliale vf) {
            return new SimpleIntegerProperty(vf.getNombrePlaces()).asObject();
        }
        return null; // Pas applicable pour les autres types
    });

    colSiegeBebe.setCellValueFactory(cellData -> {
        Vehicule vehicule = cellData.getValue();
        if (vehicule instanceof VoitureFamiliale vf) {
            return new SimpleBooleanProperty(vf.getSiegeBebeDisponible());
        }
        return null; // Pas applicable pour les autres types
    });
    
     colGrandCoffre.setCellValueFactory(cellData -> {
        Vehicule vehicule = cellData.getValue();
        if (vehicule instanceof VoitureFamiliale vf) {
            return new SimpleBooleanProperty(vf.getGrandCoffre());
        }
        return null; // Pas applicable pour les autres types
    });
    
     colCapaciteCharge.setCellValueFactory(cellData -> {
        Vehicule vehicule = cellData.getValue();
        if (vehicule instanceof VoitureCommerciale vc) {
            return new SimpleIntegerProperty(vc.getCapaciteCharge()).asObject();
        }
        return null; // Pas applicable pour les autres types
    });
     
     colToitOuvrant.setCellValueFactory(cellData -> {
        Vehicule vehicule = cellData.getValue();
        if (vehicule instanceof VoitureCommerciale vc) {
            return new SimpleBooleanProperty(vc.getToitOuvrant());
        }
        return null; // Pas applicable pour les autres types
    });
    
     colCameraRecul.setCellValueFactory(cellData -> {
        Vehicule vehicule = cellData.getValue();
        if (vehicule instanceof VoitureCommerciale vc) {
            return new SimpleBooleanProperty(vc.getCameraRecul());
        }
        return null; // Pas applicable pour les autres types
    });

    // Charger des données initiales
    vehicules.addAll(getVehiculesInitiaux());
    tableVoitures.setItems(vehicules);
    
  // Ajouter des boutons pour la colonne d'actions
    colActions.setCellFactory(param -> new TableCell<>() {
        private final HBox actionBox = new HBox(10); // Conteneur horizontal pour les icônes
        private final Button editButton = new Button();
        private final Button deleteButton = new Button();

        {
            // Configure les boutons avec des icônes
            editButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("src/ressources/images/edit.png"))));
            deleteButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("src/ressources/images/trash.png"))));

            // Ajout des actions
            editButton.setOnAction(event -> {
                Vehicule vehicule = getTableView().getItems().get(getIndex());
                onEditVehicule(vehicule);
            });

            deleteButton.setOnAction(event -> {
                Vehicule vehicule = getTableView().getItems().get(getIndex());
                onDeleteVehicule(vehicule);
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

// Méthode pour gérer l'édition
private void onEditVehicule(Vehicule vehicule) {
    // Logique pour éditer un véhicule
    System.out.println("Édition de : " + vehicule.getMatricule());
}

// Méthode pour gérer la suppression
private void onDeleteVehicule(Vehicule vehicule) {
    // Logique pour supprimer un véhicule
        System.out.println("Suppression de : " + vehicule.getMatricule());

    vehicules.remove(vehicule); // Supposons que "vehicules" est votre liste observable
    tableVoitures.refresh();
}


private List<Vehicule> getVehiculesInitiaux() {
    List<Vehicule> list = new ArrayList<>();
    list.add(new VoitureFamiliale("1234AB", "Toyota", "Corolla", "90cv", "Essence", 2020, 15000.0,
            true, true, false, true, true, true, 5, true, true, "Voiture Familiale", 50.0, null));
    list.add(new VoitureCommerciale("5678CD", "Renault", "Kangoo", "110cv", "Diesel", 2019, 30000.0,
            true, true, true, true, true, true, 1000, false, true, "Voiture Commerciale", 70.0, null));
    list.add(new Vehicule("9101EF", "Ford", "Focus", "100cv", "Essence", 2018, 20000.0,
            true, true, true, true, true, true, "Vehicule", 60.0, null));
    return list;
}


  @FXML
public void onAjouterVoitureClick(ActionEvent event) {
    Dialog<Vehicule> dialog = new Dialog<>();
    dialog.setTitle("Ajouter une voiture");

    dialog.getDialogPane().setPrefWidth(600);
    dialog.getDialogPane().setPrefHeight(700);

    // Type de véhicule
    ComboBox<String> typeBox = new ComboBox<>();
    typeBox.getItems().addAll("Voiture Familiale", "Voiture Commerciale");
    typeBox.setPromptText("Type de véhicule");

    // Champs communs
    TextField matriculeField = new TextField();
    matriculeField.setPromptText("Matricule");

    TextField marqueField = new TextField();
    marqueField.setPromptText("Marque");

    TextField modeleField = new TextField();
    modeleField.setPromptText("Modèle");

    TextField puissanceField = new TextField();
    puissanceField.setPromptText("Puissance");

    TextField carburantField = new TextField();
    carburantField.setPromptText("Carburant");

    TextField anneeField = new TextField();
    anneeField.setPromptText("Année du modèle");

    TextField kilometrageField = new TextField();
    kilometrageField.setPromptText("Kilométrage");

    TextField coutParJourField = new TextField();
    coutParJourField.setPromptText("Coût par jour");

    // Équipements
    CheckBox roueSecoursField = new CheckBox("Roue de secours");
    CheckBox cricOutilsField = new CheckBox("Cric et outils");
    CheckBox radioAntenneField = new CheckBox("Radio et antenne");
    CheckBox enjoliversField = new CheckBox("Enjoliveurs");
    CheckBox retroviseursField = new CheckBox("Rétroviseurs");
    CheckBox climatiseurMarcheField = new CheckBox("Climatiseur fonctionnel");

    // Conteneur pour champs spécifiques
    VBox champsSpecifiques = new VBox(10);

    // Listener pour ajuster les champs spécifiques
    typeBox.valueProperty().addListener((obs, oldValue, newValue) -> {
        champsSpecifiques.getChildren().clear(); // Réinitialiser les champs spécifiques

        if ("Voiture Familiale".equals(newValue)) {
            TextField nombrePlacesField = new TextField();
            nombrePlacesField.setPromptText("Nombre de places");

            CheckBox siegeBebeField = new CheckBox("Siège bébé disponible");
            CheckBox coffreField = new CheckBox("Grand coffre");

            champsSpecifiques.getChildren().addAll(
                new Label("Champs spécifiques - Voiture Familiale"),
                new Label("Nombre de places:"), nombrePlacesField,
                siegeBebeField,
                coffreField
            );
        } else if ("Voiture Commerciale".equals(newValue)) {
            TextField capaciteChargeField = new TextField();
            capaciteChargeField.setPromptText("Capacité de charge (kg)");

            CheckBox toitOuvrantField = new CheckBox("Toit ouvrant disponible");
            CheckBox cameraReculField = new CheckBox("Caméra de recul disponible");

            champsSpecifiques.getChildren().addAll(
                new Label("Champs spécifiques - Voiture Commerciale"),
                new Label("Capacité de charge:"), capaciteChargeField,
                toitOuvrantField,
                cameraReculField
            );
        }
    });

    // Formulaire complet
    VBox form = new VBox(10,
        new Label("Type de véhicule:"), typeBox,
        new Label("Matricule:"), matriculeField,
        new Label("Marque:"), marqueField,
        new Label("Modèle:"), modeleField,
        new Label("Puissance:"), puissanceField,
        new Label("Carburant:"), carburantField,
        new Label("Année du modèle:"), anneeField,
        new Label("Kilométrage:"), kilometrageField,
        new Label("Coût par jour:"), coutParJourField,
        new Label("Équipements :"),
        roueSecoursField, cricOutilsField, radioAntenneField,
        enjoliversField, retroviseursField, climatiseurMarcheField,
        champsSpecifiques
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
                String type = typeBox.getValue();
                String matricule = matriculeField.getText();
                String marque = marqueField.getText();
                String modele = modeleField.getText();
                String puissance = puissanceField.getText();
                String carburant = carburantField.getText();
                int anneeModele = Integer.parseInt(anneeField.getText());
                double kilometrage = Double.parseDouble(kilometrageField.getText());
                double coutParJour = Double.parseDouble(coutParJourField.getText());
                boolean roueSecours = roueSecoursField.isSelected();
                boolean cricOutils = cricOutilsField.isSelected();
                boolean radioAntenne = radioAntenneField.isSelected();
                boolean enjolivers = enjoliversField.isSelected();
                boolean retroviseurs = retroviseursField.isSelected();
                boolean climatiseurMarche = climatiseurMarcheField.isSelected();

                // Champs spécifiques
                if ("Voiture Familiale".equals(type)) {
                    int nombrePlaces = Integer.parseInt(((TextField) champsSpecifiques.getChildren().get(2)).getText()); // Index ajusté
                    boolean siegeBebe = ((CheckBox) champsSpecifiques.getChildren().get(3)).isSelected();
                    boolean coffre = ((CheckBox) champsSpecifiques.getChildren().get(4)).isSelected();

                    return new VoitureFamiliale(matricule, marque, modele, puissance, carburant, anneeModele, kilometrage,
                        roueSecours, cricOutils, radioAntenne, enjolivers, retroviseurs, climatiseurMarche,
                        nombrePlaces, siegeBebe, coffre, type, coutParJour, null);
                } else if ("Voiture Commerciale".equals(type)) {
                    int capaciteCharge = Integer.parseInt(((TextField) champsSpecifiques.getChildren().get(2)).getText()); // Index ajusté
                    boolean toitOuvrant = ((CheckBox) champsSpecifiques.getChildren().get(3)).isSelected();
                    boolean cameraRecul = ((CheckBox) champsSpecifiques.getChildren().get(4)).isSelected();

                    return new VoitureCommerciale(matricule, marque, modele, puissance, carburant, anneeModele, kilometrage,
                        roueSecours, cricOutils, radioAntenne, enjolivers, retroviseurs, climatiseurMarche,
                        capaciteCharge, toitOuvrant, cameraRecul, type, coutParJour, null);
                }

            } catch (Exception e) {
                System.err.println("Erreur dans les données : " + e.getMessage());
            }
        }
        return null;
    });

    // Afficher la boîte de dialogue
    dialog.showAndWait().ifPresent(vehicule -> {
        vehicules.add(vehicule);
        tableVoitures.refresh();
    });
}

}