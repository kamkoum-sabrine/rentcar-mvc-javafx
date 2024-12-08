package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import java.time.LocalDate;
import java.time.ZoneId;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import models.management.Entretien;
import models.management.Garage;
import java.util.Calendar;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import java.util.Date;
import java.util.List;
import javafx.fxml.FXML;
import javafx.util.Callback;
import java.util.Optional;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import models.Personnes.Gerant;
import models.Personnes.Technicien;
import models.vehicules.Vehicule;

public class GestionEntretienController {

    // Define TableView and columns from FXML
    @FXML
    private TableView<Entretien> tableEntretien;
    @FXML
    private TableColumn<Entretien, Integer> colIdEntretien;
    @FXML
    private TableColumn<Entretien, String> colDateEntretien;
    @FXML
    private TableColumn<Entretien, String> colTypeEntretien;
    @FXML
    private TableColumn<Entretien, Double> colCout;
    @FXML
    private TableColumn<Entretien, Integer> colKilometrage;
    @FXML
    private TableColumn<Entretien, String> colTechnicienEnCharge;
    @FXML
    private TableColumn<Entretien, String> colNotes;
    @FXML
    private TableColumn<Entretien, String> colVehicule;
    @FXML
    private TableColumn<Entretien, String> colGarage;
    @FXML
    private TableColumn<Entretien, Void> colActions; // Action column for Edit/Delete buttons

    // ObservableList to manage the Entretien objects
    private final ObservableList<Entretien> entretienList = FXCollections.observableArrayList();

    // Initialize method to set up the table and bind data
    @FXML
    public void initialize() {
        // Bind the columns to the corresponding properties in the Entretien class
        colIdEntretien.setCellValueFactory(new PropertyValueFactory<>("idEntretien"));
        colDateEntretien.setCellValueFactory(new PropertyValueFactory<>("dateEntretien"));
        colTypeEntretien.setCellValueFactory(new PropertyValueFactory<>("typeEntretien"));
        colCout.setCellValueFactory(new PropertyValueFactory<>("cout"));
        colKilometrage.setCellValueFactory(new PropertyValueFactory<>("kilometrage"));
        colTechnicienEnCharge.setCellValueFactory(new PropertyValueFactory<>("technicienEnCharge"));
        colNotes.setCellValueFactory(new PropertyValueFactory<>("notes"));
        colVehicule.setCellValueFactory(new PropertyValueFactory<>("vehicule"));
        colGarage.setCellValueFactory(new PropertyValueFactory<>("garage"));
        entretienList.addAll(getEntretiensInitiaux());
        tableEntretien.setItems(entretienList);
        
        // Set the action buttons in the "Actions" column (Edit/Delete)
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
                    Entretien Entretien = getTableView().getItems().get(getIndex());
                    System.out.println("ID Entretien "+ Entretien.getIdEntretien());
                    try {
                        onEditEntretien(Entretien);
                    } catch (Exception e) {
                        System.err.println("Erreur lors de l'édition : " + e.getMessage());
                        e.printStackTrace();
                    }
                });

                deleteButton.setOnAction(event -> {
                    
                    Entretien selectedEntretien = getTableView().getItems().get(getIndex());
                    onDeleteEntretien(selectedEntretien);
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

        // Set initial data (example, populate with your data)
        tableEntretien.setItems(entretienList);
    }

    // Add a new Entretien (call this when you want to add a new Entretien)
    public void ajouterEntretienClick(ActionEvent event) {
        // Create the dialog
        Dialog<Entretien> dialog = new Dialog<>();
        dialog.setTitle("Ajouter un entretien");
        dialog.setHeaderText("Veuillez remplir les informations de l'entretien");

        // Buttons: OK and Cancel
        ButtonType okButtonType = new ButtonType("Ajouter", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

        // Form fields
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        // Create form fields
        TextField typeField = new TextField();
        typeField.setPromptText("Type d'entretien");

        DatePicker dateField = new DatePicker();
        dateField.setPromptText("Date de l'entretien");

        TextField coutField = new TextField();
        coutField.setPromptText("Coût");

        TextField kilometrageField = new TextField();
        kilometrageField.setPromptText("Kilométrage");

        TextField notesField = new TextField();
        notesField.setPromptText("Notes");

        // Technicien (can be a combobox or textfield)
        ComboBox<Technicien> technicienComboBox = new ComboBox<>();
        technicienComboBox.setPromptText("Technicien");

        // Vehicle (also a combobox or textfield)
        ComboBox<Vehicule> vehiculeComboBox = new ComboBox<>();
        vehiculeComboBox.setPromptText("Véhicule");

        // Add fields to GridPane
        grid.add(new Label("Type d'entretien :"), 0, 0);
        grid.add(typeField, 1, 0);

        grid.add(new Label("Date :"), 0, 1);
        grid.add(dateField, 1, 1);

        grid.add(new Label("Coût :"), 0, 2);
        grid.add(coutField, 1, 2);

        grid.add(new Label("Kilométrage :"), 0, 3);
        grid.add(kilometrageField, 1, 3);

        grid.add(new Label("Notes :"), 0, 4);
        grid.add(notesField, 1, 4);

        grid.add(new Label("Technicien :"), 0, 5);
        grid.add(technicienComboBox, 1, 5);

        grid.add(new Label("Véhicule :"), 0, 6);
        grid.add(vehiculeComboBox, 1, 6);

        dialog.getDialogPane().setContent(grid);

        // Disable the OK button until all required fields are filled
        Node okButton = dialog.getDialogPane().lookupButton(okButtonType);
        okButton.setDisable(true);

        // Add listener to validate fields
        ChangeListener<Object> changeListener = (observable, oldValue, newValue) -> {
            boolean allFieldsFilled = !typeField.getText().trim().isEmpty()
                    && dateField.getValue() != null
                    && !coutField.getText().trim().isEmpty()
                    && !kilometrageField.getText().trim().isEmpty();
                    /*&& technicienComboBox.getValue() != null
                    && vehiculeComboBox.getValue() != null;*/
            okButton.setDisable(!allFieldsFilled);
        };

        // Add listeners for validation
        typeField.textProperty().addListener(changeListener);
        dateField.valueProperty().addListener(changeListener);
        coutField.textProperty().addListener(changeListener);
        kilometrageField.textProperty().addListener(changeListener);
        technicienComboBox.valueProperty().addListener(changeListener);
        vehiculeComboBox.valueProperty().addListener(changeListener);

        // Result converter to create the Entretien object
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                try {
                    // Validate numeric fields
                    if (!isNumeric(coutField.getText().trim()) || !isNumeric(kilometrageField.getText().trim())) {
                        showAlert("Erreur", "Le coût et le kilométrage doivent être des nombres.");
                        return null;
                    }

                    // Create and return the Entretien object
                    return new Entretien(
                            0, // ID can be auto-generated
                            java.sql.Date.valueOf(dateField.getValue()), // Convert LocalDate to SQL Date
                            typeField.getText().trim(),
                            Double.parseDouble(coutField.getText().trim()),
                            Integer.parseInt(kilometrageField.getText().trim()),
                            technicienComboBox.getValue(),
                            notesField.getText().trim(),
                            vehiculeComboBox.getValue(),
                            null // Assuming garage is not required in this case
                    );
                } catch (Exception e) {
                    showAlert("Erreur", "Une erreur inattendue s'est produite.");
                    return null;
                }
            }
            return null;
        });

        // Show the dialog and get the result
        Optional<Entretien> result = dialog.showAndWait();

        result.ifPresent(entretien -> {
            entretienList.add(entretien);
            tableEntretien.refresh();
            System.out.println("Entretien ajouté avec succès : " + entretien);
        });
    }


    // Edit an Entretien
    public void onEditEntretien(Entretien Entretien) {
            System.out.println("onEditEntretien appelé pour le garage : " + Entretien.getIdEntretien());
            // Configuration de la boîte de dialogue
            Dialog<Entretien> dialog = new Dialog<>();
            dialog.setTitle("Modifier le garage");
            dialog.getDialogPane().setPrefWidth(600);
            dialog.getDialogPane().setPrefHeight(700);

            // Champs de saisie dans un GridPane
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20));

            // Champs de saisie
            TextField idEntretienField = new TextField();
            idEntretienField.setPromptText("ID Entretien");
            idEntretienField.setText(String.valueOf(Entretien.getIdEntretien()));  // Valeur de l'ID du garage
            
            
            DatePicker dateEntretien = new DatePicker();
            dateEntretien.setPromptText("Date de l'entretien");
            if (Entretien.getDateEntretien() != null) {
                LocalDate localDate = Entretien.getDateEntretien().toLocalDate();
                dateEntretien.setValue(localDate);  // Set the DatePicker value
            }            
            // Champs de saisie
            TextField typeEntretien = new TextField();
            typeEntretien.setPromptText("Type de l'Entretien");
            typeEntretien.setText(String.valueOf(Entretien.getTypeEntretien()));  // Nom du garage
            
            // Fields for Adresse
            TextField coutEntretien = new TextField();
            coutEntretien.setPromptText("Cout de l'Entretien");
            coutEntretien.setText(String.valueOf(Entretien.getCout()));  // Nom du garage
            
            // Fields for Adresse
            TextField kilometrageEntretien = new TextField();
            kilometrageEntretien.setPromptText("Kilometrage");
            kilometrageEntretien.setText(String.valueOf(Entretien.getKilometrage()));  // Nom du garage

            // ComboBox pour véhicule
            ComboBox<Technicien> technicienField = new ComboBox<>();
            technicienField.getItems().addAll();
            technicienField.setPromptText("Sélectionnez un technicien");
            technicienField.setValue(Entretien.getTechnicienEnCharge());  // Sélectionne le véhicule par défaut

            // Fields for Adresse
            TextField notesEntretien = new TextField();
            notesEntretien.setPromptText("Notes de l'Entretien");
            notesEntretien.setText(String.valueOf(Entretien.getNotes()));  // Nom du garage

            ComboBox<Vehicule> vehiculeField = new ComboBox<>();
            vehiculeField.getItems().addAll(Gerant.getInstance().getVehicules());
            vehiculeField.setPromptText("Sélectionnez un véhicule");
            vehiculeField.setValue(Entretien.getVehicule());  // Sélectionne le véhicule par défaut
            
            ComboBox<Garage> garageField = new ComboBox<>();
            garageField.getItems().addAll();
            garageField.setPromptText("Sélectionnez un garage");
            garageField.setValue(Entretien.getGarage());  // Sélectionne le garage par défaut
                       

            // Add fields to the grid
            grid.add(new Label("ID Entretien:"), 0, 0);
            grid.add(idEntretienField, 1, 0);
            grid.add(new Label("date Entretien"), 0, 1);
            grid.add(dateEntretien, 1, 1);
            grid.add(new Label("Type Entretien:"), 0, 2);
            grid.add(typeEntretien, 1, 2);
            grid.add(new Label("Cout Entretien:"), 0, 3);
            grid.add(coutEntretien, 1, 3);
            grid.add(new Label("Kilometrage Entretien:"), 0, 4);
            grid.add(kilometrageEntretien, 1, 4);
            grid.add(new Label("Technicien En Charge:"), 0, 5);
            grid.add(technicienField, 1, 5);
            grid.add(new Label("Note Entretien:"), 0, 6);
            grid.add(notesEntretien, 1, 6);
            grid.add(new Label("Vehicule:"), 0, 7);
            grid.add(vehiculeField, 1, 7);
            grid.add(new Label("Garage:"), 0, 8);
            grid.add(garageField, 1, 8);

            // Add the grid to the dialog
            dialog.getDialogPane().setContent(grid);

            // Buttons
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

            // Handle OK action
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == ButtonType.OK) {
                    try {
                        // Validate numeric fields
                        if (!isNumeric(coutEntretien.getText().trim()) || !isNumeric(kilometrageEntretien.getText().trim())) {
                            showAlert("Erreur", "Le coût et le kilométrage doivent être des nombres.");
                            return null;
                        }
                        LocalDate localDate = dateEntretien.getValue();
                        java.sql.Date date = (localDate != null) 
                                    ? java.sql.Date.valueOf(localDate) 
                                    : null;                
                        Entretien.setIdEntretien(Integer.parseInt(idEntretienField.getText()));
                        Entretien.setDateEntretien(date);
                        Entretien.setTypeEntretien(typeEntretien.getText());
                        Entretien.setCout(Double.parseDouble(coutEntretien.getText()));
                        Entretien.setKilometrage(Integer.parseInt(kilometrageEntretien.getText()));
                        Entretien.setTechnicienEnCharge(technicienField.getValue());
                        Entretien.setNotes(notesEntretien.getText());
                        Entretien.setVehicule(vehiculeField.getValue());
                        return Entretien;
                        // Show success alert
                        /*Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                        successAlert.setTitle("Modification réussie");
                        successAlert.setContentText("L'entretien a été modifié avec succès.");
                        successAlert.showAndWait();*/

                    } catch (Exception e) {
                        showAlert("Erreur", "Une erreur inattendue s'est produite lors de la modification.");
                    }
                }
                return null;  // Return null if Cancel is clicked
            });
            
            dialog.showAndWait().ifPresent(updatedEntretien -> {
                tableEntretien.refresh(); // Met à jour la TableView
            });
    }

    // Delete an Entretien
    public void onDeleteEntretien(Entretien entretien) {
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Supprimer un entretien");
        confirmationDialog.setHeaderText("Confirmez-vous la suppression de cet entretien ?");
        confirmationDialog.setContentText("Entretien ID: " + entretien.getIdEntretien());

        Optional<ButtonType> result = confirmationDialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            entretienList.remove(entretien); // Remove from list
            tableEntretien.setItems(entretienList); // Refresh table view
            System.out.println("Entretien deleted: " + entretien.getIdEntretien());
        } else {
            System.out.println("Suppression annulée.");
        }
    }

    // This function checks if the given string is a valid number (integer or decimal).
    public boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        // Regular expression to match a number (including decimal point)
        return str.matches("-?\\d+(\\.\\d+)?");
    }


    

    public void showAlert(String title, String message) {
        // Create an alert of type ERROR
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);         // Set the title of the alert
        alert.setHeaderText(null);     // Set no header (optional)
        alert.setContentText(message); // Set the message content

        // Display the alert and wait for the user to close it
        alert.showAndWait().filter(response -> response == ButtonType.OK);
    }
    
    public List<Entretien> getEntretiensInitiaux() {
    // Récupère les contrats stockés dans Gerant
        return Gerant.getInstance().getEntretiens();
    }
}
