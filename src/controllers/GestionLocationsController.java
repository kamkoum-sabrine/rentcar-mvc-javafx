/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import models.Personnes.Adresse;
import models.Personnes.Client;
import models.Personnes.Gerant;
import models.VehiculesException.DateLocationException;
import models.vehicules.Assurance;
import models.vehicules.ContratLocation;
import models.vehicules.Vehicule;
import models.vehicules.VoitureCommerciale;
import models.vehicules.VoitureFamiliale;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class GestionLocationsController {

    /**
     * Initializes the controller class.
     */
  
    
@FXML
private TableView<ContratLocation> tableContratLocation;

@FXML
private TableColumn<ContratLocation, Integer> colId;

@FXML
private TableColumn<ContratLocation, Date> colDateDebut;

@FXML
private TableColumn<ContratLocation, Date> colDateFin;

// Liste observable des contracts
private final ObservableList<ContratLocation> contratLocations = FXCollections.observableArrayList();

@FXML
private TableColumn<ContratLocation, Void> colActions;

@FXML
private TableColumn<ContratLocation, String> colConducteur1;

@FXML
private TableColumn<ContratLocation, String> colConducteur2;

@FXML
private TableColumn<ContratLocation, String> colVehicule;

private ObservableList<Vehicule> listeVehicules = FXCollections.observableArrayList();


@FXML
public void initialize() {
        
   //  tableVoitures.lookup(".column-header-background").setStyle("-fx-background-color: #2980b9; -fx-text-fill: white;");
    // Initialiser les colonnes génériques
        
    colId.setCellValueFactory(new PropertyValueFactory<>("id"));
    colDateDebut.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
    colDateFin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));        
    
     colConducteur1.setCellValueFactory(cellData -> {
            if (cellData.getValue().getConducteur1()!= null) {
                return new SimpleStringProperty(cellData.getValue().getConducteur1().getNom()+' '+cellData.getValue().getConducteur1().getPrenom());
            } else {
                return new SimpleStringProperty("Aucun");
            }
        });
     
     colConducteur2.setCellValueFactory(cellData -> {
            if (cellData.getValue().getConducteur2()!= null) {
                return new SimpleStringProperty(cellData.getValue().getConducteur2().getNom()+' '+cellData.getValue().getConducteur2().getPrenom());
            } else {
                return new SimpleStringProperty("Aucun");
            }
        });
     
     colVehicule.setCellValueFactory(cellData -> {
            if (cellData.getValue().getVehicule()!= null) {
                return new SimpleStringProperty(cellData.getValue().getVehicule().getMatricule());
            } else {
                return new SimpleStringProperty("Aucune");
            }
        });

   
    // Charger des données initiales
    contratLocations.addAll(getContratLocationInitiaux());
    tableContratLocation.setItems(contratLocations);
    
  // Ajouter des boutons pour la colonne d'actions
   
    colActions.setCellFactory(param -> new TableCell<>() {
        private final HBox actionBox = new HBox(10); // Conteneur horizontal pour les icônes
        private final Button editButton = new Button();
        private final Button deleteButton = new Button();

        {
            // Configure les boutons avec des icônes
           // editButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/ressources/images/edit.png"))));
           // deleteButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/ressources/images/trash.png"))));
            // Configure les boutons avec des chaînes de caractères
            editButton.setText("Éditer");
            deleteButton.setText("Supprimer");

            // (Facultatif) Ajoute des styles pour différencier les boutons
            editButton.setStyle("-fx-background-color: #5bc0de; -fx-text-fill: white; -fx-font-weight: bold;");
            deleteButton.setStyle("-fx-background-color: #d9534f; -fx-text-fill: white; -fx-font-weight: bold;");

            // Ajout des actions
            editButton.setOnAction(event -> {
                System.out.println("edit !!");
                ContratLocation contratLocation = getTableView().getItems().get(getIndex());
                System.out.println("Numero contrat "+ contratLocation.getId());
                try {
                    onEditContratLocation(contratLocation);
                } catch (Exception e) {
                    System.err.println("Erreur lors de l'édition : " + e.getMessage());
                    e.printStackTrace();
                }
            });

            deleteButton.setOnAction(event -> {
                ContratLocation contratLocation = getTableView().getItems().get(getIndex());
                onDeleteContrat(contratLocation);
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
private void onEditContratLocation(ContratLocation contratLocation) {
        System.out.println("onEditContratLocation appelé pour le contrat : " + contratLocation.getId());
        // Configuration de la boîte de dialogue
        Dialog<ContratLocation> dialog = new Dialog<>();
        dialog.setTitle("Modifier le contrat");
        dialog.getDialogPane().setPrefWidth(600);
        dialog.getDialogPane().setPrefHeight(700);

        // Champs de saisie dans un GridPane
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        // Champs de saisie
        TextField idContratField = new TextField();
        idContratField.setPromptText("Numéro Contrat");
        idContratField.setText(String.valueOf(contratLocation.getId()));  // Valeur de l'ID du contrat

        // ComboBox pour conducteur 1
        ComboBox<Client> conducteur1Field = new ComboBox<>();
        conducteur1Field.getItems().addAll(Gerant.getInstance().getClients());
        conducteur1Field.setPromptText("Sélectionnez le conducteur principal");
        conducteur1Field.setValue(contratLocation.getConducteur1());  // Sélectionne le conducteur principal par défaut

        // ComboBox pour conducteur 2
        ComboBox<Client> conducteur2Field = new ComboBox<>();
        conducteur2Field.getItems().addAll(Gerant.getInstance().getClients());
        conducteur2Field.setPromptText("Sélectionnez le deuxième conducteur");
        conducteur2Field.setValue(contratLocation.getConducteur2());  // Sélectionne le deuxième conducteur par défaut

        // ComboBox pour véhicule
        ComboBox<Vehicule> vehiculeField = new ComboBox<>();
        vehiculeField.getItems().addAll(Gerant.getInstance().getVehicules());
        vehiculeField.setPromptText("Sélectionnez un véhicule");
        vehiculeField.setValue(contratLocation.getVehicule());  // Sélectionne le véhicule par défaut

        // DatePicker pour la date de début
        DatePicker dateDebutField = new DatePicker();
        dateDebutField.setPromptText("Date de début");
        dateDebutField.setValue(toLocalDate(contratLocation.getDateDebut()));  // Affecte la date de début par défaut

        // DatePicker pour la date de fin
        DatePicker dateFinField = new DatePicker();
        dateFinField.setPromptText("Date de fin");
        dateFinField.setValue(toLocalDate(contratLocation.getDateFin()));  // Affecte la date de fin par défaut

        // Définir une largeur préférée pour les champs
        idContratField.setPrefWidth(400);
        conducteur1Field.setPrefWidth(400);
        conducteur2Field.setPrefWidth(400);
        vehiculeField.setPrefWidth(400);
        dateDebutField.setPrefWidth(400);
        dateFinField.setPrefWidth(400);


            // Ajouter les champs au GridPane
        grid.add(new Label("Numéro du Contrat :"), 0, 0);
        grid.add(idContratField, 1, 0);

        grid.add(new Label("Conducteur 1 :"), 0, 1);
        grid.add(conducteur1Field, 1, 1);

        grid.add(new Label("Conducteur 2 :"), 0, 2);
        grid.add(conducteur2Field, 1, 2);

        grid.add(new Label("Véhicule :"), 0, 3);
        grid.add(vehiculeField, 1, 3);

        grid.add(new Label("Date de début :"), 0, 4);
        grid.add(dateDebutField, 1, 4);

        grid.add(new Label("Date de fin :"), 0, 5);
        grid.add(dateFinField, 1, 5);

        dialog.getDialogPane().setContent(grid);

        // Conteneur de la grille dans un ScrollPane pour un défilement flexible
        ScrollPane scrollPane = new ScrollPane(grid);
        scrollPane.setFitToWidth(true);
        dialog.getDialogPane().setContent(scrollPane);

        // Boutons
        ButtonType saveButtonType = new ButtonType("Sauvegarder", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        // Résultat de la conversion
       dialog.setResultConverter(buttonType -> {
    if (buttonType == saveButtonType) {
        try {
            // Mise à jour des champs de l'objet ContratLocation
            contratLocation.setId(Integer.parseInt(idContratField.getText()));  // Mise à jour de l'ID du contrat

            // Mise à jour des conducteurs
            contratLocation.setConducteur1(conducteur1Field.getValue());  // Mise à jour du conducteur 1
            contratLocation.setConducteur2(conducteur2Field.getValue());  // Mise à jour du conducteur 2

            // Mise à jour du véhicule
            contratLocation.setVehicule(vehiculeField.getValue());  // Mise à jour du véhicule

            // Conversion de LocalDate en Date pour dateDebut
            LocalDate localDateDebut = dateDebutField.getValue();
            if (localDateDebut != null) {
                Date dateDebut = Date.from(localDateDebut.atStartOfDay(ZoneId.systemDefault()).toInstant());
                contratLocation.setDateDebut(dateDebut);  // Mise à jour de la date de début
            }

            // Conversion de LocalDate en Date pour dateFin
            LocalDate localDateFin = dateFinField.getValue();
            if (localDateFin != null) {
                Date dateFin = Date.from(localDateFin.atStartOfDay(ZoneId.systemDefault()).toInstant());
                contratLocation.setDateFin(dateFin);  // Mise à jour de la date de fin
            }

            // Retourne l'objet ContratLocation mis à jour
            return contratLocation;  // Retourne l'objet modifié
            } catch (Exception e) {
                System.err.println("Erreur dans les données : " + e.getMessage());
                showAlert("Erreur", "Les données saisies sont incorrectes.");
            }
        }
        return null;
    });
    // Affiche la boîte de dialogue et met à jour la table si l'utilisateur a cliqué sur Sauvegarder
    dialog.showAndWait().ifPresent(updatedContrat -> {
        tableContratLocation.refresh(); // Met à jour la TableView
        Gerant.getInstance().setLocations(new ArrayList<>(contratLocations)); // Met à jour la liste des contrats dans le gestionnaire
    });
}



// Méthode pour gérer la suppression

// Méthode pour gérer la suppression d'un contrat
private void onDeleteContrat(ContratLocation contratLocation) {
    Gerant gerant = Gerant.getInstance(); // Obtenez l'instance unique de Gerant

    boolean supprimé = gerant.supprimerLocation(contratLocation);
    if (supprimé) {
        System.out.println("Contrat supprimé : " + contratLocation.getId());
        contratLocations.remove(contratLocation); // Met à jour la liste observable si utilisée dans l'interface
        tableContratLocation.refresh(); // Rafraîchit la table
    } else {
        System.out.println("Échec de la suppression : Contrat introuvable.");
    }
}

public List<ContratLocation> getContratLocationInitiaux() {
    // Récupère les contrats stockés dans Gerant
    return Gerant.getInstance().getLocations();
}


 // @FXML
/**public void onAjouterLocationClick(ActionEvent event) {
    Dialog<ContratLocation> dialog = new Dialog<>();
    dialog.setTitle("Ajouter un contrat");

    dialog.getDialogPane().setPrefWidth(600);
    dialog.getDialogPane().setPrefHeight(700);

   
    // Champs communs
    TextField idField = new TextField();
    idField.setPromptText("Numero Contrat");
    
    DatePicker dateDebutField = new DatePicker();
    dateDebutField.setPromptText("Date de début");

    DatePicker dateFinField = new DatePicker();
    dateFinField.setPromptText("Date de fin");

    // Formulaire complet
    VBox form = new VBox(10,
     
        new Label("Numero contrat:"), idField,
        new Label("Date début:"), dateDebutField,
        new Label("Date fin:"), dateFinField
    );

    dialog.getDialogPane().setContent(form);

     // Ajout de la barre de défilement
    ScrollPane scrollPane = new ScrollPane(form);
    scrollPane.setFitToWidth(true); // Adapter à la largeur du dialogue
    scrollPane.setPrefHeight(600); // Hauteur visible avant le défilement
    scrollPane.setPrefWidth(700); // Largeur visible

    dialog.getDialogPane().setContent(scrollPane);
    // Boutons
    ButtonType ajouterButtonType = new ButtonType("Ajouter", ButtonBar.ButtonData.OK_DONE);
    dialog.getDialogPane().getButtonTypes().addAll(ajouterButtonType, ButtonType.CANCEL);

    // Gestion de l'ajout
    dialog.setResultConverter(buttonType -> {
        if (buttonType == ajouterButtonType) {
            try {
                
                int idContrant = Integer.parseInt(idField.getText());
                LocalDate localDateDebut = dateDebutField.getValue();
                Date dateDebut = Date.from(localDateDebut.atStartOfDay(ZoneId.systemDefault()).toInstant());

                LocalDate localDateFin = dateFinField.getValue();
                Date dateFin = Date.from(localDateFin.atStartOfDay(ZoneId.systemDefault()).toInstant());

                return new ContratLocation(idContrant, );
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
    
}**/
 public void onAjouterLocationClick(ActionEvent event) {
    // Création de la boîte de dialogue
    Dialog<ContratLocation> dialog = new Dialog<>();
    dialog.setTitle("Créer un contrat de location");
    dialog.setHeaderText("Veuillez remplir les informations du contrat");

    // Boutons OK et Annuler
    ButtonType okButtonType = new ButtonType("Créer", ButtonBar.ButtonData.OK_DONE);
    dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

    // Champs du formulaire
    GridPane grid = new GridPane();
    grid.setMaxWidth(400); 
    grid.setHgap(10);
    grid.setVgap(10);
    

    // Champs de saisie
    TextField idContratField = new TextField();
    idContratField.setPromptText("Numéro Contrat");

    ComboBox<Client> conducteur1Field = new ComboBox<>();
    conducteur1Field.getItems().addAll(Gerant.getInstance().getClients());
    conducteur1Field.setPromptText("Sélectionnez le conducteur principal");

    ComboBox<Client> conducteur2Field = new ComboBox<>();
    conducteur2Field.getItems().addAll(Gerant.getInstance().getClients());
    conducteur2Field.setPromptText("Sélectionnez le deuxième conducteur");

    ComboBox<Vehicule> vehiculeField = new ComboBox<>();
    vehiculeField.getItems().addAll(Gerant.getInstance().getVehicules());
    vehiculeField.setPromptText("Sélectionnez un véhicule");

    DatePicker dateDebutField = new DatePicker();
    dateDebutField.setPromptText("Date de début");

    DatePicker dateFinField = new DatePicker();
    dateFinField.setPromptText("Date de fin");
    // Définir une largeur préférée pour les champs
    idContratField.setPrefWidth(400);
    conducteur1Field.setPrefWidth(400);
    conducteur2Field.setPrefWidth(400);
    vehiculeField.setPrefWidth(400);
    dateDebutField.setPrefWidth(400);
    dateFinField.setPrefWidth(400);


    // Ajouter les champs au GridPane
    grid.add(new Label("Numéro du Contrat :"), 0, 0);
    grid.add(idContratField, 1, 0);

    grid.add(new Label("Conducteur 1 :"), 0, 1);
    grid.add(conducteur1Field, 1, 1);

    grid.add(new Label("Conducteur 2 :"), 0, 2);
    grid.add(conducteur2Field, 1, 2);

    grid.add(new Label("Véhicule :"), 0, 3);
    grid.add(vehiculeField, 1, 3);

    grid.add(new Label("Date de début :"), 0, 4);
    grid.add(dateDebutField, 1, 4);

    grid.add(new Label("Date de fin :"), 0, 5);
    grid.add(dateFinField, 1, 5);

    dialog.getDialogPane().setContent(grid);

    // Désactiver le bouton OK tant que les champs ne sont pas remplis
    Node okButton = dialog.getDialogPane().lookupButton(okButtonType);
    okButton.setDisable(true);

    // Validation des champs pour activer le bouton OK
     ChangeListener<Object> changeListener = (observable, oldValue, newValue) -> 
        validateFields(okButton, idContratField, conducteur1Field, vehiculeField, dateDebutField, dateFinField);

    idContratField.textProperty().addListener(changeListener);
    conducteur1Field.valueProperty().addListener(changeListener);
    //conducteur2Field.valueProperty().addListener(changeListener);
    vehiculeField.valueProperty().addListener(changeListener);
    dateDebutField.valueProperty().addListener(changeListener);
    dateFinField.valueProperty().addListener(changeListener);

    // Lorsque l'utilisateur clique sur OK
    dialog.setResultConverter(dialogButton -> {
        if (dialogButton == okButtonType) {
            try {
                int id = Integer.parseInt(idContratField.getText().trim());
                try {
                    
                    LocalDate localDateDebut = dateDebutField.getValue();
                    Date dateDebut = Date.from(localDateDebut.atStartOfDay(ZoneId.systemDefault()).toInstant());

                    LocalDate localDateFin = dateFinField.getValue();
                    Date dateFin = Date.from(localDateFin.atStartOfDay(ZoneId.systemDefault()).toInstant());
                    // Vérification de la validité des dates
                    if (dateDebut.after(dateFin)) {
                        showAlert("Erreur", "La date de début doit être antérieure à la date de fin.");
                        return null; // Ne pas continuer si la date de début est après la date de fin
                    }


                    // Création du contrat
                    return new ContratLocation(
                        id,
                        conducteur1Field.getValue(),
                        conducteur2Field.getValue(),
                        vehiculeField.getValue(),
                        dateDebut,
                        dateFin
                    );

                } catch (DateLocationException e) {
                    // Handle the exception (e.g., log it or show a user message)
                    System.out.println("Invalid date range: " + e.getMessage());
                    return null; // or handle the return value accordingly
}
            } catch (NumberFormatException e) {
                // Affiche une alerte si le champ idContrat n'est pas un entier
                showAlert("Erreur", "Le numéro de contrat doit être un nombre entier.");
            }
        }
        return null;
    });

    // Afficher la boîte de dialogue et récupérer le résultat
    Optional<ContratLocation> result = dialog.showAndWait();

    result.ifPresent(contrat -> {
        Gerant.getInstance().ajouterLocation(contrat);
        contratLocations.add(contrat);
        tableContratLocation.refresh();
        System.out.println("Contrat ajouté avec succès !");
    });
}

// Méthode de validation des champs
private void validateFields(Node okButton, TextField idContratField, ComboBox<Client> conducteur1Field,  ComboBox<Vehicule> vehiculeField, DatePicker dateDebutField, DatePicker dateFinField) {
    boolean isDisabled = idContratField.getText().trim().isEmpty()
        || conducteur1Field.getValue() == null
        || vehiculeField.getValue() == null
        || dateDebutField.getValue() == null
        || dateFinField.getValue() == null
        || dateDebutField.getValue().isAfter(dateFinField.getValue()); // Date de début doit être avant la date de fin

    okButton.setDisable(isDisabled);
}

// Méthode d'affichage d'alerte
private void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}


    private void validateFields(Node okButton, ComboBox<Client> conducteur1Field,  ComboBox<Vehicule> vehiculeField, DatePicker dateDebutField, DatePicker dateFinField) {
        okButton.setDisable(
            conducteur1Field.getValue() == null ||
           
            vehiculeField.getValue() == null ||
            dateDebutField.getValue() == null ||
            dateFinField.getValue() == null
        );
    }
 // Méthode pour convertir java.util.Date en LocalDate
private LocalDate toLocalDate(Date date) {
    if (date != null) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    return null;
}

    
}
