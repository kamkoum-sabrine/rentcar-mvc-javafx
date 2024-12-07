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
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
import models.vehicules.Assurance;
import models.vehicules.FiltreVehicule;
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

   /** @FXML
    private TableColumn<VoitureFamiliale, Boolean> colSiegeBebe;**/
    @FXML
    private TableColumn<VoitureCommerciale, Integer> colCapaciteCharge;

    @FXML
    private TextField searchField;  // Le champ de recherche

    // Liste observable des véhicules
    private final ObservableList<Vehicule> vehicules = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Vehicule, Void> colActions;

    @FXML
    private TableColumn<Vehicule, Void> colActionsAssurance;

    @FXML
    private TableColumn<Vehicule, String> colAssurance;

    private ObservableList<Vehicule> listeVehicules = FXCollections.observableArrayList();


@FXML
public void initialize() {
           
    colMatricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
    colMarque.setCellValueFactory(new PropertyValueFactory<>("marque"));
    colModele.setCellValueFactory(new PropertyValueFactory<>("modele"));
    colType.setCellValueFactory(new PropertyValueFactory<>("type"));
    colCoutParJour.setCellValueFactory(new PropertyValueFactory<>("coutParJour"));
    
     colAssurance.setCellValueFactory(cellData -> {
            if (cellData.getValue().getAssurance() != null) {
                return new SimpleStringProperty(cellData.getValue().getAssurance().getNomAssureur());
            } else {
                return new SimpleStringProperty("Aucune");
            }
        });

    // Configurer les colonnes spécifiques
    colNombrePlaces.setCellValueFactory(cellData -> {
        Vehicule vehicule = cellData.getValue();
        if (vehicule instanceof VoitureFamiliale vf) {
            return new SimpleIntegerProperty(vf.getNombrePlaces()).asObject();
        }
        return null; // Pas applicable pour les autres types
    });

   
    
     colCapaciteCharge.setCellValueFactory(cellData -> {
        Vehicule vehicule = cellData.getValue();
        if (vehicule instanceof VoitureCommerciale vc) {
            return new SimpleIntegerProperty(vc.getCapaciteCharge()).asObject();
        }
        return null;
    });
     
  
    // Charger des données initiales
    vehicules.addAll(getVehiculesInitiaux());
    tableVoitures.setItems(vehicules);
    
  // Ajouter des boutons pour la colonne d'actions
   // Colonne Actions : bouton pour affecter l'assurance et supprimer l'affectation
         colActionsAssurance.setCellFactory(param -> new TableCell<Vehicule, Void>() {
              private final HBox actionBox = new HBox(10); // Conteneur horizontal pour les icônes
    
            private final Button affecterButton = new Button("Affecter");
            private final Button supprimerButton = new Button("Supprimer");

            {
                affecterButton.setOnAction(event -> affecterAssurance(getTableRow().getItem()));
                supprimerButton.setOnAction(event -> supprimerAssurance(getTableRow().getItem()));
                affecterButton.setStyle("-fx-background-color: #347928; -fx-text-fill: white; -fx-font-weight: bold;");
                supprimerButton.setStyle("-fx-background-color: #B8001F; -fx-text-fill: white; -fx-font-weight: bold;");
                actionBox.getChildren().addAll(affecterButton, supprimerButton);
            }

            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    
                    HBox hbox = new HBox(10,affecterButton, supprimerButton);
                    setGraphic(hbox);
                }
            }
        });
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
                Vehicule vehicule = getTableView().getItems().get(getIndex());
                System.out.println("Matricule vehicule "+ vehicule.getMatricule());
                try {
                    onEditVehicule(vehicule);
                } catch (Exception e) {
                    System.err.println("Erreur lors de l'édition : " + e.getMessage());
                    e.printStackTrace();
                }
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

private void onEditVehicule(Vehicule vehicule) {
    System.out.println("onEditVehicule appelé pour le véhicule : " + vehicule.getMatricule());
    
    Dialog<Vehicule> dialog = new Dialog<>();
    dialog.setTitle("Modifier le véhicule");

    dialog.getDialogPane().setPrefWidth(600);
    dialog.getDialogPane().setPrefHeight(700);

    // Type de véhicule
    ComboBox<String> typeBox = new ComboBox<>();
    typeBox.getItems().addAll("Voiture Familiale", "Voiture Commerciale");
    typeBox.setValue(vehicule instanceof VoitureFamiliale ? "Voiture Familiale" : "Voiture Commerciale");

    // Champs communs
    TextField matriculeField = new TextField(vehicule.getMatricule());
    TextField marqueField = new TextField(vehicule.getMarque());
    TextField modeleField = new TextField(vehicule.getModele());
    TextField puissanceField = new TextField(vehicule.getPuissance());
    TextField carburantField = new TextField(vehicule.getCarburant());
    TextField anneeField = new TextField(String.valueOf(vehicule.getAnneeModele()));
    TextField kilometrageField = new TextField(String.valueOf(vehicule.getKilometrage()));
    TextField coutParJourField = new TextField(String.valueOf(vehicule.getCoutParJour()));

    // Création des cases à cocher
    CheckBox roueSecoursField = new CheckBox("Roue de secours");
    roueSecoursField.setSelected(vehicule.getRoueSecours()); 

    CheckBox cricOutilsField = new CheckBox("Cric et outils");
    cricOutilsField.setSelected(vehicule.getCricOutils());

    CheckBox radioAntenneField = new CheckBox("Radio et antenne");
    radioAntenneField.setSelected(vehicule.getRadioAntenne());

    CheckBox enjoliversField = new CheckBox("Enjoliveurs");
    enjoliversField.setSelected(vehicule.getEnjolivers());

    CheckBox retroviseursField = new CheckBox("Rétroviseurs");
    retroviseursField.setSelected(vehicule.getRetroviseurs());

    CheckBox climatiseurMarcheField = new CheckBox("Climatiseur fonctionnel");
    CheckBox toitOuvrantField = new CheckBox("Toit ouvrant disponible");
        toitOuvrantField.setSelected(vehicule.getClimatiseurMarche());

        // Conteneur pour champs spécifiques
        VBox champsSpecifiques = new VBox(10);

        // Précharger les champs spécifiques pour VoitureFamiliale
        if (vehicule instanceof VoitureFamiliale vf) {
            TextField nombrePlacesField = new TextField(String.valueOf(vf.getNombrePlaces()));
            CheckBox siegeBebeField = new CheckBox("Siège bébé disponible");
            siegeBebeField.setSelected(vf.getSiegeBebeDisponible());
            CheckBox coffreField = new CheckBox("Grand coffre");
            coffreField.setSelected(vf.getGrandCoffre());

            champsSpecifiques.getChildren().addAll(
                    new Label("Champs spécifiques - Voiture Familiale"),
                    new Label("Nombre de places:"), nombrePlacesField,
                    siegeBebeField,
                    coffreField
            );
        } else if (vehicule instanceof VoitureCommerciale vc) {
            TextField capaciteChargeField = new TextField(String.valueOf(vc.getCapaciteCharge()));
            CheckBox toitOuvrantField1 = new CheckBox("Toit ouvrant");
            toitOuvrantField1.setSelected(vc.getToitOuvrant());
        CheckBox cameraReculField = new CheckBox("Caméra de recul");
        cameraReculField.setSelected(vc.getCameraRecul());

        champsSpecifiques.getChildren().addAll(
            new Label("Champs spécifiques - Voiture Commerciale"),
            new Label("Capacité de charge:"), capaciteChargeField,
            toitOuvrantField, cameraReculField );
    }

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

    ScrollPane scrollPane = new ScrollPane(form);
    scrollPane.setFitToWidth(true);
    dialog.getDialogPane().setContent(scrollPane);

    // Boutons
    ButtonType saveButtonType = new ButtonType("Sauvegarder", ButtonBar.ButtonData.OK_DONE);
    dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

    dialog.setResultConverter(buttonType -> {
        if (buttonType == saveButtonType) {
            try {
                // Mettre à jour les champs du véhicule
                vehicule.setMatricule(matriculeField.getText());
                vehicule.setMarque(marqueField.getText());
                vehicule.setModele(modeleField.getText());
                vehicule.setPuissance(puissanceField.getText());
                vehicule.setCarburant(carburantField.getText());
                vehicule.setAnneeModele(Integer.parseInt(anneeField.getText()));
                vehicule.setKilometrage(Double.parseDouble(kilometrageField.getText()));
                vehicule.setCoutParJour(Double.parseDouble(coutParJourField.getText()));

                vehicule.setRoueSecours(roueSecoursField.isSelected());
                vehicule.setCricOutils(cricOutilsField.isSelected());
                vehicule.setRadioAntenne(radioAntenneField.isSelected());
                vehicule.setEnjolivers(enjoliversField.isSelected());
                vehicule.setRetroviseurs(retroviseursField.isSelected());
                vehicule.setClimatiseurMarche(climatiseurMarcheField.isSelected());

                // Champs spécifiques
                if (vehicule instanceof VoitureFamiliale vf) {
                    vf.setNombrePlaces(Integer.parseInt(((TextField) champsSpecifiques.getChildren().get(2)).getText()));
                    vf.setSiegeBebeDisponible(((CheckBox) champsSpecifiques.getChildren().get(3)).isSelected());
                    vf.setGrandCoffre(((CheckBox) champsSpecifiques.getChildren().get(4)).isSelected());
                } else if (vehicule instanceof VoitureCommerciale vc1) {
                    vc1.setCapaciteCharge(Integer.parseInt(((TextField) champsSpecifiques.getChildren().get(2)).getText()));
                    vc1.setToitOuvrant(((CheckBox) champsSpecifiques.getChildren().get(3)).isSelected());
                    vc1.setCameraRecul(((CheckBox) champsSpecifiques.getChildren().get(4)).isSelected());
                }

                return vehicule;
            } catch (Exception e) {
                System.err.println("Erreur dans les données : " + e.getMessage());
            }
        }
        return null;
    });

    dialog.showAndWait().ifPresent(updatedVehicule -> {
        tableVoitures.refresh(); // Met à jour la TableView
        Gerant.getInstance().setVehicules(new ArrayList<>(vehicules)); // Met à jour la liste des véhicules dans le gestionnaire
    });
}


private void onDeleteVehicule(Vehicule vehicule) {
    Gerant gerant = Gerant.getInstance(); // Obtenez l'instance unique de Gerant

    boolean supprimé = gerant.supprimerVehicule(vehicule);
    if (supprimé) {
        System.out.println("Véhicule supprimé : " + vehicule.getMatricule());
        vehicules.remove(vehicule); 
        tableVoitures.refresh(); 
    } else {
        System.out.println("Échec de la suppression : Véhicule introuvable.");
    }
}

public List<Vehicule> getVehiculesInitiaux() {
    // Récupère les véhicules stockés dans Gerant
    return Gerant.getInstance().getVehicules();
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
                        nombrePlaces, siegeBebe, coffre, type, coutParJour, null,null);
                } else if ("Voiture Commerciale".equals(type)) {
                    int capaciteCharge = Integer.parseInt(((TextField) champsSpecifiques.getChildren().get(2)).getText()); // Index ajusté
                    boolean toitOuvrant = ((CheckBox) champsSpecifiques.getChildren().get(3)).isSelected();
                    boolean cameraRecul = ((CheckBox) champsSpecifiques.getChildren().get(4)).isSelected();

                    return new VoitureCommerciale(matricule, marque, modele, puissance, carburant, anneeModele, kilometrage,
                        roueSecours, cricOutils, radioAntenne, enjolivers, retroviseurs, climatiseurMarche,
                        capaciteCharge, toitOuvrant, cameraRecul, type, coutParJour, null, null);
                }

            } catch (Exception e) {
                System.err.println("Erreur dans les données : " + e.getMessage());
            }
        }
        return null;
    });

    // Afficher la boîte de dialogue
    dialog.showAndWait().ifPresent(vehicule -> {
         Gerant.getInstance().ajouterVoiture(vehicule);
        vehicules.add(vehicule);
        tableVoitures.refresh();
    });
    
}
 // Méthode pour affecter une assurance à un véhicule
    private void affecterAssurance(Vehicule vehicule) {
        // Ouvrir un dialog pour afficher les assurances
        Dialog<Assurance> dialog = new Dialog<>();
        dialog.setTitle("Choisir une Assurance");

        ComboBox<Assurance> comboBoxAssurances = new ComboBox<>();
        comboBoxAssurances.getItems().addAll(Gerant.getInstance().getAssurances());  // Liste des assurances du gestionnaire
        dialog.getDialogPane().setContent(comboBoxAssurances);

        ButtonType affecterButtonType = new ButtonType("Affecter", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(affecterButtonType, ButtonType.CANCEL);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == affecterButtonType && comboBoxAssurances.getValue() != null) {
                Assurance selectedAssurance = comboBoxAssurances.getValue();
                vehicule.setAssurance(selectedAssurance);  // Affecter l'assurance au véhicule
            }
            return null;
        });

        dialog.showAndWait();
          tableVoitures.refresh();
    }

    // Méthode pour supprimer l'affectation de l'assurance d'un véhicule
    private void supprimerAssurance(Vehicule vehicule) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Supprimer l'Assurance");
        alert.setHeaderText("Êtes-vous sûr de vouloir supprimer l'affectation de l'assurance ?");
        alert.setContentText("Cela supprimera l'assurance actuelle du véhicule.");
        
        if (alert.showAndWait().get() == ButtonType.OK) {
            vehicule.setAssurance(null);  // Supprimer l'assurance du véhicule
              tableVoitures.refresh();
        }
    }
    
     // Méthode pour filtrer les véhicules en fonction de la marque
    public void filtrerVehicules() {
        String searchQuery = searchField.getText().toLowerCase();  // Texte saisi par l'utilisateur
        FiltreVehicule filtre = vehicule -> vehicule.getMarque().toLowerCase().contains(searchQuery);

        ObservableList<Vehicule> vehiculesFiltres = FXCollections.observableArrayList();
        for (Vehicule vehicule : listeVehicules) {
            if (filtre.filtrer(vehicule)) {
                vehiculesFiltres.add(vehicule);
            }
        }

        tableVoitures.setItems(vehiculesFiltres);  // Mettre à jour la TableView avec les résultats filtrés
    }

}