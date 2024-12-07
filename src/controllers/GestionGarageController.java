/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import models.management.Garage;



/*

    @FXML
    public void initialize() {
        // Initialize generic columns
        colIdGarage.setCellValueFactory(new PropertyValueFactory<>("idGarage"));
        colNomGarage.setCellValueFactory(new PropertyValueFactory<>("nomGarage"));
        colAdresseGarage.setCellValueFactory(new PropertyValueFactory<>("adresseGarage"));
        colNumeroTelephone.setCellValueFactory(new PropertyValueFactory<>("numeroTelephone"));
        colCapaciteGarage.setCellValueFactory(new PropertyValueFactory<>("capaciteGarage"));

        // Initialize the `colTechniciens` column with conditional data
        colTechniciens.setCellValueFactory(cellData -> {
            if (cellData.getValue().getTechniciens() != null) {
                return new SimpleStringProperty(cellData.getValue().getTechniciens());
            } else {
                return new SimpleStringProperty("Non spécifié");
            }
        });

        // Initialize `colVehiculesEntretien`
        colVehiculesEntretien.setCellValueFactory(cellData -> {
            if (cellData.getValue().getVehiculesEntretien() != null) {
                return new SimpleStringProperty(cellData.getValue().getVehiculesEntretien());
            } else {
                return new SimpleStringProperty("Aucun véhicule");
            }
        });

        // Initialize `colHorairesOuverture`
        colHorairesOuverture.setCellValueFactory(cellData -> {
            if (cellData.getValue().getHorairesOuverture() != null) {
                return new SimpleStringProperty(cellData.getValue().getHorairesOuverture());
            } else {
                return new SimpleStringProperty("Non spécifié");
            }
        });

        // Initialize `colServicesDisponibles`
        colServicesDisponibles.setCellValueFactory(cellData -> {
            if (cellData.getValue().getServicesDisponibles() != null) {
                return new SimpleStringProperty(cellData.getValue().getServicesDisponibles());
            } else {
                return new SimpleStringProperty("Aucun service");
            }
        });

        // Load initial data
        ObservableList<Garage> garageList = FXCollections.observableArrayList(getGaragesInitiaux());
        tableViewGarages.setItems(garageList);

        // Add buttons for the `Actions` column
        colActions.setCellFactory(param -> new TableCell<>() {
            private final HBox actionBox = new HBox(10);
            private final Button editButton = new Button("Éditer");
            private final Button deleteButton = new Button("Supprimer");

            {
                // Button styles
                editButton.setStyle("-fx-background-color: #5bc0de; -fx-text-fill: white; -fx-font-weight: bold;");
                deleteButton.setStyle("-fx-background-color: #d9534f; -fx-text-fill: white; -fx-font-weight: bold;");

                // Button actions
                editButton.setOnAction(event -> {
                    Garage garage = getTableView().getItems().get(getIndex());
                    onEditGarage(garage);
                });

                deleteButton.setOnAction(event -> {
                    Garage garage = getTableView().getItems().get(getIndex());
                    onDeleteGarage(garage);
                });

                actionBox.getChildren().addAll(editButton, deleteButton);
            }


        });
    }

    // Methods for retrieving initial data
    private ObservableList<Garage> getGaragesInitiaux() {
        return FXCollections.observableArrayList(
                new Garage(1, "Garage A", "123 Rue Centrale", "123456789", 50, "Technician A, B", "Car A, Car B", "8 AM - 6 PM", "Oil Change, Tire Replacement"),
                new Garage(2, "Garage B", "456 Rue Nord", "987654321", 30, "Technician X, Y", "Car X, Car Y", "9 AM - 5 PM", "Engine Repair, Cleaning")
        );
    }

    // Placeholder methods for edit and delete actions
    private void onEditGarage(Garage garage) {
        System.out.println("Editing garage: " + garage.getNomGarage());
        // Add your editing logic here
    }

    private void onDeleteGarage(Garage garage) {
        System.out.println("Deleting garage: " + garage.getNomGarage());
        // Add your deletion logic here
    }


}
*/